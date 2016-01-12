package com.util;

/**
 *  페이지수 구하기
 *  @param numPerPage : 한페이지에 표시할 데이터수
 *  @param dataCount : 전페 데이터 개수
 *  @return : 전체 페이지수
 */

public class Paging
{
	public int getPageCount(int numPerPage, int dataCount)
	{
		int result = 0;
		
		if(dataCount>0)
		{
			result = dataCount/numPerPage;
			
			if(dataCount%numPerPage!=0)
				result++;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param current_page : 표시할 페이지
	 * @param total_page : 전체 페이지수
	 * @param list_url : 링크를 설정할 주소
	 * @return : 페이징 처리 결과
	 */
	
	public String pageIndexList(int current_page, int total_page, String list_url)
	{
		StringBuffer sb = new StringBuffer();
		
		int numPerBlock = 10;
		int currentPageSetup;
		int n, page;
		
		if(current_page <= 0)
			return "";
		
		if(list_url.indexOf("?") != -1)
		{
			list_url += "&";
		}
		else
		{
			list_url += "?";
		}
		
		// currentPageSetup = 표시할 첫 페이지 -1
		currentPageSetup = (current_page/numPerBlock) * numPerBlock;
		
		// 시작페이지를 결정...
		if(current_page%numPerBlock == 0)
			currentPageSetup -= numPerBlock;
		
		// 1[prev]
		n = current_page - numPerBlock;
		
		if(total_page>numPerBlock && currentPageSetup > 0)
		{
			sb.append("<a href='"+list_url+"pageNum=1'>1</a>&nbsp;");
			sb.append("[<a href='"+list_url+"pageNum="+n+"'>Prev</a>]&nbsp;");
		}
		
		// 바로가기 페이지
		page = currentPageSetup+1;
		
		while(page<=total_page && page<=(currentPageSetup+numPerBlock))
		{
			if(page == current_page)
			{
				sb.append("<font color='Fuchsia'>"+ page +"</font>&nbsp;");
			}
			else
			{
				sb.append("<a href='"+list_url+"pageNum="+page+"'>"+page+"</a>&nbsp;");
			}
			page++;					
		}
		
		// [Next] 마지막 페이지
		if(total_page > numPerBlock && (currentPageSetup+numPerBlock)<total_page)
		{
			sb.append("[<a href='" + list_url + "pageNum=" + n + "'>Next</a>]&nbsp;");
			sb.append("<a href='"+list_url+"pageNum="+total_page+"'>"+total_page+"</a>");
		}
		
		return sb.toString();
	}
	
}



