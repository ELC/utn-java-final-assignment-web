<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<form name="SelectBookable" action="${pageContext.request.contextPath}/Bookable/Select" method="post">
	        <input name="Id" id="inputId" class="form-control" placeholder="Id Bookable" required type="IdBookable" style="margin: 15px 0px 15px 0px; height:2em">
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Select Bookable</button>
	    </form>
    </jsp:body>
</t:base>
