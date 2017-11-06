package com.akos;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO: 06.11.2017 Add checkIndex method
public class MyArrayList<E extends Comparable> implements MyList<E> {
    private final int ARRAY_START_SIZE = 5;
    private Object[] array;
    private int size = 0;

    public MyArrayList() {
        this.array = new Object[ARRAY_START_SIZE];
    }


    /**
     * Добавляет элемент в массив. Если в массиве недостаточно меса для вставки нового элемента, создается массив
     * большего размера при помощи метода resize(). При добавлении элемент ставится на позицию, необходимую для
     * поддержания отсортированности. Если в массиве еще нет элементов, добавляется по индексу 0;
     *
     * @param element Элемент добавляемый в массив.
     */
    public void add(E element) {
        if ((array.length) <= (size + 1)) {
            resize();
        }
        if (size == 0) {
            array[size++] = element;
        } else {
            int buff = size;
            for (int i = 0; i <= buff - 1; i++) {
                //Если новый элемент меньше старого, он вставляется перед старым, следовательно размер массива увеличивается
                if (element.equals(minOf(element, (E) array[i]))) {
                    add(i, element);
                    break;
                }
            }
            // Если увеличения массива не произошло, элемент добавляется в конец, т.к. он больше всех остальных элементов
            if (buff == size)
                array[size++] = element;
        }
    }

    /**
     * Добавляет элемент по индексу, сдвигая старые элементы вправо
     * Private, т.к. используется только для вставки нового элемента в порядке сортировки
     *
     * @param index   позиция по которой вставляется элемент
     * @param element Элемент добавляемый в массив
     */
    private void add(int index, E element) {
        if (array.length <= index + 1) {
            resize();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    private E minOf(E value1, E value2) {
        Comparable buff = value1;
        if (buff.compareTo(value2) < 0) {
            return value1;
        } else
            return value2;
    }

    private E maxOf(E value1, E value2) {
        Comparable buff = value1;
        if (buff.compareTo(value2) >= 0) {
            return value1;
        } else
            return value2;
    }

    public E get() {
        return (E) array[size - 1];
    }            //Без аргументов, наверное не нужен

    public E get(int index) {
        return (E) array[index];
    }

    public E remove() {                                  //Без аргументов, наверное не нужен
        E oldValue = (E) array[--size];
        return oldValue;
    }

    /**
     * Удаляет элемент по индексу
     *
     * @param index индекс удаляемого элемента
     * @return Возврвщает удаленный элемент
     * @
     */
    public E remove(int index) {
        E oldValue = (E) array[index];
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        array[size] = null;
        size--;
        return oldValue;
    }

    /**
     * Увеличивает размер массива
     */
    private void resize() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int curr = 0;

        @Override
        public boolean hasNext() {
            return this.curr < size;
        }

        @Override
        public E next() {
            int nextIndex;
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E value = get(curr);
            curr++;
            return value;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size; i++)
            str = str + array[i] + " ";
        return str;
    }
}
