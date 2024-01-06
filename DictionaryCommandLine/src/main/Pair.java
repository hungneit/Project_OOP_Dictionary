package main;

public class Pair<K, V> {
    private K key;
    private V value;
    private Pair(){}
    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }
    public static <K, V> Pair of (K key, V value) {
        return new Pair<>(key, value);
    }
    public K getKey() {
        return this.key;
    }
    public V getValue() {
        return value;
    }
}

