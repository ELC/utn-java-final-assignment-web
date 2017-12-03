<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>ID</th>
	        <th>DNI</th>
	        <th>Name</th>
	        <th>Last Name</th>
	        <th>User Name</th>
	        <th>E Mail</th>
	        <th>Role</th>
	        <th>Enable</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="person" items="${Persons}">
			<tr>
				<td>${person.id}</td>
				<td>${person.dni}</td>
				<td>${person.name}</td>
				<td>${person.lastName}</td>
				<td>${person.username}</td>
				<td>${person.email}</td>
				<td>${person.userRoles.name}</td>
				<td>
				<c:if test="${person.enabled}">
					&#10004;
				</c:if>
				<c:if test="${!person.enabled}">
					&#x2613;
				</c:if>
				</td>
			</tr>
	     	</c:forEach>
	    </tbody>
	  </table>

    </jsp:body>
</t:base>