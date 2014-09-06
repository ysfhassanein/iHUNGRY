package com.IHUNGRY;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Lose extends Activity implements OnClickListener
{
	
	protected void onCreate(Bundle savedInstanceData)
	{
		super.onCreate(savedInstanceData);
		setContentView(R.layout.lose);
		 
		String score = "Final Score: " + String.valueOf(getIntent().getIntExtra("score", 0));
		TextView score_button = (TextView) findViewById(R.id.Button_Score);
		score_button.setText(score, null);
		score_button.setTextColor(R.color.white);
		score_button.setTextSize(16);
		score_button.setGravity(Gravity.CENTER_HORIZONTAL);
		View start = findViewById(R.id.return_button);
        start.setOnClickListener(this);
	}
	
	public void onClick(View v)
    {
    	switch(v.getId())
    	{
    	case R.id.Button_Return:
    		Intent i = new Intent(this, Game.class);
    		startActivity(i);
    		break;
    	}
    }

    protected void onResume()
    {
    	super.onResume();
    	Sound.play(this, R.raw.gameovermu);
    }
   
    protected void onPause()
    {
    	super.onPause();
    	Sound.stop(this);
    }
}
