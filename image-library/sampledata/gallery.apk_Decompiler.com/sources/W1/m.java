package w1;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends C0333a {
    public static final Parcelable.Creator<m> CREATOR = new l(4);
    public final int d;
    public final Account e;
    public final int f;
    public final GoogleSignInAccount g;

    public m(int i2, Account account, int i7, GoogleSignInAccount googleSignInAccount) {
        this.d = i2;
        this.e = account;
        this.f = i7;
        this.g = googleSignInAccount;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.S(parcel, 2, this.e, i2);
        c.Y(3, 4, parcel);
        parcel.writeInt(this.f);
        c.S(parcel, 4, this.g, i2);
        c.X(parcel, W6);
    }
}
