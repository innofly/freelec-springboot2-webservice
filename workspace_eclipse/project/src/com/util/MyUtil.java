package com.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyUtil {

// *******************************************************************************
    // 페이지 수 구하기
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount=0;
		
		pageCount=dataCount / numPerPage;
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;
	}
    
    // Get 방식에 의한 페이지 처리 메서드 *************************************
	public String pageIndexList(int current_page, int total_page, String list_url) {
		if(current_page < 1 || total_page < 1)
			return "";
		
		StringBuffer sb=new StringBuffer();
		int numPerBlock=10;
		int currentPageSetup;
		int n, page;
		
		if(list_url.indexOf("?")!=-1)
			list_url=list_url+"&";
		else
			list_url=list_url+"?";
		
		// currentPageSetup : 표시할첫페이지-1
		currentPageSetup=(current_page/numPerBlock)*numPerBlock;
		if(current_page%numPerBlock==0)
			currentPageSetup=currentPageSetup-numPerBlock;
		
		// 1 페이지, [Prev]:10 페이지를 이전페이지로 이동
		n=current_page-numPerBlock;
		if(total_page>numPerBlock && currentPageSetup>0) {
			sb.append("<a href='" + list_url + "pageNum=1'>1</a>&nbsp;");
			sb.append("[<a href='" + list_url + "pageNum=" + n + "'>Prev</a>]&nbsp;");
		}
		
		// 바로가기 페이지
		page=currentPageSetup+1;
		while(page<=total_page && (page<=currentPageSetup+numPerBlock)) {
			if(page==current_page) {
				sb.append("<font color='Fuchsia'>"+page+"</font>&nbsp;");
			} else {
				sb.append("<a href='" + list_url + "pageNum=" + page + "'>" + page + "</a>&nbsp;");
			}
			page++;
		}

		// [Next]:10페이지를 다음페이지로 이동, 마지막 페이지
		n=current_page+numPerBlock;
		if(total_page - currentPageSetup > numPerBlock) {
			sb.append("[<a href='" + list_url + "pageNum=" + n + "'>Next</a>]&nbsp;");
			sb.append("<a href='" + list_url + "pageNum=" + total_page + "'>" + total_page + "</a>");
		}
		
		return sb.toString();
	}

    // 자바 스크립트에 의한 페이지 처리(자바스크립트 listPage() 함수) ***********************
    public String pageIndexList(int current_page, int total_page) {
		if(current_page < 1 || total_page < 1)
			return "";

        int numPerBlock = 10;   // 리스트에 나타낼 페이지 수
        int currentPageSetUp;
        int n;
        int page;
        StringBuffer sb=new StringBuffer();
        
        // 표시할 첫 페이지
        currentPageSetUp = (current_page / numPerBlock) * numPerBlock;
        if (current_page % numPerBlock == 0)
            currentPageSetUp = currentPageSetUp - numPerBlock;

        // 1 페이지, [Prev]
        n = current_page - numPerBlock;
        if ((total_page > numPerBlock) && (currentPageSetUp > 0)) {
			sb.append("<a onclick='listPage(1);'>1</a>&nbsp;");
			sb.append("[<a onclick='listPage("+n+");'>Prev</a>]&nbsp;");
        }

        // 바로가기 페이지 구현
        page = currentPageSetUp + 1;
        while((page <= total_page) && (page <= currentPageSetUp + numPerBlock)) {
           if(page == current_page) {
			   sb.append("<font color='Fuchsia'>"+page+"</font>&nbsp;");
           } else {
			   sb.append("<a onclick='listPage("+page+");'>"+page+"</a>&nbsp;");
           }
           page++;
        }

        // [Next], 마지막 페이지
        n = current_page + numPerBlock;
        if (total_page - currentPageSetUp > numPerBlock) {
			sb.append("[<a onclick='listPage("+n+");'>Next</a>]&nbsp;");
			sb.append("<a onclick='listPage("+total_page+");'>"+total_page+"</a>");
        }

        return sb.toString();
    }
    
// *******************************************************************************
	// 문자열의 내용중 원하는 문자열을 다른 문자열로 변환
	// String str = replaceAll(str, "\r\n", "<br>"); // 엔터를 <br>로 변환
	public String replaceAll(String str, String oldStr, String newStr) throws Exception {
		if(str == null)
			return "";

        Pattern p = Pattern.compile(oldStr);

        // 입력 문자열과 함께 매쳐 클래스 생성
        Matcher m = p.matcher(str);

        StringBuffer sb = new StringBuffer();
        boolean result = m.find();

        // 패턴과 일치하는 문자열을 newStr 로 교체해가며 새로운 문자열을 만든다.
        while(result) {
            m.appendReplacement(sb, newStr);
            result = m.find();
        }

        // 나머지 부분을 새로운 문자열 끝에 덫 붙인다.
        m.appendTail(sb);

		return sb.toString();
	}

	// E-Mail 검사
    public boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches(
            "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", 
            email.trim());
        return b;
    }

	// NULL 인 경우 ""로 
    public String checkNull(String str) {
        String strTmp;
        if (str == null)
            strTmp = "";
        else
            strTmp = str;
        return strTmp;
    }

 // *******************************************************************************
    // 8859_1 를 euc-kr로
    public String isoToEuc(String str) {
        String convStr = null;
        try {
            if(str == null)
                return "";

            convStr = new String(str.getBytes("8859_1"),"euc-kr");
        } catch (UnsupportedEncodingException e) {
        }
        return convStr;
    }

    // 8859_1 를 utf-8로
    public String isoToUtf(String str) {
        String convStr = null;
        try {
            if(str == null)
                return "";

            convStr = new String(str.getBytes("8859_1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return convStr;
    }

    // euc-kr 를 ISO-8859-1 로
    public String eucToIso(String str) {
        String convStr = null;
        try {
            if(str == null)
                return "";

            convStr = new String(str.getBytes("euc-kr"),"8859_1");
        } catch (UnsupportedEncodingException e) {
        }
        return convStr;
    }
    
    // KSC56O1 를 8859_1로
    public String fromKorean(String str) {
        String convStr = null;
        try {
            if(str == null)
                return "";

            convStr = new String(str.getBytes("KSC5601"),"8859_1");
        } catch (UnsupportedEncodingException e) {
        }
        return convStr;
    }
    
    // euc-kr 를 KSC5601 로
    public String eucToKsc(String str) {
        String convStr = null;
        try {
            if(str == null)
                return "";

            convStr = new String(str.getBytes("euc-kr"),"8859_1");
            convStr = new String(convStr.getBytes("8859_1"),"KSC5601");
        } catch (UnsupportedEncodingException e) {
        }
        return convStr;
    }
}
