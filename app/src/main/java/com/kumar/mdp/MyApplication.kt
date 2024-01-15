package com.kumar.mdp

import android.app.Application
import com.kumar.mdp.data.PlantDatabase
import com.kumar.mdp.data.plants
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
                    plants.forEach { insert(it) }
                }
            }
        }
    }
}