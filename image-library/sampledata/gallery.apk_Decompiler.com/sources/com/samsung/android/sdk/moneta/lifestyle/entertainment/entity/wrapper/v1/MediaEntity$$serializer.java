package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1;

import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
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

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class MediaEntity$$serializer implements A {
    public static final MediaEntity$$serializer INSTANCE;
    private static final f descriptor;

    static {
        MediaEntity$$serializer mediaEntity$$serializer = new MediaEntity$$serializer();
        INSTANCE = mediaEntity$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity", mediaEntity$$serializer, 5);
        t.k("mediaType", true);
        t.k("title", true);
        t.k(MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST, false);
        t.k("album", true);
        t.k("preferences", false);
        descriptor = t;
    }

    private MediaEntity$$serializer() {
    }

    public final a[] childSerializers() {
        a t = D1.f.t(MediaEntity.$childSerializers[0]);
        e0 e0Var = e0.f4693a;
        return new a[]{t, D1.f.t(e0Var), e0Var, D1.f.t(e0Var), MediaPreferences$$serializer.INSTANCE};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaPreferences} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity deserialize(jg.c r13) {
        /*
            r12 = this;
            java.lang.String r12 = "decoder"
            kotlin.jvm.internal.j.e(r13, r12)
            ig.f r12 = descriptor
            jg.a r13 = r13.a(r12)
            gg.a[] r0 = com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity.$childSerializers
            r1 = 1
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r3
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r3 = r1
        L_0x0019:
            if (r3 == 0) goto L_0x006e
            int r4 = r13.d(r12)
            r11 = -1
            if (r4 == r11) goto L_0x006c
            if (r4 == 0) goto L_0x0060
            if (r4 == r1) goto L_0x0054
            r11 = 2
            if (r4 == r11) goto L_0x004d
            r11 = 3
            if (r4 == r11) goto L_0x0041
            r11 = 4
            if (r4 != r11) goto L_0x003b
            com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaPreferences$$serializer r4 = com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaPreferences$$serializer.INSTANCE
            java.lang.Object r4 = r13.n(r12, r11, r4, r10)
            r10 = r4
            com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaPreferences r10 = (com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaPreferences) r10
            r5 = r5 | 16
            goto L_0x0019
        L_0x003b:
            gg.g r12 = new gg.g
            r12.<init>(r4)
            throw r12
        L_0x0041:
            kg.e0 r4 = kg.e0.f4693a
            java.lang.Object r4 = r13.q(r12, r11, r4, r9)
            r9 = r4
            java.lang.String r9 = (java.lang.String) r9
            r5 = r5 | 8
            goto L_0x0019
        L_0x004d:
            java.lang.String r8 = r13.l(r12, r11)
            r5 = r5 | 4
            goto L_0x0019
        L_0x0054:
            kg.e0 r4 = kg.e0.f4693a
            java.lang.Object r4 = r13.q(r12, r1, r4, r7)
            r7 = r4
            java.lang.String r7 = (java.lang.String) r7
            r5 = r5 | 2
            goto L_0x0019
        L_0x0060:
            r4 = r0[r2]
            java.lang.Object r4 = r13.q(r12, r2, r4, r6)
            r6 = r4
            com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType r6 = (com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType) r6
            r5 = r5 | 1
            goto L_0x0019
        L_0x006c:
            r3 = r2
            goto L_0x0019
        L_0x006e:
            r13.b(r12)
            com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity r4 = new com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity
            r11 = 0
            r4.<init>((int) r5, (com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType) r6, (java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9, (com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaPreferences) r10, (kg.a0) r11)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, MediaEntity mediaEntity) {
        j.e(dVar, "encoder");
        j.e(mediaEntity, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        MediaEntity.write$Self$pde_sdk_1_0_40_release(mediaEntity, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
