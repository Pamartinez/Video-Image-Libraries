package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.content.MobileApplication;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ@\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b \u0010\u0017J\u0010\u0010!\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b!\u0010\u0015J\u001a\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\"HÖ\u0003¢\u0006\u0004\b%\u0010&R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010\u0017R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b*\u0010\u0019R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010+\u001a\u0004\b,\u0010\u001bR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010-\u001a\u0004\b.\u0010\u001d¨\u0006/"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/SearchingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "", "timestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "mobileApplication", "<init>", "(Ljava/lang/String;Ljava/util/List;JLcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()J", "component4", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "copy", "(Ljava/lang/String;Ljava/util/List;JLcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/SearchingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "J", "getTimestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "getMobileApplication", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchingActivity extends Activity {
    public static final Parcelable.Creator<SearchingActivity> CREATOR = new Creator();
    private final List<Content> contents;
    private final String id;
    private final MobileApplication mobileApplication;
    private final long timestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<SearchingActivity> {
        public final SearchingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(SearchingActivity.class, parcel, arrayList, i2, 1);
            }
            return new SearchingActivity(readString, arrayList, parcel.readLong(), parcel.readInt() == 0 ? null : MobileApplication.CREATOR.createFromParcel(parcel));
        }

        public final SearchingActivity[] newArray(int i2) {
            return new SearchingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SearchingActivity(String str, List<? extends Content> list, long j2, MobileApplication mobileApplication2) {
        super(ActivityType.Searching);
        j.e(str, "id");
        j.e(list, "contents");
        this.id = str;
        this.contents = list;
        this.timestamp = j2;
        this.mobileApplication = mobileApplication2;
    }

    public static /* synthetic */ SearchingActivity copy$default(SearchingActivity searchingActivity, String str, List<Content> list, long j2, MobileApplication mobileApplication2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = searchingActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = searchingActivity.contents;
        }
        if ((i2 & 4) != 0) {
            j2 = searchingActivity.timestamp;
        }
        if ((i2 & 8) != 0) {
            mobileApplication2 = searchingActivity.mobileApplication;
        }
        MobileApplication mobileApplication3 = mobileApplication2;
        List<Content> list2 = list;
        return searchingActivity.copy(str, list2, j2, mobileApplication3);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final long component3() {
        return this.timestamp;
    }

    public final MobileApplication component4() {
        return this.mobileApplication;
    }

    public final SearchingActivity copy(String str, List<? extends Content> list, long j2, MobileApplication mobileApplication2) {
        j.e(str, "id");
        j.e(list, "contents");
        return new SearchingActivity(str, list, j2, mobileApplication2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchingActivity)) {
            return false;
        }
        SearchingActivity searchingActivity = (SearchingActivity) obj;
        if (j.a(this.id, searchingActivity.id) && j.a(this.contents, searchingActivity.contents) && this.timestamp == searchingActivity.timestamp && j.a(this.mobileApplication, searchingActivity.mobileApplication)) {
            return true;
        }
        return false;
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public String getId() {
        return this.id;
    }

    public final MobileApplication getMobileApplication() {
        return this.mobileApplication;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i2;
        int c5 = C0212a.c(C0212a.f(this.contents, this.id.hashCode() * 31, 31), 31, this.timestamp);
        MobileApplication mobileApplication2 = this.mobileApplication;
        if (mobileApplication2 == null) {
            i2 = 0;
        } else {
            i2 = mobileApplication2.hashCode();
        }
        return c5 + i2;
    }

    public String toString() {
        return "SearchingActivity(id=" + this.id + ", contents=" + this.contents + ", timestamp=" + this.timestamp + ", mobileApplication=" + this.mobileApplication + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeLong(this.timestamp);
        MobileApplication mobileApplication2 = this.mobileApplication;
        if (mobileApplication2 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        mobileApplication2.writeToParcel(parcel, i2);
    }
}
