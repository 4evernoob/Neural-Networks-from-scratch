/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Util;

import java.awt.image.BufferedImage;

/**
 *
 * @author Da supa pc jr
 */
public class Mat2Im {
        public static BufferedImage toImage(double[][] mat){
             BufferedImage tmp = new BufferedImage(mat.length, mat[0].length,BufferedImage.TYPE_INT_RGB);    
            for (int i = 0; i < mat.length; i++) {
           // int c=0;          
            for (int j = 0; j <mat[0].length ; j++) {
               
                int clr=clamp(mat[i][j]*255.0);
                //int ro=c/mat.length;
                //int co=c%mat[0].length;
               // System.out.println(ro+" "+ co+" "+ clr);
                tmp.setRGB(j,i,((clr << 16) + (clr << 8) + clr));
              //  c++;
            }
        }
        return tmp;
        }
        public static double [][]toMat(BufferedImage im){
           double [][]m=new double[im.getHeight()][im.getWidth()];
            for (int i = 0; i < im.getHeight(); i++) {
                for (int j = 0; j < im.getWidth(); j++) {
                    m[i][j]=im.getRGB(i, j)&0xff;
                }
            }
        return m;
        }
     public static int clamp(double  r) {
        if (r > 255) {
            r = 255;
        } else if (r < 0) {
            r = 0;
        }
        return (int)Math.round(r);
    }
}
