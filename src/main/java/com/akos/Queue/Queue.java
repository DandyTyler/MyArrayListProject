package com.akos.queue;

public interface Queue<K, V> {
    int size();

    void push(K key, V value);

    Pair getPair(int index);

    V get(K key);

    Pair pull();
}
