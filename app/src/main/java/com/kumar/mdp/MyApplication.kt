package com.kumar.mdp

import android.app.Application
import com.kumar.mdp.data.PlantDatabase
import com.kumar.mdp.model.Plant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        populateDatabaseWithDummyEntries()
    }

    fun getPlantDatabaseInstance(): PlantDatabase {
        return PlantDatabase.getDatabase(this)
    }

    private fun populateDatabaseWithDummyEntries() {
        CoroutineScope(Dispatchers.IO).launch {
            getPlantDatabaseInstance().plantDao().apply {
                if (getAllPlants().first().isEmpty()) {
                    insert(
                        Plant(
                            1, "Evergreen oak", "Fagaceae", 2, "09-13-2022",
                            "https://d2seqvvyy3b8p2.cloudfront.net/40ab8e7cdddbe3e78a581b84efa4e893.jpg"
                        )
                    )
                    insert(
                        Plant(
                            2, "Common nettle", "Urticaceae", 3, "07-14-2022",
                            "https://bs.plantnet.org/image/o/9db58cbb3538a6b77384f972971d51869228e545"
                        )
                    )
                    insert(
                        Plant(
                            3, "Barnyard grass", "Poaceae", 1, "01-15-2023",
                            "https://bs.plantnet.org/image/o/f84a7d4fc2e627ccd451f568479b1932c2b2d900"
                        )
                    )
                    insert(
                        Plant(
                            4, "Narrow-leaf plantain", "Plantaginaceae", 2, "03-27-2021",
                            "https://bs.plantnet.org/image/o/f8d7d6fe52e36d04f5ad1fc03f46f604d5c3cc43"
                        )
                    )
                    insert(
                        Plant(
                            5, "Milfoil", "Asteraceae", 2, "01-02-2024",
                            "https://bs.plantnet.org/image/o/d788a757cd8bac8c3b1378a970c078a7a937a174"
                        )
                    )
                }
            }
        }
    }
}