package com.shujaa.Finals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int w,h,r,m,t,l;
    static Point [] mounts;
    static Task [] tasks;

    // answers
    //Number of robotic arms
    ArrayList<RoboticArm> arms = new ArrayList<>();

    public static Task binarySearch(Point p){

        int low = 0;
        int high = tasks.length - 1;

        while(low <= high){
            int mid = (high + low)/2;
            int dist = p.eDistance(tasks[mid].p[0]);

            if(dist <= 10){
                return tasks[mid];
            }else if(dist > 10){
                low = mid + 1;
            }else{
                high = mid - 1;
            }

        }
        return null;
    }


    public static void solve(){
        Point start = mounts[0];

        int i = 0;
        RoboticArm arm = new RoboticArm();

        while(l > 0){
            // binary search for the best task to begin with according to closeness in the x direction
            // complete the task and if there are steps still remaining find another task

            Task k = binarySearch(start);

            if(l - k.length() > 0){
               // arm.
            }



        }


    }

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);


        w = scan.nextInt();
        h = scan.nextInt();
        r = scan.nextInt();
        m = scan.nextInt();
        t = scan.nextInt();
        l = scan.nextInt();

        mounts = new Point[m];

        for(int i = 0; i < m; i ++){
            mounts[i] = new Point(scan.nextInt(), scan.nextInt());
        }

        tasks = new Task[t];
        for(int i = 0; i < t; i ++){
            int score = scan.nextInt();
            int n = scan.nextInt();
            Point [] p = new Point[n];
            for(int j = 0; j < n; j ++){
                p[i] = new Point(scan.nextInt(), scan.nextInt());
            }
            tasks[i] = new Task(score,n,i);
            tasks[i].p = p;
        }
        Arrays.sort(tasks);

        solve();

    }
}
