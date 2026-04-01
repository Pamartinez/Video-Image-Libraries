package com.samsung.android.sdk.moneta.event.entity;

import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.L;
import kg.Q;
import kg.T;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/event/entity/Event.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/event/entity/Event;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/event/entity/Event;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/event/entity/Event;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class Event$$serializer implements A {
    public static final Event$$serializer INSTANCE;
    private static final f descriptor;

    static {
        Event$$serializer event$$serializer = new Event$$serializer();
        INSTANCE = event$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.event.entity.Event", event$$serializer, 7);
        t.k("id", true);
        t.k("when", false);
        t.k("what", true);
        t.k("where", true);
        t.k("who", true);
        t.k("eventCategory", true);
        t.k("eventSubCategory", true);
        descriptor = t;
    }

    private Event$$serializer() {
    }

    public final a[] childSerializers() {
        a[] access$get$childSerializers$cp = Event.$childSerializers;
        return new a[]{D1.f.t(L.f4673a), When$$serializer.INSTANCE, access$get$childSerializers$cp[2], access$get$childSerializers$cp[3], access$get$childSerializers$cp[4], access$get$childSerializers$cp[5], access$get$childSerializers$cp[6]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.samsung.android.sdk.moneta.event.entity.When} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.event.entity.Event deserialize(jg.c r15) {
        /*
            r14 = this;
            java.lang.String r14 = "decoder"
            kotlin.jvm.internal.j.e(r15, r14)
            ig.f r14 = descriptor
            jg.a r15 = r15.a(r14)
            gg.a[] r0 = com.samsung.android.sdk.moneta.event.entity.Event.$childSerializers
            r1 = 1
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r3
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r3 = r1
        L_0x001b:
            if (r3 == 0) goto L_0x0085
            int r4 = r15.d(r14)
            switch(r4) {
                case -1: goto L_0x0083;
                case 0: goto L_0x0077;
                case 1: goto L_0x006b;
                case 2: goto L_0x005e;
                case 3: goto L_0x0051;
                case 4: goto L_0x0044;
                case 5: goto L_0x0037;
                case 6: goto L_0x002a;
                default: goto L_0x0024;
            }
        L_0x0024:
            gg.g r14 = new gg.g
            r14.<init>(r4)
            throw r14
        L_0x002a:
            r4 = 6
            r13 = r0[r4]
            java.lang.Object r4 = r15.n(r14, r4, r13, r12)
            r12 = r4
            com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum r12 = (com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum) r12
            r5 = r5 | 64
            goto L_0x001b
        L_0x0037:
            r4 = 5
            r13 = r0[r4]
            java.lang.Object r4 = r15.n(r14, r4, r13, r11)
            r11 = r4
            com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum r11 = (com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum) r11
            r5 = r5 | 32
            goto L_0x001b
        L_0x0044:
            r4 = 4
            r13 = r0[r4]
            java.lang.Object r4 = r15.n(r14, r4, r13, r10)
            r10 = r4
            java.util.List r10 = (java.util.List) r10
            r5 = r5 | 16
            goto L_0x001b
        L_0x0051:
            r4 = 3
            r13 = r0[r4]
            java.lang.Object r4 = r15.n(r14, r4, r13, r9)
            r9 = r4
            java.util.List r9 = (java.util.List) r9
            r5 = r5 | 8
            goto L_0x001b
        L_0x005e:
            r4 = 2
            r13 = r0[r4]
            java.lang.Object r4 = r15.n(r14, r4, r13, r8)
            r8 = r4
            java.util.List r8 = (java.util.List) r8
            r5 = r5 | 4
            goto L_0x001b
        L_0x006b:
            com.samsung.android.sdk.moneta.event.entity.When$$serializer r4 = com.samsung.android.sdk.moneta.event.entity.When$$serializer.INSTANCE
            java.lang.Object r4 = r15.n(r14, r1, r4, r7)
            r7 = r4
            com.samsung.android.sdk.moneta.event.entity.When r7 = (com.samsung.android.sdk.moneta.event.entity.When) r7
            r5 = r5 | 2
            goto L_0x001b
        L_0x0077:
            kg.L r4 = kg.L.f4673a
            java.lang.Object r4 = r15.q(r14, r2, r4, r6)
            r6 = r4
            java.lang.Long r6 = (java.lang.Long) r6
            r5 = r5 | 1
            goto L_0x001b
        L_0x0083:
            r3 = r2
            goto L_0x001b
        L_0x0085:
            r15.b(r14)
            com.samsung.android.sdk.moneta.event.entity.Event r4 = new com.samsung.android.sdk.moneta.event.entity.Event
            r13 = 0
            r4.<init>((int) r5, (java.lang.Long) r6, (com.samsung.android.sdk.moneta.event.entity.When) r7, (java.util.List) r8, (java.util.List) r9, (java.util.List) r10, (com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum) r11, (com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum) r12, (kg.a0) r13)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.entity.Event$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.event.entity.Event");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, Event event) {
        j.e(dVar, "encoder");
        j.e(event, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        Event.write$Self$pde_sdk_1_0_40_release(event, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
