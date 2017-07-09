package fi.aps;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * DB GLOBAL CONNECTION
 */
@WebServlet(description = "Db Global Connection", urlPatterns = { "/GlobalConnection" })
public class GlobalConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	Connection connection = null;
	ResultSet result = null;
	@Override
	public void init(ServletConfig config)
	{
		
		try 
		{
			super.init(config);
			String driver = config.getInitParameter("driverClass");
			String dbUrl = config.getInitParameter("dbUrl");
			String user = config.getInitParameter("userName");
			String password = config.getInitParameter("password");
			System.out.println(driver+dbUrl+user+password);
			
			Class.forName(driver);
			connection = DriverManager.getConnection(dbUrl, user, password);
			if(connection!= null){
				System.out.println("success");
			}
			if(connection==null)
			{
				System.out.println("Connection Failed");
				return;
			}
			ServletContext context = getServletContext();
			context.setAttribute("globalConnection", connection);
		} 
		
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
	}
    public GlobalConnection() {
        super();
        
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
