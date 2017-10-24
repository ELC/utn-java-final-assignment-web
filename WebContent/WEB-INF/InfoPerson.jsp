<%@page import="entities.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>

	<h2>Id : <%=((Person)session.getAttribute("person")).getDni() %></h2>
	<h2>Dni : <%=((Person)session.getAttribute("person")).getDni() %></h2>
	<h2>Name :<%=((Person)session.getAttribute("person")).getName() %></h2>
	<h2>Last_Name : <%=((Person)session.getAttribute("person")).getLastName() %></h2>
	<h2>Email :<%=((Person)session.getAttribute("person")).getEmail() %></h2>
	<h2>User : <%=((Person)session.getAttribute("person")).getUsername() %></h2>

    </jsp:body>
</t:base>
