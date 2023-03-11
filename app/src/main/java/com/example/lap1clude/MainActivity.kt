package com.example.lap1clude

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    val db = Firebase.database
    val myRef = database.getReference("Person")
    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var save = findViewById<Button>(R.id.button)
        var get = findViewById<Button>(R.id.button2)
        var PersonName = findViewById<EditText>(R.id.editTextTextPersonName3)
        var PersonID = findViewById<EditText>(R.id.editTextTextPersonName4)
        var PersonAge = findViewById<EditText>(R.id.editTextTextPersonName5)
        var textData = findViewById<TextView>(R.id.textView)


        save.setOnClickListener {
            var name = PersonName.text.toString()
            var id = PersonID.text.toString()
            var age = PersonAge.text.toString()
            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )

            myRef.child("person").child("$count").setValue(person)
            count++

            Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
        }


            get.setOnClickListener {


                // Read from the database
                myRef.addValueEventListener(object: ValueEventListener{

                    override fun onDataChange(snapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val value = snapshot.getValue()
                        textData.text = value.toString()
                        Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(applicationContext, "Failler", Toast.LENGTH_SHORT).show()
                    }

                })

            }
    }
}
