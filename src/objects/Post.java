package objects;

import java.sql.Timestamp;

public class Post {

	private int id;
	private String content;
	private Timestamp timing;
	private Member author;
	private int thread_id;
	private String readable_time;
	private String readable_date;

	public Post(int id, String content, Timestamp timing, Member author, int thread_) {
		super();
		this.id = id;
		this.content = content;
		this.timing = timing;
		this.author = author;
		this.thread_id = thread_;
		this.readable_time = timing.toString().substring(11, 16);
		this.readable_date = timing.toString().substring(0, 10);
	}

	public String getReadable_time() {
		return readable_time;
	}

	public void setReadable_time(String readable_time) {
		this.readable_time = readable_time;
	}

	public String getReadable_date() {
		return readable_date;
	}

	public void setReadable_date(String readable_date) {
		this.readable_date = readable_date;
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
		this.readable_time = timing.toString().substring(12, 16);
		this.readable_date = timing.toString().substring(0, 10);
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}

}
