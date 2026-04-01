package t1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ge.W0;
import java.util.Arrays;
import x1.C0333a;

/* renamed from: t1.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0278c extends C0333a {
    public static final Parcelable.Creator<C0278c> CREATOR = new a(29);
    public final String d;
    public final int e;
    public final long f;

    public C0278c(String str, int i2, long j2) {
        this.d = str;
        this.e = i2;
        this.f = j2;
    }

    public final long b() {
        long j2 = this.f;
        if (j2 == -1) {
            return (long) this.e;
        }
        return j2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0278c) {
            C0278c cVar = (C0278c) obj;
            String str = cVar.d;
            String str2 = this.d;
            if (((str2 == null || !str2.equals(str)) && (str2 != null || str != null)) || b() != cVar.b()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, Long.valueOf(b())});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        w02.v0(this.d, "name");
        w02.v0(Long.valueOf(b()), "version");
        return w02.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.T(parcel, 1, this.d);
        c.Y(2, 4, parcel);
        parcel.writeInt(this.e);
        long b = b();
        c.Y(3, 8, parcel);
        parcel.writeLong(b);
        c.X(parcel, W6);
    }

    public C0278c(String str, long j2) {
        this.d = str;
        this.f = j2;
        this.e = -1;
    }
}
