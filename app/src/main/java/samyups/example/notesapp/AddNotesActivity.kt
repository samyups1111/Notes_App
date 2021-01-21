package samyups.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm

private lateinit var titleED: EditText
private lateinit var descriptionED: EditText
private lateinit var saveNotesBtn: Button
private lateinit var realm: Realm


class AddNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        // init views

        realm = Realm.getDefaultInstance()
        titleED = findViewById(R.id.title_EditText)
        descriptionED = findViewById(R.id.description_EditText)
        saveNotesBtn = findViewById(R.id.saveNotesBtn)

        // onclick Listener

        saveNotesBtn.setOnClickListener {
            addNotesToDB()

        }
    }

    private fun addNotesToDB() {
        try {
            realm.beginTransaction()

            val currentIdNumber: Number? = realm.where(Notes::class.java).max("id")
            val nextID: Int

            nextID = if (currentIdNumber == null) {
                1
            } else {
                currentIdNumber.toInt() + 1
            }

            val notes = Notes()

            notes.title = titleED.text.toString()
            notes.description = descriptionED.text.toString()
            notes.id = nextID

            // copy this transaction and commit

            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()

            Toast.makeText(this, "Notes Added Successfully", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
            finish()




        } catch (e: Exception) {
            Toast.makeText(this, "Failed $e", Toast.LENGTH_SHORT).show()
        }
    }
}