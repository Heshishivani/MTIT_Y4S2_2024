package clothsubscriber;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import clothpublisher.Cloth;
import clothpublisher.ClothServicePublish;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {

		System.out.println("========================TredEco Item Management System Subscriber Service==========================");
		serviceReference = context.getServiceReference(ClothServicePublish.class.getName());
		ClothServicePublish clothServicePublish = (ClothServicePublish) context.getService(serviceReference);
		try {
			System.out.println(
					"Enter a option => \n Option 1 = Create New Item \n Option 2 = Get Items Using Id \n Option 3 = Get Item List \n Option 4 = Exit \n");
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
					System.out.print("Enter Item Id: ");
					int id = input.nextInt();
					System.out.print("Enter Item Name: ");
					String name = input.next();
					System.out.print("Enter Unit Price: ");
					float price = input.nextFloat();
					System.out.print("Enter Stock: ");
					int stock = input.nextInt();

					Cloth cloth = clothServicePublish.createCloth(id, name, price, stock);
					System.out.println("Item/ Cloth created successfully!");
					System.out.println("ID:" + cloth.getId() + "\tName:" + cloth.getName() + "\tPrice:"
							+ cloth.getPrice() + "\tStock:" + cloth.getStock());

					break;
				case 2:
					System.out.print("Enter Item Id: ");
					int Cloid = input.nextInt();

					Cloth cloth2 = clothServicePublish.getClothById(Cloid);
					
					if (cloth2 == null) {
						System.out.println("Item not found!");
						break;
					}
					
					System.out.println("Found Item!");
					System.out.println("ID:" + cloth2.getId() + "\tName:" + cloth2.getName() + "\tPrice:"
							+ cloth2.getPrice() + "\tStock:" + cloth2.getStock());

					break;

				case 3:
					ArrayList<Cloth> cloth3 = clothServicePublish.getCloths();

					for (Cloth cloths : cloth3) {
						System.out.println(cloths.toString());
						//System.out.println();
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
