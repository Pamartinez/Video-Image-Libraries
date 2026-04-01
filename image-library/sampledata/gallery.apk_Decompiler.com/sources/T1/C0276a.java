package t1;

import B3.a;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ge.W0;
import i.C0212a;
import java.util.Arrays;
import w1.r;
import x1.C0333a;

/* renamed from: t1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0276a extends C0333a {
    public static final Parcelable.Creator<C0276a> CREATOR = new a(28);

    /* renamed from: h  reason: collision with root package name */
    public static final C0276a f1921h = new C0276a(0);
    public final int d;
    public final int e;
    public final PendingIntent f;
    public final String g;

    public C0276a(int i2, int i7, PendingIntent pendingIntent, String str) {
        this.d = i2;
        this.e = i7;
        this.f = pendingIntent;
        this.g = str;
    }

    public static String b(int i2) {
        if (i2 == 99) {
            return "UNFINISHED";
        }
        if (i2 == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i2) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i2) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case 15:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case 17:
                        return "SIGN_IN_FAILED";
                    case 18:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case 21:
                        return "API_VERSION_UPDATE_REQUIRED";
                    case 22:
                        return "RESOLUTION_ACTIVITY_NOT_FOUND";
                    case 23:
                        return "API_DISABLED";
                    case 24:
                        return "API_DISABLED_FOR_CONNECTION";
                    default:
                        return C0212a.j(i2, "UNKNOWN_ERROR_CODE(", ")");
                }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0276a)) {
            return false;
        }
        C0276a aVar = (C0276a) obj;
        if (this.e != aVar.e || !r.d(this.f, aVar.f) || !r.d(this.g, aVar.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.e), this.f, this.g});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        w02.v0(b(this.e), "statusCode");
        w02.v0(this.f, EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION);
        w02.v0(this.g, "message");
        return w02.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.Y(2, 4, parcel);
        parcel.writeInt(this.e);
        c.S(parcel, 3, this.f, i2);
        c.T(parcel, 4, this.g);
        c.X(parcel, W6);
    }

    public C0276a(int i2) {
        this(1, i2, (PendingIntent) null, (String) null);
    }

    public C0276a(int i2, PendingIntent pendingIntent) {
        this(1, i2, pendingIntent, (String) null);
    }
}
