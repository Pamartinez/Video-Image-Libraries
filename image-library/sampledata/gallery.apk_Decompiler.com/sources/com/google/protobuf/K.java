package com.google.protobuf;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K extends L {
    public final void a(Object obj, long j2) {
        ((Internal$ProtobufList) p0.f1625c.k(obj, j2)).d();
    }

    public final void b(long j2, Object obj, Object obj2) {
        o0 o0Var = p0.f1625c;
        Internal$ProtobufList internal$ProtobufList = (Internal$ProtobufList) o0Var.k(obj, j2);
        Internal$ProtobufList internal$ProtobufList2 = (Internal$ProtobufList) o0Var.k(obj2, j2);
        int size = internal$ProtobufList.size();
        int size2 = internal$ProtobufList2.size();
        if (size > 0 && size2 > 0) {
            if (!internal$ProtobufList.j()) {
                internal$ProtobufList = internal$ProtobufList.a(size2 + size);
            }
            internal$ProtobufList.addAll(internal$ProtobufList2);
        }
        if (size > 0) {
            internal$ProtobufList2 = internal$ProtobufList;
        }
        p0.p(j2, obj, internal$ProtobufList2);
    }

    public final List c(Object obj, long j2) {
        int i2;
        Internal$ProtobufList internal$ProtobufList = (Internal$ProtobufList) p0.f1625c.k(obj, j2);
        if (internal$ProtobufList.j()) {
            return internal$ProtobufList;
        }
        int size = internal$ProtobufList.size();
        if (size == 0) {
            i2 = 10;
        } else {
            i2 = size * 2;
        }
        Internal$ProtobufList a7 = internal$ProtobufList.a(i2);
        p0.p(j2, obj, a7);
        return a7;
    }
}
