<%@page import="entities.TypeBookable"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
        <h2> Update Bookable :</h2>
        <form name="UpdateBookable" action="${pageContext.request.contextPath}/Bookable/Update" method="post">
           	<input name="Id" id="inputNameBookable" class="form-control" placeholder="Id Bookable" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
            <input name="NameBookable" id="inputNameBookable" class="form-control" placeholder="Name Bookable" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
            <select style="width : 143px" name="selectedType" >
            	<c:forEach var="tb" items="${ListTypeBookables}">
					<option value="${tb.id}" >
						${tb.name}
					</option>
	     		</c:forEach>
            </select>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Update Bookable</button>
        </form>
    </jsp:body>
</t:base>