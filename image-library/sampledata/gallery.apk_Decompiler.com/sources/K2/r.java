package k2;

import B2.D;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends AbsSavedState {
    public static final Parcelable.Creator<r> CREATOR = new D(5);
    public Bundle d;

    public r(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.d = parcel.readBundle(classLoader == null ? r.class.getClassLoader() : classLoader);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeBundle(this.d);
    }
}
