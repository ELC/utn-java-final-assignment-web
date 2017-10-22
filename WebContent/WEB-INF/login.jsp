<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
    	<div class="container">
			<form class="form-signin" name="signin" action="login" method="post">
				<h2 class="form-signin-heading">Login</h2>				
				<input name="user" id="inputUser" class="form-control" placeholder="User" required autofocus style="margin: 15px 0px 15px 0px;">				
				<input name="pass" id="inputPass" class="form-control" placeholder="Password" required type="Password" style="margin: 15px 0px 15px 0px;">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
			</form>
		</div>
    </jsp:body>
</t:base>
