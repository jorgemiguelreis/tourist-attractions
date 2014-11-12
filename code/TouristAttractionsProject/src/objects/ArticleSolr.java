package objects;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class ArticleDAO {

	private static final String urlString = "http://localhost:8983/solr/atracoes"; 
	
	public static void insertArticle(Article article) {
		SolrServer solr = new HttpSolrServer(urlString);
		article.printDebug();
		
		SolrInputDocument document = new SolrInputDocument();
		
		document.addField("pageid", article.getId());
		document.addField("title", article.getTitle().replace("_", " "));
		document.addField("url", article.getUrl());
		document.addField("first_paragraph", article.getFirstParagraph());
		document.addField("text_content", article.getText());
		document.addField("categoryid", article.getCategory_id());
		document.addField("lastchange", article.getLastChange());
		
		try {
			UpdateResponse response = solr.add(document);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

}
