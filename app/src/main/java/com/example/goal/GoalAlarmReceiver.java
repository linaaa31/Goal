package com.example.goal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
public class GoalAlarmReceiver extends BroadcastReceiver {

        private static final String NOTIFICATION_CHANNEL_ID = "goal_reminder_channel";
        private static final int NOTIFICATION_ID = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            String question = intent.getStringExtra("question");
           // int notificationId = intent.getIntExtra("notificationId", 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Goal Reminder", NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("Reminds you to work towards your goals");
                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Goal Reminder")
                    .setContentText(question)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            // Show the notification
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

//public class GoalAlarmReceiver extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        String question = intent.getStringExtra("question");
//
////        Log.i("ALM", "Received alarm, use intent to pass data");
////        NotificationHelper.show(context, "Goal", "Notification from goal");
////        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.cartoon);
////        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
////        mediaPlayer.start();
//    }
//}