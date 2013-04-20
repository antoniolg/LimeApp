/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.limecreativelabs.app.actionbarrefresh;

import android.os.AsyncTask;
import android.util.Log;

/**
 * A sample class used to simulate a loading task that takes 3 seconds to be finished.
 */
public class MockRefreshTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = MockRefreshTask.class.getSimpleName();
    private IRefresh refreshImpl;

    public MockRefreshTask(IRefresh ref) {
        refreshImpl = ref;
    }

    @Override
    protected void onPreExecute() {
        refreshImpl.setRefresh(true);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.w(TAG, "Thread.sleep exception", e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        refreshImpl.setRefresh(false);
    }
}