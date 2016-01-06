package creed.phoenix.avenir15;

import android.app.Application;

import com.pushbots.push.Pushbots;

/**
 * Created by Pratyush on 09-02-2015.
 */

public class MyApplication extends Application {

    String sender_id = "863330984113";
    String api_key = "AIzaSyBFNubf72ayUSDtW5y9wyMLR0uvUYXzWPA";
    String application_id = "54d87db91d0ab13c058b45dc";

    @Override
    public void onCreate() {
        super.onCreate();
        Pushbots.init(this, sender_id, application_id);
        Pushbots.getInstance().setMsgReceiver(CustomPushReceiver.class);
    }
}