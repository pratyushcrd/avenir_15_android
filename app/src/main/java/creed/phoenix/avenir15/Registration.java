package creed.phoenix.avenir15;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by PRASHANT on 06-04-2015.
 */
public class Registration extends Fragment {

    WebView wv;
    Button bt;
    public Registration(){
        // Required Empty Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.registration, container, false);
        bt= (Button) root.findViewById(R.id.btreg);
        wv = (WebView) root.findViewById(R.id.reg);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                wv.loadUrl("http://goo.gl/forms/SRMIi9nCYq");

            }
        });

        return root;
    }
}
