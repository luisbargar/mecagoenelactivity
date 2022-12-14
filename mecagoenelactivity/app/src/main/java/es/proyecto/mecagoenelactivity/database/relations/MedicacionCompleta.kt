package es.proyecto.mecagoenelactivity.database.relations

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import es.proyecto.mecagoenelactivity.database.entities.Medicacion

data class MedicacionCompleta(
    @Embedded val medicacion: Medicacion,


    val tomas: Boolean,
    val stock: Boolean,


    ):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Medicacion::class.java.classLoader)!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()

    )

    override fun toString(): String {
        return super.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(medicacion, flags)
        parcel.writeByte(if (tomas) 1 else 0)
        parcel.writeByte(if (stock) 1 else 0)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MedicacionCompleta> {
        override fun createFromParcel(parcel: Parcel): MedicacionCompleta {
            return MedicacionCompleta(parcel)
        }

        override fun newArray(size: Int): Array<MedicacionCompleta?> {
            return arrayOfNulls(size)
        }
    }

}