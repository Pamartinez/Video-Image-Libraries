package com.samsung.android.vexfwk.param;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J*\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0010J\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u0012R\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b#\u0010\u0014¨\u0006$"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntity;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;", "prop", "", "hierarchy", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;", "component2", "()Ljava/util/List;", "copy", "(Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;Ljava/util/List;)Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntity;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;", "getProp", "Ljava/util/List;", "getHierarchy", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTagEntity implements Parcelable {
    public static final Parcelable.Creator<VexFwkImageTagEntity> CREATOR = new Creator();
    private final List<VexFwkImageTagEntityProp> hierarchy;
    private final VexFwkImageTagEntityProp prop;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkImageTagEntity> {
        public final VexFwkImageTagEntity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            VexFwkImageTagEntityProp createFromParcel = VexFwkImageTagEntityProp.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(VexFwkImageTagEntityProp.CREATOR, parcel, arrayList, i2, 1);
            }
            return new VexFwkImageTagEntity(createFromParcel, arrayList);
        }

        public final VexFwkImageTagEntity[] newArray(int i2) {
            return new VexFwkImageTagEntity[i2];
        }
    }

    public VexFwkImageTagEntity(VexFwkImageTagEntityProp vexFwkImageTagEntityProp, List<VexFwkImageTagEntityProp> list) {
        j.e(vexFwkImageTagEntityProp, "prop");
        j.e(list, "hierarchy");
        this.prop = vexFwkImageTagEntityProp;
        this.hierarchy = list;
    }

    public static /* synthetic */ VexFwkImageTagEntity copy$default(VexFwkImageTagEntity vexFwkImageTagEntity, VexFwkImageTagEntityProp vexFwkImageTagEntityProp, List<VexFwkImageTagEntityProp> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            vexFwkImageTagEntityProp = vexFwkImageTagEntity.prop;
        }
        if ((i2 & 2) != 0) {
            list = vexFwkImageTagEntity.hierarchy;
        }
        return vexFwkImageTagEntity.copy(vexFwkImageTagEntityProp, list);
    }

    public final VexFwkImageTagEntityProp component1() {
        return this.prop;
    }

    public final List<VexFwkImageTagEntityProp> component2() {
        return this.hierarchy;
    }

    public final VexFwkImageTagEntity copy(VexFwkImageTagEntityProp vexFwkImageTagEntityProp, List<VexFwkImageTagEntityProp> list) {
        j.e(vexFwkImageTagEntityProp, "prop");
        j.e(list, "hierarchy");
        return new VexFwkImageTagEntity(vexFwkImageTagEntityProp, list);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkImageTagEntity)) {
            return false;
        }
        VexFwkImageTagEntity vexFwkImageTagEntity = (VexFwkImageTagEntity) obj;
        if (j.a(this.prop, vexFwkImageTagEntity.prop) && j.a(this.hierarchy, vexFwkImageTagEntity.hierarchy)) {
            return true;
        }
        return false;
    }

    public final List<VexFwkImageTagEntityProp> getHierarchy() {
        return this.hierarchy;
    }

    public final VexFwkImageTagEntityProp getProp() {
        return this.prop;
    }

    public int hashCode() {
        return this.hierarchy.hashCode() + (this.prop.hashCode() * 31);
    }

    public String toString() {
        VexFwkImageTagEntityProp vexFwkImageTagEntityProp = this.prop;
        List<VexFwkImageTagEntityProp> list = this.hierarchy;
        return "VexFwkImageTagEntity(prop=" + vexFwkImageTagEntityProp + ", hierarchy=" + list + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        this.prop.writeToParcel(parcel, i2);
        Iterator j2 = a.j(parcel, this.hierarchy);
        while (j2.hasNext()) {
            ((VexFwkImageTagEntityProp) j2.next()).writeToParcel(parcel, i2);
        }
    }
}
