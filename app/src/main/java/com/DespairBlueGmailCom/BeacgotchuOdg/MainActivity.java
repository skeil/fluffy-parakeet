package com.DespairBlueGmailCom.BeacgotchuOdg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Utils;
import com.estimote.sdk.eddystone.Eddystone;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    boolean state = true;
    Beacgotchu beacgotchu = new Beacgotchu();
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView textView = (TextView) findViewById(R.id.textView);

        beaconManager = new BeaconManager(this);
        beaconManager.setEddystoneListener(new BeaconManager.EddystoneListener() {
            @Override
            public void onEddystonesFound(List<Eddystone> list) {
                for (Eddystone eddystone : list) {
                    if (eddystone.macAddress.toStandardString().contentEquals("D9:8A:E2:24:CF:19") && Utils.computeAccuracy(eddystone) < 7) {
                        Log.i("game", "Eating.");
                        beacgotchu.eat();
                    }
                    if (eddystone.macAddress.toStandardString().contentEquals("CB:07:26:90:88:1A") && Utils.computeAccuracy(eddystone) < 7) {
                        Log.i("game", "Taking a cr**.");
                        beacgotchu.crap();
                    }

//                    Log.i("tag", String.format("MAC: %s (%.2fm)", eddystone.macAddress.toStandardString(), Utils.computeAccuracy(eddystone)));
                }
            }
        });
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startEddystoneScanning();
            }
        });


        // TODO: 15/11/15 use a question mark and offer in game hints
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beacgotchu = new Beacgotchu();
                Snackbar.make(view, "Revived", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        beacgotchu.step();

                        if (beacgotchu.alive) {
                            if (state) {
                              textView.setText(
                                      "  _+    _+  " + "Hunger:              " + beacgotchu.needToEat + "\n" +
                                      " / /___/ /  " + "Bladder Fill Status: " + beacgotchu.needToPee + "\n" +
                                      " |/    \\|\n" +
                                      " |  @ @ |\n" +
                                      " |  >Y< |\n" +
                                      "  \\  v /\n" +
                                      "  /V  V\\\n" +
                                      " |      |\n" +
                                      "$$\\_&-&°");
                            } else {
                              textView.setText(
                                      "+_    +_    " + "Hunger:              " + beacgotchu.needToEat + "\n" +
                                      "\\ \\___\\ \\   " + "Bladder Fill Status: " + beacgotchu.needToPee + "\n" +
                                      " |/    \\|\n" +
                                      " |  @ @ |\n" +
                                      " |  >Y< |\n" +
                                      "  \\  v /\n" +
                                      "  /V  V\\\n" +
                                      "$|      |\n" +
                                      " $\\_&-&°");
                            }
                        } else {
                            textView.setText(
                                     "\n" +
                                     " /^\\___/ \\  " + "Hunger:              " + beacgotchu.needToEat + "\n" +
                                     " |°*   \\|°+ " + "Bladder Fill Status: " + beacgotchu.needToPee + "\n" +
                                     " |  X X |\n" +
                                     " |  >Y< |\n" +
                                     "  \\  ^ /\n" +
                                     "  /V  V\\\n" +
                                     " |      |\n" +
                                     " $\\_&-&°\n" +
                                     "$\n\n" +
                                     "YOU KILLED IT!"
                            );
                        }


                        state = !state;
                    }
                });
            }
        }, 0, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
