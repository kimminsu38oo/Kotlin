class Patient(val name: String, var id: Int) {
    fun doctorList(d: Doctor){
        println("Patient: $name, Doctor: ${d.name}")
    }
}

class Doctor(val name: String, val p: Patient){
    val customerId = p.id

    fun patientList(){
        println("Doctor: $name, Patient: ${p.name}")
        println("Patient Id: ${customerId}")
    }
}
fun main(){
    val patient1 = Patient("Kildong",1234)
    val doctor1 = Doctor("Kimsabu", patient1)
    doctor1.patientList()
}
//Doctor class는 Patient class에 의존(Depend)함
//Doctor class가 생성되려면 Patient가 필요함