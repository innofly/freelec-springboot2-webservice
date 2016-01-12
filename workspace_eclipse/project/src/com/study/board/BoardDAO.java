package com.study.board;

import java.sql.*;
import com.util.*;
import java.util.ArrayList;

public class BoardDAO 
{
	private Connection con = DBCPConn.getConnection();
	
	// 글쓰기
	public int insertBoard(BoardVO vo)
	{
		
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();

		query.append("insert into tbl_board(seq, user_id, user_name, title, content, bbs_id, input_date) ");
		query.append(" values (tbl_board_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE ) ");
		
		try{					
			pstmt = con.prepareStatement(query.toString());
		    int key = 1;
		    
		    pstmt.setString(key++, vo.getUser_id());
		    pstmt.setString(key++, vo.getUser_name());
		    pstmt.setString(key++, vo.getTitle());
		    pstmt.setString(key++, vo.getContent());
		    pstmt.setString(key++, vo.getBbs_id());
			
		    result = pstmt.executeUpdate();			
			pstmt.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//리스트
	public ArrayList getBoardList(int pageNo, String pages)
	{
			  
       StringBuffer query = new StringBuffer();
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
		  
	   query.append("SELECT * FROM ( ");
	   query.append(" SELECT CEIL(ROW_NUMBER() OVER (ORDER BY SEQ DESC) / "+pageNo+") AS page, ");
	   query.append(" count(*) over() as TOTAL_COUNT, ");
	   query.append(" SEQ, USER_ID, USER_NAME, TITLE, CONTENT, BBS_ID, INPUT_DATE ");
	   query.append(" FROM TBL_BOARD ");
	   query.append(" ) "); 
	   query.append(" WHERE page =" +pages);
	   
	   BoardVO vo = null;
	   ArrayList<BoardVO> arraylist = new ArrayList<BoardVO>();
	
	   try{
			  pstmt = con.prepareStatement(query.toString());
		      rs = pstmt.executeQuery();
		      
		      while (rs.next()) {
		    	  vo = new BoardVO();
		    	  vo.setTotal_count(rs.getInt("TOTAL_COUNT"));
		          vo.setSeq(rs.getInt("SEQ"));	
		          vo.setUser_id(rs.getString("USER_ID"));			      
		          vo.setUser_name(rs.getString("USER_NAME"));
		          vo.setTitle(rs.getString("TITLE"));
		          vo.setContent(rs.getString("CONTENT"));
		          vo.setBbs_id(rs.getString("BBS_ID"));
		          vo.setInput_date(rs.getString("INPUT_DATE"));

		          arraylist.add(vo);
		      }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return arraylist;	   		
	}
	
	//글보기
	public BoardVO boardView(int seq)
	{
		
		BoardVO vo = null;
		StringBuffer query = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		query.append(" SELECT SEQ, USER_ID, USER_NAME, TITLE, CONTENT, BBS_ID, INPUT_DATE FROM TBL_BOARD ");
		query.append(" WHERE SEQ = ? ");
		
		try{
			pstmt = con.prepareStatement(query.toString());
			int key = 1;
			pstmt.setInt(key++, seq);
			rs = pstmt.executeQuery();
			
			 while (rs.next()) {
				 vo = new BoardVO();
				 vo.setSeq(rs.getInt("SEQ"));
				 vo.setUser_id(rs.getString("USER_ID"));
				 vo.setUser_name(rs.getString("USER_NAME"));
				 vo.setTitle(rs.getString("TITLE"));
				 vo.setContent(rs.getString("CONTENT"));
				 vo.setBbs_id(rs.getString("BBS_ID"));
				 vo.setInput_date(rs.getString("INPUT_DATE"));	 				 				 
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return vo;
	}
	
	//수정
	public int boardUpdate(BoardVO vo)
	{
		int result = 0;
		StringBuffer query = new StringBuffer();
		PreparedStatement pstmt = null;
		
		query.append(" UPDATE TBL_BOARD ");
		query.append(" SET  TITLE = ?, CONTENT = ?, INPUT_DATE = SYSDATE ");
		query.append(" WHERE SEQ = ? ");
		
		try{
			pstmt = con.prepareStatement(query.toString());
			int key = 1;
			pstmt.setString(key++, vo.getTitle());
			pstmt.setString(key++, vo.getContent());
			pstmt.setInt(key++, vo.getSeq());

			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;		
	}
	
	//삭제
	public int boardDelete(int seq)
	{
		int result = 0;
		StringBuffer query = new StringBuffer();
		PreparedStatement pstmt = null;		
		
		query.append(" DELETE TBL_BOARD ");
		query.append(" WHERE SEQ = ? ");
		
		try{		
			pstmt = con.prepareStatement(query.toString());
			int key = 1;
			pstmt.setInt(key++, seq);		    
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
}
