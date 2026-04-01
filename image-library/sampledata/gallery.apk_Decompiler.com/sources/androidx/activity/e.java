package androidx.activity;

import Ae.a;
import android.window.OnBackInvokedCallback;
import androidx.activity.OnBackPressedDispatcher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements OnBackInvokedCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f980a;

    public /* synthetic */ e(a aVar) {
        this.f980a = aVar;
    }

    public final void onBackInvoked() {
        OnBackPressedDispatcher.Api33Impl.createOnBackInvokedCallback$lambda$0(this.f980a);
    }
}
