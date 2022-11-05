package es.proyecto.mecagoenelactivity.ui.newMedicacion;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import es.proyecto.mecagoenelactivity.R
import es.proyecto.mecagoenelactivity.app.Constantes
import es.proyecto.mecagoenelactivity.database.entities.Medicacion
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import es.proyecto.mecagoenelactivity.databinding.ActivityNewMedicacionBinding

class NewMedicacionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewMedicacionBinding
    private val viewModel: NewMedicacionViewModel by viewModels()


    private lateinit var medicacionCompleta: MedicacionCompleta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMedicacionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        intent.extras?.let {
            binding.eliminar.visibility = View.VISIBLE
            medicacionCompleta = it.getParcelable(Constantes.MEDICACION)!!
            binding.tieNombre.setText(medicacionCompleta.medicacion.nombre)
            binding.tieUso.setText(medicacionCompleta.medicacion.uso)
            binding.tieImagen.setText(medicacionCompleta.medicacion.imagen)
            binding.tieUrl.setText(medicacionCompleta.medicacion.url)
            binding.tieStock2.setText(medicacionCompleta.medicacion.stock2.toString())


        }

     fun onStart() {
            super.onStart()


            binding.eliminar.setOnClickListener {
                viewModel.eliminar(medicacionCompleta.medicacion)
                finish()
            }

            binding.guardar.setOnClickListener {
                val nombre = binding.tieNombre.text.toString()
                val uso = binding.tieUso.text.toString()
                val url = binding.tieUrl.text.toString()
                val imagen = binding.tieImagen.text.toString()
                val stock2 = binding.tieStock2.text.toString()

                if (nombre.isBlank() || uso.isBlank() || url.isBlank() || imagen.isBlank() || stock2.isBlank()) {
                    Toast.makeText(this, getString(R.string.rellenar_campos), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val medicacion = Medicacion(
                    nombre,
                    uso,
                    url,
                    imagen,
                    stock2.toDouble()

                )

                var errors = false


                if (!this::medicacionCompleta.isInitialized)
                    viewModel.save(medicacion).observe(this) {
                        if (it == -1L) {
                            Toast.makeText(
                                this,
                                getString(R.string.medicacion_existe),
                                Toast.LENGTH_SHORT
                            ).show()
                            errors = true
                        }

                    }
                else {
                    medicacion.id = this.medicacionCompleta.medicacion.id
                    viewModel.update(medicacion).observe(this) {
                        if (!it) {
                            Toast.makeText(
                                this,
                                getString(R.string.medicacion_existe),
                                Toast.LENGTH_SHORT
                            ).show()
                            errors = true
                            return@observe
                        }

                    }
                }
                if (!errors)
                    finish()
            }
        }


    }
}