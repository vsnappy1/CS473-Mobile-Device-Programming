package com.kumar.mdp.data

import com.kumar.mdp.model.Plant
import kotlinx.coroutines.flow.Flow

class PlantRepository(private val plantDao: PlantDao) {

    val allPlants: Flow<List<Plant>> = plantDao.getAllPlants()

    suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun delete(plant: Plant) {
        plantDao.delete(plant.id)
    }

    fun getPlantById(plantId: Int): Flow<Plant> {
        return plantDao.getPlantById(plantId)
    }
}

