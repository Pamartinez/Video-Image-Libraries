package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\r\u001a\u00020\f\"\b\b\u0000\u0010\t*\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/ScheduledEventBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcelable;", "T", "Ljava/lang/Class;", "contentClass", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "createActivity", "(Ljava/lang/Class;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "toActivityV4", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduledEventBundleWrapper extends ActivityWrapper {
    public static final String BUNDLE_KEY_CONTENTS = "contents";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<ScheduledEventBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/ScheduledEventBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_CONTENTS", "", "BUNDLE_KEY_START_TIMESTAMP", "BUNDLE_KEY_END_TIMESTAMP", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
    public static final class Creator implements Parcelable.Creator<ScheduledEventBundleWrapper> {
        public final ScheduledEventBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ScheduledEventBundleWrapper(parcel.readString(), parcel.readBundle(ScheduledEventBundleWrapper.class.getClassLoader()));
        }

        public final ScheduledEventBundleWrapper[] newArray(int i2) {
            return new ScheduledEventBundleWrapper[i2];
        }
    }

    public ScheduledEventBundleWrapper(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        this.id = str;
        this.properties = bundle;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: ne.t} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T extends android.os.Parcelable> com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent createActivity(java.lang.Class<T> r5) {
        /*
            r4 = this;
            android.os.Bundle r0 = r4.properties
            java.util.ArrayList r5 = r0.getParcelableArrayList("contents", r5)
            if (r5 == 0) goto L_0x0054
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = ne.C1196n.w0(r5, r1)
            r0.<init>(r1)
            java.util.Iterator r5 = r5.iterator()
        L_0x0017:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0056
            java.lang.Object r1 = r5.next()
            android.os.Parcelable r1 = (android.os.Parcelable) r1
            boolean r2 = r1 instanceof com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper
            if (r2 == 0) goto L_0x002e
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper r1 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper) r1
            com.samsung.android.sdk.moneta.memory.entity.content.Content r1 = r1.toContent()
            goto L_0x0038
        L_0x002e:
            boolean r2 = r1 instanceof com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper
            if (r2 == 0) goto L_0x003c
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper r1 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper) r1
            com.samsung.android.sdk.moneta.memory.entity.content.Content r1 = r1.toContent()
        L_0x0038:
            r0.add(r1)
            goto L_0x0017
        L_0x003c:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Unsupported content type: "
            r5.<init>(r0)
            java.lang.Class r0 = r1.getClass()
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0054:
            ne.t r0 = ne.C1202t.d
        L_0x0056:
            com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent r5 = new com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent
            java.lang.String r1 = r4.id
            android.os.Bundle r2 = r4.properties
            java.lang.String r3 = "startTimestamp"
            java.lang.Long r2 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getLongOrNull(r2, r3)
            android.os.Bundle r4 = r4.properties
            java.lang.String r3 = "endTimestamp"
            java.lang.Long r4 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getLongOrNull(r4, r3)
            r5.<init>(r1, r0, r2, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ScheduledEventBundleWrapper.createActivity(java.lang.Class):com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent");
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

    public final ScheduledEvent toActivityV4() {
        return createActivity(ContentBundleWrapper.class);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
