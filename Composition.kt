class Car(val name: String, val power: String){
    private var engine = Engine(power)
    fun startEngine() = engine.start()
    fun stopEngine() = engine.stop()
}
class Engine(power: String){
    fun start() = println("Engine has been started")
    fun stop() = println("Engine has been stopped")
}

fun main(){
    var car = Car("tico", "100hp")
    car.startEngine()
    car.stopEngine()
}
//구성(Composition)관계
//car instance를 생성함과 동시에 Engine instance도 생성
//car instance가 삭제되면 engine instance도 삭제됨
