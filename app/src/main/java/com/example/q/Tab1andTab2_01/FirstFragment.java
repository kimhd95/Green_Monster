package com.example.q.Tab1andTab2_01;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FirstFragment extends Fragment{
    public FirstFragment(){
    }

    private ListView listView;
    private  FirstFragmentListViewAdapter firstFragmentListViewAdapter;
    private ArrayList<FirstFragmentContactModel> firstFragmentContactModelArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_first,container,false);
        // setContentView : xml 파일을 View로 연결
        View view = inflater.inflate(R.layout.fragment_first,null);

        listView = (ListView)view.findViewById(R.id.listview1);

        firstFragmentContactModelArrayList = new ArrayList<>();

        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        while (phones.moveToNext())
        {
            // json으로 받기
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("Name", phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                jsonObject.put("Phone",phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

            } catch (JSONException e){
                e.printStackTrace();
            }

            // json 파싱
            FirstFragmentContactModel firstFragmentContactModel = new FirstFragmentContactModel();
            try{
                String name = (String)jsonObject.get("Name");
                String phoneNumber = (String)jsonObject.get("Phone");
                firstFragmentContactModel.setName(name);
                firstFragmentContactModel.setNumber(phoneNumber);
            } catch (JSONException e){
                e.printStackTrace();
            }
            firstFragmentContactModelArrayList.add(firstFragmentContactModel);
        }

        phones.close();


        firstFragmentListViewAdapter = new FirstFragmentListViewAdapter(getActivity(), firstFragmentContactModelArrayList);
        listView.setAdapter(firstFragmentListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), firstFragmentContactModelArrayList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

}
