package h2;

import B2.D;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* renamed from: h2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0207b extends AbsSavedState {
    public static final Parcelable.Creator<C0207b> CREATOR = new D(3);
    public boolean d;

    public C0207b(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.d = parcel.readInt() != 1 ? false : true;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.d ? 1 : 0);
    }
}
