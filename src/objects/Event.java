package objects;

import java.sql.Date;

public class Event {
	private int id;
	private String name;
	private Date timing;
	private int regularity;
	private String place;
	private String content;
	private double fee;
	private int instructor;
	
	public Event(int id, String name, Date timing, int regularity, String place, String content, double fee,
			int instructor) {
		this.id = id;
		this.name = name;
		this.timing = timing;
		this.regularity = regularity;
		this.place = place;
		this.content = content;
		this.fee = fee;
		this.instructor = instructor;
	}

	
	public Date getTiming() {
		return timing;
	}


	public void setTiming(Date timing) {
		this.timing = timing;
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

}
