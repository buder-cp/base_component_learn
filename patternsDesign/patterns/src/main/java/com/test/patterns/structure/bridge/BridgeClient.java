package com.test.patterns.structure.bridge;


import com.test.patterns.structure.bridge.bag.BagAbstraction;
import com.test.patterns.structure.bridge.bag.SmallBag;
import com.test.patterns.structure.bridge.material.Material;
import com.test.patterns.structure.bridge.material.Paper;

public class BridgeClient {

    public static void main(String[] args) {
        //袋子型号
        BagAbstraction bag = new SmallBag();

        //袋子材质
        Material material = new Paper();
        bag.setMaterial(material);

        //开始采摘
        bag.pick();
    }

}
