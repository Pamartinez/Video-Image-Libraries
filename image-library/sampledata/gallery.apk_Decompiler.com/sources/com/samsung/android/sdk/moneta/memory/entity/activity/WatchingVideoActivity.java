package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.content.MediaSession;
import com.samsung.android.sdk.moneta.memory.entity.content.MobileApplication;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u0004HÆ\u0003¢\u0006\u0004\b#\u0010\u001cJZ\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0004HÆ\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b&\u0010\u001aJ\u0010\u0010'\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b'\u0010\u0018J\u001a\u0010+\u001a\u00020*2\b\u0010)\u001a\u0004\u0018\u00010(HÖ\u0003¢\u0006\u0004\b+\u0010,R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010-\u001a\u0004\b.\u0010\u001aR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010/\u001a\u0004\b0\u0010\u001cR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u00101\u001a\u0004\b2\u0010\u001eR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u00103\u001a\u0004\b4\u0010 R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u00105\u001a\u0004\b6\u0010\"R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010/\u001a\u0004\b7\u0010\u001c¨\u00068"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/WatchingVideoActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "iconic", "", "startTimestamp", "endTimestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "executionApp", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;JLjava/lang/Long;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "component4", "()J", "component5", "()Ljava/lang/Long;", "component6", "copy", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;JLjava/lang/Long;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/WatchingVideoActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MediaSession;", "getIconic", "J", "getStartTimestamp", "Ljava/lang/Long;", "getEndTimestamp", "getExecutionApp", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WatchingVideoActivity extends Activity {
    public static final Parcelable.Creator<WatchingVideoActivity> CREATOR = new Creator();
    private final List<Content> contents;
    private final Long endTimestamp;
    private final List<MobileApplication> executionApp;
    private final MediaSession iconic;
    private final String id;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<WatchingVideoActivity> {
        public final WatchingVideoActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            int i7 = 0;
            while (i7 != readInt) {
                i7 = a.b(WatchingVideoActivity.class, parcel, arrayList, i7, 1);
            }
            MediaSession createFromParcel = MediaSession.CREATOR.createFromParcel(parcel);
            long readLong = parcel.readLong();
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            int readInt2 = parcel.readInt();
            ArrayList arrayList2 = new ArrayList(readInt2);
            while (i2 != readInt2) {
                i2 = a.a(MobileApplication.CREATOR, parcel, arrayList2, i2, 1);
            }
            return new WatchingVideoActivity(readString, arrayList, createFromParcel, readLong, valueOf, arrayList2);
        }

        public final WatchingVideoActivity[] newArray(int i2) {
            return new WatchingVideoActivity[i2];
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WatchingVideoActivity(String str, List list, MediaSession mediaSession, long j2, Long l, List list2, int i2, e eVar) {
        this(str, list, mediaSession, j2, l, (i2 & 32) != 0 ? C1202t.d : list2);
    }

    public static /* synthetic */ WatchingVideoActivity copy$default(WatchingVideoActivity watchingVideoActivity, String str, List<Content> list, MediaSession mediaSession, long j2, Long l, List<MobileApplication> list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = watchingVideoActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = watchingVideoActivity.contents;
        }
        if ((i2 & 4) != 0) {
            mediaSession = watchingVideoActivity.iconic;
        }
        if ((i2 & 8) != 0) {
            j2 = watchingVideoActivity.startTimestamp;
        }
        if ((i2 & 16) != 0) {
            l = watchingVideoActivity.endTimestamp;
        }
        if ((i2 & 32) != 0) {
            list2 = watchingVideoActivity.executionApp;
        }
        long j3 = j2;
        List<Content> list3 = list;
        MediaSession mediaSession2 = mediaSession;
        return watchingVideoActivity.copy(str, list3, mediaSession2, j3, l, list2);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final MediaSession component3() {
        return this.iconic;
    }

    public final long component4() {
        return this.startTimestamp;
    }

    public final Long component5() {
        return this.endTimestamp;
    }

    public final List<MobileApplication> component6() {
        return this.executionApp;
    }

    public final WatchingVideoActivity copy(String str, List<? extends Content> list, MediaSession mediaSession, long j2, Long l, List<MobileApplication> list2) {
        j.e(str, "id");
        j.e(list, "contents");
        j.e(mediaSession, "iconic");
        j.e(list2, "executionApp");
        return new WatchingVideoActivity(str, list, mediaSession, j2, l, list2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WatchingVideoActivity)) {
            return false;
        }
        WatchingVideoActivity watchingVideoActivity = (WatchingVideoActivity) obj;
        if (j.a(this.id, watchingVideoActivity.id) && j.a(this.contents, watchingVideoActivity.contents) && j.a(this.iconic, watchingVideoActivity.iconic) && this.startTimestamp == watchingVideoActivity.startTimestamp && j.a(this.endTimestamp, watchingVideoActivity.endTimestamp) && j.a(this.executionApp, watchingVideoActivity.executionApp)) {
            return true;
        }
        return false;
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final List<MobileApplication> getExecutionApp() {
        return this.executionApp;
    }

    public final MediaSession getIconic() {
        return this.iconic;
    }

    public String getId() {
        return this.id;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public int hashCode() {
        int i2;
        int c5 = C0212a.c((this.iconic.hashCode() + C0212a.f(this.contents, this.id.hashCode() * 31, 31)) * 31, 31, this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            i2 = 0;
        } else {
            i2 = l.hashCode();
        }
        return this.executionApp.hashCode() + ((c5 + i2) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("WatchingVideoActivity(id=");
        sb2.append(this.id);
        sb2.append(", contents=");
        sb2.append(this.contents);
        sb2.append(", iconic=");
        sb2.append(this.iconic);
        sb2.append(", startTimestamp=");
        sb2.append(this.startTimestamp);
        sb2.append(", endTimestamp=");
        sb2.append(this.endTimestamp);
        sb2.append(", executionApp=");
        return C0212a.r(sb2, this.executionApp, ')');
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
        Iterator j3 = a.j(parcel, this.executionApp);
        while (j3.hasNext()) {
            ((MobileApplication) j3.next()).writeToParcel(parcel, i2);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WatchingVideoActivity(String str, List<? extends Content> list, MediaSession mediaSession, long j2, Long l, List<MobileApplication> list2) {
        super(ActivityType.WatchingVideo);
        j.e(str, "id");
        j.e(list, "contents");
        j.e(mediaSession, "iconic");
        j.e(list2, "executionApp");
        this.id = str;
        this.contents = list;
        this.iconic = mediaSession;
        this.startTimestamp = j2;
        this.endTimestamp = l;
        this.executionApp = list2;
    }
}
