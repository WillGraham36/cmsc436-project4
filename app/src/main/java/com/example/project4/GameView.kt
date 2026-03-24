package com.example.project4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View
import androidx.core.graphics.scale

class GameView(
    context: Context,
    private val galaxian: Galaxian,
    private val enemyBitmap: Bitmap
) : View(context) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (enemy in galaxian.enemies) {
            if (enemy.isAlive) {
                canvas.drawBitmap(enemyBitmap, enemy.x, enemy.y, null)
            }
        }
    }
}