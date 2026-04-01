package androidx.loader.app;

import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LoaderManagerImpl extends LoaderManager {
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LoaderViewModel extends ViewModel {
        private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() {
            public <T extends ViewModel> T create(Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        private boolean mCreatingLoader = false;
        private SparseArrayCompat<Object> mLoaders = new SparseArrayCompat<>();

        public static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(LoaderViewModel.class);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                if (this.mLoaders.size() > 0) {
                    if (this.mLoaders.valueAt(0) == null) {
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(this.mLoaders.keyAt(0));
                        printWriter.print(": ");
                        throw null;
                    }
                    throw new ClassCastException();
                }
            }
        }

        public void markForRedelivery() {
            if (this.mLoaders.size() > 0) {
                this.mLoaders.valueAt(0).getClass();
                throw new ClassCastException();
            }
        }

        public void onCleared() {
            super.onCleared();
            if (this.mLoaders.size() <= 0) {
                this.mLoaders.clear();
            } else {
                this.mLoaders.valueAt(0).getClass();
                throw new ClassCastException();
            }
        }
    }

    public LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }

    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("LoaderManager{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, sb2);
        sb2.append("}}");
        return sb2.toString();
    }
}
