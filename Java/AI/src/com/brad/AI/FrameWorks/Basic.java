package com.brad.AI.FrameWorks;

import com.brad.AI.Main;
import com.brad.AI.Util.Axon;
import com.brad.AI.Util.Layer;
import com.brad.AI.Util.Neuron;
import com.brad.AI.Util.Shape;

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
            //layers[i].print();
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


    public double train(float[] input, float[] correctResult){
        double cost = 0;


        float[] result = run(input);
        backProp(correctResult, result);
        return Math.sqrt(cost);
    }

    public void backProp(float[] correctResult, float[] result){
        float[] previousLayerOutputs = correctResult;
        for(int i = layers.length-1; i > -1; i--) {
            Layer layer = layers[i];
            System.out.println("Layer: " + layer.name);
            float[] neuronErrors = compare(layer.getNeuronValues(), previousLayerOutputs);



        }
    }

    private float[] compare(float[] target, float[] output){
        float[] results = new float[target.length];

        for(int i = 0; i < results.length; i++){
            results[i] = (target[i]-output[i])*(target[i]-output[i]);
        }

        return results;
    }

    private float backPropOld(float[] correctResult, float[] result){
        //Get the error for each neuron
        //then get the error for each weight connected to that neuron
        //change the weights
        float totalError = 0;
        float outputLayerError = 0;
        float[] outputErrors = new float[correctResult.length];
        for(int i = 0; i < result.length; i++){
            float error = (float) 0.5*((correctResult[i]-result[i])*(correctResult[i]-result[i]));
            outputErrors[i] = error;
            outputLayerError += error;
            System.out.println("Error " + i + ": " + error + " target: " + correctResult[i] + " result: " + result[i]);
        }
        for(int i = layers.length-1; i > -1; i--){
            Layer layer = layers[i];
            float[] LayerNeuronError;
            float totalLayerError = 0;
            //Layer layerDown = layers[i-1];
            LayerNeuronError = new float[layer.neurons.length];
            for(int x = 0; x < layer.neurons.length; x++){
                Neuron neuron = layer.neurons[x];


                for(int y = 0; y < neuron.axons.length; y++){
                    Axon axon = neuron.axons[y];

                    for(int z = 0; z < neuron.axons.length; z++) {
                        Axon axon2 = neuron.axons[z];
                        float error = (float) 0.5*(((neuron.value*axon2.value)-neuron.value)*((neuron.value*axon2.value)-neuron.value));
                        LayerNeuronError[x] = error;
                        totalLayerError += error;
                    }
                    float error = (float) 0.5*(((neuron.value*axon.value)-neuron.value)*((neuron.value*axon.value)-neuron.value));

                    if(i == layers.length-1){
                        LayerNeuronError = outputErrors;
                        totalLayerError = outputLayerError;
                        error = LayerNeuronError[x];
                    }

                    //This is prob wrong////////////
                    float neuronNetChange = error*(1-error);
                    float weightChange = 1*neuron.value * axon.value;//axon.value*(1-axon.value);
                    float change = totalLayerError*neuronNetChange*weightChange;
                    ////////////////////////////////
                    //System.out.println(neuronNetChange + " " + weightChange + " " + change + " " + error + " " + totalLayerError);
                    //System.out.println("Layer: " + layer.name + " neuron: " + x + " axon: " + y + " change: " + change + " old: " + axon.value + " new: " + (axon.value-change));
                    axon.value -= 0.5 * change;
                }

                //System.out.println("Layer: " + layer.name + " neuron: " + x + " error: " + error + " " + (layer.neurons[i].value-totalLayerError) + " - " + layer.neurons[i].value);
            }
            totalError += totalLayerError;
            //System.out.println("layer: " + layer.name + " error: " + totalLayerError);
        }

        return totalError;


    }


}
