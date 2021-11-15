package com.linsh.lshutils.utils;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2021/11/15
 *    desc   : Map 相关
 * </pre>
 */
public class MapUtilsEx {

    /**
     * 获取给定键所映射的值，如果不存在映射，则给定的返回默认值
     *
     * @param map          集合
     * @param key          键
     * @param defaultValue 默认值
     */
    @Nullable
    public static <K, V> V getOrDefault(@NonNull Map<K, V> map, @NonNull K key, @Nullable V defaultValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return map.getOrDefault(key, defaultValue);
        }
        V v;
        return (((v = map.get(key)) != null) || map.containsKey(key)) ? v : defaultValue;
    }

    /**
     * 获取首个给定键集合中存在映射的值
     *
     * @param map  集合
     * @param keys 键集合
     */
    @Nullable
    public static <K, V> V getFirst(@NonNull Map<K, V> map, @NonNull K... keys) {
        for (K key : keys) {
            V v = map.get(key);
            if (v != null || map.containsKey(key))
                return v;
        }
        return null;
    }

    /**
     * 获取首个给定键集合中存在映射且值不为 null 的值
     *
     * @param map  集合
     * @param keys 键集合
     */
    @Nullable
    public static <K, V> V getFirstNotNull(@NonNull Map<K, V> map, @NonNull K... keys) {
        for (K key : keys) {
            V v = map.get(key);
            if (v != null)
                return v;
        }
        return null;
    }

    /**
     * 根据给定的键设置值，如果值为 null，则使用默认值
     *
     * @param map          集合
     * @param key          键
     * @param value        值
     * @param defaultValue 默认值
     */
    public static <K, V> void putOrDefault(@NonNull Map<K, V> map, @Nullable K key, @Nullable V value, @Nullable V defaultValue) {
        if (value != null) {
            map.put(key, value);
        } else {
            map.put(key, defaultValue);
        }
    }

    /**
     * 根据给定的键设置值，如果值为 null，则不设置
     *
     * @param map   集合
     * @param key   键
     * @param value 值
     */
    public static <K, V> void putNotNull(@NonNull Map<K, V> map, @Nullable K key, @Nullable V value) {
        if (value != null) {
            map.put(key, value);
        }
    }
}
