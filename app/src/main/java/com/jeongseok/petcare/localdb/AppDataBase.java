package com.jeongseok.petcare.localdb;

import android.graphics.Bitmap;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Profile.class, HealthData.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class, ListConverter.class, BitmapConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract ProfileDao profileDao();
    public abstract HealthDataDao healthDataDao();
}
