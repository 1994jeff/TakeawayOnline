package module.base.com.takeawayonline.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import module.base.com.takeawayonline.bean.BuyStatus;
import module.base.com.takeawayonline.bean.Comment;
import module.base.com.takeawayonline.bean.Menu;
import module.base.com.takeawayonline.bean.OrderDetails;
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

    public static String getNoByOrder(String preStr,int index){
        String time = new SimpleDateFormat("MMDDhhmmss").format(new Date())+index;
        return  preStr+time;
    }

    public static boolean createOrderDetails(Context context, BuyStatus buyStatus, List<Menu> data) {
        SQLiteDatabase database = DatabaseUtil.getInstance().getWriteDataBase();
        String buyNo = getNo("BN");
        List<OrderDetails> orderDetails = new ArrayList<>();
        try {
            OrderDetails orderDetail = new OrderDetails();
            for(int i=0;i<buyStatus.nums.length;i++){
                if(buyStatus.showFlag[i]){
                    Menu menu = data.get(i);
//                    orderDetail.setMenuNo(menu.getVegetTableNo());
//                    orderDetail.setMenuName(menu.getVegetTableName());
//                    orderDetail.setMenuNum(buyStatus.nums[i]+"");
//                    orderDetail.setMenuPic(menu.getVegetTablePic());
//                    orderDetail.setMenuPrice(menu.getVegetTablePirce()+"");
//                    orderDetail.setCreateTime(getDateStr(new Date()));
//                    orderDetail.setBuyNo(buyNo);
                    String sql = "insert into order_details values('"+getNoByOrder("OD",i)+"','"+buyNo+"','"+CacheUtil.getInstance().getCurrentUser().getUserNo()+"','"+CacheUtil.getInstance().getCurrentUser().getUserName()+"','"+menu.getVegetTableNo()
                            +"','"+menu.getVegetTableName()+"','"+menu.getVegetTablePic()+"','no','"+menu.getVegetTablePirce()+"','"+buyStatus.nums[i]+"','"+getDateStr(new Date())+"')";
                    database.execSQL(sql);
                }
            }
        } catch (SQLException e) {
            return false;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return true;
    }

    private static String getDateStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }


    public static List<OrderDetails> getOrderDetailsByGroupList(Context context,boolean whereUser){
        List<OrderDetails> list = new ArrayList<>();
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        String sql = "";
        if(whereUser){
            sql = "select * from order_details where userNo='"+CacheUtil.getInstance().getCurrentUser().getUserNo()+"' group by buyNo";
        }else {
            sql = "select * from order_details group by buyNo";
        }
        try {
            Cursor cursor = database.rawQuery(sql,null);
            OrderDetails orderDetails = null;
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String orderDetailsNo = cursor.getString(cursor.getColumnIndex("orderDetailsNo"));
                    String buyNo = cursor.getString(cursor.getColumnIndex("buyNo"));
                    String menuNo = cursor.getString(cursor.getColumnIndex("menuNo"));
                    String menuName = cursor.getString(cursor.getColumnIndex("menuName"));
                    String menuPic = cursor.getString(cursor.getColumnIndex("menuPic"));
                    String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                    String userNo = cursor.getString(cursor.getColumnIndex("userNo"));
                    String userName = cursor.getString(cursor.getColumnIndex("userName"));
                    String comment = cursor.getString(cursor.getColumnIndex("comment"));
                    int menuPrice = cursor.getInt(cursor.getColumnIndex("menuPrice"));
                    int menuNum = cursor.getInt(cursor.getColumnIndex("menuNum"));
                    orderDetails = new OrderDetails(orderDetailsNo,buyNo,menuNo,menuNum+"",menuPrice+"",menuName,menuPic);
                    orderDetails.setCreateTime(createTime);
                    orderDetails.setComment(comment);
                    orderDetails.setUserName(userName);
                    orderDetails.setUserNo(userNo);
                    list.add(orderDetails);
                }
                cursor.close();
            }
        } catch (Exception e) {
            return null;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return list;
    }

    public static List<List<OrderDetails>> getUserOrderDetails(Context context){
        List<OrderDetails> list = getOrderDetailsByGroupList(context,true);
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        List<List<OrderDetails>> listUser = new ArrayList<>();
        List<OrderDetails> listBuyNo;
        try {
            if(list!=null && list.size()>0){
                for (OrderDetails orderDetails:list)
                {
                    String sql = "select * from order_details where buyNo=?";
                    Cursor cursor = database.rawQuery(sql,new String[]{orderDetails.getBuyNo()});
                    if(cursor!=null){
                        listBuyNo = new ArrayList<>();
                        while (cursor.moveToNext()){
                            String orderDetailsNo = cursor.getString(cursor.getColumnIndex("orderDetailsNo"));
                            String buyNo = cursor.getString(cursor.getColumnIndex("buyNo"));
                            String menuNo = cursor.getString(cursor.getColumnIndex("menuNo"));
                            String menuName = cursor.getString(cursor.getColumnIndex("menuName"));
                            String menuPic = cursor.getString(cursor.getColumnIndex("menuPic"));
                            String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                            String userNo = cursor.getString(cursor.getColumnIndex("userNo"));
                            String userName = cursor.getString(cursor.getColumnIndex("userName"));
                            String comment = cursor.getString(cursor.getColumnIndex("comment"));
                            int menuPrice = cursor.getInt(cursor.getColumnIndex("menuPrice"));
                            int menuNum = cursor.getInt(cursor.getColumnIndex("menuNum"));
                            orderDetails = new OrderDetails(orderDetailsNo,buyNo,menuNo,menuNum+"",menuPrice+"",menuName,menuPic);
                            orderDetails.setCreateTime(createTime);
                            orderDetails.setUserName(userName);
                            orderDetails.setUserNo(userNo);
                            orderDetails.setComment(comment);
                            listBuyNo.add(orderDetails);
                        }
                        cursor.close();
                        if(listBuyNo.size()>0){
                            listUser.add(listBuyNo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return listUser;
    }

    public static List<List<OrderDetails>> getUserCommentOrderDetails(Context context){
        List<OrderDetails> list = getOrderDetailsByGroupList(context,true);
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        List<List<OrderDetails>> listUser = new ArrayList<>();
        List<OrderDetails> listBuyNo;
        try {
            if(list!=null && list.size()>0){
                for (OrderDetails orderDetails:list)
                {
                    String sql = "select * from order_details where buyNo=?";
                    Cursor cursor = database.rawQuery(sql,new String[]{orderDetails.getBuyNo()});
                    if(cursor!=null){
                        listBuyNo = new ArrayList<>();
                        while (cursor.moveToNext()){
                            String comment = cursor.getString(cursor.getColumnIndex("comment"));
                            if("yes".equals(comment)) {
                                String orderDetailsNo = cursor.getString(cursor.getColumnIndex("orderDetailsNo"));
                                String buyNo = cursor.getString(cursor.getColumnIndex("buyNo"));
                                String menuNo = cursor.getString(cursor.getColumnIndex("menuNo"));
                                String menuName = cursor.getString(cursor.getColumnIndex("menuName"));
                                String menuPic = cursor.getString(cursor.getColumnIndex("menuPic"));
                                String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                                String userNo = cursor.getString(cursor.getColumnIndex("userNo"));
                                String userName = cursor.getString(cursor.getColumnIndex("userName"));
                                int menuPrice = cursor.getInt(cursor.getColumnIndex("menuPrice"));
                                int menuNum = cursor.getInt(cursor.getColumnIndex("menuNum"));
                                orderDetails = new OrderDetails(orderDetailsNo, buyNo, menuNo, menuNum + "", menuPrice + "", menuName, menuPic);
                                orderDetails.setCreateTime(createTime);
                                orderDetails.setUserName(userName);
                                orderDetails.setUserNo(userNo);
                                orderDetails.setComment(comment);
                                listBuyNo.add(orderDetails);
                            }else {
                                break;
                            }
                        }
                        cursor.close();
                        if(listBuyNo.size()>0){
                            listUser.add(listBuyNo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return listUser;
    }

    public static List<List<OrderDetails>> getUserUnCommentOrderDetails(Context context){
        List<OrderDetails> list = getOrderDetailsByGroupList(context,true);
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        List<List<OrderDetails>> listUser = new ArrayList<>();
        List<OrderDetails> listBuyNo;
        try {
            if(list!=null && list.size()>0){
                for (OrderDetails orderDetails:list)
                {
                    String sql = "select * from order_details where buyNo=?";
                    Cursor cursor = database.rawQuery(sql,new String[]{orderDetails.getBuyNo()});
                    if(cursor!=null){
                        listBuyNo = new ArrayList<>();
                        while (cursor.moveToNext()){
                            String comment = cursor.getString(cursor.getColumnIndex("comment"));
                            if("no".equals(comment)) {
                                String orderDetailsNo = cursor.getString(cursor.getColumnIndex("orderDetailsNo"));
                                String buyNo = cursor.getString(cursor.getColumnIndex("buyNo"));
                                String menuNo = cursor.getString(cursor.getColumnIndex("menuNo"));
                                String menuName = cursor.getString(cursor.getColumnIndex("menuName"));
                                String menuPic = cursor.getString(cursor.getColumnIndex("menuPic"));
                                String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                                String userNo = cursor.getString(cursor.getColumnIndex("userNo"));
                                String userName = cursor.getString(cursor.getColumnIndex("userName"));
                                int menuPrice = cursor.getInt(cursor.getColumnIndex("menuPrice"));
                                int menuNum = cursor.getInt(cursor.getColumnIndex("menuNum"));
                                orderDetails = new OrderDetails(orderDetailsNo, buyNo, menuNo, menuNum + "", menuPrice + "", menuName, menuPic);
                                orderDetails.setCreateTime(createTime);
                                orderDetails.setUserName(userName);
                                orderDetails.setUserNo(userNo);
                                orderDetails.setComment(comment);
                                listBuyNo.add(orderDetails);
                            }else {
                                break;
                            }
                        }
                        cursor.close();
                        if(listBuyNo.size()>0){
                            listUser.add(listBuyNo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return listUser;
    }

    /**
     * 添加评论
     *
     * @param des
     * @param buyNo
     * @param comment
     * @return
     */
    public static boolean comment(String buyNo, String comment,String des) {
        SQLiteDatabase database = DatabaseUtil.getInstance().getWriteDataBase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put("comment","yes");
            String sql = "update order_details set comment='yes' where buyNo='"+buyNo+"'";
            database.execSQL(sql);
            String sql2 = "insert into comment values('"+getNo("CM")+"','"+CacheUtil.getInstance().getCurrentUser().getUserNo()+"','"+buyNo+"','"+comment+"','"+getDateStr(new Date())+"','"+des+"','"+CacheUtil.getInstance().getCurrentUser().getUserName()+"')";
            database.execSQL(sql2);
        } catch (SQLException e) {
            return false;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return true;
    }

    public static List<Comment> getCommentByUser() {
        SQLiteDatabase database = DatabaseUtil.getInstance().getReadDataBase();
        List<Comment> commentList = new ArrayList<>();
        String sql = "select * from comment where userNo=?";
        try {
            Cursor cursor = database.rawQuery(sql,new String[]{CacheUtil.getInstance().getCurrentUser().getUserNo()});
            if(cursor!=null){
                Comment comment = null;
                while (cursor.moveToNext()){
                    String commentNo = cursor.getString(cursor.getColumnIndex("commentNo"));
                    String userNo = cursor.getString(cursor.getColumnIndex("userNo"));
                    String menuNo = cursor.getString(cursor.getColumnIndex("menuNo"));
                    String commentContent = cursor.getString(cursor.getColumnIndex("commentContent"));
                    String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                    String menuDes = cursor.getString(cursor.getColumnIndex("menuDes"));
                    String userName = cursor.getString(cursor.getColumnIndex("userName"));
                    comment = new Comment(commentNo,userNo,menuNo,commentContent,createTime);
                    comment.setMenuDes(menuDes);
                    comment.setUserName(userName);
                    commentList.add(comment);
                }
                cursor.close();
            }
        } catch (Exception e) {
            return null;
        } finally {
            if(database!=null){
                database.close();
            }
        }
        return commentList;
    }
}
