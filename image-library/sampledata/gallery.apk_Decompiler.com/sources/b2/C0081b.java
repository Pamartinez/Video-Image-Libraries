package b2;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import i.C0212a;

/* renamed from: b2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0081b extends View.BaseSavedState {
    public static final Parcelable.Creator<C0081b> CREATOR = new a(16);
    public int d;

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("MaterialCheckBox.SavedState{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" CheckedState=");
        int i2 = this.d;
        if (i2 == 1) {
            str = "checked";
        } else if (i2 != 2) {
            str = "unchecked";
        } else {
            str = "indeterminate";
        }
        return C0212a.p(sb2, str, "}");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeValue(Integer.valueOf(this.d));
    }
}
