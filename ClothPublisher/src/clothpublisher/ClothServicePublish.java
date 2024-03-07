package clothpublisher;

import java.util.ArrayList;

public interface ClothServicePublish {
	
	public Cloth createCloth(int id , String name, float price, int stock);
	public Cloth getClothById(int id);
	public ArrayList<Cloth> getCloths();
	
}