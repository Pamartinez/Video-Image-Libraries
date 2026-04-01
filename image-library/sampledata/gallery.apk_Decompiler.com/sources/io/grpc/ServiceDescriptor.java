package io.grpc;

import B1.a;
import D0.e;
import E2.k;
import He.F;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ServiceDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public final String f4622a;
    public final List b;

    public ServiceDescriptor(e eVar) {
        String str = (String) eVar.e;
        this.f4622a = str;
        ArrayList<MethodDescriptor> arrayList = (ArrayList) eVar.f;
        HashSet hashSet = new HashSet(arrayList.size());
        for (MethodDescriptor methodDescriptor : arrayList) {
            F.n(methodDescriptor, "method");
            String str2 = methodDescriptor.b;
            String str3 = methodDescriptor.f4621c;
            F.l(str.equals(str3), "service names %s != %s", str3, str);
            F.h("duplicate name %s", str2, hashSet.add(str2));
        }
        this.b = Collections.unmodifiableList(new ArrayList(arrayList));
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4622a, "name");
        V.a((Object) null, "schemaDescriptor");
        V.a(this.b, "methods");
        V.b = true;
        return V.toString();
    }
}
