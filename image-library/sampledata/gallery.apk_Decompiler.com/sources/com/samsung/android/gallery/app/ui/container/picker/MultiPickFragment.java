package com.samsung.android.gallery.app.ui.container.picker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import h.C0199b;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiPickFragment extends PickerConFragment {
    protected MvpBaseFragment<?, ?> mPicturesFragment;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindFragment$0(MvpBaseFragment mvpBaseFragment) {
        if (commitChildFragment(mvpBaseFragment, mvpBaseFragment.getTag())) {
            this.mPicturesFragment = mvpBaseFragment;
        }
    }

    public MvpBaseFragment<?, ?> createChildFragment() {
        String removeArgs = ArgumentsUtil.removeArgs(getLocationKey());
        removeArgs.getClass();
        char c5 = 65535;
        switch (removeArgs.hashCode()) {
            case -1141680973:
                if (removeArgs.equals("location://search/fileList/Category/Location")) {
                    c5 = 0;
                    break;
                }
                break;
            case -968159475:
                if (removeArgs.equals("location://search/fileList/Category/People")) {
                    c5 = 1;
                    break;
                }
                break;
            case -234597023:
                if (removeArgs.equals("location://search/fileList/Category/Pet")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1194757598:
                if (removeArgs.equals("location://search/fileList/Category/PeopleAndPets")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return new SearchLocationFragment();
            case 1:
            case 2:
            case 3:
                return new CreatureCategoryFragment();
            default:
                throw new IllegalArgumentException("fragment is not defined in ".concat(removeArgs));
        }
    }

    public List<Fragment> getChildFragments() {
        List<Fragment> childFragments = super.getChildFragments();
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mPicturesFragment;
        if (mvpBaseFragment != null) {
            childFragments.add(0, mvpBaseFragment);
        }
        return childFragments;
    }

    public void onBindFragment() {
        MvpBaseFragment<?, ?> createChildFragment = createChildFragment();
        Bundle arguments = getArguments();
        if (arguments != null) {
            createChildFragment.setArguments(arguments.deepCopy());
        }
        ThreadUtil.postOnUiThread(new C0199b(12, this, createChildFragment));
    }
}
