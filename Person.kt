class Person(age : Int ) {
    private var age : Int = age //age의 getter setter 생성
}
fun main(){
    var p1 = Person(5)
    var p2 = Person(20)
    var p3 = Person(45)
    println(p1.age)
    println(p2.age)
    println(p3.age)
}
// 이해 안되는 코드
// Person함수에서 private으로 설정한 age변수는 get함수로 가져올 수 없나?
// 프로퍼티를 private으로 설정하면 getter도 private이 됨?
// 그러면 getter가 무슨 의미가 있을까



