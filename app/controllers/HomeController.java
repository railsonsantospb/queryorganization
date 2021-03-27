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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
		private String error;
		private String variavelDeAmbiente = System.getenv("GH_TOKEN");


	    public Result index(String orgName) {
	    	try{
	    		
                String json = Jsoup.connect("https://api.github.com/users/"+orgName+"/repos").ignoreContentType(true).execute().body();
	    	  
	    	 return ok(json);
	    	} catch(Exception e){
	    		error = e+"";
	    	}

	    	return ok(error);
	      
	    }

}
