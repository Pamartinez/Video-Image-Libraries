package com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1;

import com.samsung.android.sdk.mobileservice.social.group.provider.GroupInvitationContract;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.H;
import kg.Q;
import kg.T;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/wrapper/v1/ExerciseEntity.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/wrapper/v1/ExerciseEntity;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/wrapper/v1/ExerciseEntity;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/wrapper/v1/ExerciseEntity;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class ExerciseEntity$$serializer implements A {
    public static final ExerciseEntity$$serializer INSTANCE;
    private static final f descriptor;

    static {
        ExerciseEntity$$serializer exerciseEntity$$serializer = new ExerciseEntity$$serializer();
        INSTANCE = exerciseEntity$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity", exerciseEntity$$serializer, 4);
        t.k(GroupInvitationContract.Invitation.GROUP_TYPE, false);
        t.k("name", false);
        t.k(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, false);
        t.k("preferences", false);
        descriptor = t;
    }

    private ExerciseEntity$$serializer() {
    }

    public final a[] childSerializers() {
        H h5 = H.f4669a;
        return new a[]{h5, e0.f4693a, h5, ExercisePreferences$$serializer.INSTANCE};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExercisePreferences} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity deserialize(jg.c r11) {
        /*
            r10 = this;
            java.lang.String r10 = "decoder"
            kotlin.jvm.internal.j.e(r11, r10)
            ig.f r10 = descriptor
            jg.a r11 = r11.a(r10)
            r0 = 1
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r4
            r7 = r5
            r6 = r2
            r8 = r6
            r2 = r0
        L_0x0014:
            if (r2 == 0) goto L_0x0050
            int r3 = r11.d(r10)
            r9 = -1
            if (r3 == r9) goto L_0x004e
            if (r3 == 0) goto L_0x0047
            if (r3 == r0) goto L_0x0040
            r9 = 2
            if (r3 == r9) goto L_0x0039
            r9 = 3
            if (r3 != r9) goto L_0x0033
            com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExercisePreferences$$serializer r3 = com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExercisePreferences$$serializer.INSTANCE
            java.lang.Object r3 = r11.n(r10, r9, r3, r8)
            r8 = r3
            com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExercisePreferences r8 = (com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExercisePreferences) r8
            r4 = r4 | 8
            goto L_0x0014
        L_0x0033:
            gg.g r10 = new gg.g
            r10.<init>(r3)
            throw r10
        L_0x0039:
            int r7 = r11.x(r10, r9)
            r4 = r4 | 4
            goto L_0x0014
        L_0x0040:
            java.lang.String r6 = r11.l(r10, r0)
            r4 = r4 | 2
            goto L_0x0014
        L_0x0047:
            int r5 = r11.x(r10, r1)
            r4 = r4 | 1
            goto L_0x0014
        L_0x004e:
            r2 = r1
            goto L_0x0014
        L_0x0050:
            r11.b(r10)
            com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity r3 = new com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity
            r9 = 0
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, ExerciseEntity exerciseEntity) {
        j.e(dVar, "encoder");
        j.e(exerciseEntity, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        ExerciseEntity.write$Self$pde_sdk_1_0_40_release(exerciseEntity, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
