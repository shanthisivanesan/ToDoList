package com.example.todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView lvItems;
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lvItems = (ListView)findViewById(R.id.lvItems);
		items = new ArrayList<String>();
		readItems();
		itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
		lvItems.setAdapter(itemsAdapter);
		setupListViewListener();
	}
	private void setupListViewListener() {
		// TODO Auto-generated method stub
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View view,int position,long rowId)
			{
				items.remove(position);
				itemsAdapter.notifyDataSetChanged();
				saveItems();
				return true;
			}
		}); 
		//pick the item clicked for edit and once updated notify and save the changes
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView <? > parent, View view,
	            int position, long id) {
				  String test = (String) lvItems.getAdapter().getItem(position);  
                  Log.i("Selected Item in list", test);  
                  itemsAdapter.notifyDataSetChanged();
  				saveItems();

	        }
	});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
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
	public void onAddItemClick(View v) {
		EditText text = (EditText)findViewById(R.id.etItem);
		if (text.length() > 0) {
		itemsAdapter.add(text.getText().toString());
		text.setText("");
		saveItems();
		}
		
	}
	private void readItems(){
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir,"todo.txt");
		try{
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		}
		catch(IOException ex){
			items = new ArrayList<String>();
			ex.printStackTrace();
		}
	}
	
	private void saveItems(){
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir,"todo.txt");
		try{
			FileUtils.writeLines(todoFile,items);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
}
