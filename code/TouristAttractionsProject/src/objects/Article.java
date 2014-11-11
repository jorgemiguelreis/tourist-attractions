package objects;

public class Article {
	
	private String title;
	private String id;
	private String firstParagraph;
	private String url;
	private String text;
	private String lastchange;
	private String category_id;
	
	public Article(String id, String title, String cat, String lastchange) {
		this.title=title;
		this.id=id;
		this.category_id=cat;
		this.lastchange=lastchange;
		this.url="http://pt.wikipedia.org/wiki/"+title;
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstParagraph() {
		return firstParagraph;
	}

	public void setFirstParagraph(String firstParagraph) {
		this.firstParagraph = firstParagraph;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLastChange() {
		return lastchange;
	}

	public void setLastChange(String revision) {
		this.lastchange = revision;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	@Override
	public boolean equals(Object obj) {
		Article a = (Article) obj;
		if(title.equals(a.title) && id.equals(a.id))
			return true;
		else return false;
	}

	public void printDebug() {
		System.out.println("ARTIGO");
		System.out.println("TÍTULO: "+title);
		System.out.println("ID: "+id);
		System.out.println("1st P: "+firstParagraph);
		System.out.println("URL: "+url);
		System.out.println("TEXT: "+text);
		System.out.println("LAST CHANGE: "+lastchange);
		System.out.println("\n");
	}
	
}
