package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Helper {

    public static void parseToArray(ArrayList<ArrayList<Double>> geoArray, String line){
        String[] line_array = line.split(",");
        if(line_array[0].compareTo("stop_id") == 0 || line.isEmpty()){
            return;
        }
        else{
            ArrayList<Double> temp = new ArrayList<>();
            temp.add(Double.parseDouble(line_array[4]));
            temp.add(Double.parseDouble(line_array[5]));
            geoArray.add(temp);
        }
    }

    public static ArrayList<String[]> parseData(String urls){
        ArrayList<String[]> outcome = new ArrayList<>();
        try {
            URL url = new URL(urls);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] line_array = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    if(line_array[0].compareTo("stop_id") == 0 || line.isEmpty()){
                        //
                    }
                    else{
                        outcome.add(line_array);
                    }
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return outcome;
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected static ArrayList<ArrayList<Double>>  getGeodata(String urls) {
        ArrayList<ArrayList<Double>> outcome = new ArrayList<>();
        try {
            URL url = new URL(urls);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    parseToArray(outcome, line);
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return outcome;
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected static ArrayList<String[]>  getPositionData(String urls) {
        ArrayList<String[]> outcome = new ArrayList<>();
        try {
            URL url = new URL(urls);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;


                while ((line = bufferedReader.readLine()) != null) {
                    //parseToArray(outcome, line);
                    String [] temp = line.split(",");
                    if(temp[0].compareTo("19213") == 0 || temp[0].compareTo("19214")==0){
                        outcome.add(temp);
                    }
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return outcome;
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }


    public static ArrayList<ArrayList<Double>> parseGeoArray(String s){
        //Remove Bracket
        try{
            JSONObject reader = new JSONObject(s);
            JSONObject geo  = reader.getJSONObject("geometry");
            s = geo.getString("coordinates");
        }catch(Exception e){
            Log.d("Error", "onClick: " + e.getMessage());
        }

        String lat = "";
        String lon = "";
        boolean period = false;
        boolean outside_period = false;
        ArrayList<ArrayList<Double>> outcome = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '['){
                continue;
            }
            else if(s.charAt(i) == ','){
                if(outside_period){
                    outside_period = false;
                }
                else{
                    period = true;
                    outside_period = true;
                }
            }
            else if(period == false){
                lon += s.charAt(i);
            }
            else if(s.charAt(i) == ']'){
                if(lat != "" && lon != ""){
                    ArrayList<Double> temp = new ArrayList<>();
                    temp.add(Double.parseDouble(lat));
                    temp.add(Double.parseDouble(lon));
                    outcome.add(temp);
                }
                lat = "";
                lon = "";
                period = false;
            }
            else{
                lat += s.charAt(i);
            }
        }
        return outcome;
    }
}
