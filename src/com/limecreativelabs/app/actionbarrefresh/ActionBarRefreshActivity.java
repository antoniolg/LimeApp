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

package com.limecreativelabs.app.actionbarrefresh;

import android.os.Bundle;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.limecreativelabs.app.R;
import com.limecreativelabs.app.shared.BaseActivity;

/**
 * Exampled based on tutorial:
 * http://www.limecreativelabs.com/progressbar-actionbar-refrescar-vista/
 * <p/>
 * It shows an indeterminate ProgressBar when the user clicks on Refresh button in ActionBar
 * until the view is reloaded.
 */
public class ActionBarRefreshActivity extends BaseActivity implements IRefresh {

    private MockRefreshTask mTask;

    private MenuItem mRefreshMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actionbar_refresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.actionbar_refresh, menu);

        mRefreshMenuItem = menu.findItem(R.id.action_refresh);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                mTask = new MockRefreshTask(this);
                mTask.execute();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setRefresh(boolean refresh) {

        if (refresh) {
            mRefreshMenuItem.setActionView(R.layout.actionbar_indeterminate_progress);
        } else {
            mRefreshMenuItem.setActionView(null);
            mTask = null;
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (mTask != null) {
            mTask.cancel(true);
        }
    }
}