package module.base.com.takeawayonline.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库辅助类
 */

public class MyDataBase extends SQLiteOpenHelper {

    private final String CREATE_TABLE_USER = "create table if not exists user(" +
            "userNo varchar PRIMARY KEY," +
            "userName varchar unique," +
            "userPsd varchar," +
            "userEmail varchar);";

    private final String CREATE_TABLE_COMMENT = "create table if not exists comment(" +
            "commentNo varchar PRIMARY KEY," +
            "userNo varchar," +
            "menuNo varchar," +
            "commentContent varchar," +
            "createTime varchar,"+
            "menuDes varchar,"+
            "userName varchar);";

    private final String CREATE_TABLE_MENU = "create table if not exists menu(" +
            "vegetTableNo varchar PRIMARY KEY," +
            "vegetTableName varchar," +
            "vegetTableDes varchar," +
            "vegetTablePic varchar," +
            "vegetTablePirce int," +
            "createTime varchar);";

    private final String CREATE_TABLE_ORDER_DETAILS = "create table if not exists order_details(" +
            "orderDetailsNo varchar PRIMARY KEY," +
            "buyNo varchar," +
            "userNo varchar," +
            "userName varchar," +
            "menuNo varchar," +
            "menuName varchar," +
            "menuPic varchar," +
            "comment varchar," +
            "menuPrice int," +
            "menuNum int,"+
            "createTime varchar);";

    private final String INSERT_ADMIN = "insert into user(userNo,userName,userPsd,userEmail) values('UN0000000001','admin','admin','admin@admin.com')";

    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);//创建用户表
//        db.execSQL(CREATE_TABLE_MENU);//创建菜单表
        db.execSQL(CREATE_TABLE_COMMENT);//创建评论表
        db.execSQL(CREATE_TABLE_ORDER_DETAILS);//创建用户下单细节表
        db.execSQL(INSERT_ADMIN);//插入默认管理员用户数据
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
