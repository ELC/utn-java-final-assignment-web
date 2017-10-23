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
	        <th>Type</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="book" items="${Bookables}">
			<tr>
				<td>${book.id}</td>
				<td>${book.name}</td>
				<td>${book.type.getId()}</td>
			</tr>
	     	</c:forEach>
	    </tbody>
	  </table>
    </jsp:body>
</t:base>