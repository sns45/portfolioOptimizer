package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class DepositCash
 */
@WebServlet("/DepositCash")
public class DepositCash extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositCash() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String amount = request.getParameter("Amount");
		float floatAmount = Float.parseFloat(amount);
		System.out.println("Amount user wants to deposit" +amount);
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement stSelect = null;
		ServletContext context = getServletContext();
		connection = (Connection)context.getAttribute("globalConnection");
		
		
		try {
			stSelect = connection.prepareStatement("update person set balance = balance + ?");
			stSelect.setFloat(1, floatAmount);
			result = stSelect.executeQuery();
			connection.commit();
			if(result.next()){
				PrintWriter out = response.getWriter();
				out.println("Successfully added  " + amount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
