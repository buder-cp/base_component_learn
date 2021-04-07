package com.example.iqtest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MyTest {
    @Test
    public void addition_isCorrect() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }


    @Test
    public void stubbing_test() {
        LinkedList mockedList = mock(LinkedList.class);
         when(mockedList.get(0)).thenReturn("first");
         when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));
//        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));

        verify(mockedList).get(0);
    }

    @Test
    public void test3() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("element");
        System.out.println(mockedList.get(999));
        verify(mockedList).get(anyInt());
    }

    @Test
    public void test4() {
        LinkedList mockedList = mock(LinkedList.class);
        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");


        verify(mockedList, times(2)).add("twice");
//        verify(mockedList, times(3)).add("twice");

        verify(mockedList, never()).add("hehe0");

        //verification using atLeast()/atMost()
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    //????
    @Test
    public void test5() {
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    @Test
    public void test6() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");
        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);
        //following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");
        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");
        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder1 = inOrder(firstMock, secondMock);
        //following will make sure that firstMock was called before secondMock
        inOrder1.verify(firstMock).add("was called first");
        inOrder1.verify(secondMock).add("was called second");
    }

    @Test
    public void test13() {
        List list = new LinkedList();
        List spy = spy(list);
        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0));
        System.out.println(spy.size());

        verify(spy).add("one");
        verify(spy).add("two");
    }


    @Mock
    LinkedList mockedList = new LinkedList();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test131() {
//         = mock(LinkedList.class);
        //用内置的参数匹配器来stub
        when(mockedList.get(anyInt())).thenReturn("element");

        //打印 "element"
        System.out.println(mockedList.get(999));

        //你也可以用参数匹配器来验证，此处测试通过
        verify(mockedList).get(anyInt());
        //此处测试将不通过，因为没调用get(33)
//        verify(mockedList).get(32323);
    }


}
