package com.test.patterns.structure.bridge.material;

/**
 * 包装接口
 * Material
 */
public class Paper implements Material {
    @Override
    public void draw() {
        System.out.print("用纸盒");
    }
}
