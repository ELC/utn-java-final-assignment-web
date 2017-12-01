<!-- Static navbar -->
<nav class="navbar navbar-default myFooter" style="background-color: #3c3d41; color: #f9f9f9;">
    <div class="container-fluid" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"  data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Booking System</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse" style="border-color: #e7e7e7; color: #f9f9f9; background-color: #3c3d41;">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Person<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/Person/Add">AddPerson</a></li>
                        <li><a href="${pageContext.request.contextPath}/Person/Delete">DeletePerson</a></li>
                        <li><a href="${pageContext.request.contextPath}/Person/Update">UpdatePerson</a></li>
                        <li><a href="${pageContext.request.contextPath}/Person/Select">SelectPerson</a></li>
                        <li><a href="${pageContext.request.contextPath}/Person/Show">Show All</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Type Bookable<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/TypeBookable/Add">AddTypeBookable</a></li>
                        <li><a href="${pageContext.request.contextPath}/TypeBookable/Delete">DeleteTypeBookable</a></li>
                        <li><a href="${pageContext.request.contextPath}/TypeBookable/Update">UpdateTypeBookable</a></li>
                        <li><a href="${pageContext.request.contextPath}/TypeBookable/Show">Show All</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bookable<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/Bookable/Add">AddBookable</a></li>
                          <li><a href="${pageContext.request.contextPath}/Bookable/Delete">DeleteBookable</a></li>
                          <li><a href="${pageContext.request.contextPath}/Bookable/Update">UpdateBookable</a></li>
                        <li><a href="${pageContext.request.contextPath}/Bookable/Show">Show All</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Booking<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/Booking/CRUD">Crud</a></li>
                        <li><a href="${pageContext.request.contextPath}/Booking/Show">Show All</a></li>
                    </ul>
                </li>
            </ul>
            
            <% if (session.getAttribute("user") == null) { %>
				<ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#" data-toggle="modal" data-target="#login-modal">Log In<span class="sr-only">(current)</span></a>
                </li>
            </ul>
			<% } else {%>
				<ul class="nav navbar-nav navbar-right">
	                <li class="active"><a href="${pageContext.request.contextPath}/logout">Log Out<span class="sr-only">(current)</span></a></li>
            	</ul>
			<%}%>
            
            
        </div>
        <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
</nav>

<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="loginmodal-container">
            <h1>Login</h1>
            <br>
            <form name="signin" action="${pageContext.request.contextPath}/login" method="post">
                <input name="user" id="inputUser" class="form-control" placeholder="User" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
                <input name="pass" id="inputPass" class="form-control" placeholder="Pass" required type="Password" style="margin: 15px 0px 15px 0px; height:2em">
                <input type="submit" name="login" class="login loginmodal-submit" value="Login">
            </form>

            <div class="login-help">
                If you don't have an account, contact the administrator
            </div>
        </div>
    </div>
</div>
	    
