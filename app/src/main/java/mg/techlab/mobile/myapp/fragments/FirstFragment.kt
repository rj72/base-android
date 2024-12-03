package mg.techlab.mobile.myapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import mg.techlab.mobile.myapp.MainActivity
import mg.techlab.mobile.myapp.R
import mg.techlab.mobile.myapp.adapters.PersonAdapter
import mg.techlab.mobile.myapp.capitalizeFirstLetter
import mg.techlab.mobile.myapp.data.PersonDto
import mg.techlab.mobile.myapp.databinding.FragmentFirstBinding
import mg.techlab.mobile.myapp.datamanager.PersonManager

class FirstFragment : Fragment() {

    private var personDtos: MutableList<PersonDto> = mutableListOf()
    private lateinit var personAdapter: PersonAdapter

    private lateinit var binding: FragmentFirstBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        handleAdapterAndDate()

        binding.btnSave.setOnClickListener {
            if (binding.editName.text.isNotEmpty() && binding.editLastname.text.isNotEmpty()) {
                PersonManager.insert(
                    PersonDto(
                        name = binding.editName.text.toString(),
                        lastName = binding.editLastname.text.toString()
                    )
                ) {success ->
                    if (success) {
                        (activity as MainActivity).showToast("Insertion success")
                        personDtos = PersonManager.findAll().toMutableList()
                    }
                }
                //persons.add(Person(binding.editName.text.toString(), binding.editLastname.text.toString()))

                binding.editName.text.clear()
                binding.editLastname.text.clear()
                handleAdapterAndDate()
            } else {
                (activity as MainActivity).showToast("Please fill all fields")
            }
        }

        binding.btnGo.setOnClickListener {
            pushFragment(SecondFragment())
        }

        return binding.root
    }

    private fun pushFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    private fun handleAdapterAndDate() {
        personDtos = PersonManager.findAll().toMutableList()
        personAdapter = PersonAdapter(personDtos = personDtos, onGetItem = {
//
            pushFragment(SecondFragment())

            (activity as MainActivity).addToPreference(
                "person",
                "${it.name.capitalizeFirstLetter()}  ${it.lastName.capitalizeFirstLetter()}"
            )

        })
        binding.recyclerPerson.adapter = personAdapter
        binding.recyclerPerson.layoutManager = LinearLayoutManager(requireContext())
    }

}