package com.android.training.helloworld

fun main(args: Array<String>){
//    val yamil = Player("Yamil",5,1,10)
//    val aleida = Player("Aleida", 10,2,30)
//    yamil.showInfo()
//    aleida.showInfo()
//
//    //println(one2watch.weapon.name.uppercase())
//    //println(one2watch.weapon.damageInflicted)
//
//    val sword = Weapon("Sword",70)
//    val spear = Weapon("Spear",90)
//
//    yamil.weapon = sword
//    println("${yamil.name}\'s "+yamil.weapon)
//    yamil.showInfo()
//    aleida.weapon = yamil.weapon
//    aleida.showInfo()
//    yamil.weapon = spear
//    yamil.showInfo()
//
//    val redPotion = Loot("Red Potion", LootType.POTION, 7.50)
//    val chestArmor = Loot("Chest Armor", LootType.ARMOR, 80.0)
//    yamil.getLoot(redPotion)
//    //yamil.inventory.add(chestArmor)
//    yamil.getLoot(chestArmor)
//    //yamil.inventory.add(Loot("Ring of protection", LootType.RING,65.0))
//    yamil.getLoot(Loot("Ring of protection", LootType.RING,65.0))
//    //yamil.inventory.add(Loot("Invisibility potion", LootType.POTION, 25.0))
//
//    yamil.showInventory()
//    if(yamil.dropLoot(redPotion)){
//        yamil.showInventory()
//    } else {
//        println("You don't have a ${redPotion.name}")
//    }
//
//    val bluePotion = Loot("Blue Potion",LootType.POTION,40.60)
//
//    if (yamil.dropLoot(bluePotion)){
//        yamil.showInventory()
//    } else {
//        println("You dno't have a ${bluePotion.name}")
//    }
//
//    if (yamil.dropLoot("Invisibility Potion") ) {
//        yamil.showInventory()
//    } else {
//        println("You don't have an Invisibility Potion")
//    }
//
//    //println(yamil)
//
////    for(i in 0..10){ //.. = 0 until 10
////        println("$i squared is ${i*i}")
////    }
////    println("=============================")
////    for (i in 10 downTo 0){ //downTo
////        println("$i squared is ${i*i}")
////    }
////    println("=============================")
////    println("STEPS....")
////    for(i in 0..10 step 2){ //.. = 0 until 10
////        println("$i squared is ${i*i}")
////    }
////    println("CHALLENGE....")
////    for(i in 0 until 100){
////        if(i%5==0){
////            println(i)
////        }
////    }
    //val uglyTroll = Troll("Ugly Troll", 27, 1)
//    val uglyTroll = Troll("Ugly Troll")
//    println(uglyTroll)
//    uglyTroll.takeDamage(30)
//    println(uglyTroll)
//
//    val vlad = Vampire("Vlad")
//    println(vlad)
//    vlad.takeDamage(8)
//    println(vlad)
//
//    val dracula = VampireKing("Dracula")
//    println(dracula)
//    while(dracula.lives > 0){
//        if (dracula.dodges()){
//            continue //sigue con el siguiente bloque
//        }
//        if(dracula.runAway()){
//            println("Dracula ran away!!")
//            break;
//        } else {
//            dracula.takeDamage(12)
//        }
//    }

    ///for (i in 1..10) {

/*        val dracula = VampireKing("Dracula")
        dracula.lives = 0
        println(dracula)
        do {
            if (dracula.dodges()) {
                continue //sigue con el siguiente bloque
            }
            if (dracula.runAway()) {
                println("Dracula ran away!!")
                break
            } else {
                dracula.takeDamage(12)
            }
        println("--------------------------------------")
        }
        while(dracula.lives > 0)*/
//        while (dracula.lives > 0) {
//            if (dracula.dodges()) {
//                continue //sigue con el siguiente bloque
//            }
//            if (dracula.runAway()) {
//                println("Dracula ran away!!")
//                break;
//            } else {
//                dracula.takeDamage(12)
//            }
//        }
//        println("battle number $i")
//        println("--------------------------------------")
    //}

    val conan = Player("Conan")

    conan.getLoot(Loot("Invisibility", LootType.ARMOR, 1.0))
    conan.getLoot(Loot("Mithryl", LootType.ARMOR, 1.0))
    conan.getLoot(Loot("Ring of speed", LootType.RING, 1.0))
    conan.getLoot(Loot("Ring of speed", LootType.RING, 1.0))
    conan.getLoot(Loot("Ring of speed", LootType.RING, 1.0))
    conan.getLoot(Loot("Red potion", LootType.POTION, 1.0))
    conan.getLoot(Loot("Greatshield", LootType.SHIELD, 1.0))
    conan.showInventory()

    conan.dropLoot("Ring of speed")
    conan.showInventory()

    val dropped = conan.dropLoot("Inexistent Loot")
    println(dropped)
    println(conan.dropLoot("Something else"))
    if (conan.dropLoot("A bit of junk")){
        println("Junk dropped")
    } else {
        println("You don't have a junk")
    }

}