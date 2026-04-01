package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ge.W0;
import java.util.Arrays;
import t1.C0276a;
import u1.l;
import w1.r;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Status extends C0333a implements ReflectedParcelable {
    public static final Parcelable.Creator<Status> CREATOR = new l(1);
    public final int d;
    public final String e;
    public final PendingIntent f;
    public final C0276a g;

    public Status(int i2, String str, PendingIntent pendingIntent, C0276a aVar) {
        this.d = i2;
        this.e = str;
        this.f = pendingIntent;
        this.g = aVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.d != status.d || !r.d(this.e, status.e) || !r.d(this.f, status.f) || !r.d(this.g, status.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.d), this.e, this.f, this.g});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        String str = this.e;
        if (str == null) {
            int i2 = this.d;
            switch (i2) {
                case -1:
                    str = "SUCCESS_CACHE";
                    break;
                case 0:
                    str = "SUCCESS";
                    break;
                case 2:
                    str = "SERVICE_VERSION_UPDATE_REQUIRED";
                    break;
                case 3:
                    str = "SERVICE_DISABLED";
                    break;
                case 4:
                    str = "SIGN_IN_REQUIRED";
                    break;
                case 5:
                    str = "INVALID_ACCOUNT";
                    break;
                case 6:
                    str = "RESOLUTION_REQUIRED";
                    break;
                case 7:
                    str = "NETWORK_ERROR";
                    break;
                case 8:
                    str = "INTERNAL_ERROR";
                    break;
                case 10:
                    str = "DEVELOPER_ERROR";
                    break;
                case 13:
                    str = "ERROR";
                    break;
                case 14:
                    str = "INTERRUPTED";
                    break;
                case 15:
                    str = "TIMEOUT";
                    break;
                case 16:
                    str = "CANCELED";
                    break;
                case 17:
                    str = "API_NOT_CONNECTED";
                    break;
                case 18:
                    str = "DEAD_CLIENT";
                    break;
                case 19:
                    str = "REMOTE_EXCEPTION";
                    break;
                case 20:
                    str = "CONNECTION_SUSPENDED_DURING_CALL";
                    break;
                case 21:
                    str = "RECONNECTION_TIMED_OUT_DURING_UPDATE";
                    break;
                case 22:
                    str = "RECONNECTION_TIMED_OUT";
                    break;
                default:
                    str = C0086a.i(i2, "unknown status code: ");
                    break;
            }
        }
        w02.v0(str, "statusCode");
        w02.v0(this.f, EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION);
        return w02.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.T(parcel, 2, this.e);
        c.S(parcel, 3, this.f, i2);
        c.S(parcel, 4, this.g, i2);
        c.X(parcel, W6);
    }
}
