package es.proyecto.proyecto_dam_desde0.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.Date

@Entity(
    tableName = "usuarios",
    indices = [
        Index(value = ["dni"], unique = true),
        Index (value= ["nombre_completo"]),
        Index (value=["fecha_nacimiento"]),
        Index (value=["nombre_medicacion"]),
        ],

    foreignKeys = [
        ForeignKey(
            entity = Medicacion::class,
            parentColumns = ["nombre"],
            childColumns = ["nombre_medicacion"],
            onUpdate = CASCADE
        )
    ]



)


data class Usuario(
    @PrimaryKey
    var dni: String,

    val nombre_completo:String,
    val fecha_nacimiento: String,
    val imagen : String,
    @ColumnInfo(name = "nombre_medicacion")
    val nombre_medicacion: String


)
