package com.android.training.helloworld

class Player constructor(var name:String = "", var lives:Int = 0, var level:Int = 0, var score:Int = 0){

    var weapon = Weapon("Fist", 1) //it can change
    val inventory = ArrayList<Loot>()

    fun showInfo() {
        if (lives > 0){
            println("Player $name is alive!")
        } else {
            println("Player $name is dead")
        }
    }

    override fun toString(): String{
        return """
            Player information
            -------------------
            name: $name 
            lives: $lives 
            level: $level
            score: $score         
            weapon: ${weapon.name}
            damage_inflicted: ${weapon.damageInflicted}
        """
    }

    fun getLoot(item: Loot){
        inventory.add(item)
    }

    fun dropLoot(name: String): Boolean{ //returns Boolean ; overloading functions
        //println("$name will be dropped")
        //return inventory.removeIf{ it.name == name }
        for(item in inventory){
            if(item.name == name){
                inventory.remove(item)
                return true
            }
        }
        return false
    }

    fun dropLoot(item: Loot): Boolean{
        return if(inventory.contains(item)){
            inventory.remove(item)
                true
            } else {
                false
            }
    }

    fun showInventory() {
        var total = 0.0

        println("$name 's inventory \\n")
        //println(inventory.get(0))
        for(item in inventory){
            println(item)
            total += item.value
        }
        println("--------------------------------")
        println("Total score: $total")
        println("--------------------------------")

    }
}