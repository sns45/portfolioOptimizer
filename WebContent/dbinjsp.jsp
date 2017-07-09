<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Connection connection = null;
ResultSet result = null;
PreparedStatement stSelect = null; 
try 
	{
	ServletContext context = getServletContext();
	connection = (Connection)context.getAttribute("globalConnection");
	stSelect = connection.prepareStatement("select * from addtocart ");
	result = stSelect.executeQuery();
	/* System.out.println(result);
	while(result.next()){
		System.out.println(result.getString(1));
		
	}  */
		
	}

	catch(NullPointerException e)
	{
		System.out.println("Database Connection Not Established");
	}

%>


      <table>
      <th>Stock Index</th>
        <th>Stock Name</th>
        <th>Stock Price</th>
        <th>Name</th>
        <th>Roll no</th>
       
        

<%
    while(result.next()){
        %>
        <tr>
            <td><%=result.getString(1) %></td>
            <td><%=result.getString(2) %></td>
            <td><%=result.getString(3) %></td>
            <td><%=result.getString(4) %></td>
            <td><%=result.getString(5) %></td>
            

        </tr>
        <%
    }
    result.close();
    %>
    </table>
</body>
</html>