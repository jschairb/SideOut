package com.jschairb.scorekeeper;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
// import android.view.View;
// import android.view.View.OnClickListener;

public class ScoreKeeper extends Activity {// implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Set up click listeners for all the buttons
        // View continueButton = findViewById(R.id.continue_button); 
        // continueButton.setOnClickListener(this); 
        // View newButton = findViewById(R.id.new_button); 
        // newButton.setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
//    public void onClick(View v) {
//    	switch (v.getId()) {
//    }
    
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
}