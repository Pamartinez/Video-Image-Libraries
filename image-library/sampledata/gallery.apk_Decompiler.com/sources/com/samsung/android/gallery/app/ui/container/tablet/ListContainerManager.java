package com.samsung.android.gallery.app.ui.container.tablet;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentCache;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentFactory;
import com.samsung.android.gallery.app.ui.container.factory.ChildFragmentInfo;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewFragment;
import com.samsung.android.gallery.app.ui.list.albums.mx.all.MxAllAlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.module.logger.Analytics;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.sec.android.gallery3d.R;
import g6.f;
import h.C0199b;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import n4.C0490b;
import n4.C0491c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListContainerManager {
    private final String TRANSACTION_NAME_DRAWER = "drawer";
    private final ChildFragmentCache mChildFragmentCache;
    private final ChildFragmentInfo mChildFragmentInfo;
    private final Stack<String> mChildFragmentLocationStack = new Stack<>();
    private IListContainerView mContainerFragment;
    private MvpBaseFragment mCurrentFragment;

    public ListContainerManager(IListContainerView iListContainerView) {
        this.mContainerFragment = iListContainerView;
        this.mChildFragmentCache = new ChildFragmentCache(ChildFragmentFactory.LIST_NORMAL);
        this.mChildFragmentInfo = new ChildFragmentInfo();
    }

    private Blackboard getBlackboard() {
        return this.mContainerFragment.getBlackboard();
    }

    private MvpBaseFragment getChildFragment(String str) {
        if (LocationKey.isAlbumPictures(str)) {
            return new AlbumPicturesFragment();
        }
        if (LocationKey.isFolder(str)) {
            return new FolderViewFragment();
        }
        if (LocationKey.isAllAlbumsMatch(str)) {
            return new MxAllAlbumsFragment();
        }
        throw new IllegalArgumentException(C0212a.l("unexpected locationKey=", str));
    }

    private String getCurrentLocationKey() {
        MvpBaseFragment mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment != null) {
            return mvpBaseFragment.getLocationKey();
        }
        return "location://timeline";
    }

    private void hideCurrentFragment(FragmentTransaction fragmentTransaction, String str) {
        MvpBaseFragment mvpBaseFragment;
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) && (mvpBaseFragment = this.mCurrentFragment) != null) {
            fragmentTransaction.hide(mvpBaseFragment);
            if (str != null) {
                this.mCurrentFragment.postAnalyticsLog(Analytics.getEventId(str));
            }
        }
    }

    private boolean isAlbumsExist() {
        if (this.mContainerFragment.getChildFragmentManager().findFragmentByTag("location://albums") != null) {
            return true;
        }
        return false;
    }

    private boolean isSwitchingFolder(String str) {
        MvpBaseFragment mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment == null || !LocationKey.isAlbumPictures(mvpBaseFragment.getLocationKey()) || !LocationKey.isAlbumPictures(str)) {
            return false;
        }
        return true;
    }

    private boolean isTransactionFromDrawer() {
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            return true;
        }
        try {
            return "drawer".equals(this.mContainerFragment.getChildFragmentManager().getBackStackEntryAt(this.mContainerFragment.getChildFragmentManager().getBackStackEntryCount() - 1).getName());
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeChildFragment$3() {
        popChildFragment(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$selectView$4(String str) {
        this.mContainerFragment.selectView(str, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$switchFragment$0(FragmentManager fragmentManager, String str) {
        getBlackboard().post("command://RemoveURL", (Object) null);
        fragmentManager.popBackStack();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$switchFragment$2() {
        this.mContainerFragment.selectView(getCurrentLocationKey(), false);
    }

    private void popChildFragment(boolean z) {
        try {
            String pop = this.mChildFragmentLocationStack.pop();
            getBlackboard().post("command://RemoveURL", (Object) null);
            if (z) {
                this.mContainerFragment.getChildFragmentManager().popBackStack();
            }
            setCurrentFragment();
        } catch (Exception e) {
            Log.e("ListContainerManager", e.getMessage());
        }
    }

    private void postSetCurrentFragment(String str, MvpBaseFragment mvpBaseFragment) {
        MvpBaseFragment mvpBaseFragment2 = this.mCurrentFragment;
        if (mvpBaseFragment2 != null) {
            mvpBaseFragment2.clearAdvancedMouseFocus();
        }
        this.mCurrentFragment = mvpBaseFragment;
        mvpBaseFragment.onDisplayedWithDrawer();
        this.mContainerFragment.updateCurrentLocationKey(str);
        this.mContainerFragment.onCurrentFragmentChanged();
    }

    private void refreshData(int i2, String str) {
        getBlackboard().postEvent(EventMessage.obtain(i2, str));
        this.mChildFragmentLocationStack.push(str);
        this.mContainerFragment.updateCurrentLocationKey(str);
    }

    private boolean replaceRootAsTabFragment() {
        if (!LocationKey.isRootOfContainerExceptTab(this.mCurrentFragment.getLocationKey()) || this.mCurrentFragment.isDrawerMode()) {
            return false;
        }
        selectView(GalleryPreference.getInstance().loadString("location://variable/currentv1", "location://timeline"));
        return true;
    }

    private void selectView(String str) {
        if (this.mContainerFragment.getChildFragmentManager().isStateSaved()) {
            ThreadUtil.postOnUiThreadDelayed(new C0199b(14, this, str), 100);
        } else {
            this.mContainerFragment.selectView(str, true);
        }
    }

    private void setCurrentFragment() {
        String str;
        if (!this.mContainerFragment.getChildFragmentManager().getFragments().isEmpty() && this.mCurrentFragment != null) {
            try {
                if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) || !this.mChildFragmentLocationStack.empty()) {
                    str = this.mChildFragmentLocationStack.peek();
                } else {
                    str = "location://albums";
                }
                postSetCurrentFragment(str, (MvpBaseFragment) this.mContainerFragment.getChildFragmentManager().findFragmentByTag(str));
            } catch (Exception e) {
                Log.e((CharSequence) "ListContainerManager", "setCurrentFragment failed", (Throwable) e);
                ListContainerDebugger.printChildFragments(this.mContainerFragment, this.mChildFragmentLocationStack);
            }
        }
    }

    private void setFragmentAnimation(IBaseFragment iBaseFragment, IBaseFragment iBaseFragment2, FragmentTransaction fragmentTransaction) {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack) && PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION && iBaseFragment2.supportEnterDefaultTransition() && iBaseFragment.supportExitDefaultTransition()) {
            iBaseFragment.reservePreventNoBackground(true);
            iBaseFragment2.reservePreventNoBackground(true);
            fragmentTransaction.setCustomAnimations(R.animator.sesl_fragment_open_enter, R.animator.sesl_fragment_open_exit, R.animator.sesl_fragment_close_enter, R.animator.sesl_fragment_close_exit);
        }
    }

    private Fragment setLocationKey(Fragment fragment, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("locationKey", str);
        bundle.putString("callerKey", this.mCurrentFragment.getTag());
        bundle.putString("blackboardKey", getBlackboard().getName());
        fragment.setArguments(bundle);
        return fragment;
    }

    public void addChildFragment(String str) {
        String str2;
        if (!this.mContainerFragment.getChildFragmentManager().getFragments().isEmpty() && this.mCurrentFragment != null) {
            if (!isAlbumsExist()) {
                switchFragment("location://albums");
            }
            try {
                FragmentTransaction beginTransaction = this.mContainerFragment.getChildFragmentManager().beginTransaction();
                this.mChildFragmentLocationStack.push(str);
                MvpBaseFragment childFragment = getChildFragment(str);
                setLocationKey(childFragment, str);
                PocFeatures pocFeatures = PocFeatures.FragmentPredictiveBack;
                if (PocFeatures.isEnabled(pocFeatures) && BackPressUtils.supportPredictiveBack(this.mCurrentFragment.getActivity())) {
                    FragmentTransaction beginTransaction2 = ((Fragment) this.mContainerFragment).getParentFragmentManager().beginTransaction();
                    beginTransaction2.setPrimaryNavigationFragment((Fragment) this.mContainerFragment);
                    beginTransaction2.commit();
                }
                if (PocFeatures.isEnabled(pocFeatures)) {
                    setFragmentAnimation(this.mContainerFragment, childFragment, beginTransaction);
                }
                beginTransaction.replace(R.id.fragment_container, childFragment, str);
                if (!PocFeatures.isEnabled(pocFeatures) || !this.mContainerFragment.isDrawerMode()) {
                    str2 = null;
                } else {
                    str2 = "drawer";
                }
                beginTransaction.addToBackStack(str2);
                beginTransaction.commitAllowingStateLoss();
                postSetCurrentFragment(str, childFragment);
                Log.majorEvent("ListContainerManager", "commitFragment#child" + Logger.v(Integer.valueOf(this.mChildFragmentLocationStack.size()), childFragment.getTag()) + " " + Logger.toString((Fragment) childFragment));
            } catch (Exception e) {
                Log.e((CharSequence) "ListContainerManager", "addChildFragment failed", (Throwable) e);
                ListContainerDebugger.printChildFragments(this.mContainerFragment, this.mChildFragmentLocationStack);
            }
        }
    }

    public void destroy() {
        this.mChildFragmentLocationStack.clear();
        this.mChildFragmentCache.clear();
        List<Fragment> fragments = this.mContainerFragment.getChildFragmentManager().getFragments();
        if (!fragments.isEmpty()) {
            FragmentTransaction beginTransaction = this.mContainerFragment.getChildFragmentManager().beginTransaction();
            for (Fragment remove : fragments) {
                beginTransaction.remove(remove);
            }
            beginTransaction.commitAllowingStateLoss();
        }
        this.mContainerFragment = null;
        this.mCurrentFragment = null;
    }

    public List<Fragment> getChildFragments() {
        return this.mContainerFragment.getChildFragmentManager().getFragments();
    }

    public MvpBaseFragment getCurrentFragment() {
        return this.mCurrentFragment;
    }

    public boolean hasChildFragments() {
        return !this.mChildFragmentLocationStack.empty();
    }

    public boolean isAlbumFirstSelect() {
        return this.mChildFragmentInfo.isAlbumFirstSelect();
    }

    public boolean isAlbumFragmentExist() {
        return this.mChildFragmentCache.hasChildFragment("location://albums");
    }

    public boolean isSwitchingAlbum(String str) {
        MvpBaseFragment mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment == null || !LocationKey.isAlbumPictures(mvpBaseFragment.getLocationKey()) || !LocationKey.isAlbumPictures(str)) {
            return false;
        }
        return true;
    }

    public boolean isTimelineFirstSelect() {
        return this.mChildFragmentInfo.isTimeFirstSelect();
    }

    public void popFragmentStack() {
        popChildFragment(false);
    }

    public boolean refreshChildFragment(String str) {
        int i2;
        if (this.mCurrentFragment == null || this.mChildFragmentLocationStack.isEmpty()) {
            Log.w("ListContainerManager", "refreshChildFragment : It should be run through addChildFragment.");
            return false;
        }
        boolean isAllAlbumsMatch = LocationKey.isAllAlbumsMatch(this.mChildFragmentLocationStack.get(0));
        boolean z = true;
        while (true) {
            int size = this.mChildFragmentLocationStack.size();
            if (isAllAlbumsMatch) {
                i2 = 2;
            } else {
                i2 = 1;
            }
            if (size <= i2) {
                break;
            }
            this.mChildFragmentLocationStack.pop();
            getBlackboard().post("command://RemoveURL", (Object) null);
            this.mContainerFragment.getChildFragmentManager().popBackStack();
            z = false;
        }
        this.mChildFragmentLocationStack.pop();
        if (z && isSwitchingAlbum(str)) {
            refreshData(2001, str);
        } else if (!z || !isSwitchingFolder(str)) {
            this.mContainerFragment.getChildFragmentManager().popBackStack();
            if (!z && isSwitchingAlbum(str)) {
                getBlackboard().publish("data://albums_backup/current", getBlackboard().read("data://albums/current", null));
            }
            addChildFragment(str);
        } else {
            refreshData(2008, str);
        }
        return true;
    }

    public boolean removeChildFragment() {
        if (this.mChildFragmentLocationStack.isEmpty()) {
            return replaceRootAsTabFragment();
        }
        if (this.mChildFragmentLocationStack.size() == 1 && isTransactionFromDrawer()) {
            selectView("location://albums");
            return true;
        } else if (this.mContainerFragment.getChildFragmentManager().isStateSaved()) {
            ThreadUtil.postponeOnUiThread(new C0490b(this, 0));
            return true;
        } else {
            popChildFragment(true);
            return true;
        }
    }

    public void removeSiblingFragments(String[] strArr) {
        List<Fragment> fragments = this.mContainerFragment.getChildFragmentManager().getFragments();
        if (fragments.size() > strArr.length) {
            try {
                FragmentTransaction beginTransaction = this.mContainerFragment.getChildFragmentManager().beginTransaction();
                ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
                for (Fragment next : fragments) {
                    if (!(next == null || next == this.mCurrentFragment || arrayList.contains(next.getTag()))) {
                        beginTransaction.remove(next);
                        String tag = next.getTag();
                        this.mChildFragmentCache.remove(tag);
                        this.mChildFragmentInfo.setFirstSelect(tag, true);
                    }
                }
                beginTransaction.commitAllowingStateLoss();
            } catch (Exception e) {
                Log.e((CharSequence) "ListContainerManager", "removeSiblingFragments failed", (Throwable) e);
                ListContainerDebugger.printChildFragments(this.mContainerFragment, this.mChildFragmentLocationStack);
            }
        }
    }

    public void switchFragment(String str) {
        try {
            FragmentManager childFragmentManager = this.mContainerFragment.getChildFragmentManager();
            this.mChildFragmentLocationStack.forEach(new C0491c(0, this, childFragmentManager));
            this.mChildFragmentLocationStack.clear();
            MvpBaseFragment<?, ?> mvpBaseFragment = (MvpBaseFragment) childFragmentManager.findFragmentByTag(str);
            FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
            this.mChildFragmentCache.getChildFragments().forEach(new f(4, beginTransaction));
            if (mvpBaseFragment != null) {
                hideCurrentFragment(beginTransaction, str);
                beginTransaction.show(mvpBaseFragment);
            } else {
                mvpBaseFragment = this.mChildFragmentCache.get(this.mChildFragmentInfo, str);
                if (mvpBaseFragment == null) {
                    ThreadUtil.postOnUiThreadDelayed(new C0490b(this, 1), 300);
                    return;
                }
                if (LocationKey.isRootOfContainerExceptTab(str)) {
                    setLocationKey(mvpBaseFragment, str);
                }
                this.mContainerFragment.setArgumentOnSwitchFragment(mvpBaseFragment, str);
                hideCurrentFragment(beginTransaction, (String) null);
                beginTransaction.add((int) R.id.fragment_container, (Fragment) mvpBaseFragment, str);
            }
            beginTransaction.commitAllowingStateLoss();
            postSetCurrentFragment(str, mvpBaseFragment);
            Log.majorEvent("ListContainerManager", "commitFragment#switch" + Logger.v(Integer.valueOf(this.mChildFragmentLocationStack.size()), mvpBaseFragment.getTag()) + " " + Logger.toString((Fragment) mvpBaseFragment));
        } catch (Exception e) {
            Log.e((CharSequence) "ListContainerManager", "switchFragment failed", (Throwable) e);
            ListContainerDebugger.printChildFragments(this.mContainerFragment, this.mChildFragmentLocationStack);
        }
    }
}
