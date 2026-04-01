package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum WireFormat$FieldType {
    DOUBLE(x0.DOUBLE, 1),
    FLOAT(x0.FLOAT, 5),
    INT64(r5, 0),
    UINT64(r5, 0),
    INT32(r11, 0),
    FIXED64(r5, 1),
    FIXED32(r11, 5),
    BOOL(x0.BOOLEAN, 0),
    STRING(x0.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(x0.BYTE_STRING, 2),
    UINT32(r11, 0),
    ENUM(x0.ENUM, 0),
    SFIXED32(r11, 5),
    SFIXED64(r5, 1),
    SINT32(r11, 0),
    SINT64(r5, 0);
    
    private final x0 javaType;
    private final int wireType;

    /* access modifiers changed from: public */
    WireFormat$FieldType(x0 x0Var, int i2) {
        this.javaType = x0Var;
        this.wireType = i2;
    }
}
