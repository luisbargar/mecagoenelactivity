package es.proyecto.mecagoenelactivity.database.daos
import androidx.room.Dao
import androidx.room.Query
import es.proyecto.mecagoenelactivity.database.entities.Medicacion
import es.proyecto.mecagoenelactivity.database.relations.MedicacionCompleta
import kotlinx.coroutines.flow.Flow

interface MedicacionDao: BaseDao<Medicacion> {

    @Query(
        "SELECT d.*" +
                "EXISTS (SELECT * from tomas where id_usuario=:id and id_medicacion = d.id) as toma, " +
                "EXISTS (SELECT * from stocks where id_usuario=:id and id_medicacion = d.id) as stock, " +
                "FROM medicaciones d "
    )
    fun findAll(id: Long): Flow<List<MedicacionCompleta>>

    @Query(
        "SELECT d.*" +
                "EXISTS (SELECT * from tomas where id_usuario=:id and id_medicacion = d.id) as toma, " +
                "EXISTS (SELECT * from stocks where id_usuario=:id and id_medicacion = d.id) as stock, " +
                "FROM medicaciones d "+
                "WHERE f2.id_usuario = :id"
    )
    fun findAllTomas(id: Long): Flow<List<MedicacionCompleta>>

    @Query(
        "SELECT d.*" +
                "EXISTS (SELECT * from tomas where id_usuario=:id and id_medicacion = d.id) as toma, " +
                "EXISTS (SELECT * from stocks where id_usuario=:id and id_medicacion = d.id) as stock, " +
                "FROM medicaciones d "  +
                "WHERE p.id_usuario = :id"
    )
    fun findAllStock(id: Long): Flow<List<MedicacionCompleta>>
}