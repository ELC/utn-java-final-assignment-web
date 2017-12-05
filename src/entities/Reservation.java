package entities;


import java.io.Serializable;
import java.sql.Timestamp;

public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private Person person;
	private Bookable bookable;
	private Timestamp date;
	private String detail;
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public Bookable getBookable() {
		return bookable;
	}
	public void setBookable(Bookable bookable) {
		this.bookable = bookable;
	}

	public Timestamp getDate() {
		return date;
	}
	
}
