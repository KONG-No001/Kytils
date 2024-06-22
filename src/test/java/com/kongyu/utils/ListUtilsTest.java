package com.kongyu.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void transverseSegmented() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ListUtils.transverseSegmented(9, list).forEach(System.out::println);
    }

    @Test
    public void longitudinalSegmented() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ListUtils.longitudinalSegmented(9, list).forEach(System.out::println);
    }
}