package mg.techlab.mobile.myapp.datamanager

import mg.techlab.mobile.myapp.data.PersonDo
import mg.techlab.mobile.myapp.datamanager.base.BaseRepository

class PersonRepository : BaseRepository<PersonDo>(ofType = PersonDo::class.java) {

    fun findByName(name: String) =
        realm?.where(ofType)?.equalTo("name", name)?.findFirst() ?: PersonDo()
}