package com.okhlee.capp.CustomElements;

import android.app.Activity;
import android.os.Build;
import android.transition.Slide;



/**
 * Created by Harpreet on 19/07/2017.
 */

public class CustomTransition {

    public static void slideActivity(Activity activity)
    {
        if(Build.VERSION.SDK_INT>=21)
        {
            Slide slide=new Slide();
            slide.setDuration(500);
            activity.getWindow().setEnterTransition(slide);
        }
    }
}
