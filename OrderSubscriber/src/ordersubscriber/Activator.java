package ordersubscriber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import orderpublisher.Order;
import orderpublisher.OrderCloth;
import orderpublisher.OrderServicePublish;
import userpublisher.User;
import userpublisher.UserServicePublish;
import clothpublisher.Cloth;
import clothpublisher.ClothServicePublish;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println(
				"========================TredEco Order Management System Subscriber Service==========================");
		
		serviceReference = context.getServiceReference(OrderServicePublish.class.getName());
		OrderServicePublish orderServicePublish = (OrderServicePublish) context.getService(serviceReference);

		serviceReference = context.getServiceReference(ClothServicePublish.class.getName());
		ClothServicePublish clothServicePublish = (ClothServicePublish) context.getService(serviceReference);

		serviceReference = context.getServiceReference(UserServicePublish.class.getName());
		UserServicePublish userServicePublish = (UserServicePublish) context.getService(serviceReference);

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			Boolean isExit = false;

			while (isExit == false) {
				int option = 0;

				System.out.println("Enter an option  => \nOption 1 = Create a New Order.\nOption 2 = View Orders.\nOption 3 = Exit System");
				
				System.out.println(
						"===================================================================================================\n");
				
				// get option input
				try {
					System.out.print("Enter Option: ");
					option = Integer.parseInt(in.readLine());
				} catch (NumberFormatException ex) {
					System.out.println("Only Integers are Allowed!");
				}
				
				System.out.println(
						"===================================================================================================\n");

				// process options
				switch (option) {
				case 1:
					String userId = "";
					ArrayList<OrderCloth> cloths = new ArrayList<OrderCloth>();

					// get user id
					try {
						System.out.print("User ID: ");
						userId = in.readLine();
					} catch (NumberFormatException ex) {
						System.out.println("Error!");
					}

					// Validate user
					User user = userServicePublish.getUsereById(userId);
					if (user == null) {
						System.out.println("User not found, Please try again!");
						continue;
					}

					int clothOption = 1;
					int clothId = 0;
					int quantity = 0;

					// add cloth to cart
					while (clothOption == 1) {
						// get cloth id
						try {
							System.out.print("Item ID: ");
							clothId = Integer.parseInt(in.readLine());
						} catch (NumberFormatException ex) {
							System.out.println("Only Integers are Allowed!");
						}

						// validate cloth
						Cloth cloth = clothServicePublish.getClothById(clothId);
						if (cloth == null) {
							System.out.println("Item not found, Please try again!");
							continue;
						}

						// get cloth quantity
						try {
							System.out.print(cloth.getName() + " Quanity: ");
							quantity = Integer.parseInt(in.readLine());
						} catch (NumberFormatException ex) {
							System.out.println("Only Integers are Allowed!");
						}

						float orderClothPrice = 0;
						int stock = cloth.getStock();
						float price = cloth.getPrice();

						// validate stock
						if (quantity > stock) {
							System.out.println("Does not have enough stock, Please try again!");
							continue;
						} else {
							orderClothPrice = price * quantity;
						}

						// add cloth
						OrderCloth orderCloth = new OrderCloth(clothId, quantity, orderClothPrice);
						cloths.add(orderCloth);

						try {
							System.out.println(
									"Enter 1 to Add a New CLoth to Order. \nEnter Any Number to Proceed Order.");
							System.out.print("Option:");
							clothOption = Integer.parseInt(in.readLine());
						} catch (NumberFormatException ex) {
							System.out.println("Only Integers are Allowed!");
						}
					}

					// save order
					Order order1 = orderServicePublish.createOrder(userId, cloths);
					System.out.println("Order has been created successfully with ID," + order1.getId());

					break;
				case 2:
					// get order
					ArrayList<Order> orders = orderServicePublish.getOrders();

					// print orders
					for (Order order : orders) {
						System.out.println(order.toString());
						//System.out.println();
						System.out.println(
								"---------------------------------------------------------------------------------------------------\n");
						System.out.println();
					}

					break;
				case 3:
					System.out.println("Exiting the system...");
					System.out.println("============================================Thank you==============================================");
					
					isExit = true;
					break;
				default:
					System.out.println("Please Select a Valid Option!");
					break;
				}

			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}

}