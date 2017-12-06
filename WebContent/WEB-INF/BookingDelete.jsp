<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:base>
    <jsp:body>
		<form name="DeletePerson" action="${pageContext.request.contextPath}/Booking/Delete" method="post">
		    <select required style="width : 143px" name="id">
            	<c:forEach var="tb" items="${ListBookings}">
					<option value="${tb.id}" >
						${tb.id}
					</option>
	     		</c:forEach>
            </select>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Delete Booking</button>
		</form>
    </jsp:body>
</t:base>
