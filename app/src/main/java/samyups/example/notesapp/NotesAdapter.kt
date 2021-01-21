package samyups.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import kotlinx.android.synthetic.main.notes_rv_layout.view.*


class NotesAdapter (private val context: Context?, private val notesList: RealmResults<Notes>)

    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =  LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.titleTV.text = notesList[position]!!.title
        holder.itemView.descTV.text = notesList[position]!!.description
        holder.itemView.idTV.text = notesList[position]!!.id.toString()
    }

    override fun getItemCount(): Int {

        return notesList.size
    }

    class ViewHolder(v: View?): RecyclerView.ViewHolder(v!!) {

        val title = itemView.findViewById<TextView>(R.id.titleTV)
        val desc = itemView.findViewById<TextView>(R.id.descTV)
        val id = itemView.findViewById<TextView>(R.id.idTV)
    }


}
