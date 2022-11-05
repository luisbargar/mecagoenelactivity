package es.proyecto.mecagoenelactivity.database.entities

import androidx.room.*

@Entity(
    tableName = Toma.TABLE_NAME,
    primaryKeys = ["id_usuario", "id_medicacion"],
    indices = [Index(value = ["id_usuario"]), Index(value = ["id_medicacion"])],
    foreignKeys = [ForeignKey(
        entity = Medicacion::class,
        parentColumns = ["id"],
        childColumns = ["id_medicacion"],
        onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id"],
        childColumns = ["id_usuario"],
        onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE
    )]
)
data class Toma(
    @ColumnInfo(name = "id_medicacion") val dispositivoId: Long,
    @ColumnInfo(name = "id_usuario") val usuarioId: Long
) {

    companion object {
        const val TABLE_NAME = "tomas"
    }

}