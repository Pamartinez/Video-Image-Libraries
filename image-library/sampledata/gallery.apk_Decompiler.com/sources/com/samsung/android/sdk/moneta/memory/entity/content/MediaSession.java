package com.samsung.android.sdk.moneta.memory.entity.content;

import A.a;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0018¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010 J\u0010\u0010\"\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b$\u0010 J\u0012\u0010%\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b%\u0010 J\u0012\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0012\u0010(\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b(\u0010)J\u0012\u0010*\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b*\u0010 J\u0012\u0010+\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b+\u0010 J\u0012\u0010,\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b,\u0010)J\u0012\u0010-\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b-\u0010)J\u0012\u0010.\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b.\u0010 J\u0012\u0010/\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b/\u0010 J\u0012\u00100\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b0\u0010 J²\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b3\u0010 J\u0010\u00104\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b4\u0010\u001eJ\u001a\u00108\u001a\u0002072\b\u00106\u001a\u0004\u0018\u000105HÖ\u0003¢\u0006\u0004\b8\u00109R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010:\u001a\u0004\b;\u0010 R \u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0004\u0010:\u0012\u0004\b=\u0010>\u001a\u0004\b<\u0010 R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010?\u001a\u0004\b@\u0010#R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010:\u001a\u0004\bA\u0010 R\"\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\b\u0010:\u0012\u0004\bC\u0010>\u001a\u0004\bB\u0010 R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010D\u001a\u0004\bE\u0010'R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010F\u001a\u0004\bG\u0010)R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010:\u001a\u0004\bH\u0010 R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010:\u001a\u0004\bI\u0010 R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010F\u001a\u0004\bJ\u0010)R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\u0010\u0010F\u001a\u0004\bK\u0010)R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010:\u001a\u0004\bL\u0010 R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010:\u001a\u0004\bM\u0010 R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010:\u001a\u0004\bN\u0010 ¨\u0006O"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "mediaId", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaType;", "mediaType", "title", "albumArtBitmap", "Landroid/net/Uri;", "albumArtUri", "", "duration", "artist", "albumTitle", "startTimestamp", "endTimestamp", "appPackageName", "rawMediaId", "mediaUri", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaType;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaType;", "component4", "component5", "component6", "()Landroid/net/Uri;", "component7", "()Ljava/lang/Long;", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaType;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getMediaId", "getMediaId$annotations", "()V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaType;", "getMediaType", "getTitle", "getAlbumArtBitmap", "getAlbumArtBitmap$annotations", "Landroid/net/Uri;", "getAlbumArtUri", "Ljava/lang/Long;", "getDuration", "getArtist", "getAlbumTitle", "getStartTimestamp", "getEndTimestamp", "getAppPackageName", "getRawMediaId", "getMediaUri", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaSession extends Content {
    public static final Parcelable.Creator<MediaSession> CREATOR = new Creator();
    private final String albumArtBitmap;
    private final Uri albumArtUri;
    private final String albumTitle;
    private final String appPackageName;
    private final String artist;
    private final Long duration;
    private final Long endTimestamp;
    private final String id;
    private final String mediaId;
    private final MediaType mediaType;
    private final String mediaUri;
    private final String rawMediaId;
    private final Long startTimestamp;
    private final String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MediaSession> {
        public final MediaSession createFromParcel(Parcel parcel) {
            Parcel parcel2 = parcel;
            j.e(parcel2, "parcel");
            String readString = parcel2.readString();
            String readString2 = parcel2.readString();
            MediaType valueOf = MediaType.valueOf(parcel2.readString());
            String readString3 = parcel2.readString();
            String readString4 = parcel2.readString();
            Uri uri = (Uri) parcel2.readParcelable(MediaSession.class.getClassLoader());
            Long l = null;
            Long valueOf2 = parcel2.readInt() == 0 ? null : Long.valueOf(parcel2.readLong());
            String readString5 = parcel2.readString();
            String readString6 = parcel2.readString();
            Long valueOf3 = parcel2.readInt() == 0 ? null : Long.valueOf(parcel2.readLong());
            if (parcel2.readInt() != 0) {
                l = Long.valueOf(parcel2.readLong());
            }
            return new MediaSession(readString, readString2, valueOf, readString3, readString4, uri, valueOf2, readString5, readString6, valueOf3, l, parcel2.readString(), parcel2.readString(), parcel2.readString());
        }

        public final MediaSession[] newArray(int i2) {
            return new MediaSession[i2];
        }
    }

    public MediaSession(String str, String str2, MediaType mediaType2, String str3, String str4, Uri uri, Long l, String str5, String str6, Long l8, Long l10, String str7, String str8, String str9) {
        j.e(str, "id");
        j.e(str2, "mediaId");
        j.e(mediaType2, "mediaType");
        this.id = str;
        this.mediaId = str2;
        this.mediaType = mediaType2;
        this.title = str3;
        this.albumArtBitmap = str4;
        this.albumArtUri = uri;
        this.duration = l;
        this.artist = str5;
        this.albumTitle = str6;
        this.startTimestamp = l8;
        this.endTimestamp = l10;
        this.appPackageName = str7;
        this.rawMediaId = str8;
        this.mediaUri = str9;
    }

    public static /* synthetic */ MediaSession copy$default(MediaSession mediaSession, String str, String str2, MediaType mediaType2, String str3, String str4, Uri uri, Long l, String str5, String str6, Long l8, Long l10, String str7, String str8, String str9, int i2, Object obj) {
        int i7 = i2;
        return mediaSession.copy((i7 & 1) != 0 ? mediaSession.id : str, (i7 & 2) != 0 ? mediaSession.mediaId : str2, (i7 & 4) != 0 ? mediaSession.mediaType : mediaType2, (i7 & 8) != 0 ? mediaSession.title : str3, (i7 & 16) != 0 ? mediaSession.albumArtBitmap : str4, (i7 & 32) != 0 ? mediaSession.albumArtUri : uri, (i7 & 64) != 0 ? mediaSession.duration : l, (i7 & 128) != 0 ? mediaSession.artist : str5, (i7 & 256) != 0 ? mediaSession.albumTitle : str6, (i7 & 512) != 0 ? mediaSession.startTimestamp : l8, (i7 & 1024) != 0 ? mediaSession.endTimestamp : l10, (i7 & 2048) != 0 ? mediaSession.appPackageName : str7, (i7 & 4096) != 0 ? mediaSession.rawMediaId : str8, (i7 & SerializeOptions.SORT) != 0 ? mediaSession.mediaUri : str9);
    }

    public final String component1() {
        return this.id;
    }

    public final Long component10() {
        return this.startTimestamp;
    }

    public final Long component11() {
        return this.endTimestamp;
    }

    public final String component12() {
        return this.appPackageName;
    }

    public final String component13() {
        return this.rawMediaId;
    }

    public final String component14() {
        return this.mediaUri;
    }

    public final String component2() {
        return this.mediaId;
    }

    public final MediaType component3() {
        return this.mediaType;
    }

    public final String component4() {
        return this.title;
    }

    public final String component5() {
        return this.albumArtBitmap;
    }

    public final Uri component6() {
        return this.albumArtUri;
    }

    public final Long component7() {
        return this.duration;
    }

    public final String component8() {
        return this.artist;
    }

    public final String component9() {
        return this.albumTitle;
    }

    public final MediaSession copy(String str, String str2, MediaType mediaType2, String str3, String str4, Uri uri, Long l, String str5, String str6, Long l8, Long l10, String str7, String str8, String str9) {
        String str10 = str;
        j.e(str10, "id");
        String str11 = str2;
        j.e(str11, "mediaId");
        MediaType mediaType3 = mediaType2;
        j.e(mediaType3, "mediaType");
        return new MediaSession(str10, str11, mediaType3, str3, str4, uri, l, str5, str6, l8, l10, str7, str8, str9);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSession)) {
            return false;
        }
        MediaSession mediaSession = (MediaSession) obj;
        if (j.a(this.id, mediaSession.id) && j.a(this.mediaId, mediaSession.mediaId) && this.mediaType == mediaSession.mediaType && j.a(this.title, mediaSession.title) && j.a(this.albumArtBitmap, mediaSession.albumArtBitmap) && j.a(this.albumArtUri, mediaSession.albumArtUri) && j.a(this.duration, mediaSession.duration) && j.a(this.artist, mediaSession.artist) && j.a(this.albumTitle, mediaSession.albumTitle) && j.a(this.startTimestamp, mediaSession.startTimestamp) && j.a(this.endTimestamp, mediaSession.endTimestamp) && j.a(this.appPackageName, mediaSession.appPackageName) && j.a(this.rawMediaId, mediaSession.rawMediaId) && j.a(this.mediaUri, mediaSession.mediaUri)) {
            return true;
        }
        return false;
    }

    public final String getAlbumArtBitmap() {
        return this.albumArtBitmap;
    }

    public final Uri getAlbumArtUri() {
        return this.albumArtUri;
    }

    public final String getAlbumTitle() {
        return this.albumTitle;
    }

    public final String getAppPackageName() {
        return this.appPackageName;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final Long getDuration() {
        return this.duration;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public String getId() {
        return this.id;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final String getMediaUri() {
        return this.mediaUri;
    }

    public final String getRawMediaId() {
        return this.rawMediaId;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int hashCode = (this.mediaType.hashCode() + C0212a.d(this.id.hashCode() * 31, 31, this.mediaId)) * 31;
        String str = this.title;
        int i17 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i18 = (hashCode + i2) * 31;
        String str2 = this.albumArtBitmap;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i19 = (i18 + i7) * 31;
        Uri uri = this.albumArtUri;
        if (uri == null) {
            i8 = 0;
        } else {
            i8 = uri.hashCode();
        }
        int i20 = (i19 + i8) * 31;
        Long l = this.duration;
        if (l == null) {
            i10 = 0;
        } else {
            i10 = l.hashCode();
        }
        int i21 = (i20 + i10) * 31;
        String str3 = this.artist;
        if (str3 == null) {
            i11 = 0;
        } else {
            i11 = str3.hashCode();
        }
        int i22 = (i21 + i11) * 31;
        String str4 = this.albumTitle;
        if (str4 == null) {
            i12 = 0;
        } else {
            i12 = str4.hashCode();
        }
        int i23 = (i22 + i12) * 31;
        Long l8 = this.startTimestamp;
        if (l8 == null) {
            i13 = 0;
        } else {
            i13 = l8.hashCode();
        }
        int i24 = (i23 + i13) * 31;
        Long l10 = this.endTimestamp;
        if (l10 == null) {
            i14 = 0;
        } else {
            i14 = l10.hashCode();
        }
        int i25 = (i24 + i14) * 31;
        String str5 = this.appPackageName;
        if (str5 == null) {
            i15 = 0;
        } else {
            i15 = str5.hashCode();
        }
        int i26 = (i25 + i15) * 31;
        String str6 = this.rawMediaId;
        if (str6 == null) {
            i16 = 0;
        } else {
            i16 = str6.hashCode();
        }
        int i27 = (i26 + i16) * 31;
        String str7 = this.mediaUri;
        if (str7 != null) {
            i17 = str7.hashCode();
        }
        return i27 + i17;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MediaSession(id=");
        sb2.append(this.id);
        sb2.append(", mediaId=");
        sb2.append(this.mediaId);
        sb2.append(", mediaType=");
        sb2.append(this.mediaType);
        sb2.append(", title=");
        sb2.append(this.title);
        sb2.append(", albumArtBitmap=");
        sb2.append(this.albumArtBitmap);
        sb2.append(", albumArtUri=");
        sb2.append(this.albumArtUri);
        sb2.append(", duration=");
        sb2.append(this.duration);
        sb2.append(", artist=");
        sb2.append(this.artist);
        sb2.append(", albumTitle=");
        sb2.append(this.albumTitle);
        sb2.append(", startTimestamp=");
        sb2.append(this.startTimestamp);
        sb2.append(", endTimestamp=");
        sb2.append(this.endTimestamp);
        sb2.append(", appPackageName=");
        sb2.append(this.appPackageName);
        sb2.append(", rawMediaId=");
        sb2.append(this.rawMediaId);
        sb2.append(", mediaUri=");
        return C0086a.m(sb2, this.mediaUri, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.mediaId);
        parcel.writeString(this.mediaType.name());
        parcel.writeString(this.title);
        parcel.writeString(this.albumArtBitmap);
        parcel.writeParcelable(this.albumArtUri, i2);
        Long l = this.duration;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        parcel.writeString(this.artist);
        parcel.writeString(this.albumTitle);
        Long l8 = this.startTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        Long l10 = this.endTimestamp;
        if (l10 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l10);
        }
        parcel.writeString(this.appPackageName);
        parcel.writeString(this.rawMediaId);
        parcel.writeString(this.mediaUri);
    }

    public static /* synthetic */ void getAlbumArtBitmap$annotations() {
    }

    public static /* synthetic */ void getMediaId$annotations() {
    }
}
