package com.example.virtualdoctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;



public class splash extends Activity {
	
	MyCountDownTimer mc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mc = new MyCountDownTimer(5000, 1000);
		mc.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
		super(startTime, interval);
		}

		@Override
		public void onFinish() {
			finish();
			Intent i = new Intent(splash.this , MainActivity.class);
			startActivity(i);
		}

		@Override
		public void onTick(long millisUntilFinished) {
		
		}
		}
}

 


