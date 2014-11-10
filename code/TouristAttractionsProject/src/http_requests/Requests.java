package http_requests;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Locale.Category;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Requests {
	
	private final String USER_AGENT = "Mozilla/5.0";
	public static Vector<String> categoriesTitles = new Vector<String>();
	public static Map<String, String> articleCategory = new HashMap<String, String>();
	 
	public static void main(String[] args) throws Exception {
 
		Requests http = new Requests();
 
		//System.out.println("Testing 1 - Send Http GET request");
		System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
		//categoriesTitles = http.obtainAllCategories();
		
		http.sendGet("2530912");
		
		
		//Vector<String> teste = http.obtainSubcategories(categoriesTitles.elementAt(0));
		//Vector<String> teste = http.obtainArticlesFromCategory(categoriesTitles.elementAt(0));
		
		/*
		for (int i = 0; i < categoriesTitles.size(); i++) {
			
			String categoryTitle = categoriesTitles.elementAt(i);
			
			Vector<String> articles = http.obtainArticlesFromCategory(categoryTitle);
			for (int j = 0; j < articles.size(); j++) {
				articleCategory.put(articles.elementAt(j),categoryTitle);
			}
			
			
			
		}
		*/
		
		System.out.println("DONE");
		
		/*
		for (Map.Entry<String, String> entry : articleCategory.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
				+ entry.getValue());
		}
		
		System.out.println("DONE2: " + articleCategory.size());
		*/
		
		/*
		for (int i = 0; i < teste.size(); i++) {
			System.out.println("Article title: " + teste.elementAt(i));
			
		}*/
		
		
	}
	
	
	public Vector<String> obtainArticlesFromCategory(String CategoryTitle) throws Exception
	{
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=10&categories="+CategoryTitle+"&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";
		
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "language=pt&project=wikipedia&depth=0&categories="+CategoryTitle+"&negcats=&comb%5Bsubset%5D=1&atleast_count=0&ns%5B0%5D=1&show_redirects=both&templates_yes=&templates_any=&templates_no=&outlinks_yes=&outlinks_any=&outlinks_no=&edits%5Bbots%5D=both&edits%5Banons%5D=both&edits%5Bflagged%5D=both&before=&after=&max_age=&larger=&smaller=&minlinks=&maxlinks=&min_redlink_count=1&min_topcat_count=1&sortby=none&sortorder=ascending&format=csv&doit=Do+it%21&interface_language=en";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		in.readLine();
		String structureMessage = in.readLine();
		Vector<String> categoriesTitles = new Vector<String>();

		while ((inputLine = in.readLine()) != null) {
			
			String[] result = inputLine.split(",");
			categoriesTitles.add(result[0].replace("\"",""));

			
			
		}
		in.close();
		
		return categoriesTitles;		
	}
	
	public Vector<String> obtainSubcategories(String CategoryTitle) throws Exception
	{
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=10&categories="+CategoryTitle+"&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "language=pt&project=wikipedia&depth=10&categories="+CategoryTitle+"&negcats=&comb%5Bsubset%5D=1&atleast_count=0&ns%5B14%5D=1&show_redirects=both&templates_yes=&templates_any=&templates_no=&outlinks_yes=&outlinks_any=&outlinks_no=&edits%5Bbots%5D=both&edits%5Banons%5D=both&edits%5Bflagged%5D=both&before=&after=&max_age=&larger=&smaller=&minlinks=&maxlinks=&min_redlink_count=1&min_topcat_count=1&sortby=none&sortorder=ascending&format=csv&doit=Do+it%21&interface_language=en";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		in.readLine();
		String structureMessage = in.readLine();
		Vector<String> categoriesTitles = new Vector<String>();

		while ((inputLine = in.readLine()) != null) {
			
			String[] result = inputLine.split(",");
			categoriesTitles.add(result[0].replace("\"",""));

			
			
		}
		in.close();
		
		return categoriesTitles;		
	}
 
	// HTTP GET request
	private void sendGet(String articleID) throws Exception {
 
		JSONParser parser = new JSONParser();
		
		//String url = "http://www.google.com/search?q=mkyong";
		String urlIncomplete = "http://pt.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&pageids=";
		//articleID = "2530912";
		String url = urlIncomplete + articleID;
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//System.out.println(response.toString());
		Object objparse = parser.parse(response.toString());
		
		JSONObject jsonObject = (JSONObject) objparse;
		
		/*
		String extract = (String) jsonObject.get("extract");
		System.out.println(extract);
		*/
		
		//JSONArray msg = (JSONArray) jsonObject.get("query");
		String query = jsonObject.get("query").toString();
		
		
		Object objpages = parser.parse(query);
		
		JSONObject jsonobjpages = (JSONObject) objpages;
		
		String pages = jsonobjpages.get("pages").toString();
		
		
		Object objarticleID = parser.parse(pages);
		
		JSONObject jsonobjarticleID = (JSONObject) objarticleID;
		
		String articleIDjason = jsonobjarticleID.get(articleID).toString();
		
		
		Object objextract = parser.parse(articleIDjason);
		
		JSONObject jsonobjextract = (JSONObject) objextract;
		
		String extract = jsonobjextract.get("extract").toString();
		//System.out.println(extract);
		
		String[] aux = extract.split("</p>");
		
		String firstParagraph = aux[0].replaceAll("\\<[^>]*>", "");
		
		//TODO retirar apenas o extracts
		String cleanExtract = extract.replaceAll("\\<[^>]*>", "");
		System.out.println(cleanExtract);
		
		System.out.println("First paragraph is: " + firstParagraph);
 
	}
 
	// HTTP POST request
	/*
	private void sendPost() throws Exception {
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=10&categories=Atra%C3%A7%C3%B5es+tur%C3%ADsticas+de+Portugal&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		
		
		//con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
 		
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "language=pt&project=wikipedia&depth=10&categories=Atra%C3%A7%C3%B5es+tur%C3%ADsticas+de+Portugal&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&comb%5Bsubset%5D=1&atleast_count=0&ns%5B0%5D=1&show_redirects=both&templates_yes=&templates_any=&templates_no=&outlinks_yes=&outlinks_any=&outlinks_no=&edits%5Bbots%5D=both&edits%5Banons%5D=both&edits%5Bflagged%5D=both&before=&after=&max_age=&larger=&smaller=&minlinks=&maxlinks=&min_redlink_count=1&min_topcat_count=1&sortby=none&sortorder=ascending&format=csv&doit=Do+it%21&interface_language=en";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		in.readLine();
		String structureMessage = in.readLine();
		while ((inputLine = in.readLine()) != null) {
			//response.append(inputLine);
			
			String[] result = inputLine.split(",");
			sendGet(result[1].replace("\"",""));
			
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}*/
	
	private Vector<String> obtainAllCategories() throws Exception {
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=10&categories=Atra%C3%A7%C3%B5es+tur%C3%ADsticas+de+Portugal&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		
		
		//con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
 		
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "language=pt&project=wikipedia&depth=10&categories=Atra%C3%A7%C3%B5es+tur%C3%ADsticas+de+Portugal&negcats=&comb%5Bsubset%5D=1&atleast_count=0&ns%5B14%5D=1&show_redirects=both&templates_yes=&templates_any=&templates_no=&outlinks_yes=&outlinks_any=&outlinks_no=&edits%5Bbots%5D=both&edits%5Banons%5D=both&edits%5Bflagged%5D=both&before=&after=&max_age=&larger=&smaller=&minlinks=&maxlinks=&min_redlink_count=1&min_topcat_count=1&sortby=none&sortorder=ascending&format=csv&doit=Do+it%21&interface_language=en";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        //new InputStreamReader(con.getInputStream(),"UTF-8"));
				new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		in.readLine();
		String structureMessage = in.readLine();
		Vector<String> categoriesTitles = new Vector<String>();

		while ((inputLine = in.readLine()) != null) {
			
			String[] result = inputLine.split(",");
			categoriesTitles.add(result[0].replace("\"",""));

			
			
		}
		in.close();
		
		return categoriesTitles;
 
	}

}
