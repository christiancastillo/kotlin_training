package com.android.training.helloworld

import java.util.*

class VampireKing(name: String):Vampire(name) {
    init {
        hitPoints = 140
    }

    fun runAway(): Boolean{
        return lives < 2
        /*if (lives < 2 ){
            return true
        } else {
            return false
        }*/
    }

    fun dodges():Boolean{
        val rand = Random()
        val seed = rand.nextInt(6)
        if (seed > 3){
            println("Dracula dodges!")
            return true
        }
        return false
    }
    override fun takeDamage(damage: Int) {
        super.takeDamage(damage / 2)
    }
}