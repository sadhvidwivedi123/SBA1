package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;

import com.iiht.evaluation.eloan.model.User;

import exception.LoanException;

public class UserDaoImpl implements IUserDao{

	public static final String INS_QRY=
			"INSERT INTO user_details(user_id,password,name,phone_no,email_id,role) VALUES(?,?,?,?,?,?)";
	/*public static final String UPD_QRY=
			"UPDATE TABLE books SET title=?,price=?,publish_date=?,category=? WHERE b_code=?";
	public static final String DEL_QRY=
			"DELETE FROM books WHERE b_code=?";
	public static final String SELECT_ALL_QRY=
			"SELECT b_code,title,price,publish_date,category FROM books";
	public static final String SELECT_BY_ID_QRY=
			"SELECT b_code,title,price,publish_date,category FROM books WHERE b_code=?";*/
	public static final String SELECT_BY_ID_QRY=
			"SELECT user_id,password,role FROM user_details WHERE user_id=?";
	
	@Override
	public String validate(User user) throws LoanException {
		// TODO Auto-generated method stub

		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(SELECT_BY_ID_QRY);
			pst.setString(1, user.getUserid());
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				if(user.getPassword().equals(rs.getString(2)))
				{
					if("admin".equals(rs.getString(3)))
					{
						return "admin";
					}
					else if("user".equals(rs.getString(3)))
					{
						return "user";
					}
				}
				else return "incorrect credentials";
			}
			else return "incorrect credentials";
		} catch (SQLException e) {
			//log.error(exp);
			throw new LoanException("Sorry! An Error Occured While fetching Data!");
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
		
		return null;
	}
	

	@Override
	public User addUser(User user) throws LoanException {
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(INS_QRY);
			pst.setString(1, user.getUserid());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getName());
			pst.setString(4, user.getPhoneNo());
			pst.setString(5, user.getEmail());
			pst.setString(6, "user");
		
			pst.executeUpdate();
		return user;
	}catch(SQLException e) {
		//log.error(exp);
		throw new LoanException("Sorry! An Error Occured While saving Data!");
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
	public String getUserID(String userid) throws LoanException {
		ConnectionDao conn=new ConnectionDao("jdbc:mysql://localhost:3306/eloan", "root", "root");
		Connection con;
		try {
			con = conn.connect();
			PreparedStatement pst = con.prepareStatement(SELECT_BY_ID_QRY);
			pst.setString(1, userid);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
			else
			{
				return "";
			}
		}
	 catch (SQLException e) {
		//log.error(exp);
		throw new LoanException("Sorry! An Error Occured While fetching Data!");
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
