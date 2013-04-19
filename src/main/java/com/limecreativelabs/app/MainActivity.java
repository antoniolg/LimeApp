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

package com.limecreativelabs.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mList;

    private LoadTask mTask;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        if (mList == null) {
            mList = (ListView) findViewById(android.R.id.list);
        }

        if (savedInstanceState != null) {
            mTask = new LoadTask();
            mTask.execute();
        }
    }

    @Override
    protected void onDestroy() {

        if (mTask != null) {
            mTask.cancel(true);
        }

        super.onDestroy();
    }

    class LoadTask extends AsyncTask<Void, Void, Tutorial[]> {

        @Override
        protected Tutorial[] doInBackground(Void... voids) {
            return new Tutorial[0];  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        protected void onPostExecute(Tutorial[] tutorials) {

            if (!isCancelled() && tutorials != null && tutorials.length > 0) {
                loadTutorials();
            }

            mTask = null;
        }

        private void loadTutorials() {
        }
    }
}

