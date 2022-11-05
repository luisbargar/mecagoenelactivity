package es.proyecto.proyecto_dam_desde0.database.daos

import androidx.room.Query
import es.proyecto.proyecto_dam_desde0.database.entities.Medicacion

interface MedicacionDao: BaseDao<Medicacion> {

    @Query("SELECT * from medicaciones where nombre = :nombre_medicacion")
    fun findOneByName(nombre_medicacion: String): Medicacion?
}