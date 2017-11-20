package com.akos.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс является реализацией автоматически расширяемого массива, реализует интерфейс <tt>MyList</tt>.
 * При создании экземпляр класса имеет вместимость элементов равную <tt>ARRAY_START_SIZE</tt>.
 * По мере добавления элементов вместимость увеличивается с помощью метода <tt>resize</tt>
 * Также класс реализует возможность автоматического упорядочивания элементов по мере добавления а также доступ
 * к минимальному и максимальному элементу. Поэтому в массив можно добавлять только элементы,
 * реализующие интерфейс <tt>Comparable</tt>.
 *
 * @see com.akos.list.MyList
 * @see Comparable
 */

public class MyArrayList<E extends Comparable> implements MyList<E> {
    private final int ARRAY_START_SIZE = 5;
    private Object[] array;
    private int size = 0;

    public MyArrayList() {
        this.array = new Object[ARRAY_START_SIZE];
    }


    /**
     * Добавляет элемент в массив. Если в массиве недостаточно места для вставки нового элемента, создается массив
     * большего размера при помощи метода resize(). При добавлении элемент ставится на позицию, необходимую для
     * поддержания отсортированности. Если в массиве еще нет элементов, добавляется по индексу 0.
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
        if (value1.compareTo(value2) < 0) {
            return value1;
        } else
            return value2;
    }

    private void checkIndex(int index) {
        if (index >= size())
            throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
    }

    private void checkHasElements() {
        if (size == 0) throw new NoSuchElementException("size = " + size);
    }

    public E get(int index) {
        checkIndex(index);
        return (E) array[index];
    }

    /**
     * Возвращает максимальный элемент
     *
     * @return максимальный элемент
     */
    public E getMax() {
        checkHasElements();
        return (E) array[size - 1];
    }

    /**
     * Удаляет максмальный элемент
     *
     * @return удаленный элемент
     */
    public E removeMax() {
        checkHasElements();
        return (E) array[--size];
    }

    /**
     * Возвращает минимальный элемент
     *
     * @return минимальный элемент
     */
    public E getMin() {
        checkHasElements();
        return (E) array[0];
    }

    /**
     * Удаляет минимальный элемент
     *
     * @return удаленный элемент
     */
    public E removeMin() {
        checkHasElements();
        return (E) remove(0);
    }

    /**
     * Удаляет элемент по индексу
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     */
    public E remove(int index) {
        checkIndex(index);
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
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E value = get(curr);
            curr++;
            return value;
        }

        @Override
        public void remove() {
            if (size > 0) {
                MyArrayList.this.remove(curr - 1);
                curr--;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MyList))
            return false;
        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((MyList<?>) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < this.size; i++) {
            if (i == size - 1)
                return str + array[i] + "]";
            str += array[i] + ", ";
        }
        return str + "]";
    }
}
