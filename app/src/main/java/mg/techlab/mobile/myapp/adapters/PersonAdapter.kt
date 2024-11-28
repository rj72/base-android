package mg.techlab.mobile.myapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import mg.techlab.mobile.myapp.data.Person
import mg.techlab.mobile.myapp.R
import mg.techlab.mobile.myapp.capitalizeFirstLetter

class PersonAdapter(
    private val persons: List<Person>,
    private val onDeleteItem: ((Person) -> Unit),
    private val onGetItem: ((Person) -> Unit)
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val name: TextView = view.findViewById(R.id.item_name)
        val lastName: TextView = view.findViewById(R.id.item_lastname)
        val deleteIcon: ImageView = view.findViewById(R.id.delete_icon)
        val navigateIcon : ImageView = view.findViewById(R.id.navigate_icon)
        val container : ConstraintLayout = view.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount() = persons.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.name.text = persons[position].name.capitalizeFirstLetter()
        holder.lastName.text = persons[position].lastName.capitalizeFirstLetter()
        if (position % 2 == 0) holder.container.setBackgroundResource(R.color.light_grey)
        holder.deleteIcon.setOnClickListener {
            onDeleteItem.invoke(persons[position])
        }
        holder.navigateIcon.setOnClickListener {
            onGetItem.invoke(persons[position])
        }

    }
}