package com.samsung.android.gallery.app.ui.abstraction.delegate;

import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.IDelegateView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DelegateFactory<T extends AbsDelegate, V extends IDelegateView> {
    ArrayList<T> createDelegateList(V v);
}
