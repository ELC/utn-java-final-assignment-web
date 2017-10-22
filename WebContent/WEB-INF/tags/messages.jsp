
<% if (session.getAttribute("message")!= null && !((String)session.getAttribute("message")).isEmpty()) { %>
	<div class="alert alert-danger alert-dismissible" role="alert" style="width: 1170px; margin-left: auto; margin-right: auto;">
	    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
	    </button>
	    <strong>Error</strong> ${message}
	</div>
<% } 
session.removeAttribute("message");
%>