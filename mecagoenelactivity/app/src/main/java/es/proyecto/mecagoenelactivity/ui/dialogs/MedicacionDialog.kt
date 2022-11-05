package es.proyecto.mecagoenelactivity.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import es.proyecto.mecagoenelactivity.R
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import es.proyecto.mecagoenelactivity.databinding.FullMedicacionBinding
import es.proyecto.mecagoenelactivity.ui.home.HomeViewModel

class MedicacionDialog(val data: MedicacionCompleta) : DialogFragment() {

    lateinit var binding: FullMedicacionBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.full_medicacion, null)
            binding = FullMedicacionBinding.bind(view)
            binding.nombre.text = data.medicacion.nombre
            binding.uso.text = data.medicacion.uso
            binding.stock2.text = getString(R.string.show_stock2, data.medicacion.stock2)
            binding.url.text = getString(R.string.externo)
            binding.url.setTextColor(Color.BLUE)
            binding.url.setOnClickListener {

            }

            val circularProgressDrawable = CircularProgressDrawable(requireContext())
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide
                .with(requireContext())
                .load(data.medicacion.imagen)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(binding.imagen)



            if (data.tomas) {
                binding.toma2.setIconTintResource(R.color.red)

            } else {
                binding.toma2.setIconTintResource(R.color.md_theme_light_outline)
            }
            if (data.stock) {
                binding.stock.setIconTintResource(R.color.green)
            } else {
                binding.stock.setIconTintResource(R.color.md_theme_light_outline)
            }

            binding.toma2.setOnClickListener {
                if (data.tomas) {
                    homeViewModel.delTomas(data.medicacion.id)
                    binding.toma2.setIconTintResource(R.color.md_theme_light_outline)
                } else {
                    binding.toma2.setIconTintResource(R.color.red)
                    homeViewModel.addTomas(data.medicacion.id)
                }
            }
            binding.stock.setOnClickListener {
                if (data.stock) {
                    homeViewModel.delStock(data.medicacion.id)
                    binding.stock.setIconTintResource(R.color.md_theme_light_outline)
                } else {
                    homeViewModel.addStock(data.medicacion.id)
                    binding.stock.setIconTintResource(R.color.green)
                }
            }





            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}