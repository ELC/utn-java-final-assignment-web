
<% if (session.getAttribute("message")!= null && !((String)session.getAttribute("message")).isEmpty()) { %>
	<div class="alert alert-danger alert-dismissible" role="alert" style="width: 1170px; margin-left: auto; margin-right: auto;">
	    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
	    </button>
	    <strong>Error</strong> ${message}
	</div>
<% session.removeAttribute("message");
}%>

<% if (session.getAttribute("messageSuccess")!= null && !((String)session.getAttribute("messageSuccess")).isEmpty()) { %>
	<div class="alert alert-success alert-dismissible" role="alert" style="width: 1170px; margin-left: auto; margin-right: auto;">
	    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
	    </button>
	    <strong>Error</strong> ${messageSuccess}
	</div>
<% session.removeAttribute("messageSuccess");
} %>

<% if (session.getAttribute("messageInfo")!= null && !((String)session.getAttribute("messageInfo")).isEmpty()) { %>
	<div class="alert alert-info alert-dismissible" role="alert" style="width: 1170px; margin-left: auto; margin-right: auto;">
	    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
	    </button>
	    <strong>Error</strong> ${messageInfo}
	</div>
<% 
session.removeAttribute("messageInfo");
} %>