package i0;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;

/* renamed from: i0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0215a implements LifecycleEventObserver {
    public final /* synthetic */ SavedStateRegistry d;

    public /* synthetic */ C0215a(SavedStateRegistry savedStateRegistry) {
        this.d = savedStateRegistry;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        SavedStateRegistry.performAttach$lambda$4(this.d, lifecycleOwner, event);
    }
}
