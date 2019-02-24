/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Util;

/**
 *
 * @author Da supa pc jr
 */
public class Util {
    public static double dist(double a [], double b[]){
        if(a.length!=b.length)
            return Double.NaN;
        double r =0;
        for (int i = 0; i < b.length; i++) {
            r=r+ (b[i]-a[i])*(b[i]-a[i]);
            
        }
        return Math.sqrt(r);
    }
    public static double dist(int a [], int b[]){
        if(a.length!=b.length)
            return Double.NaN;
        double r =0;
        for (int i = 0; i < b.length; i++) {
            r=r+ (b[i]-a[i])*(b[i]-a[i]);
            
        }
        return Math.sqrt(r);
    }
}
