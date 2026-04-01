package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.Q;
import kg.T;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonEntity.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonEntity;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonEntity;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonEntity;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class PersonEntity$$serializer implements A {
    public static final PersonEntity$$serializer INSTANCE;
    private static final f descriptor;

    static {
        PersonEntity$$serializer personEntity$$serializer = new PersonEntity$$serializer();
        INSTANCE = personEntity$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity", personEntity$$serializer, 5);
        t.k("id", false);
        t.k("name", true);
        t.k("phoneNumber", true);
        t.k(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID, false);
        t.k("preferences", false);
        descriptor = t;
    }

    private PersonEntity$$serializer() {
    }

    public final a[] childSerializers() {
        a t = D1.f.t(PersonPreference$$serializer.INSTANCE);
        e0 e0Var = e0.f4693a;
        return new a[]{e0Var, e0Var, e0Var, e0Var, t};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity deserialize(jg.c r12) {
        /*
            r11 = this;
            java.lang.String r11 = "decoder"
            kotlin.jvm.internal.j.e(r12, r11)
            ig.f r11 = descriptor
            jg.a r12 = r12.a(r11)
            r0 = 1
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r2 = r0
        L_0x0015:
            if (r2 == 0) goto L_0x005b
            int r3 = r12.d(r11)
            r10 = -1
            if (r3 == r10) goto L_0x0059
            if (r3 == 0) goto L_0x0052
            if (r3 == r0) goto L_0x004b
            r10 = 2
            if (r3 == r10) goto L_0x0044
            r10 = 3
            if (r3 == r10) goto L_0x003d
            r10 = 4
            if (r3 != r10) goto L_0x0037
            com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference$$serializer r3 = com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference$$serializer.INSTANCE
            java.lang.Object r3 = r12.q(r11, r10, r3, r9)
            r9 = r3
            com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference r9 = (com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference) r9
            r4 = r4 | 16
            goto L_0x0015
        L_0x0037:
            gg.g r11 = new gg.g
            r11.<init>(r3)
            throw r11
        L_0x003d:
            java.lang.String r8 = r12.l(r11, r10)
            r4 = r4 | 8
            goto L_0x0015
        L_0x0044:
            java.lang.String r7 = r12.l(r11, r10)
            r4 = r4 | 4
            goto L_0x0015
        L_0x004b:
            java.lang.String r6 = r12.l(r11, r0)
            r4 = r4 | 2
            goto L_0x0015
        L_0x0052:
            java.lang.String r5 = r12.l(r11, r1)
            r4 = r4 | 1
            goto L_0x0015
        L_0x0059:
            r2 = r1
            goto L_0x0015
        L_0x005b:
            r12.b(r11)
            com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity r3 = new com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity
            r10 = 0
            r3.<init>((int) r4, (java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8, (com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference) r9, (kg.a0) r10)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, PersonEntity personEntity) {
        j.e(dVar, "encoder");
        j.e(personEntity, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        PersonEntity.write$Self$pde_sdk_1_0_40_release(personEntity, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
