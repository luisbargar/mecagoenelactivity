package es.proyecto.mecagoenelactivity.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentManager
import es.codigonline.proyecto.smarthome.ui.newDevice.NewMedicacionActivity
import es.proyecto.mecagoenelactivity.app.Constantes
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import es.proyecto.mecagoenelactivity.ui.adapters.DeviceListener
import es.proyecto.mecagoenelactivity.ui.dialogs.MedicacionDialog

class MedicacionListenerImpl(
    val context: Context,
    val viewModel: HomeViewModel,
    private val fragmentManager: FragmentManager
) : DeviceListener {


    override fun edit(medicacionCompleta: MedicacionCompleta) {
        val intent = Intent(context, NewMedicacionActivity::class.java)
        intent.putExtra(Constantes.MEDICACION, medicacionCompleta)
        context.startActivity(intent)

    }

    override fun open(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    override fun addTomas(id: Long) {
        viewModel.addTomas(id)
    }

    override fun delTomas(id:Long) {
        viewModel.delTomas(id)
    }

    override fun addStock(id: Long) {
        viewModel.addStock(id)
    }

    override fun delStock(id: Long) {
        viewModel.delStock(id)
    }

    override fun details(medicacionCompleta: MedicacionCompleta) {
        val deviceFragment = MedicacionDialog(medicacionCompleta)
        deviceFragment.show(fragmentManager, Constantes.MEDICACION)
    }

}