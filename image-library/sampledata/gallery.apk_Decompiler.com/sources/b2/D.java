package B2;

import Y1.e;
import Z1.b;
import android.os.Parcel;
import android.os.Parcelable;
import h2.C0207b;
import h2.i;
import k2.r;
import y2.C0359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D implements Parcelable.ClassLoaderCreator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f41a;

    public /* synthetic */ D(int i2) {
        this.f41a = i2;
    }

    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.f41a) {
            case 0:
                return new E(parcel, classLoader);
            case 1:
                return new e(parcel, classLoader);
            case 2:
                return new b(parcel, classLoader);
            case 3:
                return new C0207b(parcel, classLoader);
            case 4:
                return new i(parcel, classLoader);
            case 5:
                return new r(parcel, classLoader);
            default:
                return new C0359c(parcel, classLoader);
        }
    }

    public final Object[] newArray(int i2) {
        switch (this.f41a) {
            case 0:
                return new E[i2];
            case 1:
                return new e[i2];
            case 2:
                return new b[i2];
            case 3:
                return new C0207b[i2];
            case 4:
                return new i[i2];
            case 5:
                return new r[i2];
            default:
                return new C0359c[i2];
        }
    }

    public final Object createFromParcel(Parcel parcel) {
        switch (this.f41a) {
            case 0:
                return new E(parcel, (ClassLoader) null);
            case 1:
                return new e(parcel, (ClassLoader) null);
            case 2:
                return new b(parcel, (ClassLoader) null);
            case 3:
                return new C0207b(parcel, (ClassLoader) null);
            case 4:
                return new i(parcel, (ClassLoader) null);
            case 5:
                return new r(parcel, (ClassLoader) null);
            default:
                return new C0359c(parcel, (ClassLoader) null);
        }
    }
}
