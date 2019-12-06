package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import java.io.Serializable;
import java.util.ArrayList;

public class StopInfoClass implements Serializable {
    private ArrayList<FirebaseHelper.Route_service> time_schedule;

    public ArrayList<FirebaseHelper.Route_service>  getTime_schedule(){
        return time_schedule;
    }

    public void setTime_schedule(ArrayList<FirebaseHelper.Route_service>  target){
        time_schedule = target;
    }
}
