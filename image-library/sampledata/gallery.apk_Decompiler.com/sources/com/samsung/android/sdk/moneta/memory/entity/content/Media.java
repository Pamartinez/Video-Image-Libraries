package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J.\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u0013J\u0010\u0010\u001a\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0011J\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\"\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010#\u001a\u0004\b$\u0010\u0016¨\u0006%"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/Media;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "contentUri", "", "mediaId", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()J", "copy", "(Ljava/lang/String;Ljava/lang/String;J)Lcom/samsung/android/sdk/moneta/memory/entity/content/Media;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getContentUri", "J", "getMediaId", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Media extends Content {
    public static final Parcelable.Creator<Media> CREATOR = new Creator();
    private final String contentUri;
    private final String id;
    private final long mediaId;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Media> {
        public final Media createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Media(parcel.readString(), parcel.readString(), parcel.readLong());
        }

        public final Media[] newArray(int i2) {
            return new Media[i2];
        }
    }

    public Media(String str, String str2, long j2) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        this.id = str;
        this.contentUri = str2;
        this.mediaId = j2;
    }

    public static /* synthetic */ Media copy$default(Media media, String str, String str2, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = media.id;
        }
        if ((i2 & 2) != 0) {
            str2 = media.contentUri;
        }
        if ((i2 & 4) != 0) {
            j2 = media.mediaId;
        }
        return media.copy(str, str2, j2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.contentUri;
    }

    public final long component3() {
        return this.mediaId;
    }

    public final Media copy(String str, String str2, long j2) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        return new Media(str, str2, j2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Media)) {
            return false;
        }
        Media media = (Media) obj;
        if (j.a(this.id, media.id) && j.a(this.contentUri, media.contentUri) && this.mediaId == media.mediaId) {
            return true;
        }
        return false;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public String getId() {
        return this.id;
    }

    public final long getMediaId() {
        return this.mediaId;
    }

    public int hashCode() {
        return Long.hashCode(this.mediaId) + C0212a.d(this.id.hashCode() * 31, 31, this.contentUri);
    }

    public String toString() {
        return "Media(id=" + this.id + ", contentUri=" + this.contentUri + ", mediaId=" + this.mediaId + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.contentUri);
        parcel.writeLong(this.mediaId);
    }
}
