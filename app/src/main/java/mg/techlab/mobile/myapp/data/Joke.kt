package mg.techlab.mobile.myapp.data

import org.json.JSONObject

data class Joke(
    val type: String,
    val setup: String,
    val punchline: String,
    val id: Int
) {
    companion object {
        fun fromJson(json: JSONObject): Joke {
            return Joke(
                type = json.getString("type"),
                setup = json.getString("setup"),
                punchline = json.getString("punchline"),
                id = json.getInt("id")
            )
        }
    }

    override fun toString(): String {
        return "ID:$id\nType: $type\nSetup: $setup\nPunchline: $punchline"
    }
}