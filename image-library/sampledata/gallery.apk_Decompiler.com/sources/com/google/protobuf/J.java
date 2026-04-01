package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J extends L {

    /* renamed from: c  reason: collision with root package name */
    public static final Class f1582c = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();

    public static List d(int i2, long j2, Object obj) {
        List list;
        List list2 = (List) p0.f1625c.k(obj, j2);
        if (list2.isEmpty()) {
            if (list2 instanceof I) {
                list = new H(i2);
            } else if (!(list2 instanceof b0) || !(list2 instanceof Internal$ProtobufList)) {
                list = new ArrayList(i2);
            } else {
                list = ((Internal$ProtobufList) list2).a(i2);
            }
            p0.p(j2, obj, list);
            return list;
        } else if (f1582c.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + i2);
            arrayList.addAll(list2);
            p0.p(j2, obj, arrayList);
            return arrayList;
        } else if (list2 instanceof k0) {
            H h5 = new H(list2.size() + i2);
            h5.addAll((k0) list2);
            p0.p(j2, obj, h5);
            return h5;
        } else {
            if ((list2 instanceof b0) && (list2 instanceof Internal$ProtobufList)) {
                Internal$ProtobufList internal$ProtobufList = (Internal$ProtobufList) list2;
                if (!internal$ProtobufList.j()) {
                    Internal$ProtobufList a7 = internal$ProtobufList.a(list2.size() + i2);
                    p0.p(j2, obj, a7);
                    return a7;
                }
            }
            return list2;
        }
    }

    public final void a(Object obj, long j2) {
        Object obj2;
        List list = (List) p0.f1625c.k(obj, j2);
        if (list instanceof I) {
            obj2 = ((I) list).c();
        } else if (!f1582c.isAssignableFrom(list.getClass())) {
            if (!(list instanceof b0) || !(list instanceof Internal$ProtobufList)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                Internal$ProtobufList internal$ProtobufList = (Internal$ProtobufList) list;
                if (internal$ProtobufList.j()) {
                    internal$ProtobufList.d();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        p0.p(j2, obj, obj2);
    }

    public final void b(long j2, Object obj, Object obj2) {
        List list = (List) p0.f1625c.k(obj2, j2);
        List d = d(list.size(), j2, obj);
        int size = d.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            d.addAll(list);
        }
        if (size > 0) {
            list = d;
        }
        p0.p(j2, obj, list);
    }

    public final List c(Object obj, long j2) {
        return d(10, j2, obj);
    }
}
