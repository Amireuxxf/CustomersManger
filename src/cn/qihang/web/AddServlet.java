package cn.qihang.web;

import cn.qihang.bean.Customer;
import cn.qihang.service.CustomerService;
import cn.qihang.service.impl.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: qihang
 * @Date: 2022/9/23 15:33
 * @Desc: 添加信息
 */
@WebServlet(urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();

        Customer customer = new Customer();

        try {
            BeanUtils.populate(customer,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        CustomerService customerService = new CustomerServiceImpl();

        boolean bol =  customerService.add(customer);

        if(bol){
            //添加成功
            response.sendRedirect(request.getContextPath()+"/QueryPageServlet");
        }else{
            //邮件已存在
            request.setAttribute("msg","邮箱已存在！");
            request.setAttribute("old",customer);
            request.getRequestDispatcher("add.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
