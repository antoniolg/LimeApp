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

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.limecreativelabs.app.actionbarrefresh.ActionBarRefreshActivity;
import com.limecreativelabs.app.shared.BaseActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView mList;

    private LoadTask mTask;

    /**
     * Tutorials file path
     */
    private static final String TUTORIAL_LIST = "tutorials.json";

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

        mList.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {

        if (mTask != null) {
            mTask.cancel(true);
        }

        super.onDestroy();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Class<?> c = MainActivity.class;

        switch (position) {
            case 1:
                c = ActionBarRefreshActivity.class;
                break;
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    /**
     * Load tutorials from a Json file in Assets
     */
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
         * Open Json file and parse tutorials into classes
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

                return gson.fromJson(reader, listType);

            } catch (Exception e) {
                Log.w(TAG, "Failed loading tutorials", e);
            }

            return null;
        }
    }
}

