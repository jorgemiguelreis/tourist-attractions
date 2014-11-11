package exceptions;

public class CategoryException extends Exception {
	
	String cat_title;
	
	public CategoryException(String title) {
		this.cat_title=title;
	}
	
	public String getMsg() {
		return cat_title;
	}

}
