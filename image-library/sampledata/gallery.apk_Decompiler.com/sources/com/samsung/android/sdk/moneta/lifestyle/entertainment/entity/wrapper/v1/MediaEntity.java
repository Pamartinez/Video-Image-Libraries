package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1;

import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import gg.a;
import i.C0212a;
import ig.f;
import jg.b;
import kg.C1141w;
import kg.Q;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import og.k;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0013\b\b\u0018\u0000 92\u00020\u0001:\u0002:9B;\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bBM\b\u0010\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u0010J'\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001dJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001dJ\u0010\u0010 \u001a\u00020\bHÆ\u0003¢\u0006\u0004\b \u0010!JH\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b$\u0010\u001dJ\u0010\u0010%\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b%\u0010&J\u001a\u0010)\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b)\u0010*R\"\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010+\u0012\u0004\b-\u0010.\u001a\u0004\b,\u0010\u001bR\"\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010/\u0012\u0004\b1\u0010.\u001a\u0004\b0\u0010\u001dR \u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010/\u0012\u0004\b3\u0010.\u001a\u0004\b2\u0010\u001dR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010/\u0012\u0004\b5\u0010.\u001a\u0004\b4\u0010\u001dR \u0010\t\u001a\u00020\b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\t\u00106\u0012\u0004\b8\u0010.\u001a\u0004\b7\u0010!¨\u0006;"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;", "", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "mediaType", "", "title", "artist", "album", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;", "preferences", "<init>", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "component2", "()Ljava/lang/String;", "component3", "component4", "component5", "()Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;", "copy", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;)Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "getMediaType", "getMediaType$annotations", "()V", "Ljava/lang/String;", "getTitle", "getTitle$annotations", "getArtist", "getArtist$annotations", "getAlbum", "getAlbum$annotations", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaPreferences;", "getPreferences", "getPreferences$annotations", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaEntity {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers;
    public static final Companion Companion = new Companion((e) null);
    private final String album;
    private final String artist;
    private final MediaType mediaType;
    private final MediaPreferences preferences;
    private final String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/wrapper/v1/MediaEntity;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return MediaEntity$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    static {
        MediaType[] values = MediaType.values();
        j.e(values, StateHandler.VALUES);
        $childSerializers = new a[]{new C1141w("com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType", values), null, null, null, null};
    }

    public /* synthetic */ MediaEntity(int i2, MediaType mediaType2, String str, String str2, String str3, MediaPreferences mediaPreferences, a0 a0Var) {
        if (20 == (i2 & 20)) {
            if ((i2 & 1) == 0) {
                this.mediaType = null;
            } else {
                this.mediaType = mediaType2;
            }
            if ((i2 & 2) == 0) {
                this.title = null;
            } else {
                this.title = str;
            }
            this.artist = str2;
            if ((i2 & 8) == 0) {
                this.album = null;
            } else {
                this.album = str3;
            }
            this.preferences = mediaPreferences;
            return;
        }
        Q.f(i2, 20, MediaEntity$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ MediaEntity copy$default(MediaEntity mediaEntity, MediaType mediaType2, String str, String str2, String str3, MediaPreferences mediaPreferences, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            mediaType2 = mediaEntity.mediaType;
        }
        if ((i2 & 2) != 0) {
            str = mediaEntity.title;
        }
        if ((i2 & 4) != 0) {
            str2 = mediaEntity.artist;
        }
        if ((i2 & 8) != 0) {
            str3 = mediaEntity.album;
        }
        if ((i2 & 16) != 0) {
            mediaPreferences = mediaEntity.preferences;
        }
        String str4 = str3;
        MediaPreferences mediaPreferences2 = mediaPreferences;
        return mediaEntity.copy(mediaType2, str, str2, str4, mediaPreferences2);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(MediaEntity mediaEntity, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        if (bVar.d(fVar) || mediaEntity.mediaType != null) {
            bVar.c(fVar, 0, aVarArr[0], mediaEntity.mediaType);
        }
        if (bVar.d(fVar) || mediaEntity.title != null) {
            bVar.c(fVar, 1, e0.f4693a, mediaEntity.title);
        }
        k kVar = (k) bVar;
        kVar.u(fVar, 2, mediaEntity.artist);
        if (bVar.d(fVar) || mediaEntity.album != null) {
            bVar.c(fVar, 3, e0.f4693a, mediaEntity.album);
        }
        kVar.t(fVar, 4, MediaPreferences$$serializer.INSTANCE, mediaEntity.preferences);
    }

    public final MediaType component1() {
        return this.mediaType;
    }

    public final String component2() {
        return this.title;
    }

    public final String component3() {
        return this.artist;
    }

    public final String component4() {
        return this.album;
    }

    public final MediaPreferences component5() {
        return this.preferences;
    }

    public final MediaEntity copy(MediaType mediaType2, String str, String str2, String str3, MediaPreferences mediaPreferences) {
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(mediaPreferences, "preferences");
        return new MediaEntity(mediaType2, str, str2, str3, mediaPreferences);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaEntity)) {
            return false;
        }
        MediaEntity mediaEntity = (MediaEntity) obj;
        if (this.mediaType == mediaEntity.mediaType && j.a(this.title, mediaEntity.title) && j.a(this.artist, mediaEntity.artist) && j.a(this.album, mediaEntity.album) && j.a(this.preferences, mediaEntity.preferences)) {
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

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final MediaPreferences getPreferences() {
        return this.preferences;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int i2;
        int i7;
        MediaType mediaType2 = this.mediaType;
        int i8 = 0;
        if (mediaType2 == null) {
            i2 = 0;
        } else {
            i2 = mediaType2.hashCode();
        }
        int i10 = i2 * 31;
        String str = this.title;
        if (str == null) {
            i7 = 0;
        } else {
            i7 = str.hashCode();
        }
        int d = C0212a.d((i10 + i7) * 31, 31, this.artist);
        String str2 = this.album;
        if (str2 != null) {
            i8 = str2.hashCode();
        }
        return this.preferences.hashCode() + ((d + i8) * 31);
    }

    public String toString() {
        return "MediaEntity(mediaType=" + this.mediaType + ", title=" + this.title + ", artist=" + this.artist + ", album=" + this.album + ", preferences=" + this.preferences + ')';
    }

    public MediaEntity(MediaType mediaType2, String str, String str2, String str3, MediaPreferences mediaPreferences) {
        j.e(str2, MediaSessionBundleWrapper.BUNDLE_KEY_ARTIST);
        j.e(mediaPreferences, "preferences");
        this.mediaType = mediaType2;
        this.title = str;
        this.artist = str2;
        this.album = str3;
        this.preferences = mediaPreferences;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MediaEntity(MediaType mediaType2, String str, String str2, String str3, MediaPreferences mediaPreferences, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : mediaType2, (i2 & 2) != 0 ? null : str, str2, (i2 & 8) != 0 ? null : str3, mediaPreferences);
    }

    public static /* synthetic */ void getAlbum$annotations() {
    }

    public static /* synthetic */ void getArtist$annotations() {
    }

    public static /* synthetic */ void getMediaType$annotations() {
    }

    public static /* synthetic */ void getPreferences$annotations() {
    }

    public static /* synthetic */ void getTitle$annotations() {
    }
}
