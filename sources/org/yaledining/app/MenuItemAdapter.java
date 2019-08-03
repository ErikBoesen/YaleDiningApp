package org.yaledining.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MenuItemAdapter extends BaseAdapter {
    Context context;
    private LayoutInflater mInflater;
    private ArrayList<SearchMenuItemData> searchArrayList;

    static class ViewHolder {
        TextView txtName;

        ViewHolder() {
        }
    }

    public MenuItemAdapter(Context context2, ArrayList<SearchMenuItemData> results) {
        this.context = context2;
        this.searchArrayList = results;
        this.mInflater = LayoutInflater.from(context2);
    }

    public int getCount() {
        return this.searchArrayList.size();
    }

    public Object getItem(int position) {
        return this.searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = this.mInflater.inflate(C0233R.layout.custom_row, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(C0233R.C0234id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (getCount() == 0) {
            holder.txtName.setText("No info available");
        } else {
            if (((SearchMenuItemData) this.searchArrayList.get(position)).getName().equalsIgnoreCase(SearchData.TRAITS_BOTTOM_TEXT)) {
                holder.txtName.setVisibility(8);
                convertView = this.mInflater.inflate(C0233R.layout.custom_row, null);
                holder = new ViewHolder();
                holder.txtName = (TextView) convertView.findViewById(C0233R.C0234id.bottom_name);
                holder.txtName.setVisibility(0);
                convertView.setTag(holder);
                holder.txtName.setTextSize(15.0f);
            }
            holder.txtName.setText(((SearchMenuItemData) this.searchArrayList.get(position)).getName());
        }
        return convertView;
    }
}
