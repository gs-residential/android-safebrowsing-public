package com.safebrowsing.demo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.sclpfybn.safebrowsing.R
import com.sclpfybn.safebrowsing.detector.SafeBrowseNotifier
import com.sclpfybn.safebrowsing.detector.data.SafeBrowseResponse
import timber.log.Timber
import java.net.URLDecoder

class TestSafeNotifier(context: Context?) : SafeBrowseNotifier(context) {
    override fun getNotificationBuilder(response: SafeBrowseResponse): NotificationCompat.Builder {
        var text = context.getString(R.string.safe_browsing_service_notification_text)
        if (response?.url != null) {
            text = if (response.url.encoded) {
                val decodedURL = URLDecoder.decode(response.url.value)
                "$text $decodedURL"
            } else {
                "$text ${response.url.value}"
            }
        }
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_round)
            .setContentTitle(context.getString(R.string.safe_browsing_service_notification_title))
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
    }

    override fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.safe_browsing_service_channel_name)
            val description = context.getString(R.string.safe_browsing_service_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            context.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    override fun onNotificationCreated(id: Int) {
        handler.postDelayed({
            Timber.tag(TAG).d("Cancelling notification with ID: %d", id)
            notificationManager.cancel(id)
        }, AUTO_CANCEL_NOTIFICATION_DELAY)
    }

    companion object {
        private const val TAG = "TestSafeNotifier"
        private const val CHANNEL_ID = "SafeBrowsingService"
        private const val AUTO_CANCEL_NOTIFICATION_DELAY: Long = 3000
    }
}
