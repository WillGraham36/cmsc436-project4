package com.example.project4

import java.util.TimerTask

class GameTimerTask(
    private val galaxian: Galaxian,
    private val gameView: GameView
) : TimerTask() {

    override fun run() {
        galaxian.update()

        gameView.post {
            gameView.invalidate()
        }
    }
}