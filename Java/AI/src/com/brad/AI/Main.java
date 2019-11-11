package com.brad.AI;

import com.brad.AI.FrameWorks.Basic;
import com.brad.AI.Util.Layer;
import com.brad.AI.Util.Shape;

public class Main {

    static Basic basic;

    public static void main(String[] args) {

        float[] correctResult = new float[]{(float)0.99, (float)0.01, (float)0.99, (float)0.01};

        Shape inputShape = new Shape(12);
        Shape hiddenShape = new Shape(6);
        Shape outputShape = new Shape(4);
        Shape[] shapes = new Shape[]{inputShape, hiddenShape, outputShape};
        basic = new Basic(shapes);
        double cost = 10000000;
        int index = 0;
        while(cost >= (float)0.8 && index < 7000){
            double result = basic.train(new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, correctResult, (float)0.1);
            //float[] result = basic.run(new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});

            System.out.println("Training run: " + index + ":" + "    Cost: " + cost);

            cost = result;
            index++;
            System.out.println("\n");
        }

        for(Layer layer : basic.layers){
            layer.print();
        }

    }

    public static double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }
}
