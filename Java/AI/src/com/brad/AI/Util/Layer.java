package com.brad.AI.Util;

public class Layer {

    public Neuron[] neurons;
    public String name = "";

    public Layer(Shape shape, Shape nextShape, String name){
        this.name = name;
        neurons = new Neuron[shape.size];
        for(int i = 0; i < shape.size; i++){
            neurons[i] = Neuron.createRandom(nextShape);
        }
    }

    public void print(){
        int i = 0;
        System.out.println("Layer: "  + name);
        for(Neuron n : neurons){

            System.out.println("Neuron: " + i + "\n    Value: " + n.value + "\n   Bias: " + n.bias + "\n    Axons:");
            int x = 0;
            for(Axon a : n.axons){
                System.out.println("        " + x + ": " + a.value);
                x++;
            }

            i++;
        }
        System.out.println("\n");
    }

    public float[] getNeuronValues(){
        float[] result = new float[neurons.length];

        for(int i = 0; i < neurons.length; i++){
            result[i] = neurons[i].value;
        }

        return result;
    }

}
