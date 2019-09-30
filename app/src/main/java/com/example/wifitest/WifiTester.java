package com.example.wifitest;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiTester {

    protected WifiInfo wifiinfo;

    private int speed;
    private String unit;
    private int strength;
    private int frequency;

    public WifiTester(WifiManager wifiManager){
        setWifiInfo(wifiManager.getConnectionInfo());
        setStrength(WifiManager.calculateSignalLevel(wifiinfo.getRssi(), 5));
        setSpeed(wifiinfo.getLinkSpeed());
        setUnit(wifiinfo.LINK_SPEED_UNITS);
        setFrequency(wifiinfo.getFrequency());
    }

    public void setWifiInfo(WifiInfo info) {
        this.wifiinfo = info;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getSpeed() {
        return speed;
    }

    public String getUnit() {
        return unit;
    }

    public int getStrength() {
        return strength;
    }

    public int getFrequency() {
        return frequency;
    }
}
