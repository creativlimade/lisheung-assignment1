/*

Copyright (C) 2014 Christopher Li Sheung Ying lisheung@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.

*/

package com.lisheung.todolist;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.lisheung.todolist.FragmentTodo;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/** Fragment managing the behaviors, interactions and presentation of the navigation drawer.*/
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/** Used to store the last screen title. For use in {@link #restoreActionBar()}. */
	private CharSequence mTitle;
	
	private DataManager dataManager;
	private EditText bodyText;
	private ArrayList<Item> items;
	private ArrayAdapter<Item> itemsViewAdapter;
	private ListView ItemsList;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		// Set up the array.
		/**bodyText = (EditText) findViewById(R.id.TextBody);
		ItemsList = (ListView) findViewById(R.id.ItemsList);*/
	}

	/**@Override
	protected void onStart() {
		super.onStart();
		
		dataManager = new DataFileManager();
		
		items = dataManager.loadItems();
		
		itemsViewAdapter = new ArrayAdapter<Item>(this, R.layout.list_item, items);
		ItemsList.setAdapter(itemsViewAdapter);
	}*/
	
	/**public void save(View v) {
		String text = bodyText.getText().toString();
		
		Item item = new Item(text);
		items.add(item);
		
		itemsViewAdapter.notifyDataSetChanged();
		
		bodyText.setText("");
		dataManager.saveItems(items);
	}*/
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		Fragment fragment = new FragmentTodo();
		FragmentManager fragmentManager = getFragmentManager();
		switch (position+1) {
			case 1:
				fragment = new FragmentTodo();
				break;
			case 2:
				fragment = new FragmentArchive();
				break;
			case 3:
				fragment = new FragmentEmail();
				break;
			case 4:
				fragment = new FragmentSummary();
				break;
		}
		fragmentManager.beginTransaction()
				.replace(R.id.container, fragment).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** A placeholder fragment containing a simple view. */
	public static class PlaceholderFragment extends Fragment {
		
		/** The fragment argument representing the section number for this fragment. */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/** Returns a new instance of this fragment for the given section number. */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

//=================================================================================//
	
	public class FragmentTodo extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			return inflater.inflate(R.layout.fragment_todo, container,
					false);
		}
	}
	
	public class FragmentArchive extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			return inflater.inflate(R.layout.fragment_archive, container,
					false);
		}
	}

	public class FragmentEmail extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			return inflater.inflate(R.layout.fragment_email, container,
					false);
		}
	}
	
	public class FragmentSummary extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the layout for this fragment
			return inflater.inflate(R.layout.fragment_summary, container,
					false);
		}
	}
}
