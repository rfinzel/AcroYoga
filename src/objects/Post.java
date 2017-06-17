package objects;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {

	private int id;
	private String content;
	private Timestamp timing;
	private int author;
	private int thread_id;

	public Post(int id, String content, Timestamp timing, int author, int thread_) {
		super();
		this.id = id;
		this.content = content;
		this.timing = timing;
		this.author = author;
		this.thread_id = thread_;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}

}
