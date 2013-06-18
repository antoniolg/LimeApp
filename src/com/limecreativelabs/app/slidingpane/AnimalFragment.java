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

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragment;
import com.limecreativelabs.app.R;

/**
 * Test fragment
 * User: Antonio
 * Date: 18/06/13
 * Time: 18:05
 */
public class AnimalFragment extends SherlockFragment {


    private static final String ARG_ID = "AnimalFragment:id";
    private static TypedArray mImageDrawables;
    private ImageView mImage;

    public static Fragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);

        Fragment fragment = new AnimalFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_animal, container, false);
        mImage = (ImageView) view.findViewById(R.id.animalImage);

        final Bundle args = getArguments();

        if (args != null) {
            int position = args.getInt(ARG_ID);
            mImage.setImageResource(getImageDrawable(getActivity(), position));
        }

        return view;
    }

    private static int getImageDrawable(Context context, int position) {

        if (mImageDrawables == null) {
            mImageDrawables = context.getResources().obtainTypedArray(R.array.animalImages);

        }

        return mImageDrawables.getResourceId(position, -1);
    }
}
