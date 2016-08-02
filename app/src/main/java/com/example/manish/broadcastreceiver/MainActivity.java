package com.example.manish.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import static com.example.manish.broadcastreceiver.R.attr.title;

public class MainActivity extends AppCompatActivity {


    TextView BatteryLevel;
    ProgressBar progress;
    BroadcastReceiver bcastReceiver;


    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BatteryLevel = (TextView) findViewById(R.id.textView);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        bcastReceiver = new BatteryBroadcastReceiver();



        btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri location = Uri.parse("https://www.google.co.in/maps/place/Anna+University/@13.01185,80.2361368,17z/data=!3m1!4b1!4m5!3m4!1s0x3a5267757b955541:0xdfadd2fddf270604!8m2!3d13.0118448!4d80.2383255?hl=en");
                Intent i = new Intent(Intent.ACTION_VIEW, location);
                startActivity(i);

            }
        });
    }

    public void launchGoogleChrome(View view) {
        Intent launchGoogleChrome = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchGoogleChrome);
    }

//BroadCast Battery
    @Override
    protected void onStart() {
        registerReceiver(bcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            BatteryLevel.setText(getString(R.string.battery_level) + " " + level+"%");
            progress.setProgress(level);
        }
    }

//CUSTOM INTENT
    public void broadcastIntent(View view){
       Intent intent = new Intent();
       intent.setAction("com.manish.CUSTOM_INTENT");

        sendBroadcast(intent);

    }


}

