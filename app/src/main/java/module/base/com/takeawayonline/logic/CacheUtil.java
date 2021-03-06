package module.base.com.takeawayonline.logic;

import module.base.com.takeawayonline.bean.User;

/**
 * 缓存辅助类
 * 用户登录后数据缓存
 */

public class CacheUtil {

    static CacheUtil cacheUtil = new CacheUtil();

    private User currentUser;

    public static CacheUtil getInstance() {
        if (cacheUtil==null){
            cacheUtil = new CacheUtil();
        }
        return cacheUtil;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
