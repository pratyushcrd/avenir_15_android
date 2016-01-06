package creed.phoenix.avenir15;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratyush on 18-01-2015.
 */
public class Workshop extends Fragment {
    private static final String TAG = "CardListActivity";
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;

    public Workshop() {

    }
    public static String JSONResp = null;
    private Context cxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.worshop, container, false);

        cxt = getActivity().getApplicationContext();
        listView = (ListView) rootView.findViewById(R.id.card_listView);

        listView.addHeaderView(new View(getActivity().getApplicationContext()));
        listView.addFooterView(new View(getActivity().getApplicationContext()));

        cardArrayAdapter = new CardArrayAdapter(getActivity().getApplicationContext(), R.layout.workshop_card);

        new AsyncListViewLoader().execute("http://avenir15.com/json2/workshop_details.json");

        return rootView;
    }


    private class AsyncListViewLoader extends AsyncTask<String, Void, List<Card>> {
        private ProgressDialog dialog;
        private int resCode;
        @Override
        protected void onPostExecute(List<Card> result) {
            super.onPostExecute(result);
            dialog.dismiss();

            Log.e("res_CODE",""+resCode);
            if(resCode!=200){

                Card card = new Card("Error","no internet connection!","error");
                cardArrayAdapter.add(card);
            }
            listView.setAdapter(cardArrayAdapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Downloading details..");
            dialog.show();
        }

        @Override
        protected List<Card> doInBackground(String... params) {
            List<Card> result = new ArrayList<Card>();


            JSONArray arr = null;
            JSONObject jobj = null;
            byte[] b = null;
            Card card = null;
            Boolean x = JSONResp==null;
            if(!x)
                x = JSONResp.charAt(0)!='[';
            if(x)
            try {
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);

                HttpResponse httpResponse = httpClient.execute(httpGet);


                JSONResp = EntityUtils.toString(httpResponse.getEntity());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                arr = new JSONArray(JSONResp);
                resCode = 201;
                for (int i = 0; i < arr.length(); i++) {
                    jobj = arr.getJSONObject(i);
                    card = new Card(jobj.getString("title"), jobj.getString("text"), jobj.getString("url"));
                    cardArrayAdapter.add(card);
                    jobj = null;
                    resCode = 200;
                    card = null;
                }


                return result;
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }
    }

}
