package com.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyUtil {

// *******************************************************************************
    // ������ �� ���ϱ�
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount=0;
		
		pageCount=dataCount / numPerPage;
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;
	}
    
    // Get ��Ŀ� ���� ������ ó�� �޼��� *************************************
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
		
		// currentPageSetup : ǥ����ù������-1
		currentPageSetup=(current_page/numPerBlock)*numPerBlock;
		if(current_page%numPerBlock==0)
			currentPageSetup=currentPageSetup-numPerBlock;
		
		// 1 ������, [Prev]:10 �������� ������������ �̵�
		n=current_page-numPerBlock;
		if(total_page>numPerBlock && currentPageSetup>0) {
			sb.append("<a href='" + list_url + "pageNum=1'>1</a>&nbsp;");
			sb.append("[<a href='" + list_url + "pageNum=" + n + "'>Prev</a>]&nbsp;");
		}
		
		// �ٷΰ��� ������
		page=currentPageSetup+1;
		while(page<=total_page && (page<=currentPageSetup+numPerBlock)) {
			if(page==current_page) {
				sb.append("<font color='Fuchsia'>"+page+"</font>&nbsp;");
			} else {
				sb.append("<a href='" + list_url + "pageNum=" + page + "'>" + page + "</a>&nbsp;");
			}
			page++;
		}

		// [Next]:10�������� ������������ �̵�, ������ ������
		n=current_page+numPerBlock;
		if(total_page - currentPageSetup > numPerBlock) {
			sb.append("[<a href='" + list_url + "pageNum=" + n + "'>Next</a>]&nbsp;");
			sb.append("<a href='" + list_url + "pageNum=" + total_page + "'>" + total_page + "</a>");
		}
		
		return sb.toString();
	}

    // �ڹ� ��ũ��Ʈ�� ���� ������ ó��(�ڹٽ�ũ��Ʈ listPage() �Լ�) ***********************
    public String pageIndexList(int current_page, int total_page) {
		if(current_page < 1 || total_page < 1)
			return "";

        int numPerBlock = 10;   // ����Ʈ�� ��Ÿ�� ������ ��
        int currentPageSetUp;
        int n;
        int page;
        StringBuffer sb=new StringBuffer();
        
        // ǥ���� ù ������
        currentPageSetUp = (current_page / numPerBlock) * numPerBlock;
        if (current_page % numPerBlock == 0)
            currentPageSetUp = currentPageSetUp - numPerBlock;

        // 1 ������, [Prev]
        n = current_page - numPerBlock;
        if ((total_page > numPerBlock) && (currentPageSetUp > 0)) {
			sb.append("<a onclick='listPage(1);'>1</a>&nbsp;");
			sb.append("[<a onclick='listPage("+n+");'>Prev</a>]&nbsp;");
        }

        // �ٷΰ��� ������ ����
        page = currentPageSetUp + 1;
        while((page <= total_page) && (page <= currentPageSetUp + numPerBlock)) {
           if(page == current_page) {
			   sb.append("<font color='Fuchsia'>"+page+"</font>&nbsp;");
           } else {
			   sb.append("<a onclick='listPage("+page+");'>"+page+"</a>&nbsp;");
           }
           page++;
        }

        // [Next], ������ ������
        n = current_page + numPerBlock;
        if (total_page - currentPageSetUp > numPerBlock) {
			sb.append("[<a onclick='listPage("+n+");'>Next</a>]&nbsp;");
			sb.append("<a onclick='listPage("+total_page+");'>"+total_page+"</a>");
        }

        return sb.toString();
    }
    
// *******************************************************************************
	// ���ڿ��� ������ ���ϴ� ���ڿ��� �ٸ� ���ڿ��� ��ȯ
	// String str = replaceAll(str, "\r\n", "<br>"); // ���͸� <br>�� ��ȯ
	public String replaceAll(String str, String oldStr, String newStr) throws Exception {
		if(str == null)
			return "";

        Pattern p = Pattern.compile(oldStr);

        // �Է� ���ڿ��� �Բ� ���� Ŭ���� ����
        Matcher m = p.matcher(str);

        StringBuffer sb = new StringBuffer();
        boolean result = m.find();

        // ���ϰ� ��ġ�ϴ� ���ڿ��� newStr �� ��ü�ذ��� ���ο� ���ڿ��� �����.
        while(result) {
            m.appendReplacement(sb, newStr);
            result = m.find();
        }

        // ������ �κ��� ���ο� ���ڿ� ���� �� ���δ�.
        m.appendTail(sb);

		return sb.toString();
	}

	// E-Mail �˻�
    public boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches(
            "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", 
            email.trim());
        return b;
    }

	// NULL �� ��� ""�� 
    public String checkNull(String str) {
        String strTmp;
        if (str == null)
            strTmp = "";
        else
            strTmp = str;
        return strTmp;
    }

 // *******************************************************************************
    // 8859_1 �� euc-kr��
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

    // 8859_1 �� utf-8��
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

    // euc-kr �� ISO-8859-1 ��
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
    
    // KSC56O1 �� 8859_1��
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
    
    // euc-kr �� KSC5601 ��
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
