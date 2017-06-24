package objects;

import java.sql.Date;

public class Participation {

	private int id;
	private Date timing;
	private int event;
	private Member participant;
	private String readable_timing;

	public Participation(int id, Date timing, int event, Member participant) {
		super();
		this.id = id;
		this.timing = timing;
		this.event = event;
		this.participant = participant;
		this.readable_timing = timing.toString().substring(0, 10);
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

	public Date getTiming() {
		this.readable_timing = timing.toString().substring(0, 10);
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

	public Member getParticipant() {
		return participant;
	}

	public void setParticipant(Member participant) {
		this.participant = participant;
	}

}
