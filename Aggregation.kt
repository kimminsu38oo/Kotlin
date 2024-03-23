class Pond(_name: String, _member: MutableList<Duck>){
    val name: String = _name
    val members: MutableList<Duck> = _member
    constructor(_name: String): this(_name, mutableListOf<Duck>())
}
class Duck(val name: String)

fun main(){
    val pond = Pond("myFavorite")
    val duck1 = Duck("Duck1")
    val duck2 = Duck("Duck2")

    pond.members.add(duck1)
    pond.members.add(duck2)

    for (duck in pond.members){
        println(duck.name)
    }
}

//집합(Aggregation) 관계
// 연관 관계와 거의 비슷하지만 특정 객체를 소유한다는 개념 추가
// 오리가 특정 연못을 주거지로 삼는다면 연못이 오리를 소유할 수 있음