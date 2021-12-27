package com.android.training.helloworld

open class Vampire(name: String, hitPoints: Int = 20): Enemy(name, hitPoints,3) {
    override fun takeDamage(damage: Int) {
        super.takeDamage(damage / 2) //calls the function inherited from Enemy Class
    }
}