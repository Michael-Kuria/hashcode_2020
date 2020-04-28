package com.shujaa.Finals;

public class Point  {

    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int eDistance(Point p){
        return Math.abs(p.x - x) + Math.abs(p.y - y);
    }

    @Override
    public boolean equals(Object o){

        return ((Point)o).x == this.x && ((Point)o).y == this.y;

    }
}
