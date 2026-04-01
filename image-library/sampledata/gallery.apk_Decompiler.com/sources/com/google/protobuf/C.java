package com.google.protobuf;

import java.util.AbstractList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends AbstractList {
    public final List d;
    public final Internal$ListAdapter$Converter e;

    public C(List list, Internal$ListAdapter$Converter internal$ListAdapter$Converter) {
        this.d = list;
        this.e = internal$ListAdapter$Converter;
    }

    public final Object get(int i2) {
        return this.e.convert(this.d.get(i2));
    }

    public final int size() {
        return this.d.size();
    }
}
