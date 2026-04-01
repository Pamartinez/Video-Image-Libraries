package com.samsung.android.sdk.moneta.memory.entity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.activity.Activity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.EngramBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001dJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001bJ\u0010\u0010 \u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b \u0010!JX\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b$\u0010\u0019J\u0010\u0010%\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b%\u0010\u0017J\u001a\u0010(\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010&HÖ\u0003¢\u0006\u0004\b(\u0010)R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010*\u001a\u0004\b+\u0010\u0019R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010,\u001a\u0004\b-\u0010\u001bR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010.\u001a\u0004\b/\u0010\u001dR\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b0\u0010\u001dR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010,\u001a\u0004\b1\u0010\u001bR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u00102\u001a\u0004\b\f\u0010!¨\u00063"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "Landroid/os/Parcelable;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "activities", "", "startTimestamp", "endTimestamp", "specialMoments", "", "isComplete", "<init>", "(Ljava/lang/String;Ljava/util/List;JJLjava/util/List;Z)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()J", "component4", "component5", "component6", "()Z", "copy", "(Ljava/lang/String;Ljava/util/List;JJLjava/util/List;Z)Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getActivities", "J", "getStartTimestamp", "getEndTimestamp", "getSpecialMoments", "Z", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Engram implements Parcelable {
    public static final Parcelable.Creator<Engram> CREATOR = new Creator();
    private final List<Activity> activities;
    private final long endTimestamp;
    private final String id;
    private final boolean isComplete;
    private final List<String> specialMoments;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Engram> {
        public final Engram createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(Engram.class, parcel, arrayList, i2, 1);
            }
            return new Engram(readString, arrayList, parcel.readLong(), parcel.readLong(), parcel.createStringArrayList(), parcel.readInt() != 0);
        }

        public final Engram[] newArray(int i2) {
            return new Engram[i2];
        }
    }

    public Engram(String str, List<? extends Activity> list, long j2, long j3, List<String> list2, boolean z) {
        j.e(str, "id");
        j.e(list, EngramBundleWrapper.BUNDLE_KEY_ACTIVITIES);
        j.e(list2, EngramBundleWrapper.BUNDLE_KEY_SPECIAL_MOMENTS);
        this.id = str;
        this.activities = list;
        this.startTimestamp = j2;
        this.endTimestamp = j3;
        this.specialMoments = list2;
        this.isComplete = z;
    }

    public static /* synthetic */ Engram copy$default(Engram engram, String str, List<Activity> list, long j2, long j3, List<String> list2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = engram.id;
        }
        if ((i2 & 2) != 0) {
            list = engram.activities;
        }
        if ((i2 & 4) != 0) {
            j2 = engram.startTimestamp;
        }
        if ((i2 & 8) != 0) {
            j3 = engram.endTimestamp;
        }
        if ((i2 & 16) != 0) {
            list2 = engram.specialMoments;
        }
        if ((i2 & 32) != 0) {
            z = engram.isComplete;
        }
        long j8 = j3;
        long j10 = j2;
        List<Activity> list3 = list;
        return engram.copy(str, list3, j10, j8, list2, z);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Activity> component2() {
        return this.activities;
    }

    public final long component3() {
        return this.startTimestamp;
    }

    public final long component4() {
        return this.endTimestamp;
    }

    public final List<String> component5() {
        return this.specialMoments;
    }

    public final boolean component6() {
        return this.isComplete;
    }

    public final Engram copy(String str, List<? extends Activity> list, long j2, long j3, List<String> list2, boolean z) {
        j.e(str, "id");
        j.e(list, EngramBundleWrapper.BUNDLE_KEY_ACTIVITIES);
        List<String> list3 = list2;
        j.e(list3, EngramBundleWrapper.BUNDLE_KEY_SPECIAL_MOMENTS);
        return new Engram(str, list, j2, j3, list3, z);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Engram)) {
            return false;
        }
        Engram engram = (Engram) obj;
        if (j.a(this.id, engram.id) && j.a(this.activities, engram.activities) && this.startTimestamp == engram.startTimestamp && this.endTimestamp == engram.endTimestamp && j.a(this.specialMoments, engram.specialMoments) && this.isComplete == engram.isComplete) {
            return true;
        }
        return false;
    }

    public final List<Activity> getActivities() {
        return this.activities;
    }

    public final long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getId() {
        return this.id;
    }

    public final List<String> getSpecialMoments() {
        return this.specialMoments;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isComplete) + C0212a.f(this.specialMoments, C0212a.c(C0212a.c(C0212a.f(this.activities, this.id.hashCode() * 31, 31), 31, this.startTimestamp), 31, this.endTimestamp), 31);
    }

    public final boolean isComplete() {
        return this.isComplete;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Engram(id=");
        sb2.append(this.id);
        sb2.append(", activities=");
        sb2.append(this.activities);
        sb2.append(", startTimestamp=");
        sb2.append(this.startTimestamp);
        sb2.append(", endTimestamp=");
        sb2.append(this.endTimestamp);
        sb2.append(", specialMoments=");
        sb2.append(this.specialMoments);
        sb2.append(", isComplete=");
        return C0086a.n(sb2, this.isComplete, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.activities);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeLong(this.startTimestamp);
        parcel.writeLong(this.endTimestamp);
        parcel.writeStringList(this.specialMoments);
        parcel.writeInt(this.isComplete ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Engram(String str, List list, long j2, long j3, List list2, boolean z, int i2, e eVar) {
        this(str, list, j2, j3, (i2 & 16) != 0 ? C1202t.d : list2, (i2 & 32) != 0 ? true : z);
    }
}
