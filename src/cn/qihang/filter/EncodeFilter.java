package cn.qihang.filter;

/**
 * 编码过滤器
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodeFilter implements Filter{
	
	private String encoding;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		MyRequest myrequest = new MyRequest(request);// 构造方法注入 对象
		response.setContentType("text/html;charset="+encoding);// perperties xml
		chain.doFilter(myrequest, response);// request response 传递下去 ... 将request response 对象传递后续 servlet /jsp

	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	}

}

class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;// 获取 为加强 request 对象
	private boolean flag = false;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		Map<String, String[]> map = getParameterMap();
		if (map != null && map.size() != 0) {
			String[] values = map.get(name);
			if (values != null && values.length != 0) {
				return values[0];
			}
		}
		return super.getParameter(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = getParameterMap();
		if (map != null && map.size() != 0) {
			String[] values = map.get(name);
			if (values != null && values.length != 0) {
				return values;
			}
		}
		return super.getParameterValues(name);
	}

	@Override
	public Map getParameterMap() {
		// get/post
		String method = request.getMethod();
		if ("post".equalsIgnoreCase(method)) {
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if ("get".equalsIgnoreCase(method)) {
			if (!flag) {
				Map<String, String[]> map = request.getParameterMap();// 原始request 获取数据 iso-88590-1解码
				if (map != null && map.size() != 0) {
					for (String key : map.keySet()) {
						String[] values = map.get(key);
						if (values != null && values.length != 0) {
							for (int i = 0; i < values.length; i++) {
								try {
//tomcat8以上 GET已经改为了utf-8		
									//values[i] = new String(values[i].getBytes("iso-8859-1"), "utf-8");
values[i] = new String(values[i].getBytes("utf-8"), "utf-8");
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				flag = true;
				return map;
			}
		}
		return super.getParameterMap();// 其他的请求
	}

}