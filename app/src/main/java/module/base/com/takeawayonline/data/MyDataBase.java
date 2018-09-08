package module.base.com.takeawayonline.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jeff on 18-9-8.
 */

public class MyDataBase extends SQLiteOpenHelper {

    private final String CREATE_TABLE_USER = "create table if not exists user(" +
            "userNo varchar PRIMARY KEY," +
            "userName varchar unique," +
            "userPsd varchar," +
            "userEmail varchar);";

    private final String CREATE_TABLE_ORDER = "create table if not exists orders(" +
            "orderNo varchar PRIMARY KEY," +
            "userNo varchar," +
            "menuNo varchar," +
            "orderNum int," +
            "orderPrce int," +
            "createTime varchar);";

    private final String CREATE_TABLE_COMMENT = "create table if not exists comment(" +
            "commentNo varchar PRIMARY KEY," +
            "userNo varchar," +
            "menuNo varchar," +
            "commentContent varchar," +
            "createTime varchar);";

    private final String CREATE_TABLE_MENU = "create table if not exists menu(" +
            "vegetTableNo varchar PRIMARY KEY," +
            "vegetTableName varchar," +
            "vegetTablePic varchar," +
            "vegetTablePirce int," +
            "createTime varchar);";

    private final String INSERT_ADMIN = "insert into user(userNo,userName,userPsd,userEmail) values('UN0000000001','admin','admin','admin@admin.com')";

    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDataBase(Context context) {
        super(context, "takeaway", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_MENU);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_COMMENT);
        db.execSQL(INSERT_ADMIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
