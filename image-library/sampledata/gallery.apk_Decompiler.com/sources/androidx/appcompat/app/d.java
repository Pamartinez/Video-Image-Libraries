package androidx.appcompat.app;

import android.window.OnBackInvokedCallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements OnBackInvokedCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatDelegateImpl f984a;

    public /* synthetic */ d(AppCompatDelegateImpl appCompatDelegateImpl) {
        this.f984a = appCompatDelegateImpl;
    }

    public final void onBackInvoked() {
        this.f984a.onBackPressed();
    }
}
