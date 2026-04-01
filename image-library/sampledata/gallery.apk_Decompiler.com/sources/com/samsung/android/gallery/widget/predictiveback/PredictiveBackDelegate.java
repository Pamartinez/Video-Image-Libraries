package com.samsung.android.gallery.widget.predictiveback;

import O3.l;
import Pb.a;
import Pb.b;
import Pb.c;
import android.view.View;
import androidx.activity.BackEventCompat;
import androidx.fragment.app.Fragment;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PredictiveBackDelegate {
    private StringCompat TAG;
    private SimpleOnBackPressedCallback mBackPressedCallback;
    private View mBackupView;
    private Supplier<Blackboard> mBlackboard;
    private Fragment mFragment;
    private boolean mNeedCheckBackupViewTransparent;
    private boolean mNeedEnterTransition;
    private boolean mPendingBackPressed;
    private BooleanSupplier mSupportEnterPredictiveBack;
    private BooleanSupplier mSupportExitPredictiveBack;

    public PredictiveBackDelegate(StringCompat stringCompat, Fragment fragment, Supplier<Blackboard> supplier, BooleanSupplier booleanSupplier, BooleanSupplier booleanSupplier2) {
        SimpleOnBackPressedCallback simpleOnBackPressedCallback;
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            simpleOnBackPressedCallback = new SimpleOnBackPressedCallback().setOnBackStartedListener(new a(this)).setOnBackCancelledListener(new b(this)).setOnBackPressedListener(new c(this));
        } else {
            simpleOnBackPressedCallback = null;
        }
        this.mBackPressedCallback = simpleOnBackPressedCallback;
        this.TAG = stringCompat;
        this.mFragment = fragment;
        this.mBlackboard = supplier;
        this.mSupportExitPredictiveBack = booleanSupplier;
        this.mSupportEnterPredictiveBack = booleanSupplier2;
    }

    private boolean isViewActive() {
        View view = this.mFragment.getView();
        if (!this.mFragment.isAdded() || this.mFragment.isHidden() || view == null || view.getWindowToken() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(BackEventCompat backEventCompat) {
        if (isViewActive() && this.mSupportExitPredictiveBack.getAsBoolean()) {
            this.mPendingBackPressed = true;
            Log.b(this.TAG, "CALLBACK - predictive back started", backEventCompat);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        this.mPendingBackPressed = false;
        if (!isViewActive() && this.mSupportEnterPredictiveBack.getAsBoolean()) {
            Log.b(this.TAG, "CALLBACK - predictive back canceled", new Object[0]);
            ViewUtils.setAlpha(this.mBackupView, 1.0f);
            notifyOnBackPressInvokableStateChanged();
        }
        Optional.ofNullable(this.mBlackboard.get()).ifPresent(new l(15));
    }

    private void notifyOnBackPressInvokableStateChanged() {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            Optional.ofNullable(this.mBlackboard.get()).ifPresent(new l(13));
        }
    }

    /* access modifiers changed from: private */
    public void onBackPressedInternal() {
        this.mPendingBackPressed = false;
        if (!isViewActive() && this.mSupportExitPredictiveBack.getAsBoolean()) {
            Log.b(this.TAG, "CALLBACK - predictive back pressed", new Object[0]);
            Optional.ofNullable(this.mBlackboard.get()).ifPresent(new l(14));
        }
    }

    private void removePredictiveBackPressedCallback() {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            if (this.mPendingBackPressed) {
                onBackPressedInternal();
            }
            OnBackPressedCallbackComposite onBackPressedCallbackComposite = (OnBackPressedCallbackComposite) ObjectDictionary.getTag(this.mFragment.getParentFragmentManager(), "BackPressedCallback");
            if (onBackPressedCallbackComposite != null) {
                onBackPressedCallbackComposite.remove(this.mBackPressedCallback);
            }
        }
    }

    private void rollbackIfBackupViewTransparent() {
        View view = this.mBackupView;
        if (view != null && view.getAlpha() == 0.0f) {
            Log.be(this.TAG, "Defence code for backUpView remains transparently, when predictive back canceled. So, set BackUpView opaque in force.", new Object[0]);
            ViewUtils.setAlpha(this.mBackupView, 1.0f);
        }
    }

    private void setPredictiveBackPressedCallback() {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            OnBackPressedCallbackComposite onBackPressedCallbackComposite = (OnBackPressedCallbackComposite) ObjectDictionary.getTag(this.mFragment.getParentFragmentManager(), "BackPressedCallback");
            if (onBackPressedCallbackComposite == null) {
                onBackPressedCallbackComposite = new OnBackPressedCallbackComposite();
                ObjectDictionary.setTag(this.mFragment.getParentFragmentManager(), "BackPressedCallback", onBackPressedCallbackComposite);
                this.mFragment.getParentFragmentManager().seslSetOnBackPressedCallback(onBackPressedCallbackComposite);
            }
            onBackPressedCallbackComposite.addIfAbsent(this.mBackPressedCallback);
        }
    }

    private void updatePredictiveBackEnabled() {
        BooleanSupplier booleanSupplier;
        String str;
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            if (this.mNeedEnterTransition) {
                booleanSupplier = this.mSupportEnterPredictiveBack;
            } else {
                booleanSupplier = this.mSupportExitPredictiveBack;
            }
            boolean asBoolean = booleanSupplier.getAsBoolean();
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("fragment updatePredictiveBackEnabled support=");
            sb2.append(asBoolean);
            sb2.append(ArcCommonLog.TAG_COMMA);
            if (this.mNeedEnterTransition) {
                str = "enter";
            } else {
                str = "exit";
            }
            sb2.append(str);
            Log.b(stringCompat, sb2.toString(), new Object[0]);
            this.mFragment.seslSetPredictiveBackEnabled(asBoolean, true);
            notifyOnBackPressInvokableStateChanged();
        }
    }

    public void onConfigurationChanged() {
        updatePredictiveBackEnabled();
    }

    public void onCreate() {
        notifyOnBackPressInvokableStateChanged();
    }

    public void onCreateView(View view) {
        this.mBackupView = view;
        this.mNeedCheckBackupViewTransparent = true;
        setPredictiveBackPressedCallback();
    }

    public void onDestroy() {
        this.mBackPressedCallback = null;
    }

    public void onDestroyView() {
        if (this.mNeedCheckBackupViewTransparent) {
            rollbackIfBackupViewTransparent();
        }
        this.mNeedEnterTransition = true;
        removePredictiveBackPressedCallback();
    }

    public void onResume() {
        this.mNeedEnterTransition = false;
        this.mNeedCheckBackupViewTransparent = false;
        updatePredictiveBackEnabled();
    }

    public void onViewCreated() {
        updatePredictiveBackEnabled();
    }
}
