package t1;

import C1.b;
import F1.a;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import w1.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class m extends a implements p {
    public final int b;

    public m(byte[] bArr) {
        super("com.google.android.gms.common.internal.ICertData", 0);
        if (bArr.length == 25) {
            this.b = Arrays.hashCode(bArr);
            return;
        }
        throw new IllegalArgumentException();
    }

    public static byte[] d(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean c(int i2, Parcel parcel, Parcel parcel2) {
        if (i2 == 1) {
            b bVar = new b(e());
            parcel2.writeNoException();
            F1.b.c(parcel2, bVar);
            return true;
        } else if (i2 != 2) {
            return false;
        } else {
            parcel2.writeNoException();
            parcel2.writeInt(this.b);
            return true;
        }
    }

    public abstract byte[] e();

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof p)) {
            try {
                p pVar = (p) obj;
                if (((m) pVar).b == this.b) {
                    return Arrays.equals(e(), (byte[]) b.e(new b(((m) pVar).e())));
                }
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.b;
    }
}
