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
public class KohonenNet2D {

    private double[][] weights;
    private int numberX;
    private int numberY;
    private int numberOfWeights;
    public int epochT = 25;
    final double coeffT = 1.0;

    public KohonenNet2D(int nX,int nY, int nW) {
        weights = new double[nX*nY][nW];
        numberX= nX;
        numberY=nY;
        numberOfWeights = nW;
        init();
    }

    public void train(Dataset data, int epochs, double learR) {
        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < data.getNumberOfObservations(); j++) {
                int[] winna = query(data.getD(j));
                //  if(winna==-1){
                // break;
                //}
                // System.out.println("winner "+winna+" i "+ i);
                for (int i1 = 0; i1 < numberX; i1++) {
                    for (int j1 = 0; j1 <numberY; j1++) {
                        for (int k1 = 0; k1 < getWeights()[0].length; k1++) {
                        double ne = neighborhood(winna,new int[]{i1,j1}, i, epochT);
                        weights[i1*numberX+j1][k1] = getWeights()[i1*numberX+j1][k1] + learR * (Double.isNaN(ne) ? 1 : ne) * (data.getD(j)[k1] - getWeights()[i1*numberX+j1][k1]);
                        // weights[winna][j1]=getWeights()[winna][j1]+learR*(data.getD(j)[j1]-getWeights()[winna][j1]);
                    }
                        
                    }
                    //  System.out.println(Arrays.toString(weights[i1]));
                    
                }
            }
            learR = learR / (learR + 1);
        }
    }

    public int[] query(double[] inp) {
        double dist = Double.POSITIVE_INFINITY;
        int cx = -1;
int cy = -1;
        for (int i = 0; i < numberX; i++) {
            for (int j = 0; j < numberY; j++) {
                
                 double tmp = Util.dist(getWeights()[i*numberX+j], inp);
            if (tmp < dist) {
                dist = tmp;
                cx = i;
                cy=j;
            }
            }
           
        }
        return new int[]{cx,cy};
    }

    private void init() {
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < getWeights().length; i++) {
            for (int j = 0; j < getWeights()[0].length; j++) {
                weights[i][j] = 0;

            }

        }

    }

    public void print() {
        for (double[] weight : getWeights()) {
            System.out.println(Arrays.toString(weight));
        }
    }

    public double neighborhood(int[] u, int[] v, int s, int t) {
        double result;

        double exponent = -((neuronDistance(u, v) * neuronDistance(u, v)) / (2 * (neighborhoodRadius(coeffT, s, epochT) * neighborhoodRadius(coeffT, s, epochT))));
        result = Math.exp(exponent);

        return result;
    }

    public double neuronDistance(int []u, int []v) {
        return Util.dist(u, v);
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
    public static double[][] reshape(int x, int y, double[] inp){
       double r[][]= new double[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                r[i][j]=inp[i*x+j];
                System.out.print(inp[i*x+j]+",");
            }
            System.out.println("");
        }
    return r;
    }
 
}
