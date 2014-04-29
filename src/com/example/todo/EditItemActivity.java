package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
	public int position=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		String updateItem = getIntent().getStringExtra("item");
		position = Integer.parseInt(getIntent().getStringExtra("position").toString());

	}
	
	public void onSubmit(View v) {
	  EditText etName = (EditText) findViewById(R.id.etItem);
	  // Prepare data intent 
	  Intent data = new Intent();
	  // Pass relevant data back as a result
	  data.putExtra("item", etName.getText().toString());
	  data.putExtra("position", position);
	  
	  
	  // Activity finished ok, return the data
	  setResult(RESULT_OK, data); // set result code and bundle data for response
	  finish(); // closes the activity, pass data to parent
	} 

}
