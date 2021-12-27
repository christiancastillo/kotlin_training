package com.android.training.helloworld

enum class LootType{
    POTION, RING, ARMOR, SHIELD
}

class Loot(val name: String, val type: LootType, val value: Double) {
    override fun toString():String{
    return "$name , type: $type value: \$${value}"
    }
}