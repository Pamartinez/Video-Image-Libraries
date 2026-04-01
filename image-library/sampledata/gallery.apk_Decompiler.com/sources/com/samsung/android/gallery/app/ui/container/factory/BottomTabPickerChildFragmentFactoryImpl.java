package com.samsung.android.gallery.app.ui.container.factory;

import Ad.C0720a;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchClusterResultPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerFragment;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BottomTabPickerChildFragmentFactoryImpl extends ChildFragmentFactory {
    private static boolean SUPPORT_TOP_RESULT = false;

    public BottomTabPickerChildFragmentFactoryImpl() {
        this.mFragmentSuppliers.put("location://timeline", new C0720a(25));
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            this.mFragmentSuppliers.put("location://collection", new C0720a(26));
            this.mFragmentSuppliers.put("location://search/fileList/KeywordTab", new b(this, 0));
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH) {
            this.mFragmentSuppliers.put("location://search", new C0720a(27));
        }
    }

    /* access modifiers changed from: private */
    public MvpBaseFragment<?, ?> createSearchPicturesPickerCompat() {
        if (SUPPORT_TOP_RESULT) {
            return new SearchClusterResultPickerFragment();
        }
        return new SearchPicturesPickerFragment();
    }

    public Supplier<MvpBaseFragment<?, ?>> getSupplier(String str) {
        if (!LocationKey.isAlbumsMatch(str)) {
            return super.getSupplier(str);
        }
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums) || !PreferenceFeatures.EssentialAlbums.isEnabled()) {
            return new C0720a(29);
        }
        return new C0720a(28);
    }
}
