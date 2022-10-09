package cn.qihang.filter;

import cn.qihang.bean.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: qihang
 * @Desc: 业务=做功能 ，逻辑=实现/判断。要实现逻辑的流程
 * 1。拦截所有请求路径
 * 2。把通用 login.jsp list.jsp .. 放行
 * 3。才能判断用户是否登录
 * 4。判断登录的是什么用户
 * 5。如果管理员，放行
 * 6。如果普通用户，如果请求的是什么，我给放了
 * 7。否则不放，告诉用户权限不足
 */
@WebFilter(urlPatterns = "/*")//过滤所有请求
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //首先要把通用的请求放行 /login.jsp /list.jsp
        String path = request.getRequestURI();
        if("/login.jsp".equals(path)
                || "/LoginServlet".equals(path)
                || "/query.jsp".equals(path)
                || "/LoginOutServlet".equals(path)
                || "/Checkcode".equals(path)
                || "/regist.jsp".equals(path)
                || "/RegistUserServlet".equals(path)){
            filterChain.doFilter(request,response);
            return;
        }
        //判断用户是否登录
        User user  = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }else {//否则 登录成功
            //获取登录后的是 管理员 还是 普通用户
            String username = user.getUsername();
            if("jack".equals(username) || "amireuxxf".equals(username)){//管理员
                //管理员 全部放行
                filterChain.doFilter(request,response);
                return;
            }
            if("rose".equals(username) || "tom".equals(username)){//普通用户
                if("/QueryPageServlet".equals(path)
                        || "/add.jsp".equals(path)
                        || "/AddServlet".equals(path)){
                    filterChain.doFilter(request,response);
                    return;
                }else{
                    request.getRequestDispatcher("/fault.jsp").forward(request,response);
                    return;
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
