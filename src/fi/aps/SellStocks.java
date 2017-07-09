package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


@WebServlet("/SellStocks")
public class SellStocks extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SellStocks() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String getStockName = request.getParameter("stockName");
		String indexName = request.getParameter("indexName");
		System.out.println("the index name is  "+ indexName);
		System.out.println("the stock name is  "+ getStockName);
		
		int getNoOfStocks = Integer.parseInt(request.getParameter("noOfStock"));
		System.out.println("the no of stock is  "+ getNoOfStocks);
		
		String returnedPrice = null;
		int noOfStocksInDb = 0;
		double totalStockPriceDb = 0.0;
		double calculateMoneyToDeductFromTransactionTb = 0.0;
		double priceOfOneShareBasedOnDb = 0.0;
		PrintWriter out = response.getWriter();
		System.out.println("inside sell stocks");
		
		Connection connection = null;
		
		ResultSet sellStock = null;
		ResultSet getTotalPriceofStocksResult = null;
		ResultSet updateUserBalanceResult = null;
		ResultSet getNoOfStocksResult = null;

		PreparedStatement stSelect = null;
		PreparedStatement getStockNameToSell = null;
		PreparedStatement updateUserBalanceStmt = null;
		PreparedStatement getNoOfStocksSelect = null; 
		PreparedStatement getTotalPriceOfStockSelect = null;
		PreparedStatement updateTotalPriceInTable = null;
		
		
		
		double moneyToAdd = 0.0;
		float currentPrice; 
		
		ServletContext context = getServletContext();
		connection = (Connection)context.getAttribute("globalConnection");
		try {
			
			getNoOfStocksSelect = connection.prepareStatement("select noofstocks, totalprice from transactions where stockname = ? ");
			getNoOfStocksSelect.setString(1, getStockName);
			getNoOfStocksResult = getNoOfStocksSelect.executeQuery();
			while(getNoOfStocksResult.next()){
				noOfStocksInDb =	getNoOfStocksResult.getInt(1);
				totalStockPriceDb =    getNoOfStocksResult.getDouble(2);
			}
			
			priceOfOneShareBasedOnDb = totalStockPriceDb/noOfStocksInDb;
			calculateMoneyToDeductFromTransactionTb = priceOfOneShareBasedOnDb*getNoOfStocks;
			
			System.out.println("money to deduct from db = " + calculateMoneyToDeductFromTransactionTb);
			
			
			updateTotalPriceInTable = connection.prepareStatement("update transactions set totalprice = totalprice - ? where stockname = ? ");
			updateTotalPriceInTable.setDouble(1, calculateMoneyToDeductFromTransactionTb);
			updateTotalPriceInTable.setString(2, getStockName);
			
			
			if(indexName.equals("Nifty50")){
				//if stock name is nifty50
				
					//get current price from nifty function
					aa_stockmarkets_api aa =  new aa_stockmarkets_api();
					Double y = aa.Indian(getStockName);
					if(y==0.0){
						returnedPrice = "zero" ;
					}
					moneyToAdd = y * getNoOfStocks;
					// out.print(y);
					
				
				
				
			}
			
			else if (indexName.equals("Dow30") ) {
				//if stock name is dow30
				
					//get current price from dow30 function
					Stock stock = YahooFinance.get(getStockName);
					BigDecimal price = stock.getQuote(true).getPrice();
					currentPrice= price.floatValue();
					if(currentPrice==0.0){
						returnedPrice = "zero" ;
					}
					moneyToAdd = currentPrice * getNoOfStocks;
				//out.print(price);
					
					
				
				
				
			}
			
			else if (indexName.equals("Straits_Times_Index") ) {
				//if stock name is sti
				
					//get current price from sti function
					aa_stockmarkets_api aa =  new aa_stockmarkets_api();
			        Double x= aa.Singapore(getStockName);
			        if(x==0.0){
						returnedPrice = "zero" ;
					}
			        moneyToAdd = x * getNoOfStocks;
			       // out.print(x);
							
			}
			
			else{
				System.out.println("coming to final else");
				out.println("Invalid stock name");
			}
			
			
			System.out.println("money to add is "+ moneyToAdd);
			
			
			
			updateUserBalanceStmt = connection.prepareStatement("update person set balance =balance + ? ");
			updateUserBalanceStmt.setDouble(1, moneyToAdd);
			
			updateUserBalanceResult = updateUserBalanceStmt.executeQuery();
			connection.commit();
		
			
			
			//////////////////////
			
				stSelect = connection.prepareStatement("update transactions set noofstocks = noofstocks - ? where stockname = ?");
				
				stSelect.setInt(1, getNoOfStocks);
				stSelect.setString(2, getStockName);
				sellStock = stSelect.executeQuery();
				updateTotalPriceInTable.executeQuery();
				connection.commit();
				
				
				if(sellStock.next()){
					connection.commit();
				}
			
				
				getStockNameToSell = connection.prepareStatement("delete from transactions where noofstocks=0");
				getStockNameToSell.executeQuery();
				connection.commit();
				
		
			out.print("true");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
