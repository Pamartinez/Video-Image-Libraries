package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefineModes;
import i.C0212a;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\n\b\b\u0018\u0000 .2\u00020\u0001:\u0001.BM\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001aJ\u0010\u0010\u001d\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001aJX\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\"\u001a\u00020!HÖ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b$\u0010\u0015J\u001a\u0010'\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u0015R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b,\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010-\u001a\u0004\b\u0007\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010-\u001a\u0004\b\b\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010-\u001a\u0004\b\t\u0010\u001aR\u0017\u0010\n\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010-\u001a\u0004\b\n\u0010\u001aR\u0017\u0010\u000b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000b\u0010-\u001a\u0004\b\u000b\u0010\u001a¨\u0006/"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult;", "Landroid/os/Parcelable;", "", "taskOrder", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;", "taskType", "", "isSuccess", "isCanceled", "isFingerExist", "isDogEarExist", "isMoireExist", "<init>", "(ILcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;ZZZZZ)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;", "component3", "()Z", "component4", "component5", "component6", "component7", "copy", "(ILcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;ZZZZZ)Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getTaskOrder", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;", "getTaskType", "Z", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocumentRefinerResult implements Parcelable {
    public static final Parcelable.Creator<VexFwkDocumentRefinerResult> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean isCanceled;
    private final boolean isDogEarExist;
    private final boolean isFingerExist;
    private final boolean isMoireExist;
    private final boolean isSuccess;
    private final int taskOrder;
    private final VexFwkDocRefineModes.Companion.TaskType taskType;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkDocumentRefinerResult> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkDocumentRefinerResult.CREATOR;
        }

        private Companion() {
            super(new a(25));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkDocumentRefinerResult> {
        public final VexFwkDocumentRefinerResult createFromParcel(Parcel parcel) {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            VexFwkDocRefineModes.Companion.TaskType valueOf = parcel.readInt() == 0 ? null : VexFwkDocRefineModes.Companion.TaskType.valueOf(parcel.readString());
            boolean z10 = false;
            boolean z11 = true;
            if (parcel.readInt() != 0) {
                z = false;
                z10 = true;
            } else {
                z = false;
            }
            if (parcel.readInt() != 0) {
                z3 = true;
            } else {
                z3 = true;
                z11 = z;
            }
            if (parcel.readInt() != 0) {
                z7 = z3;
            } else {
                z7 = z3;
                z3 = z;
            }
            if (parcel.readInt() != 0) {
                z9 = z7;
            } else {
                z9 = z7;
                z7 = z;
            }
            if (parcel.readInt() == 0) {
                z9 = z;
            }
            return new VexFwkDocumentRefinerResult(readInt, valueOf, z10, z11, z3, z7, z9);
        }

        public final VexFwkDocumentRefinerResult[] newArray(int i2) {
            return new VexFwkDocumentRefinerResult[i2];
        }
    }

    public VexFwkDocumentRefinerResult(int i2, VexFwkDocRefineModes.Companion.TaskType taskType2, boolean z, boolean z3, boolean z7, boolean z9, boolean z10) {
        this.taskOrder = i2;
        this.taskType = taskType2;
        this.isSuccess = z;
        this.isCanceled = z3;
        this.isFingerExist = z7;
        this.isDogEarExist = z9;
        this.isMoireExist = z10;
    }

    public static /* synthetic */ VexFwkDocumentRefinerResult copy$default(VexFwkDocumentRefinerResult vexFwkDocumentRefinerResult, int i2, VexFwkDocRefineModes.Companion.TaskType taskType2, boolean z, boolean z3, boolean z7, boolean z9, boolean z10, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = vexFwkDocumentRefinerResult.taskOrder;
        }
        if ((i7 & 2) != 0) {
            taskType2 = vexFwkDocumentRefinerResult.taskType;
        }
        if ((i7 & 4) != 0) {
            z = vexFwkDocumentRefinerResult.isSuccess;
        }
        if ((i7 & 8) != 0) {
            z3 = vexFwkDocumentRefinerResult.isCanceled;
        }
        if ((i7 & 16) != 0) {
            z7 = vexFwkDocumentRefinerResult.isFingerExist;
        }
        if ((i7 & 32) != 0) {
            z9 = vexFwkDocumentRefinerResult.isDogEarExist;
        }
        if ((i7 & 64) != 0) {
            z10 = vexFwkDocumentRefinerResult.isMoireExist;
        }
        boolean z11 = z9;
        boolean z12 = z10;
        boolean z13 = z3;
        boolean z14 = z7;
        return vexFwkDocumentRefinerResult.copy(i2, taskType2, z, z13, z14, z11, z12);
    }

    public final int component1() {
        return this.taskOrder;
    }

    public final VexFwkDocRefineModes.Companion.TaskType component2() {
        return this.taskType;
    }

    public final boolean component3() {
        return this.isSuccess;
    }

    public final boolean component4() {
        return this.isCanceled;
    }

    public final boolean component5() {
        return this.isFingerExist;
    }

    public final boolean component6() {
        return this.isDogEarExist;
    }

    public final boolean component7() {
        return this.isMoireExist;
    }

    public final VexFwkDocumentRefinerResult copy(int i2, VexFwkDocRefineModes.Companion.TaskType taskType2, boolean z, boolean z3, boolean z7, boolean z9, boolean z10) {
        return new VexFwkDocumentRefinerResult(i2, taskType2, z, z3, z7, z9, z10);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkDocumentRefinerResult)) {
            return false;
        }
        VexFwkDocumentRefinerResult vexFwkDocumentRefinerResult = (VexFwkDocumentRefinerResult) obj;
        if (this.taskOrder == vexFwkDocumentRefinerResult.taskOrder && this.taskType == vexFwkDocumentRefinerResult.taskType && this.isSuccess == vexFwkDocumentRefinerResult.isSuccess && this.isCanceled == vexFwkDocumentRefinerResult.isCanceled && this.isFingerExist == vexFwkDocumentRefinerResult.isFingerExist && this.isDogEarExist == vexFwkDocumentRefinerResult.isDogEarExist && this.isMoireExist == vexFwkDocumentRefinerResult.isMoireExist) {
            return true;
        }
        return false;
    }

    public final int getTaskOrder() {
        return this.taskOrder;
    }

    public final VexFwkDocRefineModes.Companion.TaskType getTaskType() {
        return this.taskType;
    }

    public int hashCode() {
        int i2;
        int hashCode = Integer.hashCode(this.taskOrder) * 31;
        VexFwkDocRefineModes.Companion.TaskType taskType2 = this.taskType;
        if (taskType2 == null) {
            i2 = 0;
        } else {
            i2 = taskType2.hashCode();
        }
        return Boolean.hashCode(this.isMoireExist) + C0212a.e(C0212a.e(C0212a.e(C0212a.e((hashCode + i2) * 31, 31, this.isSuccess), 31, this.isCanceled), 31, this.isFingerExist), 31, this.isDogEarExist);
    }

    public final boolean isCanceled() {
        return this.isCanceled;
    }

    public final boolean isDogEarExist() {
        return this.isDogEarExist;
    }

    public final boolean isFingerExist() {
        return this.isFingerExist;
    }

    public final boolean isMoireExist() {
        return this.isMoireExist;
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    public String toString() {
        int i2 = this.taskOrder;
        VexFwkDocRefineModes.Companion.TaskType taskType2 = this.taskType;
        boolean z = this.isSuccess;
        boolean z3 = this.isCanceled;
        boolean z7 = this.isFingerExist;
        boolean z9 = this.isDogEarExist;
        boolean z10 = this.isMoireExist;
        StringBuilder sb2 = new StringBuilder("VexFwkDocumentRefinerResult(taskOrder=");
        sb2.append(i2);
        sb2.append(", taskType=");
        sb2.append(taskType2);
        sb2.append(", isSuccess=");
        sb2.append(z);
        sb2.append(", isCanceled=");
        sb2.append(z3);
        sb2.append(", isFingerExist=");
        sb2.append(z7);
        sb2.append(", isDogEarExist=");
        sb2.append(z9);
        sb2.append(", isMoireExist=");
        return N2.j.h(sb2, z10, ")");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeInt(this.taskOrder);
        VexFwkDocRefineModes.Companion.TaskType taskType2 = this.taskType;
        if (taskType2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(taskType2.name());
        }
        parcel.writeInt(this.isSuccess ? 1 : 0);
        parcel.writeInt(this.isCanceled ? 1 : 0);
        parcel.writeInt(this.isFingerExist ? 1 : 0);
        parcel.writeInt(this.isDogEarExist ? 1 : 0);
        parcel.writeInt(this.isMoireExist ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VexFwkDocumentRefinerResult(int i2, VexFwkDocRefineModes.Companion.TaskType taskType2, boolean z, boolean z3, boolean z7, boolean z9, boolean z10, int i7, e eVar) {
        this((i7 & 1) != 0 ? 0 : i2, taskType2, (i7 & 4) != 0 ? false : z, (i7 & 8) != 0 ? false : z3, (i7 & 16) != 0 ? false : z7, (i7 & 32) != 0 ? false : z9, (i7 & 64) != 0 ? false : z10);
    }
}
