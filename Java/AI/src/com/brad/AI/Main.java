package com.brad.AI;

import com.brad.AI.FrameWorks.Basic;
import com.brad.AI.Util.Layer;
import com.brad.AI.Util.Shape;

public class Main {

    static Basic basic;

    public static void main(String[] args) {

        float[] correctResult = new float[]{1, 0};

        Shape inputShape = new Shape(8);
        Shape hiddenShape = new Shape(4);
        Shape outputShape = new Shape(2);
        Shape[] shapes = new Shape[]{inputShape, hiddenShape, outputShape};
        basic = new Basic(shapes);

        for(int i = 0; i < 1; i++){
            double result = basic.train(new float[]{1, 1, 1, 1, 1, 1, 1, 1}, correctResult);
            //float[] result = basic.run(new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
            System.out.println("Result: " + result);
        }

        for(Layer layer : basic.layers){
            //layer.print();
        }

    }

    public static double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }
}
