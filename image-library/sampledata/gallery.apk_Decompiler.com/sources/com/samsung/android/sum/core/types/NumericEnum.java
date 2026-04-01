package com.samsung.android.sum.core.types;

import Ad.C0720a;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.channel.h;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface NumericEnum {
    public static final String SEP = ":";

    static <T> T fromJson(Class<T> cls, String str) {
        return Stream.of(str.split(SEP)).filter(new a(12)).map(new b(14, cls)).findFirst().orElseThrow(new h(0));
    }

    static <T> T fromValue(Class<T> cls, int i2) {
        if (NumericEnum.class.isAssignableFrom(cls)) {
            try {
                Method method = cls.getMethod("getValue", (Class[]) null);
                Object[] enumConstants = cls.getEnumConstants();
                Objects.requireNonNull(enumConstants);
                return Arrays.stream(enumConstants).filter(new a(method, i2, 0)).findFirst().orElseThrow(new C0720a(19));
            } catch (NoSuchMethodException unused) {
                throw new UnsupportedOperationException("type is not NumericEnum");
            }
        } else {
            throw new UnsupportedOperationException("type is not NumericEnum");
        }
    }

    /* access modifiers changed from: private */
    static /* synthetic */ boolean lambda$fromValue$0(Method method, int i2, Object obj) {
        try {
            if (((Integer) method.invoke(obj, (Object[]) null)).intValue() == i2) {
                return true;
            }
            return false;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    /* access modifiers changed from: private */
    static /* synthetic */ IllegalArgumentException lambda$fromValue$1() {
        return new IllegalArgumentException("no matched value");
    }

    int getValue();

    String stringfy();
}
