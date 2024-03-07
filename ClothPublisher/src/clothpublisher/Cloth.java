package clothpublisher;

import java.io.Serializable;

public class Cloth implements Serializable{
	
	private int id;
	private String name;
	private float price;
	private int stock;
	
	
	public Cloth(int id, String name, float price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}


	public Cloth() {
		super();
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


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		// display cloth details
		sb.append("Item ID: " + this.getId() + "\tName: " + this.getName() + "\tPrice: "
				+ this.getPrice() + "\tStock: " + this.getStock());
		
		return sb.toString();
	}

}
