package androidx.lifecycle;

import L1.d;
import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001d\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Landroidx/lifecycle/SavedStateHandlesProvider;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistry", "Landroidx/lifecycle/ViewModelStoreOwner;", "viewModelStoreOwner", "<init>", "(Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/ViewModelStoreOwner;)V", "Landroid/os/Bundle;", "saveState", "()Landroid/os/Bundle;", "Lme/x;", "performRestore", "()V", "", "key", "consumeRestoredStateForKey", "(Ljava/lang/String;)Landroid/os/Bundle;", "Landroidx/savedstate/SavedStateRegistry;", "", "restored", "Z", "restoredState", "Landroid/os/Bundle;", "Landroidx/lifecycle/SavedStateHandlesVM;", "viewModel$delegate", "Lme/f;", "getViewModel", "()Landroidx/lifecycle/SavedStateHandlesVM;", "viewModel", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SavedStateHandlesProvider implements SavedStateRegistry.SavedStateProvider {
    private boolean restored;
    private Bundle restoredState;
    private final SavedStateRegistry savedStateRegistry;
    private final f viewModel$delegate;

    public SavedStateHandlesProvider(SavedStateRegistry savedStateRegistry2, ViewModelStoreOwner viewModelStoreOwner) {
        j.e(savedStateRegistry2, "savedStateRegistry");
        j.e(viewModelStoreOwner, "viewModelStoreOwner");
        this.savedStateRegistry = savedStateRegistry2;
        this.viewModel$delegate = d.q(new SavedStateHandlesProvider$viewModel$2(viewModelStoreOwner));
    }

    private final SavedStateHandlesVM getViewModel() {
        return (SavedStateHandlesVM) this.viewModel$delegate.getValue();
    }

    public final Bundle consumeRestoredStateForKey(String str) {
        Bundle bundle;
        j.e(str, "key");
        performRestore();
        Bundle bundle2 = this.restoredState;
        if (bundle2 != null) {
            bundle = bundle2.getBundle(str);
        } else {
            bundle = null;
        }
        Bundle bundle3 = this.restoredState;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.restoredState;
        if (bundle4 != null && bundle4.isEmpty()) {
            this.restoredState = null;
        }
        return bundle;
    }

    public final void performRestore() {
        if (!this.restored) {
            Bundle consumeRestoredStateForKey = this.savedStateRegistry.consumeRestoredStateForKey("androidx.lifecycle.internal.SavedStateHandlesProvider");
            Bundle bundle = new Bundle();
            Bundle bundle2 = this.restoredState;
            if (bundle2 != null) {
                bundle.putAll(bundle2);
            }
            if (consumeRestoredStateForKey != null) {
                bundle.putAll(consumeRestoredStateForKey);
            }
            this.restoredState = bundle;
            this.restored = true;
            getViewModel();
        }
    }

    public Bundle saveState() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.restoredState;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry next : getViewModel().getHandles().entrySet()) {
            String str = (String) next.getKey();
            Bundle saveState = ((SavedStateHandle) next.getValue()).savedStateProvider().saveState();
            if (!j.a(saveState, Bundle.EMPTY)) {
                bundle.putBundle(str, saveState);
            }
        }
        this.restored = false;
        return bundle;
    }
}
