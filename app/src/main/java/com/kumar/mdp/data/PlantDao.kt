package com.kumar.mdp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kumar.mdp.model.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants")
    fun getAllPlants(): Flow<List<Plant>>

    @Insert
    suspend fun insert(plant: Plant)

    @Update
    suspend fun update(plant: Plant)

    @Query("DELETE FROM plants")
    suspend fun deleteAll()

    @Query("DELETE FROM plants WHERE id = :plantId")
    suspend fun delete(plantId: Int)

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlantById(plantId: Int): Flow<Plant>

}