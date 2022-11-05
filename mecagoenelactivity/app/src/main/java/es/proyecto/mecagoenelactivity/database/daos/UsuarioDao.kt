package es.proyecto.mecagoenelactivity.database.daos

import androidx.room.Query
import es.proyecto.mecagoenelactivity.database.entities.Usuario

interface UsuarioDao: BaseDao<Usuario> {
    @Query("SELECT * from usuarios where nombre = :nombre")
    fun findOneByName(nombre: String): Usuario?

}