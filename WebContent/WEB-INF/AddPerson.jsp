<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>

<form name="AddPerson" action="${pageContext.request.contextPath}/Add/Person" method="post">
               
                <input name="Dni" id="inputDni" class="form-control" placeholder="Dni" required type="Dni" style="margin: 15px 0px 15px 0px; height:2em">
                <input name="Name_Person" id="Name_Person" class="form-control" placeholder="Name_Person" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
                <input name="Last_name_Person" id="inputLast_name_Person" class="form-control" placeholder="Last_Name" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
                <input name="User_Person" id="inputUser" class="form-control" placeholder="user" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
                <input name="Password" id="inputPassWord" class="form-control" placeholder="pass" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
                <input name="Email" id="inputEmail" class="form-control" placeholder="Email" required autofocus style="margin: 15px 0px 15px 0px; height:2em">
                <button class="btn btn-lg btn-primary btn-block" type="submit">addPerson</button>
                
            </form>

    </jsp:body>
</t:base>