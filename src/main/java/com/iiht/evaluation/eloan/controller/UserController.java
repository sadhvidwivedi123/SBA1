package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dao.UserDaoImpl;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.iiht.evaluation.eloan.service.ILoanService;
import com.iiht.evaluation.eloan.service.IUserService;
import com.iiht.evaluation.eloan.service.LoanServiceImpl;
import com.iiht.evaluation.eloan.service.UserServiceImpl;
import com.mysql.cj.xdevapi.Statement;

import exception.LoanException;




@WebServlet({"/user","/validate","/registernewuser","/placeloan","/trackloan","/editloan","/editLoanProcess"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
private IUserService userService;
private ILoanService loanService;

	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.userService = new UserServiceImpl();
		this.loanService = new LoanServiceImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		String viewName = "";
		try {
			switch (action) {
			case "/registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "/validate":
				viewName=validate(request,response);
				break;
			case "/placeloan":
				viewName=placeloan(request,response);
				break;
			case "/application1":
				viewName=application1(request,response);
				break;
			case "/editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "/registeruser":
				viewName=registerUser(request,response);
				break;
			case "/register":
				viewName = register(request, response);
				break;
			case "/application":
				viewName = application(request, response);
				break;
			case "/trackloan":
				viewName = trackloan(request, response);
				break;
			case "/editloan":
				viewName = editloan(request, response);
				break;	
			case  "/displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoanException {
		/* write the code to validate the user */
		User user=new User();
		HttpSession session=request.getSession();
		user.setUserid(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		String res=userService.validate(user);
		if(res.equals("admin"))
		{
			session.setAttribute("userId", user.getUserid());
			return "adminhome1.jsp";
		}
		else if(res.equals("user"))
		{
			session.setAttribute("userId", user.getUserid());
			return "userhome1.jsp";
		}
		else 
		{
			request.setAttribute("error", true);
			return "index.jsp";
		}
		
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws LoanException {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		HttpSession session=request.getSession();
		LoanInfo loan = new LoanInfo();
		String applicationNo=loanService.getLoanNo();
		//String applicationNo= Integer.toString((Integer.parseInt(loanService.getLoanNo())+1));
	    loan.setApplno(applicationNo);
		loan.setAmtrequest(Double.parseDouble(request.getParameter("loanAmountRequested")));
		loan.setPurpose(request.getParameter("loanName"));
		loan.setDoa(LocalDate.parse(request.getParameter("loanApplicationDate")));
		loan.setBstructure(request.getParameter("businessStructure"));
		loan.setBindicator(request.getParameter("billingIndicator"));
		loan.setTindicator(request.getParameter("taxIndicator"));
		loan.setAddress(request.getParameter("address"));
		loan.setMobile(request.getParameter("phone"));
		loan.setEmail(request.getParameter("email"));
		loan.setUserId(session.getAttribute("userId").toString());
		loan.setStatus("pending");
		
		loanService.applyLoan(loan);
		request.setAttribute("messageNewLoan",true);
		request.setAttribute("applicationNo", applicationNo);
		return "userhome1.jsp";
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		return null;
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoanException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		HttpSession session=request.getSession();
		LoanInfo loan = new LoanInfo();
	    loan.setApplno(request.getParameter("applictionNo"));
		loan.setAmtrequest(Double.parseDouble(request.getParameter("loanAmountRequested")));
		loan.setPurpose(request.getParameter("loanName"));
		loan.setBstructure(request.getParameter("businessStructure"));
		loan.setBindicator(request.getParameter("billingIndicator"));
		loan.setTindicator(request.getParameter("taxIndicator"));
		loan.setAddress(request.getParameter("address"));
		loan.setMobile(request.getParameter("phone"));
		loan.setEmail(request.getParameter("email"));
		loan.setUserId(session.getAttribute("userId").toString());
		
		loanService.updateLoan(loan);
		request.setAttribute("messageEditLoan",true);
		request.setAttribute("applicationNo", request.getParameter("applictionNo"));
		return "userhome1.jsp";
		
		
		
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		return "newuserui.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoanException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		User user=new User();
		user.setUserid(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("userName"));
		user.setPhoneNo(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		if(!userService.userIdExistsAlready(user.getUserid()))
		{
		userService.addUser(user);
		request.setAttribute("message", true);
		return "index.jsp";
		}
		else
		{
			request.setAttribute("message", true);
			return "register.jsp";	
		}
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		HttpSession session=request.getSession();
		LoanInfo loan = new LoanInfo();
		String appNo=request.getParameter("applicationNo");
		String userid=session.getAttribute("userId").toString();
		loan.setApplno(appNo);
		loan.setUserId(userid);
		loan=loanService.getLoanDetails(loan,session);
			if(loan!=null)
			{
				if(loanService.validateUser(userid,appNo))
				{
					if(!loanService.ifStatusApproved(loan))
					{
					request.setAttribute("message",true);
					request.setAttribute("loan", loan);
					return "editloanui.jsp";
					}
					else
					{
						request.setAttribute("nonEditable", true);
						return "editloan.jsp";
					}
				}
				else
				{
					request.setAttribute("invalidAppliNoForCurrentUser", true);
					return "editloan.jsp";
				}
			}
			else
			{
				request.setAttribute("messageIncorrectTrackId", true);
				return "editloan.jsp";
			}
		
		
		}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		HttpSession session=request.getSession();
		LoanInfo loan = new LoanInfo();
		String appNo=request.getParameter("applicationNo");
		String userid=session.getAttribute("userId").toString();
		loan.setApplno(appNo);
		loan.setUserId(userid);
		loan=loanService.getLoanDetails(loan,session);
			if(loan!=null)
			{
				if(loanService.validateUser(userid,appNo))
				{
				request.setAttribute("message",true);
				request.setAttribute("loan", loan);
				return "loanDetails.jsp";
				}
				else
				{
					request.setAttribute("invalidAppliNoForCurrentUser", true);
					return "trackloan.jsp";
				}
			}
			else
			{
				request.setAttribute("messageIncorrectTrackId", true);
				return "trackloan.jsp";
			}
		
		
		
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		return null;
	}
}