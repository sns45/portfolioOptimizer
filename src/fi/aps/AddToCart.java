package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String stockIndex = request.getParameter("stockIndex");
		String stockName = request.getParameter("stockName");
		String stockPrice = request.getParameter("stockPrice");
		String noOfStocks = request.getParameter("noOfStocks");
		System.out.println("inside add to cart");

		Float priceOfStock = Float.parseFloat(stockPrice);
		Float intNoOfStocks = Float.parseFloat(noOfStocks);
		Float totalPrice = priceOfStock * intNoOfStocks;
		Date dateobj = new Date();
		String purchaseDate = dateobj.toString();
		System.out.println(stockIndex + stockName + stockPrice + noOfStocks+ dateobj);

		Connection connection = null;
		ResultSet result = null;
		ResultSet getCashBalanceResult = null;
		ResultSet getIfStockAlreadyExistsResult = null;

		PreparedStatement stSelect = null;
		PreparedStatement stSelect2 = null;
		PreparedStatement getcashBalanceStatement = null;
		PreparedStatement insertToTransactionSelect = null;
		PreparedStatement getIfStockAlreadyExistsSelect = null;
		PreparedStatement updateTableIfAlreadyExistsSelect = null;

		try {
			ServletContext context = getServletContext();
			connection = (Connection) context.getAttribute("globalConnection");

			//checking is stockname already exists
			getIfStockAlreadyExistsSelect = connection.prepareStatement("select stockname from transactions where stockname = ?");
			getIfStockAlreadyExistsSelect.setString(1, stockName);
			getIfStockAlreadyExistsResult = getIfStockAlreadyExistsSelect.executeQuery();
			
			//updating is stocks already exists
			updateTableIfAlreadyExistsSelect = connection.prepareStatement("update transactions set noofstocks= noofstocks + ? , totalprice= totalprice + ? where stockname = ?");
			updateTableIfAlreadyExistsSelect.setFloat(1, intNoOfStocks);
			updateTableIfAlreadyExistsSelect.setFloat(2, totalPrice);
			updateTableIfAlreadyExistsSelect.setString(3, stockName);
			
			
			//adding values to transaction table on the click of buy button
			insertToTransactionSelect = connection.prepareStatement("insert into transactions values (?,?,?,?)");
			insertToTransactionSelect.setString(1, stockIndex);
			insertToTransactionSelect.setString(2, stockName);
			insertToTransactionSelect.setFloat(3, totalPrice);
			insertToTransactionSelect.setFloat(4, intNoOfStocks);
			
			if(getIfStockAlreadyExistsResult.next()){
				updateTableIfAlreadyExistsSelect.executeQuery();//if stockname already there the update not insert
				
			}
			else{
			insertToTransactionSelect.executeQuery();
			}//executing the add to transaction
			///////////////////////////////////////////////////////////
			
			
			stSelect = connection
					.prepareStatement("INSERT INTO ADDTOCART VALUES (?,?,?,?,?,?)"); //addint to addtocart
			stSelect.setString(1, stockIndex);
			stSelect.setString(2, stockName);
			stSelect.setFloat(3, totalPrice);
			stSelect.setFloat(4, priceOfStock);
			stSelect.setFloat(5, intNoOfStocks);
			stSelect.setString(6, purchaseDate);
			
			stSelect2 = connection
					.prepareStatement("update person set balance = balance - ?");
			stSelect2.setFloat(1, totalPrice);
			
			getcashBalanceStatement = connection
					.prepareStatement("select balance from person");

			getCashBalanceResult = getcashBalanceStatement.executeQuery();

			float cashBalance = 0;

			while (getCashBalanceResult.next())
				cashBalance = getCashBalanceResult.getFloat(1);
			System.out.println(cashBalance);

			if (cashBalance >= totalPrice) {

				result = stSelect.executeQuery();//adding to addtocart
				stSelect2.executeQuery();//updating users balance
				connection.commit();

				if (result.next()) {
					out.print("Your transaction completed successfully");
				}
			}
			else{
				out.print("Your account does not have sufficient money");
			}
			
			
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
