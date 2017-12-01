<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
		<form name="SelectPerson" action="${pageContext.request.contextPath}/Person/Select" method="post">
	        <input name="Dni" id="inputDni" class="form-control" placeholder="Dni" required type="Dni" style="margin: 15px 0px 15px 0px; height:2em">
	        <button class="btn btn-lg btn-primary btn-block" type="submit">SelectPerson</button>
	    </form>
    </jsp:body>
</t:base>
