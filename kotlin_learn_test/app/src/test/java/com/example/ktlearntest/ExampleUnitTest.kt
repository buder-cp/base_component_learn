package com.example.ktlearntest

import io.mockk.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * mockk()
     * mock类T并返回该类的mock对象
     *
     * 这个方法返回T的实例，该实例所有函数都为待mock状态，这些待mock状态的函数都不能直接调用，
     * 需要结合every{}mock对应方法后才能调用
     */
    @Test
    fun mockk() {
        val mockk = mockk<ServiceImplA>()
        every { mockk.doSomething1(any()) } returns "Unitaaa"
        mockk.doSomething1("")
//        mockk.doSomething2("")
    }

    /**
     * mockkObject
     * 将指定对象转为可mock状态
     *
     * 与mockk<>()的区别是返回的mock对象，允许mock行为跟真实行为并存，
     * 如果不主动mock，则执行真实行为
     */
    @Test
    fun mockkObject() {
        val serviceImplA = ServiceImplA()
        mockkObject(serviceImplA)
        every { serviceImplA.doSomething1(any()) } returns "Unit222"
        //调用被mock方法
        serviceImplA.doSomething1("sfas")
        //调用真实方法
        serviceImplA.doSomething2("sfas")
    }

    /**
     * spyk() & spyk(T obj)
     * 返回T的spyk对象或者obj的spyk对象
     *
     * 与mockk<>()的区别是，spyk<>()返回的对象是允许真实行为跟mock行为共存的，其表现跟mockkObject()相似
     */
    @Test
    fun spyk() {
        //返回ServiceImplA的一个spyk对象
        val spyk = spyk<ServiceImplA>()
        every { spyk.doSomething1(any()) } returns "Unit34234"
        //调用mock方法
        spyk.doSomething1("123")
        //调用真实方法
        spyk.doSomething2("999")

        /*val serviceImplA = ServiceImplA()
        //返回serviceImplA对象被spyk后的对象，原对象不会改变
        val spyk1 = spyk(serviceImplA)
        //serviceImplA不是可mock状态，这里会报错
        //every { serviceImplA.doSomething1(any()) } returns Unit

        //mock
        every { spyk1.doSomething1(any()) } returns "Unit2434"
        //同上
        spyk1.doSomething1("123999")
        spyk1.doSomething2("888888")*/
    }

    @Test
    fun retunsTest() {
        val spyk = spyk<ServiceImplA>()
        every { spyk.doSomething2(any()) } returns "buder"
        val input = "222"
        val mockkResult = spyk.doSomething2(input)
        println("mockk行为结果：$mockkResult")

        val real = ServiceImplA()
        val realResult = real.doSomething2(input)
        println("mockk行为结果:$realResult")
    }

    @Test
    fun answersTest() {
        val input = "222"
        val spyk = spyk<ServiceImplA>()
        //定制mock行为
        every { spyk.doSomething2(any()) } answers {
            //something will happen
            println("定制mock行为")

            //拿到真实函数信息
            val originalMethod = invocation.originalCall

            //调用真实行为并拿到响应结果？？？？
//            val originalResult = callOriginal()
            //同上？？？？？
//            val originalResult1 = originalMethod.invoke()

            //返回一个固定结果
            "mock result"
        }
        //调用会执行answers里代码
        spyk.doSomething2(input)

//        every { spyk.doSomething2(any()) } propertyType String::class answers {
//            //拿到第一个输入参数
//            val firstArg = firstArg<String>()
//            println("输入：$firstArg")
//            println("这里是mock后的行为")
//
//            //定制方法返回
//            "mock响应$firstArg"
//        }
//        spyk.doSomething2(input)

    }

    @Test
    fun verifyOrder() {
        val obj = mockk<MockedClass>()
        val slot = slot<Int>()

        every {
            obj.sum(any(), capture(slot))
        } answers {
            1 + firstArg<Int>() + slot.captured
        }

        obj.sum(1, 2) // returns 4
        obj.sum(1, 3) // returns 5
        obj.sum(2, 2) // returns 5

        verifyAll {
            obj.sum(1, 3)
            obj.sum(1, 2)
            obj.sum(2, 2)
        }

        verifySequence {
            obj.sum(1, 2)
            obj.sum(1, 3)
            obj.sum(2, 2)
        }

        verifyOrder {
            obj.sum(1, 2)
            obj.sum(2, 2)
        }

        val obj2 = mockk<MockedClass>()
        val obj3 = mockk<MockedClass>()
        verify {
            listOf(obj2, obj3) wasNot Called
        }

        confirmVerified(obj)
    }

}