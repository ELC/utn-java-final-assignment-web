<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<h1>Delete Bookable</h1>
		<form name="DeleteBookable" action="${pageContext.request.contextPath}/Bookable/Delete" method="post">
			<input name="Id" id="inputName" class="form-control" placeholder="Id Bookable" required type="Id" style="margin: 15px 0px 15px 0px; height:2em">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Delete Bookable</button>
        </form>
    </jsp:body>
</t:base>