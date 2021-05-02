package com.jeongseok.petcare.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HealthDataDao {
    @Query("SELECT * FROM HealthData")
    List<HealthData> getAll();

    @Insert
    void insert(HealthData data);

    @Update
    void update(HealthData data);

    @Delete
    void delete(HealthData data);
}
