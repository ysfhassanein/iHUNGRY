package com.IHUNGRY;

import android.graphics.RectF;

public class Character
{
	protected float xPosition;
	protected float yPosition;
	protected float height;
	protected float width;
	public RectF characterRectF;

	public Character()
	{
		height = 40;
		width = 150;
		xPosition = 0;
		yPosition = 0;
		characterRectF = new RectF(xPosition,yPosition ,xPosition+width ,yPosition+height);
	}
	
	public void setRectF()
	{
		characterRectF.set(xPosition, yPosition , xPosition+width , yPosition+height);
	}

	public void setSizePosition(float canvasHeight, float canvasWidth)
	{
		canvasHeight -= (float) (canvasHeight*5/100); //added new line to lift the bin up
		height = (float) (canvasHeight*1/6); //changed the value to make the bin bigger, finger covering it
		width = (float) (canvasWidth*1/4); //changed value to keep the ratio
		yPosition = canvasHeight - height;
		setRectF();
	}

}
