package controllers;

import play.libs.ws.*;
import play.api.http.EnabledFilters;
import java.net.HttpURLConnection;
import java.net.URL;
import play.filters.cors.CORSFilter;
import play.filters.csrf.CSRFFilter;
import play.http.DefaultHttpFilters;
import play.mvc.*;
import play.mvc.Http;
import play.mvc.Result;
import java.util.*;
import java.util.Scanner;
import play.libs.Json;
import org.jsoup.Jsoup;
import com.fasterxml.jackson.databind.*;
import java.io.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
		private String error;
		private String variavelDeAmbiente = System.getenv("GH_TOKEN");
		private String contributors;


	    public Result index(String orgName) {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	try{
	    		
                String json = Jsoup.connect("https://api.github.com/users/"+orgName+"/repos")
                .header("Authorization", variavelDeAmbiente)
                .ignoreContentType(true).get().body().text();

                JsonNode nodeJson = objectMapper.readValue(json, JsonNode.class);
                System.out.println(nodeJson.get(0).get("contributors_url"));

                String js = Jsoup.connect((nodeJson.get(0).get("contributors_url")+"").replaceAll("^\"|\"$", ""))
                .header("Authorization", variavelDeAmbiente)
                .ignoreContentType(true).get().body().text();


	    	  
	    	 return ok(js);
	    	} catch(Exception e){
	    		error = e+"";
	    	}

	    	return ok(error);
	      
	    }

}
