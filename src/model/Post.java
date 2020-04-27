package model;

public class Post {

	private int id;
	private String name;
	private String text;
	private String created_at;

	public Post() {
	}

	public Post(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	public Post(int id, String name, String text) {
		this.id = id;
		this.name = name;
		this.text = text;
	}
	
	public Post(String name, String text, String created_at) {
		this.name = name;
		this.text = text;
		this.created_at = created_at;
	}	
	
	public Post(int id, String name, String text, String created_at) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.created_at = created_at;
	}	
    
	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name;
	}

	public String getCreated_at() {
		return created_at;
	}

}
