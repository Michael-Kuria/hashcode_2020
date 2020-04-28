package com.shujaa.Finals;

public class Task implements Comparable<Task> {

    int score;
    int n; // number of assembly points
    Point [] p; // assembly points
    int id;
    boolean done = false;

    public Task(int score, int n, int id){
        this.score = score;
        this.n  = n;
        this.id = id;

        p = new Point[n];
    }

    public int length(){
        return p[0].eDistance(p[p.length - 1]);
    }

    @Override
    public int compareTo(Task o) {
        return ((Integer)p[0].x).compareTo(o.p[0].x);
    }
}
