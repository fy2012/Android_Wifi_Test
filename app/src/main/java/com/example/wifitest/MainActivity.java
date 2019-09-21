package com.example.wifitest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button go = findViewById(R.id.check);
        final TextView strength = findViewById(R.id.strength);
        final TextView speed = findViewById(R.id.speed);
        final TextView frequency = findViewById(R.id.frequency);
        final TextView is_good = findViewById(R.id.is_good);
        final TextView is_2_4G = findViewById(R.id.is_2_4G);

        final Button share = findViewById(R.id.share);

        final Vibrator z = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            WifiTester wifiTester = new WifiTester((WifiManager) getSystemService(WIFI_SERVICE));

            strength.setText("Strength: " + wifiTester.getStrength());

            speed.setText("Speed: " + wifiTester.getSpeed() + " " + wifiTester.getUnit());

            frequency.setText("Frequency: " + wifiTester.getFrequency() + " Mhz");

            if(wifiTester.getStrength() > 1)
                is_good.setText("Wifi is Good");
            else
                is_good.setText("Wifi is not Good");

            if(wifiTester.getFrequency() < 2800)
                is_2_4G.setText("On 2.4G Wifi");
            else
                is_2_4G.setText("Not on 2.4G Wifi");

            // Vibrate for 200 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                z.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                z.vibrate(200);
            }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
