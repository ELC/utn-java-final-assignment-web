<%@page import="entities.TypeBookable"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
	
	<h2> Add Bookable :</h2>
	<form name="AddBookable" action="${pageContext.request.contextPath}/Add/Bookable" method="post">
               
                <input name="NameBookable" id="inputNameBookable" class="form-control" placeholder="Name_Bookable" required type="Name" style="margin: 15px 0px 15px 0px; height:2em">
       	<select>		
      <% 
       	ArrayList<TypeBookable> listTypeBookable= (ArrayList<TypeBookable>)request.getAttribute("ListTypeBookables");
    	for (TypeBookable t : listTypeBookable) {
		
			%>
		
	 <option value=<%t.toString(); %>></option>
	 
	
		
			<% 
    	}

		%>	
		 </select>	
       				<button class="btn btn-lg btn-primary btn-block" type="submit">addBookable</button>
       				</form>
	


    </jsp:body>
</t:base>