package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.Recreator;
import i0.C0215a;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 22\u00020\u0001:\u0003324B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u000b2\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001f\u0010\u001cR \u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0 8\u0002X\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010&\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'R$\u0010)\u001a\u00020#2\u0006\u0010(\u001a\u00020#8G@BX\u000e¢\u0006\f\n\u0004\b)\u0010%\u001a\u0004\b)\u0010*R\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\"\u0010.\u001a\u00020#8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b.\u0010%\u001a\u0004\b/\u0010*\"\u0004\b0\u00101¨\u00065"}, d2 = {"Landroidx/savedstate/SavedStateRegistry;", "", "<init>", "()V", "", "key", "Landroid/os/Bundle;", "consumeRestoredStateForKey", "(Ljava/lang/String;)Landroid/os/Bundle;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "provider", "Lme/x;", "registerSavedStateProvider", "(Ljava/lang/String;Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;)V", "getSavedStateProvider", "(Ljava/lang/String;)Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "Ljava/lang/Class;", "Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "clazz", "runOnNextRecreation", "(Ljava/lang/Class;)V", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "performAttach$savedstate_release", "(Landroidx/lifecycle/Lifecycle;)V", "performAttach", "savedState", "performRestore$savedstate_release", "(Landroid/os/Bundle;)V", "performRestore", "outBundle", "performSave", "Landroidx/arch/core/internal/SafeIterableMap;", "components", "Landroidx/arch/core/internal/SafeIterableMap;", "", "attached", "Z", "restoredState", "Landroid/os/Bundle;", "<set-?>", "isRestored", "()Z", "Landroidx/savedstate/Recreator$SavedStateProvider;", "recreatorProvider", "Landroidx/savedstate/Recreator$SavedStateProvider;", "isAllowingSavingState", "isAllowingSavingState$savedstate_release", "setAllowingSavingState$savedstate_release", "(Z)V", "Companion", "AutoRecreated", "SavedStateProvider", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SavedStateRegistry {
    private static final Companion Companion = new Companion((e) null);
    private boolean attached;
    private final SafeIterableMap<String, SavedStateProvider> components = new SafeIterableMap<>();
    private boolean isAllowingSavingState = true;
    private boolean isRestored;
    private Recreator.SavedStateProvider recreatorProvider;
    private Bundle restoredState;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "", "Landroidx/savedstate/SavedStateRegistryOwner;", "owner", "Lme/x;", "onRecreated", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$Companion;", "", "()V", "SAVED_COMPONENTS_KEY", "", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "", "saveState", "Landroid/os/Bundle;", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SavedStateProvider {
        Bundle saveState();
    }

    /* access modifiers changed from: private */
    public static final void performAttach$lambda$4(SavedStateRegistry savedStateRegistry, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        j.e(savedStateRegistry, "this$0");
        j.e(lifecycleOwner, "<anonymous parameter 0>");
        j.e(event, "event");
        if (event == Lifecycle.Event.ON_START) {
            savedStateRegistry.isAllowingSavingState = true;
        } else if (event == Lifecycle.Event.ON_STOP) {
            savedStateRegistry.isAllowingSavingState = false;
        }
    }

    public final Bundle consumeRestoredStateForKey(String str) {
        Bundle bundle;
        j.e(str, "key");
        if (this.isRestored) {
            Bundle bundle2 = this.restoredState;
            if (bundle2 == null) {
                return null;
            }
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
            if (bundle4 != null && !bundle4.isEmpty()) {
                return bundle;
            }
            this.restoredState = null;
            return bundle;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public final SavedStateProvider getSavedStateProvider(String str) {
        j.e(str, "key");
        Iterator<Map.Entry<String, SavedStateProvider>> it = this.components.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            j.d(next, "components");
            SavedStateProvider savedStateProvider = (SavedStateProvider) next.getValue();
            if (j.a((String) next.getKey(), str)) {
                return savedStateProvider;
            }
        }
        return null;
    }

    public final void performAttach$savedstate_release(Lifecycle lifecycle) {
        j.e(lifecycle, "lifecycle");
        if (!this.attached) {
            lifecycle.addObserver(new C0215a(this));
            this.attached = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already attached.");
    }

    public final void performRestore$savedstate_release(Bundle bundle) {
        Bundle bundle2;
        if (!this.attached) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        } else if (!this.isRestored) {
            if (bundle != null) {
                bundle2 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            } else {
                bundle2 = null;
            }
            this.restoredState = bundle2;
            this.isRestored = true;
        } else {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
    }

    public final void performSave(Bundle bundle) {
        j.e(bundle, "outBundle");
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.restoredState;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = this.components.iteratorWithAdditions();
        j.d(iteratorWithAdditions, "this.components.iteratorWithAdditions()");
        while (iteratorWithAdditions.hasNext()) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            bundle2.putBundle((String) entry.getKey(), ((SavedStateProvider) entry.getValue()).saveState());
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
        }
    }

    public final void registerSavedStateProvider(String str, SavedStateProvider savedStateProvider) {
        j.e(str, "key");
        j.e(savedStateProvider, "provider");
        if (this.components.putIfAbsent(str, savedStateProvider) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final void runOnNextRecreation(Class<? extends AutoRecreated> cls) {
        j.e(cls, "clazz");
        if (this.isAllowingSavingState) {
            Recreator.SavedStateProvider savedStateProvider = this.recreatorProvider;
            if (savedStateProvider == null) {
                savedStateProvider = new Recreator.SavedStateProvider(this);
            }
            this.recreatorProvider = savedStateProvider;
            try {
                cls.getDeclaredConstructor((Class[]) null);
                Recreator.SavedStateProvider savedStateProvider2 = this.recreatorProvider;
                if (savedStateProvider2 != null) {
                    savedStateProvider2.add(cls.getName());
                }
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Class " + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }
}
