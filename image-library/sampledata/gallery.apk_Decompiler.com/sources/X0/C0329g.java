package x0;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import u1.l;

/* renamed from: x0.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0329g extends View.BaseSavedState {
    public static final Parcelable.Creator<C0329g> CREATOR = new l(10);
    public String d;
    public int e;
    public float f;
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public String f2052h;

    /* renamed from: i  reason: collision with root package name */
    public int f2053i;

    /* renamed from: j  reason: collision with root package name */
    public int f2054j;

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.d);
        parcel.writeFloat(this.f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeString(this.f2052h);
        parcel.writeInt(this.f2053i);
        parcel.writeInt(this.f2054j);
    }
}
