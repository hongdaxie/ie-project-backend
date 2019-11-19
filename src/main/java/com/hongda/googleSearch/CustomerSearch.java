package com.hongda.googleSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CustomerSearch {
    private static final String GOOGLE_API_KEY = "AIzaSyDpEvkpDPKBaQBuPMsou1gzbOtc77pIBUE";
    private static final String GOOGLE_SEARCH_ID_cx = "010857273807497535145:avarjzi-g-i";

    public static String googleSearch(String keyword,String[] params, String[] values){
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter = "";

        if (params!=null && values!=null){
            for (int i=0; i<params.length;i++){
                query_parameter += "&";
                query_parameter += params[i];
                query_parameter += "=";
                query_parameter += values[i];
            }
        }
        try{
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+ GOOGLE_API_KEY+ "&cx="+ GOOGLE_SEARCH_ID_cx + "&q="+ keyword + query_parameter);
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            connection.disconnect();
        }
        return textResult;
    }

    public static String getImageSrc(String result){
        String imageSrc = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if (jsonArray != null && jsonArray.length()>0){
                imageSrc = jsonArray.getJSONObject(0).getString("link");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imageSrc;
    }
}
