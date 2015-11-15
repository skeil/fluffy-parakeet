package com.DespairBlueGmailCom.BeacgotchuOdg;

import android.app.Application;

import com.estimote.sdk.EstimoteSDK;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EstimoteSDK.initialize(getApplicationContext(), "beacgotchu-odg", "c2f43d0ad2a57ae9a3425b39e1564987");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }
}
