package com.jschairb.sideout;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class SideOut extends Activity implements OnClickListener, OnLongClickListener {
//	private static final String TAG = "ScoreKeeper";
	
	private static final String PREF_US = "us";
	private int usScore = 0;
	
	private static final String PREF_THEM = "them";
	private int themScore = 0;
	
	private static final String PREF_RESUME = "resume";
	private int RESUME = 0;
	
	private TextView displayScoreUs;
	private TextView displayScoreThem;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View scoreUsButton = findViewById(R.id.score_us_button); 
        scoreUsButton.setOnClickListener(this); 
        View scoreThemButton = findViewById(R.id.score_them_button); 
        scoreThemButton.setOnClickListener(this);
        
        displayScoreUs = (TextView) findViewById(R.id.us_score_label);
        displayScoreUs.setOnLongClickListener(this);
        
        displayScoreThem = (TextView) findViewById(R.id.them_score_label);
        displayScoreThem.setOnLongClickListener(this);
        
        updateScoreDisplays();
        
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
    	case R.id.score_us_button:
    		weScore();
    		break;
    	case R.id.score_them_button:
    		theyScore();
    		break;
    	}
    	updateScoreDisplays();
    }
    
	public boolean onLongClick(View v) {
		switch (v.getId()) {
    	case R.id.us_score_label:
    		Intent fix_us = new Intent(this, Keypad.class);
    		startActivity(fix_us);
    		return true;
    	case R.id.them_score_label:
    		Intent fix_them = new Intent(this, Keypad.class);
    		startActivity(fix_them);
    		return true;
    	}
		return false;
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.about_menu:
    		Intent i = new Intent(this, About.class);
    		startActivity(i);
    		return true;
    	case R.id.reset_menu:
    		startGame();
    		updateScoreDisplays();
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
    	
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_US, usScore).commit();
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_THEM, themScore).commit();
    	getPreferences(MODE_PRIVATE).edit().putInt(PREF_RESUME, RESUME).commit();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	RESUME = getPreferences(MODE_PRIVATE).getInt(PREF_RESUME, RESUME);
    	
    	switch(RESUME) {
    	case 1:
    		usScore = getPreferences(MODE_PRIVATE).getInt(PREF_US, usScore);
    		themScore = getPreferences(MODE_PRIVATE).getInt(PREF_THEM, themScore);
    		break;
    	case 0:
    		startGame();
    		break;
    	}
		
		updateScoreDisplays();
    }
    
    private void startGame() {
    	usScore = 0;
    	themScore = 0;
    	RESUME = 0;
    }
    
    private void updateScoreDisplays() {
    	displayScoreUs = (TextView) findViewById(R.id.us_score_label);
    	displayScoreUs.setText( new StringBuilder().append(usScore));
    	
    	displayScoreThem = (TextView) findViewById(R.id.them_score_label);
    	displayScoreThem.setText( new StringBuilder().append(themScore));
    }
 
    private void theyScore() {
		themScore = increment_score(themScore);
    }
    
    private void weScore() {
    	usScore = increment_score(usScore);
    }
    
    private int increment_score(int s) {
    	RESUME = 1;
    	s++;
    	return s;
    }
}