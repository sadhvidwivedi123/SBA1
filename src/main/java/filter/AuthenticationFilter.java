package filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.html.HTMLHtmlElement;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(request instanceof HttpServletRequest)
		{
		HttpServletRequest httpReq=(HttpServletRequest)request;
		HttpServletResponse httpResp=(HttpServletResponse)response;
		boolean isAllowed=true;
		if(!(httpReq.getServletPath().equals("/index.jsp"))&&!(httpReq.getServletPath().equals("/validate"))&&!(httpReq.getServletPath().equals("/register.jsp"))&&!(httpReq.getServletPath().equals("/registernewuser")))
		{
			if(httpReq.getSession().getAttribute("userId")==null)
			{
				httpResp.sendRedirect("index.jsp");
				isAllowed=false;
			}
		}
		if (isAllowed) {
			chain.doFilter(request, response);
		}
		
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
