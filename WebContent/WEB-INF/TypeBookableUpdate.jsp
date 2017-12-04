<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<h1>Update TypeBookable</h1>
		<form name="UpdateTypeBookable" action="${pageContext.request.contextPath}/TypeBookable/Update" method="post">
	        <input name="Id" id="inputName" class="form-control" placeholder="Id Type Bookable" required type="IdType" style="margin: 15px 0px 15px 0px; height:2em">
	        <input name="Name" id="inputName" class="form-control" placeholder="Name" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
	        <input name="DaysLimit" id="inputDaysLimits" class="form-control" placeholder="New Days_Limit" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
	        <input type="number" name="HoursLimit" id="inputHoursLimits" class="form-control" placeholder="Number of hours" required autofocus style="margin: 15px 0px 15px 0px; height:2em" min="0" max="24" step="0.25">
	        <input type="number" name="maxBookings" id="inputMaxBookingLimits" class="form-control" placeholder="Number of MaxBookings" required autofocus style="margin: 15px 0px 15px 0px; height:2em" min="0" max="100" step="1.00">
	        <input type="checkbox" name="Option" value="1"> Restriction<br>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Update TypeBookable</button>
	    </form>
    </jsp:body>
</t:base>