package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class scheduleInfoDialog extends DialogFragment {
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Schdeule_info_class mParam1;
    //private String mParam2;

    public scheduleInfoDialog() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static scheduleInfoDialog newInstance(Schdeule_info_class info) {
        scheduleInfoDialog fragment = new scheduleInfoDialog();
        Bundle args = new Bundle();

        args.putSerializable(ARG_PARAM1, info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.schedule_info_window, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Schdeule_info_class info = (Schdeule_info_class)getArguments().getSerializable(ARG_PARAM1);
        LinearLayout ll = view.findViewById(R.id.ll);

        int counter = 0;


        if(info == null || info.getSchedule_info() == null || info.getSchedule_info().size() == 0){
            Log.d("Long Message", "onViewCreated: called");
            TextView tv = new TextView(view.getContext());
            tv.setText("No service available!");
            ll.addView(tv);
            return;
        }

        boolean have_service = false;
        for(String key : info.getSchedule_info().keySet()) {
            ArrayList<ArrayList<String>> temp = info.getSchedule_info().get(key);
            for (int i = 0; i < temp.size(); i++) {
                counter++;
                ArrayList<String> information = temp.get(i);
                if (information.size() == 8) { // transit case
                    int waiting = InfoWindowDialog.compareTime(information.get(2), information.get(4));
                    int duration = InfoWindowDialog.compareTime(information.get(0), information.get(7));
                    Calendar now = Calendar.getInstance();
                    String current_time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
                    int time_left_before_depart = InfoWindowDialog.compareTime(current_time, information.get(0));
                    if(waiting > 60 || time_left_before_depart > 60){ //Ignore Long Waiting time
                        counter--;
                        continue;
                    }
                    have_service = true;
                    final String stop1 = information.get(6);
                    final String stop2 = information.get(3);
                    TextView tv = new TextView(view.getContext());
                    final String information_str = information.get(1) + ";" + information.get(0) + ";" + information.get(3) + ";" + information.get(2) +
                            ";" + information.get(5) + ";" + information.get(6) + ";" + information.get(4) + ";" + information.get(7);
                    tv.setText("Route Option " + counter + " :" + "\n" + "    Take the line " + information.get(1) + "   at  " + information.get(0)
                    + "\n" + "    Take off at stop " + information.get(3) + "  at  " + information.get(2) + "\n" + "    Then take the " + information.get(5) + "  at stop " + information.get(6) + "\n     which leaves at  " + information.get(4)
                    + "\n" + "    The bus will arrive at " + information.get(7) + "\n" + "    Duration " + duration + " minutes" + "   Transit Waiting time: " + waiting + " minutes" +"\n");
                    tv.setClickable(true);
                    tv.setFocusable(true);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Long time = System.currentTimeMillis();
                            SharedPreferences sharedPref = getActivity().getSharedPreferences("user_schedules", 0);
                            String primitive_keys = sharedPref.getString("key", null);
                            String combine_keys = primitive_keys + ";" + information_str;

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("key", combine_keys);
                            editor.putString(Long.toString(time), information_str);
                            editor.commit();
                        }
                    });
                    ll.addView(tv);

                    Button b = new Button(view.getContext());
                    b.setText("View this route on the map!");
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getDialog().dismiss();
                            MapsActivity.connectingStops(stop1, stop2);
                        }
                    });
                    ll.addView(b);
                    tv = new TextView(view.getContext());
                    //blank space
                    ll.addView(tv);

                }
                else if(information.size() == 3){ //Direct Transit
                    have_service = true;
                    TextView tv = new TextView(view.getContext());
                    final String information_str = information.get(1) + ";" + information.get(0) + ";" + information.get(2);
                    tv.setText("Route Option " + counter + " :" + "\n" + "    Take the " + information.get(1) + "   at  " + information.get(0)
                    + "\n" + "    Take off at " + information.get(2) + "\n");

                    tv.setClickable(true);
                    tv.setFocusable(true);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Long time = System.currentTimeMillis();
                            SharedPreferences sharedPref = getActivity().getSharedPreferences("user_schedules", 0);
                            String primitive_keys = sharedPref.getString("key", null);
                            String combine_keys;
                            if(primitive_keys != null){
                                combine_keys = primitive_keys + Long.toString(time) + ";";
                            }
                            else{
                                combine_keys = Long.toString(time) + ";";
                            }

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("key", combine_keys);
                            editor.putString(Long.toString(time), information_str);
                            editor.commit();
                        }
                    });

                    ll.addView(tv);

                    Button b = new Button(view.getContext());
                    b.setText("View this route on the map!");
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getDialog().dismiss();
                            MapsActivity.direct_stop_connection();
                        }
                    });
                    ll.addView(b);
                    tv = new TextView(view.getContext());
                    //blank space
                    ll.addView(tv);

                }
                else{
                    counter--;
                    //Do nothing (wrong case)
                }

            }
        }
        if(!have_service){
            TextView tv = new TextView(view.getContext());
            tv.setText("No Service Available, please select the opposite direction or a new stop!");
            ll.addView(tv);
            MapsActivity.remove_temp_direction_marker();
        }

    }
}
