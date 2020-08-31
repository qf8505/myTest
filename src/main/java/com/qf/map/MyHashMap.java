package com.qf.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: myTest
 * @description: TODO
 * @author: qinfei1
 * @create: 2020-08-26 21:53
 * @version: 1.0
 */
public class MyHashMap<K,V> implements MyMap<K,V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1<<4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int defaultInitSize;
    private float defaultLoadFactor;

    private int entryUseSize;

    private Entry<K,V>[] table = null;

    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor){
        if(defaultInitialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: "+defaultInitialCapacity);
        if(defaultInitialCapacity <= 0 || Float.isNaN(defaultLoadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + defaultLoadFactor);

        this.defaultInitSize = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;

        table = new Entry[this.defaultInitSize];
    }

    public V put(K k, V v) {
        V oldValue = null;
        if(entryUseSize >= defaultInitSize * defaultLoadFactor){
            resize(2*defaultInitSize);
        }
        int index = hash(k) & (defaultInitSize - 1);
        if(table[index] == null) {
            table[index] = new Entry<K, V>(k,v,null);
            ++entryUseSize;
        } else {
            Entry<K,V> entry = table[index];
            Entry<K,V> e = entry;
            while(e!=null){
                if(k == e.getKey() || k.equals(e.getKey())){
                    oldValue = e.value;
                    e.value = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] = new Entry<K, V>(k,v,entry);
            ++entryUseSize;
        }
        return oldValue;
    }

    public V get(K k) {
        int index = hash(k) & (defaultInitSize - 1);

        if(table[index] == null){
            return null;
        }else {
            Entry<K,V> entry = table[index];
            do{
                if(k == entry.getKey() || k.equals(entry.getKey())){
                    return entry.value;
                }
                entry = entry.next;
            }while (entry != null);
        }
        return null;
    }

    private void resize(int i) {
        Entry[] newTable = new Entry[i];
        defaultInitSize = i;
        entryUseSize = 0;
        rehash(newTable);
    }

    private void rehash(Entry<K, V>[] newTable){
        List<Entry<K,V>> entryList = new ArrayList<Entry<K,V>>();
        for(Entry<K,V> entry : table){
            if(entry !=null){
                do{
                    entryList.add(entry);
                    entry = entry.next;
                }while (entry!=null);
            }
        }

        if(newTable.length > 0){
            table = newTable;
        }

        for(Entry<K,V> entry : entryList){
            put(entry.getKey(),entry.getValue());
        }
    }

    private int hash(K k) {
        int hashCode = k.hashCode();
        hashCode ^=(hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>>7) ^ (hashCode >>>4);
    }

    class Entry<K,V> implements MyMap.Entry<K,V>{

        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(){

        }

        public Entry(K key, V value, Entry<K,V> next) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
