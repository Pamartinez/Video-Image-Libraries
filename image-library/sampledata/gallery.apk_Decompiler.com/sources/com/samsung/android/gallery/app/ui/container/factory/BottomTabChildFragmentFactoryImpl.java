package com.samsung.android.gallery.app.ui.container.factory;

import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BottomTabChildFragmentFactoryImpl extends ChildFragmentFactory {
    public BottomTabChildFragmentFactoryImpl() {
        this.mFragmentSuppliers.put("location://timeline", new a(this, 0));
        this.mFragmentSuppliers.put("location://albums", new a(this, 1));
        this.mFragmentSuppliers.put("location://story/albums", new a(this, 2));
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            this.mFragmentSuppliers.put("location://collection", new a(this, 3));
        }
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            this.mFragmentSuppliers.put("location://mtp", new a(this, 4));
        }
    }
}
