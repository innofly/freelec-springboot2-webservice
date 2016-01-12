package com.util;

/**
 *  �������� ���ϱ�
 *  @param numPerPage : ���������� ǥ���� �����ͼ�
 *  @param dataCount : ���� ������ ����
 *  @return : ��ü ��������
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
	 * @param current_page : ǥ���� ������
	 * @param total_page : ��ü ��������
	 * @param list_url : ��ũ�� ������ �ּ�
	 * @return : ����¡ ó�� ���
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
		
		// currentPageSetup = ǥ���� ù ������ -1
		currentPageSetup = (current_page/numPerBlock) * numPerBlock;
		
		// ������������ ����...
		if(current_page%numPerBlock == 0)
			currentPageSetup -= numPerBlock;
		
		// 1[prev]
		n = current_page - numPerBlock;
		
		if(total_page>numPerBlock && currentPageSetup > 0)
		{
			sb.append("<a href='"+list_url+"pageNum=1'>1</a>&nbsp;");
			sb.append("[<a href='"+list_url+"pageNum="+n+"'>Prev</a>]&nbsp;");
		}
		
		// �ٷΰ��� ������
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
		
		// [Next] ������ ������
		if(total_page > numPerBlock && (currentPageSetup+numPerBlock)<total_page)
		{
			sb.append("[<a href='" + list_url + "pageNum=" + n + "'>Next</a>]&nbsp;");
			sb.append("<a href='"+list_url+"pageNum="+total_page+"'>"+total_page+"</a>");
		}
		
		return sb.toString();
	}
	
}



