/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.aps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Achal
 */
public class SeptemberPrices {
    
    
    public double SingaporeCurrency(String SYMBOL){
        
        try {
            
            
            
            URL BaseURL10 = new URL("http://api.fixer.io/latest?base=SGD&symbols=USD");
            URLConnection APIConnection10 = BaseURL10.openConnection();
            
            
            InputStreamReader is10 = new InputStreamReader(APIConnection10.getInputStream());
            BufferedReader br10 = new BufferedReader(is10);
            
            String Value10= br10.readLine();
            
            String[] part10=Value10.split("\"USD\":");
            String Value20= part10[1];
            String[] part11=Value20.split("}");
            
            double FinalConversion= Double.parseDouble(part11[0]);
//            System.out.println(FinalConversion);
            
            
            
            
            URL BaseURL =  new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22" + SYMBOL + ".SI%22%20and%20startDate%20%3D%20%222016-09-10%22%20and%20endDate%20%3D%20%222016-09-12%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
            URLConnection APIConnection = BaseURL.openConnection();
            InputStreamReader is = new InputStreamReader(APIConnection.getInputStream());
            BufferedReader br = new BufferedReader(is);
            
            String Value= br.readLine();
            
            
            String[] part=Value.split("\"Close\":");
            
            String Value1 = part[1].substring(1);
            
            String Value2= Value1.substring(0,4);
            
            
 
            double FinalValue_Singapore = Double.parseDouble(Value2);
            
            double FinalValueUSD;
            
            FinalValueUSD = FinalConversion * FinalValue_Singapore;
           
            
            return FinalValueUSD;
            
            
            
        }catch (IOException ex) {
            System.out.println("Error check");
            return 0.00;
        }
        
    }
    
    public double IndianCurrency(String SYMBOL){
        
        try {
            
            
        URL BaseURL10 = new URL("http://api.fixer.io/latest?base=INR&symbols=USD");
            URLConnection APIConnection10 = BaseURL10.openConnection();
            
            
            InputStreamReader is10 = new InputStreamReader(APIConnection10.getInputStream());
            BufferedReader br10 = new BufferedReader(is10);
            
            String Value10= br10.readLine();
            
            String[] part10=Value10.split("\"USD\":");
            String Value20= part10[1];
            String[] part11=Value20.split("}");
            
            double FinalConversion= Double.parseDouble(part11[0]);
        
        
        
        URL BaseURL1 =  new URL("http://www.timeapi.org/utc/now");
            
            URLConnection APIConnection1 = BaseURL1.openConnection();
            InputStreamReader is1 = new InputStreamReader(APIConnection1.getInputStream());
            BufferedReader br1 = new BufferedReader(is1);
            
            String Value1= br1.readLine();
            String[] part1=Value1.split("T");
            String Current_Date=part1[0];
            
            
            
            

            
            
            URL BaseURL =  new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22" + SYMBOL + ".BO%22%20and%20startDate%20%3D%20%222016-09-10%22%20and%20endDate%20%3D%20%222016-09-12%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
            
            URLConnection APIConnection = BaseURL.openConnection();
            
            
            InputStreamReader is = new InputStreamReader(APIConnection.getInputStream());
            BufferedReader br = new BufferedReader(is);
            
            String Value= br.readLine();
            String[] part=Value.split("\"Close\":");
//            System.out.println(part[1]);
            
            String[] nextPart = part[1].split("\",\"Volume");
//            System.out.println(nextPart[0]);
            String FinalValue= nextPart[0].substring(1);
//            System.out.println(FinalValue);
            Double FinalValue_India = Double.parseDouble(FinalValue);
            double FinalValueUSD;
            
            FinalValueUSD= FinalValue_India * FinalConversion;
            return FinalValueUSD;
            
//            String[] nextPart1 = nextPart[0].split("981.05");
//            System.out.println(nextPart1[1]);
            
            
//            double FinalValue= Double.parseDouble(part1[0]);
//            System.out.println(FinalValue); 
        } catch (IOException ex) {
            
            return 0.00;
        }
        
    }
    
    
}
