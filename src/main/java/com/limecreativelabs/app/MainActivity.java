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
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mList;

    private LoadTask mTask;

    private static final String TUTORIAL_LIST = "tutorials.json";

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

    class LoadTask extends AsyncTask<Void, Void, List<Tutorial>> {

        @Override
        protected List<Tutorial> doInBackground(Void... voids) {
            return loadTutorials();
        }

        @Override
        protected void onPostExecute(List<Tutorial> tutorials) {

            if (!isCancelled() && tutorials != null && tutorials.size() > 0) {
                mList.setAdapter(new TutorialArrayAdapter(MainActivity.this, tutorials));
            }

            mTask = null;
        }

        /**
         * Open Json file and parse tutorials into clasess
         */
        private List<Tutorial> loadTutorials() {

            Type listType = new TypeToken<List<Tutorial>>() {
            }.getType();

            try {

                AssetManager assetManager = getAssets();
                InputStream inputStream;
                inputStream = assetManager.open(TUTORIAL_LIST);

                JsonReader reader = new JsonReader(new InputStreamReader(inputStream,
                        "UTF-8"));

                Gson gson = new Gson();
                List<Tutorial> target = gson.fromJson(reader, listType);

                return target;

            } catch (Exception e) {
            }

            return null;
        }
    }
}

