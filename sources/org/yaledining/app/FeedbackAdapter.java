package org.yaledining.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class FeedbackAdapter extends BaseAdapter {
    private static ArrayList<String> list;
    String checked;
    private LayoutInflater mInflater;

    class Row {
        CheckBox check;
        TextView txtName;

        Row() {
        }
    }

    public FeedbackAdapter(Context context, ArrayList<String> list2, String checked2) {
        list = list2;
        this.mInflater = LayoutInflater.from(context);
        this.checked = checked2;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Row holder;
        if (convertView == null) {
            convertView = this.mInflater.inflate(C0233R.layout.custom_row, null);
            holder = new Row();
            holder.txtName = (TextView) convertView.findViewById(C0233R.C0234id.name);
            convertView.setTag(holder);
        } else {
            holder = (Row) convertView.getTag();
        }
        holder.txtName.setText(((String) list.get(position)).toString());
        if (this.checked.equals(holder.txtName.getText())) {
            holder.check = (CheckBox) convertView.findViewById(C0233R.C0234id.check);
            holder.check.setVisibility(0);
            holder.check.setChecked(true);
        }
        return convertView;
    }
}
