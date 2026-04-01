package com.samsung.android.sdk.moneta.travel.internal.model;

import Ad.C0721b;
import He.C0748d;
import L1.d;
import gg.a;
import java.lang.annotation.Annotation;
import java.time.ZonedDateTime;
import jg.b;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.f;
import me.h;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 ,2\u00020\u0001:\u0001,B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003B\u001b\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0002\u0010\bJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0015\u001a\u00020\u00118&X§\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0018\u001a\u00020\u00118&X§\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0016\u0010\u0013R\u0014\u0010\u001c\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0014\u0010\"\u001a\u00020\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010$\u001a\u00020\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0014\u0010&\u001a\u00020\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010!R\u0014\u0010(\u001a\u00020\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010!R\u0014\u0010*\u001a\u00020)8&X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\u0001\u0002-.¨\u0006/"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;", "", "<init>", "()V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;Ljg/b;Lig/f;)V", "Ljava/time/ZonedDateTime;", "getDepartureTime", "()Ljava/time/ZonedDateTime;", "getDepartureTime$annotations", "departureTime", "getArrivalTime", "getArrivalTime$annotations", "arrivalTime", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "getDeparture", "()Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "departure", "getArrival", "arrival", "", "getTransportationNumber", "()Ljava/lang/String;", "transportationNumber", "getSeatInfo", "seatInfo", "getType", "type", "getSourcePackage", "sourcePackage", "", "isAugmented", "()Z", "Companion", "Lcom/samsung/android/sdk/moneta/travel/internal/model/FlightInternal;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationInternal;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TransportationBaseInternal {
    /* access modifiers changed from: private */
    public static final f $cachedSerializer$delegate = d.p(h.PUBLICATION, new C0721b(4));
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TransportationBaseInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        private final /* synthetic */ a get$cachedSerializer() {
            return (a) TransportationBaseInternal.$cachedSerializer$delegate.getValue();
        }

        public final a serializer() {
            return get$cachedSerializer();
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ TransportationBaseInternal(e eVar) {
        this();
    }

    /* access modifiers changed from: private */
    public static final a _init_$_anonymous_() {
        w wVar = v.f4727a;
        return new gg.e(wVar.b(TransportationBaseInternal.class), new C0748d[]{wVar.b(FlightInternal.class), wVar.b(TransportationInternal.class)}, new a[]{FlightInternal$$serializer.INSTANCE, TransportationInternal$$serializer.INSTANCE}, new Annotation[0]);
    }

    public abstract PlaceInternal getArrival();

    public abstract ZonedDateTime getArrivalTime();

    public abstract PlaceInternal getDeparture();

    public abstract ZonedDateTime getDepartureTime();

    public abstract String getSeatInfo();

    public abstract String getSourcePackage();

    public abstract String getTransportationNumber();

    public abstract String getType();

    public abstract boolean isAugmented();

    private TransportationBaseInternal() {
    }

    public /* synthetic */ TransportationBaseInternal(int i2, a0 a0Var) {
    }

    public static /* synthetic */ void getArrivalTime$annotations() {
    }

    public static /* synthetic */ void getDepartureTime$annotations() {
    }

    public static final /* synthetic */ void write$Self(TransportationBaseInternal transportationBaseInternal, b bVar, ig.f fVar) {
    }
}
