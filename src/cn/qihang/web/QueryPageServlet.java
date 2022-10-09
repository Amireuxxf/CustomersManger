package cn.qihang.web;

import cn.qihang.bean.Page;
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
 * @Date: 2022/9/24 21:06
 * @Desc: 分页查询
 */
@WebServlet(urlPatterns = "/QueryPageServlet")
public class QueryPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();
        Page page = new Page();

        try {
            BeanUtils.populate(page,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //注入业务层
        CustomerService service = new CustomerServiceImpl();

        Page pg = service.queryPage(page,map);

        request.setAttribute("pg",pg);

        //搜索数据回显
        request.setAttribute("map",map);
        request.getRequestDispatcher("query.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
