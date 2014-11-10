package objects;

public class Category {
	
	private String title;
	private String id;
	private int depth;
	
	public Category(String title, String id, int depth) {
		this.title=title;
		this.id=id;
		this.depth=depth;
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
}
