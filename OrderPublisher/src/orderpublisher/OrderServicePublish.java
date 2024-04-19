package orderpublisher;

import java.util.ArrayList;

public interface OrderServicePublish {
	public Order createOrder(String userId, ArrayList<OrderCloth> cloths);
	public ArrayList<Order> getOrders();
}
