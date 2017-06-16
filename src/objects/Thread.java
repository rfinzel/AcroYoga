package objects;

import java.sql.Date;
import java.sql.Timestamp;

public class Thread {
	
	private int id;
	private String name;
	private Timestamp timing;
	private int author;
	private int forum_id;
	
	public Thread(int id, String name, Timestamp timing, int author, int forum_id) {
		super();
		this.id = id;
		this.name = name;
		this.timing = timing;
		this.author = author;
		this.forum_id = forum_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getTiming() {
		return timing;
	}

	public void setTiming(Timestamp timing) {
		this.timing = timing;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getForum_id() {
		return forum_id;
	}

	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	
	
	
}
