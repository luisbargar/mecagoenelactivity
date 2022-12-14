package es.proyecto.mecagoenelactivity.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import es.proyecto.mecagoenelactivity.ui.HomeActivity
import es.proyecto.mecagoenelactivity.R
import es.proyecto.mecagoenelactivity.app.App
import es.proyecto.mecagoenelactivity.database.entities.Usuario
import es.proyecto.mecagoenelactivity.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private val viewModel: UsuarioViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.cancelar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.registro.setOnClickListener {
            registro()
        }
    }

    private fun registro() {
        val nombre = binding.tieUsername.text.toString()
        val password = binding.tiePassword.text.toString()
        val password2 = binding.tieConfirmPassword.text.toString()
        if (nombre.isBlank() || password.isBlank() || password2.isBlank()) {
            App.showSnackbar(binding.root, getString(R.string.rellenar_campos))
            return
        }

        if (password != password2) {
            App.showSnackbar(binding.root, getString(R.string.contrasenyas_no_coinciden))
            return
        }
        viewModel.save(Usuario(nombre, password)).observe(this) {
            if (!it) {
                App.showSnackbar(binding.root, getString(R.string.error_crear_usuario))
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}