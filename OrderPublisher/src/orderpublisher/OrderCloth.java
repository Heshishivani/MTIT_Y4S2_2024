package orderpublisher;

public class OrderCloth {
	int clothId;
	int quantity;
	float price;

	public OrderCloth(int clothId, int quantity, float price) {
		super();
		this.clothId = clothId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getClothId() {
		return clothId;
	}

	public void setClothId(int clothId) {
		this.clothId = clothId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
