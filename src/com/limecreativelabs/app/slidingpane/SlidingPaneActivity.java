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

package com.limecreativelabs.app.slidingpane;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.view.MenuItem;
import com.limecreativelabs.app.R;
import com.limecreativelabs.app.shared.BaseActivity;

public class SlidingPaneActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    /**
     * Size of the parallax performed when hidden pane is opened or closed
     */
    private static final int PARALLAX_SIZE = 30;

    private SlidingPaneLayout mPanes;

    /**
     * Activity title
     */
    private CharSequence mTitle;

    /**
     * Current title depending on the selected animal
     */
    private CharSequence mCurrentTitle;

    /**
     * Items for ListView in sliding pane
     */
    private String[] mListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sliding_pane);
        mTitle = mCurrentTitle = getTitle();

        // SlidingPaneLayout customization
        mPanes = (SlidingPaneLayout) findViewById(R.id.slidingPane);
        mPanes.setParallaxDistance(PARALLAX_SIZE);
        mPanes.setShadowResource(R.drawable.sliding_pane_shadow);
        mPanes.openPane();

        // ListView initialization
        mListItems = getResources().getStringArray(R.array.animals);
        ListView list = (ListView) findViewById(R.id.animalList);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mListItems));
        list.setOnItemClickListener(this);

        // Sets first item selected by default
        selectItem(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mPanes.isOpen()) {
                    closePane();
                } else {
                    openPane();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPane() {
        mPanes.openPane();
        getSupportActionBar().setTitle(mTitle);
    }

    private void closePane() {
        mPanes.closePane();
        getSupportActionBar().setTitle(mCurrentTitle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {
        mCurrentTitle = mListItems[position];
        closePane();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, AnimalFragment.newInstance(position))
                .commit();
    }

}
