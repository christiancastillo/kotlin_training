package com.android.training.helloworld

open class Enemy(val name: String, var hitPoints: Int, var lives: Int) { //por defecto no se hereda, se pone open para declarar que si se pueda
    open fun takeDamage(damage: Int){
        val remainingHitPoints = hitPoints - damage
        if (remainingHitPoints > 0){
            hitPoints = remainingHitPoints
            println("$name took $damage points of damage, and has $hitPoints left")
        } else {
            lives-= 1
            if (lives > 0){
                println("$name lost a life")
            } else {
                println("No lives left, $name is dead")
            }
        }
    }

    override fun toString(): String {
        return "Name: $name, Hntpoints: $hitPoints, Lives: $lives"
    }
}