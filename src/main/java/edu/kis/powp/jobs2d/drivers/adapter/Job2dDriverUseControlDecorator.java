package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;
//import edu.kis.powp.jobs2d.file.DataFile;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

// import java.io.FileNotFoundException;

public class Job2dDriverUseControlDecorator implements Job2dDriver {

    private Job2dDriver job2dDriver;

    private double distance;
    private int prevX, prevY;

    public Job2dDriverUseControlDecorator(Job2dDriver job2dDriver) {
        this.job2dDriver = job2dDriver;
        distance = 0;
        prevX = 0;
        prevY = 0;
    }

    private void calculateDistance(int nextX, int nextY){

       double currentDistance = sqrt((pow(nextX - this.prevX,2) + pow(nextY - this.prevY,2)));
       this.distance += currentDistance;

       //publisher.notifyObservers();

}

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void setPosition(int x, int y) {
        job2dDriver.setPosition(x,y);
        this.prevX = x;
        this.prevY = y;

    }

    @Override
    public void operateTo(int x, int y) {
        calculateDistance(x,y);
        job2dDriver.operateTo(x,y);
        this.prevY = y;
        this.prevX = x;
    }
}
