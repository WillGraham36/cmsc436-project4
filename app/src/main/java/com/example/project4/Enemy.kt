package com.example.project4

class Enemy(
    var x: Float,
    var y: Float,
    var dx: Float,
    var dy: Float
) {
    var isAlive = true
    var isMoving = false
    var delay = 0
}