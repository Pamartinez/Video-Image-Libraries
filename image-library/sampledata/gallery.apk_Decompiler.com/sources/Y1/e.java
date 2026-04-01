package Y1;

import B2.D;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends AbsSavedState {
    public static final Parcelable.Creator<e> CREATOR = new D(1);
    public final int d;
    public final int e;
    public final boolean f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f949h;

    public e(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        boolean z = false;
        this.f = parcel.readInt() == 1;
        this.g = parcel.readInt() == 1;
        this.f949h = parcel.readInt() == 1 ? true : z;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f ? 1 : 0);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.f949h ? 1 : 0);
    }

    public e(Parcelable parcelable, BottomSheetBehavior bottomSheetBehavior) {
        super(parcelable);
        this.d = bottomSheetBehavior.f1415O;
        this.e = bottomSheetBehavior.f1423h;
        this.f = bottomSheetBehavior.e;
        this.g = bottomSheetBehavior.L;
        this.f949h = bottomSheetBehavior.f1413M;
    }
}
