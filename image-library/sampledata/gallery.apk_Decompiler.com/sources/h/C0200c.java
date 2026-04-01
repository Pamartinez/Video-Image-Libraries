package h;

import android.window.OnBackInvokedCallback;

/* renamed from: h.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0200c implements OnBackInvokedCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Runnable f1732a;

    public /* synthetic */ C0200c(Runnable runnable) {
        this.f1732a = runnable;
    }

    public final void onBackInvoked() {
        this.f1732a.run();
    }
}
