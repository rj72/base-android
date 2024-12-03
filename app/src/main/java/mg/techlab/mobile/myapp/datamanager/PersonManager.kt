package mg.techlab.mobile.myapp.datamanager

import mg.techlab.mobile.myapp.data.PersonDto
import mg.techlab.mobile.myapp.data.PersonDo
import mg.techlab.mobile.myapp.datamanager.base.BaseManager

object PersonManager : BaseManager<PersonDo, PersonDto, PersonRepository, PersonFactory>(
    repository = PersonRepository(),
    factory = PersonFactory,
    pkFieldName = "id"

){
    fun findByName(name: String) : PersonDto {
        val personDo = repository.findByName(name)
        val personDto = factory.toDataTransferObject(personDo)
        return personDto
    }
}