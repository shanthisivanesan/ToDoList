package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EditItemActivity extends Activity {
	public int position=0;
	EditText etItem; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		//Get the data from the main screen
		String editItem = getIntent().getStringExtra("item");
		position = getIntent().getIntExtra("position",-1);
		//Toast.makeText(this, editItem+"-"+position, Toast.LENGTH_SHORT).show();
		// show in the text field
		etItem = (EditText)findViewById(R.id.etEditItem);
		etItem.setText(editItem);
	}
	
	public void onSubmit(View v) {
	  etItem = (EditText) findViewById(R.id.etEditItem);
	  // Prepare data intent 
	  Intent data = new Intent();
	  // Pass relevant data back as a result
	  data.putExtra("item", etItem.getText().toString());
	  data.putExtra("position", position);
	  
	  
	  // Activity finished ok, return the data
	  setResult(RESULT_OK, data); // set result code and bundle data for response
	  finish(); // closes the activity, pass data to parent
	} 

}
