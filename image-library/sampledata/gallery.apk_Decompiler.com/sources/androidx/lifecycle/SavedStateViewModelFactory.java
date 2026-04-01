package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0017\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ/\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J-\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r¢\u0006\u0004\b\u0011\u0010\u0015J'\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016¢\u0006\u0004\b\u0011\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u000bH\u0017¢\u0006\u0004\b\u0019\u0010\u001aR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/lifecycle/SavedStateViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "Landroid/app/Application;", "application", "Landroidx/savedstate/SavedStateRegistryOwner;", "owner", "Landroid/os/Bundle;", "defaultArgs", "<init>", "(Landroid/app/Application;Landroidx/savedstate/SavedStateRegistryOwner;Landroid/os/Bundle;)V", "Landroidx/lifecycle/ViewModel;", "T", "Ljava/lang/Class;", "modelClass", "Landroidx/lifecycle/viewmodel/CreationExtras;", "extras", "create", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "", "key", "(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "viewModel", "Lme/x;", "onRequery", "(Landroidx/lifecycle/ViewModel;)V", "Landroid/app/Application;", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "Landroid/os/Bundle;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {
    private Application application;
    private Bundle defaultArgs;
    private final ViewModelProvider.Factory factory;
    private Lifecycle lifecycle;
    private SavedStateRegistry savedStateRegistry;

    public SavedStateViewModelFactory(Application application2, SavedStateRegistryOwner savedStateRegistryOwner, Bundle bundle) {
        ViewModelProvider.AndroidViewModelFactory androidViewModelFactory;
        j.e(savedStateRegistryOwner, "owner");
        this.savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
        this.lifecycle = savedStateRegistryOwner.getLifecycle();
        this.defaultArgs = bundle;
        this.application = application2;
        if (application2 != null) {
            androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory.Companion.getInstance(application2);
        } else {
            androidViewModelFactory = new ViewModelProvider.AndroidViewModelFactory();
        }
        this.factory = androidViewModelFactory;
    }

    public <T extends ViewModel> T create(Class<T> cls, CreationExtras creationExtras) {
        Constructor<T> constructor;
        j.e(cls, "modelClass");
        j.e(creationExtras, "extras");
        String str = (String) creationExtras.get(ViewModelProvider.NewInstanceFactory.VIEW_MODEL_KEY);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        } else if (creationExtras.get(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY) != null && creationExtras.get(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY) != null) {
            Application application2 = (Application) creationExtras.get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY);
            boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
            if (!isAssignableFrom || application2 == null) {
                constructor = SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE);
            } else {
                constructor = SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE);
            }
            if (constructor == null) {
                return this.factory.create(cls, creationExtras);
            }
            if (!isAssignableFrom || application2 == null) {
                return SavedStateViewModelFactoryKt.newInstance(cls, constructor, SavedStateHandleSupport.createSavedStateHandle(creationExtras));
            }
            return SavedStateViewModelFactoryKt.newInstance(cls, constructor, application2, SavedStateHandleSupport.createSavedStateHandle(creationExtras));
        } else if (this.lifecycle != null) {
            return create(str, cls);
        } else {
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
    }

    public void onRequery(ViewModel viewModel) {
        j.e(viewModel, "viewModel");
        if (this.lifecycle != null) {
            SavedStateRegistry savedStateRegistry2 = this.savedStateRegistry;
            j.b(savedStateRegistry2);
            Lifecycle lifecycle2 = this.lifecycle;
            j.b(lifecycle2);
            LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, savedStateRegistry2, lifecycle2);
        }
    }

    public final <T extends ViewModel> T create(String str, Class<T> cls) {
        Constructor<T> constructor;
        T t;
        Application application2;
        j.e(str, "key");
        j.e(cls, "modelClass");
        Lifecycle lifecycle2 = this.lifecycle;
        if (lifecycle2 != null) {
            boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
            if (!isAssignableFrom || this.application == null) {
                constructor = SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE);
            } else {
                constructor = SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE);
            }
            if (constructor != null) {
                SavedStateRegistry savedStateRegistry2 = this.savedStateRegistry;
                j.b(savedStateRegistry2);
                SavedStateHandleController create = LegacySavedStateHandleController.create(savedStateRegistry2, lifecycle2, str, this.defaultArgs);
                if (!isAssignableFrom || (application2 = this.application) == null) {
                    t = SavedStateViewModelFactoryKt.newInstance(cls, constructor, create.getHandle());
                } else {
                    j.b(application2);
                    t = SavedStateViewModelFactoryKt.newInstance(cls, constructor, application2, create.getHandle());
                }
                t.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", create);
                return t;
            } else if (this.application != null) {
                return this.factory.create(cls);
            } else {
                return ViewModelProvider.NewInstanceFactory.Companion.getInstance().create(cls);
            }
        } else {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        j.e(cls, "modelClass");
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return create(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
