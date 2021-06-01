package com.jeongseok.petcare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    public static boolean flag = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(flag) {
            NotificationHelper notificationHelper = new NotificationHelper(context);
            notificationHelper.createNotification();
        }
    }
}