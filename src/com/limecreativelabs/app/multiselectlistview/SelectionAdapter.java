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

package com.limecreativelabs.app.multiselectlistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.limecreativelabs.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * String array based Adapter that allows multiple selection in a list. It saves currently selected items
 * and allows to add, remove or get selected items
 *
 * @author Antonio Leiva Gordillo
 */
class SelectionAdapter extends ArrayAdapter<String>
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, ActionMode.Callback {

    /**
     * Selected items in the list
     */
    private ArrayList<Integer> mSelection = new ArrayList<Integer>();

    /**
     * We need the context activity to launch the modal action bar
     */
    private final SherlockActivity mActivity;

    private ActionMode mMode;

    /**
     * Saves the original listView click listener
     */
    private AdapterView.OnItemClickListener mItemClickListener;

    private final AdapterView mView;

    /**
     * Class constructor
     *
     * @param context            Execution context
     * @param resource           list item layout
     * @param textViewResourceId TextView identifier
     * @param objects            Array of list elements
     * @param list               ListView attached to the adapter
     */
    public SelectionAdapter(Context context, int resource,
                            int textViewResourceId, List<String> objects, AdapterView list) {
        super(context, resource, textViewResourceId, objects);

        mView = list;
        mView.setOnItemLongClickListener(this);

        mActivity = (SherlockActivity) context;
    }

    /**
     * Adds an element in selection and updates the view
     *
     * @param position Item position
     */
    void setNewSelection(int position) {
        mSelection.add(position);
        notifyDataSetChanged();
    }

    /**
     * Remove an element from selected items
     *
     * @param position Item position
     */
    void removeSelection(int position) {
        mSelection.remove(Integer.valueOf(position));
        notifyDataSetChanged();
    }

    /**
     * Clear current selection
     */
    void clearSelection() {
        mSelection = new ArrayList<Integer>();
        notifyDataSetChanged();
    }

    /**
     * Get number of selected items
     *
     * @return Selection count
     */
    int getSelectionCount() {
        return mSelection.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        v.setBackgroundColor(getContext().getResources().getColor(
                android.R.color.transparent)); // Default color

        if (mSelection.contains(position)) {
            v.setBackgroundColor(getContext().getResources().getColor(
                    android.R.color.tab_indicator_text)); // color when selected
        }

        return v;
    }

    boolean isChecked(int position) {
        return mSelection.contains(position);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // CAB is initialized
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.multiselect_cab, menu);

        // When CAB is created, original listener is saved, and this adapters begins to handle the action
        mItemClickListener = mView.getOnItemClickListener();
        mView.setOnItemClickListener(this);

        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        // CAB menu options
        switch (item.getItemId()) {
            case R.id.action_discard:
                Toast.makeText(getContext(),
                        getSelectionCount() + " " + mActivity.getString(R.string.items_deleted),
                        Toast.LENGTH_LONG).show();
                clearSelection();
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        clearSelection();

        mView.setOnItemClickListener(mItemClickListener);
        mMode = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mMode != null) {
            // If element is checked, it is added to selection; if not, it's
            // deleted
            if (isChecked(position)) {
                removeSelection(position);
            } else {
                setNewSelection(position);
            }

            mMode.setTitle(getSelectionCount() + " " + mActivity.getString(R.string.items_selected));
            return;
        }

        // If CAB not active, we use original click listener
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(parent, view, position, id);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        // CAB activation. It launches onItemClick to mark the item as selected
        if (mMode == null) {
            mMode = mActivity.startActionMode(this);
            onItemClick(parent, view, position, id);
            return true;
        }

        return false;
    }
}
