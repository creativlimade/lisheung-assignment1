package com.lisheung.todolist;

import java.util.ArrayList;
import java.util.List;

import com.lisheung.todolist.Item;

public interface DataManager {

		public ArrayList<Item> loadItems();
		
		public void saveItems(List<Item> lts);
}
