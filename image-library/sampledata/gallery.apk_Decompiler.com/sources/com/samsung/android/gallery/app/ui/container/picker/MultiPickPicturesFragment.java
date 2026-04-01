package com.samsung.android.gallery.app.ui.container.picker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerFragment;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiPickPicturesFragment extends PickerConFragment {
    private MvpBaseFragment<?, ?> mPicturesFragment;

    public List<Fragment> getChildFragments() {
        List<Fragment> childFragments = super.getChildFragments();
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mPicturesFragment;
        if (mvpBaseFragment != null) {
            childFragments.add(0, mvpBaseFragment);
        }
        return childFragments;
    }

    public void onBindFragment() {
        PicturesPickerFragment picturesPickerFragment = new PicturesPickerFragment();
        Bundle arguments = getArguments();
        if (arguments != null) {
            picturesPickerFragment.setArguments(arguments.deepCopy());
        }
        if (commitChildFragment(picturesPickerFragment, picturesPickerFragment.getTag())) {
            this.mPicturesFragment = picturesPickerFragment;
        }
    }
}
