import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class Example {
	
	public static void main(String args[]) {
		try {
			example();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void example() throws SolrServerException, IOException {
		
		String urlString = "http://localhost:8983/solr";
		SolrServer solr = new HttpSolrServer(urlString);
		
		//solr.deleteByQuery("*:*");
		
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "1");
		document.addField("name", "Vizinha à Universidade de Coimbra, em sua origem a Sé Nova foi a Igreja do Colégio dos Jesuítas (Colégio das Onze Mil Virgens), que se haviam instalado em Coimbra em 1541. O templo começou a ser construído em 1598, com projeto do arquiteto oficial dos jesuítas de Portugal, Baltazar Álvares, influenciado pela igreja do Mosteiro de São Vicente de Fora em Lisboa. As obras desenvolveram-se com lentidão, e o culto somente se iniciou em 1640, sendo o templo inaugurado apenas em 1698. Em 1759, os Jesuítas foram banidos de Portugal pelo Marquês de Pombal e, em 1772, a sede episcopal de Coimbra foi transferida da velha Sé românica para a espaçosa igreja jesuíta.");
		UpdateResponse response = solr.add(document);

		SolrInputDocument document2 = new SolrInputDocument();
		document2.addField("id", "3");
		document2.addField("name", "Coimbra OTE é uma cidade portuguesa, capital do Distrito de Coimbra, da Região Centro de Portugal, da sub-região do Baixo Mondego e da Beira Litoral com cerca de 143 396 habitantes.1 Sendo o maior núcleo urbano, é centro de referência na região das Beiras [carece de fontes], com mais de dois milhões de habitantes. Coimbra Coimbra Coimbra Coimbra");
		UpdateResponse response2 = solr.add(document2);
		
		// Remember to commit your changes!
		solr.commit();
		System.out.println("Done.");
	}
}