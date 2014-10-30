package http_requests;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;

public class Requests {
	
	private final String USER_AGENT = "Mozilla/5.0";
	 
	public static void main(String[] args) throws Exception {
 
		Requests http = new Requests();
 
		System.out.println("Testing 1 - Send Http GET request");
		
		//TODO obter lista de todos os article titles com csv
		
		
		//TODO criar um ciclo e processar todos os artigos
		//TODO http.sendGet(articleID);
		//http.sendGet("2530912");
 
		
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();
		
		
		
 
	}
 
	// HTTP GET request
	private void sendGet(String articleID) throws Exception {
 
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
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
	// HTTP POST request
	private void sendPost() throws Exception {
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=10&categories=Atra%C3%A7%C3%B5es+tur%C3%ADsticas+de+Portugal&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		
		/*
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		*/
 		
		
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
 
	}

}
