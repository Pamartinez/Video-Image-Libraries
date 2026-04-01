package B2;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.customview.view.AbsSavedState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E extends AbsSavedState {
    public static final Parcelable.Creator<E> CREATOR = new D(0);
    public CharSequence d;
    public boolean e;

    public E(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.e = parcel.readInt() != 1 ? false : true;
    }

    public final String toString() {
        return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.d + "}";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        TextUtils.writeToParcel(this.d, parcel, i2);
        parcel.writeInt(this.e ? 1 : 0);
    }
}
