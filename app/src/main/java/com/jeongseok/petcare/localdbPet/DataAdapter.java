package com.jeongseok.petcare.localdbPet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class DataAdapter
{

    protected static final String TAG = "DataAdapter";
    protected static final String TABLE_NAME = "dogDisease";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public void deleteMyTipTable(){
        try {
            mDb.execSQL("DELETE FROM myTipDisease;");
        }catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }


    public boolean checkMyImage(String date){
        boolean check=false;
        try {
            Cursor mCur = mDb.rawQuery("SELECT tipTime FROM myTipDisease WHERE tipTime='"+ date +"' ", null);
            if (mCur!=null) {
                while(mCur.moveToNext()) {
                    check = true;
                }
            }else {
                check = false;
            }
            return check;
        }catch(SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }

    }

    public List selectMyTipList(String selectday){
        try{
            Log.v("dbFile1", selectday.toString());
            List dateTipList = new ArrayList();
            myTipDisease myTipDisease = null;
            Cursor mCur = mDb.rawQuery("SELECT * FROM myTipDisease WHERE tipTime='"+selectday+"' AND imageCheck LIKE '1';", null);
            if (mCur != null) {
                while (mCur.moveToNext()) {
                    myTipDisease = new myTipDisease();
                    myTipDisease.setDisease(mCur.getString(0));
                    myTipDisease.setCurrentState(mCur.getString(1));
                    myTipDisease.setResult1(mCur.getString(2));
                    myTipDisease.setResult2(mCur.getString(3));
                    myTipDisease.setTip(mCur.getString(4));
                    myTipDisease.setMemo(mCur.getString(5));
                    myTipDisease.setImage(mCur.getString(6));
                    myTipDisease.setTip(mCur.getString(8));
                    dateTipList.add(myTipDisease);
                }
            }
            return dateTipList;
        }catch(SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }


    public void updateImage(String image,String selectday){
        try {
            mDb.execSQL("UPDATE myTipDisease SET image ='"+image+"' WHERE tipTime ='"+selectday+"' AND imageCheck LIKE '1';");
        }catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void updateMemo(String memo,String selectday){
        try {
            mDb.execSQL("UPDATE myTipDisease SET memo ='"+memo+"' WHERE tipTime ='"+selectday+"' AND imageCheck LIKE '1';");
        }catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }


    public List selectMyTipTable(String day){//-1값인 것만 가져오기
        try{
            List dateTipList = new ArrayList();
            myTipDisease myTipDisease = null;
            Cursor mCur = mDb.rawQuery("SELECT * FROM myTipDisease WHERE tipTime='"+day+"' AND imageCheck LIKE '1';", null);
            if (mCur != null) {
                while (mCur.moveToNext()) {
                    myTipDisease = new myTipDisease();
                    myTipDisease.setDisease(mCur.getString(0));
                    myTipDisease.setCurrentState(mCur.getString(1));
                    myTipDisease.setResult1(mCur.getString(2));
                    myTipDisease.setResult2(mCur.getString(3));
                    myTipDisease.setTip(mCur.getString(4));
                    myTipDisease.setMemo(mCur.getString(5));
                    myTipDisease.setImage(mCur.getString(6));
                    myTipDisease.setTip(mCur.getString(8));
                    dateTipList.add(myTipDisease);
                }
            }
            return dateTipList;
        }catch(SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List selectMyTipTable(){//-1값인 것만 가져오기
        try{
            List tipList = new ArrayList();
            dogDisease dogDisease = null;
            Cursor mCur = mDb.rawQuery("SELECT * FROM myTipDisease ;", null);
            if (mCur != null) {
                while (mCur.moveToNext()) {
                    dogDisease = new dogDisease();
                    dogDisease.setDisease(mCur.getString(1));
                    dogDisease.setResult1(mCur.getString(2));
                    dogDisease.setResult2(mCur.getString(3));
                    dogDisease.setTip(mCur.getString(4));
                    tipList.add(dogDisease);
                }
            }
            return tipList;
        }catch(SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void insertMyTipTable(String diseaseName, int idx,String selectday){
        Log.v("dbFile",selectday);
        try{
            mDb.execSQL("INSERT INTO myTipDisease(disease,currentState,result1,result2,tip,imageCheck,tipTime) SELECT disease,currentState,result1,result2,tip,1,'"+selectday+"' FROM dogDisease WHERE disease='" + diseaseName + "' and diseaseId='" + idx + "';");
        }catch(SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }


}
