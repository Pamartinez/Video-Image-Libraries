package com.samsung.android.gallery.app.ui.abstraction.delegate;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.io.Closeable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IDelegateView<M extends Closeable> extends IMvpBaseView, IDelegateGetter {
    void attachActionInvoker(ActionInvoker actionInvoker) {
        actionInvoker.attach(getActionInvoker());
    }

    void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList);

    void createSubscriberList(ArrayList<SubscriberInfo> arrayList);

    ActionInvoker getActionInvoker();

    M getModel();
}
