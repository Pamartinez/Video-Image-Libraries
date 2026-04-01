package com.samsung.android.sdk.moneta.travel.internal.model;

import Bd.C0725a;
import He.C0748d;
import gg.a;
import i.C0212a;
import ig.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jg.b;
import kg.C1122c;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import lg.g;
import me.i;
import me.x;
import ne.C1194l;
import og.k;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000 *2\u00020\u0001:\u0002*+B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\rJ'\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ*\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\tHÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u0018R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010(\u001a\u0004\b)\u0010\u001a¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "travelInfo", "", "Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "dailyItineraries", "<init>", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;Ljava/util/List;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;Ljava/util/List;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "component2", "()Ljava/util/List;", "copy", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "getTravelInfo", "Ljava/util/List;", "getDailyItineraries", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelPlanInternal {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, new C1122c(DailyItineraryInternal$$serializer.INSTANCE)};
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "TravelPlanInternal";
    private final List<DailyItineraryInternal> dailyItineraries;
    private final TravelInfoInternal travelInfo;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal$Companion;", "", "<init>", "()V", "", "string", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "fromString", "(Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelPlanInternal;", "Lgg/a;", "serializer", "()Lgg/a;", "TAG", "Ljava/lang/String;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final x fromString$lambda$2(g gVar) {
            Object obj;
            j.e(gVar, "$this$Json");
            B0.a aVar = new B0.a();
            w wVar = v.f4727a;
            C0748d b = wVar.b(TransportationBaseInternal.class);
            ArrayList arrayList = new ArrayList();
            C0748d b5 = wVar.b(TransportationInternal.class);
            a serializer = TransportationInternal.Companion.serializer();
            j.e(serializer, "serializer");
            arrayList.add(new i(b5, serializer));
            C0748d b8 = wVar.b(FlightInternal.class);
            a serializer2 = FlightInternal.Companion.serializer();
            j.e(serializer2, "serializer");
            arrayList.add(new i(b8, serializer2));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i iVar = (i) it.next();
                C0748d dVar = (C0748d) iVar.d;
                a aVar2 = (a) iVar.e;
                j.c(dVar, "null cannot be cast to non-null type kotlin.reflect.KClass<Base of kotlinx.serialization.modules.PolymorphicModuleBuilder.buildTo$lambda$1>");
                j.c(aVar2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                j.e(dVar, "concreteClass");
                j.e(aVar2, "concreteSerializer");
                String i2 = aVar2.getDescriptor().i();
                HashMap hashMap = (HashMap) aVar.f;
                Object obj2 = hashMap.get(b);
                if (obj2 == null) {
                    obj2 = new HashMap();
                    hashMap.put(b, obj2);
                }
                Map map = (Map) obj2;
                a aVar3 = (a) map.get(dVar);
                HashMap hashMap2 = (HashMap) aVar.g;
                Object obj3 = hashMap2.get(b);
                if (obj3 == null) {
                    obj3 = new HashMap();
                    hashMap2.put(b, obj3);
                }
                Map map2 = (Map) obj3;
                if (aVar3 != null) {
                    if (aVar3.equals(aVar2)) {
                        map2.remove(aVar3.getDescriptor().i());
                    } else {
                        String str = "Serializer for " + dVar + " already registered in the scope of " + b;
                        j.e(str, "msg");
                        throw new IllegalArgumentException(str);
                    }
                }
                a aVar4 = (a) map2.get(i2);
                if (aVar4 != null) {
                    Object obj4 = hashMap.get(b);
                    j.b(obj4);
                    Iterator it2 = ((Iterable) C1194l.F0(((Map) obj4).entrySet()).b).iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it2.next();
                        if (((Map.Entry) obj).getValue() == aVar4) {
                            break;
                        }
                    }
                    throw new IllegalArgumentException("Multiple polymorphic serializers for base class '" + b + "' have the same serial name '" + i2 + "': '" + dVar + "' and '" + ((Map.Entry) obj) + '\'');
                }
                map.put(dVar, aVar2);
                map2.put(i2, aVar2);
            }
            gVar.f = new B0.a((Map) (HashMap) aVar.e, (Map) (HashMap) aVar.f, (Map) (HashMap) aVar.d, (Map) (HashMap) aVar.g, (Map) (HashMap) aVar.f34h);
            gVar.f4899a = true;
            gVar.e = "kind";
            gVar.f4900c = true;
            return x.f4917a;
        }

        public final TravelPlanInternal fromString(String str) {
            j.e(str, "string");
            try {
                return (TravelPlanInternal) Gd.a.a(new C0725a(6)).a(serializer(), str);
            } catch (Exception e) {
                N2.j.D(e, new StringBuilder("Error while parsing JSON result: "), TravelPlanInternal.TAG);
                return null;
            }
        }

        public final a serializer() {
            return TravelPlanInternal$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TravelPlanInternal(int i2, TravelInfoInternal travelInfoInternal, List list, a0 a0Var) {
        if (3 == (i2 & 3)) {
            this.travelInfo = travelInfoInternal;
            this.dailyItineraries = list;
            return;
        }
        Q.f(i2, 3, TravelPlanInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ TravelPlanInternal copy$default(TravelPlanInternal travelPlanInternal, TravelInfoInternal travelInfoInternal, List<DailyItineraryInternal> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            travelInfoInternal = travelPlanInternal.travelInfo;
        }
        if ((i2 & 2) != 0) {
            list = travelPlanInternal.dailyItineraries;
        }
        return travelPlanInternal.copy(travelInfoInternal, list);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(TravelPlanInternal travelPlanInternal, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        k kVar = (k) bVar;
        kVar.t(fVar, 0, TravelInfoInternal$$serializer.INSTANCE, travelPlanInternal.travelInfo);
        kVar.t(fVar, 1, aVarArr[1], travelPlanInternal.dailyItineraries);
    }

    public final TravelInfoInternal component1() {
        return this.travelInfo;
    }

    public final List<DailyItineraryInternal> component2() {
        return this.dailyItineraries;
    }

    public final TravelPlanInternal copy(TravelInfoInternal travelInfoInternal, List<DailyItineraryInternal> list) {
        j.e(travelInfoInternal, "travelInfo");
        j.e(list, "dailyItineraries");
        return new TravelPlanInternal(travelInfoInternal, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TravelPlanInternal)) {
            return false;
        }
        TravelPlanInternal travelPlanInternal = (TravelPlanInternal) obj;
        if (j.a(this.travelInfo, travelPlanInternal.travelInfo) && j.a(this.dailyItineraries, travelPlanInternal.dailyItineraries)) {
            return true;
        }
        return false;
    }

    public final List<DailyItineraryInternal> getDailyItineraries() {
        return this.dailyItineraries;
    }

    public final TravelInfoInternal getTravelInfo() {
        return this.travelInfo;
    }

    public int hashCode() {
        return this.dailyItineraries.hashCode() + (this.travelInfo.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TravelPlanInternal(travelInfo=");
        sb2.append(this.travelInfo);
        sb2.append(", dailyItineraries=");
        return C0212a.r(sb2, this.dailyItineraries, ')');
    }

    public TravelPlanInternal(TravelInfoInternal travelInfoInternal, List<DailyItineraryInternal> list) {
        j.e(travelInfoInternal, "travelInfo");
        j.e(list, "dailyItineraries");
        this.travelInfo = travelInfoInternal;
        this.dailyItineraries = list;
    }
}
