package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J.\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0016J\u0010\u0010\u001c\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0012J\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0018¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaArtist;", "Landroid/os/Parcelable;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "mediaType", "", "artist", "Landroid/os/Bundle;", "preferences", "<init>", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "component2", "()Ljava/lang/String;", "component3", "()Landroid/os/Bundle;", "copy", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Ljava/lang/String;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaArtist;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "getMediaType", "Ljava/lang/String;", "getArtist", "Landroid/os/Bundle;", "getPreferences", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaArtist implements Parcelable {
    public static final Parcelable.Creator<MediaArtist> CREATOR = new Creator();
    private final String artist;
    private final MediaType mediaType;
    private final Bundle preferences;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MediaArtist> {
        public final MediaArtist createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MediaArtist(MediaType.valueOf(parcel.readString()), parcel.readString(), parcel.readBundle(MediaArtist.class.getClassLoader()));
        }

        public final MediaArtist[] newArray(int i2) {
            return new MediaArtist[i2];
        }
    }

    public MediaArtist(MediaType mediaType2, String str, Bundle bundle) {
        j.e(mediaType2, "mediaType");
        j.e(str, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(bundle, "preferences");
        this.mediaType = mediaType2;
        this.artist = str;
        this.preferences = bundle;
    }

    public static /* synthetic */ MediaArtist copy$default(MediaArtist mediaArtist, MediaType mediaType2, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            mediaType2 = mediaArtist.mediaType;
        }
        if ((i2 & 2) != 0) {
            str = mediaArtist.artist;
        }
        if ((i2 & 4) != 0) {
            bundle = mediaArtist.preferences;
        }
        return mediaArtist.copy(mediaType2, str, bundle);
    }

    public final MediaType component1() {
        return this.mediaType;
    }

    public final String component2() {
        return this.artist;
    }

    public final Bundle component3() {
        return this.preferences;
    }

    public final MediaArtist copy(MediaType mediaType2, String str, Bundle bundle) {
        j.e(mediaType2, "mediaType");
        j.e(str, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(bundle, "preferences");
        return new MediaArtist(mediaType2, str, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaArtist)) {
            return false;
        }
        MediaArtist mediaArtist = (MediaArtist) obj;
        if (this.mediaType == mediaArtist.mediaType && j.a(this.artist, mediaArtist.artist) && j.a(this.preferences, mediaArtist.preferences)) {
            return true;
        }
        return false;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final Bundle getPreferences() {
        return this.preferences;
    }

    public int hashCode() {
        return this.preferences.hashCode() + C0212a.d(this.mediaType.hashCode() * 31, 31, this.artist);
    }

    public String toString() {
        return "MediaArtist(mediaType=" + this.mediaType + ", artist=" + this.artist + ", preferences=" + this.preferences + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.mediaType.name());
        parcel.writeString(this.artist);
        parcel.writeBundle(this.preferences);
    }
}
