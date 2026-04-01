package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.fragment.R$id;
import androidx.fragment.app.SpecialEffectsController;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.ViewModelStoreOwner;
import c0.C0086a;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FragmentStateManager {
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;
    private final Fragment mFragment;
    private int mFragmentManagerState = -1;
    private final FragmentStore mFragmentStore;
    private boolean mMovingToState = false;

    /* renamed from: androidx.fragment.app.FragmentStateManager$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.lifecycle.Lifecycle$State[] r0 = androidx.lifecycle.Lifecycle.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$lifecycle$Lifecycle$State = r0
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.RESUMED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.STARTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.CREATED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentStateManager.AnonymousClass2.<clinit>():void");
        }
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
    }

    private boolean isFragmentViewChild(View view) {
        if (view == this.mFragment.mView) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.mFragment.mView) {
                return true;
            }
        }
        return false;
    }

    public void activityCreated() {
        Bundle bundle;
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.mFragment);
        }
        Bundle bundle2 = this.mFragment.mSavedFragmentState;
        if (bundle2 != null) {
            bundle = bundle2.getBundle("savedInstanceState");
        } else {
            bundle = null;
        }
        this.mFragment.performActivityCreated(bundle);
        this.mDispatcher.dispatchOnFragmentActivityCreated(this.mFragment, bundle, false);
    }

    public void addViewToContainer() {
        Fragment findViewFragment = FragmentManager.findViewFragment(this.mFragment.mContainer);
        Fragment parentFragment = this.mFragment.getParentFragment();
        if (findViewFragment != null && !findViewFragment.equals(parentFragment)) {
            Fragment fragment = this.mFragment;
            FragmentStrictMode.onWrongNestedHierarchy(fragment, findViewFragment, fragment.mContainerId);
        }
        int findFragmentIndexInContainer = this.mFragmentStore.findFragmentIndexInContainer(this.mFragment);
        Fragment fragment2 = this.mFragment;
        fragment2.mContainer.addView(fragment2.mView, findFragmentIndexInContainer);
    }

    public void attach() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        Fragment fragment2 = fragment.mTarget;
        FragmentStateManager fragmentStateManager = null;
        if (fragment2 != null) {
            FragmentStateManager fragmentStateManager2 = this.mFragmentStore.getFragmentStateManager(fragment2.mWho);
            if (fragmentStateManager2 != null) {
                Fragment fragment3 = this.mFragment;
                fragment3.mTargetWho = fragment3.mTarget.mWho;
                fragment3.mTarget = null;
                fragmentStateManager = fragmentStateManager2;
            } else {
                throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTarget + " that does not belong to this FragmentManager!");
            }
        } else {
            String str = fragment.mTargetWho;
            if (str != null && (fragmentStateManager = this.mFragmentStore.getFragmentStateManager(str)) == null) {
                StringBuilder sb2 = new StringBuilder("Fragment ");
                sb2.append(this.mFragment);
                sb2.append(" declared target fragment ");
                throw new IllegalStateException(C0212a.p(sb2, this.mFragment.mTargetWho, " that does not belong to this FragmentManager!"));
            }
        }
        if (fragmentStateManager != null) {
            fragmentStateManager.moveToExpectedState();
        }
        Fragment fragment4 = this.mFragment;
        fragment4.mHost = fragment4.mFragmentManager.getHost();
        Fragment fragment5 = this.mFragment;
        fragment5.mParentFragment = fragment5.mFragmentManager.getParent();
        this.mDispatcher.dispatchOnFragmentPreAttached(this.mFragment, false);
        this.mFragment.performAttach();
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, false);
    }

    public int computeExpectedState() {
        SpecialEffectsController.Operation.LifecycleImpact lifecycleImpact;
        Fragment fragment = this.mFragment;
        if (fragment.mFragmentManager == null) {
            return fragment.mState;
        }
        int i2 = this.mFragmentManagerState;
        int i7 = AnonymousClass2.$SwitchMap$androidx$lifecycle$Lifecycle$State[fragment.mMaxState.ordinal()];
        if (i7 != 1) {
            if (i7 == 2) {
                i2 = Math.min(i2, 5);
            } else if (i7 == 3) {
                i2 = Math.min(i2, 1);
            } else if (i7 != 4) {
                i2 = Math.min(i2, -1);
            } else {
                i2 = Math.min(i2, 0);
            }
        }
        Fragment fragment2 = this.mFragment;
        if (fragment2.mFromLayout) {
            if (fragment2.mInLayout) {
                i2 = Math.max(this.mFragmentManagerState, 2);
                View view = this.mFragment.mView;
                if (view != null && view.getParent() == null) {
                    i2 = Math.min(i2, 2);
                }
            } else {
                i2 = this.mFragmentManagerState < 4 ? Math.min(i2, fragment2.mState) : Math.min(i2, 1);
            }
        }
        Fragment fragment3 = this.mFragment;
        if (fragment3.mInDynamicContainer && fragment3.mContainer == null) {
            i2 = Math.min(i2, 4);
        }
        if (!this.mFragment.mAdded) {
            i2 = Math.min(i2, 1);
        }
        Fragment fragment4 = this.mFragment;
        ViewGroup viewGroup = fragment4.mContainer;
        if (viewGroup != null) {
            lifecycleImpact = SpecialEffectsController.getOrCreateController(viewGroup, fragment4.getParentFragmentManager()).getAwaitingCompletionLifecycleImpact(this);
        } else {
            lifecycleImpact = null;
        }
        if (lifecycleImpact == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            i2 = Math.min(i2, 6);
        } else if (lifecycleImpact == SpecialEffectsController.Operation.LifecycleImpact.REMOVING) {
            i2 = Math.max(i2, 3);
        } else {
            Fragment fragment5 = this.mFragment;
            if (fragment5.mRemoving) {
                if (fragment5.isInBackStack()) {
                    i2 = Math.min(i2, 1);
                } else {
                    i2 = Math.min(i2, -1);
                }
            }
        }
        Fragment fragment6 = this.mFragment;
        if (fragment6.mDeferStart && fragment6.mState < 5) {
            i2 = Math.min(i2, 4);
        }
        if (this.mFragment.mTransitioning) {
            i2 = Math.max(i2, 3);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            StringBuilder o2 = C0086a.o(i2, "computeExpectedState() of ", " for ");
            o2.append(this.mFragment);
            Log.v("FragmentManager", o2.toString());
        }
        return i2;
    }

    public void create() {
        Bundle bundle;
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.mFragment);
        }
        Bundle bundle2 = this.mFragment.mSavedFragmentState;
        if (bundle2 != null) {
            bundle = bundle2.getBundle("savedInstanceState");
        } else {
            bundle = null;
        }
        Fragment fragment = this.mFragment;
        if (!fragment.mIsCreated) {
            this.mDispatcher.dispatchOnFragmentPreCreated(fragment, bundle, false);
            this.mFragment.performCreate(bundle);
            this.mDispatcher.dispatchOnFragmentCreated(this.mFragment, bundle, false);
            return;
        }
        fragment.mState = 1;
        fragment.restoreChildFragmentState();
    }

    public void createView() {
        Bundle bundle;
        String str;
        if (!this.mFragment.mFromLayout) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
            }
            Bundle bundle2 = this.mFragment.mSavedFragmentState;
            ViewGroup viewGroup = null;
            if (bundle2 != null) {
                bundle = bundle2.getBundle("savedInstanceState");
            } else {
                bundle = null;
            }
            LayoutInflater performGetLayoutInflater = this.mFragment.performGetLayoutInflater(bundle);
            Fragment fragment = this.mFragment;
            ViewGroup viewGroup2 = fragment.mContainer;
            if (viewGroup2 != null) {
                viewGroup = viewGroup2;
            } else {
                int i2 = fragment.mContainerId;
                if (i2 != 0) {
                    if (i2 != -1) {
                        viewGroup = (ViewGroup) fragment.mFragmentManager.getContainer().onFindViewById(this.mFragment.mContainerId);
                        if (viewGroup == null) {
                            Fragment fragment2 = this.mFragment;
                            if (!fragment2.mRestored && !fragment2.mInDynamicContainer) {
                                try {
                                    str = fragment2.getResources().getResourceName(this.mFragment.mContainerId);
                                } catch (Resources.NotFoundException unused) {
                                    str = "unknown";
                                }
                                throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + str + ") for fragment " + this.mFragment);
                            }
                        } else if (!(viewGroup instanceof FragmentContainerView)) {
                            FragmentStrictMode.onWrongFragmentContainer(this.mFragment, viewGroup);
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
                    }
                }
            }
            Fragment fragment3 = this.mFragment;
            fragment3.mContainer = viewGroup;
            fragment3.performCreateView(performGetLayoutInflater, viewGroup, bundle);
            if (this.mFragment.mView != null) {
                if (FragmentManager.isLoggingEnabled(3)) {
                    Log.d("FragmentManager", "moveto VIEW_CREATED: " + this.mFragment);
                }
                this.mFragment.mView.setSaveFromParentEnabled(false);
                Fragment fragment4 = this.mFragment;
                fragment4.mView.setTag(R$id.fragment_container_view_tag, fragment4);
                if (viewGroup != null) {
                    addViewToContainer();
                }
                Fragment fragment5 = this.mFragment;
                if (fragment5.mHidden) {
                    fragment5.mView.setVisibility(8);
                }
                if (this.mFragment.mView.isAttachedToWindow()) {
                    ViewCompat.requestApplyInsets(this.mFragment.mView);
                } else {
                    final View view = this.mFragment.mView;
                    view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        public void onViewAttachedToWindow(View view) {
                            view.removeOnAttachStateChangeListener(this);
                            ViewCompat.requestApplyInsets(view);
                        }

                        public void onViewDetachedFromWindow(View view) {
                        }
                    });
                }
                this.mFragment.performViewCreated();
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment6 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment6, fragment6.mView, bundle, false);
                int visibility = this.mFragment.mView.getVisibility();
                this.mFragment.setPostOnViewCreatedAlpha(this.mFragment.mView.getAlpha());
                Fragment fragment7 = this.mFragment;
                if (fragment7.mContainer != null && visibility == 0) {
                    View findFocus = fragment7.mView.findFocus();
                    if (findFocus != null) {
                        this.mFragment.setFocusedView(findFocus);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + this.mFragment);
                        }
                    }
                    this.mFragment.mView.setAlpha(0.0f);
                }
            }
            this.mFragment.mState = 2;
        }
    }

    public void destroy() {
        boolean z;
        Fragment findActiveFragment;
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        boolean z3 = true;
        if (!fragment.mRemoving || fragment.isInBackStack()) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            Fragment fragment2 = this.mFragment;
            if (!fragment2.mBeingSaved) {
                this.mFragmentStore.setSavedState(fragment2.mWho, (Bundle) null);
            }
        }
        if (z || this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
            FragmentHostCallback<?> fragmentHostCallback = this.mFragment.mHost;
            if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                z3 = this.mFragmentStore.getNonConfig().isCleared();
            } else if (fragmentHostCallback.getContext() instanceof Activity) {
                z3 = true ^ ((Activity) fragmentHostCallback.getContext()).isChangingConfigurations();
            }
            if ((z && !this.mFragment.mBeingSaved) || z3) {
                this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment, false);
            }
            this.mFragment.performDestroy();
            this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
            for (FragmentStateManager next : this.mFragmentStore.getActiveFragmentStateManagers()) {
                if (next != null) {
                    Fragment fragment3 = next.getFragment();
                    if (this.mFragment.mWho.equals(fragment3.mTargetWho)) {
                        fragment3.mTarget = this.mFragment;
                        fragment3.mTargetWho = null;
                    }
                }
            }
            Fragment fragment4 = this.mFragment;
            String str = fragment4.mTargetWho;
            if (str != null) {
                fragment4.mTarget = this.mFragmentStore.findActiveFragment(str);
            }
            this.mFragmentStore.makeInactive(this);
            return;
        }
        String str2 = this.mFragment.mTargetWho;
        if (!(str2 == null || (findActiveFragment = this.mFragmentStore.findActiveFragment(str2)) == null || !findActiveFragment.mRetainInstance)) {
            this.mFragment.mTarget = findActiveFragment;
        }
        this.mFragment.mState = 0;
    }

    public void destroyFragmentView() {
        View view;
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        ViewGroup viewGroup = fragment.mContainer;
        if (!(viewGroup == null || (view = fragment.mView) == null)) {
            viewGroup.removeView(view);
        }
        this.mFragment.performDestroyView();
        this.mDispatcher.dispatchOnFragmentViewDestroyed(this.mFragment, false);
        Fragment fragment2 = this.mFragment;
        fragment2.mContainer = null;
        fragment2.mView = null;
        fragment2.mViewLifecycleOwner = null;
        fragment2.mViewLifecycleOwnerLiveData.setValue(null);
        this.mFragment.mInLayout = false;
    }

    public void detach() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.mFragment);
        }
        this.mFragment.performDetach();
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if ((fragment.mRemoving && !fragment.isInBackStack()) || this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
            }
            this.mFragment.initState();
        }
    }

    public void ensureInflatedView() {
        Bundle bundle;
        Fragment fragment = this.mFragment;
        if (fragment.mFromLayout && fragment.mInLayout && !fragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
            }
            Bundle bundle2 = this.mFragment.mSavedFragmentState;
            if (bundle2 != null) {
                bundle = bundle2.getBundle("savedInstanceState");
            } else {
                bundle = null;
            }
            Fragment fragment2 = this.mFragment;
            fragment2.performCreateView(fragment2.performGetLayoutInflater(bundle), (ViewGroup) null, bundle);
            View view = this.mFragment.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.mFragment;
                fragment3.mView.setTag(R$id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.mFragment;
                if (fragment4.mHidden) {
                    fragment4.mView.setVisibility(8);
                }
                this.mFragment.performViewCreated();
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment5 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment5, fragment5.mView, bundle, false);
                this.mFragment.mState = 2;
            }
        }
    }

    public Fragment getFragment() {
        return this.mFragment;
    }

    public void moveToExpectedState() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (!this.mMovingToState) {
            try {
                this.mMovingToState = true;
                boolean z = false;
                while (true) {
                    int computeExpectedState = computeExpectedState();
                    Fragment fragment = this.mFragment;
                    int i2 = fragment.mState;
                    if (computeExpectedState != i2) {
                        if (computeExpectedState <= i2) {
                            switch (i2 - 1) {
                                case -1:
                                    detach();
                                    break;
                                case 0:
                                    if (fragment.mBeingSaved && this.mFragmentStore.getSavedState(fragment.mWho) == null) {
                                        this.mFragmentStore.setSavedState(this.mFragment.mWho, saveState());
                                    }
                                    destroy();
                                    break;
                                case 1:
                                    destroyFragmentView();
                                    this.mFragment.mState = 1;
                                    break;
                                case 2:
                                    fragment.mInLayout = false;
                                    fragment.mState = 2;
                                    break;
                                case 3:
                                    if (FragmentManager.isLoggingEnabled(3)) {
                                        Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.mFragment);
                                    }
                                    Fragment fragment2 = this.mFragment;
                                    if (fragment2.mBeingSaved) {
                                        this.mFragmentStore.setSavedState(fragment2.mWho, saveState());
                                    } else if (fragment2.mView != null && fragment2.mSavedViewState == null) {
                                        saveViewState();
                                    }
                                    Fragment fragment3 = this.mFragment;
                                    if (!(fragment3.mView == null || (viewGroup2 = fragment3.mContainer) == null)) {
                                        SpecialEffectsController.getOrCreateController(viewGroup2, fragment3.getParentFragmentManager()).enqueueRemove(this);
                                    }
                                    this.mFragment.mState = 3;
                                    break;
                                case 4:
                                    stop();
                                    break;
                                case 5:
                                    fragment.mState = 5;
                                    break;
                                case 6:
                                    pause();
                                    break;
                            }
                        } else {
                            switch (i2 + 1) {
                                case 0:
                                    attach();
                                    break;
                                case 1:
                                    create();
                                    break;
                                case 2:
                                    ensureInflatedView();
                                    createView();
                                    break;
                                case 3:
                                    activityCreated();
                                    break;
                                case 4:
                                    if (!(fragment.mView == null || (viewGroup3 = fragment.mContainer) == null)) {
                                        SpecialEffectsController.getOrCreateController(viewGroup3, fragment.getParentFragmentManager()).enqueueAdd(SpecialEffectsController.Operation.State.from(this.mFragment.mView.getVisibility()), this);
                                    }
                                    this.mFragment.mState = 4;
                                    break;
                                case 5:
                                    start();
                                    break;
                                case 6:
                                    fragment.mState = 6;
                                    break;
                                case 7:
                                    resume();
                                    break;
                            }
                        }
                        z = true;
                    } else {
                        if (!z && i2 == -1 && fragment.mRemoving && !fragment.isInBackStack() && !this.mFragment.mBeingSaved) {
                            if (FragmentManager.isLoggingEnabled(3)) {
                                Log.d("FragmentManager", "Cleaning up state of never attached fragment: " + this.mFragment);
                            }
                            this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment, true);
                            this.mFragmentStore.makeInactive(this);
                            if (FragmentManager.isLoggingEnabled(3)) {
                                Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
                            }
                            this.mFragment.initState();
                        }
                        Fragment fragment4 = this.mFragment;
                        if (fragment4.mHiddenChanged) {
                            if (!(fragment4.mView == null || (viewGroup = fragment4.mContainer) == null)) {
                                SpecialEffectsController orCreateController = SpecialEffectsController.getOrCreateController(viewGroup, fragment4.getParentFragmentManager());
                                if (this.mFragment.mHidden) {
                                    orCreateController.enqueueHide(this);
                                } else {
                                    orCreateController.enqueueShow(this);
                                }
                            }
                            Fragment fragment5 = this.mFragment;
                            FragmentManager fragmentManager = fragment5.mFragmentManager;
                            if (fragmentManager != null) {
                                fragmentManager.invalidateMenuForFragment(fragment5);
                            }
                            Fragment fragment6 = this.mFragment;
                            fragment6.mHiddenChanged = false;
                            fragment6.onHiddenChanged(fragment6.mHidden);
                            this.mFragment.mChildFragmentManager.dispatchOnHiddenChanged();
                        }
                        this.mMovingToState = false;
                        return;
                    }
                }
            } catch (Throwable th) {
                this.mMovingToState = false;
                throw th;
            }
        } else if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + getFragment());
        }
    }

    public void pause() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.mFragment);
        }
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    public void restoreState(ClassLoader classLoader) {
        Bundle bundle = this.mFragment.mSavedFragmentState;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            if (this.mFragment.mSavedFragmentState.getBundle("savedInstanceState") == null) {
                this.mFragment.mSavedFragmentState.putBundle("savedInstanceState", new Bundle());
            }
            try {
                Fragment fragment = this.mFragment;
                fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("viewState");
                Fragment fragment2 = this.mFragment;
                fragment2.mSavedViewRegistryState = fragment2.mSavedFragmentState.getBundle("viewRegistryState");
                FragmentState fragmentState = (FragmentState) this.mFragment.mSavedFragmentState.getParcelable("state");
                if (fragmentState != null) {
                    Fragment fragment3 = this.mFragment;
                    fragment3.mTargetWho = fragmentState.mTargetWho;
                    fragment3.mTargetRequestCode = fragmentState.mTargetRequestCode;
                    Boolean bool = fragment3.mSavedUserVisibleHint;
                    if (bool != null) {
                        fragment3.mUserVisibleHint = bool.booleanValue();
                        this.mFragment.mSavedUserVisibleHint = null;
                    } else {
                        fragment3.mUserVisibleHint = fragmentState.mUserVisibleHint;
                    }
                }
                Fragment fragment4 = this.mFragment;
                if (!fragment4.mUserVisibleHint) {
                    fragment4.mDeferStart = true;
                }
            } catch (BadParcelableException e) {
                throw new IllegalStateException("Failed to restore view hierarchy state for fragment " + getFragment(), e);
            }
        }
    }

    public void resume() {
        String str;
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.mFragment);
        }
        View focusedView = this.mFragment.getFocusedView();
        if (focusedView != null && isFragmentViewChild(focusedView)) {
            boolean requestFocus = focusedView.requestFocus();
            if (FragmentManager.isLoggingEnabled(2)) {
                StringBuilder sb2 = new StringBuilder("requestFocus: Restoring focused view ");
                sb2.append(focusedView);
                sb2.append(" ");
                if (requestFocus) {
                    str = "succeeded";
                } else {
                    str = "failed";
                }
                sb2.append(str);
                sb2.append(" on Fragment ");
                sb2.append(this.mFragment);
                sb2.append(" resulting in focused view ");
                sb2.append(this.mFragment.mView.findFocus());
                Log.v("FragmentManager", sb2.toString());
            }
        }
        this.mFragment.setFocusedView((View) null);
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        this.mFragmentStore.setSavedState(this.mFragment.mWho, (Bundle) null);
        Fragment fragment = this.mFragment;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
    }

    public Bundle saveState() {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        Fragment fragment = this.mFragment;
        if (fragment.mState == -1 && (bundle = fragment.mSavedFragmentState) != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("state", new FragmentState(this.mFragment));
        if (this.mFragment.mState > -1) {
            Bundle bundle3 = new Bundle();
            this.mFragment.performSaveInstanceState(bundle3);
            if (!bundle3.isEmpty()) {
                bundle2.putBundle("savedInstanceState", bundle3);
            }
            this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, bundle3, false);
            Bundle bundle4 = new Bundle();
            this.mFragment.mSavedStateRegistryController.performSave(bundle4);
            if (!bundle4.isEmpty()) {
                bundle2.putBundle("registryState", bundle4);
            }
            Bundle saveAllStateInternal = this.mFragment.mChildFragmentManager.lambda$attachController$5();
            if (!saveAllStateInternal.isEmpty()) {
                bundle2.putBundle("childFragmentManager", saveAllStateInternal);
            }
            if (this.mFragment.mView != null) {
                saveViewState();
            }
            SparseArray<Parcelable> sparseArray = this.mFragment.mSavedViewState;
            if (sparseArray != null) {
                bundle2.putSparseParcelableArray("viewState", sparseArray);
            }
            Bundle bundle5 = this.mFragment.mSavedViewRegistryState;
            if (bundle5 != null) {
                bundle2.putBundle("viewRegistryState", bundle5);
            }
        }
        Bundle bundle6 = this.mFragment.mArguments;
        if (bundle6 != null) {
            bundle2.putBundle("arguments", bundle6);
        }
        return bundle2;
    }

    public void saveViewState() {
        if (this.mFragment.mView != null) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Saving view state for fragment " + this.mFragment + " with view " + this.mFragment.mView);
            }
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.mFragment.mView.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.mFragment.mSavedViewState = sparseArray;
            }
            Bundle bundle = new Bundle();
            this.mFragment.mViewLifecycleOwner.performSave(bundle);
            if (!bundle.isEmpty()) {
                this.mFragment.mSavedViewRegistryState = bundle;
            }
        }
    }

    public void setFragmentManagerState(int i2) {
        this.mFragmentManagerState = i2;
    }

    public void start() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.mFragment);
        }
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    public void stop() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.mFragment);
        }
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, ClassLoader classLoader, FragmentFactory fragmentFactory, Bundle bundle) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        Fragment instantiate = ((FragmentState) bundle.getParcelable("state")).instantiate(fragmentFactory, classLoader);
        this.mFragment = instantiate;
        instantiate.mSavedFragmentState = bundle;
        Bundle bundle2 = bundle.getBundle("arguments");
        if (bundle2 != null) {
            bundle2.setClassLoader(classLoader);
        }
        instantiate.setArguments(bundle2);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + instantiate);
        }
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment, Bundle bundle) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
        fragment.mTarget = null;
        fragment.mSavedFragmentState = bundle;
        fragment.mArguments = bundle.getBundle("arguments");
    }
}
