package cn.qihang.dao;

import cn.qihang.bean.User;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:11
 * @Desc:
 */
public interface UserDao {

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 注册重名查询
     * @param username
     * @return
     */
    User queryUserByName(String username);

    /**
     * 注册
     * @param user
     */
    void registered(User user);
}
