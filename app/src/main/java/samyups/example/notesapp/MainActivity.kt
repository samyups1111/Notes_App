package samyups.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults

class MainActivity : AppCompatActivity() {

    private lateinit var addNotes: FloatingActionButton
    private lateinit var notesRV: RecyclerView
    private lateinit var notesList: ArrayList<Notes>
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init views

        realm = Realm.getDefaultInstance()
        addNotes = findViewById(R.id.addNotes)
        notesRV = findViewById(R.id.notesRV)

        // onclick add notes button

        addNotes.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity:: class.java))
            finish()
        }

        notesRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        getAllNotes()
    }

    private fun getAllNotes() {

        notesList = ArrayList()

        val results: RealmResults<Notes> = realm.where<Notes>(Notes::class.java).findAll()

        notesRV.adapter = NotesAdapter (this, results)

        notesRV.adapter!!.notifyDataSetChanged()
    }

}
