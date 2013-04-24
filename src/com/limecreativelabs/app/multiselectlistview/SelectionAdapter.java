package com.limecreativelabs.app.multiselectlistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
 *
 * String array based Adapter that allows multiple selection in a list. It saves currently selected items
 * and allows to add, remove or get selected items
 *
 * @author Antonio Leiva Gordillo
 *
 */
public class SelectionAdapter extends ArrayAdapter<String>
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, ActionMode.Callback {

    /** Selected items in the list */
    private ArrayList<Integer> mSelection = new ArrayList<Integer>();

    private ListView mList;

    private SherlockActivity mActivity;

    private ActionMode mMode;

    /**
     * Class constructor
     * @param context Execution context
     * @param resource list item layout
     * @param textViewResourceId TextView identifier
     * @param objects Array of list elements
     */
    public SelectionAdapter(SherlockActivity context, int resource,
                            int textViewResourceId, List<String> objects, ListView list) {
        super(context, resource, textViewResourceId, objects);

        mList = list;
        mList.setAdapter(this);
        mList.setOnItemLongClickListener(this);
        mList.setOnItemClickListener(this);

        mActivity = context;
    }

    /**
     * Adds an element in selection and updates the view
     * @param position Item position
     */
    public void setNewSelection(int position) {
        mSelection.add(position);
        notifyDataSetChanged();
    }

    /**
     * Remove an element from selected items
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

    public boolean isChecked(int position) {
        return mSelection.contains(position);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // CAB is initialized
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.multiselect_cab, menu);

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
                        getSelectionCount() + " items deleted",
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
        mMode = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mMode != null){
            // If element is checked, it is added to selection; if not, it's
            // deleted
            if (isChecked(position)) {
                removeSelection(position);
            } else {
                setNewSelection(position);
            }

            mMode.setTitle(getSelectionCount() + " items selected");
            return;
        }

        Toast.makeText(getContext(), position + " " + getContext().getString(R.string.clicked), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        if (mMode == null){
            mMode = mActivity.startActionMode(this);
            onItemClick(parent, view, position, id);
            return true;
        }

        return false;
    }
}
