package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Events
 *
 */
@Entity

public class Events implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String subject;

	private Date eventDate;
	private int max;
	private int min;
	private String lieu;
	private Float rating;

	@ManyToOne
	private Employee employee;

	private static final long serialVersionUID = 1L;

	public Events() {
		super();
	}

	public Events(String name, String subject, Date eventDate, int max, int min, String lieu) {
		super();
		this.name = name;
		this.subject = subject;
		this.eventDate = eventDate;
		this.max = max;
		this.min = min;
		this.lieu = lieu;
	}

	
	
	public Events(String name, String subject, Date eventDate, int max, int min, String lieu, Float rating) {
		super();
		this.name = name;
		this.subject = subject;
		this.eventDate = eventDate;
		this.max = max;
		this.min = min;
		this.lieu = lieu;
		this.rating = rating;
	}
	
	

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Events [id=" + id + ", name=" + name + ", subject=" + subject + ", eventDate=" + eventDate + ", max="
				+ max + ", min=" + min + ", lieu=" + lieu + ", employee=" + employee + "]";
	}

}
