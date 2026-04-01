package com.google.common.util.concurrent;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements PrivilegedExceptionAction {
    public static Unsafe a() {
        Class<Unsafe> cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get((Object) null);
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        throw new NoSuchFieldError("the Unsafe");
    }

    public final /* bridge */ /* synthetic */ Object run() {
        return a();
    }
}
