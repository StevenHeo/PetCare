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

    public List getTableData()
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
