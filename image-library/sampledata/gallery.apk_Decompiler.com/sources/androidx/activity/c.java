package androidx.activity;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements OnContextAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ComponentActivity f979a;

    public /* synthetic */ c(ComponentActivity componentActivity) {
        this.f979a = componentActivity;
    }

    public final void onContextAvailable(Context context) {
        this.f979a.lambda$new$2(context);
    }
}
