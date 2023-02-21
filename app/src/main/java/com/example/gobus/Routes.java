package com.example.gobus;

public class Routes {

    private int RouteId ;
    private String Route;
    private String StartAndDestination;

    public Routes() {
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getStartAndDestination() {
        return StartAndDestination;
    }

    public void setStartAndDestination(String startAndDestination) {
        StartAndDestination = startAndDestination;
    }
}
