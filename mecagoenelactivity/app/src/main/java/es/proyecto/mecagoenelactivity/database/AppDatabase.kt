package es.proyecto.proyecto_dam_desde0.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import es.proyecto.mecagoenelactivity.app.App
import es.proyecto.mecagoenelactivity.database.converters.DateConverter
import es.proyecto.mecagoenelactivity.database.daos.MedicacionDao
import es.proyecto.mecagoenelactivity.database.daos.StockDao
import es.proyecto.mecagoenelactivity.database.daos.TomaDao
import es.proyecto.mecagoenelactivity.database.daos.UsuarioDao
import es.proyecto.mecagoenelactivity.database.entities.Medicacion
import es.proyecto.mecagoenelactivity.database.entities.Stock
import es.proyecto.mecagoenelactivity.database.entities.Toma
import es.proyecto.mecagoenelactivity.database.entities.Usuario
import kotlinx.coroutines.*

@Database(
    entities = [Usuario::class, Medicacion::class,  Toma::class, Stock::class],
    version = 1
)

@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun medicacionDao(): MedicacionDao
    abstract fun stockDao(): StockDao
    abstract fun tomaDao(): TomaDao



    companion object { //static
        private lateinit var db: AppDatabase

        fun initDB(context: Context): AppDatabase {
            if (!this::db.isInitialized) { //Singleton
                db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
                    .addCallback(callback)
                    .build()
            }
            return db
        }

        private val callback: Callback = object : Callback() { //creamos un objeto de la clase
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    //INSERCION DE DATOS
                    withContext(Dispatchers.IO) {
                        App.getDatabase().usuarioDao().save(Usuario("luis", "123456"))
                         App.getDatabase().medicacionDao().save(
                            Medicacion(
                                "Ibuprofeno",
                                "Para el dolor de cabeza",
                                "https://medlineplus.gov/spanish/druginfo/meds/a682159-es.html",
                                "https://www.dosfarma.com/62501-large_default/ibuprofeno-teva-400-mg-20-capsulas-blandas.jpg",
                                10.5

                            )
                        )

                    }
                }

            }
        }
    }


}