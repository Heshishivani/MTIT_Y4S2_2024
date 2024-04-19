package orderpublisher;

import java.util.ArrayList;

public class Order {
	long id;
	String userId;
	ArrayList<OrderCloth> cloths;
	float totalPrice;

	public Order(long id, String userId, ArrayList<OrderCloth> cloths, float totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.cloths = cloths;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<OrderCloth> getCloths() {
		return cloths;
	}

	public void setCloths(ArrayList<OrderCloth> cloths) {
		this.cloths = cloths;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		// display order details
		sb.append("Order ID: " + this.getId() + "\tUser ID: " + this.getUserId() + "\tTotal Price: "
				+ this.getTotalPrice() + "\nCloths\n");
		// display medicines
		for (OrderCloth orderCloth : this.getCloths()) {
			sb.append("Item ID: " + orderCloth.getClothId() + "\tQuantity: " + orderCloth.getQuantity()
					+ "\tNet Price: " + orderCloth.getPrice());
		}

		return sb.toString();
	}

}
