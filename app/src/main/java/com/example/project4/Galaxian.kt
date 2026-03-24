package com.example.project4

import kotlin.random.Random

enum class GameState {
    PLAYING,
    WON,
    LOST
}

class Galaxian(
    private val screenX: Int,
    private val screenY: Int,
    private val enemyWidth: Int
) {

    companion object {
        const val NUM_ENEMIES = 6
    }

    val enemies = ArrayList<Enemy>()
    var score = 0
    var gameState = GameState.PLAYING

    init {
        createEnemies()
    }

    fun update() {
        if (gameState != GameState.PLAYING) return

        moveEnemies()
    }

    private fun createEnemies() {

        val leftMargin = 25f
        val rightMargin = 25f
        val yPosition = 100f
        val speed = 8f

        val totalEnemyWidth = NUM_ENEMIES * enemyWidth
        val availableSpace = screenX - leftMargin - rightMargin - totalEnemyWidth

        val spacing = availableSpace / (NUM_ENEMIES - 1)

        for (i in 0 until NUM_ENEMIES) {
            val x = leftMargin + i * (enemyWidth + spacing)
            val dx = if (Random.nextBoolean()) speed else -speed
            val dy = speed  // always moving downward

            val enemy = Enemy(x, yPosition, dx, dy)

            enemy.delay = Random.nextInt(0, 240)
            enemy.isMoving = false

            enemies.add(enemy)
        }
    }

    private fun moveEnemies() {

        for (enemy in enemies) {

            if (!enemy.isAlive) continue

            // waiting
            if (!enemy.isMoving) {
                enemy.delay--

                if (enemy.delay <= 0) {
                    enemy.isMoving = true
                }

                continue
            }

            // moving
            enemy.x += enemy.dx
            enemy.y += enemy.dy

            // Bounce off left wall
            if (enemy.x <= 0) {
                enemy.x = 0f
                enemy.dx *= -1
            }

            // Bounce off right wall
            if (enemy.x + enemyWidth >= screenX) {
                enemy.x = (screenX - enemyWidth).toFloat()
                enemy.dx *= -1
            }

            // Reset if off bottom
            if (enemy.y > screenY) {
                resetEnemyToTop(enemy)
            }
        }
    }
    private fun resetEnemyToTop(enemy: Enemy) {
        enemy.y = 100f

        val speed = 8f
        enemy.dx = if (Random.nextBoolean()) speed else -speed
        enemy.dy = speed

        enemy.isMoving = false
        enemy.delay = Random.nextInt(0, 240)
    }

    fun destroyEnemy(enemy: Enemy) {

        if (!enemy.isAlive) return

        enemy.isAlive = false
        score++

        checkGameOver()
    }

    private fun checkGameOver() {

        if (score == NUM_ENEMIES) {
            gameState = GameState.WON
        }
    }

    fun loseGame() {
        gameState = GameState.LOST
    }

}