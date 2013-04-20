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

package com.limecreativelabs.app.shared;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.limecreativelabs.app.MainActivity;
import com.limecreativelabs.app.R;

/**
 * Implements common usage in every Activity in the App
 */
public class BaseActivity extends SherlockActivity {

    public static final String EXTRA_TUTORIAL_URL = "BaseActivity:tutorialUrl";

    private String mTutorialUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set home as up when we are not on main activity
        if (!(this instanceof MainActivity)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTutorialUrl = getIntent().getStringExtra(EXTRA_TUTORIAL_URL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                if (this instanceof MainActivity) {
                    return false;
                }

                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_open_tutorial:
                if (mTutorialUrl != null && !mTutorialUrl.equals("")) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(mTutorialUrl));
                    startActivity(i);
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
