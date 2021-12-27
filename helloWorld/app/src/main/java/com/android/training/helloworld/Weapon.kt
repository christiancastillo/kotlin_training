package com.android.training.helloworld

class Weapon constructor(val name: String, var damageInflicted: Int,){

    override fun toString(): String{
        return "$name inflicts $damageInflicted points of damage"
    }
    fun getWeapon(): String {
        return name
    }

    fun getDamage(): Int{
        return damageInflicted
    }
}