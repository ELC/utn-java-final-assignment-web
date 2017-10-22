<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
    	<div class="container">
			<form class="form-signin" name="signin" action="login" method="post">
				<h2 class="form-signin-heading">Login</h2>
				<label for="inputUser" class="sr-only">User</label>
				<input name="user" id="inputUser" class="form-control" placeholder="User" required="" autofocus="" type="">
				<label for="inputPass" class="sr-only">Password</label>
				<input name="pass" id="inputPass" class="form-control" placeholder="Password" required="" type="Password">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
			</form>
		</div>
    </jsp:body>
</t:base>
