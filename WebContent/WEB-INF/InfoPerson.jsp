<%@page import="entities.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
    <jsp:body>
    
    <c:set var="person" value="${person}" />

	<h2>Id : ${person.id} </h2>
	<h2>Dni : ${person.dni}</h2>
	<h2>Name : ${person.name}</h2>
	<h2>Last_Name :	${person.lastName}</h2>
	<h2>Email : ${person.email}</h2>
	<h2>UserName : ${person.username}</h2>

    </jsp:body>
</t:base>
