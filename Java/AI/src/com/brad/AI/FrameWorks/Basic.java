package com.brad.AI.FrameWorks;

import com.brad.AI.Main;
import com.brad.AI.Util.Axon;
import com.brad.AI.Util.Layer;
import com.brad.AI.Util.Neuron;
import com.brad.AI.Util.Shape;

import java.util.Arrays;
import java.util.Random;

public class Basic {

    public Layer[] layers;
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
        Layer previousLayer = null;
        for(Layer layer : layers){
            for(int i = 0; i < layer.neurons.length; i++){
                Neuron neuron = layer.neurons[i];
                double nValue = input[i];
                if(previousLayer != null){
                    nValue = 0;
                    //go through all the neurons in the previous layer to get the new value
                    for(int x = 0; x < previousLayer.neurons.length; x++){
                        Neuron prevNeuron = previousLayer.neurons[x];
                        nValue += prevNeuron.value * prevNeuron.axons[i].value;
                    }
                    neuron.value = (float) Main.sigmoid(nValue);
                } else{
                    neuron.value = (float) nValue;
                }
                nValue = neuron.value;
                //System.out.println("Layer: " + layer.name + " Neuron: " + i + " new value: " + nValue);

            }
            //System.out.println("\n\n");
            previousLayer = layer;
        }

        for(int i = 0; i < layers[layers.length-1].neurons.length; i++){
            result[i] = layers[layers.length-1].neurons[i].value;
        }

        return result;
    }


    public double train(float[] input, float[] correctResult, float learningRate){
        double cost = 0;


        float[] result = run(input);

        cost = backProp(correctResult, result, learningRate);
        System.out.println(Arrays.toString(result) + " " + Arrays.toString(correctResult));
        return cost;
    }

    public double backProp(float[] correctResult, float[] result, float learningRate){
        float[] previousLayerOutputs = correctResult;
        double cost = 0;
        for(int i = layers.length-1; i > 0; i--) {
            Layer layer = layers[i];
            Layer downLayer = layers[i-1];
            float layerError = (float) Math.sqrt(Arrays.stream(compare(previousLayerOutputs, layer.getNeuronValues())).sum());
            double[] layerErrors = compare(previousLayerOutputs, layer.getNeuronValues());
            for(int x = 0; x < layer.neurons.length; x++){
                Neuron neuron = downLayer.neurons[x];
                float[] axonValues = downLayer.neurons[x].getAxonValues();
                for(int y = 0; y < axonValues.length; y++){
                    float c = 2*(neuron.value - previousLayerOutputs[x]);
                    float a = (float)Main.sigmoid(layerError);
                    float z = previousLayerOutputs[x];
                    float change = z*a*c;//(float)grad*z;//
                    downLayer.neurons[y].axons[x].value -= learningRate * change;
                }
            }
            previousLayerOutputs = downLayer.getNeuronValues();
            cost += layerError;
        }
        return Math.sqrt(cost);
    }

    private double[] compare(float[] target, float[] output){
        double[] results = new double[target.length];

        for(int i = 0; i < results.length; i++){
            results[i] = (target[i]-output[i])*(target[i]-output[i]);
        }

        return results;
    }


}
