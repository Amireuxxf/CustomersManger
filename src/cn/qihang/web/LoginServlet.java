package cn.qihang.web;

import cn.qihang.bean.User;
import cn.qihang.service.UserService;
import cn.qihang.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: qihang
 * @Date: 2022/9/15 15:31
 * @Desc: 登录
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //获取系统生成的验证码
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");

        //获取用户输入的验证码
        String ckimg = request.getParameter("ckimg");

        //为了保证验证是有效的唯一，把上一个验证码清除
        session.removeAttribute("code");


        //对比
        if (!ckimg.equalsIgnoreCase(code)) {
            //给出提示
            request.setAttribute("msg","验证码输入错误！");
        } else {
            /*String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);*/

            //自动封装
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            UserService service = new UserServiceImpl();
            User user1 = service.login(user);

            if (user1 != null) {
                request.getSession().setAttribute("user",user1);
                request.getRequestDispatcher("QueryPageServlet").forward(request,response);
            } else {
                request.setAttribute("msg","登录失败");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
