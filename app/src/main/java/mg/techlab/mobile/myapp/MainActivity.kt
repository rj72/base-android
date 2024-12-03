package mg.techlab.mobile.myapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import mg.techlab.mobile.myapp.fragments.FirstFragment
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        addFirstFragment(FirstFragment())
        initializeRealmDatabase()

    }

    private fun addFirstFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_fragment, fragment)
            //.addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    private fun popFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_fragment, fragment)
            .commit()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addToPreference(key: String, value: Any) {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
        }
        editor.apply()
    }

    fun getFromPreference(key: String, defaultValue: Any): Any? {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        return when (defaultValue) {
            is String -> sharedPreference.getString(key, defaultValue)
            is Int -> sharedPreference.getInt(key, defaultValue)
            is Long -> sharedPreference.getLong(key, defaultValue)
            is Boolean -> sharedPreference.getBoolean(key, defaultValue)
            is Float -> sharedPreference.getFloat(key, defaultValue)
            else -> null
        }
    }


    fun addToPref() {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("me", "moi")
        editor.apply()
    }

    fun getFromPref(): String {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val person = sharedPreference.getString("me", "no name")
        return person!!
    }

    //initializing database
    private fun initializeRealmDatabase() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .schemaVersion(4)
            .deleteRealmIfMigrationNeeded()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    fun showConfirmDialog(title: String, message: String, completion: ((Boolean) -> Unit)) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OUI") { _, _ -> completion.invoke(true) }
            .setNegativeButton("NON") { _, _ -> completion.invoke(false) }
            .show()
    }


}