package es.proyecto.mecagoenelactivity.ui.home

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import es.proyecto.mecagoenelactivity.databinding.FragmentMedicacionesBinding
import es.proyecto.mecagoenelactivity.ui.adapters.DeviceListener
import es.proyecto.mecagoenelactivity.ui.adapters.MedicacionesRecyclerViewAdapter

enum class EstadoMedicacion(val tipo: Int) {
    MEDICACIONES(1), TOMAS(2), STOCKS(3)
}

class CommonFragmentImpl(
    val deviceListener: DeviceListener,
    val context: Context,
    val binding: FragmentMedicacionesBinding
) {
    private lateinit var mAdapterMedicaciones: MedicacionesRecyclerViewAdapter
    fun createRecyclerView(medicaciones: List<MedicacionCompleta>) {
        mAdapterMedicaciones =
            MedicacionesRecyclerViewAdapter(
                medicaciones as MutableList<MedicacionCompleta>,
                deviceListener,
                context
            )
        val recyclerView = binding.rvHome
        recyclerView.apply {
            //EL RECYCLER VIEW VA A SER UNA LISTA VERTICAL
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mAdapterMedicaciones
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }
}