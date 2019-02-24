/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Util;

/**
 *
 * @author da supa pc
 */
public class Activators {
    /**Constant for sigmoid function
    */
    public static final int  SIGMOID=0;
    /**Constant for (1+tanh(z))/2 function
    */
    public static final int  TANH2=1;
    /**Constant for tanh(z/2) function
    */
    public static final int  TANHZ2=2;
    /**Constant for tanh(z) function
    */
    public static final int  TANH=3;
    /** COnstant for tan(50z) funtion
     */
    public static final int TANH50=4;
    /**Constant for leaky RELu
    */
    public static final int  LRELU=5;
    /**
     * Identity function
     */
    public static final int IDENTITY=6;
    /**
     Softsign function
     */
    public static final int SOFTSIGN=7;
     /** Call a predefined activator 
     * 
     * @param input number or the activator
     * @param type selects the activation function 
     * type=0 sigmoid(z), type=1 (1+tanh(z))/2 type=2 tanh(z/2) type=3 tanh(z)
     *@return returns te value given bye the chosen actiovation function*/
    public static double activator(double input,int type){
        double val=0;
    switch(type){
        case 0:
            val = sigmoid(input);
            break;
        case 1:
            val = tanh2(input);
            break;
        case 2:
            val = tanhz2(input);
            break;
        case 3:
            val = tanh(input);
            break;
        case 4:
            val=tanh(50*input);
            break;
        case 5:
            val=relu(input);
            break;
        case 6:
            val=input;
            break;
        case 7:
            val=input/(1+Math.abs(input));
            break;
        default:
            val=Float.NaN;
    }
    return val;
    }
    
    private static double sigmoid(double input){      
    return (1/(1+Math.exp(-1*input)));
    }
    private static double tanh2(double input){
    return (Math.exp(2*input)/(1+Math.exp(2*input)));
    }
    private static double tanhz2(double input){
    return (2/(1+Math.exp(-1*input)))-1;
    }
    private static double tanh(double input){
    return Math.tanh(input);
    }
     private static double relu(double input){
    if(input>=0)
        return input;
    else
        return input*.01;
    }
    /**Derivatives*/
    
    /** Call a predefined activator derivative
     * 
     * @param input number or the activator
     * @param type selects the derivative of an activation function 
     * type=0 sigmoid(z), type=1 (1+tanh(z))/2 type=2 tanh(z/2) type=3 tanh(z)
     *@return returns the value of the derivative given by the chosen actiovation function*/
    public static double activatorDerivative(double input,int type){
        double val=0;
    switch(type){
        case 0:
            val = sigmoidD(input);
            break;
        case 1:
            val = tanh2D(input);
            break;
        case 2:
            val = tanhz2D(input);
            break;
        case 3:
            val = tanhD(input);
            break;
        case 4:
            val=tanh50D(input);
            break;
        case 5:
            val=reluD(input);
            break;
        case 6:
            val=1;
            break;
        case 7:
            val=1/((1+Math.abs(input))*(1+Math.abs(input)));
            break;
        default:
            val=Float.NaN;
    }
    return val;
    }
    
    private static double sigmoidD(double input){      
    return (Math.exp(-1*input)/Math.pow((1+Math.exp(-1*input)),2));
    }
    private static double tanh2D(double input){
    return ((2*Math.exp(2*input))/Math.pow((1+Math.exp(2*input)),2));
    }
    private static double tanhz2D(double input){
     return 2*sigmoidD(input);
    }
    private static double tanhD(double input){
    return 2*tanh2D(input);
    }

    private static double tanh50D(double input) {
        return 50*Math.pow(1/Math.cosh(50*input),2);
    }

    private static double reluD(double input) {
        if(input>=0)
            return 1;
        else
            return .01;
    }
    
    
}
