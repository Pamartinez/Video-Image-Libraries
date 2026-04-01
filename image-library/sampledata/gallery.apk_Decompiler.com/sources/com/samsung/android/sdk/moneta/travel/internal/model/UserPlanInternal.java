package com.samsung.android.sdk.moneta.travel.internal.model;

import com.samsung.android.sdk.mobileservice.profile.Privacy;
import com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer;
import gg.a;
import i.C0212a;
import ig.f;
import java.time.ZonedDateTime;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import og.k;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u000f\b\b\u0018\u0000 42\u00020\u0001:\u000254B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bBI\b\u0010\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u0010J'\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J>\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b#\u0010\u001eJ\u0010\u0010$\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010(\u001a\u00020'2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b(\u0010)R \u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010*\u0012\u0004\b,\u0010-\u001a\u0004\b+\u0010\u001bR \u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0004\u0010*\u0012\u0004\b/\u0010-\u001a\u0004\b.\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u00100\u001a\u0004\b1\u0010\u001eR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u00102\u001a\u0004\b3\u0010 ¨\u00066"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal;", "", "Ljava/time/ZonedDateTime;", "startTime", "endTime", "", "title", "", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "places", "<init>", "(Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/util/List;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/util/List;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/time/ZonedDateTime;", "component2", "component3", "()Ljava/lang/String;", "component4", "()Ljava/util/List;", "copy", "(Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/time/ZonedDateTime;", "getStartTime", "getStartTime$annotations", "()V", "getEndTime", "getEndTime$annotations", "Ljava/lang/String;", "getTitle", "Ljava/util/List;", "getPlaces", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UserPlanInternal {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, null, null, new C1122c(PlaceInternal$$serializer.INSTANCE)};
    public static final Companion Companion = new Companion((e) null);
    private final ZonedDateTime endTime;
    private final List<PlaceInternal> places;
    private final ZonedDateTime startTime;
    private final String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/UserPlanInternal;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return UserPlanInternal$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ UserPlanInternal(int i2, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List list, a0 a0Var) {
        if (15 == (i2 & 15)) {
            this.startTime = zonedDateTime;
            this.endTime = zonedDateTime2;
            this.title = str;
            this.places = list;
            return;
        }
        Q.f(i2, 15, UserPlanInternal$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ UserPlanInternal copy$default(UserPlanInternal userPlanInternal, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List<PlaceInternal> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            zonedDateTime = userPlanInternal.startTime;
        }
        if ((i2 & 2) != 0) {
            zonedDateTime2 = userPlanInternal.endTime;
        }
        if ((i2 & 4) != 0) {
            str = userPlanInternal.title;
        }
        if ((i2 & 8) != 0) {
            list = userPlanInternal.places;
        }
        return userPlanInternal.copy(zonedDateTime, zonedDateTime2, str, list);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(UserPlanInternal userPlanInternal, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        k kVar = (k) bVar;
        kVar.t(fVar, 0, zonedDateTimeSerializer, userPlanInternal.startTime);
        kVar.t(fVar, 1, zonedDateTimeSerializer, userPlanInternal.endTime);
        kVar.u(fVar, 2, userPlanInternal.title);
        kVar.t(fVar, 3, aVarArr[3], userPlanInternal.places);
    }

    public final ZonedDateTime component1() {
        return this.startTime;
    }

    public final ZonedDateTime component2() {
        return this.endTime;
    }

    public final String component3() {
        return this.title;
    }

    public final List<PlaceInternal> component4() {
        return this.places;
    }

    public final UserPlanInternal copy(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List<PlaceInternal> list) {
        j.e(zonedDateTime, "startTime");
        j.e(zonedDateTime2, "endTime");
        j.e(str, "title");
        j.e(list, Privacy.KEY_PLACES);
        return new UserPlanInternal(zonedDateTime, zonedDateTime2, str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserPlanInternal)) {
            return false;
        }
        UserPlanInternal userPlanInternal = (UserPlanInternal) obj;
        if (j.a(this.startTime, userPlanInternal.startTime) && j.a(this.endTime, userPlanInternal.endTime) && j.a(this.title, userPlanInternal.title) && j.a(this.places, userPlanInternal.places)) {
            return true;
        }
        return false;
    }

    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    public final List<PlaceInternal> getPlaces() {
        return this.places;
    }

    public final ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.places.hashCode() + C0212a.d((this.endTime.hashCode() + (this.startTime.hashCode() * 31)) * 31, 31, this.title);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("UserPlanInternal(startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", title=");
        sb2.append(this.title);
        sb2.append(", places=");
        return C0212a.r(sb2, this.places, ')');
    }

    public UserPlanInternal(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List<PlaceInternal> list) {
        j.e(zonedDateTime, "startTime");
        j.e(zonedDateTime2, "endTime");
        j.e(str, "title");
        j.e(list, Privacy.KEY_PLACES);
        this.startTime = zonedDateTime;
        this.endTime = zonedDateTime2;
        this.title = str;
        this.places = list;
    }

    public static /* synthetic */ void getEndTime$annotations() {
    }

    public static /* synthetic */ void getStartTime$annotations() {
    }
}
