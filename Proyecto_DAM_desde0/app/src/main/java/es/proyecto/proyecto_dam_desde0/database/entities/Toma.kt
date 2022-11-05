package es.proyecto.proyecto_dam_desde0.database.entities

import androidx.room.*
import java.util.Date

@Entity(
    tableName = "tomas",
    indices = [

      Index(value= ["nombre_medicacion"], unique = true),
    Index(value= ["horario"]),
    Index(value=["dni_usuario"], unique = true)


    ],

    foreignKeys = [
        ForeignKey(
            entity = Medicacion::class,
            parentColumns = ["nombre"],
            childColumns = ["nombre_medicacion"],
            onUpdate = ForeignKey.CASCADE
        ),
                ForeignKey(
                entity = Usuario::class,
        parentColumns = ["dni"],
        childColumns = ["dni_usuario"],
        onUpdate = ForeignKey.CASCADE
)
    ]


)


data class Toma(
    @PrimaryKey
    val nombre_medicacion: String,
    val horario: String,
    val imagen: String,
    val dni_usuario: String



)