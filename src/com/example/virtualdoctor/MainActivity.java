package com.example.virtualdoctor;

import java.io.IOException;
import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	CheckBox c;
	ListView lv;
	String checked_items;
	boolean[] array;
	int numberOfCheckboxesChecked = 0;

	String[] values;
	ListAdapter adapter;
	ArrayAdapter<String> adapter1;
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Integer> IDs = new ArrayList<Integer>();

	ArrayList<Integer> checkings = new ArrayList<Integer>();

	public static database db1;
	SparseBooleanArray mbooleanarray;
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdsymdirectory);
		setContentView(MainActivity.this);
		db1 = new database(MainActivity.this);
		try {
			db1.createDataBase();
			// db_writable = db.getWritableDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		lv = (ListView) findViewById(R.id.listView1);
		c = (CheckBox) findViewById(R.id.checkBox1);
		names = db1.get_names();
		IDs = db1.get_IDs();
		array = new boolean[names.size()];
		adapter = new ListAdapter(names);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			 public void onItemClick(AdapterView<?> parent, final View view,
			          int position, long id) {
			        final String item = (String) parent.getItemAtPosition(position);
			        Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
			       
			      }

			    });
	}
	

	private void setContentView(MainActivity mainActivity) {
		// TODO Auto-generated method stub

	}

	public void onItemClick(AdapterView<?> parent, final View view,
			int position, long id) {
		final String item = (String) parent.getItemAtPosition(position);
		Toast toast = Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG);
		toast.show();

	}

	public void View(View v) {
		String checked_ids = getCheckedIDs();
		Log.d("checked_ids", "on submit click  " + checked_ids);
		Intent i = new Intent(MainActivity.this, second.class);
		i.putExtra("checked_ids", checked_ids);
		startActivity(i);
	}
	
	public String getCheckedIDs() {
		String checked_items = "";

		for (int i = 0; i < names.size(); i++) {
			if (mbooleanarray.get(i)) {
				if (checked_items.equalsIgnoreCase("")) {
					checked_items = String.valueOf(IDs.get(i));
				} else {
					checked_items = checked_items + ","
							+ String.valueOf(IDs.get(i));
				}
			}
		}
		return checked_items;

	}

	public class ListAdapter extends ArrayAdapter<String> {
		String str[];

		class ViewHolder {
			public TextView tv;
			public CheckBox cb;
			int id;

		}

		public ListAdapter(ArrayList<String> names) {

			super(MainActivity.this, R.layout.symdirectory, names);
			mbooleanarray = new SparseBooleanArray();

		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View rowView = convertView;
			if (rowView == null) {
				LayoutInflater inflater = getLayoutInflater();
				rowView = inflater.inflate(R.layout.symdirectory, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.tv = (TextView) rowView.findViewById(R.id.textView1);
				viewHolder.cb = (CheckBox) rowView.findViewById(R.id.checkBox1);
				rowView.setTag(viewHolder);

			}

			final ViewHolder viewHolder = (ViewHolder) rowView.getTag();
			viewHolder.id = position;
			viewHolder.cb.setTag(position);
			viewHolder.cb.setChecked(mbooleanarray.get(position));
			viewHolder.cb
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton arg0,
								boolean ischecked) {

							if (ischecked) {
								numberOfCheckboxesChecked++;
							} else {
								numberOfCheckboxesChecked--;
							}
							if (ischecked && numberOfCheckboxesChecked >= 3) {
								c.setChecked(false);

							} else {
								mbooleanarray.put((Integer) arg0.getTag(),
										ischecked);
							}
							
							  
						}
					
						  

						

					});
			// String s = values[position];
			String s = names.get(position);
			//viewHolder.tv.setText(position + "");
			viewHolder.tv.setText(s);

			return rowView;
		}

	}

}
