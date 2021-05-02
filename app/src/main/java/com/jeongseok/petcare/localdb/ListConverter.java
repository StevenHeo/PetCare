package com.jeongseok.petcare.localdb;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ListConverter {
    @TypeConverter
    public static List<Integer> restoreList(String listOfInt) {
        return new Gson().fromJson(listOfInt, new TypeToken<List<Integer>>() {}.getType());
    }

    @TypeConverter
    public static String saveList(List<Integer> listOfInt) {
        return new Gson().toJson(listOfInt);
    }
}