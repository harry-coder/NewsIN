package com.okhlee.capp.CustomElements;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Harpreet on 18/07/2017.
 */

public class CustomToast {

    public static void showSnackBar(String subject,View view)
    {
        Snackbar snackbar = Snackbar
                .make(view, subject, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
    public static void showToast(String subject,Context context)
    {
        Toast toast= Toast.makeText(context,
                subject, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}
