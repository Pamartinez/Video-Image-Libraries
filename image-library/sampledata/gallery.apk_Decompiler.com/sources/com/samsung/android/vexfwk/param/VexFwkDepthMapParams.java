package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.depthmap.DepthDataType;
import com.samsung.android.vexfwk.depthmap.DepthModeType;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0012\u0010\u0013R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010!R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006("}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDepthMapParams;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "depthMode", "Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "depthData", "", "focalLength", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "imageOrientation", "<init>", "(Lcom/samsung/android/vexfwk/depthmap/DepthModeType;Lcom/samsung/android/vexfwk/depthmap/DepthDataType;ILcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "getDepthMode", "()Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "setDepthMode", "(Lcom/samsung/android/vexfwk/depthmap/DepthModeType;)V", "Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "getDepthData", "()Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "setDepthData", "(Lcom/samsung/android/vexfwk/depthmap/DepthDataType;)V", "I", "getFocalLength", "setFocalLength", "(I)V", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "getImageOrientation", "()Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "setImageOrientation", "(Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDepthMapParams implements Parcelable {
    public static final Parcelable.Creator<VexFwkDepthMapParams> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private DepthDataType depthData;
    private DepthModeType depthMode;
    private int focalLength;
    private VexFwkOrientation imageOrientation;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDepthMapParams$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkDepthMapParams;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkDepthMapParams> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkDepthMapParams.CREATOR;
        }

        private Companion() {
            super(new a(22));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkDepthMapParams> {
        public final VexFwkDepthMapParams createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkDepthMapParams(DepthModeType.valueOf(parcel.readString()), DepthDataType.valueOf(parcel.readString()), parcel.readInt(), VexFwkOrientation.valueOf(parcel.readString()));
        }

        public final VexFwkDepthMapParams[] newArray(int i2) {
            return new VexFwkDepthMapParams[i2];
        }
    }

    public VexFwkDepthMapParams(DepthModeType depthModeType, DepthDataType depthDataType, int i2, VexFwkOrientation vexFwkOrientation) {
        j.e(depthModeType, "depthMode");
        j.e(depthDataType, "depthData");
        j.e(vexFwkOrientation, "imageOrientation");
        this.depthMode = depthModeType;
        this.depthData = depthDataType;
        this.focalLength = i2;
        this.imageOrientation = vexFwkOrientation;
    }

    public final int describeContents() {
        return 0;
    }

    public final DepthDataType getDepthData() {
        return this.depthData;
    }

    public final DepthModeType getDepthMode() {
        return this.depthMode;
    }

    public final int getFocalLength() {
        return this.focalLength;
    }

    public final VexFwkOrientation getImageOrientation() {
        return this.imageOrientation;
    }

    public final void setDepthData(DepthDataType depthDataType) {
        j.e(depthDataType, "<set-?>");
        this.depthData = depthDataType;
    }

    public final void setDepthMode(DepthModeType depthModeType) {
        j.e(depthModeType, "<set-?>");
        this.depthMode = depthModeType;
    }

    public final void setFocalLength(int i2) {
        this.focalLength = i2;
    }

    public final void setImageOrientation(VexFwkOrientation vexFwkOrientation) {
        j.e(vexFwkOrientation, "<set-?>");
        this.imageOrientation = vexFwkOrientation;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.depthMode.name());
        parcel.writeString(this.depthData.name());
        parcel.writeInt(this.focalLength);
        parcel.writeString(this.imageOrientation.name());
    }
}
