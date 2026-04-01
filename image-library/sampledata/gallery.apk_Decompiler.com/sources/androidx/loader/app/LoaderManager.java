package androidx.loader.app;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LoaderManager {
    public static <T extends LifecycleOwner & ViewModelStoreOwner> LoaderManager getInstance(T t) {
        return new LoaderManagerImpl(t, ((ViewModelStoreOwner) t).getViewModelStore());
    }

    @Deprecated
    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract void markForRedelivery();
}
