package com.test.patterns.structure.adapter;

import com.test.patterns.entity.bag.AppleBag;
import com.test.patterns.entity.bag.OrangeBag;

public class OrangeBagAdapter extends OrangeBag {

    private AppleBag appleBag;

    public OrangeBagAdapter(AppleBag appleBag) {
        this.appleBag = appleBag;
    }

    @Override
    public void pack() {
        appleBag.pack();
    }
}
