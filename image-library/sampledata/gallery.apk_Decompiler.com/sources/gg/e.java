package gg;

import He.C0748d;
import L1.d;
import i.C0212a;
import ig.f;
import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jg.a;
import jg.c;
import kotlin.jvm.internal.j;
import me.h;
import ne.C1192j;
import ne.C1202t;
import ne.z;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements a {

    /* renamed from: a  reason: collision with root package name */
    public final C0748d f4578a;
    public final List b = C1202t.d;

    /* renamed from: c  reason: collision with root package name */
    public final Object f4579c = d.p(h.PUBLICATION, new d(0, this));
    public final LinkedHashMap d;

    public e(C0748d dVar, C0748d[] dVarArr, a[] aVarArr, Annotation[] annotationArr) {
        j.e(dVar, "baseClass");
        this.f4578a = dVar;
        if (dVarArr.length == aVarArr.length) {
            Set<Map.Entry> entrySet = z.e0(C1192j.A0(dVarArr, aVarArr)).entrySet();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : entrySet) {
                String i2 = ((a) entry.getValue()).getDescriptor().i();
                Object obj = linkedHashMap.get(i2);
                if (obj == null) {
                    linkedHashMap.containsKey(i2);
                }
                Map.Entry entry2 = (Map.Entry) obj;
                if (entry2 == null) {
                    linkedHashMap.put(i2, entry);
                } else {
                    throw new IllegalStateException(("Multiple sealed subclasses of '" + this.f4578a + "' have the same serial name '" + i2 + "': '" + entry2.getKey() + "', '" + entry.getKey() + '\'').toString());
                }
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.Z(linkedHashMap.size()));
            for (Map.Entry entry3 : linkedHashMap.entrySet()) {
                linkedHashMap2.put(entry3.getKey(), (a) ((Map.Entry) entry3.getValue()).getValue());
            }
            this.d = linkedHashMap2;
            this.b = C1192j.a0(annotationArr);
            return;
        }
        throw new IllegalArgumentException("All subclasses of sealed class " + dVar.o() + " should be marked @Serializable");
    }

    public final C0748d a() {
        return this.f4578a;
    }

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        f descriptor = getDescriptor();
        a a7 = cVar.a(descriptor);
        Object obj = null;
        String str = null;
        while (true) {
            int d2 = a7.d(getDescriptor());
            if (d2 != -1) {
                if (d2 == 0) {
                    str = a7.l(getDescriptor(), d2);
                } else if (d2 != 1) {
                    StringBuilder sb2 = new StringBuilder("Invalid index in polymorphic deserialization of ");
                    if (str == null) {
                        str = "unknown class";
                    }
                    sb2.append(str);
                    sb2.append("\n Expected 0, 1 or DECODE_DONE(-1), but found ");
                    sb2.append(d2);
                    throw new IllegalArgumentException(sb2.toString());
                } else if (str != null) {
                    obj = a7.n(getDescriptor(), d2, k.x(this, a7, str), (Object) null);
                } else {
                    throw new IllegalArgumentException("Cannot read polymorphic value before its type token");
                }
            } else if (obj != null) {
                a7.b(descriptor);
                return obj;
            } else {
                throw new IllegalArgumentException(C0212a.l("Polymorphic value has not been read for class ", str).toString());
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final f getDescriptor() {
        return (f) this.f4579c.getValue();
    }
}
