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

import javax.net.ssl.HttpsURLConnection;

import objects.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Requests {

	private final String USER_AGENT = "Mozilla/5.0";

	public static Map<Article, Category> articleCategory = new HashMap<Article, Category>();
	private static FactoryDAO dao;

	public static void main(String[] args) throws Exception {

		Requests http = new Requests();
		dao = new FactoryDAO();

		Category mainCat = new Category("Atra%C3%A7%C3%B5es+tur%C3%ADsticas+de+Portugal", "1172679" , 0);
		processCat(mainCat);

		dao.closeConnection();
		System.out.println("DONE");
	}

	public static void processCat(Category category) throws Exception {

		try {
			CategoryDAO.insertCategory(category);

			Requests http = new Requests();
			Vector<Category> subcats = http.obtainSubcategories(category.getTitle(), category.getDepth()+1);
			Vector<Article> articles = http.obtainArticlesFromCategory(category);

			for(Article article : articles) {
				if(articleCategory.containsKey(article))
					if(category.getDepth() >= articleCategory.get(article).getDepth()) {
						ArticleDAO.insertArticle(category, article);
						articleCategory.put(article, category);
					}
			}

			//guardar herança na bd
			for(int i=0; i<subcats.size(); i++) {	
				CategoryDAO.insertCategoriesInheritance(category, subcats.elementAt(i));
				processCat(subcats.elementAt(i));
			}

		}catch (CategoryException e) {
			System.err.println("Categoria "+e.getMsg()+" repetida.");
		}
	}


	public Vector<Article> obtainArticlesFromCategory(Category category) throws Exception
	{
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=10&categories="+category.getTitle()+"&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";


		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "language=pt&project=wikipedia&depth=0&categories="+category.getTitle()+"&negcats=&comb%5Bsubset%5D=1&atleast_count=0&ns%5B0%5D=1&show_redirects=both&templates_yes=&templates_any=&templates_no=&outlinks_yes=&outlinks_any=&outlinks_no=&edits%5Bbots%5D=both&edits%5Banons%5D=both&edits%5Bflagged%5D=both&before=&after=&max_age=&larger=&smaller=&minlinks=&maxlinks=&min_redlink_count=1&min_topcat_count=1&sortby=none&sortorder=ascending&format=csv&doit=Do+it%21&interface_language=en";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		//		System.out.println("\nSending 'POST' request to URL : " + url);
		//		System.out.println("Post parameters : " + urlParameters);
		//		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		in.readLine();
		String structureMessage = in.readLine();

		Vector<Article> articles = new Vector<Article>();

		while ((inputLine = in.readLine()) != null) {
			String[] result = inputLine.split(",");
			Article a = new Article(result[1].replace("\"",""), result[0].replace("\"",""), category.getId());
			articles.add(a);	
		}
		in.close();

		return articles;
	}

	public Vector<Category> obtainSubcategories(String CategoryTitle, int depth) throws Exception
	{
		String url = "https://tools.wmflabs.org/catscan2/catscan2.php?language=pt&depth=0&categories="+CategoryTitle+"&negcats=Est%C3%A2ncias+balneares+de+Portugal%E2%80%8E&doit=1";

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "language=pt&project=wikipedia&depth=10&categories="+CategoryTitle+"&negcats=Est%C3%A2ncias+balneares+de+Portugal&comb%5Bsubset%5D=1&atleast_count=0&ns%5B14%5D=1&show_redirects=both&templates_yes=&templates_any=&templates_no=&outlinks_yes=&outlinks_any=&outlinks_no=&edits%5Bbots%5D=both&edits%5Banons%5D=both&edits%5Bflagged%5D=both&before=&after=&max_age=&larger=&smaller=&minlinks=&maxlinks=&min_redlink_count=1&min_topcat_count=1&sortby=none&sortorder=ascending&format=csv&doit=Do+it%21&interface_language=en";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Post parameters : " + urlParameters);
		System.out.println("POST Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		in.readLine();
		String structureMessage = in.readLine();
		Vector<Category> subcats = new Vector<Category>();

		while ((inputLine = in.readLine()) != null) {	
			String[] result = inputLine.split(",");
			String name = result[0].replace("\"","");
			if(!name.startsWith("!")) {
				String id = result[1].replace("\"","");
				Category cat = new Category(name, id, depth);
				subcats.add(cat);
			}
		}
		in.close();

		return subcats;		
	}

	// HTTP GET request
	private void sendGet(Article article) throws Exception {

		JSONParser parser = new JSONParser();

		String urlIncomplete = "http://pt.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&pageids=";
		String url = urlIncomplete + article.getId();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("GET Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Object objparse = parser.parse(response.toString());
		JSONObject jsonObject = (JSONObject) objparse;
		String query = jsonObject.get("query").toString();


		Object objpages = parser.parse(query);
		JSONObject jsonobjpages = (JSONObject) objpages;
		String pages = jsonobjpages.get("pages").toString();


		Object objarticleID = parser.parse(pages);
		JSONObject jsonobjarticleID = (JSONObject) objarticleID;
		String articleIDjason = jsonobjarticleID.get(article.getId()).toString();


		Object objextract = parser.parse(articleIDjason);
		JSONObject jsonobjextract = (JSONObject) objextract;
		String extract = jsonobjextract.get("extract").toString();

		String[] aux = extract.split("</p>");

		String firstParagraph = aux[0].replaceAll("\\<[^>]*>", "");

		//TODO retirar apenas o extracts
		String cleanExtract = extract.replaceAll("\\<[^>]*>", "");
		System.out.println(cleanExtract);

		System.out.println("First paragraph is: " + firstParagraph);

		article.setFirstParagraph(firstParagraph);
		article.setText(cleanExtract);
	}


}
