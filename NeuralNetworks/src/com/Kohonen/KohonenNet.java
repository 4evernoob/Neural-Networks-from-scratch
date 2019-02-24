/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Kohonen;

import com.Dataset.Dataset;
import com.Util.Util;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Da supa pc jr
 */
public class KohonenNet {

    private double[][] weights;
    private int neuronNumber;
    private int numberOfWeights;
    public int epochT = 30;
    final double coeffT = 1.0;

    public KohonenNet(int nN, int nW) {
        weights = new double[nN][nW];
        neuronNumber = nN;
        numberOfWeights = nW;
        init();
    }

    public void train(Dataset data, int epochs, double learR) {
        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < data.getNumberOfObservations(); j++) {
                int winna = query(data.getD(j));
               
                for (int i1 = 0; i1 < getWeights().length; i1++) {
               
                    for (int j1 = 0; j1 < getWeights()[0].length; j1++) {
                        double ne = neighborhood(winna, i1, i, epochT);
                        weights[i1][j1] = getWeights()[i1][j1] + learR * (Double.isNaN(ne) ? 1 : ne) * (data.getD(j)[j1] - getWeights()[i1][j1]);
               
                    }
                }
            }
            learR = learR / (learR + 1);
        }
    }

    public int query(double[] inp) {
        double dist = Double.POSITIVE_INFINITY;
        int c = -1;

        for (int i = 0; i < neuronNumber; i++) {
            double tmp = Util.dist(getWeights()[i], inp);
            if (tmp < dist) {
                dist = tmp;
                c = i;
            }
        }
        return c;
    }

    private void init() {
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < getWeights().length; i++) {
            for (int j = 0; j < getWeights()[0].length; j++) {
                weights[i][j] = 0.5;

            }

        }

    }

    public void print() {
        for (double[] weight : getWeights()) {
            System.out.println(Arrays.toString(weight));
        }
    }

    public double neighborhood(int u, int v, int s, int t) {
        double result;

        double exponent = -((neuronDistance(u, v) * neuronDistance(u, v)) / (2 * (neighborhoodRadius(coeffT, s, epochT) * neighborhoodRadius(coeffT, s, epochT))));
        result = Math.exp(exponent);

        return result;
    }

    public double neuronDistance(int u, int v) {
        return Math.abs(u - v);
    }

    public double neighborhoodRadius(double coeff, int epoch, int refEpoch) {
        return coeff * Math.exp(-((double) epoch / (double) refEpoch));
    }

    /**
     * @return the weights
     */
    public double[][] getWeights() {
        return weights;
    }

}
