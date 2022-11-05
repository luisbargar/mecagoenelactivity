package es.proyecto.mecagoenelactivity.database.daos

import androidx.room.Query
import es.proyecto.mecagoenelactivity.database.entities.Stock

interface StockDao : BaseDao<Stock> {
    @Query("DELETE FROM stocks where id_medicacion=:id")
    fun deleteByDeviceId(id: Long)

}