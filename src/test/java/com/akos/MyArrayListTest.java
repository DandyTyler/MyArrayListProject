package com.akos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MyArrayListTest {
    private MyArrayList<String> list;

    @Before
    public void setUp() {
        this.list = new MyArrayList<>();
    }

    @Test
    public void test_size_add_one() {
        list.add("A");
        assertThat(list.size(), is(1));
    }

    @Test
    public void test_size_add_two() {
        list.add("A");
        list.add("A");
        assertThat(list.size(), is(2));
    }

    @Test
    public void test_size_add_and_remove() {
        list.add("A");
        list.remove(0);
        assertThat(list.size(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_size_add_one_and_remove_Another() {
        list.add("A");
        list.remove(2);
    }

    @Test
    public void test_size_empty_add_null() {
        list.add(null);
        assertThat(list.size(), is(1));
    }

    @Test
    public void test_remove_max() {
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("b");
        assertThat(list.removeMax(), is("d"));
    }

    @Test
    public void test_remove_min() {
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("b");
        assertThat(list.removeMin(), is("a"));
    }

    @Test
    public void test_sort_two() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(-6);
        Assert.assertTrue(list.get(0) < list.get(1));
    }
}
