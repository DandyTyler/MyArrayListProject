package com.akos.Queue;

class MyQueue<K, V> implements Queue<K, V> {
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

    private class QueueEntry {
        // Поле для хранения объектов
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

        public void setValue(V value) {
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
