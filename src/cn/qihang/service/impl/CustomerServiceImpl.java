package cn.qihang.service.impl;

import cn.qihang.bean.Customer;
import cn.qihang.bean.Page;
import cn.qihang.dao.CustomerDao;
import cn.qihang.dao.impl.CustomerDaoImpl;
import cn.qihang.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * @Author: qihangc
 * @Date: 2022/9/23 14:18
 * @Desc: customer表service层的实现层
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = new CustomerDaoImpl();
    /**
     * 全部查询
     * @return
     */
    @Override
    public List query() {
        List query = customerDao.query();
        if (query != null){
            return query;
        } else {
            return null;
        }
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void selectDelete(String[] ids) {
        for (String id : ids) {
            this.customerDao.deleteCustomerById(id);
        }
    }

    /**
     * 单个删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        boolean delete = customerDao.delete(id);
        if (delete){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 新增信息
     * @param customer
     * @return
     */
    @Override
    public boolean add(Customer customer) {
        //邮箱不重复校验
        Customer email = this.customerDao.queryByEmail(customer.getEmail());
        if(email == null){
            //可以添加
            this.customerDao.add(customer);
            return true;
        }

        return false;
    }


    /**
     * 修改查询
     * @return
     */
    @Override
    public Customer updateQuery(String id) {
        return this.customerDao.updateQuery(id);
    }

    /**
     * 修改
     * @param customer
     * @return
     */
    @Override
    public boolean update(Customer customer) {
        Customer cus = this.customerDao.queryByEmail(customer.getEmail(),customer.getId());
        if (cus == null){
            this.customerDao.update(customer);
            return true;
        }
        return false;
    }


    /**
     * 分页查询
     * @param page
     * @param map
     * @return
     */
    @Override
    public Page queryPage(Page page, Map<String, String[]> map) {

        //查询数据总条数
        page.setTotalCounts(this.customerDao.getCountCustomer(map));

        //查询所有要展示的数据
        page.setList(this.customerDao.getListCustomer(page.getStartIndex(),page.getPageSize(),map));

        return page;
    }
}
