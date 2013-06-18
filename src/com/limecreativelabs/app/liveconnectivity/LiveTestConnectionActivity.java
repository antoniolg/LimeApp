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

package com.limecreativelabs.app.liveconnectivity;

import android.os.Bundle;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
import com.limecreativelabs.app.R;
import com.limecreativelabs.app.shared.BaseActivity;

public class LiveTestConnectionActivity extends BaseActivity implements ConnectivityObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_livetestconnection);

        LiveConnectivityManager.singleton(this).addObserver(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LiveConnectivityManager.singleton(this).addObserver(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.tutorial_standard, menu);
        return true;
    }

    //@Override
    public void manageNotification(boolean connectionEnabled) {

        TextView textView = (TextView) findViewById(R.id.text_status);

        if (connectionEnabled) {
            textView.setText(R.string.enabled);
            textView.setBackgroundColor(getResources().getColor(R.color.holo_dark_green));
        } else {
            textView.setText(R.string.disabled);
            textView.setBackgroundColor(getResources().getColor(R.color.holo_dark_red));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LiveConnectivityManager.singleton(this).removeObserver(this);
    }
}
