package com.example.virtualdoctor;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.R.string;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class totaldesc extends FragmentActivity
{
	private GoogleMap googleMap;
	private SupportMapFragment mapFM;
	String latitude = "" , longitude = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.totaldesc);
		initMap();
		//int doc_id = getIntent().getExtras().getInt("doc_id");
		latitude =  getIntent().getExtras().getString("latitude");
		longitude =  getIntent().getExtras().getString("longitude");
		Toast.makeText(totaldesc.this, latitude + "  " + longitude, Toast.LENGTH_LONG).show();
		afterRequestData();
		
		

	}
	private void initMap()
	{
		mapFM = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
		if (mapFM == null)
		{
			return;
		}
		googleMap = mapFM.getMap();
		if (googleMap == null)
		{
			//AlertDialogUtility.showAlert(mActivity, "Google Map Not Supported!");
			return;
		}
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

		// Showing / hiding your current location
		googleMap.setMyLocationEnabled(true);

		// Enable / Disable zooming controls
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		// Enable / Disable my location button
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		// Enable / Disable Compass icon
		googleMap.getUiSettings().setCompassEnabled(true);

		// Enable / Disable Rotate gesture
		googleMap.getUiSettings().setRotateGesturesEnabled(true);

		// Enable / Disable zooming functionality
		googleMap.getUiSettings().setZoomGesturesEnabled(true);
		//googleMap.setInfoWindowAdapter(null);
	}
	private void afterRequestData()
	{
		if (googleMap != null)
		{
			googleMap.clear();
			
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)));
			// markerOptions.title(classRequest.title);
			//markerOptions.title(classRequest.request_id);
			// markerOptions.snippet(String.valueOf(count));
			markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.doctorplus));
			googleMap.addMarker(markerOptions);
		
		}
	

		
	}
}



