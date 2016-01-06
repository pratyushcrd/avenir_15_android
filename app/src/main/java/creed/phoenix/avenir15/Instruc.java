package creed.phoenix.avenir15;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Instruc extends Activity {

    static boolean fin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruc);

        if(fin){
            finish();
        }

        fin = true;
    }

    public void clk(View v){
        finish();
    }
}
