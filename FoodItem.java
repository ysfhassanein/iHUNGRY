package com.IHUNGRY;

import java.util.Random;

import android.graphics.RectF;
import android.util.Log;

public class FoodItem
{
	protected int itemNum;
	protected Random generator;
	protected float yPosition = 1;
	protected float xPosition;
	protected float width;
	protected float height;
	protected RectF itemRectF;
	protected int speed;
	protected float canvasHeight = 0;
	protected float canvasWidth = 0;
	protected float increase_size;
	protected float characterSize;

	private int value = 0;
	
	private String TAG = "FoodItem";
	
	public FoodItem()
	{
		Log.i(TAG, "here");
		generator = new Random();
		getRandItem();
		xPosition = generator.nextInt(400);
		speed = 10;
		itemRectF = new RectF(xPosition, yPosition, xPosition+width, yPosition+height);
	}
	
	public void getRandItem()
	{
		width = 50;
		height = 50;
		itemNum = generator.nextInt(20);
		int n = itemNum;
		switch(n)
		{
		case 0:
			value = 10;
			break;
		case 1:
			value = 15;
			break;
		case 2:
			value = 20;
			break;
		case 3:
			value = 25;
			break;
		case 4:
			value = 50;
			break;
		case 5:
			value = 10;
			break;
		case 6:
			value = 15;
			break;
		case 7:
			value = 20;
			break;
		case 8:
			value = 25;
			break;
		case 9:
			value = 50;
			break;
		case 10:
			value = -10;
			break;
		case 11:
			value = -15;
			break;
		case 12:
			value = -20;
			break;
		case 13:
			value = -25;
			break;
		case 14:
			value = -50;
			break;
		case 15:
			value = -10;
			break;
		case 16:
			value = -15;
			break;
		case 17:
			value = -20;
			break;
		case 18:
			value = -25;
			break;
		case 19:
			value = -50;
			break;
		default:
			value = 0;
		}
	}
	
	public void getRandXPosition()
	{
		float margin;
		
		margin = (float)(((canvasWidth*0.1)+characterSize)/2);
		
		//get xPosition that's only within the margin
		do
		{
			xPosition = (float) (generator.nextInt((int)(canvasWidth-margin)));
		}while(xPosition < margin);
		
	}
	
	public void setItem(float screenHeight, float screenWidth, float csize)
	{
		canvasHeight = screenHeight;
		canvasWidth = screenWidth;
		characterSize = csize;
	}
	
	public void updateItem(int velocity)
	{
		if (xPosition == -1){
			xPosition = (float) (generator.nextInt((int) canvasWidth) ); 
		}
		if (yPosition > canvasHeight)
		{ // if item gets out of the canvas, resets item number and position
			reset();
		}
		else
		{ // if item is within the screen, gets new rectangle for item
			speed = velocity;
			
			itemRectF.set(xPosition, yPosition, xPosition+width, yPosition+height);
			
			yPosition+=(float) speed;
		}		
	}

	
	public void reset()
	{
		yPosition = generator.nextInt(50) * -1;
		getRandItem();
		getRandXPosition();
		this.setValue(this.itemNum);
	}


	public void setValue(int p)
	{
		switch(p)
		{
		case 0:
			value = 10;
			break;
		case 1:
			value = 15;
			break;
		case 2:
			value = 20;
			break;
		case 3:
			value = 25;
			break;
		case 4:
			value = 50;
			break;
		case 5:
			value = 10;
			break;
		case 6:
			value = 15;
			break;
		case 7:
			value = 20;
			break;
		case 8:
			value = 25;
			break;
		case 9:
			value = 50;
			break;
		case 10:
			value = -10;
			break;
		case 11:
			value = -15;
			break;
		case 12:
			value = -20;
			break;
		case 13:
			value = -25;
			break;
		case 14:
			value = -50;
			break;
		case 15:
			value = -10;
			break;
		case 16:
			value = -15;
			break;
		case 17:
			value = -20;
			break;
		case 18:
			value = -25;
			break;
		case 19:
			value = -50;
			break;
		default:
			value = 0;
		}
	}
	public int getValue()
	{
		return value;
	}
}
