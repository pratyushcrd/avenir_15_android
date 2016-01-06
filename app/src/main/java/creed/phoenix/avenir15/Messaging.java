package creed.phoenix.avenir15;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;

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

public class Messaging extends Activity implements OnDismissCallback {
    private static final int INITIAL_DELAY_MILLIS = 300;

    static Boolean isActive = false;
    static Boolean reload = true;
    private GoogleCardsAdapter mGoogleCardsAdapter;
    SwingBottomInAnimationAdapter swingBottomInAnimationAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlecards);


        assert getActionBar() != null;
        listView = (ListView) findViewById(R.id.activity_googlecards_listview);

        mGoogleCardsAdapter = new GoogleCardsAdapter(this);
        swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter(mGoogleCardsAdapter, this));
        swingBottomInAnimationAdapter.setAbsListView(listView);

        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        listView.setAdapter(swingBottomInAnimationAdapter);
        new AsyncListViewLoader().execute("http://avenir15.com/json2/updates.json");
        isActive = true;

    }


    @Override
    protected void onResume() {

        isActive = true;
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
        finish();
        overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_out);
    }

    @Override
    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            if (reverseSortedPositions.length == position)
                position--;
            mGoogleCardsAdapter.remove(position);
        }
    }

    static String JSONResp;
    static String[] txt1, txt2, txt3;

    private class AsyncListViewLoader extends AsyncTask<String, String, List<Card>> {
        private ProgressDialog dialog;
        private int resCode;

        @Override
        protected void onPostExecute(List<Card> result) {
            super.onPostExecute(result);

            if (resCode != 200) {

            }

            listView.setAdapter(swingBottomInAnimationAdapter);

            if (reload) {
                reload = !reload;
                onStop();
                Intent i = new Intent("creed.phoenix.avenir15.Messaging");
                startActivity(i);
            }
            if(txt1!=null)
            for (i = 0; i < txt1.length; ++i) {

                try {
                    mGoogleCardsAdapter.add(txt1.length-i-1);
                } catch (Exception e) {
                }
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            txt1[i] = values[0];
            txt2[i] = values[1];
            txt3[i] = values[2];


            super.onProgressUpdate(values);
        }

        int i;

        @Override
        protected List<Card> doInBackground(String... params) {
            List<Card> result = new ArrayList<Card>();


            JSONArray arr = null;
            JSONObject jobj = null;
            byte[] b = null;
            Card card = null;
            Boolean x = JSONResp == null;
            if (!x)
                x = JSONResp.charAt(0) != '[';
            if (x)
                try {
                    // defaultHttpClient
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(params[0]);

                    HttpResponse httpResponse = httpClient.execute(httpGet);


                    JSONResp = EntityUtils.toString(httpResponse.getEntity());
                    JSONResp += "]";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            try {
                arr = new JSONArray(JSONResp);

                txt1 = new String[arr.length()];
                txt2 = new String[arr.length()];
                txt3 = new String[arr.length()];

                resCode = 201;

                for (i = 0; i < arr.length(); i++) {
                    jobj = arr.getJSONObject(i);
                    String[] send = {jobj.getString("title"), jobj.getString("text"), jobj.getString("url")};
                    onProgressUpdate(send);
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

