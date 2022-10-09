package cn.qihang.web;

import cn.qihang.service.CustomerService;
import cn.qihang.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qihang
 * @Date: 2022/9/23 15:02
 * @Desc: 批量删除/选中删除
 */
@WebServlet(urlPatterns = "/SelectDeleteServlet")
public class SelectDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("checkbox");

        CustomerService service = new CustomerServiceImpl();

        service.selectDelete(ids);

        response.sendRedirect(request.getContextPath()+"/QueryPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
