package com.brad.AI.Util;

import java.util.Random;

public class Neuron {

    public float value = 0;
    public float bias = 0;
    public Axon[] axons;

    public Neuron(float value, float bias, int amountOfConnections){
        this.value = value;
        this.bias = bias;
        this.axons = new Axon[amountOfConnections];
        for(int i = 0; i < amountOfConnections; i++){
            axons[i] = Axon.createRandom();
        }
    }

    public static Neuron createRandom(Shape nextShape){
        return new Neuron((float)Math.random(), (float)Math.random(), nextShape.size);
    }

    public float[] getAxonValues(){
        float[] result = new float[axons.length];

        for(int i = 0; i < axons.length; i++){
            result[i] = axons[i].value;
        }

        return result;
    }
}
