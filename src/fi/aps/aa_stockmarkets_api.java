/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.aps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achal
 */
public class aa_stockmarkets_api {

    /**
     * @param SYMBOL
     * @param args the command line arguments
     */
    
    
    
public double Indian(String SYMBOL){
    
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
            
            
            
            

            
            
            URL BaseURL =  new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22" + SYMBOL + ".BO%22%20and%20startDate%20%3D%20%222016-10-04%22%20and%20endDate%20%3D%20%22" + Current_Date + "%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
            
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

    
    
    public double Singapore(String SYMBOL){
        
        
        
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
            
            
            
            
            URL BaseURL =  new URL("http://finance.yahoo.com/d/quotes.csv?s=" + SYMBOL + ".SI&f=json");
            URLConnection APIConnection = BaseURL.openConnection();
            
            
            InputStreamReader is = new InputStreamReader(APIConnection.getInputStream());
            BufferedReader br = new BufferedReader(is);
            
            String Value= br.readLine();
            
            
            String[] part=Value.split("I\",");
            
            
            String[] part1 = part[1].split(",\"");
//            System.out.println(part1[0]);
            
            double FinalValue_Singapore = Double.parseDouble(part1[0]);
            
            double FinalValueUSD;
            FinalValueUSD = FinalConversion * FinalValue_Singapore;
//            System.out.println(FinalValueUSD);
            
            return FinalValueUSD;
            
            
            
        }catch (IOException ex) {
            System.out.println("Error check");
            return 0.00;
        }
    } 
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        aa_stockmarkets_api aa =  new aa_stockmarkets_api();
        Double x= aa.Singapore("S58");
        Double y = aa.Indian("ASHOKLEY");
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
    }
    
}
