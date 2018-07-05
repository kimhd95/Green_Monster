package com.example.q.Tab1andTab2_01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class FirstFragmentListViewAdapter extends BaseAdapter{
    private Context context;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<FirstFragmentContactModel> contactModelArrayList;
    private int test_int = 123;
    private final static String SpecialNumber = "010-0000-0000";

    // ListViewAdapter의 생성자
    public FirstFragmentListViewAdapter(Context context, ArrayList<FirstFragmentContactModel> contactModelArrayList) {
        this.context = context;
        this.contactModelArrayList = contactModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) { return position; }
    @Override
    public int getCount() {
        return contactModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ArrayList<String> korean;
        String FirstLetter = contactModelArrayList.get(position).getName().substring(0,1);
        // Log.d("Test02", "position 0 is "+contactModelArrayList.get(0).getName());

        if(contactModelArrayList.get(position).getNumber().equals(SpecialNumber))
        {
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_first_korean_letter, null, true);
                holder.tvFirstLetter = (TextView) convertView.findViewById(R.id.textView0);
                //holder.tvname = (TextView) convertView.findViewById(R.id.textView0);
                //holder.tvnumber = (TextView) convertView.findViewById(R.id.textView0);
                convertView.setTag(holder);
            }else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder)convertView.getTag();
            }

            //holder.tvname.setText(contactModelArrayList.get(position).getName());
            //holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());
            holder.tvFirstLetter.setText(getInitialSound(contactModelArrayList.get(position).getName()));

            return convertView;
        }




        // Log.d("TestTag","int is "+test_int);
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_first_listview_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.textView1);
            holder.tvnumber = (TextView) convertView.findViewById(R.id.textView2);
            holder.tvFirstLetter = (TextView) convertView.findViewById(R.id.textView0);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText(contactModelArrayList.get(position).getName());
        Log.d("Test","what is name? Adapter " + contactModelArrayList.get(position).getName());
        Log.d("Test","what is name? Adapter " + contactModelArrayList.get(position).getName().charAt(0));
        holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());
        //holder.tvFirstLetter.setText(contactModelArrayList.get(position).getName().charAt(0));
        holder.tvFirstLetter.setText(contactModelArrayList.get(position).getName().substring(0,1));

        return convertView;


    }

    private class ViewHolder {
        protected TextView tvname, tvnumber, tvFirstLetter;
    }

    private String getInitialSound(String text) {
        String[] chs = {
                "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        };

        if(text.length() > 0) {
            char chName = text.charAt(0);
            if(chName >= 0xAC00)
            {
                int uniVal = chName - 0xAC00;
                int cho = ((uniVal - (uniVal % 28))/28)/21;
                return chs[cho];
            }
        }
        return null;
    }

}
