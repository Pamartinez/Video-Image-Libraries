package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefineModes;
import i.C0212a;
import j4.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\n\b\b\u0018\u0000 '2\u00020\u0001:\u0001'B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0012J\u0010\u0010\u0016\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J4\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0012J\u001a\u0010 \u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eHÖ\u0003¢\u0006\u0004\b \u0010!R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b#\u0010\u0014R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b%\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010&\u001a\u0004\b\b\u0010\u0017¨\u0006("}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;", "taskList", "", "targetResolution", "", "isMoireOnlyCropRegion", "<init>", "(Ljava/util/List;IZ)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/util/List;", "component2", "component3", "()Z", "copy", "(Ljava/util/List;IZ)Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getTaskList", "I", "getTargetResolution", "Z", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocumentRefinerParams implements Parcelable {
    public static final Parcelable.Creator<VexFwkDocumentRefinerParams> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean isMoireOnlyCropRegion;
    private final int targetResolution;
    private final List<VexFwkDocRefineModes.Companion.TaskType> taskList;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkDocumentRefinerParams> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkDocumentRefinerParams.CREATOR;
        }

        private Companion() {
            super(new a(24));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkDocumentRefinerParams> {
        public final VexFwkDocumentRefinerParams createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            boolean z = false;
            for (int i2 = 0; i2 != readInt; i2++) {
                arrayList.add(VexFwkDocRefineModes.Companion.TaskType.valueOf(parcel.readString()));
            }
            int readInt2 = parcel.readInt();
            if (parcel.readInt() != 0) {
                z = true;
            }
            return new VexFwkDocumentRefinerParams(arrayList, readInt2, z);
        }

        public final VexFwkDocumentRefinerParams[] newArray(int i2) {
            return new VexFwkDocumentRefinerParams[i2];
        }
    }

    public VexFwkDocumentRefinerParams(List<? extends VexFwkDocRefineModes.Companion.TaskType> list, int i2, boolean z) {
        j.e(list, "taskList");
        this.taskList = list;
        this.targetResolution = i2;
        this.isMoireOnlyCropRegion = z;
    }

    public static /* synthetic */ VexFwkDocumentRefinerParams copy$default(VexFwkDocumentRefinerParams vexFwkDocumentRefinerParams, List<VexFwkDocRefineModes.Companion.TaskType> list, int i2, boolean z, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            list = vexFwkDocumentRefinerParams.taskList;
        }
        if ((i7 & 2) != 0) {
            i2 = vexFwkDocumentRefinerParams.targetResolution;
        }
        if ((i7 & 4) != 0) {
            z = vexFwkDocumentRefinerParams.isMoireOnlyCropRegion;
        }
        return vexFwkDocumentRefinerParams.copy(list, i2, z);
    }

    public final List<VexFwkDocRefineModes.Companion.TaskType> component1() {
        return this.taskList;
    }

    public final int component2() {
        return this.targetResolution;
    }

    public final boolean component3() {
        return this.isMoireOnlyCropRegion;
    }

    public final VexFwkDocumentRefinerParams copy(List<? extends VexFwkDocRefineModes.Companion.TaskType> list, int i2, boolean z) {
        j.e(list, "taskList");
        return new VexFwkDocumentRefinerParams(list, i2, z);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkDocumentRefinerParams)) {
            return false;
        }
        VexFwkDocumentRefinerParams vexFwkDocumentRefinerParams = (VexFwkDocumentRefinerParams) obj;
        if (j.a(this.taskList, vexFwkDocumentRefinerParams.taskList) && this.targetResolution == vexFwkDocumentRefinerParams.targetResolution && this.isMoireOnlyCropRegion == vexFwkDocumentRefinerParams.isMoireOnlyCropRegion) {
            return true;
        }
        return false;
    }

    public final int getTargetResolution() {
        return this.targetResolution;
    }

    public final List<VexFwkDocRefineModes.Companion.TaskType> getTaskList() {
        return this.taskList;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isMoireOnlyCropRegion) + C0212a.b(this.targetResolution, this.taskList.hashCode() * 31, 31);
    }

    public final boolean isMoireOnlyCropRegion() {
        return this.isMoireOnlyCropRegion;
    }

    public String toString() {
        List<VexFwkDocRefineModes.Companion.TaskType> list = this.taskList;
        int i2 = this.targetResolution;
        boolean z = this.isMoireOnlyCropRegion;
        StringBuilder sb2 = new StringBuilder("VexFwkDocumentRefinerParams(taskList=");
        sb2.append(list);
        sb2.append(", targetResolution=");
        sb2.append(i2);
        sb2.append(", isMoireOnlyCropRegion=");
        return N2.j.h(sb2, z, ")");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Iterator j2 = A.a.j(parcel, this.taskList);
        while (j2.hasNext()) {
            parcel.writeString(((VexFwkDocRefineModes.Companion.TaskType) j2.next()).name());
        }
        parcel.writeInt(this.targetResolution);
        parcel.writeInt(this.isMoireOnlyCropRegion ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VexFwkDocumentRefinerParams(List list, int i2, boolean z, int i7, e eVar) {
        this(list, (i7 & 2) != 0 ? 0 : i2, (i7 & 4) != 0 ? true : z);
    }
}
