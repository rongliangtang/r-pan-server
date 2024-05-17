package com.imooc.pan.bloom.filter.core;

/**
 * 布隆过滤器的顶级接口
 */
public interface BloomFilter<T> {

    /**
     * 放入元素
     *
     * @param object
     * @return
     */
    boolean put(T object);

    /**
     * 判断元素是不是可能存在
     *
     * @param object
     * @return
     */
    boolean mightContain(T object);

    /**
     * 清空过滤器
     */
    void clear();

}
