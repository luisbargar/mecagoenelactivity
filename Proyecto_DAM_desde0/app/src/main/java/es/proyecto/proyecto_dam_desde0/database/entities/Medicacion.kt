package es.proyecto.proyecto_dam_desde0.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "medicaciones",
    indices = [
        Index(value = ["nombre"], unique = true),
        Index(value = ["uso"]),
        Index(value = ["dni_usuario"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["dni"],
            childColumns = ["dni_usuario"],
            onUpdate = CASCADE
        )

    ]
)

data class  Medicacion(
    @PrimaryKey
    var nombre: String,
    val uso: String,
    val imagen: String,
    @ColumnInfo(name = "dni_usuario")
    val dniUsuario: String

) :  Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,

        ) {
      // nombre = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(uso)
        parcel.writeString(imagen)
        parcel.writeString(dniUsuario)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicacion> {
        override fun createFromParcel(parcel: Parcel): Medicacion{
            return Medicacion(parcel)
        }

        override fun newArray(size: Int): Array<Medicacion?> {
            return arrayOfNulls(size)
        }


    }
}


