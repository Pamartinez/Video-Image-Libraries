package com.google.protobuf;

import com.samsung.android.gallery.support.utils.MapUtil;
import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum G {
    VOID(Void.class, Void.class, (Class) null),
    INT(r4, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(MapUtil.INVALID_LOCATION)),
    BOOLEAN(Boolean.TYPE, Boolean.class, r11),
    STRING(String.class, String.class, ""),
    BYTE_STRING(ByteString.class, ByteString.class, ByteString.e),
    ENUM(r4, Integer.class, (Class) null),
    MESSAGE(Object.class, Object.class, (Class) null);
    
    private final Class<?> boxedType;
    private final Object defaultDefault;
    private final Class<?> type;

    /* access modifiers changed from: public */
    G(Class cls, Class cls2, Serializable serializable) {
        this.type = cls;
        this.boxedType = cls2;
        this.defaultDefault = serializable;
    }

    public final Class a() {
        return this.boxedType;
    }
}
