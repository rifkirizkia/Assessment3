package org.d3if1139.penghitungkalori.network

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.d3if1139.penghitungkalori.MainActivity
import org.d3if1139.penghitungkalori.R

class UpdateWorker (
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    private val notifId = 44
    override fun doWork(): Result {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        val builder = NotificationCompat.Builder(
            applicationContext,
            MainActivity.CHANNEL_ID
        )
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.getString(R.string.notif_title))
            .setContentText(applicationContext.getString(R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        NotificationManagerCompat.from(applicationContext)
            .notify(notifId, builder.build())
        return Result.success()
    }
}