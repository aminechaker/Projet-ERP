package tn.esprit.b2.esprit1718b2erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Assets
 *
 */
@Entity

public class Assets implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Float value;
	private Float actual_value;
	private String type;
	@ManyToOne
	private User user;
	public Assets() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;

	public Assets(String type, String name, float actual_value) {
		super();
	} 
	
	public Assets(int id,String name, Float value, Float actual_value, String type, User user) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.actual_value = actual_value;
		this.type = type;
		this.user = user;
	}

	public int getId() {
		return this.id;
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

	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getActual_value() {
		return actual_value;
	}
	public void setActual_value(Float actual_value) {
		this.actual_value = actual_value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Assets [id=" + id + ", value=" + value + ", actual_value=" + actual_value + ", type=" + type + ", user="
				+ user + "]";
	}
	
   
}
