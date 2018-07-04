package com.example.q.Tab1andTab2_01;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class FirstFragmentPhoneCall {

    public static final String TELL_FORMAT = "tel:";

    public static final String DIAL = "dial";
    public static final String DIRECT_CALL = "directCall";

    private Context context;

    public FirstFragmentPhoneCall(Context context){
        this.context = context;
    }

    public void makeACall(String callType , String phoneNumber){

        Intent intent = null;

        if(callType.equals(DIAL)){//dial
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse(TELL_FORMAT+phoneNumber));
        }else{//directCall
            intent = new Intent(Intent.ACTION_CALL, Uri.parse(TELL_FORMAT+phoneNumber));
        }
        context.startActivity(intent);
    }

}
