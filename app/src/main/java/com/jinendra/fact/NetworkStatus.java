package com.jinendra.fact;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {

    public static boolean isNetworkStatusAvailable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

}
