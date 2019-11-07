package com.brad.AI.Util;

public class Axon {

    float value = 0;

    public Axon(float value){
        this.value = value;
    }

    public static Axon createRandom(){
        return new Axon((float)(Math.random() * ((1 - 0) + 1)) + 0);
    }

}
