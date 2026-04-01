package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;
import te.C1295a;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u0000 \u00112\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u000ej\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode;", "Landroid/os/Parcelable;", "", "", "value", "<init>", "(Ljava/lang/String;II)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "I", "getValue", "Companion", "OBJECT_DETECTION", "OBJECT_DETECTION_AND_CLASSIFICATION", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkUnifiedDetectorMode implements Parcelable {
    OBJECT_DETECTION(0),
    OBJECT_DETECTION_AND_CLASSIFICATION(1);
    
    public static final Parcelable.Creator<VexFwkUnifiedDetectorMode> CREATOR = null;
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkUnifiedDetectorMode> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkUnifiedDetectorMode.CREATOR;
        }

        private Companion() {
            super(new b(5));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkUnifiedDetectorMode> {
        public final VexFwkUnifiedDetectorMode createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return VexFwkUnifiedDetectorMode.valueOf(parcel.readString());
        }

        public final VexFwkUnifiedDetectorMode[] newArray(int i2) {
            return new VexFwkUnifiedDetectorMode[i2];
        }
    }

    static {
        VexFwkUnifiedDetectorMode[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        CREATOR = new Creator();
    }

    private VexFwkUnifiedDetectorMode(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getValue() {
        return this.value;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(name());
    }
}
