package com.brad.AI.FrameWorks;

import com.brad.AI.Util.Layer;
import com.brad.AI.Util.Neuron;
import com.brad.AI.Util.Shape;

public class Basic {

    Layer[] layers;
    Shape[] shapes;

    public Basic(Shape[] shapes){
        this.shapes = shapes;
        this.layers = new Layer[shapes.length];
        int i = 0;
        for(Shape s : shapes){
            Shape nextShape = new Shape(0);
            if(i < shapes.length-1) {
                nextShape = shapes[i + 1];
            }
            layers[i] = new Layer(s, nextShape, ""+i+"");
            layers[i].print();
            i++;
        }

    }




    public float[] run(float[] input){
        float[] result = new float[shapes[shapes.length-1].size];

        for(Layer layer : layers){
            
        }
        return result;
    }




}
