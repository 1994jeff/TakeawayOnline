package module.base.com.takeawayonline.logic;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import module.base.com.takeawayonline.bean.User;
import module.base.com.takeawayonline.data.MyDataBase;

/**
 * Created by jeff on 18-9-8.
 */

public class SystemUtils {

    private static boolean isAdminLogin;

    public static boolean isIsAdminLogin() {
        return isAdminLogin;
    }

    public static boolean login(Context context, String userNameString, String userPsdString) {
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        String sql = "select * from user where userName=? and userPsd=?";
        try {
            Cursor cursor = database.rawQuery(sql,new String[]{userNameString,userPsdString});
            User user = null;
            if(cursor!=null)
            {
                while (cursor.moveToNext()){
                    String userNo = cursor.getString(cursor.getColumnIndex("userNo"));
                    String userName = cursor.getString(cursor.getColumnIndex("userName"));
                    String userPsd = cursor.getString(cursor.getColumnIndex("userPsd"));
                    String userEmail = cursor.getString(cursor.getColumnIndex("userEmail"));
                    user = new User(userNo,userName,userPsd,userEmail);
                }
                cursor.close();
            }
            if(user!=null){
                CacheUtil.getInstance().setCurrentUser(user);
                if(user.getUserName().equals("admin")){
                    isAdminLogin = true;
                }else {
                    isAdminLogin = false;
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }finally {
            if(database!=null){
                database.close();
            }
        }
        return false;
    }

    public static boolean register(Context context, String userNameString, String userPsdString, String userMailString) {
        SQLiteDatabase database = DatabaseUtil.getInstance().getWriteDataBase();
        try {
            database.execSQL("insert into user values('"+getNo("UN")+"','"+userNameString+"','"+userPsdString+"','"+userMailString+"')");
        } catch (SQLException e) {
            return false;
        }finally {
            if(database!=null){
                database.close();
            }
        }
        return true;
    }

    public static boolean forgetPsd(Context context, String psdString, String userMailString,String userNameString) {
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        String sql = "update user set userPsd='"+psdString+"' where userName='"+userNameString+"' and userEmail='"+userMailString+"'";
        try {
            database.execSQL(sql);
        } catch (Exception e) {
            return false;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return true;
    }

    public static String getNo(String preStr){
        String time = new SimpleDateFormat("MMDDhhmmss").format(new Date());
      return  preStr+time;
    }

}
