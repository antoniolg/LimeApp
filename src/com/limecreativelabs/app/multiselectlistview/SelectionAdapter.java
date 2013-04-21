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

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * String array based Adapter that allows multiple selection in a list. It saves currently selected items
 * and allows to add, remove or get selected items
 *
 * @author Antonio Leiva Gordillo
 */
public class SelectionAdapter extends ArrayAdapter<String> {

    /**
     * Selected items in the list
     */
    private ArrayList<Integer> mSelection = new ArrayList<Integer>();

    /**
     * Class construactor
     *
     * @param context            Execution context
     * @param resource           list item layout
     * @param textViewResourceId TextView identifier
     * @param objects            Array of list elements
     */
    public SelectionAdapter(Context context, int resource,
                            int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    /**
     * Adds an element in selection and updates the view
     *
     * @param position Item position
     */
    public void setNewSelection(int position) {
        mSelection.add(position);
        notifyDataSetChanged();
    }

    /**
     * Get current selected items
     *
     * @return list of items
     */
    public ArrayList<Integer> getCurrentCheckedPosition() {
        return mSelection;
    }

    /**
     * Remove an element from selected items
     *
     * @param position Item position
     */
    public void removeSelection(int position) {
        mSelection.remove(Integer.valueOf(position));
        notifyDataSetChanged();
    }

    /**
     * Clear current selection
     */
    public void clearSelection() {
        mSelection = new ArrayList<Integer>();
        notifyDataSetChanged();
    }

    /**
     * Get number of selected items
     *
     * @return Selection count
     */
    public int getSelectionCount() {
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
}