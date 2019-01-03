package br.com.pcsist.mobile.restfood.ui.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;

/**
 * Created by bruno.andrade on 07/02/2017.
 */

public class ActivityUtils {

    public static void showSnackbarMessage(Activity activity, String message) {
        Snackbar.make(activity.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG).show();
    }
}
