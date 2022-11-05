package es.proyecto.mecagoenelactivity.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import es.proyecto.mecagoenelactivity.R
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import es.proyecto.mecagoenelactivity.databinding.MedicacionBinding

class MedicacionesRecyclerViewAdapter(
    val list: List<MedicacionCompleta>,
    private val listener: DeviceListener,
    val context: Context
) :
    RecyclerView.Adapter<MedicacionesRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder private constructor(
        private val binding: MedicacionBinding,
        private val listener: DeviceListener,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(data: MedicacionCompleta) {
            binding.root.setOnClickListener {
                listener.details(data)
            }
            binding.nombre.text = data.medicacion.nombre
            binding.stock2.text = context.getString(R.string.show_stock2, data.medicacion.stock2)
           binding.URL.text = context.getString(R.string.externo)
            binding.URL.setTextColor(Color.BLUE)
            binding.URL.setOnClickListener {
                listener.open(data.medicacion.url)
            }
            binding.edit.setOnClickListener {
                listener.edit(data)
            }
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
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide
                .with(context)
                .load(data.medicacion.imagen)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(binding.imagen)

            binding.toma2.setOnClickListener {
                if (data.tomas) {
                    listener.delTomas(data.medicacion.id)
                    binding.toma2.setIconTintResource(R.color.md_theme_light_outline)
                } else {
                    binding.toma2.setIconTintResource(R.color.red)
                    listener.addTomas(data.medicacion.id)
                }
            }
            binding.stock.setOnClickListener {
                if (data.stock) {
                    listener.delStock(data.medicacion.id)
                    binding.stock.setIconTintResource(R.color.md_theme_light_outline)
                } else {
                    listener.addStock(data.medicacion.id)
                    binding.stock.setIconTintResource(R.color.green)
                }
            }
        }

        companion object {
            fun newInstance(
                parent: ViewGroup, listener: DeviceListener, context: Context
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MedicacionBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, listener, context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.newInstance(parent, listener, context)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.rellenarDatos(list[position])

    override fun getItemCount() = list.size



}

interface DeviceListener {
    fun open(url: String)
    fun addTomas(id: Long)
    fun delTomas(id: Long)
    fun addStock(id: Long)
    fun delStock(id: Long)
    fun details(medicacionCompleta: MedicacionCompleta)
    fun edit(medicacionCompleta: MedicacionCompleta)
}