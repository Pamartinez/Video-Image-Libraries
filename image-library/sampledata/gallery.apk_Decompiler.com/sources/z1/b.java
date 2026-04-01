package Z1;

import B2.D;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends AbsSavedState {
    public static final Parcelable.Creator<b> CREATOR = new D(2);
    public boolean d;

    public b(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        if (classLoader == null) {
            b.class.getClassLoader();
        }
        this.d = parcel.readInt() != 1 ? false : true;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.d ? 1 : 0);
    }
}
