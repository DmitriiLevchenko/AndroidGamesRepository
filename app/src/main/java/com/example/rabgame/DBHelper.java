package com.example.rabgame;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
public  class DBHelper extends SQLiteOpenHelper {
    static String DBName = "GameUserDB.db";
    Context context;
    SQLiteDatabase db;
    String path2DB;
    String path;

    public DBHelper(Context context) {
        super(context, DBName, null, 1);
        this.context = context;
        path2DB = "/data/data/"+context.getPackageName()+"/databases/";
        path = path2DB+DBName;
        openDataBase();
    }

    public SQLiteDatabase openDataBase() {
        if(db == null) {
            createDataBase();
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }
        return db;
    }

    public void createDataBase() {
        boolean existDB = checkDataBase();
        if(!existDB) {
            getReadableDatabase();
            try {
                copyDataBase();
                return;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream is = context.getAssets().open(DBName);
        FileOutputStream os = new FileOutputStream(path);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer))>0){
            os.write(buffer, 0, length);
        }

        //Close the streams
        os.flush();
        os.close();
        is.close();

    }

    public void close() {
        if(db != null) {
            db.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}