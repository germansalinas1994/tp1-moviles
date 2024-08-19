package com.utn.appmoviles.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Register : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRepetirPassword: EditText
    private lateinit var btnRegistrarse: Button

    private var nombre = ""
    private var email = ""
    private var password = ""
    private var repeatPass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()

    }

    private fun initComponents(){
        etNombre = findViewById(R.id.etNombre)
        etPassword = findViewById(R.id.etPassword)
        etRepetirPassword = findViewById(R.id.etRepetirPassword)
        etEmail = findViewById(R.id.etEmail)
        btnRegistrarse = findViewById(R.id.btnRegistrar)

    }

    private fun initListeners(){
        btnRegistrarse.setOnClickListener(){

            var formularioValido = ValidarFormulario()

            if(formularioValido){
//                 Si todo es correcto, se realiza el registro (en este caso, simplemente se retorna a login)
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                // Volver a la pantalla de login
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()            }
        }
    }

    private fun ValidarFormulario(): Boolean{
        nombre = etNombre.text.toString()
        password = etPassword.text.toString()
        repeatPass = etRepetirPassword.text.toString()
        email = etEmail.text.toString()

        if(nombre.isEmpty()){
            etNombre.error = "El nombre no puede estar vacío"
            return false
        }
        if (!validarEmail(email))
            return false


        if(!validarPass(password,repeatPass))
            return false


        return true
    }
    private fun validarEmail(email: String): Boolean{
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(email.isEmpty()){
            etEmail.error = "El email no puede estar vacío"
            return false
        }
        if(!email.matches(emailPattern.toRegex())) {
            etEmail.error = "Por favor, ingrese un email válido"
            return false
        }
        return true
    }

    private fun validarPass(pass: String, passRepe : String): Boolean{
        if(pass.length < 6){
            etPassword.error = "La contraseña debe tener al menos 6 caracteres"
            return false
        }
        if(pass != passRepe){
            etRepetirPassword.error = "Las contraseñas no coinciden"
            return false
        }

        return true
    }
}