package com.ufms.mediadorpedagogico.data.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity


class FirebaseMessagingServiceHandler : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        with(FirebaseMessaging.getInstance()) {
            subscribeToTopic(KEY_TOPIC_NOTICES)
            subscribeToTopic(KEY_TOPIC_NEWS)
        }
    }

    /**
     * Chamado quando uma notificação chega e o app está em foreground
     * */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data.isNotEmpty().let {
            if (it) {
                createNotification(remoteMessage.data["titulo"], remoteMessage.data["descricao"])
            }
        }
    }


    /**
     * Se o app está em foreground, mas não na tela de listagem -> Cria a notificação e a lógica dela
     *
     * @param dataNoticeReceived a notícia que será carregada para exibir todas as suas informações
     * */
    private fun createNotification(title: String?, description: String?) {
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
            .setContentText("Descricao - $description")
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent)
        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {

        /**
         * @param data os dados vindos do firebase para a notificação
         *
         * @return Se existirem todos os dados (check de segurança) -> Cria uma notícia e retorna-a
         * @return Se tiver algo de errado (um campo obrigatório faltando) -> Retorna null
         * */
//        fun thereIsDataFromNotification(data: Bundle): Notice? {
//            return if (data["author"] != null && data["id"] != null && data["tag"] != null && data["title"] != null) {
//                Notice()
//                    .withId((data["id"] as String).toInt())
//                    .withTitle(data["title"] as String)
//                    .withDate(data["date"] as String)
//                    .withAuthor(data["author"] as String)
//                    .withImg(data["img"] as String?)
//                    .withFullText(data["fullText"] as String?)
//                    .withResume(data["resume"] as String?)
//                    .withLink(data["link"] as String?)
//                    .withTag(data["tag"] as String)
//            } else {
//                null
//            }
//        }
        const val KEY_TOPIC_NEWS = "Noticias"
        const val KEY_TOPIC_NOTICES = "Avisos"
    }
}