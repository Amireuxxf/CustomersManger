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
 * @Date: 2022/9/23 15:51
 * @Desc: 修改信息
 */
@WebServlet(urlPatterns = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
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

        CustomerService service = new CustomerServiceImpl();
        boolean update = service.update(customer);
        if (update){
            response.sendRedirect(request.getContextPath()+"/QueryPageServlet");
        }else {
            request.setAttribute("msg", "修改的邮箱已存在！");
            request.setAttribute("customer",customer);
            request.getRequestDispatcher("update.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CustomerService service = new CustomerServiceImpl();
        Customer customer = service.updateQuery(id);

        request.setAttribute("customer",customer);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}
