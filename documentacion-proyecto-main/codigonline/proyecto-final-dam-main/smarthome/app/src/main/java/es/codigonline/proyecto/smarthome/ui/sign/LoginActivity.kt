package es.proyecto.mecagoenelactivity.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import es.proyecto.mecagoenelactivity.ui.HomeActivity
import es.proyecto.mecagoenelactivity.R
import es.proyecto.mecagoenelactivity.app.App
import es.proyecto.mecagoenelactivity.database.entities.Usuario
import es.proyecto.mecagoenelactivity.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val usuario = App.getUsuario()
        usuario?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.login.setOnClickListener {
            login()
        }
        binding.registro.setOnClickListener {
            registro()
        }
    }

    private fun login() {
        val nombre = binding.tieUsername.text.toString()
        val password = binding.tiePassword.text.toString()
        if (nombre.isBlank() || password.isBlank()) {
            App.showSnackbar(binding.root, getString(R.string.rellenar_campos))
            return
        }
        viewModel.login(Usuario(nombre, password)).observe(this) {
            if (!it.result) {
                App.showSnackbar(binding.root, it.msg)
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

    }

    private fun registro() {
        startActivity(Intent(this, RegistroActivity::class.java))
        finish()
    }


}