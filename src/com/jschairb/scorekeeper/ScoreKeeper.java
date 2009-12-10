package com.jschairb.scorekeeper;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class ScoreKeeper extends Activity implements OnClickListener {
	private static final String TAG = "ScoreKeeper";
	
	private static final String PREF_US = "us";
	private int US_SCORE = 0;
	
	private static final String PREF_THEM = "them";
	private int THEM_SCORE = 0;
	
	private static final String PREF_RESUME = "resume";
	private int RESUME = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.main);
        
        //Set up click listeners for all the buttons
        View scoreUsButton = findViewById(R.id.score_us_button); 
        scoreUsButton.setOnClickListener(this); 
        View scoreThemButton = findViewById(R.id.score_them_button); 
        scoreThemButton.setOnClickListener(this);
        View resetButton = findViewById(R.id.reset_button); 
        resetButton.setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.reset_button:
    		Log.d(TAG, "onClick reset_button");
    		startGame();
    		break;
    	case R.id.score_us_button:
    		Log.d(TAG, "onClick score_us_button");
    		weScore();
    		break;
    	case R.id.score_them_button:
    		Log.d(TAG, "onClick score_them_button");
    		theyScore();
    		break;
    	}
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.about_menu:
    		Intent i = new Intent(this, About.class);
    		startActivity(i);
    		return true;
    	case R.id.exit_menu:
    		finish();
    		return true;
    	}
    	return false;
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.d(TAG, "onPause");
    	
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_US, US_SCORE).commit();
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_THEM, THEM_SCORE).commit();
    	
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_RESUME, RESUME).commit();
    	Log.d(TAG, "resume: " + RESUME);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume");
    	
    	RESUME = getPreferences(MODE_PRIVATE).getInt(PREF_RESUME, RESUME);
    	
    	Log.d(TAG, "onResume() resume: " + RESUME);
    	
    	switch(RESUME) {
    	case 1:
    		US_SCORE = getPreferences(MODE_PRIVATE).getInt(PREF_US, US_SCORE);
    		THEM_SCORE = getPreferences(MODE_PRIVATE).getInt(PREF_THEM, THEM_SCORE);
    		break;
    	case 0:
    		startGame();
    		break;
    	}
		
		Log.d(TAG, "onResume() US: " + US_SCORE);
		Log.d(TAG, "onResume() THEM: " + THEM_SCORE);
    }
    
    private void startGame() {
    	Log.d(TAG, "startGame");
    	US_SCORE = 0;
    	THEM_SCORE = 0;
    	RESUME = 0;
    }
 
    private void theyScore() {
		THEM_SCORE = score(THEM_SCORE);
		Log.d(TAG, "theyScore() THEM_SCORE: " + THEM_SCORE);
    }
    
    private void weScore() {
    	US_SCORE = score(US_SCORE);
    	Log.d(TAG, "weScore() US_SCORE: " + US_SCORE);
    }
    
    private int score(int s) {
    	RESUME = 1;
    	s++;
    	Log.d(TAG, "score: resume: " + RESUME);
    	return s;
    }
}