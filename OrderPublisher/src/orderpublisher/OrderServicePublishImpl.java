package orderpublisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderServicePublishImpl implements OrderServicePublish {

	@Override
	public Order createOrder(String userId, ArrayList<OrderCloth> cloths) {

		long orderId = System.currentTimeMillis();
		float totalPrice = 0;

		// write to file
		try {
			FileWriter writer = new FileWriter("orders.txt", true);

			StringBuilder sb = new StringBuilder();

			// id
			sb.append(orderId).append(":");

			// user ID
			sb.append(userId).append(":");

			// cloths
			for (int i = 0; i < cloths.size(); i++) {
				OrderCloth cloth = cloths.get(i);

				// total price calculation
				totalPrice += cloth.getPrice();

				sb.append(cloth.getClothId()).append(",").append(cloth.getQuantity()).append(",")
						.append(cloth.getPrice());
				if (i < cloths.size() - 1) {
					sb.append(";");
				}
			}

			// Total price
			sb.append(":").append(totalPrice).append(":");

			sb.append(System.lineSeparator());
			writer.write(sb.toString());

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Order order = new Order(orderId, userId, cloths, totalPrice);
		return order;
	}

	@Override
	public ArrayList<Order> getOrders() {

		ArrayList<Order> orders = new ArrayList<>();

		try {
			FileReader reader = new FileReader("orders.txt");
			BufferedReader buffer = new BufferedReader(reader);

			String line;
			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(":");

				long id = Long.parseLong(parts[0]);
				String userId = parts[1];
				String clothListString = parts[2];
				float totalPrice = Float.parseFloat(parts[3]);

				// parse medicines list
				ArrayList<OrderCloth> cloths = new ArrayList<OrderCloth>();
				String[] clothStrings = clothListString.split(";");
				for (String clothString : clothStrings) {
					String[] clothParts = clothString.split(",");
					int clothId = Integer.parseInt(clothParts[0]);
					int quantity = Integer.parseInt(clothParts[1]);
					float price = Float.parseFloat(clothParts[2]);
					cloths.add(new OrderCloth(clothId, quantity, price));
				}

				// create order object
				Order order = new Order(id, userId, cloths, totalPrice);
				orders.add(order);
			}

			buffer.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return orders;
	}

}
