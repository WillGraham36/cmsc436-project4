package com.example.project4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.graphics.scale

class GameView(
    context: Context,
    private val galaxian: Galaxian,
    private val enemyBitmap: Bitmap
) : View(context) {

    private val paint = Paint().apply {
        textSize = 80f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (enemy in galaxian.enemies) {
            if (enemy.isAlive) {
                canvas.drawBitmap(enemyBitmap, enemy.x, enemy.y, null)
            }
        }

        when (galaxian.gameState) {

            GameState.WON -> {
                canvas.drawText("YOU WON !!", 300f, 1000f, paint)
                canvas.drawText("Enemies destroyed: ${galaxian.score}", 200f, 1200f, paint)
            }

            GameState.LOST -> {
                canvas.drawText("YOU LOST", 300f, 1000f, paint)
                canvas.drawText("Enemies destroyed: ${galaxian.score}", 200f, 1200f, paint)
            }

            else -> {}
        }
    }
}