package de.anshaana.playground.java19;

public class RecordExample {

    record GPSPoint (double latitude, double longitude) {}

    public static void main(String args[]) {

        //old way
        GPSPoint gpsPoint = new GPSPoint(10, 11);
        System.out.println(gpsPoint.latitude);
        System.out.println(gpsPoint.longitude);

        // using record pattern
        if(gpsPoint instanceof GPSPoint(double latitude, double longitude)) {
            System.out.println(latitude);
            System.out.println(longitude);
        }
    }
}
