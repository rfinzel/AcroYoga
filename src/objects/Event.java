package objects;

import java.sql.Date;
import java.sql.Timestamp;

public class Event {
	private int id;
	private String name;
	private Timestamp timing;
	private String readable_timing;
	private int regularity;
	private String place;
	private String shortContent;
	private double fee;
	private int instructor;
	private Date endDate;
	
	public Event(int id, String name, Timestamp timing, int regularity, String place, String shortContent, String content, double fee,
			int instructor, Date endDate) {
		this.id = id;
		this.name = name;
		this.timing = timing;
		this.regularity = regularity;
		this.place = place;
		this.content = content;
		this.shortContent = shortContent;
		this.fee = fee;
		this.instructor = instructor;
		this.readable_timing = timing.toString().substring(0, 16);
		this.endDate = endDate;

	}

	public String getShortContent() {
		return shortContent;
	}
	
	
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	
	private String content;
	
	public Timestamp getTiming() {
		return timing;
	}


	public void setTiming(Timestamp timing) {
		this.readable_timing = timing.toString().substring(0, 16);
		this.timing = timing;
	}


	public String getReadable_timing() {
		return readable_timing;
	}

	public void setReadable_timing(String readable_timing) {
		this.readable_timing = readable_timing;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegularity() {
		return regularity;
	}


	public void setRegularity(int regularity) {
		this.regularity = regularity;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public double getFee() {
		return fee;
	}


	public void setFee(double fee) {
		this.fee = fee;
	}


	public int getInstructor() {
		return instructor;
	}


	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}


	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

}
