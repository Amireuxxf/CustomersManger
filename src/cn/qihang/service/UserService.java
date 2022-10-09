package cn.qihang.service;

import cn.qihang.bean.User;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:08
 * @Desc: User表service层
 */
public interface UserService {
    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    boolean registered(User user);
}
