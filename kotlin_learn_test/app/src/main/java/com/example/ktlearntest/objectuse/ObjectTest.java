package com.example.ktlearntest.objectuse;

public class ObjectTest {

    //java调用kotlin
    private void singleTest() {
        RepositoryManager.INSTANCE.method();//不加 @jvmStatic

        RepositoryManager.method();//加 @jvmStatic
    }

}
