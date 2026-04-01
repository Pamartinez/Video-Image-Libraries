package androidx.activity;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SavedStateRegistry.SavedStateProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ComponentActivity f978a;

    public /* synthetic */ b(ComponentActivity componentActivity) {
        this.f978a = componentActivity;
    }

    public final Bundle saveState() {
        return this.f978a.lambda$new$1();
    }
}
