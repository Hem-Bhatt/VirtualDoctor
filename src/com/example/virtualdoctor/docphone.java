package com.example.virtualdoctor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.virtualdoctor.MainActivity.ListAdapter;

public class docphone extends Activity {

	ListView lv;
	boolean[] array;
	String a;

	String[] values;
	ListAdapter adapter;
	ArrayAdapter<docname> adapter1;
	ArrayList<docname> names = new ArrayList<docname>();

	ArrayList<Integer> checkings = new ArrayList<Integer>();

	String[] doc_ids;
	ArrayList<String> doc_names = new ArrayList<String>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.docloc);
		lv = (ListView) findViewById(R.id.listView1);
		// a = MainActivity.db1.get_Docnames();
		// array = new boolean[names.size()];
		// lv.setAdapter(adapter);
		a = getIntent().getExtras().getString("IDs");
		a = a.substring(1, a.length());
		a = a.substring(0, a.length() - 1);

		doc_ids = a.split(",");

		MainActivity.db1.openDataBase();
		for (int i = 0; i < doc_ids.length; i++) {
			doc_names.add(MainActivity.db1.get_Docnames(Integer
					.parseInt(doc_ids[i])));
		}
		MainActivity.db1.CloseDataBase();

		ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, doc_names);
		lv.setAdapter(adp);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String latitude = MainActivity.db1.get_latitude(Integer.parseInt(doc_ids[arg2]));
				String longitude = MainActivity.db1.get_longitude(Integer.parseInt(doc_ids[arg2]));
				Intent i = new Intent(docphone.this , totaldesc.class);
				i.putExtra("latitude", latitude);
				i.putExtra("longitude", longitude);
				startActivity(i);
			}
		});
	}

	public class ListAdapter1 extends ArrayAdapter<docname> {

		public ListAdapter1(ArrayList<docname> items) {
			super(docphone.this, R.layout.doclist, items);
		}

		class ViewHolder {
			public TextView tv;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View rowView = convertView;
			if (rowView == null) {
				LayoutInflater inflater = getLayoutInflater();
				rowView = inflater.inflate(R.layout.doclist, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.tv = (TextView) rowView.findViewById(R.id.textView1);
				rowView.setTag(viewHolder);
			}

			final ViewHolder viewHolder = (ViewHolder) rowView.getTag();
			return rowView;

		}
	}

}
