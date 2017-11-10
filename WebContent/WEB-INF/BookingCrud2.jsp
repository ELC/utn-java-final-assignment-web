<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<t:base>
    <jsp:body>
		<form name="AddBooking" action="${pageContext.request.contextPath}/Booking/CRUD" method="POST">
        	
            <select style="width : 143px" name="selectedType" >
            	<c:forEach var="b" items="${ListBookables}">
					<option value="${b.id}" >
						${b.name}
					</option>
	     		</c:forEach>
            </select>			
        	<button class="btn btn-lg btn-primary btn-block" type="submit">add Booking</button>        
    	</form>
    </jsp:body>
</t:base>

