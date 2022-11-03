package com.example.weatherapiapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String PRE_QUERY_FOR_CITY_ID = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String POST_QUERY_APPID = "&appid=34eb2996dca002dbcec2dc78867e7707&units=metric";

    Context context;
    String cityID;
    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(String cityId);
    }

    public void getCityId(String cityName, VolleyResponseListener volleyResponseListener){
        String url = PRE_QUERY_FOR_CITY_ID + cityName + POST_QUERY_APPID;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        cityID = "";
                        try {
//                                    JSONObject cityInfo = response.getJSONObject("sys");
//                                    cityID = cityInfo.getString("id");
                            cityID = response.getString("id");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyResponseListener.onResponse(cityID);
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something Wrong!");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
        //return cityID;
    }





//    public List<WeatherReportModel> getCityForcastById(String cityId){
//
//    }

    public interface ForeCastByNameResponse {
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModel);
    }

    public void getCityForcastByName(String cityName, ForeCastByNameResponse foreCastByNameResponse) {
        List<WeatherReportModel> report = new ArrayList<>();
        String url = PRE_QUERY_FOR_CITY_ID + cityName + POST_QUERY_APPID;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            WeatherReportModel first_day = new WeatherReportModel();
                            JSONObject cityInfo = response.getJSONObject("main");

                            first_day.setTemp((float) cityInfo.getDouble("temp"));
                            first_day.setFeels_like((float) cityInfo.getDouble("feels_like"));
                            first_day.setTemp_min((float) cityInfo.getDouble("temp_min"));
                            first_day.setTemp_max((float) cityInfo.getDouble("temp_max"));
                            first_day.setPressure((float) cityInfo.getDouble("pressure"));
                            first_day.setHumidity(cityInfo.getInt("humidity"));
                            report.add(first_day);
                            foreCastByNameResponse.onResponse(report);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
