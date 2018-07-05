package com.example.q.Tab1andTab2_01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class ThirdFragment extends Fragment {
    public ThirdFragment(){
    }

    private Intent intent;
    private ListView listView;
    private ListViewAdapter adapter;
    private ArrayList<ContentModel> contentModelArrayList;
    private SQLiteDatabase DB;
    private mComparator comparator;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_third,container,false);
        View view = inflater.inflate(R.layout.fragment_third,null);
        listView = (ListView) view.findViewById(R.id.lv_LISTVIEW);          // 리스트뷰
        contentModelArrayList = new ArrayList<ContentModel>();  // 저장할 배열
        File file = new File(getActivity().getFilesDir(), "ledgeData.db");
        try {
            DB = SQLiteDatabase.openOrCreateDatabase(file, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        // 2. DB의 Table에서 데이터 추출
        if (DB != null) {
            DB.execSQL("CREATE TABLE IF NOT EXISTS history (Year INTEGER, " +
                    "Month INTEGER, Day INTEGER, Hour INTEGER, Minute INTEGER, " +
                    "Description TEXT, Sum TEXT)");
//            DB.execSQL("DELETE FROM history WHERE Description=\"sample\"");

            /*

            DB.execSQL("INSERT INTO history VALUES(2018,07,01,08,30,\"아침\",\"4200\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,01,09,32,\"매점\",\"2800\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,01,11,17,\"면도기\",\"12000\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,01,12,04,\"점심\",\"6500\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,01,13,10,\"커피\",\"4200\")");

            DB.execSQL("INSERT INTO history VALUES(2018,07,02,11,30,\"점심\",\"9000\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,02,12,30,\"커피\",\"4400\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,02,18,30,\"저녁\",\"5200\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,02,23,31,\"술\",\"42000\")");

            DB.execSQL("INSERT INTO history VALUES(2018,07,03,11,48,\"서브웨이\",\"3900\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,03,12,43,\"교통카드\",\"10000\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,03,15,39,\"기숙사\",\"500000\")");
            DB.execSQL("INSERT INTO history VALUES(2018,07,03,17,32,\"계절학기\",\"50000\")");

            */



            // 쿼리문으로 데이터 불러옴
            Cursor cursor = DB.rawQuery("SELECT * FROM history", null);
            while (cursor.moveToNext()) {
                int yr = cursor.getInt(0);
                int mo = cursor.getInt(1);
                int dy = cursor.getInt(2);
                int hr = cursor.getInt(3);
                int mn = cursor.getInt(4);
                String ds = cursor.getString(5);
                String sm = cursor.getString(6);
                contentModelArrayList.add(new ContentModel(yr, mo, dy, hr, mn, ds, sm));
            }
        }
        // Divider, Total 역할의 객체 add
        Cursor cs = DB.rawQuery("SELECT distinct Year, Month, Day FROM history", null);
        while(cs.moveToNext()) {
            int Y = cs.getInt(0);
            int M = cs.getInt(1);
            int D = cs.getInt(2);
            // 총지출 계산하여 Total 객체 추가
            Cursor cs_in = DB.rawQuery("SELECT sum FROM history WHERE " +
                    "Year=" + Y + " and Month=" + M + " and Day=" + D, null);
            int tot = 0;
            while(cs_in.moveToNext()) {
                tot += Integer.parseInt(cs_in.getString(0));
            }
            contentModelArrayList.add(new ContentModel(Y, M, D, 99, 99, "@@TOTAL@@", Integer.toString(tot)));       // Total 객체
            contentModelArrayList.add(new ContentModel(Y, M, D, 0, 0, "@@DATE@@", "0"));                      // Divider 객체
        }


        // 정렬
        comparator = new mComparator();
        Collections.sort(contentModelArrayList, comparator);

        adapter = new ListViewAdapter(getActivity(), contentModelArrayList);
        listView.setAdapter(adapter);

        FloatingActionButton add_FAB = (FloatingActionButton) view.findViewById(R.id.add);
        add_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Add Activity 호출
                 */
                intent = new Intent(getActivity(), AddItemActivity.class);
                startActivityForResult(intent, 1111);
            }
        });

        //return layout;
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1111) {
            if(resultCode == 1234) {
                int year = Integer.parseInt(data.getStringExtra("Year"));
                int month = Integer.parseInt(data.getStringExtra("Month"));
                int day = Integer.parseInt(data.getStringExtra("Day"));
                int hour = Integer.parseInt(data.getStringExtra("Hour"));
                int min = Integer.parseInt(data.getStringExtra("Minute"));
                String desc = data.getStringExtra("Desc");
                String sum = data.getStringExtra("Sum");

                // insert in DB
                DB.execSQL("INSERT INTO history VALUES (" + year +", "+
                        month +", "+ day +", "+ hour +", "+ min +", \""+ desc +"\", \""+ sum + "\")");
                contentModelArrayList.add(new ContentModel(year, month, day, hour, min, desc, sum));

                // 해당 날짜가 새로 추가되었으면 <구분아이템 추가>
                Cursor csr = DB.rawQuery("SELECT all Year, Month, Day, Sum, Description FROM history WHERE Year=" +
                        year + " and Month=" + month + " and Day=" + day, null);
                if(csr.getCount() != 0 && csr.getCount() == 1) {
                    csr.moveToFirst();
                    contentModelArrayList.add(new ContentModel(csr.getInt(0), csr.getInt(1), csr.getInt(2), 0, 0, "@@DATE@@", "0"));
                    contentModelArrayList.add(new ContentModel(csr.getInt(0), csr.getInt(1), csr.getInt(2), 99, 99, "@@TOTAL@@", csr.getString(3)));
                }
                // 원래 있던 날짜에 추가되었으면 ArrayList에서 해당 Total객체 찾아서 변경
                else if (csr.getCount() > 2) {
                    //csr.moveToFirst();
                    int tot = 0;
                    while(csr.moveToNext()) {
                        tot += Integer.parseInt(csr.getString(3));
                    }
                    //int t = Integer.parseInt(sum) + old_sum;
                    int idx = -1;
                    for (int i=0; i < contentModelArrayList.size(); i++) {
                        if(contentModelArrayList.get(i).getYear() == year &&
                                contentModelArrayList.get(i).getMonth() == month &&
                                contentModelArrayList.get(i).getDay() == day &&
                                contentModelArrayList.get(i).getDescription().equals("@@TOTAL@@") == true) {
                            idx = i;
                            break;
                        }
                    }
                    if (idx > -1)
                        contentModelArrayList.get(idx).setSum(Integer.toString(tot));

                }


                Collections.sort(contentModelArrayList, comparator);
                listView.setAdapter(adapter);

                //Log.v("MainActivity", "Main2Acitiy result : " + data.getStringExtra("result"));
            }
        }
    }



}
