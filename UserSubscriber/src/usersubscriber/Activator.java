package usersubscriber;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import userpublisher.UserServicePublish;
import userpublisher.User;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println(
				"========================TredEco User Management System Subscriber Service==========================");
		serviceReference = context.getServiceReference(UserServicePublish.class.getName());
		UserServicePublish servicePublish = (UserServicePublish) context.getService(serviceReference);

		try {
			System.out.println("Enter an option  => \n Option 1 = Create User \n Option 2 = Get User Using Id \n Option 3 = Get User List \n Option 4 = Exit");
			
			int option;
			Scanner input = new Scanner(System.in);
			Boolean isExit = false;

			// Loop endlessly.
			while (isExit == false) {
				System.out.println(
						"===================================================================================================\n");
				// Ask the user to enter a word.
				System.out.print("Enter Option: ");
				
				option = input.nextInt();
				System.out.println(
						"===================================================================================================\n");

				switch (option) {
				case 1:
					System.out.print("Enter User Id: ");
					String user_id = input.next();
					System.out.print("Enter Name: ");
					String username = input.next();
					System.out.print("Enter Contact Number: ");
					String contect_number = input.next();
					System.out.print("Enter Adderess: ");
					String address = input.next();

					User user = servicePublish.creteUser(user_id, username, contect_number, address);
					System.out.println("User created successfully!");

					System.out.println("User ID:" + user.getUser_id() + "\tName:" + user.getUsername()
							+ "\tContact Number:" + user.getContect_number()
							+ "\tAddress:" + user.getAddress());

					break;
				case 2:
					System.out.print("Enter User Id: ");
					String UserId = input.next();

					User user2 = servicePublish.getUsereById(UserId);
					
					if (user2 == null) {
						System.out.println("User not found!");
						break;
					}
					
					System.out.println("Found User!");
					System.out.println("User ID:" + user2.getUser_id() + "\tName:" + user2.getUsername()
							+ "\tContact Number:" + user2.getContect_number()
							+ "\tAddress:" + user2.getAddress());

					break;
				case 3:
					// get user
					ArrayList<User> users = servicePublish.getUsers();
					
					//print user
					for ( User user1 : users) {
						System.out.println(user1.toString());
					}
					break;
				case 4:
					System.out.println("Exiting the system...");
					System.out.println("============================================Thank you==============================================");
					
					isExit = true;
					break;
					
				default:
					System.out.println("Please Select a Valid Option!");
				}
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}

}
