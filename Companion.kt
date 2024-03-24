//companion object 활용
// java의 static과 유사

class Person{
    var id: Int = 0
    var name: String = "Youngdeok"
    companion object{
        var language: String = "Korean"
        fun work(){
            println("working...")
        }
    }
}
fun main(){
    println(Person.language)
    Person.language = "English"
    println(Person.language)
    Person.work()
}