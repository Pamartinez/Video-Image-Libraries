package com.samsung.android.sdk.moneta.event.entity.event;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J$\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0012J\u0010\u0010\u0016\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u000fJ\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\u0012¨\u0006 "}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;", "Landroid/os/Parcelable;", "", "categoryId", "", "categoryName", "<init>", "(ILjava/lang/String;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Ljava/lang/String;", "copy", "(ILjava/lang/String;)Lcom/samsung/android/sdk/moneta/event/entity/event/EventCategory;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCategoryId", "Ljava/lang/String;", "getCategoryName", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EventCategory implements Parcelable {
    public static final Parcelable.Creator<EventCategory> CREATOR = new Creator();
    private final /* synthetic */ int categoryId;
    private final /* synthetic */ String categoryName;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EventCategory> {
        public final EventCategory createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EventCategory(parcel.readInt(), parcel.readString());
        }

        public final EventCategory[] newArray(int i2) {
            return new EventCategory[i2];
        }
    }

    public EventCategory(int i2, String str) {
        j.e(str, "categoryName");
        this.categoryId = i2;
        this.categoryName = str;
    }

    public static /* synthetic */ EventCategory copy$default(EventCategory eventCategory, int i2, String str, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = eventCategory.categoryId;
        }
        if ((i7 & 2) != 0) {
            str = eventCategory.categoryName;
        }
        return eventCategory.copy(i2, str);
    }

    public final int component1() {
        return this.categoryId;
    }

    public final String component2() {
        return this.categoryName;
    }

    public final EventCategory copy(int i2, String str) {
        j.e(str, "categoryName");
        return new EventCategory(i2, str);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EventCategory)) {
            return false;
        }
        EventCategory eventCategory = (EventCategory) obj;
        if (this.categoryId == eventCategory.categoryId && j.a(this.categoryName, eventCategory.categoryName)) {
            return true;
        }
        return false;
    }

    public final int getCategoryId() {
        return this.categoryId;
    }

    public final String getCategoryName() {
        return this.categoryName;
    }

    public int hashCode() {
        return this.categoryName.hashCode() + (Integer.hashCode(this.categoryId) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("EventCategory(categoryId=");
        sb2.append(this.categoryId);
        sb2.append(", categoryName=");
        return C0086a.m(sb2, this.categoryName, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeInt(this.categoryId);
        parcel.writeString(this.categoryName);
    }
}
