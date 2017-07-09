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
public class SeptemberYahoo {
    
    public double USASeptember(String SYMBOL){
        
        try {
            
            
        URL BaseURL =  new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22" + SYMBOL + "%22%20and%20startDate%20%3D%20%222016-09-10%22%20and%20endDate%20%3D%20%222016-09-12%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
            
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
            Double FinalValue_USA = Double.parseDouble(FinalValue);
            
            return FinalValue_USA;
            
//            String[] nextPart1 = nextPart[0].split("981.05");
//            System.out.println(nextPart1[1]);
            
            
//            double FinalValue= Double.parseDouble(part1[0]);
//            System.out.println(FinalValue); 
        } catch (IOException ex) {
            
            return 0.00;
        }
        
    }
    
    
}
