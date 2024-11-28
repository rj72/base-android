package mg.techlab.mobile.myapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import mg.techlab.mobile.myapp.fragments.FirstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        addFirstFragment(FirstFragment())

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

    fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


}