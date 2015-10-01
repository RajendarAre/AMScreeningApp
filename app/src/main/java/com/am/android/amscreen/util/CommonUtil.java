package com.am.android.amscreen.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Rajendar Are on 9/30/15.
 */
public class CommonUtil {

    private static WeakReference<ProgressDialog> mProgressDialog;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = connectivityManager.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        return i.isAvailable();
    }


    public static void startProgressDialog(final Context context, int stringId) {
        startProgressDialog(context, context.getResources().getString(stringId));
    }

    public static void startProgressDialog(final Context context, String message) {
        stopProgressDialog();
        mProgressDialog = new WeakReference<>(ProgressDialog.show(context, "", message, true));
    }

    public static void stopProgressDialog() {
        try {
            if (null != mProgressDialog) {
                if (null != mProgressDialog.get() && mProgressDialog.get().isShowing()) {
                    mProgressDialog.get().dismiss();
                }
            }
        } catch (IllegalArgumentException e) {
            Log.e("CommonUtils", "Exception while dismissing progress dialog of detached fragment");
        }
    }
}
