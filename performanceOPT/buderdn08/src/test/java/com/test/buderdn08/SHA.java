package com.test.buderdn08;

import org.apache.commons.codec.digest.Sha2Crypt;
import org.junit.Test;

public class SHA {
    @Test
    public void test() {
        String result = Sha2Crypt.sha256Crypt("buder".getBytes());
        System.out.println(result);
    }
}
