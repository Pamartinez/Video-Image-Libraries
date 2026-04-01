package com.samsung.android.gallery.app.ui.container.factory;

import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import i.C0212a;
import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChildFragmentCache {
    private final Object LOCK = new Object();
    private final ChildFragmentFactory mChildFragmentFactory;
    private final HashMap<String, MvpBaseFragment<?, ?>> mChildFragmentsMap = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FactoryHolder {
        static final HashMap<String, Supplier<ChildFragmentFactory>> map;

        static {
            HashMap<String, Supplier<ChildFragmentFactory>> hashMap = new HashMap<>();
            map = hashMap;
            hashMap.put(ChildFragmentFactory.LIST_NORMAL, new c(0));
            hashMap.put(ChildFragmentFactory.BOTTOM_NORMAL, new c(1));
            hashMap.put(ChildFragmentFactory.BOTTOM_PICKER, new c(2));
        }

        public static ChildFragmentFactory getFactory(String str) {
            return (ChildFragmentFactory) map.get(str).get();
        }
    }

    public ChildFragmentCache(String str) {
        ChildFragmentFactory factory = FactoryHolder.getFactory(str);
        this.mChildFragmentFactory = factory;
        if (factory == null) {
            throw new IllegalArgumentException(C0212a.l("unknown factory type=", str));
        }
    }

    public void clear() {
        synchronized (this.LOCK) {
            this.mChildFragmentsMap.clear();
        }
    }

    public MvpBaseFragment<?, ?> get(ChildFragmentInfo childFragmentInfo, String str) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mChildFragmentsMap.get(str);
        if (mvpBaseFragment != null) {
            return mvpBaseFragment;
        }
        MvpBaseFragment<?, ?> create = this.mChildFragmentFactory.create(childFragmentInfo, str);
        if (create == null) {
            return create;
        }
        synchronized (this.LOCK) {
            this.mChildFragmentsMap.put(str, create);
        }
        return create;
    }

    public HashMap<String, MvpBaseFragment<?, ?>> getChildFragments() {
        return this.mChildFragmentsMap;
    }

    public boolean hasChildFragment(String str) {
        return this.mChildFragmentsMap.containsKey(str);
    }

    public void remove(String str) {
        synchronized (this.LOCK) {
            this.mChildFragmentsMap.remove(str);
        }
    }
}
