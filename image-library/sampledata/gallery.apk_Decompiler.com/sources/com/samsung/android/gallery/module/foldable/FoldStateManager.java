package com.samsung.android.gallery.module.foldable;

import A.a;
import F3.C0395a;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import androidx.core.util.Consumer;
import androidx.window.java.layout.WindowInfoTrackerCallbackAdapter;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import j.C0217b;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import k9.C0696a;
import k9.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FoldStateManager {
    /* access modifiers changed from: private */
    public final Activity mActivity;
    private WindowInfoTrackerCallbackAdapter mCallbackAdapter;
    private final ComponentCallbacks mComponentCallbacks = new ComponentCallbacks() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onConfigurationChanged$0() {
            if (FoldStateManager.this.mActivity != null) {
                FoldStateManager.this.replaceWindowLayoutInfoListener();
            }
            Log.d("FoldStateManager", "onConfigurationChanged " + FoldStateManager.this);
        }

        public void onConfigurationChanged(Configuration configuration) {
            FoldStateManager foldStateManager = FoldStateManager.this;
            foldStateManager.notifyIfScreenChanged(FoldUtils.getScreenType(foldStateManager.mActivity, configuration));
            ThreadUtil.postOnUiThreadDelayed(new a(this), 300);
        }

        public void onLowMemory() {
        }
    };
    private Rect mDisplayBound;
    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() {
        private int mDisplayId = -1;

        public void onDisplayChanged(int i2) {
            int i7 = this.mDisplayId;
            if (i7 != i2) {
                Log.d("FoldStateManager", "onDisplayChanged", Integer.valueOf(i7), Integer.valueOf(i2));
                this.mDisplayId = i2;
                FoldStateManager foldStateManager = FoldStateManager.this;
                foldStateManager.notifyIfScreenChanged(FoldUtils.getScreenType(foldStateManager.mActivity));
            }
        }

        public void onDisplayAdded(int i2) {
        }

        public void onDisplayRemoved(int i2) {
        }
    };
    private DisplayManagerCompat mDisplayManager;
    protected final ArrayList<FoldChangedListener> mFoldChangedListeners = new ArrayList<>();
    private FoldingFeature mFoldingFeature;
    protected Boolean mIsTableMode;
    protected final Consumer<WindowLayoutInfo> mOnLayoutStateChanged = new C0696a(0, this);
    protected boolean mPendingResume = false;
    protected FoldableScreen mScreen = FoldableScreen.NONE;

    public FoldStateManager(Activity activity) {
        this.mActivity = activity;
        if (activity == null) {
            throw new IllegalStateException("FoldStateManager failed by null activity");
        }
    }

    private boolean checkAndGetTableMode() {
        String str;
        if (this.mFoldingFeature == null || this.mActivity.isInMultiWindowMode()) {
            if (this.mFoldingFeature == null) {
                str = "null feature";
            } else {
                str = "multi window";
            }
            Log.d("FoldStateManager", "check table mode skip", str);
            return false;
        }
        FoldingFeature.State state = this.mFoldingFeature.getState();
        if (!FoldableScreen.MAIN.equals(this.mScreen) || !state.equals(FoldingFeature.State.HALF_OPENED) || !isHorizontalFoldingLine(this.mFoldingFeature)) {
            return false;
        }
        return true;
    }

    private Context getContext() {
        return this.mActivity;
    }

    public static FoldStateManager getInstance(Blackboard blackboard) {
        try {
            return (FoldStateManager) blackboard.computeIfAbsent("data://FoldStateManager", new C0395a(blackboard, 2));
        } catch (Exception e) {
            a.s(e, new StringBuilder("getInstance failed e="), "FoldStateManager");
            return null;
        }
    }

    private String getState() {
        FoldingFeature foldingFeature = this.mFoldingFeature;
        if (foldingFeature != null) {
            return foldingFeature.getState().toString();
        }
        return C2paManifestList.UNKNOWN_VALUE;
    }

    private void initDisplayManager() {
        try {
            if (this.mDisplayManager == null) {
                DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
                this.mDisplayManager = displayManagerCompat;
                Optional.ofNullable(displayManagerCompat).ifPresent(new b(this, 1));
            }
        } catch (Exception e) {
            Log.rme("FoldStateManager", "initDisplayManager failed e=" + e.getMessage());
        }
    }

    private boolean isHorizontalFoldingLine(FoldingFeature foldingFeature) {
        if (foldingFeature.getOrientation() == FoldingFeature.Orientation.HORIZONTAL) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FoldStateManager lambda$getInstance$0(Blackboard blackboard, String str) {
        return new FoldStateManager((Activity) blackboard.read("data://activity"));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FoldStateManager lambda$getInstance$1(Activity activity, String str) {
        return new FoldStateManager(activity);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initDisplayManager$3(DisplayManagerCompat displayManagerCompat) {
        displayManagerCompat.registerDisplayListener(this.mDisplayListener, (Handler) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unregister$2(DisplayManagerCompat displayManagerCompat) {
        displayManagerCompat.unregisterDisplayListener(this.mDisplayListener);
    }

    private void notifyIfTableModeChanged(int i2) {
        boolean checkAndGetTableMode = checkAndGetTableMode();
        Boolean bool = this.mIsTableMode;
        if (bool == null || bool.booleanValue() != checkAndGetTableMode) {
            this.mIsTableMode = Boolean.valueOf(checkAndGetTableMode);
            synchronized (this.mFoldChangedListeners) {
                try {
                    if (this.mFoldChangedListeners.size() > 0) {
                        Log.d("FoldStateManager", "onTableModeChanged", Integer.valueOf(i2), Boolean.valueOf(checkAndGetTableMode));
                        ArrayList<FoldChangedListener> arrayList = this.mFoldChangedListeners;
                        ListIterator<FoldChangedListener> listIterator = arrayList.listIterator(arrayList.size());
                        while (listIterator.hasPrevious()) {
                            listIterator.previous().onTableModeChanged(this.mIsTableMode.booleanValue(), i2);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onLayoutStateChanged(WindowLayoutInfo windowLayoutInfo) {
        FoldingFeature.State state;
        int i2;
        FoldingFeature foldingFeature = this.mFoldingFeature;
        if (foldingFeature != null) {
            state = foldingFeature.getState();
        } else {
            state = null;
        }
        updateWindowLayout(windowLayoutInfo);
        FoldingFeature foldingFeature2 = this.mFoldingFeature;
        if (foldingFeature2 == null || foldingFeature2.getState().equals(state)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        notifyIfTableModeChanged(i2);
    }

    /* access modifiers changed from: private */
    public void replaceWindowLayoutInfoListener() {
        Activity activity;
        synchronized (this.mFoldChangedListeners) {
            try {
                if (this.mCallbackAdapter != null && (activity = this.mActivity) != null && !activity.isDestroyed() && !this.mFoldChangedListeners.isEmpty()) {
                    this.mCallbackAdapter.removeWindowLayoutInfoListener(this.mOnLayoutStateChanged);
                    WindowInfoTrackerCallbackAdapter windowInfoTrackerCallbackAdapter = new WindowInfoTrackerCallbackAdapter(WindowInfoTracker.getOrCreate(this.mActivity));
                    this.mCallbackAdapter = windowInfoTrackerCallbackAdapter;
                    windowInfoTrackerCallbackAdapter.addWindowLayoutInfoListener(this.mActivity, new C0217b(0), this.mOnLayoutStateChanged);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public FoldableScreen getScreen() {
        FoldableScreen foldableScreen = this.mScreen;
        if (foldableScreen == FoldableScreen.NONE) {
            return FoldUtils.getScreenType(this.mActivity);
        }
        return foldableScreen;
    }

    public boolean isTableMode() {
        if (this.mIsTableMode == null) {
            this.mIsTableMode = Boolean.valueOf(checkAndGetTableMode());
        }
        return this.mIsTableMode.booleanValue();
    }

    public void notifyIfScreenChanged(FoldableScreen foldableScreen) {
        if (this.mScreen != foldableScreen) {
            synchronized (this.mFoldChangedListeners) {
                try {
                    if (this.mFoldChangedListeners.size() > 0) {
                        Log.d("FoldStateManager", "onScreenChanged", this.mScreen, foldableScreen);
                        ArrayList<FoldChangedListener> arrayList = this.mFoldChangedListeners;
                        ListIterator<FoldChangedListener> listIterator = arrayList.listIterator(arrayList.size());
                        while (listIterator.hasPrevious()) {
                            listIterator.previous().onScreenChanged(this.mScreen, foldableScreen);
                        }
                    }
                    this.mScreen = foldableScreen;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void onApplyWindowInsets() {
        replaceWindowLayoutInfoListener();
    }

    public void register(FoldChangedListener foldChangedListener) {
        synchronized (this.mFoldChangedListeners) {
            try {
                if (this.mFoldChangedListeners.size() == 0) {
                    register();
                }
                this.mFoldChangedListeners.add(foldChangedListener);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void resume() {
        if (this.mPendingResume) {
            notifyIfTableModeChanged(1);
            this.mPendingResume = false;
            Log.d("FoldStateManager", "resume: " + this.mIsTableMode);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("FoldState{");
        sb2.append(getState());
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (this.mDisplayBound != null) {
            str = this.mDisplayBound.width() + "x" + this.mDisplayBound.height();
        } else {
            str = "null";
        }
        sb2.append(str);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.mScreen);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.mIsTableMode);
        sb2.append("}");
        return sb2.toString();
    }

    public void unregister(FoldChangedListener foldChangedListener) {
        synchronized (this.mFoldChangedListeners) {
            try {
                this.mFoldChangedListeners.remove(foldChangedListener);
                if (this.mFoldChangedListeners.size() == 0) {
                    unregister();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void updateWindowLayout(WindowLayoutInfo windowLayoutInfo) {
        List<DisplayFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
        if (displayFeatures.isEmpty()) {
            this.mFoldingFeature = null;
            this.mPendingResume = true;
            Log.w("FoldStateManager", "updateDisplay failed. empty features");
            return;
        }
        FoldingFeature foldingFeature = (FoldingFeature) displayFeatures.get(0);
        this.mFoldingFeature = foldingFeature;
        Rect bounds = foldingFeature.getBounds();
        if (bounds.width() > 0 || bounds.height() > 0) {
            this.mDisplayBound = bounds;
            return;
        }
        this.mPendingResume = true;
        Log.w("FoldStateManager", "window size wasn't confirmed yet");
    }

    private void register() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.mScreen = FoldUtils.getScreenType(this.mActivity);
            WindowInfoTrackerCallbackAdapter windowInfoTrackerCallbackAdapter = new WindowInfoTrackerCallbackAdapter(WindowInfoTracker.getOrCreate(this.mActivity));
            this.mCallbackAdapter = windowInfoTrackerCallbackAdapter;
            windowInfoTrackerCallbackAdapter.addWindowLayoutInfoListener(this.mActivity, new C0217b(0), this.mOnLayoutStateChanged);
            getContext().registerComponentCallbacks(this.mComponentCallbacks);
            if (FoldUtils.SUPPORT_FLIP_COVER_GALLERY && this.mScreen.equals(FoldableScreen.FLIP_COVER)) {
                initDisplayManager();
            }
            Log.d("FoldStateManager", "register " + this + " +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Error | Exception unused) {
            Log.w("FoldStateManager", "register failed");
        }
    }

    private void unregister() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.mScreen = FoldableScreen.NONE;
            this.mCallbackAdapter.removeWindowLayoutInfoListener(this.mOnLayoutStateChanged);
            getContext().unregisterComponentCallbacks(this.mComponentCallbacks);
            Optional.ofNullable(this.mDisplayManager).ifPresent(new b(this, 0));
            this.mDisplayManager = null;
            Log.d("FoldStateManager", "unregister +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Error | Exception e) {
            Log.w("FoldStateManager", "unregister failed e=" + e.getMessage());
        }
    }

    public static FoldStateManager getInstance(Blackboard blackboard, Activity activity) {
        try {
            return (FoldStateManager) blackboard.computeIfAbsent("data://FoldStateManager", new com.samsung.android.motionphoto.utils.ex.b(26, activity));
        } catch (Exception e) {
            a.s(e, new StringBuilder("getInstance failed e="), "FoldStateManager");
            return null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FoldChangedListener {
        void onScreenChanged(FoldableScreen foldableScreen, FoldableScreen foldableScreen2) {
        }

        void onTableModeChanged(boolean z, int i2) {
        }
    }
}
