package com.example.testnews.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelId = "notification_channel"
const val channelName = "com.example.testnews"

class MyFirebaseNotification: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

      if(message.getNotification() != null){
          genereateNot(message.notification!!.title!!,message.notification!!.body!!)
      }

    }

    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title: String, author: String): RemoteViews{
        val remoteView = RemoteViews("com.example.testnews", R.layout.notification)

        remoteView.setTextViewText(R.id.textView3, title)
        remoteView.setTextViewText(R.id.textView4, author)

        return remoteView
    }

    fun genereateNot(title: String, author: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


        val pendIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_baseline_newspaper_24)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendIntent)

        builder = builder.setContent(getRemoteView(title, author))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())

    }

}