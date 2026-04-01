package w1;

import E1.a;
import F1.b;
import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Scope;
import t1.C0278c;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C0333a {
    public static final Parcelable.Creator<d> CREATOR = new l(9);
    public static final Scope[] r = new Scope[0];
    public static final C0278c[] s = new C0278c[0];
    public final int d;
    public final int e;
    public final int f;
    public String g;

    /* renamed from: h  reason: collision with root package name */
    public IBinder f1998h;

    /* renamed from: i  reason: collision with root package name */
    public Scope[] f1999i;

    /* renamed from: j  reason: collision with root package name */
    public Bundle f2000j;
    public Account k;
    public C0278c[] l;
    public C0278c[] m;
    public final boolean n;

    /* renamed from: o  reason: collision with root package name */
    public final int f2001o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f2002p;
    public final String q;

    public d(int i2, int i7, int i8, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, C0278c[] cVarArr, C0278c[] cVarArr2, boolean z, int i10, boolean z3, String str2) {
        Scope[] scopeArr2;
        Bundle bundle2;
        C0278c[] cVarArr3;
        Account account2;
        Object obj;
        if (scopeArr == null) {
            scopeArr2 = r;
        } else {
            scopeArr2 = scopeArr;
        }
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = bundle;
        }
        C0278c[] cVarArr4 = s;
        if (cVarArr == null) {
            cVarArr3 = cVarArr4;
        } else {
            cVarArr3 = cVarArr;
        }
        cVarArr4 = cVarArr2 != null ? cVarArr2 : cVarArr4;
        this.d = i2;
        this.e = i7;
        this.f = i8;
        if ("com.google.android.gms".equals(str)) {
            this.g = "com.google.android.gms";
        } else {
            this.g = str;
        }
        if (i2 < 2) {
            account2 = null;
            if (iBinder != null) {
                int i11 = C0314a.b;
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface instanceof e) {
                    obj = (e) queryLocalInterface;
                } else {
                    obj = new a(iBinder, "com.google.android.gms.common.internal.IAccountAccessor", 1);
                }
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    C c5 = (C) obj;
                    Parcel a7 = c5.a(c5.c(), 2);
                    Account account3 = (Account) b.a(a7, Account.CREATOR);
                    a7.recycle();
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    account2 = account3;
                } catch (RemoteException unused) {
                    Log.w("AccountAccessor", "Remote account accessor probably died");
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    throw th2;
                }
            }
        } else {
            this.f1998h = iBinder;
            account2 = account;
        }
        this.k = account2;
        this.f1999i = scopeArr2;
        this.f2000j = bundle2;
        this.l = cVarArr3;
        this.m = cVarArr4;
        this.n = z;
        this.f2001o = i10;
        this.f2002p = z3;
        this.q = str2;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        l.a(this, parcel, i2);
    }
}
