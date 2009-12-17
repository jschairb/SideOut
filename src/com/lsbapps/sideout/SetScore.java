package com.lsbapps.sideout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SetScore extends Activity {
	private static final String TAG = "SideOut";
	
	private static final String PREF_US = "us";
	private int usScore = 0;
	
	private static final String PREF_THEM = "them";
	private int themScore = 0;
	
	private static final String PREF_RESUME = "resume";
	private int RESUME = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_score);
		
		Log.d(TAG, "onCreate");
		
		getPreferences();
		
		Log.d(TAG, "usScore: " + usScore);
		Log.d(TAG, "themScore: " + themScore);
		Log.d(TAG, "RESUME: " + RESUME);
	}
	
	private void getPreferences() {
	    RESUME = getPreferences(MODE_PRIVATE).getInt(PREF_RESUME, RESUME);	
	    usScore = getPreferences(MODE_PRIVATE).getInt(PREF_US, usScore);
		themScore = getPreferences(MODE_PRIVATE).getInt(PREF_THEM, themScore);
	}
}