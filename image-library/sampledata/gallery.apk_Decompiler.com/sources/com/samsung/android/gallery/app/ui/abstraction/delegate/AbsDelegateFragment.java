package com.samsung.android.gallery.app.ui.abstraction.delegate;

import Fb.k;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegatePresenter;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.abstraction.delegate.IDelegateView;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.sum.core.Def;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsDelegateFragment<V extends IDelegateView, P extends AbsDelegatePresenter, M extends Closeable, C extends DelegateComposite> extends MvpBaseFragment<V, P> implements IDelegateView<M> {
    protected final ActionInvoker mActionInvoker = new ActionInvoker(this.TAG.toString());
    protected final C mDelegateComposite = createDelegateComposite();
    private boolean mDensityChange = false;
    private boolean mDualScreenChanged = false;
    private int mForceTransitionEndTimerKey = Timer.getTimerId();
    private boolean mIsFirstCreated = true;
    protected final M mModel = createModel();
    private boolean mOrientationChange = false;
    private boolean mResolutionChange = false;
    private boolean mTransitionEndCalled = false;
    private boolean mViewCreated = false;
    protected WindowInsets mWindowInsets;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(int i2) {
        if (!this.mTransitionEndCalled) {
            Log.w(this.TAG, "call onEnterTransitionEnd by timer");
            onEnterTransitionEndV2();
        }
    }

    public abstract C createDelegateComposite();

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        this.mDelegateComposite.createGlobalSubscriberList(arrayList);
    }

    public abstract M createModel();

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        this.mDelegateComposite.createSubscriberList(arrayList);
    }

    public final ActionInvoker getActionInvoker() {
        return this.mActionInvoker;
    }

    public <T extends AbsDelegate> T getDelegate(Class<T> cls) {
        return this.mDelegateComposite.getDelegate(cls);
    }

    public final M getModel() {
        return this.mModel;
    }

    public void handleDensityChange(int i2) {
        this.mDensityChange = true;
    }

    public void handleDualScreenChanged() {
        this.mDualScreenChanged = true;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        this.mOrientationChange = true;
    }

    public void handleResolutionChange(int i2) {
        this.mResolutionChange = true;
    }

    public boolean isConfigurationChanged() {
        if (this.mOrientationChange || this.mResolutionChange || this.mDensityChange) {
            return true;
        }
        return false;
    }

    public abstract void onApplyWindowInsetModel(View view, WindowInsets windowInsets, M m);

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (windowInsets != null && !windowInsets.equals(this.mWindowInsets)) {
            this.mWindowInsets = windowInsets;
            onApplyWindowInsetModel(view, windowInsets, this.mModel);
            this.mDelegateComposite.onApplyWindowInsets();
            ((AbsDelegatePresenter) this.mPresenter).onApplyWindowInsets(view, windowInsets);
        }
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mDelegateComposite.onAttach(context);
    }

    public abstract void onBindModel(M m);

    public void onBindView(View view) {
        onBindModel(this.mModel);
        this.mDelegateComposite.onBindView(view);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mViewCreated) {
            this.mDelegateComposite.onConfigurationChanged(configuration, this.mOrientationChange, this.mResolutionChange, this.mDensityChange, this.mDualScreenChanged);
        }
        this.mOrientationChange = false;
        this.mResolutionChange = false;
        this.mDensityChange = false;
        this.mDualScreenChanged = false;
    }

    public void onCreate(Bundle bundle) {
        this.mDelegateComposite.onCreate(bundle);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        onCreateViewModel(this.mModel);
        this.mDelegateComposite.onCreateView(layoutInflater, viewGroup, bundle);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public abstract void onCreateViewModel(M m);

    public void onDestroy() {
        super.onDestroy();
        this.mDelegateComposite.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mViewCreated = false;
    }

    public void onDetach() {
        this.mDelegateComposite.onDetach();
        try {
            this.mModel.close();
        } catch (IOException e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "model Close exception : " + e.getMessage());
        }
        super.onDetach();
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        C c5 = this.mDelegateComposite;
        c5.onDump(printWriter, str + " + ");
    }

    public void onEnterTransitionEndV2() {
        Timer.stopTimer(this.mForceTransitionEndTimerKey);
        if (this.mTransitionEndCalled || isDestroyed()) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onEnterTransitionEndV2() ignored" + this.mTransitionEndCalled);
            return;
        }
        this.mTransitionEndCalled = true;
        this.mDelegateComposite.onEnterTransitionEnd();
    }

    public void onEnterTransitionStartV2() {
        this.mDelegateComposite.onEnterTransitionStart();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        return super.onHandleEvent(eventMessage) | this.mDelegateComposite.onHandleEvent(eventMessage);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.mDelegateComposite.onKeyDown(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return this.mDelegateComposite.onKeyUp(i2, keyEvent);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.mDelegateComposite.onMultiWindowModeChanged(z);
    }

    public void onPause() {
        super.onPause();
        this.mDelegateComposite.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mDelegateComposite.onResume();
    }

    public void onReturnTransitionEndV2() {
        this.mDelegateComposite.onReturnTransitionEnd();
    }

    public void onStart() {
        super.onStart();
        this.mDelegateComposite.onStart();
    }

    public void onStop() {
        super.onStop();
        this.mDelegateComposite.onStop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mDelegateComposite.onViewCreated(view, bundle);
        this.mViewCreated = true;
        if (!this.mIsFirstCreated && isConfigurationChanged()) {
            onConfigurationChanged(getResources().getConfiguration());
        }
        this.mIsFirstCreated = false;
        Timer.startTimer(this.mForceTransitionEndTimerKey, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, new k(4, this));
    }
}
