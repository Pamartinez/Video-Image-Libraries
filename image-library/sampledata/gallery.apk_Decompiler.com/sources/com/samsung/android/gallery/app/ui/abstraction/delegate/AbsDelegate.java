package com.samsung.android.gallery.app.ui.abstraction.delegate;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.abstraction.delegate.IDelegateView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.io.Closeable;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsDelegate<V extends IDelegateView<M>, M extends Closeable> implements IDelegateGetter {
    /* access modifiers changed from: protected */
    public final String TAG;
    /* access modifiers changed from: protected */
    public final ActionInvoker mActionInvoker;
    /* access modifiers changed from: protected */
    public final Blackboard mBlackboard;
    /* access modifiers changed from: protected */
    public final M mModel;
    /* access modifiers changed from: protected */
    public final V mView;

    public AbsDelegate(V v) {
        Blackboard blackboard;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        ActionInvoker actionInvoker = new ActionInvoker(simpleName);
        this.mActionInvoker = actionInvoker;
        this.mView = v;
        if (v != null) {
            blackboard = v.getBlackboard();
        } else {
            blackboard = null;
        }
        this.mBlackboard = blackboard;
        this.mModel = v.getModel();
        setActionInvokeListener(actionInvoker);
        v.getActionInvoker().attach(actionInvoker);
    }

    public Context getContext() {
        Context context = this.mView.getContext();
        if (context == null) {
            return BlackboardUtils.readActivity(this.mBlackboard);
        }
        return context;
    }

    public <T extends AbsDelegate> T getDelegate(Class<T> cls) {
        return this.mView.getDelegate(cls);
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return false;
    }

    public void onApplyWindowInsets() {
    }

    public void onDestroy() {
    }

    public void onDetach() {
    }

    public void onEnterTransitionEnd() {
    }

    public void onEnterTransitionStart() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onReturnTransitionEnd() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }

    public void onAttach(Context context) {
    }

    public void onBindView(View view) {
    }

    public void onCreate(Bundle bundle) {
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
    }

    public void onDump(PrintWriter printWriter, String str) {
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
    }
}
