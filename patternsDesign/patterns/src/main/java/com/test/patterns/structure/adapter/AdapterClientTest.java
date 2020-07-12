package com.test.patterns.structure.adapter;

import com.test.patterns.entity.bag.AppleBag;
import com.test.patterns.entity.bag.OrangeBag;
import com.test.patterns.entity.fruit.Orange;

public class AdapterClientTest {
    public static void main(String[] args){
        Orange orange = new Orange("peter",100);
        OrangeBag bag = getBag2();
//        orange.pack(bag);
        bag.pack();
    }

    /**
     * 取桔子包装
     * @return
     */
	private static OrangeBag getBag(){
		OrangeBag bag = new OrangeBag();
		return bag;
	}

    private static OrangeBag getBag2(){
        //准备用途苹果盒代替
        AppleBag appleBag = new AppleBag();

        //把苹果盒适配成桔子包装盒
        OrangeBag orangeBag = new OrangeBagAdapter(appleBag);

        return orangeBag;
    }
}
