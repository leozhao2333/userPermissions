package com.zly.common.service;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */

public interface RedisService {

    /**
     * 将值放入缓存
     * @param key   键
     * @param value 值
     * @return true成功 false 失败
     */
    void set(String key, String value);

    /**
     * 将值放入缓存并设置时间
     * @param key   键
     * @param value 值
     * @return true成功 false 失败
     */
    void set(String key, String value, long time);

    /**
     * 根据key从缓存拿值
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 给一个指定的 key 值附加过期时间
     *
     * @param key
     * @param time
     * @return
     */
    boolean expire(String key, long time);

    /**
     * 删除
     * @param key
     */
    void deleteByKey(String key);

    /**
     * 根据key 获取过期时间
     *
     * @param key
     * @return
     */
    long getTime(String key);

    /**
     * 移除指定key 的过期时间
     *
     * @param key
     * @return
     */
    boolean persist(String key);

    /**
     * 批量添加 key (重复的键会覆盖)
     *
     * @param keyAndValue
     */
    void batchSet(Map<String, String> keyAndValue);

    /**
     * 批量添加 key-value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     *
     * @param keyAndValue
     */
    void batchSetIfAbsent(Map<String, String> keyAndValue);

    /**
     * 对一个 key-value 的值进行加减操作,
     * 如果该 key 不存在 将创建一个key 并赋值该 number
     * 如果 key 存在,但 value 不是长整型 ,将报错
     *
     * @param key
     * @param number
     */
    Long increment(String key, long number);

    /**
     * 对一个 key-value 的值进行加减操作,
     * 如果该 key 不存在 将创建一个key 并赋值该 number
     * 如果 key 存在,但 value 不是 纯数字 ,将报错
     *
     * @param key
     * @param number
     */
    Double increment(String key, double number);

}
