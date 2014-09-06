package com.IHUNGRY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.graphics.RectF;
import android.util.Log;

public class Game extends Activity
{
	//Log variable
	private String TAG = "Game";
	public int count = 0;
	
	
	//graphic variables
	public Character character;
	public Bitmap backgroundBitmap, characterBitmap, foodBitmap[], appleBitmap, bananaBitmap, yogurtBitmap, milkBitmap, eggplantBitmap,
	pizzaBitmap, pieBitmap, cookieBitmap, sodaBitmap, drumstickBitmap, watermelonBitmap, waterbottleBitmap,
	strawberryBitmap, oliveBitmap, hamBitmap, friesBitmap, fishBitmap, doughnutBitmap, corndogBitmap, baconBitmap;
				
	int foodBottom[], foodLeft[], foodRight[];
	private float canvasHeight;
	private float canvasWidth;	
	public RectF backgroundRectF;
	public FoodItem foodItem[];
	
	//game manipulation variables
	private boolean inMouth = false;
	private int velocity = 4;
	boolean firstRun = true;
	boolean fourItems = false;
	boolean running = true;
	boolean lose = false;
	private int lives = 5; //lives
	private int counter = 0;
	
	//time variables
	public String asText;
	public Paint paint;
	
	
	//Declaration for score and life bar
	public static int score = 0;
	

    public void onCreate(Bundle savedInstanceState)
	{
		score = 0;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        
        //initialize all the graphics needed for the game
        
        //character
        character = new Character();
        characterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        
        //bitmap graphics
		backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back3);
		
		appleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fauapple);
		bananaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faubanana);
		yogurtBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fauyogurt);
		milkBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faumilk2);
		eggplantBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faueggplant);
		watermelonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fauwatermelon);
		waterbottleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fauwaterbottle);
		strawberryBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faustrawberry);
		oliveBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fauolive);
		fishBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faufish);

		pizzaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faupizza);
		pieBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faupie);
		cookieBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faucookie);
		sodaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fausoda);
		drumstickBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faudrumstick);
		hamBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fauham);
		friesBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faufries);
		doughnutBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faudoughnut);
		corndogBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faucorndog);
		baconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faubacon);

		//items
		foodItem = new FoodItem[4];
		for (int ta = 0; ta < 4; ta++)
		{
			foodItem[ta] = new FoodItem();
			foodItem[ta].setItem(this.canvasHeight, this.canvasWidth, character.width);
		}

		
		//additional variables
		asText = "t";
		paint = new Paint();
		paint.setColor(Color.BLACK);
		
		
		//send the drawing to the screen
		setContentView(new Panel(this));
    }
	
	class Panel extends SurfaceView implements SurfaceHolder.Callback
	{
		private GameThread _thread;
		public Panel(Context context)
		{
            super(context);
            getHolder().addCallback(this);
            _thread = new GameThread(getHolder(), this);
        }
		

        public void onDraw(Canvas canvas)
		{
        	if(firstRun)
        	{
				setDrawingObjects(canvas);
			}
        	
        	
        	//draw background
        	canvas.drawBitmap(backgroundBitmap, null, backgroundRectF, null);
        	
        	

        	for (int i = 0; i < 4; i++)
        	{
        		if (foodItem != null)
        	    {
        	        foodItem[i].updateItem(velocity);
        	        foodBitmap[i] = getCorrectBitmap(foodItem[i]);
        	  	}
            }
        	drawItem(foodItem, foodBitmap, canvas);
			
			//draw text on top of the screen
			canvas.drawText(Score(), (float)(canvasWidth*0.05), (float)(canvasHeight*0.05), paint);
			//draw character
			canvas.drawBitmap(characterBitmap, null, character.characterRectF, null);
            
            
            //go to scoring
            CheckMouth(canvas);
	    }
		
		public void drawItem(FoodItem[ ] foodItem, Bitmap[ ] itemBitmap, Canvas canvas)
		{
			for(int i=0; i<4; i++)
			{
				if ((foodItem[i] != null) && (itemBitmap[i] != null))
				{
					canvas.drawBitmap(itemBitmap[i], null, foodItem[i].itemRectF, null);
				}
			}
		}
		
		public Bitmap getCorrectBitmap(FoodItem newfoodItem)
		{
			Bitmap itemBitmap;
			switch(newfoodItem.itemNum){
			case 0:
				itemBitmap = appleBitmap;
				break;
			case 1:
				itemBitmap = bananaBitmap;
				break;
			case 2:
				itemBitmap = eggplantBitmap;
				break;
			case 3:
				itemBitmap = milkBitmap;
				break;
			case 4:
				itemBitmap = yogurtBitmap;
				break;
			case 10:
				itemBitmap = cookieBitmap;
				break;
			case 11:
				itemBitmap = pieBitmap;
				break;
			case 12:
				itemBitmap = sodaBitmap;
				break;
			case 13:
				itemBitmap = drumstickBitmap;
				break;
			case 14:
				itemBitmap = pizzaBitmap;
				break;
			case 5:
				itemBitmap = watermelonBitmap;
				break;
			case 6:
				itemBitmap = waterbottleBitmap;
				break;
			case 7:
				itemBitmap = strawberryBitmap;
				break;
			case 8:
				itemBitmap = oliveBitmap;
				break;
			case 9:
				itemBitmap = fishBitmap;
				break;
			case 15:
				itemBitmap = hamBitmap;
				break;
			case 16:
				itemBitmap = friesBitmap;
				break;
			case 17:
				itemBitmap = doughnutBitmap;
				break;
			case 18:
				itemBitmap = corndogBitmap;
				break;
			case 19:
				itemBitmap = baconBitmap;
				break;
			default:
				itemBitmap = appleBitmap;
			}
			return itemBitmap;
		}

		synchronized public void CheckMouth(Canvas canvas)
		{
			for(int i=0; i<4; i++)
			{
				boolean isBad = false;
				if ((foodItem[i] != null) && (foodBitmap[i] != null))
				{
 				foodBottom[i] = (int)(foodItem[i].yPosition + foodItem[i].height);
				foodRight[i] = (int)(foodItem[i].xPosition + foodItem[i].width);
				foodLeft[i] = (int)(foodItem[i].xPosition);
				if (((character.xPosition <= foodRight[i]) && ((character.xPosition + character.width) >= foodLeft[i])) && (character.yPosition <=foodBottom[i]) && ((character.yPosition+character.height) >= foodBottom[i]))
				{
					if (foodItem[i].itemNum > 9) isBad = true;
					else isBad = false;
					updateScore(canvas, foodItem[i]);
					foodItem[i].reset();
					inMouth = true;
				}
					
					//if hit == true then increment score, otherwise decrement
					if(inMouth)
					{
						if (isBad)
						{
							lives--;
							String msg = String.valueOf(lives);
							Log.d(TAG, msg);
							Sound.play(Game.this, R.raw.badeat);
							if (lives == 0)
							{
								running = false;
								lose = true;
							}
						}
						else
						{
							Sound.play(Game.this, R.raw.goodeat);
						}
						inMouth = false;
					}
				}
			}
		}
		
		public void setDrawingObjects(Canvas canvas)
		{
        	float characterSize = (float)(character.width/3);
        	
        	
			canvasHeight = canvas.getHeight();
			canvasWidth = canvas.getWidth(); 
			character.setSizePosition(canvasHeight, canvasWidth);
			backgroundRectF = new RectF(0, 0, canvasWidth, canvasHeight);
			for (int l = 0; l < 4; l++)
			{
			foodItem[l].setItem(canvasHeight, canvasWidth, characterSize);
			}
			foodBitmap = new Bitmap[4];
			foodBottom = new int[4];
			foodLeft = new int[4];
			foodRight = new int[4];
			
			
			//set the size of the text display
			paint.setTextSize((float)(canvasHeight*0.05));
			
			
			//reinitialize firstRun to avoid the program to call this function again
			firstRun = false;
		}

	public void updateScore(Canvas canvas, FoodItem item)
	{
		int addition = item.getValue();
		score+=addition;
		
		counter++;
		if(counter==10)
		{
			velocity++;
			counter = 0;
		}
	}
		


     
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
        { }
 
   
        public void surfaceCreated(SurfaceHolder holder)
        {
            _thread.setRunning(true);
            _thread.start();
        }
    
        public void surfaceDestroyed(SurfaceHolder holder)
        {
            // simply copied from sample application LunarLander:
            // we have to tell thread to shut down & wait for it to finish, or else
            // it might touch the Surface after we return and explode
            boolean retry = true;
            _thread.setRunning(false);
            while (retry)
            {
                try {
                    _thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                	Log.i(TAG,e.toString());
                    // we will try it again and again...
                }
            }
        }

	}
	
	class GameThread extends Thread
	{
        private SurfaceHolder _surfaceHolder;
        private Panel _panel;
 
        public GameThread(SurfaceHolder surfaceHolder, Panel panel)
        {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }
 
        public void setRunning(boolean run) {
            running = run;
        }
 
   
        public void run()
        {
            Canvas c;
            while (running) 
            {
                c = null;
                try
                {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        _panel.onDraw(c);
                    }
                } finally
                {
                	//finally is called with or without an exception
                    if (c != null)
                    {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
			if(lose)
			{
				finish();
				Intent i = new Intent(getBaseContext(), Lose.class); 
				i.putExtra("score", score);
				startActivity(i); 
			}
			else
			{
				finish();
				Intent j = new Intent(getBaseContext(), MainScreen.class);
				startActivity(j);
			}
            
        }
    }
	
	public String Score()
	{
		asText = "Lives: " + lives + "     ";
		asText += "Score: " + String.valueOf(score);
		return asText;
	}
	

	public boolean onTouchEvent(MotionEvent event)
	 { 
		if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			//get position from finger on DRAG for 
			character.xPosition = (event.getX()-character.width/2);
			character.setRectF();
		}
		return true;
	}



    public boolean onKeyDown(int keyCode, KeyEvent event)
    { 
    if (keyCode == KeyEvent.KEYCODE_BACK) {
    	finish();
        return true; 
    	} 
    return super.onKeyDown(keyCode, event); 
	}


    protected void onPause()
    {
    	super.onPause();
    	Music.stop(getBaseContext());
    }

    protected void onResume()
    {
    	super.onResume();
    	Music.play(getBaseContext(), R.raw.gamemu);
    }
}
