package com.IHUNGRY;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound
{
private static MediaPlayer mp = null;
public static boolean shouldPlay = true;

	/**Stop old song and start new one. */
	public static void play(Context context, int resource)
	{
		if (shouldPlay)
		{
			stop(context);
			mp = MediaPlayer.create(context, resource);
			mp.start();
		}
	}
	
	/**Stop the music. */
	public static void stop(Context context)
	{
		if (mp != null)
		{
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}
