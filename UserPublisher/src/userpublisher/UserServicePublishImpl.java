package userpublisher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserServicePublishImpl implements UserServicePublish {

	String fileName = "user.txt";
	File file = new File(fileName);

	@Override
	public User creteUser(String user_id, String username, String contect_number, String address) {
		User user = new User(user_id, username, contect_number, address);

		// write to file
		try {
			FileWriter writer = new FileWriter(file, true);

			StringBuilder sb = new StringBuilder();
			sb.append(user_id).append(":");
			sb.append(username).append(":");
			sb.append(contect_number).append(":");
			sb.append(address).append(":");

			sb.append(System.lineSeparator());
			writer.write(sb.toString());

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUsereById(String user_id) {

		User user = null;

		try {
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			String line;
			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(":");

				String userId = parts[0];
				String UserName = parts[1];
				String contectNumber = parts[2];
				String Address = parts[3];

				if (!userId.equals(user_id))
					continue;

				// set values to user
				user = new User();
				user.setUser_id(userId);
				user.setUsername(UserName);
				user.setContect_number(contectNumber);
				user.setAddress(Address);

				break;
			}

			buffer.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return user;
	}
	
	@Override
	public ArrayList<User> getUsers(){
		ArrayList<User> users  = new ArrayList<>();
		
		try {
			FileReader reader = new FileReader("user.txt");
			BufferedReader buffer = new BufferedReader(reader);
			
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] parts = line.split(":");
				
				String userId = parts[0];
				String UserName = parts[1];
				String contectNumber = parts[2];
				String Address = parts[3];
				
				User user = new User(userId, UserName, contectNumber, Address);
	            users.add(user);
			}
			buffer.close();
		}catch(Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return users;
	}
}