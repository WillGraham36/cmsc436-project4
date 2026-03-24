package com.example.project4

class Galaxian(
    private val screenX: Int,
    private val screenY: Int,
    private val enemyWidth: Int
) {

    companion object {
        const val NUM_ENEMIES = 6
    }

    val enemies = ArrayList<Enemy>()

    init {
        createEnemies()
    }

    private fun createEnemies() {

        val leftMargin = 25f
        val rightMargin = 25f
        val yPosition = 100f

        val totalEnemyWidth = NUM_ENEMIES * enemyWidth
        val availableSpace = screenX - leftMargin - rightMargin - totalEnemyWidth

        val spacing = availableSpace / (NUM_ENEMIES - 1)

        for (i in 0 until NUM_ENEMIES) {
            val x = leftMargin + i * (enemyWidth + spacing)
            enemies.add(Enemy(x, yPosition))
        }
    }
}