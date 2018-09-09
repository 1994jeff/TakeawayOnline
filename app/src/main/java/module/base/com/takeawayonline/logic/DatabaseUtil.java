package module.base.com.takeawayonline.logic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import module.base.com.takeawayonline.data.MyDataBase;

/**
 * SQLite数据库辅助类
 */

public class DatabaseUtil {

    MyDataBase dataBase;

    private static DatabaseUtil databaseUtil = new DatabaseUtil();

    public static DatabaseUtil getInstance() {
        if(databaseUtil==null){
            databaseUtil = new DatabaseUtil();
        }
        return databaseUtil;
    }

    public MyDataBase createDB(Context mContext){
        dataBase = new MyDataBase(mContext,"takeaway.db",null,1);
        return dataBase;
    }

    public SQLiteDatabase getReadDataBase(){
        return dataBase.getReadableDatabase();
    }

    public SQLiteDatabase getWriteDataBase(){
        return dataBase.getWritableDatabase();
    }
}
