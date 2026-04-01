package y2;

import B2.D;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.sidesheet.SideSheetBehavior;

/* renamed from: y2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0359c extends AbsSavedState {
    public static final Parcelable.Creator<C0359c> CREATOR = new D(6);
    public final int d;

    public C0359c(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.d = parcel.readInt();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.d);
    }

    public C0359c(Parcelable parcelable, SideSheetBehavior sideSheetBehavior) {
        super(parcelable);
        this.d = sideSheetBehavior.k;
    }
}
