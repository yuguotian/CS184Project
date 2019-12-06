package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

public class Backup {
    //Fetch Code for stop info
    /* outcome = Helper.parseData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/stops.txt");
        for(int i = 0; i < outcome.size(); i++){
            String[] line_array = outcome.get(i);
            if(line_array.length == 0 || line_array[0].compareTo("stop_id") == 0){
                //
            }
            else{
                Stop temp = new Stop(Double.parseDouble(line_array[5]),Double.parseDouble(line_array[4]), line_array[2], Integer.parseInt(line_array[0]));
                table.child(line_array[0]).setValue(temp);
            }
        }*/

        //
    //Fetch Code for route info
    /*outcome = Helper.parseData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/routes.txt");

        for(int i = 0; i < outcome.size(); i++) {
        String[] line_array = outcome.get(i);
        if (line_array.length == 0 || line_array[0].compareTo("route_id") == 0) {
            //
        } else {
                Route temp = new Route(line_array[2], line_array[3], line_array[0]);
                table.child("Route:  " + line_array[0]).setValue(temp); // Two blank space
        }
    }*/


    //Fetch Code for trip info
    /*outcome = Helper.parseData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/trips.txt");
        for(int i = 0; i < outcome.size(); i++){
            String[] line_array = outcome.get(i);
            if(line_array.length == 0 || line_array[0].compareTo("route_id") == 0){
                //
            }
            else{
                Trip temp = new Trip(line_array[0],line_array[1], line_array[2], line_array[5], line_array[3], line_array[7]);
                table.child("Trip: " + line_array[2]).setValue(temp);
            }
        }*/

    //Fetch Code for route position
    /*        outcome = Helper.parseData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/shapes.txt");
        String currentRoute = "19213";
        ArrayList<LatLng> position = new ArrayList<>();
        for(int i = 0; i < outcome.size(); i++){
            String[] line_array = outcome.get(i);
            if(line_array.length == 0 || line_array[0].compareTo("shape_id") == 0){
                //
            }
            else if(line_array[0].compareTo(currentRoute) == 0){
                position.add(new LatLng(Double.parseDouble(line_array[1]), Double.parseDouble(line_array[2])));
            }
            else{
                table.child("Trip: " + currentRoute).setValue(position);
                position.clear();
                currentRoute = line_array[0];
                position.add(new LatLng(Double.parseDouble(line_array[1]), Double.parseDouble(line_array[2])));
            }
        }*/

    //Fetch data for special schedule
    /*        outcome = Helper.parseData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/calendar_dates.txt");
        for(int i = 0; i < outcome.size(); i++){
            String[] line_array = outcome.get(i);
            if(line_array.length == 0 || line_array[0].compareTo("service_id") == 0){
                //
            }
            else{
                DatabaseReference specific_trip = table.child("Trip " + line_array[0]);
                specific_trip.child("Date " + line_array[1]).setValue(line_array[1]);
            }
        }*/



    //Fetch data for route and position connection
    /*table.addListenerForSingleValueEvent(new ValueEventListener() {;
        @Override
        public void onDataChange(DataSnapshot data) {
            HashMap<String, LinkedHashSet<String>> map = new HashMap<>();
            for (DataSnapshot dataSnapshot : data.getChildren()) {
                Trip trip = dataSnapshot.getValue(Trip.class);
                if(map.get(trip.route_id) == null){
                    LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add(trip.shape_id);
                    map.put(trip.route_id, temp);
                }
                else{
                    LinkedHashSet<String> temp = map.get(trip.route_id);
                    temp.add(trip.shape_id);

                }
            }

            DatabaseReference myfb = db.getReference();
            for(String key : map.keySet()){
                DatabaseReference route = myfb.child("Routes");
                ArrayList<String> convert = new ArrayList<>(map.get(key));
                route.child("Route:  " + key).child("Shape").setValue(convert);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
                });*/

    //Fetch data for stop and service relation
    /*          final HashMap<String, LinkedHashSet<ArrayList<String>>> stop_route = new HashMap<>();
        table.addListenerForSingleValueEvent(new ValueEventListener() {;
            @Override
            public void onDataChange(DataSnapshot data) {
                for(DataSnapshot dataSnapshot1 : data.getChildren()){
                    String s = dataSnapshot1.getKey();
                    for(DataSnapshot dataSnapshot : dataSnapshot1.getChildren()){
                        Trip_Time time = dataSnapshot.getValue(Trip_Time.class);
                        if(stop_route.get(time.getStop_id()) == null){
                            LinkedHashSet<ArrayList<String>> temp = new LinkedHashSet<>();
                            ArrayList<String> input = new ArrayList<>();
                            input.add(s.substring(6));
                            input.add(time.getTime());
                            temp.add(input);
                            stop_route.put(time.getStop_id(), temp);
                        }
                        else{
                            ArrayList<String> input = new ArrayList<>();
                            input.add(s.substring(6));
                            input.add(time.getTime());
                            stop_route.get(time.getStop_id()).add(input);
                        }
                    }
                }

                DatabaseReference myfb = db.getReference();
                for(String target : stop_route.keySet()){
                    DatabaseReference stop = myfb.child("Stops");
                    ArrayList<ArrayList<String>> convert = new ArrayList<>(stop_route.get(target));
                    ArrayList<Route_service> route_services = new ArrayList<>();
                    for(int i =0; i < convert.size(); i++){
                        Route_service rs = new Route_service(convert.get(i).get(0), convert.get(i).get(1));
                        route_services.add(rs);
                    }


                    stop.child("Stop: " + target).child("Service").setValue(route_services);
                    //Log.d("Long Message", "onDataChange: " + target + "    " + rs);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    //Service Mapping
    /*        for(int i = 1 ; i < 1200; i++){
            DatabaseReference stop1 = table.child("Stop: " + i);
            stop1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Stop s = dataSnapshot.getValue(Stop.class);
                    if(s != null){
                        getStopSchedule_Info(s.getStop_id(), null);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    public static void getStopSchedule_Info(final int stop_id, final Marker m){ //Input is the numerical value, like 1, 2, Marker can be null

        final DatabaseReference stop = db.getReference().child("Stops").child("Stop: " + stop_id).child("Service");
        Query order = stop.orderByKey();
        final HashMap<String, ArrayList<String>> route_schedule = new HashMap<>();
        order.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String currentDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
                Long schedule_child_count = dataSnapshot.getChildrenCount();
                int[] schedule_count = {0};
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Route_service service = data.getValue(Route_service.class);

                    if(route_schedule.get(service.getTime()) == null){
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(service.getService_id());
                        route_schedule.put(service.getTime(), temp);
                    }
                    else{
                        route_schedule.get(service.getTime()).add(service.getService_id());
                    }

                }
                ArrayList<String> sortedKeys = new ArrayList<String>(route_schedule.keySet());
                Collections.sort(sortedKeys, new TimeComparator());

                Calendar cal = Calendar.getInstance();
                cal.setTime(cal.getTime());

                ArrayList<ArrayList<String>> outcome = new ArrayList<>();
                for(String s : sortedKeys){ //Holiday usually have Sunday service, except Thanksgiving and Christmas
                    for(int i = 0; i < route_schedule.get(s).size(); i++){
                        if(cal.get(Calendar.DAY_OF_WEEK) > 1 && cal.get(Calendar.DAY_OF_WEEK) < 7){ // Workdays
                            getRouteName(s, route_schedule.get(s).get(i), 1,  schedule_count, schedule_child_count, outcome, m, stop_id);
                        }
                        else if(cal.get(Calendar.DAY_OF_WEEK) == 7){ //Saturday
                            getRouteName(s, route_schedule.get(s).get(i), 2,  schedule_count, schedule_child_count, outcome, m, stop_id);
                        }
                        else{
                            getRouteName(s, route_schedule.get(s).get(i), 3,  schedule_count, schedule_child_count, outcome, m, stop_id);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //Check the route name and operation date by service id
    private static void getRouteName(final String time, final String service_id, final int allowed_service_date, final int[] counter, final Long child_counter, final ArrayList<ArrayList<String>> outcome, final Marker m, final int stop_id){
        DatabaseReference trip = db.getReference().child("Trips").child("Trip: " + service_id);
        trip.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Trip target = dataSnapshot.getValue(Trip.class);
                //if(Integer.parseInt(target.service_id) == allowed_service_date){
                    DatabaseReference name = db.getReference().child("Routes").child("Route:  " + target.getRoute_id());
                    final String direction = target.getDirection();
                    name.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Route route = dataSnapshot.getValue(Route.class);
                            //Log.d("Long Message", "onDataChange: " + time + "\n" + route.name + ":  " + route.name_id + "   Driection  " + direction);
                            ArrayList<String> str_arr = new ArrayList<>();
                            str_arr.add(time);
                            str_arr.add(route.name);
                            str_arr.add(route.name_id);
                            str_arr.add(direction);
                            str_arr.add(service_id);
                            str_arr.add(target.service_id);
                            outcome.add(str_arr);
                            manage_schedule_count(counter, child_counter, outcome, m, stop_id);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                //}
                //else{
                 //   manage_schedule_count(counter, child_counter, outcome, m, stop_id);
                //}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static void manage_schedule_count(int schedule_count[], Long schedule_child_count, ArrayList<ArrayList<String>> outcome, Marker m, int stop_id){ //Used for checking counts
        schedule_count[0]++;
        if(schedule_count[0] == schedule_child_count){ //It means the arraylist is ready for use
            manage_stop_schedule_info(outcome, m, stop_id);
        }
    }

    private static void manage_stop_schedule_info(ArrayList<ArrayList<String>> outcome, Marker stop, int stop_id){ //Define your operations there
        //Now every qualified schedule is in the arraylist: outcome
        //index 0 is the time, 1 is the numerical id, 2 is the name, 3 is the direction
        //Log.d("Long Message", "manage_stop_schedule_info: " + schedule_result);
        /*if(stop != null){
            StopInfoClass info = new StopInfoClass();
            info.setTime_schedule(outcome);
            stop.setTag(info);
        }
        /*else if(stop_id != 0){
            DatabaseReference ff = db.getReference().child("Operation Route").child("Stop: " + stop_id);
            ArrayList<String> removed = new ArrayList<>();
            boolean repeat = false;
            for(int i = 0; i< outcome.size(); i++){
                for(int j = 0; j < removed.size(); j++){
                    if(outcome.get(i).get(1).compareTo(removed.get(j)) == 0){
                        repeat = true;
                        break;
                    }
                }
                if(!repeat){
                    removed.add(outcome.get(i).get(1));
                }
                repeat = false;
            }
            ff.setValue(removed);
        }
       Log.d("Long Message", "manage_stop_schedule_info: outcome : " + outcome.size());
        ArrayList<Route_service> weekday = new ArrayList<>();
        ArrayList<Route_service> Saturday = new ArrayList<>();
        ArrayList<Route_service> Sunday = new ArrayList<>();
        ArrayList<Route_service> Special_Weekday= new ArrayList<>();
        ArrayList<Route_service> Special_Saturday = new ArrayList<>();
        ArrayList<Route_service> Special_Sunday = new ArrayList<>();
        ArrayList<Route_service> Special = new ArrayList<>();
        for(int i = 0; i < outcome.size(); i++){
            Route_service rs = new Route_service(outcome.get(i).get(4), outcome.get(i).get(0),outcome.get(i).get(3), outcome.get(i).get(2), outcome.get(i).get(1));
            if(outcome.get(i).get(5).compareTo("1") == 0){
                //outcome.get(i).remove(5);
                //weekday.add(rs);
            }
            else if(outcome.get(i).get(5).compareTo("2") == 0){
                //outcome.get(i).remove(5);
                //Saturday.add(rs);
            }
            else if(outcome.get(i).get(5).compareTo("3") == 0){
                //outcome.get(i).remove(5);
                //Sunday.add(rs);
            }
            else if (outcome.get(i).get(5).compareTo("7501") == 0 || outcome.get(i).get(5).compareTo("30701") == 0 || outcome.get(i).get(5).compareTo("1101") == 0 || outcome.get(i).get(5).compareTo("26201") == 0 || outcome.get(i).get(5).compareTo("25101") == 0
                    || outcome.get(i).get(5).compareTo("24801") == 0 || outcome.get(i).get(5).compareTo("25501") == 0 || outcome.get(i).get(5).compareTo("25601") == 0 || outcome.get(i).get(5).compareTo("25801") == 0
                    || outcome.get(i).get(5).compareTo("26001") == 0 || outcome.get(i).get(5).compareTo("26401") == 0 || outcome.get(i).get(5).compareTo("26701") == 0 || outcome.get(i).get(5).compareTo("26801") == 0
                    || outcome.get(i).get(5).compareTo("27101") == 0 || outcome.get(i).get(5).compareTo("43001") == 0 ){
                outcome.get(i).remove(5);
                Special_Weekday.add(rs);
            }
            else if (outcome.get(i).get(5).compareTo("43202") == 0 || outcome.get(i).get(5).compareTo("29702") == 0){
                outcome.get(i).remove(5);
                Special_Saturday.add(rs);
            }
            else if (outcome.get(i).get(5).compareTo("43303") == 0 || outcome.get(i).get(5).compareTo("30203") == 0 || outcome.get(i).get(5).compareTo("29903") == 0 ){
                outcome.get(i).remove(5);
                Special_Sunday.add(rs);
            }
            else{
                outcome.get(i).remove(5);
                Special.add(rs);
            }
        }
        DatabaseReference service = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Weekday Service");
        service.setValue(Special_Weekday);
        service = db.getReference().child("Stops").child("Stop: " + stop_id).child("Saturday Service");
        service.setValue(Saturday);
        service = db.getReference().child("Stops").child("Stop: " + stop_id).child("Sunday Service");
        service.setValue(Sunday);
        service = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Saturday Service");
        service.setValue(Special_Saturday);
        service = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special_Sunday Service");
        service.setValue(Special_Sunday);
        service = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special Service");
        service.setValue(Special);

        */


    //For stop_route mapping (transfer schedule)
    /*        for(int i = 1000 ; i < 1200; i++){
            DatabaseReference stop1 = table.child("Stop: " + i);
            stop1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final Stop s = dataSnapshot.getValue(Stop.class);
                    if(s != null){
                        DatabaseReference route_mapping = db.getReference().child("Operation Route").child("Stop: " + s.getStop_id());
                        route_mapping.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                ArrayList<String> temp = new ArrayList<>();
                                for(DataSnapshot data : dataSnapshot.getChildren()){
                                    if(data.getKey().charAt(0) != 'T'){
                                        String xx = data.getValue(String.class);
                                        temp.add(xx);
                                    }
                                }
                                ArrayList<String> route = new ArrayList<>();

                                LatLng ll = new LatLng(s.getLatitude(), s.getLongitude());
                                getNearByStop(ll, 400, route, null, temp, s.getStop_id());
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


            public static void getNearByStop(final LatLng targetPosition, final int max_distance, final ArrayList<String> nearby_stop_route, final ArrayList<String> target_stop_route, final ArrayList<String> self_route, final int target_stop_id){
        DatabaseReference table = db.getReference().child("Stops");
        //final ArrayList<Stop> result_stop = new ArrayList<>();
        user_stop.clear();
        table.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long[] counter = {new Long(0)};
                ArrayList<Stop> nearby_stop = new ArrayList<>();
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Stop current_stop = data.getValue(Stop.class);
                    float[] results = new float[1];
                    Location.distanceBetween(targetPosition.latitude, targetPosition.longitude,current_stop.getLatitude(), current_stop.getLongitude(),results);
                    if(results[0] < max_distance){
                        nearby_stop.add(current_stop);
                        user_stop.add(current_stop);
                    }
                    if(nearby_stop.size() > 6){
                        break;
                    }
                }
                for(int i = 0; i < nearby_stop.size(); i++){
                    checkRoute(nearby_stop.get(i).getStop_id(), nearby_stop_route, target_stop_route,counter, nearby_stop.size(), self_route, target_stop_id);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public static void checkRoute(final int stop_id, final ArrayList<String> route, final ArrayList<String> target_stop_route, final Long[] stop_counter, final int target, final ArrayList<String> self_route, final int target_stop_id){
        DatabaseReference stop_route = db.getReference().child("Operation Route").child("Stop: " + stop_id);
        stop_route.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    //GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                    //ArrayList<String> temp = data.getValue(t);
                    if(target_stop_route == null || target_stop_route.size() == 0){
                        if(data.getKey().charAt(0) != 'T'){
                            String temp = data.getValue(String.class);
                            boolean duplicate = false;
                            for(String s : route){
                                if(s.compareTo(temp) == 0){
                                    duplicate = true;
                                }
                            }
                            if(!duplicate){
                                route.add(temp);
                            }
                        }
                    }
                    else{
                        if(data.getKey().charAt(0) != 'T'){
                            String temp = data.getValue(String.class);
                            boolean duplicate = false;
                            for(String s : target_stop_route){
                                if(s.compareTo(temp) == 0){
                                    duplicate = true;
                                }
                            }
                            if(!duplicate){
                                target_stop_route.add(temp);
                            }
                        }

                    }
                }
                stop_counter[0]++;

                if(stop_counter[0] == target){ //Route ready
                    if(target_stop_route == null || target_stop_route.size() == 0){ //Need to get the target stop route info
                        //ArrayList<String> target_stop = new ArrayList<>();
                        //target_stop.add("temp");
                        //getNearByStop(marker_location, 500, route, target_stop);
                        ArrayList<String> transfer_Route = new ArrayList<>();
                        for(int i = 0; i < route.size(); i++){
                            boolean duplicate = false;
                            for(int j = 0; j < self_route.size(); j++){
                                if(route.get(i).compareTo(self_route.get(j)) == 0){
                                    duplicate = true;
                                    break;
                                }
                            }
                            if(!duplicate){
                                transfer_Route.add(stop_id + " : " + route.get(i));
                            }
                        }
                        DatabaseReference transfer_route = db.getReference().child("Operation Route").child("Stop: " + target_stop_id).child("Transfer Route");
                        transfer_route.setValue(transfer_Route);
                    }
                    /*else{
                        ArrayList<String> possible_route = new ArrayList<>();
                        for(int i = 0; i < route.size(); i++){
                            for(int j = 0; j < target_stop_route.size(); j++){
                                if(route.get(i).compareTo(target_stop_route.get(j)) == 0){
                                    possible_route.add(route.get(i));
                                    Log.d("Long message", "onDataChange: " + route.get(i));
                                }
                            }
                        }

                        if(possible_route.size() == 0){ //No direct route
                            //find_Transit(route, target_stop_route);
                        }
                    }
    //Log.d("Long Message", "onDataChange: " + route);
}
            }

@Override
public void onCancelled(DatabaseError databaseError) {

        }
        });
        }




        */




    //Unused functions
        /*private static void find_Transit(ArrayList<String> route, ArrayList<String> target){
        try{
            final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Calendar now = Calendar.getInstance();
            final String time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);

            final ArrayList<String> service_list = new ArrayList<>();
            int difference = 60;
            if(user_stop.size() != 0){
                for(int i = 0; i < user_stop.size(); i++){
                    DatabaseReference dr = db.getReference().child("Stops").child("Stop: " + i).child("Service");
                    dr.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                Route_service s = data.getValue(Route_service.class);
                                try{
                                    Date current_time = format.parse(time);
                                    Date service_time = format.parse(s.getTime());
                                    Long difference = service_time.getTime() - current_time.getTime();
                                    long diffMinutes = difference / (60 * 1000) % 60;
                                    if(diffMinutes > 0 && diffMinutes < difference){
                                        service_list.add(s.getService_id());
                                    }
                                }catch (Exception e){
                                    Log.d("Message", "find_Transit: " + e.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

        }catch (Exception e){
            Log.d("Message", "find_Transit: " + e.getMessage());
        }
    }*/



        //Special schedule
    //Normal schedule: 1 for workdays, 2 for Saturday, 3 for Sunday
    /*public static class Unqiue_shedule implements Serializable{
        private String service_id;
        private String date;

        public Unqiue_shedule(){}

        public Unqiue_shedule(String service_id, String date){
            this.service_id = service_id;
            this.date = date;
        }

        public String getService_id(){
            return service_id;
        }

        public String getDate(){
            return date;
        }
    }*/



    //Route mapping
    /*
        public static void getStopSchedule_Info(final int stop_id, final Marker m){ //Input is the numerical value, like 1, 2, Marker can be null

        final DatabaseReference stop = db.getReference().child("Stops").child("Stop: " + stop_id).child("Special Service");
        Query order = stop.orderByKey();
        final HashMap<String, ArrayList<String>> route_schedule = new HashMap<>();
        order.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String currentDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
                Long schedule_child_count = dataSnapshot.getChildrenCount();
                int[] schedule_count = {0};
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Route_service service = data.getValue(Route_service.class);

                    if(route_schedule.get(service.getTime()) == null){
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(service.getService_id());
                        route_schedule.put(service.getTime(), temp);
                    }
                    else{
                        route_schedule.get(service.getTime()).add(service.getService_id());
                    }

                }
                ArrayList<String> sortedKeys = new ArrayList<String>(route_schedule.keySet());
                Collections.sort(sortedKeys, new TimeComparator());

                Calendar cal = Calendar.getInstance();
                cal.setTime(cal.getTime());

                ArrayList<ArrayList<String>> outcome = new ArrayList<>();
                for(String s : sortedKeys){ //Holiday usually have Sunday service, except Thanksgiving and Christmas
                    for(int i = 0; i < route_schedule.get(s).size(); i++){
                        if(cal.get(Calendar.DAY_OF_WEEK) > 1 && cal.get(Calendar.DAY_OF_WEEK) < 7){ // Workdays
                            getRouteName(s, route_schedule.get(s).get(i), 1,  schedule_count, schedule_child_count, outcome, m, stop_id);
                        }
                        else if(cal.get(Calendar.DAY_OF_WEEK) == 7){ //Saturday
                            getRouteName(s, route_schedule.get(s).get(i), 2,  schedule_count, schedule_child_count, outcome, m, stop_id);
                        }
                        else{
                            getRouteName(s, route_schedule.get(s).get(i), 3,  schedule_count, schedule_child_count, outcome, m, stop_id);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //Check the route name and operation date by service id
    private static void getRouteName(final String time, final String service_id, final int allowed_service_date, final int[] counter, final Long child_counter, final ArrayList<ArrayList<String>> outcome, final Marker m, final int stop_id){
        DatabaseReference trip = db.getReference().child("Trips").child("Trip: " + service_id);
        trip.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Trip target = dataSnapshot.getValue(Trip.class);
                //if(Integer.parseInt(target.service_id) == allowed_service_date){
                DatabaseReference name = db.getReference().child("Routes").child("Route:  " + target.getRoute_id());
                final String direction = target.getDirection();
                name.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Route route = dataSnapshot.getValue(Route.class);
                        //Log.d("Long Message", "onDataChange: " + time + "\n" + route.name + ":  " + route.name_id + "   Driection  " + direction);
                        ArrayList<String> str_arr = new ArrayList<>();
                        str_arr.add(time);
                        str_arr.add(route.name);
                        str_arr.add(route.name_id);
                        str_arr.add(direction);
                        str_arr.add(service_id);
                        str_arr.add(target.service_id);
                        outcome.add(str_arr);
                        manage_schedule_count(counter, child_counter, outcome, m, stop_id);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //}
                //else{
                //   manage_schedule_count(counter, child_counter, outcome, m, stop_id);
                //}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static void manage_schedule_count(int schedule_count[], Long schedule_child_count, ArrayList<ArrayList<String>> outcome, Marker m, int stop_id){ //Used for checking counts
        schedule_count[0]++;
        if(schedule_count[0] == schedule_child_count){ //It means the arraylist is ready for use
            manage_stop_schedule_info(outcome, m, stop_id);
        }
    }

    private static void manage_stop_schedule_info(ArrayList<ArrayList<String>> outcome, Marker stop, int stop_id){ //Define your operations there
        //Now every qualified schedule is in the arraylist: outcome
        //index 0 is the time, 1 is the numerical id, 2 is the name, 3 is the direction
        //Log.d("Long Message", "manage_stop_schedule_info: " + schedule_result);
        if(stop != null){
            //StopInfoClass info = new StopInfoClass();
            //info.setTime_schedule(outcome);
            //stop.setTag(info);
        }
        else if(stop_id != 0){
            DatabaseReference ff = db.getReference().child("Operation Route").child("Stop: " + stop_id);
            ArrayList<String> removed = new ArrayList<>();
            boolean repeat = false;
            for(int i = 0; i< outcome.size(); i++){
                for(int j = 0; j < removed.size(); j++){
                    if(outcome.get(i).get(1).compareTo(removed.get(j)) == 0){
                        repeat = true;
                        break;
                    }
                }
                if(!repeat){
                    removed.add(outcome.get(i).get(1));
                }
                repeat = false;
            }
            ff.setValue(removed);
        }
    }
     */
}


