package com.example.project4

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.scale
import java.util.Timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenX = resources.displayMetrics.widthPixels
        val screenY = resources.displayMetrics.heightPixels

        val original = BitmapFactory.decodeResource(resources, R.drawable.enemy)

        val enemySize = screenX / 10
        val enemyBitmap = original.scale(enemySize, enemySize, false)

        val galaxian = Galaxian(screenX, screenY, enemyBitmap.width)

        // Create view
        val gameView = GameView(this, galaxian, enemyBitmap)
        setContentView(gameView)

        val timer = Timer()
        timer.schedule(GameTimerTask(galaxian, gameView), 0, 30)
    }
}