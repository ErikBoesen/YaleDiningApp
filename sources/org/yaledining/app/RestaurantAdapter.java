package org.yaledining.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class RestaurantAdapter extends BaseAdapter {
    private static ArrayList<SearchMenuData> searchArrayList;
    private LayoutInflater mInflater;

    static class ViewHolder {
        ImageView image;
        TextView txtName;

        ViewHolder() {
        }
    }

    public RestaurantAdapter(Context context, ArrayList<SearchMenuData> results) {
        searchArrayList = results;
        this.mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
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
            holder.image = (ImageView) convertView.findViewById(C0233R.C0234id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(((SearchMenuData) searchArrayList.get(position)).getName());
        holder.txtName.setTag(Integer.valueOf(((SearchMenuData) searchArrayList.get(position)).getMenuid()));
        holder.image.setBackgroundResource(((SearchMenuData) searchArrayList.get(position)).getImage());
        return convertView;
    }
}
