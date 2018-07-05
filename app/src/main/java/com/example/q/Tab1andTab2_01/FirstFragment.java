package com.example.q.Tab1andTab2_01;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment{
    public FirstFragment(){
    }

    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private ImageButton btnScrollTop;

    private  FirstFragmentListViewAdapter firstFragmentListViewAdapter;
    private ArrayList<FirstFragmentContactModel> firstFragmentContactModelArrayList;
    private final static String SpecialNumber = "010-0000-0000";
    PopupMenu pm;


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
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);


        firstFragmentContactModelArrayList = new ArrayList<>();

        final Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");



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

            if(firstFragmentContactModelArrayList.size() > 1){
                String prev = getInitialSound(firstFragmentContactModelArrayList.get(firstFragmentContactModelArrayList.size()-2).getName());
                String curr = getInitialSound(firstFragmentContactModelArrayList.get(firstFragmentContactModelArrayList.size()-1).getName());
                Log.d("Test-1", "prev : "+prev);
                Log.d("Test-2","curr : "+curr);

                if(!prev.equals(curr)){
                    Log.d("Test-3", "this spot!");


                    FirstFragmentContactModel korean_character = new FirstFragmentContactModel();
                    korean_character.setName(firstFragmentContactModelArrayList.get(firstFragmentContactModelArrayList.size()-1).getName());
                    korean_character.setNumber(SpecialNumber);
                    firstFragmentContactModelArrayList.add(firstFragmentContactModelArrayList.size()-1,korean_character);

                }
            }


            Log.d("Test","what is name? " + firstFragmentContactModelArrayList.get(firstFragmentContactModelArrayList.size()-1).getName());
            Log.d("Test1","what is number1? " + firstFragmentContactModelArrayList.get(firstFragmentContactModelArrayList.size()-1).getNumber());
        }

        phones.close();

        // for korean letter "ㄱ"
        FirstFragmentContactModel korean_letter = new FirstFragmentContactModel();
        korean_letter.setName("기역");
        korean_letter.setNumber(SpecialNumber);
        firstFragmentContactModelArrayList.add(0,korean_letter);


        // Log.d("Test","what is name? " + firstFragmentContactModelArrayList.get(firstFragmentContactModelArrayList.size()-1).getName());
        firstFragmentListViewAdapter = new FirstFragmentListViewAdapter(getActivity(), firstFragmentContactModelArrayList);
        listView.setAdapter(firstFragmentListViewAdapter);


        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View view){
                listView.smoothScrollToPosition(0);

            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d("Test03", "positionis "+position);
                Toast.makeText(getActivity(), firstFragmentContactModelArrayList.get(position).getName(), Toast.LENGTH_LONG).show();
                /*
                FirstFragmentPhoneCall firstFragmentPhoneCall;
                firstFragmentPhoneCall = new FirstFragmentPhoneCall(getActivity());
                String PhoneNumber = firstFragmentContactModelArrayList.get(position).getNumber();
                firstFragmentPhoneCall.makeACall("dial",PhoneNumber);
                */

                /*
                String PhoneNumber = firstFragmentContactModelArrayList.get(position).getNumber();

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+PhoneNumber));
                startActivity(intent);
                */

                /*
                String PhoneNumber = firstFragmentContactModelArrayList.get(position).getNumber();
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:"+PhoneNumber));
                smsIntent.putExtra("sms_body","");
                startActivity(smsIntent);
                */
            }
        });

        btnScrollTop = (ImageButton)view.findViewById(R.id.imageScrollTop);
        btnScrollTop.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                //listView.smoothScrollByOffset(0);
                listView.smoothScrollToPositionFromTop(0,0);
            }});


        return view;
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
