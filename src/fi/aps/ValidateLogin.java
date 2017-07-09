package fi.aps;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class testlogin
 */
@WebServlet("/ValidateLogin")
public class ValidateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ValidateLogin() {
        super();
      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("gfhdfhy");
		
		
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement stSelect = null;
		
		try 
 		{
			ServletContext context = getServletContext();
			connection = (Connection)context.getAttribute("globalConnection");
				String userName = request.getParameter("email");
				String password = request.getParameter("pass");
				
				Cookie objCookie = new Cookie("usercookie",userName);
				objCookie.setMaxAge(120);
				response.addCookie(objCookie);
				
			
				stSelect = connection.prepareStatement("select * from person where uname= ? and password= ?");
				stSelect.setString(1, userName);
				stSelect.setString(2, password);
				result = stSelect.executeQuery();
				
				if(result.next())
				{
					HttpSession session = request.getSession();
					session.setAttribute("userName", userName);
					response.sendRedirect("Profile.jsp");
				}
				else{
					
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("errormsg", "Wrong Username or Password");
					rd.forward(request, response); } 
		} 
 		catch (SQLException e) 
 		{
			e.printStackTrace();
		}
 		catch(NullPointerException e)
 		{
 			System.out.println("Database Connection Not Established");
 		}
		/*finally
		{
			try
			{
				if(result!=null)
					result.close();
				if(connection!=null)
					connection.close();
				if(stSelect!=null)
					stSelect.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}*/
	}

}
