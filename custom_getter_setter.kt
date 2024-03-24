//custom getter setter
class User(_id: Int,_name: String, _age: Int) {
    val id = _id
    var name: String = _name
        set(value){
            println("The name was changed")
            field = value.uppercase()
        }
        //get() = "**" + name + "**"  stack overflow code. because "name" calls other getter. name means get()
        get() = "**" + field + "**" //field is real name
    var age: Int = _age
}
fun main(){
    val user1 = User(1,"kildong",35)
    user1.name = "COCO"
    println("${user1.name}")
}