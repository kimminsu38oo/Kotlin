class InvalidSelectException(messege: String) : Exception(messege)
class NullException(messege: String) : Exception(messege)
class NotEnoughMoneyException(messege: String) : Exception(messege)

val README="""
    Earn 50000$ as soon as possible!
    You are farmer and you want to increase your assets.
    To raise your asset, you can either buy animals from Seller or do farming.
    animals price and value is different. Price is higher than current value.
    But as time goes by the price and value is higher gradually.
    You should buy livestock early! You might not be able to buy them later.
    
    version 1
"""

class Operator(val player : Farmer,val seller: Seller){
    var day = 1
    fun run(){
        while(true) {
            if(player.asset()>=50000){
                println("The End!")
                println("It took $day days")
                break
            }
            player_status()
            while (true) {
                try {
                    player_select()
                    break
                } catch (e: InvalidSelectException) {
                    println(e.message)
                }catch(e: NullException){
                    println(e.message)
                }catch(e: NotEnoughMoneyException){
                    println(e.message)
                }
                continue
            }

            day_passed()
        }
    }

    fun buy(){
        println("---------------------------------------")
        if(seller.animals.size==0) throw InvalidSelectException("Seller have no animal")
        for(i in seller.animals.indices) println("${i+1}. ${seller.animals[i].name} | price: ${seller.animals[i].price} | value: ${seller.animals[i].value}")
        print("Select the number you want to buy: ")
        val animal_number: Int? = readLine()?.toInt()
        if(animal_number!=null)
            if (animal_number < 1 || animal_number > seller.animals.size) throw InvalidSelectException("Select right number")
            else{
                val animal_price = seller.animals[animal_number-1].price
                if(player.money.current_money < animal_price)
                    throw NotEnoughMoneyException("You don't have enough money to buy this animal")
                else{
                    println("You Successfully bought new ${seller.animals[animal_number-1].name}")
                    player.money.sub_money(animal_price)
                    seller.money.add_money(animal_price)
                    player.animals.add(seller.animals[animal_number-1])
                    seller.animals.removeAt(animal_number-1)
                }
            }
        else throw NullException("null error")
    }
    fun sell(){
        println("-------------------You have ${player.money.current_money}--------------------")
        if(player.animals.size==0) throw InvalidSelectException("You have no animal")
        for(i in player.animals.indices) println("${i+1}. ${player.animals[i].name} | price: ${player.animals[i].price} | value: ${player.animals[i].value}")
        print("Select the number you want to sell: ")
        val animal_number: Int? = readLine()?.toInt()
        if(animal_number!=null)
            if (animal_number < 1 || animal_number > player.animals.size) throw InvalidSelectException("Select right number")
            else{
                val animal_price = player.animals[animal_number-1].price
                if(seller.money.current_money < animal_price)
                    throw NotEnoughMoneyException("Seller don't have enough money to buy this animal")
                else{
                    println("You Successfully sold your ${player.animals[animal_number].name}")
                    player.money.add_money(animal_price)
                    seller.money.sub_money(animal_price)
                    seller.animals.add(player.animals[animal_number-1])
                    player.animals.removeAt(animal_number-1)
                }
            }
            else throw NullException("null error")
    }
    fun player_status(){
        println("--------------------your_asset-------------${day}days---")
        println("Money: ${player.money.current_money}")
        println("Animals: ")
        if (!player.animals.isEmpty()) {
            for (i in player.animals.indices) {
                println("        ${i+1}. ${player.animals[i].name} | value: ${player.animals[i].value} ")
            }
        } else {
            println("None")
        }
        println("Current asset: ${player.asset()}")
    }
    fun player_select() {
        println("--------------------------------------------------")
        println("1. Buy animals from seller ")
        println("2. Sell animals to seller")
        println("3. Do farming +300$")
        print("Select the number you want to do: ")
        when (readLine()?.toInt()) {
            1 -> buy()
            2 -> sell()
            3 -> player.farming()
            else -> throw InvalidSelectException("error")
        }
    }
    fun day_passed(){
        day++
        for(i in player.animals.indices) player.animals[i].aging()
        for(i in seller.animals.indices) seller.animals[i].aging()
    }
}

interface Person{
    var money: Money
    val animals: MutableList<Animal>
}

class Farmer(override var money : Money,
             override val animals: MutableList<Animal> = mutableListOf()
             ): Person {

    fun farming() {
        println("--------------------------------------------------")
        println("farming . . .")
        money.add_money(300)
    }

    fun asset(): Int {
        var current_asset = money.current_money
        for (animal in animals) {
            current_asset += animal.value
        }
        return current_asset
    }
}

class Seller(override var money: Money,
             override val animals: MutableList<Animal> = mutableListOf()) : Person{
    init{
        for(add_animal: Int in 1..10){
            animals.add(Cow())
            animals.add(Pig())
        }
    }
}
class Money(var current_money: Int){
    fun add_money(money: Int){
        current_money+=money
    }
    fun sub_money(money: Int){
        current_money-=money
    }
}

interface Animal {
    val name: String
    var price: Int
    var value: Int
    fun aging()
}
class Pig(override val name: String = "Pig",
          override var price: Int = 1000,
          override var value: Int = 800):Animal{
    override fun aging() {
        price = (price+(price*0.04)).toInt()
        value = (price*0.8).toInt()
    }
}
class Cow(override val name: String = "Cow",
          override var price: Int = 2000,
          override var value: Int = 1500):Animal{
    override fun aging() {
        price = (price+(price*0.06)).toInt()
        value = (price*0.75).toInt()
    }
}

fun main() {
    println(README)
    var turn = 0
    var player = Farmer(Money(10000))
    var seller = Seller(Money(50000))
    val operator = Operator(player,seller)
    operator.run()
}