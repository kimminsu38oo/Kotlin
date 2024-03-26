interface Pet{//추상 클래스로는 할 수 없음. : 다중상속이 불가능하기 때문에
    var category: String //abstract키워드가 없어도 기본은 추상 프로퍼티
    var species: String
    fun feeding() // 마찬가지로 추상 메소드
    fun patting(){ //일반 메소드. 구현부를 포함하면 일반 메소드로 됨
        println("Keep patting!")
    }
}

open class Animal(val name: String)

class Dog(name: String, override var category: String) : Animal(name), Pet{
    override var species: String = "Dog"
    override fun feeding(){
        println("Feed the dog a bone")
    }
}
class Cat(name: String, override var category: String) : Animal(name), Pet{
    override var species: String = "Cat"
    override fun feeding() {
        println("Feed the cat a tuna can")
    }
}

class Master{
    fun playWithPet2(dog: Dog){
        println("Enjoy with my dog")
    }
    fun playWithPet2(cat: Cat){
        println("Enjoy with my cat")
    }//Cat과 Dog의 공통 프로퍼티 species을 이용 안하는 경우, 오버로딩하여 여러개 함수만들어야 함

    fun playWithPet1(pet: Pet){  //Cat과 Dog의 공통 프로퍼티 이용
        println("Enjoy with my ${pet.species}")
    }
}

fun main(){
    val master = Master()
    val dog = Dog("Toto","Small")
    val cat = Cat("Coco", "BigFat")
    master.playWithPet1(dog)
    master.playWithPet1(cat)
}