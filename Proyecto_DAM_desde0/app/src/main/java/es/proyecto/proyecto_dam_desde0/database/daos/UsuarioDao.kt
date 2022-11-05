package es.proyecto.proyecto_dam_desde0.database.daos

import androidx.room.Query
import es.proyecto.proyecto_dam_desde0.database.entities.Usuario

interface UsuarioDao: BaseDao<Usuario> {
    @Query("SELECT * from usuarios where nombre_completo = :nombre_completo")
    fun findOneByName(nombre_completo: String): Usuario?

}