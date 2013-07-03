package com.mitaiti.indiavotes;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public class SubmitActivity extends Activity {

	TextView tv;
	LocationListener loclistener;
	LocationManager locmgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		// Show the Up button in the action bar.
		setupActionBar();
		boolean answer = getIntent().getBooleanExtra("Answer", false);
		tv = (TextView) findViewById(R.id.submit_text);
		if(answer)
			tv.setText("60% of respondents replied YES!");
		else
			tv.setText("40% respondents replied NO!");
		
		locmgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		loclistener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				double lat = location.getLatitude();
				double lon = location.getLongitude();
				tv.setText("Location Listener...\n" + "Latitude = " + lat + "\nLongitude = " + lon);
				Toast.makeText(SubmitActivity.this, "Location Changed!", Toast.LENGTH_SHORT)
				.show();
			}

			@Override
			public void onProviderDisabled(String provider) {
				Toast.makeText(SubmitActivity.this, "Location Disabled!", Toast.LENGTH_SHORT)
				.show();
			}

			@Override
			public void onProviderEnabled(String provider) {
				Toast.makeText(SubmitActivity.this, "Location Enabled!", Toast.LENGTH_SHORT)
				.show();				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void startListeningtoLocation(View view) {
		locmgr.requestLocationUpdates(locmgr.GPS_PROVIDER,5000,2,loclistener);
	}
	
	public void stopListeningtoLocation(View view) {
		locmgr.removeUpdates(loclistener);
	}

}
