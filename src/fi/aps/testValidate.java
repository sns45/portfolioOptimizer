package fi.aps;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/testValidate")
public class testValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public testValidate() {
        super();
     
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		


		
		Connection connection = null;
		JSONObject json = new JSONObject();
		ServletContext context = getServletContext();
		connection = (Connection) context.getAttribute("globalConnection");
		int count = 0;
		ResultSet result2 = null;
		PreparedStatement stSelect2 = null;
	
		
		ResultSet result6 = null;
		PreparedStatement stSelect6 = null;
		
		ResultSet result7 = null;
		PreparedStatement stSelect7 = null;
		
		
		ResultSet result8 = null;
		PreparedStatement stSelect8 = null;
		
		ResultSet result9 = null;
		PreparedStatement stSelect9 = null;
		
		//////////////////////////////////////////////////////////////////////////////////
		
		try {
			stSelect2 = connection.prepareStatement("select count(distinct stockname) from transactions ");
			result2 = stSelect2.executeQuery();
			
			stSelect6 = connection.prepareStatement("select balance from person where uname = 'aps'");
			result6 = stSelect6.executeQuery();

			stSelect7 = connection.prepareStatement("select sum(totalprice) from transactions");
			result7 = stSelect7.executeQuery();
			
			stSelect8 = connection.prepareStatement("select sum(totalprice) from transactions where stockindex='Dow30' group by stockindex");
			result8 = stSelect8.executeQuery();
			
			stSelect9 = connection.prepareStatement("select sum(totalprice) from transactions where stockindex not in ('Dow30')");
			result9 = stSelect9.executeQuery();
			
			////////////////////////////////////////////////////////////////////////////////
			while(result2.next())
			{
				//checking minimum and maximum number of stocks
				
				System.out.println(result2.getString(1));
				
				if(Integer.parseInt(result2.getString(1)) < 7) 
				{
					json.put("a", "Minimum 7 stocks should be there in Portfolio. Buy more stocks");
				} 
				 
				else if(Integer.parseInt(result2.getString(1)) > 10)
				{
					json.put("b", "Maximum 10 Stocks should be there in portfolio. Sell more stocks");
				}
				else
				{
					System.out.println("1st validation successful");
					count++;
				}
			
			//	result2.close();
			}
			result2.close();
			
				while(result6.next() && result7.next())
				{
					double a = Double.parseDouble(result6.getString(1));
					double b = Double.parseDouble(result7.getString(1));
					
					if(a > (a+b)/10)
					{	
					 json.put("c", "Cash more than 10%. Buy more Stocks");
					}
					else
					{
						System.out.println("2nd validation successful");
						count++;
					}
					
				}
			result6.close();
			
				
				while(result8.next())
				{	
			
					double a = Double.parseDouble(result7.getString(1));
					double b = Double.parseDouble(result8.getString(1));
					
					if(a*0.7 > b)
					{ 
					json.put("d", "DOW-30 Stocks more than 70%");
					}
					else if(a*0.7 < b)
					{
					json.put("e", "DOW-30 stocks less than 70%");
					}
					else
					{
						System.out.println("3rd Validation sucessful");
						count++;
					}
					
				}
			
				result8.close();
				
				if(result9.next()){
				while(result9.next())
				{
					double a= Double.parseDouble(result7.getString(1));
					double b= Double.parseDouble(result9.getString(1));
					
					if(	a*0.3 >	b)
					{ 
						json.put("f", "Foreign stocks more than 30%.");
					}
					else if (a*0.3 < b)
					{
						json.put("g", "Foreign stocks more than 30%.");
					}
					
				}
				result7.close();
				result9.close();
				}
				else{
					json.put("h", "No foreign Stocks");
				}
				
				
			
				response.setContentType("application/json");
				 response.getWriter().write(json.toString());
			
			
			
			System.out.println(json);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {}

}
