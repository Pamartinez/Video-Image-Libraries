package com.samsung.android.gallery.app.ui.list.search.toolbar;

import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum SearchToolbarMode {
    LEGACY;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends SearchToolbarMode {
        public /* synthetic */ AnonymousClass1() {
            this("LEGACY_PICK", 1);
        }

        public boolean isPickMode() {
            return true;
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends SearchToolbarMode {
        public /* synthetic */ AnonymousClass2() {
            this("NEW_BOTTOM", 2);
        }

        public boolean isNotSupportedView(String str) {
            if (LocationKey.isSearchRelationshipPreview(str) || LocationKey.isSearchPicturesLocation(str)) {
                return true;
            }
            return false;
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends SearchToolbarMode {
        public /* synthetic */ AnonymousClass3() {
            this("NEW_BOTTOM_PICK", 3);
        }

        public boolean isPickMode() {
            return true;
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public static SearchToolbarMode getMode(boolean z) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar)) {
            if (z) {
                return NEW_BOTTOM_PICK;
            }
            return NEW_BOTTOM;
        } else if (z) {
            return LEGACY_PICK;
        } else {
            return LEGACY;
        }
    }

    public boolean isNotSupportedView(String str) {
        if (isPickMode()) {
            if (LocationKey.isFolder(str)) {
                return true;
            }
            if (LocationKey.isTimelinePictures(str) || LocationKey.isAlbums(str)) {
                if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH || !PickerUtil.supportSearch()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean isPickMode() {
        return false;
    }
}
