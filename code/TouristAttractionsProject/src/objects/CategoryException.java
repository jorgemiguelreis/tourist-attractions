package objects;

public class CategoryException extends Exception {
	
	String msg;
	
	public CategoryException(String msg) {
		this.msg=msg;
	}
	
	public String getMsg() {
		return msg;
	}

}
