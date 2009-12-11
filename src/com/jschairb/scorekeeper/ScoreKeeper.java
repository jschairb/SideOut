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
import android.widget.TextView;

public class ScoreKeeper extends Activity implements OnClickListener {
	private static final String TAG = "ScoreKeeper";
	
	private static final String PREF_US = "us";
	private int usScore = 0;
	
	private static final String PREF_THEM = "them";
	private int themScore = 0;
	
	private static final String PREF_RESUME = "resume";
	private int RESUME = 0;
	
	private TextView usScoreDisplay;
	private TextView themScoreDisplay;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.main);
        
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
    	updateScoreDisplays();
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
    	
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_US, usScore).commit();
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_THEM, themScore).commit();
    	
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
    		usScore = getPreferences(MODE_PRIVATE).getInt(PREF_US, usScore);
    		themScore = getPreferences(MODE_PRIVATE).getInt(PREF_THEM, themScore);
    		updateScoreDisplays();
    		break;
    	case 0:
    		startGame();
    		break;
    	}
		
		Log.d(TAG, "onResume() US: " + usScore);
		Log.d(TAG, "onResume() THEM: " + themScore);
    }
    
    private void startGame() {
    	Log.d(TAG, "startGame");
    	usScore = 0;
    	themScore = 0;
    	RESUME = 0;
    }
    
    private void updateScoreDisplays() {
    	usScoreDisplay = (TextView) findViewById(R.id.us_score_label);
    	usScoreDisplay.setText( new StringBuilder().append(usScore));
    	
    	themScoreDisplay = (TextView) findViewById(R.id.them_score_label);
    	themScoreDisplay.setText( new StringBuilder().append(themScore));
    }
 
    private void theyScore() {
		themScore = increment_score(themScore);
		Log.d(TAG, "theyScore() themScore: " + themScore);
    }
    
    private void weScore() {
    	usScore = increment_score(usScore);
    	Log.d(TAG, "weScore() usScore: " + usScore);
    }
    
    private int increment_score(int s) {
    	RESUME = 1;
    	s++;
    	return s;
    }
}