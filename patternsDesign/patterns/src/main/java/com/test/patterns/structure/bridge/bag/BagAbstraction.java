package com.test.patterns.structure.bridge.bag;

import com.test.patterns.structure.bridge.material.Material;

public abstract class BagAbstraction {
    public Material material;

    public void setMaterial(Material material){
        this.material = material;
    }

    //采摘
    public abstract void pick();
}
