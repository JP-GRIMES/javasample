//WRITTEN BY JONATHAN GRIMES
//SHEEP CLASS

import java.io.*;
import java.util.*;

class sheep implements Comparable<sheep> {

    // instance variables
    String name;
    int shearingTime;
    int arrivalTime;

    // constructors
    public sheep() {
        this.name = "none";
        this.shearingTime = 0;
    }

    public sheep(String aName, int aShearTime, int aArrTime) {
        this.setName(aName);
        this.setShearTime(aShearTime);
        this.setArrTime(aArrTime);
    }

    // get methods
    public String getName() {
        return this.name;
    }

    public int getShearTime() {
        return this.shearingTime;
    }

    public int getArrTime() {
        return this.arrivalTime;
    }

    // set methods
    public void setArrTime(int aArrTime) {
        this.arrivalTime = aArrTime;
    }

    public void setShearTime(int shearTime) {
        this.shearingTime = shearTime;
    }

    public void setName(String aName) {
        if (aName != null)
            this.name = aName;
        else
            this.name = "none";
    }

    // override compareTo method
    @Override
    public int compareTo(sheep other) {
        if (this.shearingTime != other.shearingTime) {
            return this.shearingTime - other.shearingTime;
        }
        return this.name.compareTo(other.name);
    }

    // to string method
    public String toString() {
        return "Name: " + this.name + ",\tShear Time: " + this.shearingTime + ",\tArrival Time: " + this.arrivalTime;
    }
}