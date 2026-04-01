package com.samsung.android.sdk.moneta.travel.internal.model;

import gg.a;
import gg.g;
import ig.f;
import java.util.List;
import jg.b;
import jg.c;
import jg.d;
import kg.A;
import kg.Q;
import kg.T;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class TravelPlanInternal$$serializer implements A {
    public static final TravelPlanInternal$$serializer INSTANCE;
    private static final f descriptor;

    static {
        TravelPlanInternal$$serializer travelPlanInternal$$serializer = new TravelPlanInternal$$serializer();
        INSTANCE = travelPlanInternal$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.travel.internal.model.TravelPlanInternal", travelPlanInternal$$serializer, 2);
        t.k("travelInfo", false);
        t.k("dailyItineraries", false);
        descriptor = t;
    }

    private TravelPlanInternal$$serializer() {
    }

    public final a[] childSerializers() {
        return new a[]{TravelInfoInternal$$serializer.INSTANCE, TravelPlanInternal.$childSerializers[1]};
    }

    public final TravelPlanInternal deserialize(c cVar) {
        j.e(cVar, "decoder");
        f fVar = descriptor;
        jg.a a7 = cVar.a(fVar);
        a[] access$get$childSerializers$cp = TravelPlanInternal.$childSerializers;
        boolean z = true;
        int i2 = 0;
        TravelInfoInternal travelInfoInternal = null;
        List list = null;
        while (z) {
            int d = a7.d(fVar);
            if (d == -1) {
                z = false;
            } else if (d == 0) {
                travelInfoInternal = (TravelInfoInternal) a7.n(fVar, 0, TravelInfoInternal$$serializer.INSTANCE, travelInfoInternal);
                i2 |= 1;
            } else if (d == 1) {
                list = (List) a7.n(fVar, 1, access$get$childSerializers$cp[1], list);
                i2 |= 2;
            } else {
                throw new g(d);
            }
        }
        a7.b(fVar);
        return new TravelPlanInternal(i2, travelInfoInternal, list, (a0) null);
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, TravelPlanInternal travelPlanInternal) {
        j.e(dVar, "encoder");
        j.e(travelPlanInternal, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        TravelPlanInternal.write$Self$pde_sdk_1_0_40_release(travelPlanInternal, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
