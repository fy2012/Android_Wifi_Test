package com.example.wifitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity {

    int speedinfo;
    String units = "";
    int strengthinfo;
    int frequencyinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final TextView title = findViewById(R.id.title);
        final Button go = findViewById(R.id.check);
        final TextView strength = findViewById(R.id.strength);
        final TextView speed = findViewById(R.id.speed);
        final TextView frequency = findViewById(R.id.frequency);
        final TextView is_good = findViewById(R.id.is_good);
        final TextView is_2_4G = findViewById(R.id.is_2_4G);

        final Vibrator z = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInfo();

                strength.setText("Strength: " + strengthinfo);

                speed.setText("Speed: " + speedinfo + " " + units);

                frequency.setText("Frequency: " + frequencyinfo + "");

                if(isWifiGood())
                    is_good.setText("Wifi is Good");
                else
                    is_good.setText("Wifi is not Good");

                if(is_2_4G())
                    is_2_4G.setText("On 2.4G Wifi");
                else
                    is_2_4G.setText("Not on 2.4G Wifi");


            // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    z.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    z.vibrate(200);
                }
            }
        });
    }

    private void getInfo(){
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

            speedinfo = info.getLinkSpeed();
            units = WifiInfo.LINK_SPEED_UNITS;
            strengthinfo = WifiManager.calculateSignalLevel(info.getRssi(), 5);
            frequencyinfo = info.getFrequency();
    }

    private boolean isWifiGood(){
        int strength;

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        //WIFI 信号强度分为5级
        strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);

        //如果在1级以上，给正面反馈
        if (strength > 1)
            return true;
        else
            return false;
    }

    private boolean is_2_4G(){

        int frequency;

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        //检测Wifi信号频率
        frequency = info.getFrequency();

        //如果频率在范围之内，给正面反馈
        if (frequency < 2800 && frequency > 2000)
            return true;
        else return false;
    }
}
