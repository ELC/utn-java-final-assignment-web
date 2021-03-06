<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
	<table class="table table-hover" style="width:1170px;margin-left:auto;margin-right:auto">
	    <thead>
	      <tr>
	        <th>ID</th>
	        <th>Name</th>
	        <th>Hours Limit</th>
	        <th>Days Limit</th>
	        <th>maxBookings</th>
	        <th>Restriction</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="typebookable" items="${TypeBookables}"> 
			<tr>
				<td>${typebookable.id}</td>
				<td>${typebookable.name}</td>
				<td>${typebookable.hourslimit}</td>
				<td>${typebookable.dayslimit}</td>
				<td>${typebookable.maxBookings}</td>
				<td>
				<c:if test="${typebookable.restriction == 1}">
					&#10004;
				</c:if>
				<c:if test="${typebookable.restriction == 0}">
					&#x2613;
				</c:if>
				</td>
			</tr>
	     </c:forEach>
	    </tbody>
	  </table>
    </jsp:body>
</t:base>