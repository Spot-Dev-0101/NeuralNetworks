package com.brad.AI.Util;

import java.util.Random;

public class Neuron {

    float value = 0;
    float bias = 0;
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
        return new Neuron((float)(Math.random() * ((1 - 0) + 1)) + 0, (float)(Math.random() * ((1 - 0) + 1)) + 0, nextShape.size);
    }
}
