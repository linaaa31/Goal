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

    public void scheduleAlarm(Context context, Goal goal, long frequencyInMillis) {
        Intent intent = new Intent(context, GoalAlarmReceiver.class);
        intent.putExtra("goal_id", goal.getGoalId());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, goal.getGoalId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long triggerTime = System.currentTimeMillis() + frequencyInMillis;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, alarmIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, alarmIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, alarmIntent);
        }
    }

    public void stopAlarm(Context context, int goalId) {
        Intent intent = new Intent(context, GoalAlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, goalId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(alarmIntent);
        alarmIntent.cancel();
    }
}
//    public void stopAlarm() {
//        if (alarmManager != null) {
//            alarmManager.cancel(pendingIntent);
//        }
//    }
//}





