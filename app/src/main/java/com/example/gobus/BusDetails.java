package com.example.gobus;

public class BusDetails {

    private String BusNumber;
    private String Busroute;
    private String BusOwnerName;
    private int BusOwnerPhonenumber;

    public BusDetails() {
    }

    public BusDetails(String busNumber, String busroute, String busOwnerName, int busOwnerPhonenumber) {
        BusNumber = busNumber;
        Busroute = busroute;
        BusOwnerName = busOwnerName;
        BusOwnerPhonenumber = busOwnerPhonenumber;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String busNumber) {
        BusNumber = busNumber;
    }

    public String getBusroute() {
        return Busroute;
    }

    public void setBusroute(String busroute) {
        Busroute = busroute;
    }

    public String getBusOwnerName() {
        return BusOwnerName;
    }

    public void setBusOwnerName(String busOwnerName) {
        BusOwnerName = busOwnerName;
    }

    public int getBusOwnerPhonenumber() {
        return BusOwnerPhonenumber;
    }

    public void setBusOwnerPhonenumber(int busOwnerPhonenumber) {
        BusOwnerPhonenumber = busOwnerPhonenumber;
    }
}
