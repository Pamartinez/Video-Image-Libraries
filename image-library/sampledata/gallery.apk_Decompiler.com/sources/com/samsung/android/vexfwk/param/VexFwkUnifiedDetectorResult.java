package com.samsung.android.vexfwk.param;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u0017\u0010\u000fJ\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u0011¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorResult;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagInfo;", "tags", "<init>", "(Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/util/List;", "copy", "(Ljava/util/List;)Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorResult;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getTags", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkUnifiedDetectorResult implements Parcelable {
    public static final Parcelable.Creator<VexFwkUnifiedDetectorResult> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<VexFwkUnifiedDetectorTagInfo> tags;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorResult$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorResult;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkUnifiedDetectorResult> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkUnifiedDetectorResult.CREATOR;
        }

        private Companion() {
            super(new b(6));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkUnifiedDetectorResult> {
        public final VexFwkUnifiedDetectorResult createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(VexFwkUnifiedDetectorTagInfo.CREATOR, parcel, arrayList, i2, 1);
            }
            return new VexFwkUnifiedDetectorResult(arrayList);
        }

        public final VexFwkUnifiedDetectorResult[] newArray(int i2) {
            return new VexFwkUnifiedDetectorResult[i2];
        }
    }

    public VexFwkUnifiedDetectorResult(List<VexFwkUnifiedDetectorTagInfo> list) {
        j.e(list, "tags");
        this.tags = list;
    }

    public static /* synthetic */ VexFwkUnifiedDetectorResult copy$default(VexFwkUnifiedDetectorResult vexFwkUnifiedDetectorResult, List<VexFwkUnifiedDetectorTagInfo> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = vexFwkUnifiedDetectorResult.tags;
        }
        return vexFwkUnifiedDetectorResult.copy(list);
    }

    public final List<VexFwkUnifiedDetectorTagInfo> component1() {
        return this.tags;
    }

    public final VexFwkUnifiedDetectorResult copy(List<VexFwkUnifiedDetectorTagInfo> list) {
        j.e(list, "tags");
        return new VexFwkUnifiedDetectorResult(list);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof VexFwkUnifiedDetectorResult) && j.a(this.tags, ((VexFwkUnifiedDetectorResult) obj).tags)) {
            return true;
        }
        return false;
    }

    public final List<VexFwkUnifiedDetectorTagInfo> getTags() {
        return this.tags;
    }

    public int hashCode() {
        return this.tags.hashCode();
    }

    public String toString() {
        List<VexFwkUnifiedDetectorTagInfo> list = this.tags;
        return "VexFwkUnifiedDetectorResult(tags=" + list + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Iterator j2 = a.j(parcel, this.tags);
        while (j2.hasNext()) {
            ((VexFwkUnifiedDetectorTagInfo) j2.next()).writeToParcel(parcel, i2);
        }
    }
}
