package org.yinxianren.springboot.tools;

import java.util.Map;

public interface IPayMap<K,V> extends Map<K,V> {

    PayMap<K,V> lput(K key, V value);
}
