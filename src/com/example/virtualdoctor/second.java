package com.example.virtualdoctor;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class second extends Activity
{
	ArrayAdapter<String> adapter1;
	ArrayList<ClassDesease> a = new ArrayList<ClassDesease>();
	ListView lv;
	boolean[] array;
	String[] values;

	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Integer> IDs = new ArrayList<Integer>();

	ArrayList<Integer> checkings = new ArrayList<Integer>();

	public static database db1;
	SparseBooleanArray mbooleanarray;
	String checked_ids;
	String[] arr_ids;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.probabledis);
		lv = (ListView)findViewById(R.id.listView1);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				 
				Intent i = new Intent(second.this, description.class);
				i.putExtra("checked_id", a.get(position).desease_id);
		         startActivity(i);
			
			}
		});
		
		//a = MainActivity.db1.get_names();
		checked_ids = getIntent().getExtras().getString("checked_ids");
		
		arr_ids = checked_ids.split(",");
		
		Log.d("checked_ids", arr_ids.length+"");
		if(arr_ids.length == 1)
		{
			a = MainActivity.db1.get_desease_names(Integer.parseInt(arr_ids[0]));
		}
		else if(arr_ids.length == 2)
		{
			a = MainActivity.db1.get_desease_names(Integer.parseInt(arr_ids[0]), Integer.parseInt(arr_ids[1]));
		}
		else if(arr_ids.length == 3)
		{
			a = MainActivity.db1.get_desease_names(Integer.parseInt(arr_ids[0]), Integer.parseInt(arr_ids[1]) , Integer.parseInt(arr_ids[2]));
		}
		
		ListAdapter1 adapter1 = new ListAdapter1(a);
		lv.setAdapter(adapter1);
	}
	
public class ListAdapter1 extends ArrayAdapter<ClassDesease> {
    	
    	String today[];
    	 class ViewHolder 
    	 {
    		public TextView tv ;
    	 }

    	 public ListAdapter1(ArrayList<ClassDesease> items) 
    	 {
 			super(second.this,R.layout.row_desesase, items);
 		 }
    	
    	 @Override
    	public View getView(final int position, View convertView, ViewGroup parent) {
    		View rowView = convertView;
    		if (rowView == null) 
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			rowView = inflater.inflate(R.layout.row_desesase, null);
    			final ViewHolder viewHolder = new ViewHolder();
    			viewHolder.tv = (TextView)rowView.findViewById(R.id.tvDeseaseName);
    			rowView.setTag(viewHolder);
    		}

    		final ViewHolder viewHolder = (ViewHolder) rowView.getTag();
    		
    		viewHolder.tv.setText(a.get(position).desease_name);
    		return rowView;
    	}
    	
    } 	
	
}
 


