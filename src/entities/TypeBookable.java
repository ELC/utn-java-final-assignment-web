package entities;

import java.io.Serializable;
import java.util.Date;

public class TypeBookable implements Serializable {
	
	
	private int id;
	private String name;
	private String hourslimit;
	private int dayslimit;
	private int restriction;
	
	public TypeBookable(){}
	
	
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
	
	public String getHourslimit() {
		return hourslimit;
	}
	public void setHourslimit(String hourslimit) {
		this.hourslimit = hourslimit;
	}
	
	public int getDayslimit() {
		return dayslimit;
	}
	public void setDayslimit(int dayslimit) {
		this.dayslimit = dayslimit;
	}
	
	public int getRestriction() {
		return restriction;
	}
	public void setRestriction(int restriction) {
		this.restriction = restriction;
	}

	@Override
	public String toString(){
		return this.getName();
	}
	
	@Override
	public boolean equals(Object o){      
		return (o instanceof TypeBookable && ((TypeBookable)o).getId()==this.getId());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId()).hashCode();
	}

	
	
}
