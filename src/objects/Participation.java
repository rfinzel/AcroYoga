package objects;

import java.sql.Date;

public class Participation {
	
	private int id;
	private Date timing;
	private int event;
	private int participants;
	public Participation(int id, Date timing, int event, int participants) {
		super();
		this.id = id;
		this.timing = timing;
		this.event = event;
		this.participants = participants;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTiming() {
		return timing;
	}
	public void setTiming(Date timing) {
		this.timing = timing;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
	public int getParticipants() {
		return participants;
	}
	public void setParticipants(int participants) {
		this.participants = participants;
	}
	
	
	
}
