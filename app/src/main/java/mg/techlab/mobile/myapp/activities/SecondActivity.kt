package mg.techlab.mobile.myapp.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import mg.techlab.mobile.myapp.R

class SecondActivity : AppCompatActivity() {
    private lateinit var nameView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        nameView = findViewById(R.id.txt_data)
        val name = intent.getStringExtra("person")
        nameView.text = name
    }
}