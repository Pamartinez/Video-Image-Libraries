package androidx.fragment.app;

import N2.j;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FragmentManagerViewModel extends ViewModel {
    private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() {
        public <T extends ViewModel> T create(Class<T> cls) {
            return new FragmentManagerViewModel(true);
        }
    };
    private final HashMap<String, FragmentManagerViewModel> mChildNonConfigs = new HashMap<>();
    private boolean mHasBeenCleared = false;
    private boolean mHasSavedSnapshot = false;
    private boolean mIsStateSaved = false;
    private final HashMap<String, Fragment> mRetainedFragments = new HashMap<>();
    private final boolean mStateAutomaticallySaved;
    private final HashMap<String, ViewModelStore> mViewModelStores = new HashMap<>();

    public FragmentManagerViewModel(boolean z) {
        this.mStateAutomaticallySaved = z;
    }

    private void clearNonConfigStateInternal(String str, boolean z) {
        FragmentManagerViewModel fragmentManagerViewModel = this.mChildNonConfigs.get(str);
        if (fragmentManagerViewModel != null) {
            if (z) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(fragmentManagerViewModel.mChildNonConfigs.keySet());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    fragmentManagerViewModel.clearNonConfigState((String) it.next(), true);
                }
            }
            fragmentManagerViewModel.onCleared();
            this.mChildNonConfigs.remove(str);
        }
        ViewModelStore viewModelStore = this.mViewModelStores.get(str);
        if (viewModelStore != null) {
            viewModelStore.clear();
            this.mViewModelStores.remove(str);
        }
    }

    public static FragmentManagerViewModel getInstance(ViewModelStore viewModelStore) {
        return (FragmentManagerViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(FragmentManagerViewModel.class);
    }

    public void addRetainedFragment(Fragment fragment) {
        if (this.mIsStateSaved) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (!this.mRetainedFragments.containsKey(fragment.mWho)) {
            this.mRetainedFragments.put(fragment.mWho, fragment);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    public void clearNonConfigState(Fragment fragment, boolean z) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        clearNonConfigStateInternal(fragment.mWho, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FragmentManagerViewModel.class == obj.getClass()) {
            FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) obj;
            if (!this.mRetainedFragments.equals(fragmentManagerViewModel.mRetainedFragments) || !this.mChildNonConfigs.equals(fragmentManagerViewModel.mChildNonConfigs) || !this.mViewModelStores.equals(fragmentManagerViewModel.mViewModelStores)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Fragment findRetainedFragmentByWho(String str) {
        return this.mRetainedFragments.get(str);
    }

    public FragmentManagerViewModel getChildNonConfig(Fragment fragment) {
        FragmentManagerViewModel fragmentManagerViewModel = this.mChildNonConfigs.get(fragment.mWho);
        if (fragmentManagerViewModel != null) {
            return fragmentManagerViewModel;
        }
        FragmentManagerViewModel fragmentManagerViewModel2 = new FragmentManagerViewModel(this.mStateAutomaticallySaved);
        this.mChildNonConfigs.put(fragment.mWho, fragmentManagerViewModel2);
        return fragmentManagerViewModel2;
    }

    public Collection<Fragment> getRetainedFragments() {
        return new ArrayList(this.mRetainedFragments.values());
    }

    public ViewModelStore getViewModelStore(Fragment fragment) {
        ViewModelStore viewModelStore = this.mViewModelStores.get(fragment.mWho);
        if (viewModelStore != null) {
            return viewModelStore;
        }
        ViewModelStore viewModelStore2 = new ViewModelStore();
        this.mViewModelStores.put(fragment.mWho, viewModelStore2);
        return viewModelStore2;
    }

    public int hashCode() {
        int hashCode = this.mChildNonConfigs.hashCode();
        return this.mViewModelStores.hashCode() + ((hashCode + (this.mRetainedFragments.hashCode() * 31)) * 31);
    }

    public boolean isCleared() {
        return this.mHasBeenCleared;
    }

    public void onCleared() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.mHasBeenCleared = true;
    }

    public void removeRetainedFragment(Fragment fragment) {
        if (this.mIsStateSaved) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.mRetainedFragments.remove(fragment.mWho) != null && FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    public void setIsStateSaved(boolean z) {
        this.mIsStateSaved = z;
    }

    public boolean shouldDestroy(Fragment fragment) {
        if (!this.mRetainedFragments.containsKey(fragment.mWho)) {
            return true;
        }
        if (this.mStateAutomaticallySaved) {
            return this.mHasBeenCleared;
        }
        return !this.mHasSavedSnapshot;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FragmentManagerViewModel{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append("} Fragments (");
        Iterator<Fragment> it = this.mRetainedFragments.values().iterator();
        while (it.hasNext()) {
            sb2.append(it.next());
            if (it.hasNext()) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append(") Child Non Config (");
        Iterator<String> it2 = this.mChildNonConfigs.keySet().iterator();
        while (it2.hasNext()) {
            sb2.append(it2.next());
            if (it2.hasNext()) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append(") ViewModelStores (");
        Iterator<String> it3 = this.mViewModelStores.keySet().iterator();
        while (it3.hasNext()) {
            sb2.append(it3.next());
            if (it3.hasNext()) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append(')');
        return sb2.toString();
    }

    public void clearNonConfigState(String str, boolean z) {
        if (FragmentManager.isLoggingEnabled(3)) {
            j.w("Clearing non-config state for saved state of Fragment ", str, "FragmentManager");
        }
        clearNonConfigStateInternal(str, z);
    }
}
