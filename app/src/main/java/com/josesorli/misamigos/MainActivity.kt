package com.josesorli.misamigos

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var consultabutton: Button
    private lateinit var resultado: TextView

    private lateinit var db: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        saveButton = findViewById(R.id.saveButton)
        consultabutton =findViewById(R.id.consultabutton)
        resultado = findViewById(R.id.resultado)

        db = DatabaseHandler(this)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val id = db.addContact(name, email)
                if (id != -1L) {
                    // Éxito al guardar en la base de datos
                    // Puedes mostrar un mensaje de éxito o realizar alguna otra acción aquí
                    Toast.makeText(applicationContext, "Guardado con exito", Toast.LENGTH_LONG).show()
                    nameEditText.text.clear()
                    emailEditText.text.clear()
                } else {
                    // Ocurrió un error al guardar en la base de datos
                    // Puedes mostrar un mensaje de error o realizar alguna otra acción aquí
                }
            } else {
                // Los campos están vacíos, muestra un mensaje de error o realiza alguna otra acción aquí
                Toast.makeText(applicationContext, "Te falta algún campo por rellenar", Toast.LENGTH_SHORT).show()
            }
        }
        consultabutton.setOnClickListener{
            val contactlist = db.getAllContact()
            resultado.text=""

            for(contact in contactlist){
                //Log.d("Contacto","ID:${contact.id}, nombre: ${contact.name}, Email: ${contact.email}")
                resultado.append("ID:${contact.id}, NOMBRE:${contact.name}, EMAIL:${contact.email}\n")
               // resultado.append("NOMBRE: $contact.name\n")
            }
        /*val texto = contactlist.joinToString()
            resultado.text=""
            resultado.text=texto*/

        }
    }
}
