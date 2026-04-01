package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SavedStateRegistry.SavedStateProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SavedStateHandle f997a;

    public /* synthetic */ a(SavedStateHandle savedStateHandle) {
        this.f997a = savedStateHandle;
    }

    public final Bundle saveState() {
        return SavedStateHandle.savedStateProvider$lambda$0(this.f997a);
    }
}
