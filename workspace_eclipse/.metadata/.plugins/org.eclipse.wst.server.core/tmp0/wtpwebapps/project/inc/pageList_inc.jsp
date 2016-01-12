<%@ page pageEncoding="utf-8"%>
							<div class="pagination">
<%	
if (allCount > 0) {
    // 전체 페이지 구하기
    if (allCount % pageNo > 0) {
        lastpage = (allCount / pageNo) + 1; 
    } else {
        lastpage = (allCount / pageNo); 
    }
	int PAGESIZE = 10;
	int pageStart = (int)((p-1)/PAGESIZE) * PAGESIZE + 1;
	if (p-10 > 0) {
%>
								<a href="<%=request.getRequestURI()%>?<%=addStr%>&p=<%=pageStart-10%>"><img src="../images/sub01/prev_btn1.gif" alt="" /></a>
<%
	}
	if (p > 1) {
%>
								<a href="<%=request.getRequestURI()%>?<%=addStr%>&p=<%=p-1%>"><img src="../images/sub01/prev_btn2.gif" alt="" /></a>
<%
	}

	int pageCnt = 0;
	while(pageStart <= lastpage)
	{
		if(pageStart != p) {
%>
								<a href="<%=request.getRequestURI()%>?<%=addStr%>&p=<%= pageStart %>"><%= pageStart %></a>
<%
		}else{
%>
								<strong><%= pageStart %></strong>
<%
		}

		if (pageStart < lastpage) out.print (" | ");

		pageStart++;
		pageCnt++;
		if(pageCnt >= 10) break;
	}

	if (p+10 <= lastpage) {
%>
								<a href="<%=request.getRequestURI()%>?<%=addStr%>&p=<%=p+1%>"><img src="../images/sub01/next_btn1.gif" alt="" /></a>
<%
	}

	if (p+10 <= lastpage) {
%>
								<a href="<%=request.getRequestURI()%>?<%=addStr%>&p=<%=p+10%>"><img src="../images/sub01/next_btn2.gif" alt="" /></a>
<%
	}
} 
else 
{
%>
								&nbsp;
<%  
} 
%>
							</div>