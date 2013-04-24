package com.limecreativelabs.app.multiselectlistview;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
import com.limecreativelabs.app.R;
import com.limecreativelabs.app.shared.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectActivity extends BaseActivity {

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

        mList = (ListView) findViewById(android.R.id.list);

        BaseAdapter adapter = new SelectionAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items, mList);

        mList.setAdapter(adapter);

        setupActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.multiselect, menu);
        return true;
    }

    private void setupActionBar() {
        mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mList.setItemsCanFocus(false);
    }
}