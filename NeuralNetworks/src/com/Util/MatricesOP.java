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
public class MatricesOP {

    static double tol = 0.0000000000000000001;

    public static double[][] suma(double a[][], double b[][]) {
        if ((a.length == b.length) && (a[0].length == b[0].length)) {
            double result[][] = new double[a.length][a[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    result[i][j] = a[i][j] + b[i][j];

                }

            }
            return result;
        } else {
            System.out.println("No valido");
            return null;
        }
    }

    public static double[] sumaVectores(double a[], double b[]) {
        double res[] = new double[a.length];
        for (int i = 0; i < res.length; i++) {

            res[i] = a[i] + b[i];
            if (Math.abs(res[i]) < tol) {
                res[i] = 0.00;

            }

        }
        return res;

    }
//    public static int[] sumaVectores(int a[], int b[]) {
//        int res[] = new int[a.length];
//        for (int i = 0; i < res.length; i++) {
//            res[i] = Errores.clamp(a[i] + b[i]);          
//        }
//        return res;
//
//    }

    public static double[][] multEscalar(double a[][], double esc) {
        double result[][] = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = esc * a[i][j];

            }

        }
        return result;
    }

    public static double[] multEscalarVector(double a[], double esc) {
        double result[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = esc * a[i];

        }
        return result;
    }
//    public static int[] multEscalarVector(int a[], double esc) {
//      double result[] = new double[a.length];
//      int res[]=new int[a.length];
//        for (int i = 0; i < a.length; i++) {
//            result[i] =  esc * a[i];
//res[i]= Errores.clamp(result[i]);
//        }
//        return res;
//    }

    public static double[][] multMatrices(double a[][], double b[][]) {
        if ((a[0].length == b.length) && (a.length == b[0].length)) {
            double result[][] = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        result[i][j] += a[i][k] * b[k][j];

                    }
                }

            }
            return result;

        } else {
            System.out.println("No valido");
            return null;
        }

    }

    public static void imprime(double a[][]) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");

            }
            System.out.println("");

        }
    }
    public static void imprime(int a[][]) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");

            }
            System.out.println("");

        }
    }

    public static void imprime(int a[]) {

        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");

        }
        System.out.println("");

    }
     public static void imprime(double a[]) {

        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");

        }
        System.out.println("");

    }

    public static double[] resuelveSistema(double a[][], double b[]) {
        if (a.length == b.length) {
            double z[][] = concatenaMatrizVector(a, b);
            System.out.println("Sistema original");
            imprime(z);
            System.out.println("");
            //agregar el swap e implementar flag 
            //en caso de que flag llegue y los items i,j sean 0 se crea un break
            double vectt[] = new double[z.length];

            for (int j = 0; j < z.length; j++) {
                double temp[] = null;
                if (Math.abs(z[j][j]) > tol) {
                    temp = multEscalarVector(z[j], 1 / z[j][j]);
                    z[j] = multEscalarVector(z[j], 1 / z[j][j]);
                } else {
                    for (int w = j; w < z.length; w++) {
                        if (Math.abs(z[w][j]) > tol) {
                            z = intercambia(z, j, w);
                        }
                        temp = multEscalarVector(z[j], 1 / z[j][j]);
                        //z[j] = temp;
                    }
                }
                //imprime(temp);
                //System.out.println("");
                for (int i = j + 1; i < z.length; i++) {
                    if (Math.abs(z[i][j]) > tol) {
                        z[i] = multEscalarVector(z[i], 1 / z[i][j]);
                        z[i] = sumaVectores(z[i], multEscalarVector(temp, -1));
                    } else {

                    }
                }
                //imprime(z);
                //System.out.println("");
            }
            for (int i = 0; i < z.length; i++) {
                z[i] = multEscalarVector(z[i], 1 / z[i][i]);
            }
            for (int j = (z.length - 1); j >= 1; j--) {
                double temp[] = z[j];
                for (int i = 0; i < (j); i++) {
                    z[i] = sumaVectores(z[i], multEscalarVector(temp, -(z[i][j])));
                }
            }
            for (int i = 0; i < vectt.length; i++) {
                vectt[i] = z[i][z[0].length - 1];

            }

            return vectt;
        } else {
            System.out.println("No valido");
            return null;
        }
    }

    public static double[][] resuelveInversa(double a[][]) {
        double b[][] = new double[a.length][a[0].length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (i == j) {
                    b[i][j] = 1;
                } else {
                    b[i][j] = 0;
                }
            }

        }
        imprime(b);

        if (a.length > 2) {
            double z[][] = concatenaMatrizMatriz(a, b);
            System.out.println("Inversa calcular");
            imprime(z);
            System.out.println("");
            //agregar el swap e implementar flag 
            //en caso de que flag llegue y los items i,j sean 0 se crea un break
            double vectt[][] = new double[b.length][b[0].length];

            for (int j = 0; j < z.length; j++) {
                double temp[] = null;
                if (Math.abs(z[j][j]) > tol) {
                    temp = multEscalarVector(z[j], 1 / z[j][j]);
                    z[j] = multEscalarVector(z[j], 1 / z[j][j]);
                } else {
                    for (int w = j; w < z.length; w++) {
                        if (Math.abs(z[w][j]) > tol) {
                            z = intercambia(z, j, w);
                        }
                        temp = multEscalarVector(z[j], 1 / z[j][j]);
                        //z[j] = temp;
                    }
                }
                //imprime(temp);
                //System.out.println("");
                for (int i = j + 1; i < z.length; i++) {
                    if (Math.abs(z[i][j]) > tol) {
                        z[i] = multEscalarVector(z[i], 1 / z[i][j]);
                        z[i] = sumaVectores(z[i], multEscalarVector(temp, -1));
                    } else {

                    }
                }
                //imprime(z);
                //System.out.println("");
            }
            for (int i = 0; i < z.length; i++) {
                z[i] = multEscalarVector(z[i], 1 / z[i][i]);
            }
            for (int j = (z.length - 1); j >= 1; j--) {
                double temp[] = z[j];
                for (int i = 0; i < (j); i++) {
                    z[i] = sumaVectores(z[i], multEscalarVector(temp, -(z[i][j])));
                }
            }
            for (int i = 0; i < vectt.length; i++) {
                for (int j = 0; j < vectt[0].length; j++) {
                    if (Math.abs(z[i][(a.length) + j]) > tol) {
                        vectt[i][j] = z[i][(a.length) + j];
                    } else {
                        vectt[i][j] = 0;

                    }

                }
            }

            return vectt;
        } else {
            System.out.println("No valido");
            return null;
        }
    }

    public static double[][] concatenaMatrizVector(double[][] a, double b[]) {
        if (a.length == b.length) {
            double r[][] = new double[a.length][a[0].length + 1];
            for (int i = 0; i < r.length; i++) {
                for (int j = 0; j < r[0].length; j++) {
                    if (a[0].length > j) {
                        r[i][j] = a[i][j];
                    } else {
                        r[i][j] = b[i];
                    }
                }
            }
            return r;
        } else {
            System.out.println("Error no valido");
            return null;
        }
    }

    public static double[][] concatenaMatrizMatriz(double[][] a, double b[][]) {
        if ((a.length == b.length)) {
            double r[][] = new double[a.length][a[0].length + b[0].length];
            for (int i = 0; i < r.length; i++) {
                for (int j = 0; j < r[0].length; j++) {
                    if (a[0].length > j) {
                        r[i][j] = a[i][j];
                    } else {
                        r[i][j] = b[i][j - a[0].length];
                    }
                }
            }
            return r;
        } else {
            System.out.println("Error no valido");
            return null;
        }
    }

    //implementar swap
    public static double[][] intercambia(double a[][], int ini, int destino) {
        double at[][] = new double[a.length][a[0].length];
        for (int i = 0; i < at.length; i++) {
            for (int j = 0; j < at[0].length; j++) {
                at[i][j] = a[i][j];

            }

        }
        double temp[] = at[destino];
        at[destino] = at[ini];
        at[ini] = temp;
        return at;
    }

    public static double[] resuelveSistema(double a[][]) {
        if (a.length != 0) {
            //double z[][] = new double[a.length][a[0].length];
            double[][] z = new double[a.length][];
            for (int i = 0; i < a.length; i++) {
                z[i] = a[i].clone();
            }
            System.out.println("Sistema original");
            imprime(z);
            System.out.println("");
            //agregar el swap e implementar flag 
            //en caso de que flag llegue y los items i,j sean 0 se crea un break
            double vectt[] = new double[z.length];

            for (int j = 0; j < z.length; j++) {
                double temp[] = null;
                if (Math.abs(z[j][j]) > tol) {
                    temp = multEscalarVector(z[j], 1 / z[j][j]);
                    z[j] = multEscalarVector(z[j], 1 / z[j][j]);
                } else {
                    for (int w = j; w < z.length; w++) {
                        if (Math.abs(z[w][j]) > tol) {
                            z = intercambia(z, j, w);
                        }
                        temp = multEscalarVector(z[j], 1 / z[j][j]);
                        //z[j] = temp;
                    }
                }
                //imprime(temp);
                //System.out.println("");
                for (int i = j + 1; i < z.length; i++) {
                    if (Math.abs(z[i][j]) > tol) {
                        z[i] = multEscalarVector(z[i], 1 / z[i][j]);
                        z[i] = sumaVectores(z[i], multEscalarVector(temp, -1));
                    } else {

                    }
                }
                //imprime(z);
                //System.out.println("");
            }
            for (int i = 0; i < z.length; i++) {
                z[i] = multEscalarVector(z[i], 1 / z[i][i]);
            }
            for (int j = (z.length - 1); j >= 1; j--) {
                double temp[] = z[j];
                for (int i = 0; i < (j); i++) {
                    z[i] = sumaVectores(z[i], multEscalarVector(temp, -(z[i][j])));
                }
            }
            for (int i = 0; i < vectt.length; i++) {
                vectt[i] = z[i][z[0].length - 1];

            }

            return vectt;
        } else {
            System.out.println("No valido");
            return null;
        }
    }
    
    public static double[][] acondicionaVectoresEntrada(double a[][]){
    double resultado[][]= new double [a.length+1][a[0].length];
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if(i==0)
                    resultado[i][j]=1;
                else
                    resultado[i]=a[i-1];
                
            }
            
        }
     return resultado;       
    }
     public static double[][] acondicionaVectoresEntrada(double a[]){
    double resultado[][]= new double [2][a.length];
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if(i==0)
                    resultado[i][j]=1;
                else
                    resultado[i][j]=a[j];
                
            }
            
        }
     return resultado;       
    }
 public static double error   (double a[][],double w[], double d[]){
 double error[]=new double[d.length];
 double err=0;
     //System.out.println(a.length+"x"+a[0].length);
     for (int i = 0; i <a[0].length ; i++) {
         double sum=0;
         for (int j = 0; j <a.length ; j++) {
             
             sum+=(a[j][i]*w[j]);
             
             
         } 
         System.out.println(sum);
         error[i]=(d[i]-sum)*(d[i]-sum);
     }
     for (int i = 0; i < error.length; i++) {
         err=err+ error[i];                 
     }
     return err/(2.0*d.length);
 } 
      public static double[][] transpose(double[][]in){
    double res[][]= new double[in[0].length][in.length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                res[j][i]=in[i][in[0].length-1-j];
                
            }
            
        }
            return res;
    }
      public static double[]activate(double[]a){
        double[]res=new double[a.length];
        for (int i = 0; i < res.length; i++) {
            if(a[i]>.0001f)
                res[i]=1;
            else 
                res[i]=-1;            
        }
        return res;
      }
}
