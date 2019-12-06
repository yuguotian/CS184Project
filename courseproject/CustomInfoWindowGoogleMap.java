package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info_window, null);
        StopInfoClass stopInfoClass = (StopInfoClass)marker.getTag();

        LinearLayout ll = view.findViewById(R.id.scroll);
        ArrayList<FirebaseHelper.Route_service>  stop_info = stopInfoClass.getTime_schedule();

        for(int i = 0; i < stop_info.size(); i++){
            TextView tv = new TextView(view.getContext());
            tv.setText(stop_info.get(i).getRoute_id() + ": " + stop_info.get(i).getRoute_name() + "       " + stop_info.get(i).getTime());
            ll.addView(tv);
        }

        return view;
    }
}