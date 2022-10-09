package cn.qihang.dao.impl;

import cn.qihang.bean.User;
import cn.qihang.dao.UserDao;
import cn.qihang.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:12
 * @Desc:
 */
public class UserDaoImpl implements UserDao {
    private org.springframework.jdbc.core.JdbcTemplate JdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            return this.JdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 注册重名查询
     * @param username
     * @return
     */
    @Override
    public User queryUserByName(String username) {
        try {
            String sql = "select * from user where username = ?";
            return this.JdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username);
        } catch (DataAccessException e) {
            return null;
        }
    }


    /**
     * 注册
     * @param user
     */
    @Override
    public void registered(User user) {
        String sql ="insert into user(username,password,nickname,createdtime,updatedtime) value(?,?,?,?,?)";
        this.JdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getNickname(),new Date(),new Date());
    }
}
