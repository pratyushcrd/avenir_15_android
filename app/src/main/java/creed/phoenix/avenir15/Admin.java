package creed.phoenix.avenir15;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Fragment {

    EditText e1, e2, e3, e4;
    Button b1, b2;
    RelativeLayout r1, r2;

    public Admin() {
        // Required empty public constructor
    }

    final String s1 = "http://avenir15.com/json2/update.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin, container, false);

        e1 = (EditText) rootView.findViewById(R.id.admin_et1);
        e2 = (EditText) rootView.findViewById(R.id.admin_et2);
        e3 = (EditText) rootView.findViewById(R.id.admin_et3);
        e4 = (EditText) rootView.findViewById(R.id.admin_et4);
        b1 = (Button) rootView.findViewById(R.id.admin_bt1);
        b2 = (Button) rootView.findViewById(R.id.admin_bt2);
        r1 = (RelativeLayout) rootView.findViewById(R.id.admin_View1);
        r2 = (RelativeLayout) rootView.findViewById(R.id.admin_View2);

        r2.setVisibility(View.GONE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new back().execute(e1.getText().toString().toLowerCase());

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Post Update?")
                        .setMessage("Title :" + e2.getText().toString() + "\nText:" + e3.getText().toString() + "\nPosted By:" + e4.getText().toString())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                String[] str = {s1,e2.getText().toString(),e3.getText().toString(),e4.getText().toString()};
                                new send().execute(str);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        return rootView;
    }


    class back extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Working");
            dialog.show();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid.equals("creed15xavenir")) {
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.VISIBLE);

            } else {
                Toast.makeText(getActivity(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
            }

            dialog.hide();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return params[0];
        }
    }

    class send extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Working");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);

            if (aVoid) {
                Toast.makeText(getActivity(), "Successfully Posted!", Toast.LENGTH_SHORT).show();
                Messaging.JSONResp = null;
            } else {
                Toast.makeText(getActivity(), "Failure!", Toast.LENGTH_SHORT).show();
            }


            dialog.hide();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String response = "false";
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpP = new HttpPost(params[0]);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
           
                JSONObject jo = new JSONObject();
                try {
                    jo.put("title", params[1]);
                    jo.put("text", params[2]);
                    jo.put("url", params[3]);
                    nameValuePairs.add(new BasicNameValuePair("json", jo.toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                httpP.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse httpResponse = httpClient.execute(httpP);
                if (httpResponse.getStatusLine().getStatusCode() == 200)
                    response = EntityUtils.toString(httpResponse.getEntity());

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!response.equals("true"))
                response = "false";
            Boolean a = Boolean.parseBoolean(response);
            return a;
        }
    }
}