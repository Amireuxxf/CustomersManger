package cn.qihang.dao.impl;

import cn.qihang.bean.Customer;
import cn.qihang.dao.CustomerDao;
import cn.qihang.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:24
 * @Desc:
 */
public class CustomerDaoImpl implements CustomerDao {
    private org.springframework.jdbc.core.JdbcTemplate JdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 全部查询
     * @return
     */
    @Override
    public List query() {
        try {
            String sql = "select * from customer where deleteid = 0 order by id desc";
            List<Customer> emps = this.JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
            return emps;
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 批量删除
     * @param id
     */
    @Override
    public void deleteCustomerById(String id) {
        String sql = "update customer set deleteid = 1 where id = ?";
        this.JdbcTemplate.update(sql,id);
    }

    /**
     * 单个删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        try {
            String sql = "update customer set deleteid = 1 where id = ?";
            int i = this.JdbcTemplate.update(sql, id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    /**
     * 新增邮箱校验
     * @param email
     * @return
     */
    @Override
    public Customer queryByEmail(String email) {
        try {
            String sql ="select * from customer where email = ?";
            return this.JdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Customer.class),email);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 修改邮箱校验
     * @param email
     * @return
     */
    @Override
    public Customer queryByEmail(String email , int id) {
        try {
            String sql ="select * from customer where email = ? and id <> ?";
            return this.JdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Customer.class),email,id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 增加信息
     * @param customer
     */
    @Override
    public void add(Customer customer) {
        String sql ="insert into customer(name,gender,age,address,qq,email) value(?,?,?,?,?,?)";
        this.JdbcTemplate.update(sql,customer.getName(),customer.getGender(),customer.getAge(),customer.getAddress(),customer.getQq(),customer.getEmail());
    }




    /**
     * 修改查询
     * @param id
     * @return
     */
    @Override
    public Customer updateQuery(String id) {
        try {
            String sql = "select * from customer where id = ? and deleteId = 0";
            return this.JdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Customer.class),id);
        } catch (DataAccessException e) {
            return null;
        }
    }


    /**
     * 修改
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        try {
            String sql = "update customer set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
            this.JdbcTemplate.update(sql, customer.getName(),customer.getGender(),customer.getAge(), customer.getAddress(),customer.getQq(),customer.getEmail(),customer.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException("修改失败！");
        }
    }


    /**
     * 分页查询总条数
     * @return
     * @param map
     */
    @Override
    public int getCountCustomer(Map<String, String[]> map) {
        /*try {
            String sql = "select count(1) from customer where deleteId = 0";
            return this.JdbcTemplate.queryForObject(sql,Integer.class);
        } catch (DataAccessException e) {
            throw  new RuntimeException("分页查询总条数失败！");
        }*/

        String sql = "select count(1) from customer where 1 = 1 and deleteId = 0 ";

        //可变字符串
        StringBuilder sb = new StringBuilder(sql);

        //可变参数
        List<Object> list = new ArrayList<>();

        Set<String> set = map.keySet();
        for (String key : set) {
            if ("pageNum".equals(key) || "username".equals(key) || "password".equals(key) || "ckimg".equals(key)) {
                continue;
            }

            String value = map.get(key)[0];


            //判断是否输入条件
            if (value != null && !value.equals("")) {
                sb.append(" and " + key + " like ? ");
                list.add("%" + value + "%");
            }
        }
        return this.JdbcTemplate.queryForObject(sb.toString(),Integer.class,list.toArray());
    }



    /**
     * 分页查询要展示的数据
     * @param startIndex
     * @param pageSize
     * @param map
     * @return
     */
    @Override
    public List<Customer> getListCustomer(int startIndex, int pageSize, Map<String, String[]> map) {
        /*try {
            String sql = "select * from customer where deleteid = 0 order by id desc limit ? , ?";
            return this.JdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Customer.class),startIndex,pageSize);
        } catch (DataAccessException e) {
            return null;
        }*/

        String sql = "select * from customer where 1 = 1 and deleteId = 0 ";

        //可变字符串
        StringBuilder sb = new StringBuilder(sql);

        //可变参数
        List<Object> list = new ArrayList<>();

        Set<String> set = map.keySet();
        for (String key : set) {
            if ("pageNum".equals(key) || "username".equals(key) || "password".equals(key) || "ckimg".equals(key)) {
                continue;
            }

            String value = map.get(key)[0];

            //判断是否输入条件
            if (value != null && !value.equals("")) {
                sb.append(" and " + key + " like ? ");
                list.add("%" + value + "%");
            }
        }

        sb.append(" order by id desc limit ? , ? ");
        list.add(startIndex);
        list.add(pageSize);
        return this.JdbcTemplate.query(sb.toString(),new BeanPropertyRowMapper<>(Customer.class),list.toArray());
    }
}
