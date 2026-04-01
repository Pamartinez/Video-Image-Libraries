package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.content.MediaSession;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/activity/ListeningToMusicActivityWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "iconic", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;JLjava/lang/Long;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ListeningToMusicActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ListeningToMusicActivity;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getContents", "()Ljava/util/List;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "getIconic", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "J", "getStartTimestamp", "()J", "Ljava/lang/Long;", "getEndTimestamp", "()Ljava/lang/Long;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ListeningToMusicActivityWrapperV1 extends ActivityWrapper {
    public static final Parcelable.Creator<ListeningToMusicActivityWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<Content> contents;
    private final Long endTimestamp;
    private final MediaSession iconic;
    private final String id;
    private final long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/activity/ListeningToMusicActivityWrapperV1$Companion;", "", "<init>", "()V", "fromActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/activity/ListeningToMusicActivityWrapperV1;", "listeningToMusicActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ListeningToMusicActivity;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ListeningToMusicActivityWrapperV1 fromActivity(ListeningToMusicActivity listeningToMusicActivity) {
            j.e(listeningToMusicActivity, "listeningToMusicActivity");
            return new ListeningToMusicActivityWrapperV1(listeningToMusicActivity.getId(), listeningToMusicActivity.getContents(), listeningToMusicActivity.getIconic(), listeningToMusicActivity.getStartTimestamp(), listeningToMusicActivity.getEndTimestamp());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ListeningToMusicActivityWrapperV1> {
        public final ListeningToMusicActivityWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(ListeningToMusicActivityWrapperV1.class, parcel, arrayList, i2, 1);
            }
            return new ListeningToMusicActivityWrapperV1(readString, arrayList, MediaSession.CREATOR.createFromParcel(parcel), parcel.readLong(), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()));
        }

        public final ListeningToMusicActivityWrapperV1[] newArray(int i2) {
            return new ListeningToMusicActivityWrapperV1[i2];
        }
    }

    public ListeningToMusicActivityWrapperV1(String str, List<? extends Content> list, MediaSession mediaSession, long j2, Long l) {
        j.e(str, "id");
        j.e(list, "contents");
        j.e(mediaSession, "iconic");
        this.id = str;
        this.contents = list;
        this.iconic = mediaSession;
        this.startTimestamp = j2;
        this.endTimestamp = l;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<Content> getContents() {
        return this.contents;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final MediaSession getIconic() {
        return this.iconic;
    }

    public final String getId() {
        return this.id;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        this.iconic.writeToParcel(parcel, i2);
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
    }
}
