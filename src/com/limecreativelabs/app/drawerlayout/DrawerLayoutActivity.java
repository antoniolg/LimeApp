package com.limecreativelabs.app.drawerlayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.limecreativelabs.abssupport.ActionBarDrawerToggle;
import com.limecreativelabs.app.R;
import com.limecreativelabs.app.shared.BaseActivity;

public class DrawerLayoutActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout mDrawer;
    private ListView mDrawerOptions;
    private ActionBarDrawerToggle mToggle;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerOptions = (ListView) findViewById(R.id.left_drawer);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerOptions.setAdapter(ArrayAdapter.createFromResource(this, R.array.drawer, android.R.layout.simple_list_item_1));
        mDrawerOptions.setOnItemClickListener(this);

        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(R.string.drawer_layout);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.drawer_open);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mToggle.setDrawerIndicatorEnabled(true);

        mDrawer.setDrawerListener(mToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.tutorial_standard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()){
            case android.R.id.home:
                if (mDrawer.isDrawerOpen(mDrawerOptions)){
                    mDrawer.closeDrawers();
                }else{
                    mDrawer.openDrawer(mDrawerOptions);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv = (TextView) view;
        Toast.makeText(this, getString(R.string.pressed) + " " + tv.getText(), Toast.LENGTH_SHORT).show();
        mDrawer.closeDrawers();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }
}