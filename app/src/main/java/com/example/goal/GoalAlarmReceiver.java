package com.example.goal;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class GoalAlarmReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        String goal = intent.getStringExtra("goal");
        String question = intent.getStringExtra("question");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setContentTitle(goal)
                .setContentText(question)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Set the intent that will be launched when the user taps on the notification
        Intent answerIntent = new Intent(context, AnswerActivity.class);
        answerIntent.putExtra("goal", goal);
        answerIntent.putExtra("question", question);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, answerIntent, 0);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
