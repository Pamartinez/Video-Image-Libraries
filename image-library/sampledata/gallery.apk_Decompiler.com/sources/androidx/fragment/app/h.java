package androidx.fragment.app;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements OnContextAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FragmentActivity f990a;

    public /* synthetic */ h(FragmentActivity fragmentActivity) {
        this.f990a = fragmentActivity;
    }

    public final void onContextAvailable(Context context) {
        this.f990a.lambda$init$3(context);
    }
}
