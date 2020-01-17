package com.example.schoolassistant;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class TimeNotification extends BroadcastReceiver {


    static String text = "Test";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //NotificationCompat.Builder builder;

        builder.setContentTitle("Уведомдение").setContentText(text).setSmallIcon(R.mipmap.ic_launcher);

        //Notification notification = new Notification(R.mipmap.ic_launcher, "Test", System.currentTimeMillis());

        //Интент для активити, которую мы хотим запускать при нажатии на уведомление
        Intent intentTL = new Intent(context, MainActivity.class);

        //builder.setContentIntent(PendingIntent.getActivity(PendingIntent.getActivity(context,0,intentTL,PendingIntent.FLAG_CANCEL_CURRENT));
        //builder.setContentIntent(intentTL);

        Notification notification = builder.build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        nm.notify(1, notification);
        // Установим следующее напоминание.

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY, pendingIntent);
    }


}
