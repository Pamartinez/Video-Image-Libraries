package rf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Q {
    DOUBLE(S.DOUBLE, 1),
    FLOAT(S.FLOAT, 5),
    INT64(r5, 0),
    UINT64(r5, 0),
    INT32(r11, 0),
    FIXED64(r5, 1),
    FIXED32(r11, 5),
    BOOL(S.BOOLEAN, 0),
    STRING(S.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(S.BYTE_STRING, 2),
    UINT32(r11, 0),
    ENUM(S.ENUM, 0),
    SFIXED32(r11, 5),
    SFIXED64(r5, 1),
    SINT32(r11, 0),
    SINT64(r5, 0);
    
    private final S javaType;
    private final int wireType;

    /* access modifiers changed from: public */
    Q(S s, int i2) {
        this.javaType = s;
        this.wireType = i2;
    }

    public final S a() {
        return this.javaType;
    }

    public final int b() {
        return this.wireType;
    }

    public boolean c() {
        return !(this instanceof M);
    }
}
