package entities;

import java.io.Serializable;

public class Bookable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private TypeBookable type;
	
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
	
	public TypeBookable getType() {
		return type;
	}
	public void setType(TypeBookable type) {
		this.type = type;
	}

	@Override
	public String toString(){
		return this.getName();
	}
	
	@Override
	public boolean equals(Object o){      
		return (o instanceof Bookable && ((Bookable)o).getId()==this.getId());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId()).hashCode();
	}
}