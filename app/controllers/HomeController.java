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
import java.util.stream.Collectors;


public class HomeController extends Controller {

		private String error;

		// A variável de ambiente foi configurada no sistema linux Ubuntu.
		private String variavelDeAmbiente = System.getenv("GH_TOKEN");
		
	    public Result index(String orgName) {

	    	// mapValue armazena o(s) login(s) e o valor da(s) contribution(s) da organização
			Map<String, Integer> mapValue = new TreeMap<String, Integer>();

	    	ObjectMapper objectJson = new ObjectMapper();

	    	try{
	    		
	    		Integer contributions;
	    		String login; 

	    		// Pegar os dados dos respositórios da organização 
                String json = Jsoup.connect("https://api.github.com/users/"+orgName+"/repos")
                .header("Authorization", "Bearer "+variavelDeAmbiente)
                .ignoreContentType(true).get().body().text();

                // Transformar a resposta em JSON
                JsonNode nodeJson1 = objectJson.readValue(json, JsonNode.class);

                // Acessar as contributors_url e adicionar no mapValue o login (key) e contribution (value)
              	for(int i = 0; i < nodeJson1.size(); i++){

              		String js = Jsoup.connect((nodeJson1.get(i).get("contributors_url")+"").replaceAll("^\"|\"$", ""))
                	.header("Authorization", "Bearer "+variavelDeAmbiente)
                	.ignoreContentType(true).get().body().text();
                	
                	JsonNode nodeJson2 = objectJson.readValue(js, JsonNode.class);

                	login = (nodeJson2.get(0).get("login")+"").replaceAll("^\"|\"$", "");
                	contributions = Integer.parseInt(nodeJson2.get(0).get("contributions")+"");
                	

                	if(mapValue.get(login) != null){
                		// Incrementar em cima do valor existente no login repetido
                		mapValue.put(login, mapValue.get(login)+contributions);
                	} else {
                		mapValue.put(login, contributions); 
                	}
                	

                	
              	}
              	
              	// Ordenar o map de forma descendente por valor
              	final Map<String, Integer> mapSort = mapValue.entrySet()
        		.stream()
        		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

	    	 // retorna o map no formato JSON 
	    	 return ok(Json.toJson(mapSort));

	    	} catch(Exception e){
	    		error = e+"";
	    	}

	    	return ok(error);
	      
	    }

}
