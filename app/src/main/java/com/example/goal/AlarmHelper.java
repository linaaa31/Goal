package com.example.goal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.goal.db.Goal;

import java.util.Calendar;

public class AlarmHelper {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    public void scheduleAlarm(Context context, Goal goal) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, GoalAlarmReceiver.class);
        intent.putExtra("question", goal.getQuestion());
        int notificationFrequency = Integer.parseInt(goal.getFrequency());
        pendingIntent = PendingIntent.getBroadcast(context, goal.getGoalId() , intent,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(System.currentTimeMillis());
        start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(goal.getStartHour()));
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(System.currentTimeMillis());
        end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(goal.getEndHour()));
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);

        long intervalMillis = notificationFrequency * 1000 * 60; // *60
        long triggerAtMillis = System.currentTimeMillis();
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, pendingIntent);
    }

    public void stopAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}




