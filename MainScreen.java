package com.IHUNGRY;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainScreen extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        View start = findViewById(R.id.start_button);
        start.setOnClickListener(this);
        View settings = findViewById(R.id.settings_button);
        settings.setOnClickListener(this);
        View credits = findViewById(R.id.credits_button);
        credits.setOnClickListener(this);
    }
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    	case R.id.start_button:
    		Intent i = new Intent(this, Story.class);
    		startActivity(i);
    		break;
    	case R.id.settings_button:
    		Intent l = new Intent(this, Settings.class);
    		startActivity(l);
    		break;
    	case R.id.credits_button:
    		Intent q = new Intent(this, Credits.class);
    		startActivity(q);
    		break;
    	}
    }

    protected void onResume()
    {
    	super.onResume();
    	Music.play(this, R.raw.mainmu);
    }

    protected void onPause()
    {
    	super.onPause();
    	Music.stop(this);
    }
}
