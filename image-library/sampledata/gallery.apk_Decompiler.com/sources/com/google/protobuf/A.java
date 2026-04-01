package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A implements Comparable {
    public final Internal$EnumLiteMap d;
    public final int e;
    public final WireFormat$FieldType f;

    public A(Internal$EnumLiteMap internal$EnumLiteMap, int i2, WireFormat$FieldType wireFormat$FieldType, boolean z, boolean z3) {
        this.d = internal$EnumLiteMap;
        this.e = i2;
        this.f = wireFormat$FieldType;
    }

    public final int compareTo(Object obj) {
        return this.e - ((A) obj).e;
    }
}
