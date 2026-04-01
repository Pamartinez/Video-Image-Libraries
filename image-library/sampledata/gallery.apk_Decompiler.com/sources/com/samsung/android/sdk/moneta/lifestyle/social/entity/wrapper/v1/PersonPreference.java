package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel;
import gg.a;
import ig.f;
import jg.b;
import kg.C1136q;
import kg.C1141w;
import kg.H;
import kg.L;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\b\b\u0018\u0000 ]2\u00020\u0001:\u0002^]B£\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0013\u0010\u0014B\u0001\b\u0010\u0012\u0006\u0010\u0015\u001a\u00020\b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0013\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001aJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001aJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b!\u0010 J\u0012\u0010\"\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010$\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b$\u0010 J\u0012\u0010%\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b%\u0010&J\u0012\u0010'\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b'\u0010 J\u0012\u0010(\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b(\u0010 J\u0012\u0010)\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b)\u0010#J¬\u0001\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0004\b*\u0010+J\u0010\u0010-\u001a\u00020,HÖ\u0001¢\u0006\u0004\b-\u0010.J\u0010\u0010/\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b/\u00100J\u001a\u00103\u001a\u0002022\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b3\u00104J'\u0010=\u001a\u00020:2\u0006\u00105\u001a\u00020\u00002\u0006\u00107\u001a\u0002062\u0006\u00109\u001a\u000208H\u0001¢\u0006\u0004\b;\u0010<R\"\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010>\u0012\u0004\b@\u0010A\u001a\u0004\b?\u0010\u001aR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0004\u0010>\u0012\u0004\bC\u0010A\u001a\u0004\bB\u0010\u001aR\"\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010>\u0012\u0004\bE\u0010A\u001a\u0004\bD\u0010\u001aR\"\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010>\u0012\u0004\bG\u0010A\u001a\u0004\bF\u0010\u001aR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010>\u0012\u0004\bI\u0010A\u001a\u0004\bH\u0010\u001aR\"\u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\t\u0010J\u0012\u0004\bL\u0010A\u001a\u0004\bK\u0010 R\"\u0010\n\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\n\u0010J\u0012\u0004\bN\u0010A\u001a\u0004\bM\u0010 R\"\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\f\u0010O\u0012\u0004\bQ\u0010A\u001a\u0004\bP\u0010#R\"\u0010\r\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\r\u0010J\u0012\u0004\bS\u0010A\u001a\u0004\bR\u0010 R\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0004¢\u0006\u0012\n\u0004\b\u000f\u0010T\u0012\u0004\bV\u0010A\u001a\u0004\bU\u0010&R\"\u0010\u0010\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0010\u0010J\u0012\u0004\bX\u0010A\u001a\u0004\bW\u0010 R\"\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0011\u0010J\u0012\u0004\bZ\u0010A\u001a\u0004\bY\u0010 R\"\u0010\u0012\u001a\u0004\u0018\u00010\u000b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0012\u0010O\u0012\u0004\b\\\u0010A\u001a\u0004\b[\u0010#¨\u0006_"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;", "", "", "latestTimestamp", "startTimestamp", "endTimestamp", "eventTimestamp", "lastContactTimestamp", "", "daysOfContact", "numOfContact", "", "periodOfContact", "requestedNumOfDays", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;", "preferenceLevel", "numOfIncoming", "numOfOutgoing", "outgoingRate", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Integer;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;)V", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Integer;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Lkg/a0;)V", "component1", "()Ljava/lang/Long;", "component2", "component3", "component4", "component5", "component6", "()Ljava/lang/Integer;", "component7", "component8", "()Ljava/lang/Double;", "component9", "component10", "()Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;", "component11", "component12", "component13", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Integer;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;)Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;Ljg/b;Lig/f;)V", "write$Self", "Ljava/lang/Long;", "getLatestTimestamp", "getLatestTimestamp$annotations", "()V", "getStartTimestamp", "getStartTimestamp$annotations", "getEndTimestamp", "getEndTimestamp$annotations", "getEventTimestamp", "getEventTimestamp$annotations", "getLastContactTimestamp", "getLastContactTimestamp$annotations", "Ljava/lang/Integer;", "getDaysOfContact", "getDaysOfContact$annotations", "getNumOfContact", "getNumOfContact$annotations", "Ljava/lang/Double;", "getPeriodOfContact", "getPeriodOfContact$annotations", "getRequestedNumOfDays", "getRequestedNumOfDays$annotations", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;", "getPreferenceLevel", "getPreferenceLevel$annotations", "getNumOfIncoming", "getNumOfIncoming$annotations", "getNumOfOutgoing", "getNumOfOutgoing$annotations", "getOutgoingRate", "getOutgoingRate$annotations", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonPreference {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers;
    public static final Companion Companion = new Companion((e) null);
    private final Integer daysOfContact;
    private final Long endTimestamp;
    private final Long eventTimestamp;
    private final Long lastContactTimestamp;
    private final Long latestTimestamp;
    private final Integer numOfContact;
    private final Integer numOfIncoming;
    private final Integer numOfOutgoing;
    private final Double outgoingRate;
    private final Double periodOfContact;
    private final PreferenceLevel preferenceLevel;
    private final Integer requestedNumOfDays;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return PersonPreference$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    static {
        PreferenceLevel[] values = PreferenceLevel.values();
        j.e(values, StateHandler.VALUES);
        $childSerializers = new a[]{null, null, null, null, null, null, null, null, null, new C1141w("com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel", values), null, null, null};
    }

    public PersonPreference() {
        this((Long) null, (Long) null, (Long) null, (Long) null, (Long) null, (Integer) null, (Integer) null, (Double) null, (Integer) null, (PreferenceLevel) null, (Integer) null, (Integer) null, (Double) null, 8191, (e) null);
    }

    public static /* synthetic */ PersonPreference copy$default(PersonPreference personPreference, Long l, Long l8, Long l10, Long l11, Long l12, Integer num, Integer num2, Double d, Integer num3, PreferenceLevel preferenceLevel2, Integer num4, Integer num5, Double d2, int i2, Object obj) {
        int i7 = i2;
        if ((i7 & 1) != 0) {
            l = personPreference.latestTimestamp;
        }
        return personPreference.copy(l, (i7 & 2) != 0 ? personPreference.startTimestamp : l8, (i7 & 4) != 0 ? personPreference.endTimestamp : l10, (i7 & 8) != 0 ? personPreference.eventTimestamp : l11, (i7 & 16) != 0 ? personPreference.lastContactTimestamp : l12, (i7 & 32) != 0 ? personPreference.daysOfContact : num, (i7 & 64) != 0 ? personPreference.numOfContact : num2, (i7 & 128) != 0 ? personPreference.periodOfContact : d, (i7 & 256) != 0 ? personPreference.requestedNumOfDays : num3, (i7 & 512) != 0 ? personPreference.preferenceLevel : preferenceLevel2, (i7 & 1024) != 0 ? personPreference.numOfIncoming : num4, (i7 & 2048) != 0 ? personPreference.numOfOutgoing : num5, (i7 & 4096) != 0 ? personPreference.outgoingRate : d2);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(PersonPreference personPreference, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        if (bVar.d(fVar) || personPreference.latestTimestamp != null) {
            bVar.c(fVar, 0, L.f4673a, personPreference.latestTimestamp);
        }
        if (bVar.d(fVar) || personPreference.startTimestamp != null) {
            bVar.c(fVar, 1, L.f4673a, personPreference.startTimestamp);
        }
        if (bVar.d(fVar) || personPreference.endTimestamp != null) {
            bVar.c(fVar, 2, L.f4673a, personPreference.endTimestamp);
        }
        if (bVar.d(fVar) || personPreference.eventTimestamp != null) {
            bVar.c(fVar, 3, L.f4673a, personPreference.eventTimestamp);
        }
        if (bVar.d(fVar) || personPreference.lastContactTimestamp != null) {
            bVar.c(fVar, 4, L.f4673a, personPreference.lastContactTimestamp);
        }
        if (bVar.d(fVar) || personPreference.daysOfContact != null) {
            bVar.c(fVar, 5, H.f4669a, personPreference.daysOfContact);
        }
        if (bVar.d(fVar) || personPreference.numOfContact != null) {
            bVar.c(fVar, 6, H.f4669a, personPreference.numOfContact);
        }
        if (bVar.d(fVar) || personPreference.periodOfContact != null) {
            bVar.c(fVar, 7, C1136q.f4714a, personPreference.periodOfContact);
        }
        if (bVar.d(fVar) || personPreference.requestedNumOfDays != null) {
            bVar.c(fVar, 8, H.f4669a, personPreference.requestedNumOfDays);
        }
        if (bVar.d(fVar) || personPreference.preferenceLevel != null) {
            bVar.c(fVar, 9, aVarArr[9], personPreference.preferenceLevel);
        }
        if (bVar.d(fVar) || personPreference.numOfIncoming != null) {
            bVar.c(fVar, 10, H.f4669a, personPreference.numOfIncoming);
        }
        if (bVar.d(fVar) || personPreference.numOfOutgoing != null) {
            bVar.c(fVar, 11, H.f4669a, personPreference.numOfOutgoing);
        }
        if (bVar.d(fVar) || personPreference.outgoingRate != null) {
            bVar.c(fVar, 12, C1136q.f4714a, personPreference.outgoingRate);
        }
    }

    public final Long component1() {
        return this.latestTimestamp;
    }

    public final PreferenceLevel component10() {
        return this.preferenceLevel;
    }

    public final Integer component11() {
        return this.numOfIncoming;
    }

    public final Integer component12() {
        return this.numOfOutgoing;
    }

    public final Double component13() {
        return this.outgoingRate;
    }

    public final Long component2() {
        return this.startTimestamp;
    }

    public final Long component3() {
        return this.endTimestamp;
    }

    public final Long component4() {
        return this.eventTimestamp;
    }

    public final Long component5() {
        return this.lastContactTimestamp;
    }

    public final Integer component6() {
        return this.daysOfContact;
    }

    public final Integer component7() {
        return this.numOfContact;
    }

    public final Double component8() {
        return this.periodOfContact;
    }

    public final Integer component9() {
        return this.requestedNumOfDays;
    }

    public final PersonPreference copy(Long l, Long l8, Long l10, Long l11, Long l12, Integer num, Integer num2, Double d, Integer num3, PreferenceLevel preferenceLevel2, Integer num4, Integer num5, Double d2) {
        return new PersonPreference(l, l8, l10, l11, l12, num, num2, d, num3, preferenceLevel2, num4, num5, d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonPreference)) {
            return false;
        }
        PersonPreference personPreference = (PersonPreference) obj;
        if (j.a(this.latestTimestamp, personPreference.latestTimestamp) && j.a(this.startTimestamp, personPreference.startTimestamp) && j.a(this.endTimestamp, personPreference.endTimestamp) && j.a(this.eventTimestamp, personPreference.eventTimestamp) && j.a(this.lastContactTimestamp, personPreference.lastContactTimestamp) && j.a(this.daysOfContact, personPreference.daysOfContact) && j.a(this.numOfContact, personPreference.numOfContact) && j.a(this.periodOfContact, personPreference.periodOfContact) && j.a(this.requestedNumOfDays, personPreference.requestedNumOfDays) && this.preferenceLevel == personPreference.preferenceLevel && j.a(this.numOfIncoming, personPreference.numOfIncoming) && j.a(this.numOfOutgoing, personPreference.numOfOutgoing) && j.a(this.outgoingRate, personPreference.outgoingRate)) {
            return true;
        }
        return false;
    }

    public final Integer getDaysOfContact() {
        return this.daysOfContact;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final Long getEventTimestamp() {
        return this.eventTimestamp;
    }

    public final Long getLastContactTimestamp() {
        return this.lastContactTimestamp;
    }

    public final Long getLatestTimestamp() {
        return this.latestTimestamp;
    }

    public final Integer getNumOfContact() {
        return this.numOfContact;
    }

    public final Integer getNumOfIncoming() {
        return this.numOfIncoming;
    }

    public final Integer getNumOfOutgoing() {
        return this.numOfOutgoing;
    }

    public final Double getOutgoingRate() {
        return this.outgoingRate;
    }

    public final Double getPeriodOfContact() {
        return this.periodOfContact;
    }

    public final PreferenceLevel getPreferenceLevel() {
        return this.preferenceLevel;
    }

    public final Integer getRequestedNumOfDays() {
        return this.requestedNumOfDays;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        Long l = this.latestTimestamp;
        int i19 = 0;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        int i20 = i2 * 31;
        Long l8 = this.startTimestamp;
        if (l8 == null) {
            i7 = 0;
        } else {
            i7 = l8.hashCode();
        }
        int i21 = (i20 + i7) * 31;
        Long l10 = this.endTimestamp;
        if (l10 == null) {
            i8 = 0;
        } else {
            i8 = l10.hashCode();
        }
        int i22 = (i21 + i8) * 31;
        Long l11 = this.eventTimestamp;
        if (l11 == null) {
            i10 = 0;
        } else {
            i10 = l11.hashCode();
        }
        int i23 = (i22 + i10) * 31;
        Long l12 = this.lastContactTimestamp;
        if (l12 == null) {
            i11 = 0;
        } else {
            i11 = l12.hashCode();
        }
        int i24 = (i23 + i11) * 31;
        Integer num = this.daysOfContact;
        if (num == null) {
            i12 = 0;
        } else {
            i12 = num.hashCode();
        }
        int i25 = (i24 + i12) * 31;
        Integer num2 = this.numOfContact;
        if (num2 == null) {
            i13 = 0;
        } else {
            i13 = num2.hashCode();
        }
        int i26 = (i25 + i13) * 31;
        Double d = this.periodOfContact;
        if (d == null) {
            i14 = 0;
        } else {
            i14 = d.hashCode();
        }
        int i27 = (i26 + i14) * 31;
        Integer num3 = this.requestedNumOfDays;
        if (num3 == null) {
            i15 = 0;
        } else {
            i15 = num3.hashCode();
        }
        int i28 = (i27 + i15) * 31;
        PreferenceLevel preferenceLevel2 = this.preferenceLevel;
        if (preferenceLevel2 == null) {
            i16 = 0;
        } else {
            i16 = preferenceLevel2.hashCode();
        }
        int i29 = (i28 + i16) * 31;
        Integer num4 = this.numOfIncoming;
        if (num4 == null) {
            i17 = 0;
        } else {
            i17 = num4.hashCode();
        }
        int i30 = (i29 + i17) * 31;
        Integer num5 = this.numOfOutgoing;
        if (num5 == null) {
            i18 = 0;
        } else {
            i18 = num5.hashCode();
        }
        int i31 = (i30 + i18) * 31;
        Double d2 = this.outgoingRate;
        if (d2 != null) {
            i19 = d2.hashCode();
        }
        return i31 + i19;
    }

    public String toString() {
        return "PersonPreference(latestTimestamp=" + this.latestTimestamp + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ", eventTimestamp=" + this.eventTimestamp + ", lastContactTimestamp=" + this.lastContactTimestamp + ", daysOfContact=" + this.daysOfContact + ", numOfContact=" + this.numOfContact + ", periodOfContact=" + this.periodOfContact + ", requestedNumOfDays=" + this.requestedNumOfDays + ", preferenceLevel=" + this.preferenceLevel + ", numOfIncoming=" + this.numOfIncoming + ", numOfOutgoing=" + this.numOfOutgoing + ", outgoingRate=" + this.outgoingRate + ')';
    }

    public /* synthetic */ PersonPreference(int i2, Long l, Long l8, Long l10, Long l11, Long l12, Integer num, Integer num2, Double d, Integer num3, PreferenceLevel preferenceLevel2, Integer num4, Integer num5, Double d2, a0 a0Var) {
        if ((i2 & 1) == 0) {
            this.latestTimestamp = null;
        } else {
            this.latestTimestamp = l;
        }
        if ((i2 & 2) == 0) {
            this.startTimestamp = null;
        } else {
            this.startTimestamp = l8;
        }
        if ((i2 & 4) == 0) {
            this.endTimestamp = null;
        } else {
            this.endTimestamp = l10;
        }
        if ((i2 & 8) == 0) {
            this.eventTimestamp = null;
        } else {
            this.eventTimestamp = l11;
        }
        if ((i2 & 16) == 0) {
            this.lastContactTimestamp = null;
        } else {
            this.lastContactTimestamp = l12;
        }
        if ((i2 & 32) == 0) {
            this.daysOfContact = null;
        } else {
            this.daysOfContact = num;
        }
        if ((i2 & 64) == 0) {
            this.numOfContact = null;
        } else {
            this.numOfContact = num2;
        }
        if ((i2 & 128) == 0) {
            this.periodOfContact = null;
        } else {
            this.periodOfContact = d;
        }
        if ((i2 & 256) == 0) {
            this.requestedNumOfDays = null;
        } else {
            this.requestedNumOfDays = num3;
        }
        if ((i2 & 512) == 0) {
            this.preferenceLevel = null;
        } else {
            this.preferenceLevel = preferenceLevel2;
        }
        if ((i2 & 1024) == 0) {
            this.numOfIncoming = null;
        } else {
            this.numOfIncoming = num4;
        }
        if ((i2 & 2048) == 0) {
            this.numOfOutgoing = null;
        } else {
            this.numOfOutgoing = num5;
        }
        if ((i2 & 4096) == 0) {
            this.outgoingRate = null;
        } else {
            this.outgoingRate = d2;
        }
    }

    public PersonPreference(Long l, Long l8, Long l10, Long l11, Long l12, Integer num, Integer num2, Double d, Integer num3, PreferenceLevel preferenceLevel2, Integer num4, Integer num5, Double d2) {
        this.latestTimestamp = l;
        this.startTimestamp = l8;
        this.endTimestamp = l10;
        this.eventTimestamp = l11;
        this.lastContactTimestamp = l12;
        this.daysOfContact = num;
        this.numOfContact = num2;
        this.periodOfContact = d;
        this.requestedNumOfDays = num3;
        this.preferenceLevel = preferenceLevel2;
        this.numOfIncoming = num4;
        this.numOfOutgoing = num5;
        this.outgoingRate = d2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PersonPreference(java.lang.Long r14, java.lang.Long r15, java.lang.Long r16, java.lang.Long r17, java.lang.Long r18, java.lang.Integer r19, java.lang.Integer r20, java.lang.Double r21, java.lang.Integer r22, com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel r23, java.lang.Integer r24, java.lang.Integer r25, java.lang.Double r26, int r27, kotlin.jvm.internal.e r28) {
        /*
            r13 = this;
            r0 = r27
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0008
            r14 = r2
        L_0x0008:
            r1 = r0 & 2
            if (r1 == 0) goto L_0x000e
            r1 = r2
            goto L_0x000f
        L_0x000e:
            r1 = r15
        L_0x000f:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x0015
            r3 = r2
            goto L_0x0017
        L_0x0015:
            r3 = r16
        L_0x0017:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x001d
            r4 = r2
            goto L_0x001f
        L_0x001d:
            r4 = r17
        L_0x001f:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x0025
            r5 = r2
            goto L_0x0027
        L_0x0025:
            r5 = r18
        L_0x0027:
            r6 = r0 & 32
            if (r6 == 0) goto L_0x002d
            r6 = r2
            goto L_0x002f
        L_0x002d:
            r6 = r19
        L_0x002f:
            r7 = r0 & 64
            if (r7 == 0) goto L_0x0035
            r7 = r2
            goto L_0x0037
        L_0x0035:
            r7 = r20
        L_0x0037:
            r8 = r0 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x003d
            r8 = r2
            goto L_0x003f
        L_0x003d:
            r8 = r21
        L_0x003f:
            r9 = r0 & 256(0x100, float:3.59E-43)
            if (r9 == 0) goto L_0x0045
            r9 = r2
            goto L_0x0047
        L_0x0045:
            r9 = r22
        L_0x0047:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x004d
            r10 = r2
            goto L_0x004f
        L_0x004d:
            r10 = r23
        L_0x004f:
            r11 = r0 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x0055
            r11 = r2
            goto L_0x0057
        L_0x0055:
            r11 = r24
        L_0x0057:
            r12 = r0 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x005d
            r12 = r2
            goto L_0x005f
        L_0x005d:
            r12 = r25
        L_0x005f:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x007e
            r27 = r2
        L_0x0065:
            r15 = r14
            r16 = r1
            r17 = r3
            r18 = r4
            r19 = r5
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r9
            r24 = r10
            r25 = r11
            r26 = r12
            r14 = r13
            goto L_0x0081
        L_0x007e:
            r27 = r26
            goto L_0x0065
        L_0x0081:
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference.<init>(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.Double, java.lang.Integer, com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel, java.lang.Integer, java.lang.Integer, java.lang.Double, int, kotlin.jvm.internal.e):void");
    }

    public static /* synthetic */ void getDaysOfContact$annotations() {
    }

    public static /* synthetic */ void getEndTimestamp$annotations() {
    }

    public static /* synthetic */ void getEventTimestamp$annotations() {
    }

    public static /* synthetic */ void getLastContactTimestamp$annotations() {
    }

    public static /* synthetic */ void getLatestTimestamp$annotations() {
    }

    public static /* synthetic */ void getNumOfContact$annotations() {
    }

    public static /* synthetic */ void getNumOfIncoming$annotations() {
    }

    public static /* synthetic */ void getNumOfOutgoing$annotations() {
    }

    public static /* synthetic */ void getOutgoingRate$annotations() {
    }

    public static /* synthetic */ void getPeriodOfContact$annotations() {
    }

    public static /* synthetic */ void getPreferenceLevel$annotations() {
    }

    public static /* synthetic */ void getRequestedNumOfDays$annotations() {
    }

    public static /* synthetic */ void getStartTimestamp$annotations() {
    }
}
