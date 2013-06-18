/*
 * Copyright (C) 2013 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.limecreativelabs.app.liveconnectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage connectivity observers. It notifies observers when there is a connectivity status change
 */
public class LiveConnectivityManager {

    private static List<ConnectivityObserver> mObservers;

    private boolean mConnected;

    private final Context mContext;

    /**
     * Unique manager in application
     */
    private static LiveConnectivityManager mManager;

    /**
     * Implements singleton pattern
     *
     * @param context Execution context
     * @return Connectivity Manager
     */
    public static LiveConnectivityManager singleton(Context context) {

        if (mManager == null) {
            mManager = new LiveConnectivityManager(context);
        }

        return mManager;
    }

    /**
     * Class constructor
     */
    private LiveConnectivityManager(Context context) {
        mObservers = new ArrayList<ConnectivityObserver>();
        mContext = context;
        mConnected = isConnectionEnabled();

    }

    /**
     * Test if connection is enabled
     */
    boolean isConnectionEnabled() {

        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Adds observer to observers list
     */
    public void addObserver(ConnectivityObserver observer) {
        mObservers.add(observer);
        observer.manageNotification(mConnected);
    }

    /**
     * Remove observer form observers list
     */
    public void removeObserver(ConnectivityObserver observer) {
        mObservers.remove(observer);
    }

    /**
     * Called when there's a connectivity change.
     */
    void notifyConnectionChange() {

        mConnected = isConnectionEnabled();

        for (ConnectivityObserver observer : mObservers) {
            observer.manageNotification(mConnected);
        }
    }
}
