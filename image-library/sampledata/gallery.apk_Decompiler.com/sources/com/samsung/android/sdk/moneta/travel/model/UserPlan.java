package com.samsung.android.sdk.moneta.travel.model;

import com.samsung.android.sdk.mobileservice.profile.Privacy;
import i.C0212a;
import java.time.ZonedDateTime;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/model/UserPlan;", "", "startTime", "Ljava/time/ZonedDateTime;", "endTime", "title", "", "places", "", "Lcom/samsung/android/sdk/moneta/travel/model/Place;", "<init>", "(Ljava/time/ZonedDateTime;Ljava/time/ZonedDateTime;Ljava/lang/String;Ljava/util/List;)V", "getStartTime", "()Ljava/time/ZonedDateTime;", "getEndTime", "getTitle", "()Ljava/lang/String;", "getPlaces", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UserPlan {
    private final ZonedDateTime endTime;
    private final List<Place> places;
    private final ZonedDateTime startTime;
    private final String title;

    public UserPlan(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List<Place> list) {
        j.e(zonedDateTime, "startTime");
        j.e(zonedDateTime2, "endTime");
        j.e(str, "title");
        j.e(list, Privacy.KEY_PLACES);
        this.startTime = zonedDateTime;
        this.endTime = zonedDateTime2;
        this.title = str;
        this.places = list;
    }

    public static /* synthetic */ UserPlan copy$default(UserPlan userPlan, ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List<Place> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            zonedDateTime = userPlan.startTime;
        }
        if ((i2 & 2) != 0) {
            zonedDateTime2 = userPlan.endTime;
        }
        if ((i2 & 4) != 0) {
            str = userPlan.title;
        }
        if ((i2 & 8) != 0) {
            list = userPlan.places;
        }
        return userPlan.copy(zonedDateTime, zonedDateTime2, str, list);
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

    public final List<Place> component4() {
        return this.places;
    }

    public final UserPlan copy(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, String str, List<Place> list) {
        j.e(zonedDateTime, "startTime");
        j.e(zonedDateTime2, "endTime");
        j.e(str, "title");
        j.e(list, Privacy.KEY_PLACES);
        return new UserPlan(zonedDateTime, zonedDateTime2, str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserPlan)) {
            return false;
        }
        UserPlan userPlan = (UserPlan) obj;
        if (j.a(this.startTime, userPlan.startTime) && j.a(this.endTime, userPlan.endTime) && j.a(this.title, userPlan.title) && j.a(this.places, userPlan.places)) {
            return true;
        }
        return false;
    }

    public final ZonedDateTime getEndTime() {
        return this.endTime;
    }

    public final List<Place> getPlaces() {
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
        StringBuilder sb2 = new StringBuilder("UserPlan(startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", title=");
        sb2.append(this.title);
        sb2.append(", places=");
        return C0212a.r(sb2, this.places, ')');
    }
}
