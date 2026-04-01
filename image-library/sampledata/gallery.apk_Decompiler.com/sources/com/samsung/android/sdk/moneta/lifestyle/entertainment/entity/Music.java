package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J8\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0012J\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\u0014R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0018¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Music;", "Landroid/os/Parcelable;", "", "title", "artist", "album", "Landroid/os/Bundle;", "preferences", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()Landroid/os/Bundle;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Music;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTitle", "getArtist", "getAlbum", "Landroid/os/Bundle;", "getPreferences", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Music implements Parcelable {
    public static final Parcelable.Creator<Music> CREATOR = new Creator();
    private final String album;
    private final String artist;
    private final Bundle preferences;
    private final String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Music> {
        public final Music createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Music(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readBundle(Music.class.getClassLoader()));
        }

        public final Music[] newArray(int i2) {
            return new Music[i2];
        }
    }

    public Music(String str, String str2, String str3, Bundle bundle) {
        j.e(str, "title");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(str3, "album");
        j.e(bundle, "preferences");
        this.title = str;
        this.artist = str2;
        this.album = str3;
        this.preferences = bundle;
    }

    public static /* synthetic */ Music copy$default(Music music, String str, String str2, String str3, Bundle bundle, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = music.title;
        }
        if ((i2 & 2) != 0) {
            str2 = music.artist;
        }
        if ((i2 & 4) != 0) {
            str3 = music.album;
        }
        if ((i2 & 8) != 0) {
            bundle = music.preferences;
        }
        return music.copy(str, str2, str3, bundle);
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.artist;
    }

    public final String component3() {
        return this.album;
    }

    public final Bundle component4() {
        return this.preferences;
    }

    public final Music copy(String str, String str2, String str3, Bundle bundle) {
        j.e(str, "title");
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(str3, "album");
        j.e(bundle, "preferences");
        return new Music(str, str2, str3, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Music)) {
            return false;
        }
        Music music = (Music) obj;
        if (j.a(this.title, music.title) && j.a(this.artist, music.artist) && j.a(this.album, music.album) && j.a(this.preferences, music.preferences)) {
            return true;
        }
        return false;
    }

    public final String getAlbum() {
        return this.album;
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
        return this.preferences.hashCode() + C0212a.d(C0212a.d(this.title.hashCode() * 31, 31, this.artist), 31, this.album);
    }

    public String toString() {
        return "Music(title=" + this.title + ", artist=" + this.artist + ", album=" + this.album + ", preferences=" + this.preferences + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.album);
        parcel.writeBundle(this.preferences);
    }
}
