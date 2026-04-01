package C1;

import A0.l;
import K1.j;
import L1.d;
import L1.g;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Bundle f103a;
    public final /* synthetic */ j b;

    public c(j jVar, Bundle bundle) {
        this.b = jVar;
        this.f103a = bundle;
    }

    public final int a() {
        return 1;
    }

    public final void b() {
        l lVar = this.b.f383a;
        Bundle bundle = this.f103a;
        ViewGroup viewGroup = (ViewGroup) lVar.e;
        g gVar = (g) lVar.f;
        try {
            Bundle bundle2 = new Bundle();
            d.B(bundle, bundle2);
            Parcel c5 = gVar.c();
            H1.d.c(c5, bundle2);
            gVar.d(c5, 2);
            d.B(bundle2, bundle);
            Parcel b5 = gVar.b(gVar.c(), 8);
            a d = b.d(b5.readStrongBinder());
            b5.recycle();
            lVar.g = (View) b.e(d);
            viewGroup.removeAllViews();
            viewGroup.addView((View) lVar.g);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
