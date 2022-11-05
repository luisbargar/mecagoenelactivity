package es.proyecto.mecagoenelactivity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import es.proyecto.mecagoenelactivity.app.App
import es.proyecto.mecagoenelactivity.database.daos.StockDao
import es.proyecto.mecagoenelactivity.database.entities.Stock
import es.proyecto.mecagoenelactivity.database.entities.Toma
import es.proyecto.mecagoenelactivity.database.entities.Usuario
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val medicacionDao= App.getDatabase().medicacionDao()
    private val tomaDao = App.getDatabase().tomaDao()
    private val stockDao = App.getDatabase().stockDao()

    fun medicaciones (tipo:EstadoMedicacion): LiveData<List<MedicacionCompleta>> {
        return when (tipo.tipo) {
            1 -> medicacionDao.findAll(App.getUsuario()!!.id).asLiveData()
            2 -> medicacionDao.findAllTomas(App.getUsuario()!!.id).asLiveData()
            else -> medicacionDao.findAllStock(App.getUsuario()!!.id).asLiveData()
        }
    }
    fun addTomas(medicacionId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tomaDao.save(Toma(medicacionId, App.getUsuario()!!.id))
            }
        }
    }

    fun delTomas(medicacionId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tomaDao.delete(Toma(medicacionId, App.getUsuario()!!.id))
            }
        }
    }
    fun addStock(medicacionId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                stockDao.save(Stock(medicacionId, App.getUsuario()!!.id))
            }
        }
    }

    fun delStock(medicacionId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                stockDao.delete(Stock(medicacionId, App.getUsuario()!!.id))
            }
        }
    }




}