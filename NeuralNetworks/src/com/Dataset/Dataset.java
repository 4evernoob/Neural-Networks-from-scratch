/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dataset;

import java.util.Arrays;

/**
 *
 * @author Da supa pc jr
 */
public class Dataset {

    private double[][] data;
    private double[][] tags;

    public Dataset(double[][] d, double[][] t) {
        data = d.clone();
        tags = t.clone();
    }

    public Dataset(double[][] d) {
        data = d.clone();
        tags = new double[data.length][1];
    }

    public double[] getD(int a) {
        return getData()[a];
    }

    public double[] getT(int a) {
        return tags[a];
    }

    public int getNumberOfObservations() {
        return getData().length;
    }

    public void print() {
        for (int i = 0; i < getData().length; i++) {
            System.out.println(Arrays.toString(getData()[i]) + " " + Arrays.toString(tags[i]));

        }
    }

    /**
     * @return the data
     */
    public double[][] getData() {
        return data;
    }
}
