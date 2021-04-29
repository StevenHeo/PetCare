package com.jeongseok.petcare;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Profile.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PetCareDao petCareDao();
}
