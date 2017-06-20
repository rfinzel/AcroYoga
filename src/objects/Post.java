package objects;

import java.sql.Timestamp;

public class Post {

	private int id;
	private String content;
	private Timestamp timing;
	private int author;
	private int thread_id;
	private String readable_timing;

	public Post(int id, String content, Timestamp timing, int author, int thread_) {
		super();
		this.id = id;
		this.content = content;
		this.timing = timing;
		this.author = author;
		this.thread_id = thread_;
		this.readable_timing = timing.toString().substring(0, 16);
	}

	public String getReadable_timing() {
		return readable_timing;
	}

	public void setReadable_timing(String readable_timing) {
		this.readable_timing = readable_timing;
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
		this.readable_timing = timing.toString().substring(16);

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
