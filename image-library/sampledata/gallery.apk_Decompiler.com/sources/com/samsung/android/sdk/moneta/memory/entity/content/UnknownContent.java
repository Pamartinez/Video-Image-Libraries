package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0014\u0010\u000eJ\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/UnknownContent;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "<init>", "(Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/memory/entity/content/UnknownContent;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnknownContent extends Content {
    public static final Parcelable.Creator<UnknownContent> CREATOR = new Creator();
    private final String id;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<UnknownContent> {
        public final UnknownContent createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new UnknownContent(parcel.readString());
        }

        public final UnknownContent[] newArray(int i2) {
            return new UnknownContent[i2];
        }
    }

    public UnknownContent(String str) {
        j.e(str, "id");
        this.id = str;
    }

    public static /* synthetic */ UnknownContent copy$default(UnknownContent unknownContent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = unknownContent.id;
        }
        return unknownContent.copy(str);
    }

    public final String component1() {
        return this.id;
    }

    public final UnknownContent copy(String str) {
        j.e(str, "id");
        return new UnknownContent(str);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof UnknownContent) && j.a(this.id, ((UnknownContent) obj).id)) {
            return true;
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return C0086a.m(new StringBuilder("UnknownContent(id="), this.id, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
    }
}
