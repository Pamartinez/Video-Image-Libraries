package androidx.fragment.app;

import N2.j;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import i.C0212a;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FragmentTransaction {
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack = true;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    private final ClassLoader mClassLoader;
    ArrayList<Runnable> mCommitRunnables;
    int mEnterAnim;
    int mExitAnim;
    private final FragmentFactory mFragmentFactory;
    String mName;
    ArrayList<Op> mOps = new ArrayList<>();
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed = false;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    int mTransition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Op {
        int mCmd;
        Lifecycle.State mCurrentMaxState;
        int mEnterAnim;
        int mExitAnim;
        Fragment mFragment;
        boolean mFromExpandedOp;
        Lifecycle.State mOldMaxState;
        int mPopEnterAnim;
        int mPopExitAnim;

        public Op() {
        }

        public Op(int i2, Fragment fragment) {
            this.mCmd = i2;
            this.mFragment = fragment;
            this.mFromExpandedOp = false;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.mOldMaxState = state;
            this.mCurrentMaxState = state;
        }

        public Op(int i2, Fragment fragment, boolean z) {
            this.mCmd = i2;
            this.mFragment = fragment;
            this.mFromExpandedOp = z;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.mOldMaxState = state;
            this.mCurrentMaxState = state;
        }
    }

    public FragmentTransaction(FragmentFactory fragmentFactory, ClassLoader classLoader) {
        this.mFragmentFactory = fragmentFactory;
        this.mClassLoader = classLoader;
    }

    private Fragment createFragment(Class<? extends Fragment> cls, Bundle bundle) {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory != null) {
            ClassLoader classLoader = this.mClassLoader;
            if (classLoader != null) {
                Fragment instantiate = fragmentFactory.instantiate(classLoader, cls.getName());
                if (bundle != null) {
                    instantiate.setArguments(bundle);
                }
                return instantiate;
            }
            throw new IllegalStateException("The FragmentManager must be attached to itshost to create a Fragment");
        }
        throw new IllegalStateException("Creating a Fragment requires that this FragmentTransaction was built with FragmentManager.beginTransaction()");
    }

    public FragmentTransaction add(Fragment fragment, String str) {
        doAddOp(0, fragment, str, 1);
        return this;
    }

    public void addOp(Op op) {
        this.mOps.add(op);
        op.mEnterAnim = this.mEnterAnim;
        op.mExitAnim = this.mExitAnim;
        op.mPopEnterAnim = this.mPopEnterAnim;
        op.mPopExitAnim = this.mPopExitAnim;
    }

    public FragmentTransaction addSharedElement(View view, String str) {
        if (!FragmentTransition.supportsTransition()) {
            return this;
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<>();
                this.mSharedElementTargetNames = new ArrayList<>();
            } else if (this.mSharedElementTargetNames.contains(str)) {
                throw new IllegalArgumentException(C0212a.m("A shared element with the target name '", str, "' has already been added to the transaction."));
            } else if (this.mSharedElementSourceNames.contains(transitionName)) {
                throw new IllegalArgumentException(C0212a.m("A shared element with the source name '", transitionName, "' has already been added to the transaction."));
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(str);
            return this;
        }
        throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
    }

    public FragmentTransaction addToBackStack(String str) {
        if (this.mAllowAddToBackStack) {
            this.mAddToBackStack = true;
            this.mName = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public FragmentTransaction attach(Fragment fragment) {
        addOp(new Op(7, fragment));
        return this;
    }

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    public FragmentTransaction detach(Fragment fragment) {
        addOp(new Op(6, fragment));
        return this;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if (!this.mAddToBackStack) {
            this.mAllowAddToBackStack = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public void doAddOp(int i2, Fragment fragment, String str, int i7) {
        String str2 = fragment.mPreviousWho;
        if (str2 != null) {
            FragmentStrictMode.onFragmentReuse(fragment, str2);
        }
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str3 = fragment.mTag;
            if (str3 == null || str.equals(str3)) {
                fragment.mTag = str;
            } else {
                StringBuilder sb2 = new StringBuilder("Can't change tag of fragment ");
                sb2.append(fragment);
                sb2.append(": was ");
                throw new IllegalStateException(j.f(sb2, fragment.mTag, " now ", str));
            }
        }
        if (i2 != 0) {
            if (i2 != -1) {
                int i8 = fragment.mFragmentId;
                if (i8 == 0 || i8 == i2) {
                    fragment.mFragmentId = i2;
                    fragment.mContainerId = i2;
                } else {
                    throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i2);
                }
            } else {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
        }
        addOp(new Op(i7, fragment));
    }

    public FragmentTransaction hide(Fragment fragment) {
        addOp(new Op(4, fragment));
        return this;
    }

    public FragmentTransaction remove(Fragment fragment) {
        addOp(new Op(3, fragment));
        return this;
    }

    public FragmentTransaction replace(int i2, Fragment fragment) {
        return replace(i2, fragment, (String) null);
    }

    public FragmentTransaction runOnCommit(Runnable runnable) {
        return runOnCommitInternal(false, runnable);
    }

    public FragmentTransaction runOnCommitInternal(boolean z, Runnable runnable) {
        if (!z) {
            disallowAddToBackStack();
        }
        if (this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList<>();
        }
        this.mCommitRunnables.add(runnable);
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i2, int i7) {
        return setCustomAnimations(i2, i7, 0, 0);
    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment) {
        addOp(new Op(8, fragment));
        return this;
    }

    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.mReorderingAllowed = z;
        return this;
    }

    public FragmentTransaction setTransition(int i2) {
        this.mTransition = i2;
        return this;
    }

    public FragmentTransaction show(Fragment fragment) {
        addOp(new Op(5, fragment));
        return this;
    }

    public final FragmentTransaction add(int i2, Class<? extends Fragment> cls, Bundle bundle) {
        return add(i2, createFragment(cls, bundle));
    }

    public FragmentTransaction replace(int i2, Fragment fragment, String str) {
        if (i2 != 0) {
            doAddOp(i2, fragment, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public FragmentTransaction setCustomAnimations(int i2, int i7, int i8, int i10) {
        this.mEnterAnim = i2;
        this.mExitAnim = i7;
        this.mPopEnterAnim = i8;
        this.mPopExitAnim = i10;
        return this;
    }

    public FragmentTransaction add(int i2, Fragment fragment) {
        doAddOp(i2, fragment, (String) null, 1);
        return this;
    }

    public FragmentTransaction add(int i2, Fragment fragment, String str) {
        doAddOp(i2, fragment, str, 1);
        return this;
    }

    public final FragmentTransaction add(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.mContainer = viewGroup;
        fragment.mInDynamicContainer = true;
        return add(viewGroup.getId(), fragment, str);
    }
}
