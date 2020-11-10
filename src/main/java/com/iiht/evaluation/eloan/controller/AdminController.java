package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.service.ILoanService;
import com.iiht.evaluation.eloan.service.LoanServiceImpl;

import exception.LoanException;


@WebServlet({"/admin","/listall","/process","/updatestatus","/logout"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
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
		this.loanService = new LoanServiceImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getServletPath();
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "/listall" : 
				viewName = listall(request, response);
				break;
			case "/process":
				viewName=process(request,response);
				break;
			case "/callemi":
				viewName=calemi(request,response);
				break;
			case "/updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "/logout":
				viewName = adminLogout(request, response);
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

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoanException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		
		HttpSession session=request.getSession();
		ApprovedLoan sLoan=new ApprovedLoan();
		if(request.getParameter("reject")!=null)
		{
			loanService.updateRejectedLoan(session.getAttribute("currentApplicationId").toString())	;
			if(loanService.updateStatus("rejected", session.getAttribute("currentApplicationId").toString()))
			{
				request.setAttribute("rejected", true);
				request.setAttribute("applicationNo", session.getAttribute("currentApplicationId").toString());
				return "adminhome1.jsp";
			}
			else
			{
				request.setAttribute("rejected", false);
				return "calemi.jsp";
			}
						
		}
		else if(request.getParameter("approve")!=null)
		{
			sLoan.setAmotsanctioned(Double.parseDouble(request.getParameter("amountSactioned")));
			sLoan.setRate(Double.parseDouble(request.getParameter("rate")));
			sLoan.setLoanterm(Integer.parseInt(request.getParameter("loanTerm")));
			sLoan.setPsd(request.getParameter("startDate"));
			sLoan.setLcd(request.getParameter("closureDate"));	
			sLoan.setApplno(session.getAttribute("currentApplicationId").toString());
			loanService.sanctionLoan(sLoan);
			if(loanService.updateStatus("approved", session.getAttribute("currentApplicationId").toString()))
			{
				
				request.setAttribute("approved", true);
				request.setAttribute("applicationNo", session.getAttribute("currentApplicationId").toString());
				request.setAttribute("sanctionedAmt",sLoan.getAmotsanctioned() );
				request.setAttribute("termOfLoan",sLoan.getLoanterm());
				request.setAttribute("psd",sLoan.getPsd());
				request.setAttribute("lcd",sLoan.getLcd());
				request.setAttribute("emi", sLoan.getEmi());
				return "adminhome1.jsp";
			}
			else
			{
				request.setAttribute("approved", false);
				return "calemi.jsp";
			}
		}
		else
			return "notfound.jsp";
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
HttpSession session=request.getSession();
		
		LoanInfo loan = new LoanInfo();
		//ApprovedLoan apLoan=new ApprovedLoan();
		loan.setApplno(request.getParameter("applicationNo"));
		loan.setUserId(session.getAttribute("userId").toString());
		loan=loanService.getLoanDetails(loan,session);
		if(loan!=null)
		{
		request.setAttribute("message",true);
		request.setAttribute("loan", loan);
		session.setAttribute("currentApplicationId", request.getParameter("applicationNo"));
		
		return "calemi.jsp";
		}
		else
		{
		request.setAttribute("message",true);
		session.setAttribute("currentApplicationId", request.getParameter("applicationNo"));
		return "process.jsp";
		}
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoanException {
		// TODO Auto-generated method stub
	/* return to process page */
		HttpSession session=request.getSession();
		LoanInfo loan = new LoanInfo();
		ApprovedLoan aloan=new ApprovedLoan();
		String appNo=request.getParameter("applicationNo");
		loan.setApplno(appNo);
		loan.setUserId(session.getAttribute("userId").toString());
		loan=loanService.getLoanDetailsAdmin(loan,session);
		
		if(loan!=null)
		{
		request.setAttribute("message",true);
		request.setAttribute("loan", loan);
		if(loan.getStatus().equals("approved"))
		{
			aloan=loanService.getProcessedLoanDetails(loan.getApplno());
			request.setAttribute("aloan", aloan);
			
		}
		if(loan.getStatus().equals("approved")||loan.getStatus().equals("rejected"))
		{
		request.setAttribute("processed", true);
		}
		session.setAttribute("currentApplicationId", request.getParameter("applicationNo"));
		return "calemi.jsp";
		}
		else
		{
		request.setAttribute("message",true);
		session.setAttribute("currentApplicationId", appNo);
		return "process.jsp";
		}
		
		
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		 HttpSession session=request.getSession();  
         session.invalidate();  
         request.setAttribute("logout", true);
		return "index.jsp";
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException, LoanException {
	/* write the code to display all the loans */
		request.setAttribute("loansList", loanService.listAll());
		return "listall.jsp";
	}

	
}