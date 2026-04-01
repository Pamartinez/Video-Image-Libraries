package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\r\u001a\u00020\f\"\b\b\u0000\u0010\t*\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/EatingActivityBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcelable;", "T", "Ljava/lang/Class;", "contentClass", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "createActivity", "(Ljava/lang/Class;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "toActivityV4", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EatingActivityBundleWrapper extends ActivityWrapper {
    public static final String BUNDLE_KEY_CONTENTS = "contents";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_FOODS = "foods";
    public static final String BUNDLE_KEY_LOCATION = "location";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<EatingActivityBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/EatingActivityBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_CONTENTS", "", "BUNDLE_KEY_FOODS", "BUNDLE_KEY_LOCATION", "BUNDLE_KEY_START_TIMESTAMP", "BUNDLE_KEY_END_TIMESTAMP", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
    public static final class Creator implements Parcelable.Creator<EatingActivityBundleWrapper> {
        public final EatingActivityBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EatingActivityBundleWrapper(parcel.readString(), parcel.readBundle(EatingActivityBundleWrapper.class.getClassLoader()));
        }

        public final EatingActivityBundleWrapper[] newArray(int i2) {
            return new EatingActivityBundleWrapper[i2];
        }
    }

    public EatingActivityBundleWrapper(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        this.id = str;
        this.properties = bundle;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: ne.t} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T extends android.os.Parcelable> com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity createActivity(java.lang.Class<T> r9) {
        /*
            r8 = this;
            android.os.Bundle r0 = r8.properties
            java.util.ArrayList r9 = r0.getParcelableArrayList("contents", r9)
            ne.t r0 = ne.C1202t.d
            if (r9 == 0) goto L_0x0059
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            int r2 = ne.C1196n.w0(r9, r2)
            r1.<init>(r2)
            java.util.Iterator r9 = r9.iterator()
        L_0x0019:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x0056
            java.lang.Object r2 = r9.next()
            android.os.Parcelable r2 = (android.os.Parcelable) r2
            boolean r3 = r2 instanceof com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper
            if (r3 == 0) goto L_0x0030
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper r2 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper) r2
            com.samsung.android.sdk.moneta.memory.entity.content.Content r2 = r2.toContent()
            goto L_0x003a
        L_0x0030:
            boolean r3 = r2 instanceof com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper
            if (r3 == 0) goto L_0x003e
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper r2 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper) r2
            com.samsung.android.sdk.moneta.memory.entity.content.Content r2 = r2.toContent()
        L_0x003a:
            r1.add(r2)
            goto L_0x0019
        L_0x003e:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "Unsupported content type: "
            r9.<init>(r0)
            java.lang.Class r0 = r2.getClass()
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0056:
            r9 = r0
            r2 = r1
            goto L_0x005b
        L_0x0059:
            r9 = r0
            r2 = r9
        L_0x005b:
            com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity r0 = new com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity
            java.lang.String r1 = r8.id
            android.os.Bundle r3 = r8.properties
            java.lang.String r4 = "foods"
            java.util.ArrayList r3 = r3.getStringArrayList(r4)
            if (r3 == 0) goto L_0x006a
            goto L_0x006b
        L_0x006a:
            r3 = r9
        L_0x006b:
            android.os.Bundle r9 = r8.properties
            java.lang.Object r9 = r9.getParcelable("location", com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper.class)
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper r9 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper) r9
            if (r9 == 0) goto L_0x007b
            com.samsung.android.sdk.moneta.memory.entity.context.Place r9 = r9.toContext()
        L_0x0079:
            r4 = r9
            goto L_0x007d
        L_0x007b:
            r9 = 0
            goto L_0x0079
        L_0x007d:
            android.os.Bundle r9 = r8.properties
            java.lang.String r5 = "startTimestamp"
            r6 = 0
            long r5 = r9.getLong(r5, r6)
            android.os.Bundle r8 = r8.properties
            java.lang.String r9 = "endTimestamp"
            java.lang.Long r7 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getLongOrNull(r8, r9)
            r0.<init>(r1, r2, r3, r4, r5, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.EatingActivityBundleWrapper.createActivity(java.lang.Class):com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity");
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

    public final EatingActivity toActivityV4() {
        return createActivity(ContentBundleWrapper.class);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
