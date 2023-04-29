package com.example.goal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

//public class NotificationHelper {
//    final static String CHANNEL_ID = "goal_reminder_channel";
//    public static void createChannel(Context context) {
//        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Goal Channel", NotificationManager.IMPORTANCE_HIGH);
//        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); // to display on locked screen
//
//        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
//        notificationManager.createNotificationChannel(channel);
//    }
//
//    public static void show(Context context, String title, String content) {
//        if (context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//
//
//            Intent intent = new Intent(context, GoalsActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.notification_icon)
//                    .setContentTitle(title)
//                    .setContentText(content)
//                    .setContentIntent(pendingIntent)
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//            Random random = new Random();
//            int notificationId = random.nextInt(1000);
//            notificationManager.notify(notificationId, builder.build());
//        }
//    }
//}
