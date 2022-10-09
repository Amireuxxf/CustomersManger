package cn.qihang.web;

import cn.qihang.bean.Customer;
import cn.qihang.service.CustomerService;
import cn.qihang.service.UserService;
import cn.qihang.service.impl.CustomerServiceImpl;
import cn.qihang.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qihang
 * @Date: 2022/9/23 15:19
 * @Desc: 单个删除
 */
@WebServlet(urlPatterns = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        CustomerService service = new CustomerServiceImpl();
        boolean delete = service.delete(id);
        if (delete){
            // request.getRequestDispatcher("QueryPageServlet").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/QueryPageServlet");
        }else {
            response.getWriter().println("删除失败！！！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
