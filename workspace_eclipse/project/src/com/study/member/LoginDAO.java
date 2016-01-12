package com.study.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;

import com.util.DBCPConn;
import com.study.member.*;

public class LoginDAO 
{	
	private Connection con = DBCPConn.getConnection();
	
	public MemberVO loginMember(String id, String pass)
	{
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();		
		MemberVO vo = null;
		
		query.append("select * from tbl_member ");
		query.append(" where user_id = ? and pass = ? ");
		
		try{										    
		    pstmt = con.prepareStatement(query.toString());
		    
		    int key = 1;
			pstmt.setString(key++, id);
			pstmt.setString(key++, pass);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setUser_id(rs.getString("user_id"));
				vo.setUser_name(rs.getString("user_name"));
				vo.setPhone_num(rs.getString("phone_num"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setAddress(rs.getString("address"));
				vo.setLocation(rs.getString("location"));				
			}
			rs.close();
			pstmt.close();
		    			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		return vo;
	}
}
