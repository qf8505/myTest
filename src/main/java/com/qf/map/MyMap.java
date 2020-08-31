package com.qf.map;

/**
 * @program: myTest
 * @description: TODO
 * @author: qinfei1
 * @create: 2020-08-26 21:50
 * @version: 1.0
 */
public interface MyMap<K,V> {

    public V put(K k,V v);
    public V get(K k);

    interface Entry<K,V>{
        public K getKey();
        public V getValue();
    }
}
