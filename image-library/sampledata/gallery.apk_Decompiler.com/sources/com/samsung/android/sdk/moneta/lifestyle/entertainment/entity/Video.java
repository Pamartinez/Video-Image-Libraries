package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J.\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u0013J\u0010\u0010\u001a\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0011J\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\"\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010#\u001a\u0004\b$\u0010\u0016¨\u0006%"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Video;", "Landroid/os/Parcelable;", "", "title", "artist", "Landroid/os/Bundle;", "preferences", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Video;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTitle", "getArtist", "Landroid/os/Bundle;", "getPreferences", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Video implements Parcelable {
    public static final Parcelable.Creator<Video> CREATOR = new Creator();
    private final String artist;
    private final Bundle preferences;
    private final String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Video> {
        public final Video createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Video(parcel.readString(), parcel.readString(), parcel.readBundle(Video.class.getClassLoader()));
        }

        public final Video[] newArray(int i2) {
            return new Video[i2];
        }
    }

    public Video(String str, String str2, Bundle bundle) {
        j.e(str, "title");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(bundle, "preferences");
        this.title = str;
        this.artist = str2;
        this.preferences = bundle;
    }

    public static /* synthetic */ Video copy$default(Video video, String str, String str2, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = video.title;
        }
        if ((i2 & 2) != 0) {
            str2 = video.artist;
        }
        if ((i2 & 4) != 0) {
            bundle = video.preferences;
        }
        return video.copy(str, str2, bundle);
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.artist;
    }

    public final Bundle component3() {
        return this.preferences;
    }

    public final Video copy(String str, String str2, Bundle bundle) {
        j.e(str, "title");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(bundle, "preferences");
        return new Video(str, str2, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Video)) {
            return false;
        }
        Video video = (Video) obj;
        if (j.a(this.title, video.title) && j.a(this.artist, video.artist) && j.a(this.preferences, video.preferences)) {
            return true;
        }
        return false;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final Bundle getPreferences() {
        return this.preferences;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.preferences.hashCode() + C0212a.d(this.title.hashCode() * 31, 31, this.artist);
    }

    public String toString() {
        return "Video(title=" + this.title + ", artist=" + this.artist + ", preferences=" + this.preferences + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeBundle(this.preferences);
    }
}
