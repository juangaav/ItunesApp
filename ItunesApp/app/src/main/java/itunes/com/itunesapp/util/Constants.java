package itunes.com.itunesapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;

/**
 * Created by @jvillafane on 4/11/15.
 */
public class Constants {

    public static int getScreenOrientation(Activity activity)
    {
        Display getOrient = activity.getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }

}
