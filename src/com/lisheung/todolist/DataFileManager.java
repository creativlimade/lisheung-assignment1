package com.lisheung.todolist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.lisheung.todolist.Item;

public class DataFileManager implements DataManager {

	private static final String FILENAME = "file.sav";
	
	public DataFileManager() {
		
	}
	
	
	@Override
	public ArrayList<Item> loadItems() {
		ArrayList<Item> lts = new ArrayList<Item>();
		
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			lts = (ArrayList<Item>) ois.readObject();
			
		} catch (Exception e) {
			Log.i("TodoList", "Error casting");
		}
		
		return lts;
	}

	@Override
	public void saveItems(List<Item> lts) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lts);
			fos.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
