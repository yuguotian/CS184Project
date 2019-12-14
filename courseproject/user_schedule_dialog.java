package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class user_schedule_dialog extends DialogFragment {
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Schdeule_info_class mParam1;
    //private String mParam2;

    public user_schedule_dialog() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static user_schedule_dialog newInstance() {
        user_schedule_dialog fragment = new user_schedule_dialog();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_schedule_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        final LinearLayout ll = view.findViewById(R.id.schedule_ll);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_schedules", 0);
        String keys_primitive = sharedPreferences.getString("key", null);
        if(keys_primitive == null){
            TextView tv = new TextView(view.getContext());
           /* Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                    "fonts/Arial.otf");
            tv.setTypeface(tf);*/
            tv.setText("Currently, there are no schedules. \nYou can try to add it in direction mode! \nBy tapping the second fab, the arrow one");
            tv.setTextSize(20);
            tv.setPadding(40,40,40,40);
            ll.addView(tv);
            return;
        }
        if(keys_primitive == ""){
            TextView tv = new TextView(view.getContext());
           /* Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                    "fonts/Arial.otf");
            tv.setTypeface(tf);*/
            tv.setText("Currently, there are no schedules. \nYou can try to add it in direction mode! \nBy tapping the second fab, the arrow one");
            tv.setTextSize(20);
            tv.setPadding(40,40,40,40);
            ll.addView(tv);
            return;
        }
        String[] key_arr = keys_primitive.split(";");

        Log.d("Long Message", "onViewCreated: primitive " + keys_primitive);
        int schedule_count = 0;
        String newKey = "";

        for(int i = 0; i < key_arr.length; i++){
            schedule_count++;
            final String current_key = key_arr[i];
            String primitive_schedule = sharedPreferences.getString(key_arr[i], null);
            if(primitive_schedule == null){
                schedule_count--;
            }
            else{
                String[] schedule_arr = primitive_schedule.split(";");
                if(schedule_arr.length == 3){
                    newKey = newKey + current_key + ";";
                    final TextView tv = new TextView(view.getContext());
                    tv.setText("Schedule " + schedule_count + " :" + "\n" + "    Line: " + schedule_arr[1] + "   Time:  " + schedule_arr[0]
                            + "\n" + "    Arrive time: " + schedule_arr[2] + "\n");
                    tv.setClickable(true);
                    tv.setFocusable(true);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences preferences = getContext().getSharedPreferences("user_schedules", 0);
                            preferences.edit().remove(current_key).commit();
                            ll.removeView(tv);
                        }
                    });
                    ll.addView(tv);
                }
                else if(schedule_arr.length == 8){
                    newKey = newKey + current_key + ";";
                    final TextView tv = new TextView(view.getContext());
                    tv.setText("Schedule " + schedule_count + " :" + "\n" + "    Line " + schedule_arr[1] + "   Time:  " + schedule_arr[0]
                            + "\n" + "    Take off Stop:" + schedule_arr[3] + "   Time:  " + schedule_arr[2] + "\n" + "    Transit Line: " + schedule_arr[5] + "  Transit Stop: " + schedule_arr[4] + "\n     Time:  " + schedule_arr[6]
                            + "\n" + "    Arrive Time: " + schedule_arr[7] + "\n");
                    tv.setClickable(true);
                    tv.setFocusable(true);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences preferences = getContext().getSharedPreferences("user_schedules", 0);
                            preferences.edit().remove(current_key).commit();
                            ll.removeView(tv);
                        }
                    });
                    ll.addView(tv);
                }
                else{
                    schedule_count--;
                    Log.d("Long Message", "onViewCreated: " + "Undefined behavior");
                }
            }
            Log.d("Long Message", "onViewCreated: " + primitive_schedule);
        }
        Log.d("Long Message", "onViewCreated: after " + newKey );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", newKey); //Update key
        editor.commit();
    }
}
