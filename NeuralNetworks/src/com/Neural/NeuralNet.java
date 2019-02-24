package com.Neural;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Da supa pc jr man
 */
public class NeuralNet {
    Layer layer[];
    double alpha=.8;
   public double mse;
    public NeuralNet(int[]npL,int[]activators){
        layer= new Layer[npL.length];
        layer[0]= new Layer(npL[0], 1, activators[0],false);
        for (int i = 1; i < npL.length; i++) {
         layer[i]= new Layer(npL[i], npL[i-1], activators[i],true);   
            
        }
    
    }
    public void printNet(){
        for (int i = 0; i < layer.length; i++) {
            System.out.println("Layer #"+(i+1));
            System.out.println("Activator :"+layer[i].activ);
            layer[i].printLayer();
            
        }
    }
    public void printOut(){
        for (int i = 0; i < layer.length; i++) {
            System.out.println("Output #"+(i+1));
          
            layer[i].printOut();
            
        }
    }
    public int getNumberOfLayers(){
    return layer.length;
    }
    public Layer getLayerAt(int i){
    return layer[i];
    }
    public double[]getOutputLayerAt(int i){
    return layer[i].outputs;
    }
    public double[] getOutputLayerBa(int i){
    return layer[i].outputsBA;
    }
    public double getOutputAt(int i, int j){
    return layer[i].outputs[j];
    }
    public double getOutputBa(int i, int j){
    return layer[i].outputsBA[j];
    }
    public double getWeightAt(int i, int j ,int k){
    return layer[i].weights[j][k];
    }
    public void setWeightAt(int i, int j , int k, double val){
    layer[i].weights[j][k]=val;
    }
    public int getSizeAt(int i){
    return layer[i].outputs.length;
    }
      public double getWeightDAt(int i, int j ,int k){
    return layer[i].deltaW[j][k];
    }
    public void setWeightDAt(int i, int j , int k, double val){
    layer[i].deltaW[j][k]=val;
    }   
    public void reinit(){
        for (int i = 1; i < layer.length; i++) {
             layer[i].init();
            
        }
    }

    
         

}
