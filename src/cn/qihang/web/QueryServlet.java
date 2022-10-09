package cn.qihang.web;

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
import java.util.List;

/**
 * @Author: qihang
 * @Date: 2022/9/16 11:33
 * @Desc: 全部查询--未分页
 */
@WebServlet(urlPatterns = "/QueryServlet")
public class QueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerService service = new CustomerServiceImpl();
        List list = service.query();


        request.setAttribute("list",list);
        request.getRequestDispatcher("query.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
