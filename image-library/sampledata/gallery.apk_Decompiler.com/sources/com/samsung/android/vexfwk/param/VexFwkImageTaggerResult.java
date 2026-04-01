package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import j4.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u0017\u0010\u000fJ\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u0011¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerResult;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntity;", "list", "<init>", "(Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/util/List;", "copy", "(Ljava/util/List;)Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerResult;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getList", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTaggerResult implements Parcelable {
    public static final Parcelable.Creator<VexFwkImageTaggerResult> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<VexFwkImageTagEntity> list;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerResult$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerResult;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkImageTaggerResult> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkImageTaggerResult.CREATOR;
        }

        private Companion() {
            super(new a(29));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkImageTaggerResult> {
        public final VexFwkImageTaggerResult createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = A.a.a(VexFwkImageTagEntity.CREATOR, parcel, arrayList, i2, 1);
            }
            return new VexFwkImageTaggerResult(arrayList);
        }

        public final VexFwkImageTaggerResult[] newArray(int i2) {
            return new VexFwkImageTaggerResult[i2];
        }
    }

    public VexFwkImageTaggerResult(List<VexFwkImageTagEntity> list2) {
        j.e(list2, "list");
        this.list = list2;
    }

    public static /* synthetic */ VexFwkImageTaggerResult copy$default(VexFwkImageTaggerResult vexFwkImageTaggerResult, List<VexFwkImageTagEntity> list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list2 = vexFwkImageTaggerResult.list;
        }
        return vexFwkImageTaggerResult.copy(list2);
    }

    public final List<VexFwkImageTagEntity> component1() {
        return this.list;
    }

    public final VexFwkImageTaggerResult copy(List<VexFwkImageTagEntity> list2) {
        j.e(list2, "list");
        return new VexFwkImageTaggerResult(list2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof VexFwkImageTaggerResult) && j.a(this.list, ((VexFwkImageTaggerResult) obj).list)) {
            return true;
        }
        return false;
    }

    public final List<VexFwkImageTagEntity> getList() {
        return this.list;
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public String toString() {
        List<VexFwkImageTagEntity> list2 = this.list;
        return "VexFwkImageTaggerResult(list=" + list2 + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Iterator j2 = A.a.j(parcel, this.list);
        while (j2.hasNext()) {
            ((VexFwkImageTagEntity) j2.next()).writeToParcel(parcel, i2);
        }
    }
}
