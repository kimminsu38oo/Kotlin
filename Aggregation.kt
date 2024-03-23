class Patient(val name: String) {
    fun doctorList(d: Doctor){
        println("Patient: $name, Doctor: ${d.name}")
    }
}

class Doctor(val name: String){
    fun patientList(p: Patient){
        println("Doctor: $name, Patient: ${p.name}")
    }
}
fun main(){
    val doctor1 = Doctor("Kimsabu")
    val patient1 = Patient("Kildong")
    patient1.doctorList(doctor1)
    doctor1.patientList(patient1)
}
//서로 독립적인 생명 주기를 가짐
//이 코드에서는 두 클래스가 서로의 객체를 참조하고 있으므로 양방향 참조를 가짐
//단방향이든, 양방향이든 각각의 각체의 생명주기에 영향을 주지 않을 때 연관관계라고 함