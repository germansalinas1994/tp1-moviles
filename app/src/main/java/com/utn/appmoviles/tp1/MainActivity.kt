package com.utn.appmoviles.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val USUARIO_CORRECTO = "Juan Torres"
const val PASSWORD_CORRECTA = "1234utn"


class MainActivity : AppCompatActivity() {
    //esto es porque no se pueden usar nulos pero despues la vamos a inicializar

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistrarse: Button

    private var usuarioActual = ""
    private var passwordActual = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //buenas practicas
        initComponents()
        //los listeners son patrones de diseño que esperar a que suceda una accion
        initListeners()
    }

    private fun initComponents() {
        //cuando inicie la aplicacion, la variable etusuario va a estar con el valor del id
        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)

    }

    private fun initListeners() {

        btnLogin.setOnClickListener() {
            usuarioActual = etUsuario.text.toString()
            passwordActual = etPassword.text.toString()


            if (validarUsuarioRegistrado(usuarioActual)) {
                //valido que la pass sea correcta
                if (validarPassword(passwordActual)) {
                    //cambiar a pantalla de bienvenido con el nombre de usuario
                    val intent = Intent(this, Welcome::class.java)
                    intent.putExtra(
                        "user",
                        usuarioActual
                    )  // Pasar el nombre de usuario a la nueva actividad
                    startActivity(intent)
                } else {
                    etPassword.error = "Contraseña incorrecta."
                }
            } else {
                etUsuario.error = "Usuario incorrecto."
            }
        }

        btnRegistrarse.setOnClickListener() {
            //cambiar a pantalla de bienvenido con el nombre de usuario
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }


    private fun validarUsuarioRegistrado(email: String): Boolean {
        return email == USUARIO_CORRECTO
    }

    private fun validarPassword(pass: String): Boolean {
        return pass == PASSWORD_CORRECTA
    }

}