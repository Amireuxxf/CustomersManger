package cn.qihang.service.impl;

import cn.qihang.bean.User;
import cn.qihang.dao.UserDao;
import cn.qihang.dao.impl.UserDaoImpl;
import cn.qihang.service.UserService;
import cn.qihang.utils.MD5Utils;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:09
 * @Desc: User表service层的实现层
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        user.setPassword(MD5Utils.getPWD(user.getPassword()));
        User login = userDao.login(user);
        if (login != null){
            return login;
        } else {
            return null;
        }
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public boolean registered(User user) {
        User user1 = userDao.queryUserByName(user.getUsername());
        if (user1 == null){
            //密码加密
            user.setPassword(MD5Utils.getPWD(user.getPassword()));

            this.userDao.registered(user);
            return true;
        }
        return false;
    }
}
