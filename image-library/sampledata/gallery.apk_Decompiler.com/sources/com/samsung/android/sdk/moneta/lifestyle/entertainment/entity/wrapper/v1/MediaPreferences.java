package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1;

import N2.j;
import gg.a;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000 #2\u00020\u0001:\u0002$#B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B#\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\tJ'\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0014J\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR \u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010\u001f\u0012\u0004\b!\u0010\"\u001a\u0004\b \u0010\u0014¨\u0006%"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;", "", "", "count", "<init>", "(I)V", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(IILkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;Ljg/b;Lig/f;)V", "write$Self", "component1", "()I", "copy", "(I)Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCount", "getCount$annotations", "()V", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaPreferences {
    public static final Companion Companion = new Companion((e) null);
    private final int count;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return MediaPreferences$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ MediaPreferences(int i2, int i7, a0 a0Var) {
        if (1 == (i2 & 1)) {
            this.count = i7;
        } else {
            Q.f(i2, 1, MediaPreferences$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ MediaPreferences copy$default(MediaPreferences mediaPreferences, int i2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = mediaPreferences.count;
        }
        return mediaPreferences.copy(i2);
    }

    public final int component1() {
        return this.count;
    }

    public final MediaPreferences copy(int i2) {
        return new MediaPreferences(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof MediaPreferences) && this.count == ((MediaPreferences) obj).count) {
            return true;
        }
        return false;
    }

    public final int getCount() {
        return this.count;
    }

    public int hashCode() {
        return Integer.hashCode(this.count);
    }

    public String toString() {
        return j.e(new StringBuilder("MediaPreferences(count="), this.count, ')');
    }

    public MediaPreferences(int i2) {
        this.count = i2;
    }

    public static /* synthetic */ void getCount$annotations() {
    }
}
