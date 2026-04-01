package G1;

import Fd.C0744a;
import N1.a;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import o1.C0246a;
import u1.C0283a;
import u1.C0285c;
import u1.g;
import u1.h;
import v1.k;
import w1.j;
import y1.C0356c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends C0246a {

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f282h;

    public /* synthetic */ b(int i2) {
        this.f282h = i2;
    }

    public C0285c L(Context context, Looper looper, C0744a aVar, Object obj, g gVar, h hVar) {
        switch (this.f282h) {
            case 1:
                a aVar2 = (a) obj;
                aVar.getClass();
                Integer num = (Integer) aVar.f3376i;
                Bundle bundle = new Bundle();
                bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable) null);
                if (num != null) {
                    bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
                }
                bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
                bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
                bundle.putString("com.google.android.gms.signin.internal.serverClientId", (String) null);
                bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
                bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
                bundle.putString("com.google.android.gms.signin.internal.hostedDomain", (String) null);
                bundle.putString("com.google.android.gms.signin.internal.logSessionId", (String) null);
                bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
                return new O1.a(context, looper, aVar, bundle, gVar, hVar);
            case 2:
                obj.getClass();
                throw new ClassCastException();
            default:
                return super.L(context, looper, aVar, obj, gVar, hVar);
        }
    }

    public /* synthetic */ C0285c M(Context context, Looper looper, C0744a aVar, Object obj, k kVar, k kVar2) {
        switch (this.f282h) {
            case 0:
                C0283a aVar2 = (C0283a) obj;
                C0744a aVar3 = aVar;
                return new f(context, looper, aVar3, kVar, kVar2);
            case 3:
                return new C0356c(context, looper, aVar, (j) obj, kVar, kVar2);
            default:
                return super.M(context, looper, aVar, obj, kVar, kVar2);
        }
    }
}
