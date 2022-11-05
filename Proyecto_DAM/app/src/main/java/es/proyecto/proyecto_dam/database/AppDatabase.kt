package es.proyecto.proyecto_dam.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import es.proyecto.proyecto_dam.app.App
import es.proyecto.proyecto_dam.database.converters.DateConverter
import es.proyecto.proyecto_dam.database.daos.*
import es.proyecto.proyecto_dam.database.entities.*
import kotlinx.coroutines.*

@Database(
    entities = [Usuario::class, Dispositivo::class, Tipo::class, Marca::class, Fabricante::class, Favorito::class, Personal::class, Sistema::class, Compatibilidad::class],
    version = 1
)

@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun tipoDao(): TipoDao
    abstract fun fabricanteDao(): FabricanteDao
    abstract fun marcaDao(): MarcaDao
    abstract fun dispositivoDao(): DispositivoDao
    abstract fun personalDao(): PersonalDao
    abstract fun favoritoDao(): FavoritoDao
    abstract fun compatibleDao(): CompatibleDao
    abstract fun compatibilidadDao(): CompatibilidadDao

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
                        App.getDatabase().usuarioDao().save(Usuario("alvaro", "123456"))
                        App.getDatabase().compatibleDao().insertAll(
                            Sistema("Amazon"),
                            Sistema("Google"),
                            Sistema("Apple"),
                        )
                        App.getDatabase().tipoDao().insertAll(
                            Tipo("Bombillas"), Tipo("Enchufe"),
                            Tipo(("Interruptor"))
                        )
                        App.getDatabase().fabricanteDao().insertAll(
                            Fabricante("TP-Link"), Fabricante("Amazon")
                        )
                        App.getDatabase().marcaDao().insertAll(
                            Marca("Tapo", 1L), Marca("Basics", 2L)
                        )

                        App.getDatabase().dispositivoDao().save(
                            Dispositivo(
                                "TP-Link Tapo P100",
                                "programe el enchufe inteligente para que suministre energía automáticamente de acuerdo con sus necesidades, cómo configurar las luces al anochecer y apagarlas al amanecer",
                                "https://www.amazon.es/TP-Link-Tapo-P100-Inteligente-Inalámbrico/dp/B08GDD17BS/ref=sr_1_5?__mk_es_ES=ÅMÅŽÕÑ&crid=3MWQ9LMCVQ6SF&keywords=enchufe+wifi&qid=1661089108&sprefix=enchufe+wifi%2Caps%2C90&sr=8-5",
                                "https://m.media-amazon.com/images/I/61NfVfnla8L._AC_SL1000_.jpg",
                                21.99,
                                1,
                                1
                            )
                        )

                    }
                }

            }
        }
    }


