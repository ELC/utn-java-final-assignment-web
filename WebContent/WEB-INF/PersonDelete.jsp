<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<form name="DeletePerson" action="${pageContext.request.contextPath}/Person/Delete" method="post">
		    <input name="Dni" id="inputDni" class="form-control" placeholder="Dni" required type="Dni" style="margin: 15px 0px 15px 0px; height:2em">
		    <button class="btn btn-lg btn-primary btn-block" type="submit">DeletePerson</button>
		</form>
    </jsp:body>
</t:base>
