package org.yaledining.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyCustomBaseAdapter extends BaseAdapter {
    private static ArrayList<SearchResults> searchArrayList;
    private LayoutInflater mInflater;

    static class ViewHolder {
        TextView capacity;
        ImageView image;
        TextView name_restaurant;
        TextView txtName;

        ViewHolder() {
        }
    }

    public MyCustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
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
        View convertView2 = this.mInflater.inflate(C0233R.layout.custom_row_view, null);
        ViewHolder holder = new ViewHolder();
        holder.name_restaurant = (TextView) convertView2.findViewById(C0233R.C0234id.name_restaurant);
        holder.txtName = (TextView) convertView2.findViewById(C0233R.C0234id.name);
        holder.image = (ImageView) convertView2.findViewById(C0233R.C0234id.image);
        holder.capacity = (TextView) convertView2.findViewById(C0233R.C0234id.capacity);
        convertView2.setTag(holder);
        try {
            holder.txtName.setText(((SearchResults) searchArrayList.get(position)).getName());
            holder.name_restaurant.setText(((SearchResults) searchArrayList.get(position)).getName_restaurant());
            holder.name_restaurant.setTag(Integer.valueOf(((SearchResults) searchArrayList.get(position)).getLocation_id()));
            holder.image.setImageResource(((SearchResults) searchArrayList.get(position)).getImage());
            int i = ((SearchResults) searchArrayList.get(position)).getCapacity();
            if (i == 11) {
                holder.capacity.setVisibility(0);
                holder.capacity.setText("");
                holder.capacity.setBackgroundResource(17170445);
            } else if (i >= 0) {
                holder.capacity.setText("");
                holder.capacity.setVisibility(0);
                holder.capacity.setBackgroundResource(i);
                holder.capacity.setText("");
            } else {
                holder.capacity.setVisibility(0);
                holder.capacity.setText("  Closed    ");
                holder.capacity.setBackgroundResource(17170445);
            }
        } catch (Exception e) {
        }
        return convertView2;
    }
}
