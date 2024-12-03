package mg.techlab.mobile.myapp.datamanager

import mg.techlab.mobile.myapp.data.PersonDto
import mg.techlab.mobile.myapp.data.PersonDo
import mg.techlab.mobile.myapp.datamanager.base.BaseFactory

object PersonFactory : BaseFactory<PersonDo, PersonDto> {
    override fun toDomainObject(value: PersonDto) = PersonDo().apply {
        id = value.id
        name = value.name
        lastname = value.lastName
    }

    override fun toDataTransferObject(value: PersonDo) = PersonDto(
        id = value.id,
        name = value.name,
        lastName = value.lastname
    )
}