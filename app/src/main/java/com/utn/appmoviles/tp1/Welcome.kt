package com.utn.appmoviles.tp1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Welcome : AppCompatActivity() {

    private lateinit var tvBienvenida: TextView
    private lateinit var usuario: String
    private lateinit var rgPlataforma: RadioGroup
    private lateinit var ivLogoPlataforma: ImageView
    private lateinit var etOtraPreferencia: EditText
    private lateinit var cbOtra : CheckBox
    private lateinit var btnContinuar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()

    }

    private fun initComponents(){
        // Obtengo el nombre de usuario pasado desde MainActivity
        usuario = intent.getStringExtra("user").toString()

        // Referenciar el TextView y asignar el mensaje de bienvenida
        tvBienvenida = findViewById(R.id.tvBienvenida)
        rgPlataforma = findViewById(R.id.rgPlataforma)
        ivLogoPlataforma = findViewById(R.id.ivLogoPlataforma)
        cbOtra = findViewById(R.id.cbOtra)
        etOtraPreferencia = findViewById(R.id.etOtraPreferencia)
        btnContinuar = findViewById(R.id.btnContinuar)
        tvBienvenida.text = "Bienvenido a la aplicación, $usuario"

    }

    private fun initListeners() {
        rgPlataforma.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAndroid -> ivLogoPlataforma.setImageResource(R.drawable.android_logo)
                R.id.rbIOS -> ivLogoPlataforma.setImageResource(R.drawable.ios_logo)
            }
        }

        cbOtra.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Si el CheckBox está marcado, mostrar el EditText
                etOtraPreferencia.visibility = View.VISIBLE
            } else {
                // Si el CheckBox no está marcado, ocultar el EditText
                etOtraPreferencia.visibility = View.GONE
            }
        }

        btnContinuar.setOnClickListener {
            // Aquí puedes manejar la lógica para continuar a la siguiente pantalla
            // o procesar la información seleccionada
        }
    }
}