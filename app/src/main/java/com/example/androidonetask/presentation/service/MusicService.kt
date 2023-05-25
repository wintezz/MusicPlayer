package com.example.androidonetask.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.androidonetask.presentation.fragment.bottom.BottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicService : Service() {

    private var bottomSheet: BottomSheet? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bottomSheet?.initializationPlayer(applicationContext)

        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }
}