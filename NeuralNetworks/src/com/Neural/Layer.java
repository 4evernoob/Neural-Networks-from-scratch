package com.Neural;

import com.Util.Activators;
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Da supa pc jr
 */
public class Layer {

    double[][] weights;
    double[][] deltaW;
    double[] outputs;
    double[] outputsBA;
    boolean biasActiv;
    int activ;

    public Layer(int n, int nW, int activ, boolean fL) {
        biasActiv = fL;
        this.activ = activ;
        if (!fL) {
            weights = new double[n][nW];
            deltaW = new double[n][nW];
            outputs = new double[n];
            outputsBA = new double[n];
            initOnes();
        } else {
            weights = new double[n][nW + 1];
            deltaW = new double[n][nW + 1];
            outputs = new double[n];
            outputsBA = new double[n];
            init();
        }

    }

    public double calcNeuron(int i, double[] inp) {
        double res = 0;

        if (biasActiv) {
            for (int j = 0; j < inp.length; j++) {
                res += inp[j] * weights[i][j];
            }
            res += weights[i][weights[0].length - 1];
        } else {
            res = inp[i];
        }

        return res;

    }

    public void calcLayer(double[] inp) {
        double tmp = 0;
        for (int i = 0; i < weights.length; i++) {
            outputsBA[i] = calcNeuron(i, inp);
            outputs[i] = Activators.activator(outputsBA[i], activ);

        }

    }

    public void printLayer() {
        for (int i = 0; i < weights.length; i++) {
            System.out.println("Neuron #" + i + "\n" + Arrays.toString(weights[i]));

        }
    }

    public void printOut() {
        System.out.println("Output:\n " + Arrays.toString(outputs));
    }

    public void printOutBA() {
        System.out.println("Output Before activation: \n" + Arrays.toString(outputsBA));
    }

    public void init() {
        Random r = new Random(System.nanoTime());
        for (double[] weight : weights) {
            for (int j = 0; j < weights[0].length; j++) {
                weight[j] = 2*r.nextDouble() - 1;
            }
        }
    }

    public void initOnes() {
        Random r = new Random(System.nanoTime());
        for (double[] weight : weights) {
            for (int j = 0; j < weights[0].length; j++) {
                weight[j] = 1.0;
            }
        }
    }
}
