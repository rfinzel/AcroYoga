package objects;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Event {
	private int id;
	private String name;
	private Timestamp timing;
	private String readable_time;
	private String readable_date;
	private int regularity;
	private String place;
	private String content;
	private String shortContent;
	private double fee;
	private int instructor;
	private Date endDate;
	private String weekday;
	private String content;
	
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
		this.readable_time = timing.toString().substring(11, 16);
		this.readable_date = timing.toString().substring(0, 10);
		this.endDate = endDate;
		this.weekday = new SimpleDateFormat("EE").format(timing);
	}

	public String getShortContent() {
		return shortContent;
	}
	
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	
	

	public Timestamp getTiming() {
		return timing;
	}

	public void setTiming(Timestamp timing) {
		this.timing = timing;
		this.weekday = new SimpleDateFormat("EE").format(timing);
		this.readable_time = timing.toString().substring(12, 16);
		this.readable_date = timing.toString().substring(0, 10);
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekdate) {
		this.weekday = weekdate;
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
