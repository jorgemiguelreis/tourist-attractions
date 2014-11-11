package exceptions;

public class ArticleException extends Exception {
	
	private String title;
	
	public ArticleException(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;
	}

}
