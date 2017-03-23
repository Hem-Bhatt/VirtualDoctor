package com.example.virtualdoctor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class description extends Activity {

	int selected_descease_id = 0;
	public TextView textview1;
	docname doc = new docname();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		selected_descease_id = getIntent().getExtras().getInt("checked_id");
		textview1 = (TextView) findViewById(R.id.textview1);
		doc = MainActivity.db1.get_DocIDS(selected_descease_id);
		textview1.setText(doc.desc);
	}

	public void dview (View v) {

		Intent i = new Intent(description.this, docphone.class);
		i.putExtra("IDs", doc.doc_name);
		startActivity(i);
	}

}
