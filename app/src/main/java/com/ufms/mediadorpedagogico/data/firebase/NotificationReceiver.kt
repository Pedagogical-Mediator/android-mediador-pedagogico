//package com.ufms.mediadorpedagogico.data.firebase
//
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//
//
//class NotificationReceiver() : BroadcastReceiver() {
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        val tags = intent?.extras?.getParcelable("tags_notification")
//    }
//
//    companion object {
//        val REFRESH_ACTION = "current.activity.actio"
//        val REFRESH_FILTER = IntentFilter(REFRESH_ACTION)
//    }
//}