<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
int totalPage = page.getTotalPages();

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("totalPage", totalPage);
%>
<aside class="paging">
           <%if(page.hasPreviousPage()){ %>
			<a href="?pageNo=${current-1}&${searchParams}">上一页</a>
			<%}%>
       <c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <a href="javasript:void(0)" style="background-color: gray;">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="?pageNo=${i}&${searchParams}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
		 <% if (page.hasNextPage()){%>
			 <a href="?pageNo=${current+1}&${searchParams}">下一页</a>
			 <%} %>
		<b>共 <%=page.getTotalElements()%> 条数据</b>
</aside>

