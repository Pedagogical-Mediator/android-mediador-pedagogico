package com.ufms.mediadorpedagogico.data.firebase

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingServiceHandler : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        FirebaseMessaging.getInstance().subscribeToTopic("Fruta")
    }

    /**
     * Chamado quando uma notificação chega e o app está em foreground
     * */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data.isNotEmpty().let {
            if (it) {

//            val bundle = Bundle()
//            val data = remoteMessage.data
//
//            for (entry in data.entries)
//                bundle.putString(entry.key, entry.value)
//
//            val dataNoticeReceived = thereIsDataFromNotification(bundle)
//
//            if (dataNoticeReceived != null) {
//                createNotification(dataNoticeReceived)
//            }
            }
        }
    }


    /**
     * Se o app está em foreground, mas não na tela de listagem -> Cria a notificação e a lógica dela
     *
     * @param dataNoticeReceived a notícia que será carregada para exibir todas as suas informações
     * */
    private fun createNotification(dataNoticeReceived: Any) {

        //Dispara o refresh da lista de notícias, caso esteja na tela de listagem
//        val refreshMessage = Intent(NewNoticeReceiver.REFRESH_ACTION)
//        refreshMessage.putExtra("tags_notification", dataNoticeReceived)
//        val wasOnListOfNoticesFragment = LocalBroadcastManager.getInstance(this).sendBroadcast(refreshMessage)
//
//
//        //Se não estava nas telas de listar notícias E notícia contém tag de interesse -> cria a notificação
//        if (!wasOnListOfNoticesFragment && noticeContainingSelectedTags(dataNoticeReceived.tag)) {
//            val intent = Intent(this, PostViewAllDetailsActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            intent.putExtra("selected_notice", dataNoticeReceived)
//
//            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
//
//            val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, "notif_fire")
//            notificationBuilder.setContentTitle("Nova notícia - ${dataNoticeReceived.title}").setContentText(dataNoticeReceived.resume).setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent).build()
//            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.notify(0, notificationBuilder.build())
//        }
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
    }
}