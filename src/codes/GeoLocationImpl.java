package codes;

import api.GeoLocation;

/**
 * represents a geo location <x,y,z>, (aka Point3D data).
 */
public class GeoLocationImpl implements GeoLocation {
    private double x, y, z;

    //constructor
    public GeoLocationImpl(String geo) {
        String[] arrOfStr = geo.split(",");
        this.x = Double.parseDouble(arrOfStr[0]);
        this.y = Double.parseDouble(arrOfStr[1]);
        this.z = Double.parseDouble(arrOfStr[2]);
    }

    public GeoLocationImpl(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //copy constructor
    public GeoLocationImpl(GeoLocation other) {
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();
    }

    //getter
    @Override
    public double x() {
        return x;
    }

    //getter
    @Override
    public double y() {
        return y;
    }

    //getter
    @Override
    public double z() {
        return z;
    }

    //simple function to calculate distance from other GeoLocation
    @Override
    public double distance(GeoLocation g) {
        double x = Math.pow(g.x() - this.x, 2);
        double y = Math.pow(g.y() - this.y, 2);
        double z = Math.pow(g.z() - this.z, 2);
        return Math.sqrt(x + y + z);
    }

    //to string
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y + ", Z = " + z;
    }
}