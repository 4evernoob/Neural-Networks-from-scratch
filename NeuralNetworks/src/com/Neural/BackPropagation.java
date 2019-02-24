package com.Neural;

import com.Dataset.Dataset;
import com.Util.Activators;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Da supa pc jr
 */
public class BackPropagation {

    public static double[] propagate(NeuralNet n, double[] inp) {
        n.getLayerAt(0).calcLayer(inp);
        for (int i = 1; i < n.getNumberOfLayers(); i++) {
            n.getLayerAt(i).calcLayer(n.getOutputLayerAt(i - 1));
        }
        return n.getOutputLayerAt(n.getNumberOfLayers() - 1);
    }

    public static void train(NeuralNet net, double lr, double dr, int epochs, Dataset data) {
    double mse=0;
        for (int m = 0; m < epochs; m++) {
            mse=0;
            for (int o = 0; o < data.getNumberOfObservations(); o++) {
                int lc = net.getNumberOfLayers() - 1;
                double deltaN[] = new double[net.getSizeAt(lc)];
                double deltaA[];//= new double[net.getSizeAt(lc)-1];
                double error[] = new double[net.getSizeAt(lc)];
                BackPropagation.propagate(net, data.getD(o));
                for (int i = 0; i < net.getOutputLayerAt(lc).length; i++) {
                    error[i] = data.getT(o)[i] - net.getOutputAt(lc, i);
                }
                mse+=mse(error);
                // actualiza el layer de salida
                for (int i = 0; i < net.getOutputLayerAt(lc).length; i++) {
                    deltaN[i] = error[i] * Activators.activatorDerivative(net.getOutputBa(lc, i), net.getLayerAt(lc).activ);
                    //actualizo pesos en la salida
                    for (int j = 0; j < net.getSizeAt(lc - 1); j++) {
                        double tt = net.getWeightAt(lc, i, j) + lr * deltaN[i] * net.getOutputAt(lc - 1, j) + net.alpha * net.getWeightDAt(lc, i, j);
                        net.setWeightDAt(lc, i, j, lr * deltaN[i] * net.getOutputAt(lc - 1, j));
                        net.setWeightAt(lc, i, j, tt);
                    }
                    //actualizo bias en la salida
                    double tt = net.getWeightAt(lc, i, net.getSizeAt(lc - 1)) + dr * deltaN[i] + net.alpha * net.getWeightDAt(lc, i, net.getSizeAt(lc - 1));
                    net.setWeightDAt(lc, i, net.getSizeAt(lc - 1), dr * deltaN[i]);
                    net.setWeightAt(lc, i, net.getSizeAt(lc - 1), tt);
                }
               // System.out.println("ll"+Arrays.toString(deltaN));
                //actualiza los layers restantes
                for (int l = (lc - 1); l > 0; l--) {
                    deltaA = new double[deltaN.length];
                    System.arraycopy(deltaN, 0, deltaA, 0, deltaN.length);
                    deltaN = new double[net.getSizeAt(l)];
                    for (int i = 0; i < net.getSizeAt(l); i++) {
                        deltaN[i] = 0;
                        for (int j = 0; j < net.getSizeAt(l + 1); j++) {
                            deltaN[i] = deltaN[i] + deltaA[j] * net.getWeightAt(l + 1, j, i);
                        }
                        deltaN[i] = deltaN[i] * Activators.activatorDerivative(net.getOutputBa(l, i), net.getLayerAt(l).activ);
                        for (int j = 0; j < net.getSizeAt(l - 1); j++) {
                            double tt = net.getWeightAt(l, i, j) + lr * deltaN[i] * net.getOutputAt(l - 1, j) + net.alpha * net.getWeightDAt(l, i, j);
                            net.setWeightDAt(l, i, j, lr * deltaN[i] * net.getOutputAt(l - 1, j));
                            net.setWeightAt(l, i, j, tt);
                        }
                        double tt = net.getWeightAt(l, i, net.getSizeAt(l - 1)) + dr * deltaN[i] + net.alpha * net.getWeightDAt(l, i, net.getSizeAt(l - 1));
                        net.setWeightDAt(l, i, net.getSizeAt(l - 1), lr * deltaN[i]);
                        net.setWeightAt(l, i, net.getSizeAt(l - 1), tt);
                    }
                }
            }
         mse=mse/data.getNumberOfObservations();
         net.mse=mse;
        }
        
    }
     public static double mse(double []stuff){
        double sum=0;
        for (int i = 0; i < stuff.length; i++) {
            sum += stuff[i]*stuff[i];
            
        }
        return sum;
    }
}
