/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back3;

import com.Dataset.Dataset;
import com.Kohonen.KohonenNet2D;
import com.Util.ImageView;
import com.Util.Mat2Im;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Da supa pc jr
 */
public class back2 {
 public static double[][]gen(int n, double in){
        Random r= new Random(System.nanoTime());
        double[][] j= new double[n][2];
        for (int i = 0; i <n; i++) {
            
            //j[i][0]=r.nextInt(10)/100.0f+r.nextGaussian()/100;
            //j[i][1]=r.nextInt(10)/100.0f+r.nextGaussian()/100;
            j[i][0]=Math.sin(i*in)+r.nextDouble();
            j[i][1]=Math.cos(i*in)+r.nextDouble();
            
        }
        return j;
    }
    public static void main(String[] args) {
        double[][] d = new double[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1}};
        double[][] t = new double[][]{{0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0},
        {0, 0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0}};
        KohonenNet2D s = new KohonenNet2D(2, 3, 25);
        Dataset data = new Dataset(d, t);
        s.train(data, 7000, .7);
        for (int i = 0; i < data.getNumberOfObservations(); i++) {
            int a[] = s.query(data.getD(i));
            System.out.println("neuron" + Arrays.toString(a));
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("Neuron #" + i + " " + j);
                double tmp[][] = KohonenNet2D.reshape(5, 5, s.getWeights()[i * 3 + j]);
                new Thread(() -> {
                    ImageView s1 = new ImageView(new javax.swing.JFrame(), false, Mat2Im.toImage(tmp), "out");
                    s1.setVisible(true);
                }).start();
            }
        }

    }
}
