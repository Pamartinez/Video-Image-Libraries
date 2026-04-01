package com.samsung.android.gallery.app.ui.container.factory;

import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import j4.a;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ListDrawerChildFragmentFactoryImpl extends BottomTabChildFragmentFactoryImpl {
    public ListDrawerChildFragmentFactoryImpl() {
        this.mFragmentSuppliers.put("location://virtual/album/video/fileList", new a(0));
        this.mFragmentSuppliers.put("location://albums/fileList/mxVirtual/favorite", new a(2));
        this.mFragmentSuppliers.put("location://albums/fileList/mxVirtual/recent", new a(2));
        this.mFragmentSuppliers.put("location://albums/fileList/fromSearchCluster", new a(2));
        this.mFragmentSuppliers.put("location://suggestions", new a(3));
        if (PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU) {
            this.mFragmentSuppliers.put("location://search/fileList/Category/ShotMode", new a(4));
        } else if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            this.mFragmentSuppliers.put("location://search/fileList/Category/Location", new a(5));
        } else {
            this.mFragmentSuppliers.put("location://search/fileList/Category/Location", new a(4));
        }
        this.mFragmentSuppliers.put("location://sharing/albums", new b(this, 1));
        this.mFragmentSuppliers.put("location://mtp", new a(6));
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
            this.mFragmentSuppliers.put("location://trash", new a(1));
        }
    }

    public MvpBaseFragment<?, ?> createAlbumsCompat() {
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            return super.createAlbumsCompat();
        }
        return new AlbumsFragment();
    }

    public Supplier<MvpBaseFragment<?, ?>> getSupplier(String str) {
        return this.mFragmentSuppliers.get(ArgumentsUtil.removeArgs(str));
    }
}
