<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<h1>Update TypeBookable</h1>
		<form name="UpdateTypeBookable" action="${pageContext.request.contextPath}/TypeBookable/Update" method="post">
	        <input name="Name" id="inputName" class="form-control" placeholder="Name" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
	        <input name="DaysLimit" id="inputDaysLimits" class="form-control" placeholder="New Days_Limit" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
	        <input name="HoursLimit" id="inputHoursLimits" class="form-control" placeholder="New Hours_Limit : HH:mm:ss" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Update TypeBookable</button>
	    </form>
    </jsp:body>
</t:base>