package com.akos.queue;

/**
 * Реализация интерфейса DoubleQueue
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 * @see DoubleQueue
 */
public class MyDoubleQueue<K, V> implements DoubleQueue<K, V> {
    private QueueEntry head = null;
    private QueueEntry tail = null;
    private int size = 0;

    public void push(K key, V value) {
        QueueEntry currEntry = new QueueEntry(key, value);
        if (head == null) {
            head = currEntry;
        } else {
            tail.setNext(currEntry);
        }
        tail = currEntry;
        size++;
    }

    /**
     * Возвращает обьект типа Pair, содержащий ключ и значение лежащие по данному индексу
     * Если по такому индексу нет значений возвращает null
     *
     * @param index индекс элемента
     * @return обьект класса <tt>Pair</tt> хранящий ключ и значение
     */
    public Pair getPair(int index) {
        if (size == 0 || index >= size || index < 0) {
            return null;
        }
        QueueEntry current = head;
        int position = 0;
        while (position < index) {
            current = current.getNext();
            position++;
        }
        return new Pair(current.getKey(), current.getValue());
    }

    public V get(K key) {
        int position = 0;
        QueueEntry current = head;
        while (position < size) {
            if (key.equals(current.getKey())) {
                return current.getValue();
            }
            current = current.getNext();
            position++;
        }
        return null;
    }

    /**
     * "Достает" элемент из начала очереди. Если очередь пуста, возвращает null
     */
    public Pair pull() {
        if (size == 0) {
            return null;
        }
        Pair pair = new Pair(head.getKey(), head.getValue());
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return pair;
    }

    public int size() {
        return size;
    }

    /**
     * Класс для хранения ключа, значения а также ссылки на следующий элемент в очереди
     */
    private class QueueEntry {
        private K key;
        private V value;
        private QueueEntry next;

        QueueEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }

        QueueEntry getNext() {
            return next;
        }

        void setNext(QueueEntry next) {
            this.next = next;
        }
    }
}
