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




import org.json.JSONException;
import org.json.JSONObject;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Servlet implementation class GetPrice
 */
@WebServlet("/GetPrice")
public class GetPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetPrice() {
        super();
       
    }

    public JSONObject jsonReturnCurrentPrice (double price){
    	JSONObject json = new JSONObject();
    	try {
			json.put("price", price);
			json.put("message", "Buying on current price");
			System.out.println(json);
			//out.println(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //json.put("txt22", txt2);
	    
    	
		return json;
    	
    }
    
    
    public JSONObject jsonReturnTwelvePrice (double price){
    	JSONObject json = new JSONObject();
    	try {
			json.put("price", price);
			json.put("message", "Buying on Twelveth price");
			System.out.println(json);
			//out.println(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //json.put("txt22", txt2);
	    
    	
		return json;
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String indexNames[] = {"Nifty50" , "Dow30" , "Straits_Times_Index"};
		PrintWriter out = response.getWriter();
		System.out.println("Inside Get price from yahoo");
		String stockName = request.getParameter("name");
		String indexName = request.getParameter("indexName");
		System.out.println(indexName);
		Connection connection = null;
		ServletContext context = getServletContext();
		connection = (Connection) context.getAttribute("globalConnection");
		String stockAlreadyPurchased = null;
		JSONObject json = new JSONObject();
		
		PreparedStatement checkIfStockAlreadyPurchasedSelect = null;
		ResultSet checkIfStockAlreadyPurchasedResult = null;
		
		try {
			checkIfStockAlreadyPurchasedSelect = connection.prepareStatement("select stockname from addtocart where stockname = ?");
			checkIfStockAlreadyPurchasedSelect.setString(1, stockName);
			checkIfStockAlreadyPurchasedResult = checkIfStockAlreadyPurchasedSelect.executeQuery();
			if(checkIfStockAlreadyPurchasedResult.next())
				stockAlreadyPurchased = "true";
			else
				stockAlreadyPurchased = "false";
				System.out.println(stockAlreadyPurchased);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	/* this code is working don't delete
	 * try {
				json.put("txt11", n);
				json.put("txt21", "achal");
				System.out.println(json);
				//out.println(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //json.put("txt22", txt2);
		    response.setContentType("application/json");
		    response.getWriter().write(json.toString());
	 */
		
		if(indexName.equals("Nifty50")){
			//if stock name is nifty50
			if(stockAlreadyPurchased == "true"){
				//get current price from nifty function
				System.out.println("coming here");
				aa_stockmarkets_api aa =  new aa_stockmarkets_api();
				Double y = aa.Indian(stockName);
				// out.print(y);
				
				json = jsonReturnCurrentPrice(y);
				 response.setContentType("application/json");
				 response.getWriter().write(json.toString());
				
			}
			else {
				//get 9th sept price from nifty function
				SeptemberPrices bb= new SeptemberPrices();
		        Double z = bb.IndianCurrency(stockName);
		        
		        json = jsonReturnTwelvePrice(z);
				 response.setContentType("application/json");
				 response.getWriter().write(json.toString());
		        //out.print(z);
			}
			
			
		}
		
		else if (indexName.equals("Dow30") ) {
			//if stock name is dow30
			if(stockAlreadyPurchased == "true"){
				//get current price from dow30 function
				Stock stock = YahooFinance.get(stockName);
				BigDecimal price = stock.getQuote(true).getPrice();
				double dPrice = price.doubleValue();
				
				json = jsonReturnCurrentPrice(dPrice);
				 response.setContentType("application/json");
				 response.getWriter().write(json.toString());
			//out.print(price);
				
				
			}
			else {
				//get 9th sept price from dow30 function
				SeptemberYahoo cc = new SeptemberYahoo();
		        Double z2 = cc.USASeptember(stockName);
		        json = jsonReturnTwelvePrice(z2);
				 response.setContentType("application/json");
				 response.getWriter().write(json.toString());
		       // out.print(z2);
			}
			
		}
		
		else if (indexName.equals("Straits_Times_Index") ) {
			//if stock name is sti
			if(stockAlreadyPurchased == "true"){
				//get current price from sti function
				aa_stockmarkets_api aa =  new aa_stockmarkets_api();
		        Double x= aa.Singapore(stockName);
		        json = jsonReturnCurrentPrice(x);
				 response.setContentType("application/json");
				 response.getWriter().write(json.toString());
		       // out.print(x);
				
			}
			else {
				//get 9th sept price from sti-0 function
				SeptemberPrices bb= new SeptemberPrices();
				Double z1 = bb.SingaporeCurrency(stockName);
				
				json = jsonReturnTwelvePrice(z1);
				 response.setContentType("application/json");
				 response.getWriter().write(json.toString());
				//out.print(z1);
			}
			
		}
		
		else{
			System.out.println("coming to final else");
			out.println("Invalid stock name");
		}
		
		/*
		 * aa_stockmarkets_api aa =  new aa_stockmarkets_api();
        Double x= aa.Singapore("S58");
        Double y = aa.Indian("COLPAL");
        System.out.println(x);
        System.out.println(y);
        
        SeptemberPrices bb= new SeptemberPrices();
        Double z = bb.IndianCurrency("ASHOKLEY");
        System.out.println(z);
        Double z1 = bb.SingaporeCurrency("S58");
        System.out.println(z1);
        SeptemberYahoo cc = new SeptemberYahoo();
        Double z2 = cc.USASeptember("AXP");
        System.out.println(z2);
		 */
		
		
	}

	

}
