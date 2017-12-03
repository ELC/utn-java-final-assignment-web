<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
    	<h1>Delete TypeBookable</h1>
		<form name="DeleteTypeBookable" action="${pageContext.request.contextPath}/TypeBookable/Delete" method="post">
        	<input name="Id" id="inputName" class="form-control" placeholder="Id Type Bookable" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
        	<button class="btn btn-lg btn-primary btn-block" type="submit">Delete TypeBookable</button>
       	</form>
    </jsp:body>
</t:base>