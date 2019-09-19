package com.example.wifitest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView title = findViewById(R.id.title);
        final Button go = findViewById(R.id.check);
        final TextView indicator = findViewById(R.id.indicator);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isWifiGood())
                    title.setText("Wifi is Good!");
                else
                    title.setText("Wifi is not Good!");

            }
        });

    }

    private boolean isWifiGood(){
        int strength = 0;
        int speed = 0;
        String units = "";
        String ssid = "";

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        if (info.getBSSID() != null) {
            //WIFI 信号强度分为5级
            strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
            speed = info.getLinkSpeed();
            units = WifiInfo.LINK_SPEED_UNITS;
            ssid = info.getSSID();
            //如果在1级以上，给正面反馈
            if (strength > 1)
                return true;
        }
        return false;
    }
}
