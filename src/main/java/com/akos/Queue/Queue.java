package com.akos.Queue;

public interface Queue<K,V> {
    int size();
    void push(K key, V value);
    Object pull();
}
