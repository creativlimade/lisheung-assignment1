package com.lisheung.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.lisheung.todolist.DataFileManager;
import com.lisheung.todolist.DataManager;

public class FragmentTodo extends Activity {
	
	private DataManager dataManager;
	private EditText bodyText;
	private ArrayList<Item> items;
	private ArrayAdapter<Item> itemsViewAdapter;
	private ListView ItemsList;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_todo);
		
		// Set up array.
		bodyText = (EditText) findViewById(R.id.TextBody);
		ItemsList = (ListView) findViewById(R.id.ItemsList);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		dataManager = new DataFileManager();
		
		items = dataManager.loadItems();
		
		itemsViewAdapter = new ArrayAdapter<Item>(this, R.layout.list_item, items);
		ItemsList.setAdapter(itemsViewAdapter);
	}
	
	public void save(View v) {
		String text = bodyText.getText().toString();
		
		Item item = new Item(text);
		items.add(item);
		
		itemsViewAdapter.notifyDataSetChanged();
		
		bodyText.setText("");
		dataManager.saveItems(items);
	}
}