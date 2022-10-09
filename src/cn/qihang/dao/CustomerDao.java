package cn.qihang.dao;

import cn.qihang.bean.Customer;

import java.util.List;
import java.util.Map;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:24
 * @Desc:
 */
public interface CustomerDao {

    /**
     * 全部查询
     * @return
     */
    List query();

    /**
     * 批量删除
     * @param id
     */
    void deleteCustomerById(String id);

    /**
     * 单个删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 邮箱校验
     * @param email
     * @return
     */
    Customer queryByEmail(String email);

    /**
     * 邮箱校验，方法重载
     * @param email
     * @param id
     * @return
     */
    Customer queryByEmail(String email,int id);

    /**
     * 添加信息
     * @param customer
     */
    void add(Customer customer);



    /**
     * 修改查询
     * @param id
     * @return
     */
    Customer updateQuery(String id);

    /**
     * 修改
     * @param customer
     */
    void update(Customer customer);


    /**
     * 分页查询总条数
     * @return
     * @param map
     */
    int getCountCustomer(Map<String, String[]> map);

    /**
     * 分页查询要展示的数据
     * @param startIndex
     * @param pageSize
     * @param map
     * @return
     */
    List<Customer> getListCustomer(int startIndex, int pageSize, Map<String, String[]> map);
}
