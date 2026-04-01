package com.google.protobuf;

import com.samsung.android.gallery.support.utils.MapUtil;
import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum x0 {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(MapUtil.INVALID_LOCATION)),
    BOOLEAN(Boolean.FALSE),
    STRING(""),
    BYTE_STRING(ByteString.e),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object defaultDefault;

    /* access modifiers changed from: public */
    x0(Serializable serializable) {
        this.defaultDefault = serializable;
    }
}
