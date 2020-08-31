package com.qf.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: myTest
 * @description: TODO
 * @author: qinfei1
 * @create: 2020-08-26 22:21
 * @version: 1.0
 */
public class TestMyHashMap {

    public static void main(String[] args) {
        MyMap<String,String> myMap = new MyHashMap<String, String>();
        for(int i=0; i<500;i++){
            myMap.put("key"+i,"value"+i);
        }
        for(int i=0;i<500;i++){
            System.out.println("key"+i+",value is :" +myMap.get("key"+i));
        }
        Map<String,Object> map = new HashMap();
        map.put("test","test");
        Object oldVal = map.put("test","test1");
        System.out.println(oldVal);
        map.put(null,"testnull");
        System.out.println(map.get("test"));
        map = new ConcurrentHashMap();
        map.put("testc","testc");
        System.out.println(map.get("testc"));
    }
}
