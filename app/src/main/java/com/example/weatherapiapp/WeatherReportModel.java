package com.example.weatherapiapp;

public class WeatherReportModel {
    private Float temp;
    private Float feels_like;
    private Float temp_min;
    private Float temp_max;
    private Float pressure;
    private int humidity;

    public WeatherReportModel(Float temp, Float feels_like, Float temp_min, Float temp_max, Float pressure, int humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public WeatherReportModel(){

    }

    @Override
    public String toString() {
        return  "Temp= " + temp + "\n" +
                "Feels Like = " + feels_like +"\n" +
                "Temp_min = " + temp_min +"\n" +
                "Temp_max = " + temp_max +"\n" +
                "Pressure = " + pressure +"\n" +
                "Humidity = " + humidity;
    }

    public Double convertToCelsius(Double temp){
        temp = (temp - 32) * 0.5559;
        return temp;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Float feels_like) {
        this.feels_like = feels_like;
    }

    public Float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Float temp_min) {
        this.temp_min = temp_min;
    }

    public Float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Float temp_max) {
        this.temp_max = temp_max;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
