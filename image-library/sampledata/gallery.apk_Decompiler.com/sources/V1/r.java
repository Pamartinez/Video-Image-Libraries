package v1;

import E1.e;
import F1.a;
import Fd.C0744a;
import G1.b;
import O1.c;
import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import ge.s1;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import s1.C0270a;
import t1.C0276a;
import u1.g;
import u1.h;
import w1.m;
import w1.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends a implements g, h {

    /* renamed from: i  reason: collision with root package name */
    public static final b f1976i = N1.b.f424a;
    public final Context b;

    /* renamed from: c  reason: collision with root package name */
    public final Handler f1977c;
    public final b d = f1976i;
    public final Set e;
    public final C0744a f;
    public O1.a g;

    /* renamed from: h  reason: collision with root package name */
    public Yd.b f1978h;

    public r(Context context, e eVar, C0744a aVar) {
        attachInterface(this, "com.google.android.gms.signin.internal.ISignInCallbacks");
        this.b = context;
        this.f1977c = eVar;
        this.f = aVar;
        this.e = (Set) aVar.f;
    }

    public final void a(C0276a aVar) {
        this.f1978h.b(aVar);
    }

    public final void b(int i2) {
        Yd.b bVar = this.f1978h;
        k kVar = (k) ((C0298c) bVar.f).f1962j.get((C0296a) bVar.f3915c);
        if (kVar == null) {
            return;
        }
        if (kVar.f1967i) {
            kVar.o(new C0276a(17));
        } else {
            kVar.b(i2);
        }
    }

    public final void onConnected() {
        GoogleSignInAccount googleSignInAccount;
        Parcel obtain;
        Parcel obtain2;
        ReentrantLock reentrantLock;
        O1.a aVar = this.g;
        aVar.getClass();
        try {
            aVar.z.getClass();
            Account account = new Account("<<default account>>", "com.google");
            if ("<<default account>>".equals(account.name)) {
                Context context = aVar.f1339c;
                ReentrantLock reentrantLock2 = C0270a.f1912c;
                w1.r.b(context);
                reentrantLock = C0270a.f1912c;
                reentrantLock.lock();
                if (C0270a.d == null) {
                    C0270a.d = new C0270a(context.getApplicationContext());
                }
                C0270a aVar2 = C0270a.d;
                reentrantLock.unlock();
                String a7 = aVar2.a("defaultGoogleSignInAccount");
                if (!TextUtils.isEmpty(a7)) {
                    String a10 = aVar2.a("googleSignInAccount:" + a7);
                    if (a10 != null) {
                        try {
                            googleSignInAccount = GoogleSignInAccount.b(a10);
                        } catch (JSONException unused) {
                        }
                        Integer num = aVar.B;
                        w1.r.b(num);
                        m mVar = new m(2, account, num.intValue(), googleSignInAccount);
                        c cVar = (c) aVar.p();
                        obtain = Parcel.obtain();
                        obtain.writeInterfaceToken(cVar.f162c);
                        int i2 = E1.b.f163a;
                        obtain.writeInt(1);
                        int W6 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.W(obtain, 20293);
                        com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(1, 4, obtain);
                        obtain.writeInt(1);
                        com.samsung.context.sdk.samsunganalytics.internal.sender.c.S(obtain, 2, mVar, 0);
                        com.samsung.context.sdk.samsunganalytics.internal.sender.c.X(obtain, W6);
                        obtain.writeStrongBinder(this);
                        obtain2 = Parcel.obtain();
                        cVar.b.transact(12, obtain, obtain2, 0);
                        obtain2.readException();
                        obtain.recycle();
                        obtain2.recycle();
                    }
                }
            }
            googleSignInAccount = null;
            Integer num2 = aVar.B;
            w1.r.b(num2);
            m mVar2 = new m(2, account, num2.intValue(), googleSignInAccount);
            c cVar2 = (c) aVar.p();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken(cVar2.f162c);
            int i22 = E1.b.f163a;
            obtain.writeInt(1);
            int W62 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.W(obtain, 20293);
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(1, 4, obtain);
            obtain.writeInt(1);
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.S(obtain, 2, mVar2, 0);
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.X(obtain, W62);
            obtain.writeStrongBinder(this);
            obtain2 = Parcel.obtain();
            cVar2.b.transact(12, obtain, obtain2, 0);
            obtain2.readException();
            obtain.recycle();
            obtain2.recycle();
        } catch (RemoteException e7) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                this.f1977c.post(new s1(5, this, new O1.e(1, new C0276a(8, (PendingIntent) null), (n) null)));
            } catch (RemoteException unused2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e7);
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
