package com.example.workscheduler;

import java.util.*;

public class Algo {
    public String[] Calculate(String[] time,String[] days,String[] Imp,int size){
        // Creating time's double array and sorted set
        double[] double_time = new double[size];
        SortedSet<Double> t = new TreeSet<>();

        // Creating days' int array and sorted set
        int[] int_days = new int[size];
        SortedSet<Integer> d = new TreeSet<>();

        // Creating Imp's int array
        int[] int_imp = new int[size];

        // assign values in arrays and sets
        for (int i = 0; i < size; i++) {
            double_time[i] = Double.parseDouble(time[i]);
            t.add(double_time[i]);
            int_days[i] = Integer.parseInt(days[i]);
            d.add(int_days[i]);
            int_imp[i] = Integer.parseInt(Imp[i]);
        }

        // introducing an intermediate array for priority calculation
        int[] priority = new int[size];

        // Now searching for the int set value in int_day array to give priority number in priority array
        int n = 1;
        for(int i:d)
        {
            for (int j = 0; j < size; j++) {
                if(i==int_days[j])
                {
                    priority[j] = n;
                    priority[j] *= 10;
                    int_days[j] = -100;
                }
            }
            n++;
        }

        // Now update the priority array according to importance
        for(int index=0;index<size;index++)
        {
            priority[index] += int_imp[index];
            priority[index] *= 10;
        }

        // Now searching for the double set value in double_time array to give priority number in priority array
        n=1;
        for(double k:t)
        {
            for (int j = 0; j < size; j++) {
                if (k == double_time[j]) {
                    priority[j] += n;
                    double_time[j] = -100.0;
                }
            }
            n++;
        }

        // Creating a string array p to return for printing purpose
        String[] p = new String[size];
        for (int l = 0; l < size; l++) {
             p[l] = String.valueOf(priority[l]);
        }

        // Sort the priority array to give sequence no. to final string
        n=1;
        Arrays.sort(priority);
        for(int i:priority)
        {
            for (int j = 0; j < size; j++) {
                if(String.valueOf(i).equals(p[j]))
                {
                    p[j] = String.valueOf(n);
                }
            }
            n++;
        }

        return p;
    }

}
