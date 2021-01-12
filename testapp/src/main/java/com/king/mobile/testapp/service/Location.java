package com.king.mobile.testapp.service;

import java.util.Timer;
import java.util.TimerTask;

public class Location {

    public static void start(LocationListener locationListener) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int longitude = (int) (Math.random() * 1000);
                int latitude = (int) (Math.random() * 1000);
                locationListener.onLocated(longitude, latitude);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000 * 3);
    }


    public interface LocationListener {
        void onLocated(int longitude, int latitude);
    }
}
