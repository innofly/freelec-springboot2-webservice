package com.study.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import com.util.DBCPConn;

public class MemberDAO 
{	
	private Connection con = DBCPConn.getConnection();
	
	public int insertMember(MemberVO vo)
	{
		
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();

		query.append("insert into tbl_member(seq, user_id, pass, user_name, email, phone_num, birthday, address, location, join_date) ");
		query.append(" values (tbl_member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE ) ");
		
		try{					
			pstmt = con.prepareStatement(query.toString());
		    int key = 1;
		    
		    pstmt.setString(key++, vo.getUser_id());
		    pstmt.setString(key++, vo.getPass());
		    pstmt.setString(key++, vo.getUser_name());
		    pstmt.setString(key++, vo.getEmail());
		    pstmt.setString(key++, vo.getPhone_num());
		    pstmt.setString(key++, vo.getBirthday());
		    pstmt.setString(key++, vo.getAddress());
		    pstmt.setString(key++, vo.getLocation());
			
		    result = pstmt.executeUpdate();			
			pstmt.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		return result;
	}
}
