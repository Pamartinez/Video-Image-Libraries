package com.samsung.android.sdk.moneta.travel.internal.model;

import c0.C0086a;
import gg.a;
import i.C0212a;
import ig.f;
import jg.b;
import kg.C1125f;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import og.k;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0010\b\b\u0018\u0000 22\u00020\u0001:\u000232B1\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bBE\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\n\u0010\u000fJ'\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010!JD\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010%\u001a\u00020$HÖ\u0001¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b'\u0010\u001fJ\u001a\u0010)\u001a\u00020\u00022\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b)\u0010*R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010+\u001a\u0004\b\u0003\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010,\u001a\u0004\b-\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010,\u001a\u0004\b.\u0010\u001cR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010/\u001a\u0004\b0\u0010\u001fR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u00101\u001a\u0004\b\t\u0010!¨\u00064"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "", "", "isDomestic", "", "startTime", "endTime", "", "travelDuration", "isAugmented", "<init>", "(Ljava/lang/Boolean;JJIZ)V", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/Boolean;JJIZLkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/Boolean;", "component2", "()J", "component3", "component4", "()I", "component5", "()Z", "copy", "(Ljava/lang/Boolean;JJIZ)Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Boolean;", "J", "getStartTime", "getEndTime", "I", "getTravelDuration", "Z", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelInfoInternal {
    public static final Companion Companion = new Companion((e) null);
    private final long endTime;
    private final boolean isAugmented;
    private final Boolean isDomestic;
    private final long startTime;
    private final int travelDuration;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return TravelInfoInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ TravelInfoInternal(int i2, Boolean bool, long j2, long j3, int i7, boolean z, a0 a0Var) {
        if (31 == (i2 & 31)) {
            this.isDomestic = bool;
            this.startTime = j2;
            this.endTime = j3;
            this.travelDuration = i7;
            this.isAugmented = z;
            return;
        }
        Q.f(i2, 31, TravelInfoInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ TravelInfoInternal copy$default(TravelInfoInternal travelInfoInternal, Boolean bool, long j2, long j3, int i2, boolean z, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            bool = travelInfoInternal.isDomestic;
        }
        if ((i7 & 2) != 0) {
            j2 = travelInfoInternal.startTime;
        }
        if ((i7 & 4) != 0) {
            j3 = travelInfoInternal.endTime;
        }
        if ((i7 & 8) != 0) {
            i2 = travelInfoInternal.travelDuration;
        }
        if ((i7 & 16) != 0) {
            z = travelInfoInternal.isAugmented;
        }
        long j8 = j3;
        long j10 = j2;
        return travelInfoInternal.copy(bool, j10, j8, i2, z);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(TravelInfoInternal travelInfoInternal, b bVar, f fVar) {
        bVar.c(fVar, 0, C1125f.f4694a, travelInfoInternal.isDomestic);
        k kVar = (k) bVar;
        kVar.s(fVar, 1, travelInfoInternal.startTime);
        kVar.s(fVar, 2, travelInfoInternal.endTime);
        kVar.r(3, travelInfoInternal.travelDuration, fVar);
        kVar.o(fVar, 4, travelInfoInternal.isAugmented);
    }

    public final Boolean component1() {
        return this.isDomestic;
    }

    public final long component2() {
        return this.startTime;
    }

    public final long component3() {
        return this.endTime;
    }

    public final int component4() {
        return this.travelDuration;
    }

    public final boolean component5() {
        return this.isAugmented;
    }

    public final TravelInfoInternal copy(Boolean bool, long j2, long j3, int i2, boolean z) {
        return new TravelInfoInternal(bool, j2, j3, i2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TravelInfoInternal)) {
            return false;
        }
        TravelInfoInternal travelInfoInternal = (TravelInfoInternal) obj;
        if (j.a(this.isDomestic, travelInfoInternal.isDomestic) && this.startTime == travelInfoInternal.startTime && this.endTime == travelInfoInternal.endTime && this.travelDuration == travelInfoInternal.travelDuration && this.isAugmented == travelInfoInternal.isAugmented) {
            return true;
        }
        return false;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final int getTravelDuration() {
        return this.travelDuration;
    }

    public int hashCode() {
        int i2;
        Boolean bool = this.isDomestic;
        if (bool == null) {
            i2 = 0;
        } else {
            i2 = bool.hashCode();
        }
        return Boolean.hashCode(this.isAugmented) + C0212a.b(this.travelDuration, C0212a.c(C0212a.c(i2 * 31, 31, this.startTime), 31, this.endTime), 31);
    }

    public final boolean isAugmented() {
        return this.isAugmented;
    }

    public final Boolean isDomestic() {
        return this.isDomestic;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TravelInfoInternal(isDomestic=");
        sb2.append(this.isDomestic);
        sb2.append(", startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", travelDuration=");
        sb2.append(this.travelDuration);
        sb2.append(", isAugmented=");
        return C0086a.n(sb2, this.isAugmented, ')');
    }

    public TravelInfoInternal(Boolean bool, long j2, long j3, int i2, boolean z) {
        this.isDomestic = bool;
        this.startTime = j2;
        this.endTime = j3;
        this.travelDuration = i2;
        this.isAugmented = z;
    }
}
