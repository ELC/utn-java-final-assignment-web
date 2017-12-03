<%@page import="entities.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
	    <c:if test="${bookable != null}">
	    
			<c:set var="bookable" value="${bookable}" />
		
			<h2>Id : ${bookable.id} </h2>
			<h2>Name : ${bookable.name}</h2>
			<h2>Type : ${bookable.type.name}</h2>
		
			</c:if>
    </jsp:body>
</t:base>