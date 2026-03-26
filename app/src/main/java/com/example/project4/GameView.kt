package com.example.project4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.media.SoundPool
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.scale
import kotlin.math.max
import kotlin.math.min

class GameView(
    context: Context,
    private val galaxian: Galaxian,
    private val enemyBitmap: Bitmap
) : View(context) {

    private lateinit var soundPool: SoundPool
    private var fireSoundId: Int = 0
    private val paint = Paint().apply {
        textSize = 80f
    }

    private val shipBitmap: Bitmap = BitmapFactory
        .decodeResource(resources, R.drawable.ship)
        .scale(150, 150, false)

    init {
        soundPool = SoundPool.Builder().setMaxStreams(5).build()

        fireSoundId = soundPool.load(context, R.raw.fire, 1)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (enemy in galaxian.enemies) {
            if (enemy.isAlive) {
                canvas.drawBitmap(enemyBitmap, enemy.x, enemy.y, null)
            }
        }

        if (galaxian.gameState == GameState.PLAYING) {
            canvas.drawBitmap(shipBitmap, galaxian.ship.x, galaxian.ship.y, null)
        }

        if (galaxian.bullet.isActive) {
            canvas.drawCircle(
                galaxian.bullet.x,
                galaxian.bullet.y,
                10f,
                paint
            )
        }

        when (galaxian.gameState) {

            GameState.WON -> {
                canvas.drawText("YOU WON !!", 365f, 1000f, paint)
                canvas.drawText("Enemies destroyed: ${galaxian.score}", 180f, 1200f, paint)
            }

            GameState.LOST -> {
                canvas.drawText("YOU LOST", 365f, 1000f, paint)
                canvas.drawText("Enemies destroyed: ${galaxian.score}", 180f, 1200f, paint)
            }

            else -> {}
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {

            MotionEvent.ACTION_MOVE -> {
                galaxian.ship.x = event.x - galaxian.ship.width / 2

                galaxian.ship.x = max(
                    0f,
                    min(width - galaxian.ship.width.toFloat(), galaxian.ship.x)
                )
            }

            MotionEvent.ACTION_DOWN -> {
                if (!galaxian.bullet.isActive) {
                    soundPool.play(fireSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
                    galaxian.bullet.isActive = true
                }
            }
        }

        return true
    }
}