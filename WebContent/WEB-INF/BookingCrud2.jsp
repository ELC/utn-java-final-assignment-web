<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<t:base>
    <jsp:body>
		<form name="AddBooking" action="${pageContext.request.contextPath}/Booking/CRUD" method="GET">
        	<select style="width : 143px" name="tb" >
            	<c:forEach var="tb" items="${ListTypeBookables}">
					<option value="${tb.id}" >
						${tb.name}
					</option>
	     		</c:forEach>
            </select>			
        	<button class="btn btn-lg btn-primary btn-block" type="submit">next Step</button>        
    	</form>
    </jsp:body>
</t:base>