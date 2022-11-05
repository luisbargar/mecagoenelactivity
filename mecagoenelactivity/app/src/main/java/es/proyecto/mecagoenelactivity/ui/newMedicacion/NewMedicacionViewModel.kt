package es.proyecto.mecagoenelactivity.ui.newMedicacion;

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.*
import es.proyecto.mecagoenelactivity.app.App
import es.proyecto.mecagoenelactivity.database.entities.Medicacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewMedicacionViewModel : ViewModel() {


    private val medicacionDao = App.getDatabase().medicacionDao()


    fun save(medicacion: Medicacion): LiveData<Long> {
        val data = MutableLiveData<Long>()
        viewModelScope.launch {
            try {
                val id = withContext(Dispatchers.IO) {
                    medicacionDao.save(medicacion)
                }
                data.value = id
            } catch (ex: SQLiteConstraintException) {
                data.value = -1
            }
        }
        return data
    }








    fun update(medicacion: Medicacion): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    medicacionDao.update(medicacion)
                    data.postValue(true)
                } catch (ex: SQLiteConstraintException) {
                    data.postValue(false)
                }
            }
        }
        return data
    }



    fun eliminar(medicacion: Medicacion) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                medicacionDao.delete(medicacion)
            }
        }

    }


}