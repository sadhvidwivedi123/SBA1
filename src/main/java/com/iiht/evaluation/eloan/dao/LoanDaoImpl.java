package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

import exception.LoanException;

public class LoanDaoImpl implements ILoanDao{
	
	public static final String INS_QRY=
			"INSERT INTO loan_info(user_id,appl_no,loan_desc,amount_requested,appl_date,business_strc,billing_ind,tax_ind,address,mobile,email,loan_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_BY_TOTALAPPNO_QRY="SELECT COUNT(*) FROM loan_info";

	public static final String SELECT_BY_APPNO_QRY="SELECT * FROM loan_info WHERE appl_no=?";
	
	public static final String SELECT_STATUS_BY_APPNO_QRY="SELECT app_no FROM approved_loans WHERE app_no=?";
	
	public static final String UPDATE_BY_APPNO_QRY="UPDATE loan_info SET loan_desc=?,amount_requested=?,business_strc=?,billing_ind=?,tax_ind=?,address=?,mobile=?,email=? WHERE appl_no=?";
	
	public static final String SELECT_USER_BY_APPNO="SELECT user_id from loan_info where appl_no=?";
	
	public static final String SELECT_LOANS="SELECT appl_no,loan_desc,amount_requested,appl_date,loan_status from loan_info";
	
	public static final String UPDATE_STATUS_BY_APPNO="UPDATE loan_info SET loan_status=? where appl_no=?";
	
	
	public static final String INSERT_APPROVELOAN="INSERT INTO approved_loans(app_no, sanctioned_amount,rate,term,payment_start_dte,loan_closure_dte,emi,term_payment_amount,comments) VALUES(?,?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_APPROVELOAN="UPDATE approved_loans SET sanctioned_amount=?,rate=?,term=?,payment_start_dte=?,loan_closure_dte=?,emi=?,term_payment_amount=?,comments=? WHERE app_no=?";
	
	public static final String SELECT_APPROVELOAN="SELECT * FROM approved_loans where app_no=?";
	
	public static final String UPDATE_REJECTEDLOAN="UPDATE approved_loans SET sanctioned_amount=null,rate=null,term=null,payment_start_dte=null,loan_closure_dte=null,emi=null,term_payment_amount=null where app_no=?";
	
	@Override
	public String getLoanNo() throws LoanException{
		
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(SELECT_BY_TOTALAPPNO_QRY);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
			else
			{
				return "0";
			}
				
		}
		catch(SQLException e)
		{
			throw new LoanException("Sorry! An Error Occured While Fetching Data!");
		}
			
	}
	@Override
	public LoanInfo applyLoan(LoanInfo loan) throws LoanException {

		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;

		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(INS_QRY);
			pst.setString(1, loan.getUserId());
			pst.setString(2, loan.getApplno());
			pst.setString(3, loan.getPurpose());
			pst.setDouble(4, loan.getAmtrequest());
			pst.setDate(5, java.sql.Date.valueOf(loan.getDoa()));
			pst.setString(6, loan.getBstructure());
			pst.setString(7, loan.getBindicator());
			pst.setString(8, loan.getTindicator());
			pst.setString(9, loan.getAddress());
			pst.setString(10, loan.getMobile());
			pst.setString(11, loan.getEmail());
			pst.setString(12, loan.getStatus());
			
			pst.executeUpdate();
			
			return loan;
			
		} catch (SQLException e) {
			//log.error(exp);
			throw new LoanException("Sorry! An Error Occured While Saving Data!");
			
		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	
	}
	@Override
	public void updateLoan(LoanInfo loan) throws LoanException {
		// TODO Auto-generated method stub
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(UPDATE_BY_APPNO_QRY);
			pst.setString(1, loan.getPurpose());
			pst.setDouble(2, loan.getAmtrequest());
			pst.setString(3, loan.getBstructure());
			pst.setString(4, loan.getBindicator());
			pst.setString(5, loan.getTindicator());
			pst.setString(6, loan.getAddress());
			pst.setString(7, loan.getMobile());
			pst.setString(8, loan.getEmail());
			pst.setString(9, loan.getApplno());

			pst.executeUpdate();
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Updating Data based on Tracking Id!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	@Override
	public LoanInfo getLoanDetails(LoanInfo loan) throws LoanException {
		// TODO Auto-generated method stub
		
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(SELECT_BY_APPNO_QRY);
			pst.setString(1,loan.getApplno());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				loan.setUserId(rs.getString(1));
				loan.setApplno(rs.getString(2));
				loan.setPurpose((rs.getString(3)));
				loan.setAmtrequest((Double.parseDouble(rs.getString(4))));
				loan.setDoa((LocalDate.parse(rs.getString(5))));
				loan.setBstructure(rs.getString(6));
				loan.setBindicator(rs.getString(7));
				loan.setTindicator(rs.getString(8));
				loan.setAddress(rs.getString(9));
				loan.setMobile(rs.getString(10));
				loan.setEmail(rs.getString(11));
				loan.setStatus(rs.getString(12));
			}
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Updating Data based on Tracking Id!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return loan;
	}
	@Override
	public String getLoanUser(String applno) throws LoanException {
		// TODO Auto-generated method stub
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(SELECT_BY_APPNO_QRY);
			pst.setString(1,applno);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Updating Data based on Tracking Id!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
		
	}
	public List<LoanInfo> listAll() throws LoanException, SQLException {
		List<LoanInfo> loans=new ArrayList<>();
		
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;	
		con = conn.connect();
		try( 
				
			PreparedStatement pst = con.prepareStatement(SELECT_LOANS)){
							
			ResultSet rs = pst.executeQuery();
//appl_no,loan_desc,amount_requested,appl_date			
			while(rs.next()) {
				LoanInfo loan = new LoanInfo();
				loan.setApplno(rs.getString(1));
				loan.setPurpose(rs.getString(2));
				loan.setAmtrequest(Double.parseDouble(rs.getString(3)));
				loan.setDoa(LocalDate.parse(rs.getString(4)));
				loan.setStatus(rs.getString(5));
				loans.add(loan);
			}
						
		}catch(SQLException exp) {
			//log.error(exp);
			throw new LoanException("Sorry! An Error Occured While Fetching Data!");
		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return loans;
	}
	@Override
	public boolean updateStatus(String status, String applNo) throws LoanException {
		// TODO Auto-generated method stub
		
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(UPDATE_STATUS_BY_APPNO);
			pst.setString(1, status);
			pst.setString(2, applNo);
			pst.executeUpdate();
			return true;
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Updating Status based on Tracking Id!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void sanctionLoan(ApprovedLoan sLoan) throws LoanException {
		// TODO Auto-generated method stub
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			//PreparedStatement pst = con.prepareStatement(SELECT_APPROVELOAN);
			//PreparedStatement pst1 = con.prepareStatement(UPDATE_APPROVELOAN);
			PreparedStatement pst2 = con.prepareStatement(INSERT_APPROVELOAN);
			//pst.setString(1,sLoan.getApplno());
			//ResultSet rs1=pst.executeQuery();
			
					
			pst2.setString(1, sLoan.getApplno());
			pst2.setString(2, sLoan.getAmotsanctioned().toString());
			pst2.setString(3, sLoan.getRate().toString());
			pst2.setString(4, sLoan.getLoanterm().toString());
			pst2.setString(5, sLoan.getPsd());
			pst2.setString(6, sLoan.getLcd());
			pst2.setString(7, sLoan.getEmi().toString());
			pst2.setString(8, sLoan.getTermpayment().toString());
			pst2.setString(9, sLoan.getComments());
			pst2.executeUpdate();
			
			
			
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Creating Approved loan!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public ApprovedLoan getProcessedLoanDetails(String applno) throws LoanException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ApprovedLoan aloan=new ApprovedLoan();
				ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
				Connection con;
				try {
					con = conn.connect();
					PreparedStatement pst = con.prepareStatement(SELECT_APPROVELOAN);
					pst.setString(1,applno);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						aloan.setApplno(rs.getString(1));
						aloan.setAmotsanctioned(Double.parseDouble(rs.getString(2)));						
						aloan.setRate(Double.parseDouble(rs.getString(3)));
						aloan.setLoanterm(Integer.parseInt(rs.getString(4)));
						aloan.setPsd(rs.getString(5));
						aloan.setLcd(rs.getString(6));
						aloan.setEmi(Double.parseDouble(rs.getString(7)));
						aloan.setTermpayment(Double.parseDouble(rs.getString(8)));
						aloan.setComments(rs.getString(9));
						
					}
				}
				catch(SQLException exp)
				{
						//log.error(exp);
						throw new LoanException("Sorry! An Error Occured While Fetching Approved loan Data based on Tracking Id!");

				}
				finally
				{
					try {
						conn.disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return aloan;
	}
	@Override
	public void updateRejectedLoan(String appNo) throws LoanException {
		ApprovedLoan aloan=new ApprovedLoan();
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(UPDATE_REJECTEDLOAN);
			pst.setString(1,appNo);
			pst.executeUpdate();
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Fetching Approved loan Data based on Tracking Id!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public String ifLoanExists(String appNo) throws LoanException
	{
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(SELECT_STATUS_BY_APPNO_QRY);
			pst.setString(1,appNo);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
			else
			{
				return "noRecords";
			}
		}
		catch(SQLException exp)
		{
				//log.error(exp);
				throw new LoanException("Sorry! An Error Occured While Fetching Approved loan Data based on Tracking Id!");

		}
		finally
		{
			try {
				conn.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	@Override
	public void updateSanctionLoan(ApprovedLoan sLoan) throws LoanException {
		// TODO Auto-generated method stub
		{
			// TODO Auto-generated method stub
			ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
			Connection con;
			try {
				con = conn.connect();
				//PreparedStatement pst = con.prepareStatement(SELECT_APPROVELOAN);
				PreparedStatement pst1 = con.prepareStatement(UPDATE_APPROVELOAN);
				//PreparedStatement pst2 = con.prepareStatement(INSERT_APPROVELOAN);
				//pst.setString(1,sLoan.getApplno());
				//ResultSet rs1=pst.executeQuery();
				
			//sanctioned_amount=?,rate=?,term=?,payment_start_dte=?,loan_closure_dte=?,emi=?,term_payment_amount?,comments=?) WHERE app_no=?
			pst1.setString(1, sLoan.getAmotsanctioned().toString());
			pst1.setString(2, sLoan.getRate().toString());
			pst1.setString(3, sLoan.getLoanterm().toString());
			pst1.setString(4, sLoan.getPsd());
			pst1.setString(5, sLoan.getLcd());
			pst1.setString(6, sLoan.getEmi().toString());
			pst1.setString(7, sLoan.getTermpayment().toString());
			pst1.setString(8, sLoan.getComments());
			pst1.setString(9, sLoan.getApplno());
			pst1.executeUpdate();
		}
			catch(SQLException exp)
			{
					//log.error(exp);
					throw new LoanException("Sorry! An Error Occured While Creating Approved loan!");

			}
			finally
			{
				try {
					conn.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
}
}
