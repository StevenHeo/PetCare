package com.jeongseok.petcare.localdbPet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
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

    public void deleteMyTipTable(){//테스트 연산 다 지우기
        try {
            mDb.execSQL("DELETE FROM myTipDisease;");
        }catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void updateMyTipTable(){
        try {
            mDb.execSQL("UPDATE myTipDisease SET showCheck=1 WHERE showCheck=-1;");
        }catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List selectMyTipTable(){//-1값인 것만 가져오기
        try{
            List tipList = new ArrayList();
            dogDisease dogDisease = null;
            Cursor mCur = mDb.rawQuery("SELECT * FROM myTipDisease WHERE showCheck=-1;", null);
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
    public void insertMyTipTable(String diseaseName, int idx){//선택한거 tip테이블에 넣기

        try{
            mDb.execSQL("INSERT INTO myTipDisease(disease,currentState,result1,result2,tip,tipTime,showCheck) SELECT disease,currentState,result1,result2,tip,datetime('NOW','+9 hours'),-1 FROM dogDisease WHERE disease='" + diseaseName + "' and diseaseId='" + idx + "';");
        }catch(SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List getTableData()//table의 data를 데리고 오는
    {
        try
        {
            String sql ="SELECT * FROM " + TABLE_NAME;
            List userList = new ArrayList();

            dogDisease dogDisease = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                while( mCur.moveToNext() ) {
                    dogDisease = new dogDisease();

                    dogDisease.setIdx(mCur.getInt(0));
                    dogDisease.setDisease(mCur.getString(1));
                    dogDisease.setDiseaseId(mCur.getInt(2));
                    dogDisease.setCurrentState(mCur.getString(3));
                    dogDisease.setResult1(mCur.getString(4));
                    dogDisease.setResult2(mCur.getString(5));
                    dogDisease.setTip(mCur.getString(6));

                    userList.add(dogDisease);
                }
            }
            return userList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}
