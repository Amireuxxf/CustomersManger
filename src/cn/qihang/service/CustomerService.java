package cn.qihang.service;

import cn.qihang.bean.Customer;
import cn.qihang.bean.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: qihang
 * @Date: 2022/9/23 14:18
 * @Desc: customer表service层
 */
public interface CustomerService {

    /**
     * 全部查询
     * @return
     */
    List query();

    /**
     * 批量删除
     * @param ids
     */
    void selectDelete(String[] ids);

    /**
     * 单个删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 新增信息
     * @param customer
     * @return
     */
    boolean add(Customer customer);



    /**
     * 修改查询
     * @return
     */
    Customer updateQuery(String id);


    /**
     * 修改
     * @param customer
     * @return
     */
    boolean update(Customer customer);

    /**
     * 分页查询
     * @param page
     * @param map
     * @return
     */
    Page queryPage(Page page, Map<String, String[]> map);
}
