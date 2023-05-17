package de.anshaana.playground.java19;

public class NestedRecordExample {

    record GPSPoint (double latitude, double longitude) {}

    record TwoLocations (GPSPoint point1, GPSPoint point2) {}

    public static void main(String args[]) {

        TwoLocations locations = new TwoLocations(new GPSPoint(1, 2), new GPSPoint(3,4));
        if(locations instanceof
                TwoLocations (GPSPoint (double latitude1, double longitude1),
                              GPSPoint (double latitude2, double longitude2))) {
            System.out.println(latitude1 + " " + longitude1);
            System.out.println(latitude2 + " " + longitude2);
        }
    }
}
