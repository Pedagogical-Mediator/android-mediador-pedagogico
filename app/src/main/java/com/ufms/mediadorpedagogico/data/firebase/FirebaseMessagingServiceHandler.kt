package com.ufms.mediadorpedagogico.data.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.data.storage.PreferencesCache
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity

class FirebaseMessagingServiceHandler : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        PreferencesCache.init(applicationContext).set(KEY_TOPIC_TOKEN, p0)
    }

    /**
     * Chamado quando uma notificação chega e o app está em foreground
     * */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data.isNotEmpty().let {
            if (it) {
                createNotification(remoteMessage.data["titulo"])
            }
        }
    }


    /**
     * Se o app está em foreground, mas não na tela de listagem -> Cria a notificação e a lógica dela
     *
     * @param dataNoticeReceived a notícia que será carregada para exibir todas as suas informações
     * */
    private fun createNotification(title: String?) {
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(
                "notif_fire",
                "notif_fire",
                importance
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, "notif_fire")
        notificationBuilder
            .setContentTitle("Novidades chegando - $title")
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent)
        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {
        const val KEY_TOPIC_NEWS = "Noticias"
        const val KEY_TOPIC_NOTICES = "Avisos"
        const val KEY_TOPIC_TOKEN = "KEY_TOPIC_TOKEN"
    }
}