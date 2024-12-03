package mg.techlab.mobile.myapp.data

import mg.techlab.mobile.myapp.randomUUID

data class PersonDto(
    var id: String = randomUUID,
    var name: String = "",
    var lastName: String = ""
){
    override fun toString(): String {
        return "$name $lastName"
    }
}
