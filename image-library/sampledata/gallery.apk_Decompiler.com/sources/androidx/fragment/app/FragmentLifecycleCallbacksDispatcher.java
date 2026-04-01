package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001-B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0013J'\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0019\u0010\u0018J'\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u001a\u0010\u0018J/\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u001f\u0010\u0013J\u001d\u0010 \u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b \u0010\u0013J\u001d\u0010!\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b!\u0010\u0013J\u001d\u0010\"\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\"\u0010\u0013J%\u0010$\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b$\u0010\u0018J\u001d\u0010%\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b%\u0010\u0013J\u001d\u0010&\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b&\u0010\u0013J\u001d\u0010'\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b'\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)8\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010,¨\u0006."}, d2 = {"Landroidx/fragment/app/FragmentLifecycleCallbacksDispatcher;", "", "Landroidx/fragment/app/FragmentManager;", "fragmentManager", "<init>", "(Landroidx/fragment/app/FragmentManager;)V", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "cb", "", "recursive", "Lme/x;", "registerFragmentLifecycleCallbacks", "(Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;Z)V", "unregisterFragmentLifecycleCallbacks", "(Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;)V", "Landroidx/fragment/app/Fragment;", "f", "onlyRecursive", "dispatchOnFragmentPreAttached", "(Landroidx/fragment/app/Fragment;Z)V", "dispatchOnFragmentAttached", "Landroid/os/Bundle;", "savedInstanceState", "dispatchOnFragmentPreCreated", "(Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V", "dispatchOnFragmentCreated", "dispatchOnFragmentActivityCreated", "Landroid/view/View;", "v", "dispatchOnFragmentViewCreated", "(Landroidx/fragment/app/Fragment;Landroid/view/View;Landroid/os/Bundle;Z)V", "dispatchOnFragmentStarted", "dispatchOnFragmentResumed", "dispatchOnFragmentPaused", "dispatchOnFragmentStopped", "outState", "dispatchOnFragmentSaveInstanceState", "dispatchOnFragmentViewDestroyed", "dispatchOnFragmentDestroyed", "dispatchOnFragmentDetached", "Landroidx/fragment/app/FragmentManager;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/fragment/app/FragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder;", "lifecycleCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "FragmentLifecycleCallbacksHolder", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FragmentLifecycleCallbacksDispatcher {
    private final FragmentManager fragmentManager;
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> lifecycleCallbacks = new CopyOnWriteArrayList<>();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/fragment/app/FragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder;", "", "callback", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "recursive", "", "(Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;Z)V", "getCallback", "()Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "getRecursive", "()Z", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FragmentLifecycleCallbacksHolder {
        private final FragmentManager.FragmentLifecycleCallbacks callback;
        private final boolean recursive;

        public FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            j.e(fragmentLifecycleCallbacks, Profile.PhoneNumberData.TYPE_CALLBACK);
            this.callback = fragmentLifecycleCallbacks;
            this.recursive = z;
        }

        public final FragmentManager.FragmentLifecycleCallbacks getCallback() {
            return this.callback;
        }

        public final boolean getRecursive() {
            return this.recursive;
        }
    }

    public FragmentLifecycleCallbacksDispatcher(FragmentManager fragmentManager2) {
        j.e(fragmentManager2, "fragmentManager");
        this.fragmentManager = fragmentManager2;
    }

    public final void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentActivityCreated(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentActivityCreated(this.fragmentManager, fragment, bundle);
            }
        }
    }

    public final void dispatchOnFragmentAttached(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Context context = this.fragmentManager.getHost().getContext();
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentAttached(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentAttached(this.fragmentManager, fragment, context);
            }
        }
    }

    public final void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentCreated(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentCreated(this.fragmentManager, fragment, bundle);
            }
        }
    }

    public final void dispatchOnFragmentDestroyed(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentDestroyed(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentDestroyed(this.fragmentManager, fragment);
            }
        }
    }

    public final void dispatchOnFragmentDetached(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentDetached(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentDetached(this.fragmentManager, fragment);
            }
        }
    }

    public final void dispatchOnFragmentPaused(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentPaused(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentPaused(this.fragmentManager, fragment);
            }
        }
    }

    public final void dispatchOnFragmentPreAttached(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Context context = this.fragmentManager.getHost().getContext();
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentPreAttached(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentPreAttached(this.fragmentManager, fragment, context);
            }
        }
    }

    public final void dispatchOnFragmentPreCreated(Fragment fragment, Bundle bundle, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentPreCreated(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentPreCreated(this.fragmentManager, fragment, bundle);
            }
        }
    }

    public final void dispatchOnFragmentResumed(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentResumed(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentResumed(this.fragmentManager, fragment);
            }
        }
    }

    public final void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean z) {
        j.e(fragment, "f");
        j.e(bundle, "outState");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentSaveInstanceState(this.fragmentManager, fragment, bundle);
            }
        }
    }

    public final void dispatchOnFragmentStarted(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentStarted(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentStarted(this.fragmentManager, fragment);
            }
        }
    }

    public final void dispatchOnFragmentStopped(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentStopped(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentStopped(this.fragmentManager, fragment);
            }
        }
    }

    public final void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean z) {
        j.e(fragment, "f");
        j.e(view, "v");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentViewCreated(fragment, view, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentViewCreated(this.fragmentManager, fragment, view, bundle);
            }
        }
    }

    public final void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean z) {
        j.e(fragment, "f");
        Fragment parent = this.fragmentManager.getParent();
        if (parent != null) {
            FragmentManager parentFragmentManager = parent.getParentFragmentManager();
            j.d(parentFragmentManager, "parent.getParentFragmentManager()");
            parentFragmentManager.getLifecycleCallbacksDispatcher().dispatchOnFragmentViewDestroyed(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.lifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.getRecursive()) {
                next.getCallback().onFragmentViewDestroyed(this.fragmentManager, fragment);
            }
        }
    }

    public final void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        j.e(fragmentLifecycleCallbacks, "cb");
        this.lifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(fragmentLifecycleCallbacks, z));
    }

    public final void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        j.e(fragmentLifecycleCallbacks, "cb");
        synchronized (this.lifecycleCallbacks) {
            int size = this.lifecycleCallbacks.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (this.lifecycleCallbacks.get(i2).getCallback() == fragmentLifecycleCallbacks) {
                    this.lifecycleCallbacks.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
    }
}
