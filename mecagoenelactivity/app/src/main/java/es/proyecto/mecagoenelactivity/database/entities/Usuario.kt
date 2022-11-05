package es.proyecto.mecagoenelactivity.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import es.proyecto.mecagoenelactivity.database.entities.Medicacion


@Entity(
    tableName = "usuarios",
    indices = [
        Index(value = ["nombre"], unique = true)
    ]
)


data class Usuario(val nombre: String, val password: String? = "") : BaseEntity()
