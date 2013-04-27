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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Array Adapter that manages Android tutorials in Main ListView
 */
class TutorialArrayAdapter extends ArrayAdapter<Tutorial> {

    private final ArrayList<Tutorial> mTutorials;

    public TutorialArrayAdapter(Context context, ArrayList<Tutorial> objects) {
        super(context, R.layout.tutorial_list_item, objects);

        mTutorials = objects;
    }

    public ArrayList<Tutorial> getTutorials() {
        return mTutorials;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.tutorial_list_item, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.date = (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        holder.date.setText(getItem(position).getDate());

        return convertView;
    }

    private class ViewHolder {

        public TextView name;

        public TextView date;
    }
}