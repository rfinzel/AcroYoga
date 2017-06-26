package objects;

import java.sql.Timestamp;

public class Thread {
	
	private int id;
	private String name;
	private Timestamp timing;
	private Member author;
	private int forum_id;
	private String readable_time;
	private String readable_date;
	
	public Thread(int id, String name, Timestamp timing, Member author, int forum_id) {
		super();
		this.id = id;
		this.name = name;
		this.timing = timing;
		this.author = author;
		this.forum_id = forum_id;
		this.readable_time = timing.toString().substring(11, 16);
		this.readable_date = timing.toString().substring(0, 10);	}
	
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
		this.readable_time = timing.toString().substring(12, 16);
		this.readable_date = timing.toString().substring(0, 10);		this.timing = timing;
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public int getForum_id() {
		return forum_id;
	}

	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}	
}
