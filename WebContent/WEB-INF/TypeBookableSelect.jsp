<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<form name="SelectTypeBookable" action="${pageContext.request.contextPath}/TypeBookable/Select" method="post">
	        <input name="name" id="inputNameTypeBookable" class="form-control" placeholder="Name Type Bookable" required type="TypeBookable" style="margin: 15px 0px 15px 0px; height:2em">
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Select TypeBookable</button>
	    </form>
    </jsp:body>
</t:base>