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
 * @Date: 2022/9/23 14:33
 * @Desc: 注册
 */
@WebServlet(urlPatterns = "/RegistUserServlet")
public class RegistUserServlet extends HttpServlet {
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
        if (!ckimg.equalsIgnoreCase(code)){
            //给出提示
            response.getWriter().println("输入的验证码有误!");
            return;
        } else {

           /* String username = request.getParameter("username");
            String password = request.getParameter("password");
            int age = Integer.parseInt(request.getParameter("age"));

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);*/
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

            boolean registered = service.registered(user);
            if (registered){
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }else {
                response.getWriter().println("用户名已存在！");
                request.getRequestDispatcher("regist.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
