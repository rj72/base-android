package mg.techlab.mobile.myapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mg.techlab.mobile.myapp.MainActivity
import mg.techlab.mobile.myapp.R
import mg.techlab.mobile.myapp.activities.SecondActivity
import mg.techlab.mobile.myapp.adapters.PersonAdapter
import mg.techlab.mobile.myapp.capitalizeFirstLetter
import mg.techlab.mobile.myapp.data.Person

class FirstFragment : Fragment() {

    private lateinit var editName: EditText
    private lateinit var editLastName: EditText
    private lateinit var saveButton: Button
    private var persons: MutableList<Person> = mutableListOf()
    private lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        editName = view.findViewById(R.id.edit_name)
        editLastName = view.findViewById(R.id.edit_lastname)
        saveButton = view.findViewById(R.id.btn_save)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_person)
        //val customAdapter = PersonAdapter(persons)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        saveButton.setOnClickListener {
            if (editName.text.isNotEmpty() && editLastName.text.isNotEmpty()) {
                persons.add(Person(editName.text.toString(), editLastName.text.toString()))

                editName.text.clear()
                editLastName.text.clear()
                personAdapter = PersonAdapter(persons = persons,
                    onDeleteItem = {
                        persons.remove(it)
                        personAdapter.notifyDataSetChanged()

                    }, onGetItem = {
//                        val intent = Intent(this, SecondActivity::class.java)
//                        intent.putExtra("person", "${it.name}  ${it.lastName}")
//                        startActivity(intent)

                        val fragment = SecondFragment()
                        val bundle = Bundle()
                        bundle.putString(
                            "person",
                            "${it.name.capitalizeFirstLetter()}  ${it.lastName.capitalizeFirstLetter()}"
                        )
                        fragment.arguments = bundle
                        pushFragment(fragment)

                    })
                recyclerView.adapter = personAdapter
            } else {
                (activity as MainActivity).showToast("Please fill all fields")
            }
        }

        return view
    }

    private fun pushFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }
}