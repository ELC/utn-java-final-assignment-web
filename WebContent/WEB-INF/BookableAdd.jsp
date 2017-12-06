<%@page import="entities.TypeBookable"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
        <h2> Add Bookable :</h2>
        <form name="AddBookable" action="${pageContext.request.contextPath}/Bookable/Add" method="post">
            <input name="NameBookable" id="inputNameBookable" class="form-control" placeholder="Name_Bookable" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
            <select required style="width : 143px" name="selectedType" >
            	<c:forEach var="tb" items="${ListTypeBookables}">
					<option value="${tb.id}" >
						${tb.name}
					</option>
	     		</c:forEach>
            </select>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Add Bookable</button>
        </form>
    </jsp:body>
</t:base>