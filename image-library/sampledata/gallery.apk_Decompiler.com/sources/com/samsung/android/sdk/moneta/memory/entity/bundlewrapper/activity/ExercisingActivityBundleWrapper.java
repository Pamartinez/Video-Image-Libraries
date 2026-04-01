package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\r\u001a\u00020\f\"\b\b\u0000\u0010\t*\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/ExercisingActivityBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Landroid/os/Parcelable;", "T", "Ljava/lang/Class;", "contentClass", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "createActivity", "(Ljava/lang/Class;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "toActivityV4", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExercisingActivityBundleWrapper extends ActivityWrapper {
    public static final String BUNDLE_KEY_CALORIE = "calorie";
    public static final String BUNDLE_KEY_CONTENTS = "contents";
    public static final String BUNDLE_KEY_END_TIMESTAMP = "endTimestamp";
    public static final String BUNDLE_KEY_EXERCISE_TYPE = "exerciseType";
    public static final String BUNDLE_KEY_LOCATION = "location";
    public static final String BUNDLE_KEY_MAX_HEART_RATE = "maxHeartRate";
    public static final String BUNDLE_KEY_MEAN_HEART_RATE = "meanHeartRate";
    public static final String BUNDLE_KEY_MIN_HEART_RATE = "minHeartRate";
    public static final String BUNDLE_KEY_NAME = "name";
    public static final String BUNDLE_KEY_START_TIMESTAMP = "startTimestamp";
    public static final Parcelable.Creator<ExercisingActivityBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/activity/ExercisingActivityBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_CONTENTS", "", "BUNDLE_KEY_NAME", "BUNDLE_KEY_LOCATION", "BUNDLE_KEY_CALORIE", "BUNDLE_KEY_MAX_HEART_RATE", "BUNDLE_KEY_MEAN_HEART_RATE", "BUNDLE_KEY_MIN_HEART_RATE", "BUNDLE_KEY_START_TIMESTAMP", "BUNDLE_KEY_END_TIMESTAMP", "BUNDLE_KEY_EXERCISE_TYPE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
    public static final class Creator implements Parcelable.Creator<ExercisingActivityBundleWrapper> {
        public final ExercisingActivityBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ExercisingActivityBundleWrapper(parcel.readString(), parcel.readBundle(ExercisingActivityBundleWrapper.class.getClassLoader()));
        }

        public final ExercisingActivityBundleWrapper[] newArray(int i2) {
            return new ExercisingActivityBundleWrapper[i2];
        }
    }

    public ExercisingActivityBundleWrapper(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        this.id = str;
        this.properties = bundle;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: ne.t} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T extends android.os.Parcelable> com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity createActivity(java.lang.Class<T> r17) {
        /*
            r16 = this;
            r0 = r16
            android.os.Bundle r1 = r0.properties
            r2 = r17
            java.util.ArrayList r1 = r1.getParcelableArrayList("contents", r2)
            if (r1 == 0) goto L_0x005a
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = ne.C1196n.w0(r1, r3)
            r2.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x001b:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0058
            java.lang.Object r3 = r1.next()
            android.os.Parcelable r3 = (android.os.Parcelable) r3
            boolean r4 = r3 instanceof com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper
            if (r4 == 0) goto L_0x0032
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper r3 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper) r3
            com.samsung.android.sdk.moneta.memory.entity.content.Content r3 = r3.toContent()
            goto L_0x003c
        L_0x0032:
            boolean r4 = r3 instanceof com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper
            if (r4 == 0) goto L_0x0040
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper r3 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper) r3
            com.samsung.android.sdk.moneta.memory.entity.content.Content r3 = r3.toContent()
        L_0x003c:
            r2.add(r3)
            goto L_0x001b
        L_0x0040:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unsupported content type: "
            r1.<init>(r2)
            java.lang.Class r2 = r3.getClass()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0058:
            r5 = r2
            goto L_0x005d
        L_0x005a:
            ne.t r2 = ne.C1202t.d
            goto L_0x0058
        L_0x005d:
            com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity r3 = new com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity
            java.lang.String r4 = r0.id
            android.os.Bundle r1 = r0.properties
            java.lang.String r2 = "name"
            java.lang.String r6 = ""
            java.lang.String r1 = r1.getString(r2, r6)
            java.lang.String r2 = "getString(...)"
            kotlin.jvm.internal.j.d(r1, r2)
            android.os.Bundle r7 = r0.properties
            java.lang.Object r7 = r7.getParcelable("location", com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper.class)
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper r7 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper) r7
            if (r7 == 0) goto L_0x007f
            com.samsung.android.sdk.moneta.memory.entity.context.Place r7 = r7.toContext()
            goto L_0x0080
        L_0x007f:
            r7 = 0
        L_0x0080:
            android.os.Bundle r8 = r0.properties
            java.lang.String r9 = "calorie"
            java.lang.Float r8 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getFloatOrNull(r8, r9)
            android.os.Bundle r9 = r0.properties
            java.lang.String r10 = "maxHeartRate"
            java.lang.Float r9 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getFloatOrNull(r9, r10)
            android.os.Bundle r10 = r0.properties
            java.lang.String r11 = "meanHeartRate"
            java.lang.Float r10 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getFloatOrNull(r10, r11)
            android.os.Bundle r11 = r0.properties
            java.lang.String r12 = "minHeartRate"
            java.lang.Float r11 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getFloatOrNull(r11, r12)
            android.os.Bundle r12 = r0.properties
            java.lang.String r13 = "startTimestamp"
            r14 = 0
            long r12 = r12.getLong(r13, r14)
            android.os.Bundle r14 = r0.properties
            java.lang.String r15 = "endTimestamp"
            java.lang.Long r14 = com.samsung.android.sdk.moneta.memory.util.BundleGetExtensionKt.getLongOrNull(r14, r15)
            com.samsung.android.sdk.moneta.memory.entity.content.ExerciseType$Companion r15 = com.samsung.android.sdk.moneta.memory.entity.content.ExerciseType.Companion
            android.os.Bundle r0 = r0.properties
            r17 = r1
            java.lang.String r1 = "exerciseType"
            java.lang.String r0 = r0.getString(r1, r6)
            kotlin.jvm.internal.j.d(r0, r2)
            com.samsung.android.sdk.moneta.memory.entity.content.ExerciseType r15 = r15.fromString(r0)
            r6 = r17
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r15)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ExercisingActivityBundleWrapper.createActivity(java.lang.Class):com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity");
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

    public final ExercisingActivity toActivityV4() {
        return createActivity(ContentBundleWrapper.class);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
