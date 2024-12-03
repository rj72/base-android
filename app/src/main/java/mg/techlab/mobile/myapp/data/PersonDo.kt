package mg.techlab.mobile.myapp.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PersonDo : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var name: String = "---"
    var lastname: String = "---"
}