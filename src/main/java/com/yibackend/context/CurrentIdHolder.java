package com.yibackend.context;
import java.lang.ThreadLocal;

public class CurrentIdHolder {
    private static final ThreadLocal<Long> threadLocal = new java.lang.ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
