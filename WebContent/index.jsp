<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="ValidateLogin">
		
				EmailID:
				<input type="text" name="email" id="uid"/></br>
			
				Password:
				<input type="password" name="pass"  id ="pwd"/></br>
			
				<input type="submit" value="login" />
			
	</form>
	
<%-- <%
    if(null!=request.getAttribute("errormsg"))
    {
        out.println(request.getAttribute("errormsg"));
    }
%>	 --%>

<c:if test="${not empty errormsg}">
   <c:out value="${errormsg}"/>
</c:if>



</body>
</html>