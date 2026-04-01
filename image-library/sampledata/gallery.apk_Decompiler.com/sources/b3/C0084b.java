package b3;

import Fd.C0744a;
import android.os.IBinder;
import c3.a;
import c3.c;
import og.k;

/* renamed from: b3.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0084b implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0744a f1050a;

    public C0084b(C0744a aVar) {
        this.f1050a = aVar;
    }

    public final void binderDied() {
        k.m("AsVisionServiceConnection", "binderDied deathRecipient callback", new Object[0]);
        C0744a aVar = this.f1050a;
        ((a) ((c) aVar.f3375h)).f1096a.unlinkToDeath((C0084b) aVar.f3376i, 0);
    }
}
