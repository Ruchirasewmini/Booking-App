package com.example.gobus;

public class SearchRoutes {

    private int SearchRouteId;
    private String Location;
    private String Destination;
    private String Route_Numbers;
    private  String Distance;
    private String Ticket_Prie;
    private String Time;

    public SearchRoutes() {
    }

    public SearchRoutes(int searchRouteId, String location, String destination, String route_Numbers, String distance, String ticket_Prie, String time) {
        SearchRouteId = searchRouteId;
        Location = location;
        Destination = destination;
        Route_Numbers = route_Numbers;
        Distance = distance;
        Ticket_Prie = ticket_Prie;
        Time = time;
    }

    public int getSearchRouteId() {
        return SearchRouteId;
    }

    public void setSearchRouteId(int id) {
        this.SearchRouteId = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getRoute_Numbers() {
        return Route_Numbers;
    }

    public void setRoute_Numbers(String route_Numbers) {
        Route_Numbers = route_Numbers;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getTicket_Prie() {
        return Ticket_Prie;
    }

    public void setTicket_Prie(String ticket_Prie) {
        Ticket_Prie = ticket_Prie;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
