package com.example.gobus;

public class BusTimes {

    private String BusNumber;
    private String Route;
    private String Time;
    private String StartingPlace;

    public BusTimes() {
    }

    public BusTimes(String busNumber, String route, String time, String startingPlace) {
        BusNumber = busNumber;
        Route = route;
        Time = time;
        StartingPlace = startingPlace;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String busNumber) {
        BusNumber = busNumber;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStartingPlace() {
        return StartingPlace;
    }

    public void setStartingPlace(String startingPlace) {
        StartingPlace = startingPlace;
    }
}