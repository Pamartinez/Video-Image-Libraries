package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0005¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkFocalLengthParams;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "imageOrientation", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "getImageOrientation", "()Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "setImageOrientation", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkFocalLengthParams implements Parcelable {
    public static final Parcelable.Creator<VexFwkFocalLengthParams> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private VexFwkOrientation imageOrientation;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkFocalLengthParams$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkFocalLengthParams;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkFocalLengthParams> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkFocalLengthParams.CREATOR;
        }

        private Companion() {
            super(new a(26));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkFocalLengthParams> {
        public final VexFwkFocalLengthParams createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkFocalLengthParams(VexFwkOrientation.valueOf(parcel.readString()));
        }

        public final VexFwkFocalLengthParams[] newArray(int i2) {
            return new VexFwkFocalLengthParams[i2];
        }
    }

    public VexFwkFocalLengthParams(VexFwkOrientation vexFwkOrientation) {
        j.e(vexFwkOrientation, "imageOrientation");
        this.imageOrientation = vexFwkOrientation;
    }

    public final int describeContents() {
        return 0;
    }

    public final VexFwkOrientation getImageOrientation() {
        return this.imageOrientation;
    }

    public final void setImageOrientation(VexFwkOrientation vexFwkOrientation) {
        j.e(vexFwkOrientation, "<set-?>");
        this.imageOrientation = vexFwkOrientation;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.imageOrientation.name());
    }
}
