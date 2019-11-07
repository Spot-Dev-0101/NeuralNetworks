package com.brad.AI;

import com.brad.AI.FrameWorks.Basic;
import com.brad.AI.Util.Shape;

public class Main {

    static Basic basic;

    public static void main(String[] args) {
        Shape inputShape = new Shape(10);
        Shape outputShape = new Shape(5);
        Shape[] shapes = new Shape[]{inputShape, outputShape};
        basic = new Basic(shapes);
    }
}
