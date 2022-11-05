package es.proyecto.proyecto_dam_desde0.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import es.proyecto.proyecto_dam_desde0.app.App
import es.proyecto.proyecto_dam_desde0.database.converters.DateConverter
import es.proyecto.proyecto_dam_desde0.database.daos.MedicacionDao
import es.proyecto.proyecto_dam_desde0.database.daos.StockDao
import es.proyecto.proyecto_dam_desde0.database.daos.TomaDao
import es.proyecto.proyecto_dam_desde0.database.daos.UsuarioDao
import es.proyecto.proyecto_dam_desde0.database.daos.*
import es.proyecto.proyecto_dam_desde0.database.entities.*
import es.proyecto.proyecto_dam_desde0.database.entities.Medicacion
import es.proyecto.proyecto_dam_desde0.database.entities.Stock
import es.proyecto.proyecto_dam_desde0.database.entities.Toma
import es.proyecto.proyecto_dam_desde0.database.entities.Usuario
import kotlinx.coroutines.*

@Database(
    entities = [Usuario::class, Medicacion::class, Stock::class, Toma::class],
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
                        App.getDatabase().usuarioDao().save(Usuario("123456789A", "Pedro Páramo Rodriguez",  "25-09-1949",  "Ibuprofeno"))

                        App.getDatabase().stockDao().insertAll(
                            Stock(

                                "Ibuprofeno",
                                10

                            ))

                        App.getDatabase().TomaDao().insertAll(
                            Toma(
                            "Ibuprofeno",
                            "Mañanas"
                            ))

                        App.getDatabase().medicacionDao().save(
                            Medicacion(
                                "Ibuprofeno",
                                "dolor de cabeza",
                                "https://www.dosfarma.com/62501-large_default/ibuprofeno-teva-400-mg-20-capsulas-blandas.jpg",
                            "123456789A"
                            )
                        )

                    }
                }

            }
        }
    }


}