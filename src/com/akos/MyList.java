package com.akos;

public interface MyList<E> extends Iterable<E> {

    int size();

    void add(E element);

    E get(int index);

    E remove(int index);
}
