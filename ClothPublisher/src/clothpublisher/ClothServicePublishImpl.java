package clothpublisher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClothServicePublishImpl implements ClothServicePublish {

	String fileName = "item.txt";
	File file = new File(fileName);

	@Override
	public Cloth createCloth(int id, String name, float price, int stock) {
		Cloth cloth = new Cloth(id, name, price, stock);

		// write to file
		try {
			FileWriter writer = new FileWriter(file, true);

			StringBuilder sb = new StringBuilder();
			sb.append(id).append(":");
			sb.append(name).append(":");
			sb.append(price).append(":");
			sb.append(stock).append(":");

			sb.append(System.lineSeparator());
			writer.write(sb.toString());

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return cloth;

	}

	@Override
	public Cloth getClothById(int id) {

		Cloth cloth = null;

		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			String line;
			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(":");

				int Mid = Integer.parseInt(parts[0]);
				String name = parts[1];
				float price = Float.parseFloat(parts[2]);
				int stock = Integer.parseInt(parts[3]);

				if (Mid != id)
					continue;

				// set values to cloth
				cloth = new Cloth();
				cloth.setId(Mid);
				cloth.setName(name);
				cloth.setPrice(price);
				cloth.setStock(stock);
				
				break;
			}

			buffer.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return cloth;

	}

	@Override
	public ArrayList<Cloth> getCloths() {
		
		ArrayList<Cloth> cloths = new ArrayList<>();

		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			String line;
			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(":");

				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				float price = Float.parseFloat(parts[2]);
				int stock = Integer.parseInt(parts[3]);

				// create object
				Cloth cloth = new Cloth(id, name, price, stock);
				cloths.add(cloth);
			}

			buffer.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return cloths;
	
	}

}