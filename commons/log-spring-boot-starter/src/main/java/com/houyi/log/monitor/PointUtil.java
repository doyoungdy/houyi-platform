package com.houyi.log.monitor;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志埋点工具类
 *
 * @Author: houyi
 */
@Slf4j
public class PointUtil {
    private PointUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SPLIT = "|";

    /**
     * 格式为：{时间}|{来源}|{对象id}|{类型}|{对象属性(以&分割)}
     * 例子1：2016-07-27 23:37:23|business-center|1|user-login|ip=xxx.xxx.xx&userName=张三&userType=后台管理员
     * 例子2：2016-07-27 23:37:23|file-center|c0a895e114526786450161001d1ed9|file-upload|fileName=xxx&filePath=xxx
     *
     * @param id      对象id
     * @param type    类型
     * @param message 对象属性
     */
    public static void info(String id, String type, String message) {
        log.info(id + SPLIT + type + SPLIT + message);
    }
    
    public static void debug(String id, String type, String message) {
        log.debug(id + SPLIT + type + SPLIT + message);
    }
    
    public static void warn(String id, String type, String message) {
        log.warn(id + SPLIT + type + SPLIT + message);
    }
    
    public static void trace(String id, String type, String message) {
        log.trace(id + SPLIT + type + SPLIT + message);
    }
    
    public static void error(String id, String type, String message) {
        log.error(id + SPLIT + type + SPLIT + message);
    }
}
