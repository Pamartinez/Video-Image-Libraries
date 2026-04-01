package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\r\u001a\u00020\f\"\b\b\u0000\u0010\t*\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/ListeningToMusicActivityBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcelable;", "T", "Ljava/lang/Class;", "contentClass", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ListeningToMusicActivity;", "createActivity", "(Ljava/lang/Class;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/ListeningToMusicActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ListeningToMusicActivity;", "toActivityV4", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ListeningToMusicActivityBundleWrapper extends ActivityWrapper {
    public static final String BUNDLE_KEY_CONTENTS = "contents";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_EXECUTION_APP = "executionApp";
    public static final String BUNDLE_KEY_ICONIC = "iconic";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<ListeningToMusicActivityBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/ListeningToMusicActivityBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_CONTENTS", "", "BUNDLE_KEY_ICONIC", "BUNDLE_KEY_START_TIMESTAMP", "BUNDLE_KEY_END_TIMESTAMP", "BUNDLE_KEY_EXECUTION_APP", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ListeningToMusicActivityBundleWrapper> {
        public final ListeningToMusicActivityBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ListeningToMusicActivityBundleWrapper(parcel.readString(), parcel.readBundle(ListeningToMusicActivityBundleWrapper.class.getClassLoader()));
        }

        public final ListeningToMusicActivityBundleWrapper[] newArray(int i2) {
            return new ListeningToMusicActivityBundleWrapper[i2];
        }
    }

    public ListeningToMusicActivityBundleWrapper(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        this.id = str;
        this.properties = bundle;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: ne.t} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.List] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T extends android.os.Parcelable> com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity createActivity(java.lang.Class<T> r11) {
        /*
            r10 = this;
            android.os.Bundle r0 = r10.properties
            java.util.ArrayList r11 = r0.getParcelableArrayList("contents", r11)
            ne.t r0 = ne.C1202t.d
            r1 = 10
            if (r11 == 0) goto L_0x0056
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = ne.C1196n.w0(r11, r1)
            r2.<init>(r3)
            java.util.Iterator r11 = r11.iterator()
        L_0x0019:
            boolean r3 = r11.hasNext()
            if (r3 == 0) goto L_0x0057
            java.lang.Object r3 = r11.next()
            android.os.Parcelable r3 = (android.os.Parcelable) r3
            boolean r4 = r3 instanceof com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper
            if (r4 == 0) goto L_0x0030
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper r3 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper) r3
            com.samsung.android.sdk.moneta.memory.entity.content.Content r3 = r3.toContent()
            goto L_0x003a
        L_0x0030:
            boolean r4 = r3 instanceof com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper
            if (r4 == 0) goto L_0x003e
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper r3 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper) r3
            com.samsung.android.sdk.moneta.memory.entity.content.Content r3 = r3.toContent()
        L_0x003a:
            r2.add(r3)
            goto L_0x0019
        L_0x003e:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "Unsupported content type: "
            r11.<init>(r0)
            java.lang.Class r0 = r3.getClass()
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x0056:
            r2 = r0
        L_0x0057:
            android.os.Bundle r11 = r10.properties
            java.util.ArrayList r11 = r11.getParcelableArrayList("executionApp", com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MobileApplicationBundleWrapper.class)
            if (r11 == 0) goto L_0x0080
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = ne.C1196n.w0(r11, r1)
            r0.<init>(r1)
            java.util.Iterator r11 = r11.iterator()
        L_0x006c:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L_0x0080
            java.lang.Object r1 = r11.next()
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MobileApplicationBundleWrapper r1 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MobileApplicationBundleWrapper) r1
            com.samsung.android.sdk.moneta.memory.entity.content.MobileApplication r1 = r1.toContent()
            r0.add(r1)
            goto L_0x006c
        L_0x0080:
            r7 = r0
            com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity r0 = new com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity
            java.lang.String r1 = r10.id
            android.os.Bundle r11 = r10.properties
            java.lang.Object r11 = r11.getParcelable("iconic", com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper.class)
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper r11 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper) r11
            if (r11 == 0) goto L_0x0097
            com.samsung.android.sdk.moneta.memory.entity.content.MediaSession r11 = r11.toContent()
            if (r11 == 0) goto L_0x0097
        L_0x0095:
            r3 = r11
            goto L_0x009e
        L_0x0097:
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper$Companion r11 = com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper.Companion
            com.samsung.android.sdk.moneta.memory.entity.content.MediaSession r11 = r11.getUnknownContent()
            goto L_0x0095
        L_0x009e:
            android.os.Bundle r11 = r10.properties
            java.lang.String r4 = "startTimestamp"
            r5 = 0
            long r4 = r11.getLong(r4, r5)
            android.os.Bundle r10 = r10.properties
            java.lang.String r11 = "endTimestamp"
            r8 = -1
            long r10 = r10.getLong(r11, r8)
            java.lang.Long r6 = java.lang.Long.valueOf(r10)
            r0.<init>(r1, r2, r3, r4, r6, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ListeningToMusicActivityBundleWrapper.createActivity(java.lang.Class):com.samsung.android.sdk.moneta.memory.entity.activity.ListeningToMusicActivity");
    }

    public final int describeContents() {
        return 0;
    }

    public final String getId() {
        return this.id;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final ListeningToMusicActivity toActivityV4() {
        return createActivity(ContentBundleWrapper.class);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
