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

package com.limecreativelabs.app.multiselectlistview;

import android.os.Bundle;
import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
import com.limecreativelabs.app.R;
import com.limecreativelabs.app.shared.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Exampled based on tutorial:
 * http://www.limecreativelabs.com/seleccion-multiple-listview-contextual-action-bar/
 * <p/>
 * Allows the user to select several list items and perform a joint action
 */
public class MultiSelectActivity extends BaseActivity {

    /**
     * MultiSelect list adapter
     */
    private SelectionAdapter mAdapter;

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multiselect);

        List<String> items = new ArrayList<String>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        items.add("Item 5");
        items.add("Item 6");

        mAdapter = new SelectionAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);

        if (mList == null) {
            mList = (ListView) findViewById(android.R.id.list);
        }

        mList.setAdapter(mAdapter);

        setupActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.multiselect, menu);
        return true;
    }

    private void setupActionBar() {
        mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        /*mList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {

                // If element is checked, it is added to selection; if not, it's
                // deleted
                if (checked) {
                    mAdapter.setNewSelection(position);
                } else {
                    mAdapter.removeSelection(position);
                }

                mode.setTitle(mAdapter.getSelectionCount() + " items selected");
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // CAB menu options
                switch (item.getItemId()) {
                    case R.id.action_discard:
                        Toast.makeText(MultiSelectActivity.this,
                                mAdapter.getSelectionCount() + getString(R.string.items_deleted),
                                Toast.LENGTH_LONG).show();
                        mAdapter.clearSelection();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                // CAB is initialized
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.multiselect_cab, menu);

                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mAdapter.clearSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                return false;
            }
        });*/
    }
}
