package mg.techlab.mobile.myapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import mg.techlab.mobile.myapp.MainActivity
import mg.techlab.mobile.myapp.data.PersonDto
import mg.techlab.mobile.myapp.R
import mg.techlab.mobile.myapp.capitalizeFirstLetter
import mg.techlab.mobile.myapp.datamanager.PersonManager
import mg.techlab.mobile.myapp.getParentActivity

class PersonAdapter(
    private val personDtos: List<PersonDto>,
    //private val onDeleteItem: ((Person) -> Unit),
    private val onGetItem: ((PersonDto) -> Unit)
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val name: TextView = view.findViewById(R.id.item_name)
        val lastName: TextView = view.findViewById(R.id.item_lastname)
        val deleteIcon: ImageView = view.findViewById(R.id.delete_icon)
        val navigateIcon: ImageView = view.findViewById(R.id.navigate_icon)
        val container: ConstraintLayout = view.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount() = personDtos.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.name.text = personDtos[position].name.capitalizeFirstLetter()
        holder.lastName.text = personDtos[position].lastName.capitalizeFirstLetter()
        if (position % 2 == 0) holder.container.setBackgroundResource(R.color.light_grey)
        holder.deleteIcon.setOnClickListener {

            it.getParentActivity<MainActivity>()?.apply {
                showConfirmDialog(
                    "Delete",
                    "Are you sure you want to delete this item?") {ok ->
                    if (ok){
                        PersonManager.delete(personDtos[position]) { deleted ->
                            if (deleted) {
                                showToast("Item deleted")
                                recreate()
                            }
                        }
                    }
                }
            }
            //onDeleteItem.invoke(persons[position])
        }
        holder.navigateIcon.setOnClickListener {
            onGetItem.invoke(personDtos[position])
        }

    }
}