package com.lisheung.todolist;

import java.io.Serializable;

public class Item implements Serializable {

	public String itemBody;
	
	public Item (String itemBody) {
		super();
		this.itemBody = itemBody;
	}
	
	public String getItemBody() {
		return itemBody;
	}
	
	public String toString() {
		return itemBody;
	}
}
