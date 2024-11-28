package mg.techlab.mobile.myapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mg.techlab.mobile.myapp.R
import mg.techlab.mobile.myapp.data.Joke

class ThirdFragment : Fragment() {

    private lateinit var dataView: TextView
    private lateinit var editQuery: EditText
    private lateinit var findBtn: Button
    private val url = "https://official-joke-api.appspot.com/random_joke"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        dataView = view.findViewById(R.id.view_data)
        editQuery = view.findViewById(R.id.edit_search)
        findBtn = view.findViewById(R.id.btn_search)

        findBtn.setOnClickListener {
            fetchData()
        }
        return view
    }

    private fun fetchData() {
        // on below line we are creating a variable for our
        // request queue and initializing it.
        val queue: RequestQueue = Volley.newRequestQueue(requireContext())

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null
            , { response ->
            // on below line we are setting our response to our text view.
            val joke = Joke.fromJson(response)
            dataView.text = joke.toString()
        }, { error ->
            // this method is called when we get any error and we are displaying an error message.
            dataView.text = error.message
        })

        queue.add(request)
    }


}