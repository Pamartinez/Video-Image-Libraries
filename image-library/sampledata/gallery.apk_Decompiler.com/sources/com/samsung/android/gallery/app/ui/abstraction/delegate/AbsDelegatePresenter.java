package com.samsung.android.gallery.app.ui.abstraction.delegate;

import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.abstraction.delegate.IDelegateView;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.io.Closeable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsDelegatePresenter<V extends IDelegateView<M>, M extends Closeable> extends MvpBasePresenter<V> implements IDelegateGetter {
    protected final String TAG;
    protected final ActionInvoker mActionInvoker;
    protected final M mModel;

    public AbsDelegatePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        ActionInvoker actionInvoker = new ActionInvoker(simpleName);
        this.mActionInvoker = actionInvoker;
        v.attachActionInvoker(actionInvoker);
        this.mModel = v.getModel();
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        ((IDelegateView) this.mView).createGlobalSubscriberList(arrayList);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        ((IDelegateView) this.mView).createSubscriberList(arrayList);
    }

    public <T extends AbsDelegate> T getDelegate(Class<T> cls) {
        return ((IDelegateView) this.mView).getDelegate(cls);
    }

    public abstract void onApplyWindowInsets(View view, WindowInsets windowInsets);
}
