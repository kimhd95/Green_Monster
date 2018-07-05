package com.example.q.Tab1andTab2_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ContentModel> contentModelArrayList;

    public ListViewAdapter(Context context, ArrayList<ContentModel> contentModelArrayList) {
        this.context = context;
        this.contentModelArrayList = contentModelArrayList;
    }

    @Override
    public int getCount() {
        return contentModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentModelArrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (contentModelArrayList.get(position).getDescription() == "@@DATE@@") {
            convertView = inflater.inflate(R.layout.date_listview, null);
            holder.tvtime = (TextView) convertView.findViewById(R.id.Line1);
            holder.tvdesc = (TextView) convertView.findViewById(R.id.Date);
            holder.tvsum = (TextView) convertView.findViewById(R.id.Line2);
            convertView.setTag(holder);
            String y = Integer.toString(contentModelArrayList.get(position).getYear());
            String m = Integer.toString(contentModelArrayList.get(position).getMonth());
            String d = Integer.toString(contentModelArrayList.get(position).getDay());
            if (d.length() == 1)
                d = "0" + d;
            if (m.length() == 1)
                m = "0" + m;
            holder.tvtime.setText("");
            holder.tvdesc.setText(y + "년 " + m + "월 " + d + "일");
            holder.tvsum.setText("");
        }
        else {
            convertView = inflater.inflate(R.layout.item_listview, null);
            holder.tvtime = (TextView) convertView.findViewById(R.id.time_TEXTVIEW);
            holder.tvdesc = (TextView) convertView.findViewById(R.id.description_TEXTVIEW);
            holder.tvsum = (TextView) convertView.findViewById(R.id.sum_TEXTVIEW);
            convertView.setTag(holder);
            String h = Integer.toString(contentModelArrayList.get(position).getHour());
            String m = Integer.toString(contentModelArrayList.get(position).getMinute());
            if (h.length() == 1)
                h = "0" + h;
            if (m.length() == 1)
                m = "0" + m;
            holder.tvtime.setText(h + ":" + m);
            holder.tvdesc.setText(contentModelArrayList.get(position).getDescription());
            int s = Integer.parseInt(contentModelArrayList.get(position).getSum());
            holder.tvsum.setText("￦ " + String.format("%,d", s));
        }
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvtime, tvdesc, tvsum;
    }
}