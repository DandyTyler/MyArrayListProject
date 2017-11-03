package com.akos;

import java.util.Iterator;

public interface MyList <E>  extends Iterable<E> {
    int size();
    void add(E element);
    E get(int index);
    E remove(int index);
    Iterator<E> iterator();
}
