package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;

import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Donghao Ren on 03/11/2017.
 * Modified by Ehsan Sayyad on 11/9/2018
 * Modified by Jake Guida on 11/6/2019
 */

public class FirebaseHelper { //Abandoned

    //Global Variable
    private static int global_count = 0;
    private static LatLng marker_location  = new LatLng(34.417099, -119.869120);
    private static LatLng user_location = new LatLng(34.434862, -119.848546);
    private static Marker m1; //For testing
    private static Marker m2; //For testing
    private static boolean temp_indicator = true;
    private FragmentManager fm;

    //Class objects for database
    /*public static class Stop implements Serializable {

        private Double latitude;
        private Double longitude;
        private String name;
        private int stop_id;


        public Stop(double longitude, double latitude, String name, int id){
            this.latitude = latitude;
            this.longitude = longitude;
            this.name = name;
            this.stop_id = id;
        }

        public Double getLatitude(){
            return latitude;
        }

        public Double getLongitude(){
            return longitude;
        }

        public String getName(){
            return name;
        }

        public int getStop_id(){
            return stop_id;
        }

        public Stop(){}
    }

    public static class Stop2 implements Serializable {

        private Double latitude;
        private Double longitude;
        private String name;
        private ArrayList<Route_service> Weekday_service;
        private int stop_id;


        public Stop2(double longitude, double latitude, String name, int id, ArrayList<Route_service> Weekday_service){
            this.latitude = latitude;
            this.longitude = longitude;
            this.name = name;
            this.stop_id = id;
        }

        public Double getLatitude(){
            return latitude;
        }

        public Double getLongitude(){
            return longitude;
        }

        public String getName(){
            return name;
        }

        public int getStop_id(){
            return stop_id;
        }

        public ArrayList<Route_service> getWeekday_service(){return Weekday_service;}

        public Stop2(){}
    }

    public static class geoPosition implements Serializable{
        private Double latitude;
        private Double longitude;

        public geoPosition(){}

        public Double getLatitude(){
            return latitude;
        }

        public Double getLongitude(){
            return longitude;
        }
    } //Similar to Latlon

    public static class Route implements Serializable {

        public String name_id;
        public String name;
        public String route_id;
        private ArrayList<String> Shape;

        public Route(String name, String id, String route_id, ArrayList<String> shape){
            this.name = name;
            this.name_id = id;
            this.route_id = route_id;
            this.Shape = shape;
        }

        public String getName(){
            return name;
        }

        public String getName_id(){
            return name_id;
        }

        public String getRoute_id(){
            return route_id;
        }

        public ArrayList<String> getShape(){return Shape;}

        public Route(){}
    }

    public static class Trip_Time implements Serializable {

        public String time;
        public String stop_id;
        public String stop_sequence;

        public Trip_Time(String time, String stop_id, String stop_sequence){
            this.time = time;
            this.stop_id = stop_id;
            this.stop_sequence = stop_sequence;
        }

        public String getTime(){
            return time;
        }

        public String getStop_id(){
            return stop_id;
        }

        public String getStop_sequence(){
            return stop_sequence;
        }

        public Trip_Time(){}
    }

    public static class Route_service implements Serializable{
        private String service_id;
        private String time;
        private String direction;
        private String route_name;
        private String route_id;

        public Route_service(String service_id, String time, String direction, String route_name, String route_id){
            this.service_id = service_id;
            this.time = time;
            this.direction = direction;
            this.route_name = route_name;
            this.route_id = route_id;
        }

        public Route_service(){}

        public String getService_id(){
            return service_id;
        }

        public String getTime(){
            return time;
        }

        public String getDirection(){return direction;}

        public String getRoute_name() {return route_name;}

        public String getRoute_id() {return route_id;}
    }

    public static class Trip implements Serializable {

        public String route_id;
        public String service_id;
        public String trip_id;
        public String direction;
        public String trip_headsign;
        public String shape_id;

        public Trip(String route_id, String service_id, String trip_id, String direction, String trip_headsign, String shape_id){
            this.route_id = route_id;
            this.service_id = service_id;
            this.trip_headsign = trip_headsign;
            this.direction = direction;
            this.shape_id = shape_id;
            this.trip_id = trip_id;
        }

        public String getService_id(){
            return service_id;
        }

        public String getTrip_id(){
            return trip_id;
        }

        public String getDirection(){
            return direction;
        }

        public String getTrip_headsign(){
            return trip_headsign;
        }

        public String getShape_id(){
            return shape_id;
        }

        public String getRoute_id(){
            return route_id;
        }

        public Trip(){}
    }

    private static boolean initialized = false;

    private static GoogleMap map; //Map Instance*/

    /*public static void getMap(GoogleMap mMap){
        map = mMap;
    } //Retrieve map from map activity

    /** The Firebase database object */
    /*private static FirebaseDatabase db;

    public static void Initialize(final Context context) {
        if (!initialized) {
            initialized = true;
            FirebaseApp.initializeApp(context);
            // Call the OnDatabaseInitialized to setup application logic
        }
        OnDatabaseInitialized();
    }

    /** This is called once we initialize the firebase database object */
    /*private static void OnDatabaseInitialized() {
        //final GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
        db = FirebaseDatabase.getInstance();

        collectBasic_Stop_info();

        //getDirection(current, marker_location, 500);

        //displayNearByStop(current, 500);
    }

    private static void collectBasic_Stop_info(){
        DatabaseReference stop = db.getReference().child("Basic_Stops");
        stop.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Stop stop = data.getValue(Stop.class);
                    all_stops.put(Integer.toString(stop.stop_id), stop);
                }
                do_operations();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static void do_operations(){ //Define all your operations during initialization at there!
        DatabaseReference fb = db.getReference();
        DatabaseReference table = fb.child("Basic_Stops");
        ArrayList<String[]> outcome = new ArrayList<>();
        //outcome = Helper.parseData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/routes.txt");

        ArrayList<String> Route_list = new ArrayList<>();
        DatabaseReference stop = fb.child("Routes");

        ArrayList<String> depart_route = new ArrayList<>();
        ArrayList<String> destination_route = new ArrayList<>();
        Query order = table.orderByKey();
        LatLng current = new LatLng(34.434862, -119.848546);

        DatabaseReference local = table.child("Stop: 180"); //Temp

        local.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Stop s = dataSnapshot.getValue(Stop.class);
                Log.d("Long Message", "onDataChange: " + s.getStop_id());
                MarkerOptions marker = new MarkerOptions();
                marker.position(new LatLng(s.getLatitude(), s.getLongitude()));
                marker.title("User Nearby stop:" + s.stop_id);
                m1 = map.addMarker(marker);
                display_nearby_stop_helper(s.stop_id, m1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        local = table.child("Stop: 371");
        local.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Stop s = dataSnapshot.getValue(Stop.class);
                Log.d("Long Message", "onDataChange: " + s.getStop_id());
                MarkerOptions marker = new MarkerOptions();
                marker.position(new LatLng(s.getLatitude(), s.getLongitude()));
                marker.title("User Nearby stop:" + s.stop_id);
                m2 = map.addMarker(marker);
                display_nearby_stop_helper(s.stop_id, m2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //Time comparator, using for sort
    public static class TimeComparator implements Comparator<String>{
        @Override
        public int compare(String time1, String time2) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            try {
                Date d1 = sdf.parse(time1);
                Date d2 = sdf.parse(time2);
                if (d1.getTime() > d2.getTime()) {
                    return 1;
                }
                else if(d1.getTime() < d2.getTime()){
                    return -1;
                }
                else{
                    return 0;
                }

            } catch (Exception e) {
                Log.e("Error", "compare: " + e.getMessage());
            }
            return 0;
        }
    }
    //Display the whole route
    public static void setTripRoute(String shape_id){ //Display the trip route
        DatabaseReference shape = db.getReference().child("Shape").child("Trip: " + shape_id);
        Query order = shape.orderByKey();

        order.addListenerForSingleValueEvent(new ValueEventListener() {;
            @Override
            public void onDataChange(DataSnapshot data) {
                Random rnd = new Random();
                PolylineOptions option = new PolylineOptions().color(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                for (DataSnapshot dataSnapshot : data.getChildren()) {
                    //Log.d("Long Message", "onDataChange: " + dataSnapshot.getValue());
                    geoPosition position = dataSnapshot.getValue(geoPosition.class);
                    //Log.d("Long Message", "onDataChange: " + position.getLongitude());
                    LatLng ll = new LatLng(position.getLatitude(), position.getLongitude());
                    option.add(ll);
                }
                map.addPolyline(option);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void displayNearByStop(final LatLng currentPosition, final int max_distance){ //distance in meters, find the nearby stops to the position
        DatabaseReference table = db.getReference().child("Stops");
        //final ArrayList<Stop> result_stop = new ArrayList<>();
        table.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Stop current_stop = data.getValue(Stop.class);
                    float[] results = new float[1];
                    Location.distanceBetween(currentPosition.latitude, currentPosition.longitude,current_stop.getLatitude(), current_stop.getLongitude(),results);
                    if(results[0] < max_distance){
                        MarkerOptions marker = new MarkerOptions();
                        marker.position(new LatLng(current_stop.getLatitude(), current_stop.getLongitude()));
                        marker.title(current_stop.stop_id + "");
                        display_nearby_stop_helper(current_stop.getStop_id(), map.addMarker(marker));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static void display_nearby_stop_helper(int stop_id, final Marker m){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        DatabaseReference schedule;
        DatabaseReference special_schedule;

        if(cal.get(Calendar.DAY_OF_WEEK) > 1 && cal.get(Calendar.DAY_OF_WEEK) < 7){ // Workdays
            schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Weekday Service");
            special_schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Weekday Service");
        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 7){ //Saturday
            schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Saturday Service");
            special_schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Saturday Service");
        }
        else{ //Sunday
            schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Sunday Service");
            special_schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Sunday Service");
        }

        final GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>() {};
        final ArrayList<Route_service> outcome = new ArrayList<>();
        final int[] counter = {0};

        schedule.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() != 0){
                    outcome.addAll(dataSnapshot.getValue(t));
                }
                counter[0]++;
                display_nearby_stop_finish(outcome, counter[0], m);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        special_schedule.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() != 0){
                    outcome.addAll(dataSnapshot.getValue(t));
                }
                counter[0]++;
                display_nearby_stop_finish(outcome, counter[0], m);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private static void display_nearby_stop_finish(ArrayList<Route_service> outcome, int indicator, Marker m){
        if(indicator == 2){ //All outcome added
            if(m != null){
                StopInfoClass info = new StopInfoClass();
                info.setTime_schedule(outcome);
                m.setTag(info);
                if(m1 != null && m2 != null && m1.getTag() != null && m2.getTag() != null && temp_indicator){
                    temp_indicator = false;
                    find_routes_between_two_marker(m1,m2);
                }
            }
            else{
                //Define your function there
            }
        }
    }

    public static void display_specific_route(ArrayList<String> service_id){ //Helper function for infoWindowDialog: display the route shape and stops when a route's textview is clicked
        map.clear();
        for(int i = 0; i < service_id.size(); i++){
            DatabaseReference service = db.getReference().child("Trips").child("Trip: " + service_id.get(i));
            service.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Trip t = dataSnapshot.getValue(Trip.class);
                    //Log.d("Long Message", "onDataChange: " + t.shape_id);
                    setTripRoute(t.shape_id);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            final GenericTypeIndicator<ArrayList<Trip_Time>> t = new GenericTypeIndicator<ArrayList<Trip_Time>>() {};
            DatabaseReference trip_time = db.getReference().child("Trip_Time").child("Trip: " + service_id.get(i));
            trip_time.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Trip_Time> trip = dataSnapshot.getValue(t); //Index starts from 1
                    ArrayList<String> stop = new ArrayList<>();
                    for(int i = 0; i < trip.size(); i++){
                        if(trip.get(i) != null){
                            stop.add(trip.get(i).getStop_id());
                        }
                    }
                    show_route_stop_helper(stop);
                    //show_transfer_stop_helper(stop);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    private static void show_route_stop_helper(ArrayList<String> stop_id_arr){
        for(int i = 0; i < stop_id_arr.size(); i++){
            DatabaseReference stop = db.getReference().child("Stops").child("Stop: " + stop_id_arr.get(i));
            stop.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Stop s = dataSnapshot.getValue(Stop.class);
                    MarkerOptions marker = new MarkerOptions();
                    marker.position(new LatLng(s.getLatitude(), s.getLongitude()));
                    marker.title("Route Marker:" + s.stop_id); //Special name route marker

                    display_nearby_stop_helper(s.getStop_id(), map.addMarker(marker));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private static void find_routes_between_two_marker(Marker start, Marker end){
        find_transit_helper_arr.add(start);
        find_transit_helper_arr.add(end);
        StopInfoClass start_info = (StopInfoClass)start.getTag();
        StopInfoClass end_info = (StopInfoClass)end.getTag();

        HashMap<String, Route_service> close_start_service = new HashMap<>();
        HashMap<String, ArrayList<Route_service>> target_end_service = new HashMap<>();
        ArrayList<Route_service> start_service = start_info.getTime_schedule();
        ArrayList<Route_service> end_service = end_info.getTime_schedule();

        Calendar now = Calendar.getInstance();
        String current_time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
        //String current_time = "17:32:04"; //Temp

        //Calculate the distance to estimate time? Temp
        //float[] results = new float[1];
        //Location.distanceBetween(currentPosition.latitude, currentPosition.longitude,current_stop.getLatitude(), current_stop.getLongitude(),results);

        for(int i = 0; i < start_service.size(); i++){
            int remaining = InfoWindowDialog.compareTime(current_time, start_service.get(i).getTime());
            if(remaining > 4){ //Temp Range
                if(close_start_service.get(start_service.get(i).getRoute_id()) == null){
                    close_start_service.put(start_service.get(i).getRoute_id(), start_service.get(i));
                }
            }
        }

        //All unique start route service info are in the hashmap: close_start_service (Key is route id, value is the Route service value)
        for(int j = 0; j < end_service.size(); j++){
            int remaining = InfoWindowDialog.compareTime(current_time, end_service.get(j).getTime());
            if(remaining > 100){ //Temp Range
                if(target_end_service.get(end_service.get(j).getRoute_id()) == null){
                    ArrayList<Route_service> single_route_service = new ArrayList<>();
                    single_route_service.add(end_service.get(j));
                    target_end_service.put(end_service.get(j).getRoute_id(), single_route_service);
                }
                else{
                    target_end_service.get(end_service.get(j).getRoute_id()).add(end_service.get(j));
                }
            }
        }

        Log.d("Long Message", "find_routes_between_two_marker: start  " + close_start_service.size() + "    target:  " + target_end_service.size());

        //All possible end service are in the hashmap: target_end_service
        ArrayList<String> possible_keys = new ArrayList<>();
        for(String s : close_start_service.keySet()){
            if(target_end_service.containsKey(s)){
                possible_keys.add(s);
            }
        }

        if(possible_keys.size() != 0){ //Have direct bus connecting two stops
            Log.d("Long Message", "find_routes_between_two_marker: " + possible_keys.get(0) + " size " + possible_keys.size());
        }
        else{ //We need to find the transit
            Log.d("Long Message", "find_routes_between_two_marker: We need to find transit!");
            String start_stop_title_arr[] = start.getTitle().split(":");
            String start_stop_title = start_stop_title_arr[1];
            String end_stop_title_arr[] = start.getTitle().split(":");
            String end_stop_title = start_stop_title_arr[1];
            ArrayList<Route_service> route_services = new ArrayList<>(close_start_service.values());
            //ArrayList<String> target_key_set = new ArrayList<>(target_end_service.keySet());
            find_transit(route_services,target_end_service, start_stop_title, end_stop_title);
        }
    }

    //Helper function to find transit between two markers (Currently, maximum one transit)
    private static void find_transit(final ArrayList<Route_service> current_service, final HashMap<String, ArrayList<Route_service>>  target_route_name, final String stop_id, final String end_stop_id){
        Log.d("Long Message", "find_transit: Find transit get called");
        final HashMap<String, ArrayList<String>> stop_ids = new HashMap<>();
        final int[] counter_helper = {0};
        for(int i = 0; i < current_service.size(); i++){
            final String service = current_service.get(i).getService_id();
            DatabaseReference single_service = db.getReference().child("Trip_Time").child("Trip: " + service);
            final int temp = i;
            Query query = single_service.orderByKey();
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean after_stop = false;
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        Trip_Time trip_time = data.getValue(Trip_Time.class);
                        if(trip_time.getStop_id().compareTo(stop_id) == 0){
                            after_stop = true;
                        }
                        if(after_stop){
                            if(stop_ids.get(current_service.get(temp).getRoute_id()) == null){
                                ArrayList<String> single_stop_id = new ArrayList<>();
                                single_stop_id.add(trip_time.getStop_id() + ":" + service);
                                stop_ids.put(current_service.get(temp).getRoute_id(), single_stop_id);
                            }
                            else{
                                stop_ids.get(current_service.get(temp).getRoute_id()).add(trip_time.getStop_id() + ":" + service);
                            }
                        }
                    }
                    counter_helper[0]++;
                    find_transit_helper(stop_ids, target_route_name, end_stop_id, current_service.size(), counter_helper[0]);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private static void find_transit_helper(HashMap<String,ArrayList<String>> service_stop_mapping, final HashMap<String, ArrayList<Route_service>> target_route, final String end_stop_id, int target_size, int current_size){
        if(target_size == current_size){
            Log.d("Long Message", "find_transit_helper: find transit helper get called");
            final int[] count_helper = {0};
            int size = 0;
            for(String service : service_stop_mapping.keySet()){
                size += service_stop_mapping.get(service).size();
            }
            final int total_size = size;
            final HashMap<String, HashSet<String>> possible_route_stops = new HashMap<>(); //Route name is the key, Hashset contains all possible stop for that route
            for(String service : service_stop_mapping.keySet()){
                Log.d("Long Message", "find_transit_helper: " + service);
                final ArrayList<String> stop = service_stop_mapping.get(service);
                final ArrayList<String> target_stop_key_set = new ArrayList<String>(target_route.keySet());
                for(String stop_info : stop){
                    String [] split_stop_info = stop_info.split(":"); //0 is the stop_id, 1 is the service id
                    final String stop_id = split_stop_info[0];
                    final String service_id = split_stop_info[1];
                    Log.d("Long Message", "find_transit_helper: real " + service_id);
                    DatabaseReference operation_route_info = db.getReference().child("Operation Route").child("Stop: " + stop_id);
                    operation_route_info.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                if(data.getKey().charAt(0) != 'T'){
                                    String route_name = data.getValue(String.class);
                                    if(target_stop_key_set.contains(route_name)){
                                        Log.d("Long Message", "onDataChange: "  + "    stop   " + stop_id + "   direct transfer    " + route_name);
                                        if(possible_route_stops.get(route_name) == null){
                                            HashSet<String> route_temp = new HashSet<>();
                                            route_temp.add(stop_id + ":" + service_id); //The drop-off stop id
                                            possible_route_stops.put(route_name, route_temp);
                                        }
                                        else{
                                            possible_route_stops.get(route_name).add(stop_id + ":" + service_id);
                                        }
                                    }
                                }
                                else{
                                    GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                                    ArrayList<String> transfer_route = data.getValue(t);
                                    for(String transfer : transfer_route){
                                        String[] transfer_arr = transfer.replaceAll("\\s+","").split(":");
                                        if(target_stop_key_set.contains(transfer_arr[1])){
                                            Log.d("Long Message", "onDataChange: " + "  parent stop" + stop_id + "  stop   " + transfer_arr[0] + "   route_name " + transfer_arr[1]);
                                            if(possible_route_stops.get(transfer_arr[1]) == null){
                                                HashSet<String> route_temp = new HashSet<>();
                                                route_temp.add(stop_id + ":" + service_id); //The drop-off stop id
                                                possible_route_stops.put(transfer_arr[1], route_temp);
                                            }
                                            else{
                                                possible_route_stops.get(transfer_arr[1]).add(stop_id + ":" + service_id);
                                            }
                                        }
                                    }
                                }
                            }
                            count_helper[0]++;
                            find_transit_finish(total_size, count_helper[0], possible_route_stops, target_route, end_stop_id);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        }
    }

    private static void find_transit_finish(int total_size, int current_size, final HashMap<String, HashSet<String>> possible_route_stops, final HashMap<String, ArrayList<Route_service>> target_route, final String end_stop_id){
        Log.d("Long Message", "get_transit_finish: current size " + current_size + "   total   " + total_size);
        if(total_size == current_size){ //Ready
            for(String s : possible_route_stops.keySet()){
                HashSet<String> set = possible_route_stops.get(s);
                for(String x : set){
                    Log.d("Long Message", "find_transit_finish: route   " + x);
                }

            }
            final HashMap<String,ArrayList<String>> final_mapping = new HashMap<>(); //Key is the service id and route name, value is the reachable stop by backtracing the services of final stop and the service id for starting stop
            final int size = possible_route_stops.keySet().size();
            final int[] count_helper = {0};
            for(final String route_name : possible_route_stops.keySet()){ //Every key should be existed in the target route's keyset, if not something goes wrong

                ArrayList<Route_service> special_route_service = target_route.get(route_name);
                DatabaseReference service = db.getReference().child("Trip_Time").child("Trip: " + special_route_service.get(0).getService_id());
                Query query = service.orderByKey();
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean before_stop = true;
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            Trip_Time trip_time = data.getValue(Trip_Time.class);
                            if(trip_time.stop_id.compareTo(end_stop_id) == 0){
                                before_stop = false;
                            }
                            if(before_stop){ //backtrace
                                LatLng start = new LatLng(all_stops.get(trip_time.stop_id).latitude, all_stops.get(trip_time.stop_id).longitude);
                                for(String target_possible_stops_info : possible_route_stops.get(route_name)){
                                    String[] spilt_target_possible_stops_info = target_possible_stops_info.split(":"); //0 is the stop id, 1 is the service id
                                    String target_possible_stops = spilt_target_possible_stops_info[0];
                                    String service_id = spilt_target_possible_stops_info[1];
                                    LatLng end = new LatLng(all_stops.get(target_possible_stops).latitude, all_stops.get(target_possible_stops).longitude);
                                    float distance = calculateDistance(start, end);
                                    if(distance < 10){ //Currently set the direct transit as the priority (Can't handle opposite direction properly now)
                                        Log.d("Long Message", "onDataChange: direct transit  reachable stop:  " + trip_time.stop_id + "   target stop   " + target_possible_stops + "  route name  " + route_name + "    start service id   " + service_id);
                                        ArrayList<String> temp = new ArrayList<>();
                                        temp.add(Float.toString(distance)); // Index 0 is the distance to represent direct transit or indirect transfer
                                        temp.add(target_possible_stops); // Index 1 is the reachable stop for the starting stop's service
                                        temp.add(trip_time.stop_id); // Index 2 is the reachable stop for the ending stop's service by backtrace
                                        temp.add(route_name); // Index 3 is the target service id for transfer, not the starting service's id (Query that by service id)
                                        temp.add(service_id); // Index 4 is the starting service id
                                        temp.add(trip_time.stop_sequence); // Index 5 is the stop sequence, used for deciding which stop to use

                                        final_mapping.put(service_id + ":" + route_name, temp); //Override any value, regard the direct transfer as the first option
                                        break;
                                    }
                                    else if(distance < 300){
                                        Log.d("Long Message", "onDataChange: reachable stop:  " + trip_time.stop_id + "   target stop   " + target_possible_stops + "  route name  " + route_name + "    start service id   " + service_id);
                                        if(final_mapping.get(service_id + ":" + route_name) == null){
                                            ArrayList<String> temp = new ArrayList<>();
                                            temp.add(Float.toString(distance)); // Index 0 is the distance to represent direct transit or indirect transfer
                                            temp.add(target_possible_stops); // Index 1 is the reachable stop for the starting stop's service
                                            temp.add(trip_time.stop_id); // Index 2 is the reachable stop for the ending stop's service by backtrace
                                            temp.add(route_name); // Index 3 is the target service id for transfer, not the starting service's id (Query that by service id)
                                            temp.add(service_id); // Index 4 is the starting service id
                                            temp.add(trip_time.stop_sequence); // Index 5 is the stop sequence, used for deciding which stop to use
                                            final_mapping.put(service_id + ":" + route_name, temp);
                                        }
                                        else{
                                            if(Integer.parseInt(trip_time.stop_sequence) - Integer.parseInt(final_mapping.get(service_id + ":" + route_name).get(5)) > 4){ //Close in sequence stop has priority
                                                ArrayList<String> temp = new ArrayList<>();
                                                temp.add(Float.toString(distance)); // Index 0 is the distance to represent direct transit or indirect transfer
                                                temp.add(target_possible_stops); // Index 1 is the reachable stop for the starting stop's service
                                                temp.add(trip_time.stop_id); // Index 2 is the reachable stop for the ending stop's service by backtrace
                                                temp.add(route_name); // Index 3 is the target service id for transfer, not the starting service's id (Query that by service id)
                                                temp.add(service_id); // Index 4 is the starting service id
                                                temp.add(trip_time.stop_sequence); // Index 5 is the stop sequence, used for deciding which stop to use
                                                final_mapping.put(service_id + ":" + route_name, temp);
                                            }
                                            else{
                                                if(Float.parseFloat(final_mapping.get(service_id + ":" + route_name).get(0)) < distance){ //Less distance stop has priority
                                                    continue; //Do nothing
                                                }
                                                else{
                                                    ArrayList<String> temp = new ArrayList<>();
                                                    temp.add(Float.toString(distance)); // Index 0 is the distance to represent direct transit or indirect transfer
                                                    temp.add(target_possible_stops); // Index 1 is the reachable stop for the starting stop's service
                                                    temp.add(trip_time.stop_id); // Index 2 is the reachable stop for the ending stop's service by backtrace
                                                    temp.add(route_name); // Index 3 is the target service id for transfer, not the starting service's id (Query that by service id)
                                                    temp.add(service_id); // Index 4 is the starting service id
                                                    temp.add(trip_time.stop_sequence); // Index 5 is the stop sequence, used for deciding which stop to use
                                                    final_mapping.put(service_id + ":" + route_name, temp);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        count_helper[0]++;
                        Log.d("Long Message", "onDataChange: total " + size + "   current   " + count_helper[0]);
                        get_transit_schedule_helper(final_mapping, size, count_helper[0]);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    private static void get_transit_schedule_helper(final HashMap<String,ArrayList<String>> mapper, int total_size, int current_size){
        // Mapper : 0 distance, 1 reachable stop for starting service, 2 reachable stop for end stop by backtrace, 3 route name, 4 starting service id, 5 sequence (no usage there)
        Log.d("Long Message", "get_transit_schedule_helper: current size " + current_size + "   total   " + total_size);
        if(current_size == total_size){
            final int size = mapper.keySet().size();
            final int[] count_helper = {0};
            Calendar cal = Calendar.getInstance();
            cal.setTime(cal.getTime());
            final int time_identifier = cal.get(Calendar.DAY_OF_WEEK); // 0 for weekday, 1 for Saturday, 2 for Sunday
            final HashMap<String, ArrayList<Route_service>> all_stop_service_info = new HashMap<>();

            for(String key : mapper.keySet()){
                Log.d("Long Message", "get_transit_schedule_helper: " + mapper.get(key));
                ArrayList<Route_service> temp = new ArrayList<>();
                all_stop_service_info.put(mapper.get(key).get(1),temp); //Avoid query same stop multiple time
                all_stop_service_info.put(mapper.get(key).get(2),temp);
            }

            final int stop_size_indicator = all_stop_service_info.size();
            //Key is the route id, key is the service info for that

            for(final String stop_name : all_stop_service_info.keySet()){
                DatabaseReference stop_info = db.getReference().child("Stops").child("Stop: " + stop_name);
                stop_info.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(time_identifier == 7){ // Saturday
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                //Log.d("Long Message", "onDataChange: Saturday  " + data.getKey());
                                if(data.getKey().compareTo("Saturday Service") == 0){
                                    GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                    ArrayList<Route_service> route_services = data.getValue(t);
                                    all_stop_service_info.get(stop_name).addAll(route_services);
                                }
                                else if(data.getKey().compareTo("Special_Saturday Service") == 0){
                                    GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                    ArrayList<Route_service> route_services = data.getValue(t);
                                    all_stop_service_info.get(stop_name).addAll(route_services);
                                }
                            }
                        }
                        else if(time_identifier == 0){ // Sunday
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                //Log.d("Long Message", "onDataChange: Sunday " + data.getKey());
                                if(data.getKey().compareTo("Saturday Service") == 0){
                                    GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                    ArrayList<Route_service> route_services = data.getValue(t);
                                    all_stop_service_info.get(stop_name).addAll(route_services);
                                }
                                else if(data.getKey().compareTo("Special_Sunday Service") == 0){
                                    GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                    ArrayList<Route_service> route_services = data.getValue(t);
                                    all_stop_service_info.get(stop_name).addAll(route_services);
                                }
                            }
                        }
                        else{ //Workday
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                //Log.d("Long Message", "onDataChange: Saturday  " + data.getKey());
                                if(data.getKey().compareTo("Weekday Service") == 0){
                                    GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                    ArrayList<Route_service> route_services = data.getValue(t);
                                    all_stop_service_info.get(stop_name).addAll(route_services);
                                }
                                else if(data.getKey().compareTo("Special_Weekday Service") == 0){
                                    GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                    ArrayList<Route_service> route_services = data.getValue(t);
                                    all_stop_service_info.get(stop_name).addAll(route_services);
                                }
                            }
                        }
                        count_helper[0]++;
                        get_transit_schedule_finish(mapper, all_stop_service_info, stop_size_indicator, count_helper[0]);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    private static void get_transit_schedule_finish(HashMap<String,ArrayList<String>> mapper, HashMap<String, ArrayList<Route_service>> all_stop_service_info, int total_size, int current_size){
        // Mapper : 0 distance, 1 reachable stop for starting service, 2 reachable stop for end stop by backtrace, 3 transit route name, 4 starting service id, 6 is the transit time
        if(total_size == current_size){ // Collect all info together
            for(String s : mapper.keySet()){
                Log.d("Long Message ", "get_transit_schedule_finish: " + s);
            }
            Log.d("Long Message", "get_transit_schedule_finish: finish get called");
            Marker start = find_transit_helper_arr.get(0);
            Marker end = find_transit_helper_arr.get(1);
            StopInfoClass start_info = (StopInfoClass)start.getTag();
            StopInfoClass end_info = (StopInfoClass)end.getTag();
            String start_title = start.getTitle().split(":")[1];
            String end_title = end.getTitle().split(":")[1];
            ArrayList<Route_service> start_service = start_info.getTime_schedule();
            ArrayList<Route_service> end_service = end_info.getTime_schedule();
            ArrayList<String> all_info = new ArrayList<>();
            String transit_time = "12:00:00"; // Temp
            String depart_time = "0";
            String target_service_id = "";
            HashMap<String, ArrayList<String>> all_info_collections = new HashMap<>(); // Will store one quickest transfer info for each route


            for(String info : mapper.keySet()){
                ArrayList<String> collection = new ArrayList<>(); //Compare collection
                String service = info.split(":")[0];
                for(Route_service route_service : start_service){
                    if(route_service.service_id.compareTo(service) == 0){
                        Log.d("Long Message", "get_transit_schedule_finish: start time  " + route_service.getTime() + " route  " + route_service.getRoute_id());
                        collection.add(route_service.getTime()); // 0 for starting time
                        collection.add(route_service.getRoute_id()); // 1 for depart route id
                    }
                }
                for(Route_service route_service : all_stop_service_info.get(mapper.get(info).get(1))){
                    if(route_service.service_id.compareTo(service) == 0){
                        Log.d("Long Message", "get_transit_schedule_finish: transit time  " + route_service.getTime() + " route  " + route_service.getRoute_id() + "   Stop id   " + mapper.get(info).get(1));
                        transit_time = route_service.getTime();
                        collection.add(route_service.getTime()); // 2 for Take off time
                        //collection.add(mapper.get(info).get(1));
                    }
                }
                for(Route_service route_service : all_stop_service_info.get(mapper.get(info).get(2))){
                    if(route_service.getRoute_id().compareTo(mapper.get(info).get(3)) == 0){
                        int remaining = InfoWindowDialog.compareTime(transit_time, route_service.getTime());
                        if(remaining > 3){ //3 is temp
                            depart_time = route_service.getTime();
                            target_service_id = route_service.getService_id();
                            Log.d("Long Message", "get_transit_schedule_finish: depart time   " + depart_time + "  depart route id  " + mapper.get(info).get(3));
                            collection.add(depart_time); // 3 for transit depart time
                            collection.add(mapper.get(info).get(3)); // 4 for transit route name
                            collection.add(mapper.get(info).get(2)); // 5 for transit stop id
                            break;
                        }
                    }
                }
                for(Route_service route_service : end_service){
                    if(route_service.getService_id().compareTo(target_service_id) == 0){
                        Log.d("Long Message ", "get_transit_schedule_finish: " + route_service.getService_id() + "   route name   " + route_service.getRoute_id() +  "  arrive time  " + route_service.getTime());
                        collection.add(route_service.getTime()); // 6 for arrive time
                    }
                }
                if(all_info_collections.get(collection.get(1)) == null){
                    all_info_collections.put(collection.get(1), collection);
                }
                else{
                    int difference = InfoWindowDialog.compareTime(all_info_collections.get(collection.get(1)).get(6), collection.get(6));
                    if(difference < 0){
                        all_info_collections.put(collection.get(1), collection);
                    }
                }
            }

            Schdeule_info_class information_class = new Schdeule_info_class();
            information_class.setSchedule_info(all_info_collections);

        }
    }

    private static float calculateDistance(LatLng start_position, LatLng end_position){
        float[] results = new float[1];
        Location.distanceBetween(start_position.latitude, start_position.longitude,end_position.latitude, end_position.longitude,results);
        return results[0];
    }

    private static void get_bus_info_helper(ArrayList<String> stop_ids){

        final int total_size = stop_ids.size(); // The num of stops we need to query
        final int count_helper[] = {0};

        Calendar cal = Calendar.getInstance(); //Get current date
        cal.setTime(cal.getTime());
        final int time_identifier = cal.get(Calendar.DAY_OF_WEEK); // 0 for weekday, 1 for Saturday, 2 for Sunday

        final HashMap<String, ArrayList<Route_service>> all_stop_service_info = new HashMap<>(); // Hashmap to catch all stop service info
        final HashMap<String, Stop> all_stop_basic_info = new HashMap<>(); // Hashmap to catch all stop basic info, key is the stop_id

        for(final String stop_id : stop_ids){ //Query stop one by one
            DatabaseReference stop_info = db.getReference().child("Stops").child("Stop: " + stop_id);
            ArrayList<Route_service> service_temp = new ArrayList<>();
            all_stop_service_info.put(stop_id, service_temp);

            stop_info.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Stop stop = dataSnapshot.getValue(Stop.class);
                    all_stop_basic_info.put(stop_id, stop);
                    if(time_identifier == 7){ // Saturday
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            //Log.d("Long Message", "onDataChange: Saturday  " + data.getKey());
                            if(data.getKey().compareTo("Saturday Service") == 0){
                                GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                ArrayList<Route_service> route_services = data.getValue(t);
                                all_stop_service_info.get(stop_id).addAll(route_services);
                            }
                            else if(data.getKey().compareTo("Special_Saturday Service") == 0){
                                GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                ArrayList<Route_service> route_services = data.getValue(t);
                                all_stop_service_info.get(stop_id).addAll(route_services);
                            }
                        }
                    }
                    else if(time_identifier == 0){ // Sunday
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            //Log.d("Long Message", "onDataChange: Sunday " + data.getKey());
                            if(data.getKey().compareTo("Saturday Service") == 0){
                                GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                ArrayList<Route_service> route_services = data.getValue(t);
                                all_stop_service_info.get(stop_id).addAll(route_services);
                            }
                            else if(data.getKey().compareTo("Special_Sunday Service") == 0){
                                GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                ArrayList<Route_service> route_services = data.getValue(t);
                                all_stop_service_info.get(stop_id).addAll(route_services);
                            }
                        }
                    }
                    else{ //Workday
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            //Log.d("Long Message", "onDataChange: Saturday  " + data.getKey());
                            if(data.getKey().compareTo("Weekday Service") == 0){
                                GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                ArrayList<Route_service> route_services = data.getValue(t);
                                all_stop_service_info.get(stop_id).addAll(route_services);
                            }
                            else if(data.getKey().compareTo("Special_Weekday Service") == 0){
                                GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>(){};
                                ArrayList<Route_service> route_services = data.getValue(t);
                                all_stop_service_info.get(stop_id).addAll(route_services);
                            }
                        }
                    }
                    count_helper[0]++;
                    get_bus_info_helper_finish(all_stop_basic_info, all_stop_service_info, total_size, count_helper[0]);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    private static void get_bus_info_helper_finish(HashMap<String, Stop> all_stop_basic_info, HashMap<String, ArrayList<Route_service>> all_stop_service_info, int total_size, int current_size){
        if(current_size == total_size){
            //Define your operation
            for(String s : all_stop_basic_info.keySet()){
                Log.d("Long Message", "get_bus_info_helper_finish: " + all_stop_basic_info.get(s).getStop_id());
            }
            for(String s : all_stop_service_info.keySet()){
                Log.d("Long Message", "get_bus_info_helper_finish: " + all_stop_service_info.get(s).get(0).getRoute_id());
            }
        }
    }
    /*private static void show_transfer_stop_helper(ArrayList<String> stop_id_arr){
        for(int i = 0; i < stop_id_arr.size(); i++){
            DatabaseReference stop = db.getReference().child("Stops").child("Stop: " + stop_id_arr.get(i)).child();
        }
    }*/

    /*public static void getDirection(final LatLng current_position, final LatLng destination_position, final int max_distance){
        DatabaseReference table = db.getReference().child("Stops");
        table.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Stop> near_current_stop = new ArrayList<>();
                final ArrayList<Stop> near_destination_stop = new ArrayList<>();

                Stop current_stop;
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    current_stop = data.getValue(Stop.class);
                    float[] current_results = new float[1];
                    float[] destination_results = new float[1];
                    Location.distanceBetween(current_position.latitude, current_position.longitude,current_stop.getLatitude(), current_stop.getLongitude(),current_results);
                    Location.distanceBetween(destination_position.latitude, destination_position.longitude,current_stop.getLatitude(), current_stop.getLongitude(),destination_results);
                    if(current_results[0] < max_distance){
                        Stop temp = current_stop;
                        near_current_stop.add(temp);
                    }
                    if(destination_results[0] < max_distance){
                        Stop temp = current_stop;
                        near_destination_stop.add(temp);
                    }
                }

                current_stop_list = near_current_stop;
                destination_stop_list = near_destination_stop;
                //All possible stop info should be in the two arraylists now
                final HashSet<String> current_route = new HashSet<>();
                final HashSet<String> destination_route = new HashSet<>();
                final int current_counter[] = {0};
                final int total = near_current_stop.size() + near_destination_stop.size();

                for(int i = 0; i < near_current_stop.size(); i++){
                    DatabaseReference stop = db.getReference().child("Operation Route").child("Stop: " + near_current_stop.get(i).getStop_id());
                    stop.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                if(data.getKey().charAt(0) != 'T') { //If the first char is T, it means it is the transfer route section
                                    current_route.add(data.getValue(String.class));
                                }
                            }
                            current_counter[0]++;
                            getDirection_helper(current_route, destination_route, current_counter[0], total);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                for(int j = 0; j < near_destination_stop.size(); j++){
                    DatabaseReference stop = db.getReference().child("Operation Route").child("Stop: " + near_destination_stop.get(j).getStop_id());
                    stop.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                if(data.getKey().charAt(0) != 'T') { //If the first char is T, it means it is the transfer route section
                                    destination_route.add(data.getValue(String.class));
                                }
                            }
                            current_counter[0]++;
                            getDirection_helper(current_route, destination_route, current_counter[0], total);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static void getDirection_helper(HashSet<String> current, HashSet<String> destination, int counter, int total_count){
        if(counter == total_count){ //Means everything is there
            ArrayList<String> poosible_routes = new ArrayList<>();
            for(String s : current){
                if(destination.contains(s)){
                    poosible_routes.add(s);
                }
            }
            if(poosible_routes.size() != 0){ // Have direct route
                retrieveService(poosible_routes);
            }
        }
    }

    private static void retrieveService(ArrayList<String> possible_routes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        final GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>() {};
        DatabaseReference schedule;
        DatabaseReference special_schedule;
        final ArrayList<Route_service> all_close_service = new ArrayList<>();
        final HashMap<String, String> close_service = new HashMap<>();
        final int[] counter = {0};

        if(cal.get(Calendar.DAY_OF_WEEK) > 1 && cal.get(Calendar.DAY_OF_WEEK) < 7){ // Workdays
            for(int i = 0; i < current_stop_list.size(); i++){
                int stop_id = current_stop_list.get(i).getStop_id();
                DatabaseReference stop = db.getReference().child("Stops").child("Stop: " + stop_id);
                stop.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            if(data.getKey().compareTo("Weekday Service") == 0){
                                all_close_service.addAll(data.getValue(t));
                            }
                            else if(data.getKey().compareTo("Special_Weekday Service") == 0){
                                all_close_service.addAll(data.getValue(t));
                            }
                        }
                        retrieveServiceFinish(all_close_service, counter, current_stop_list.size());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //retrieveServiceHelper(schedule, special_schedule, all_close_service, counter, current_stop_list.size() * 2);
            }

        }
        else if(cal.get(Calendar.DAY_OF_WEEK) == 7){ //Saturday
            for(int i = 0; i < current_stop_list.size(); i++){
                int stop_id = current_stop_list.get(i).getStop_id();
                schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Saturday Service");
                special_schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Saturday Service");
            }

        }
        else{ //Sunday
            for(int i = 0; i < current_stop_list.size(); i++){
                int stop_id = current_stop_list.get(i).getStop_id();
                schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Sunday Service");
                special_schedule = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Sunday Service");
            }
        }
    }

    private static void retrieveServiceFinish(final ArrayList<Route_service> outcome, final int[] counter, int target){

    }

    private static void retrieveServiceHelper(DatabaseReference schedule, DatabaseReference special_schedule, final ArrayList<Route_service> outcome, final int[] counter, int target){
        final GenericTypeIndicator<ArrayList<Route_service>> t = new GenericTypeIndicator<ArrayList<Route_service>>() {};
        schedule.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() != 0){
                    outcome.addAll(dataSnapshot.getValue(t));
                }
                counter[0]++;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        special_schedule.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() != 0){
                    outcome.addAll(dataSnapshot.getValue(t));
                }
                counter[0]++;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/ //Not doable

}
