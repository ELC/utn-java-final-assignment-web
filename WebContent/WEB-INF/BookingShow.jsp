<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>ID</th>
	        <th>Person</th>
	        <th>Bookable</th>
	        <th>Date</th>
	        <th>Detail</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="booking" items="${Bookings}">
			<tr>
				<td>${booking.id}</td>
				<td>${booking.person.name}</td>
				<td>${booking.bookable}</td>
				<td>${booking.date}</td>
				<td>${booking.detail}</td>
			</tr>
	     	</c:forEach>
	    </tbody>
	  </table>
    </jsp:body>
</t:base>