<%@page import="entities.TypeBookable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
	    <c:if test="${typeBookable != null}">
	    
			<c:set var="type" value="${typeBookable}" />
		
			<h2>Id : ${type.id} </h2>
			<h2>Name : ${type.name}</h2>
			<h2>Days Limits : ${type.dayslimit}</h2>
			<h2>Hours Limits : ${type.hourslimit} hs</h2>
			<h2>MaxBookings : ${type.maxBookings} </h2>
		 <c:if test="${type.restriction==1}">
	    <h2>Restriction : Yes </h2>
			</c:if>
			 <c:if test="${type.restriction==0}">
	    <h2>Restriction : No </h2>
			</c:if>
		
			
		</c:if>
    </jsp:body>
</t:base>
