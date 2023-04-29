package com.example.goal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.goal.db.Goal;

import java.util.Calendar;

public class AlarmHelper {
    public void scheduleAlarm(Context context, Goal goal) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        Intent intent = new Intent(context, GoalAlarmReceiver.class);
        intent.putExtra("question", goal.getQuestion());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        long timeInMillis = System.currentTimeMillis() + Long.parseLong(goal.getFrequency()) * 1000;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }

}

//    public void scheduleAlarm(Context context, Goal goal) {
//        int notificationFrequency = Integer.parseInt(goal.getFrequency());
//        int startHour = Integer.parseInt(goal.getStartHour());
//        int endHour = Integer.parseInt(goal.getEndHour());
//
//        int startHour24 = startHour % 12 + (startHour < 12 ? 0 : 12);
//        int endHour24 = endHour % 12 + (endHour < 12 ? 0 : 12);
//
//        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, GoalAlarmReceiver.class);
//        intent.putExtra("question", goal.getQuestion());
//        alarmIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.cancel(alarmIntent);
//
//        Calendar now = Calendar.getInstance();
//        int currentHour = now.get(Calendar.HOUR_OF_DAY);
//
//        int nextNotificationTime = currentHour * 60 + (notificationFrequency - (currentHour % notificationFrequency)) * 60;
//
//        if (nextNotificationTime >= startHour24 * 60 && nextNotificationTime < endHour24 * 60) {
//            nextNotificationTime = endHour24 * 60 + (notificationFrequency - (endHour24 * 60 % notificationFrequency)) % notificationFrequency;
//        }
//        //        int nextNotificationTime = currentHour + (notificationFrequency - (currentHour % notificationFrequency));
////
////        if (nextNotificationTime >= startHour24  && nextNotificationTime <endHour24) {
////            nextNotificationTime = endHour24 + (notificationFrequency - (endHour24 % notificationFrequency));
////        }
//        // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, now.getTimeInMillis() + (nextNotificationTime - currentHour) * 60 * 60 * 1000, interval, alarmIntent);
////        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S || alarmManager.canScheduleExactAlarms()) {
////
////            alarmManager.setExact(AlarmManager.RTC_WAKEUP, now.getTimeInMillis() + (nextNotificationTime - currentHour) * 60 * 60 * 1000, alarmIntent);
////        }
//
//    }}
////    public void stopAlarm() {
////        if (alarmManager != null) {
////            alarmManager.cancel(alarmIntent);
////        }
////    }
////}


