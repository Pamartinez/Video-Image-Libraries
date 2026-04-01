package com.samsung.android.gallery.module.settings;

import android.content.Context;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MiscSettingPreference implements SettingPrefApi {
    AlbumChangeCover("album_change_cover"),
    AlbumRename("album_rename"),
    CategoryAutoAlbum("album_update_category"),
    AlbumAutoUpdate("album_auto_update"),
    AlbumSelectedPeople("album_selected_people"),
    CategorySharedAlbum("shared_album_option_category"),
    SharedAlbumLink("shared_album_link"),
    SharedAlbumShortcut("shared_album_link_shortcut"),
    Eof("");
    
    private static final String TAG = "MiscSettingPreference";
    public final String key;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.MiscSettingPreference$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends MiscSettingPreference {
        public /* synthetic */ AnonymousClass1() {
            this("AllowDataUsageForChn", 0, "allow_data_usage_for_chn");
        }

        public boolean isEnabled() {
            if (GalleryPreference.getInstance().loadInt(PreferenceName.ALLOW_DATA_USAGE_FOR_CHN) == 1) {
                return true;
            }
            return false;
        }

        public void setEnabled(boolean z) {
            GalleryPreference.getInstance().saveState(PreferenceName.ALLOW_DATA_USAGE_FOR_CHN, z ? 1 : 0);
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.MiscSettingPreference$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends MiscSettingPreference {
        public /* synthetic */ AnonymousClass2() {
            this("SearchSaveRecent", 1, "save_recent_searches");
        }

        public boolean isEnabled() {
            if (GalleryPreference.getInstance().loadInt(PreferenceName.SAVE_RECENT_SEARCHES, 1) > 0) {
                return true;
            }
            return false;
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://search/setting/save/recent/changed", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            GalleryPreference.getInstance().saveState(PreferenceName.SAVE_RECENT_SEARCHES, z ? 1 : 0);
        }

        private AnonymousClass2(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.MiscSettingPreference$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends MiscSettingPreference {
        public /* synthetic */ AnonymousClass3() {
            this("SearchShowSuggestions", 2, "show_suggestions");
        }

        public boolean isEnabled() {
            if (GalleryPreference.getInstance().loadInt(PreferenceName.SHOW_SUGGESTIONS, 1) > 0) {
                return true;
            }
            return false;
        }

        public void notifyChanged(Context context, boolean z) {
            Blackboard.getApplicationInstance().post("global://search/setting/show/suggestion/changed", Boolean.valueOf(z));
        }

        public void setEnabled(boolean z) {
            GalleryPreference.getInstance().saveState(PreferenceName.SHOW_SUGGESTIONS, z ? 1 : 0);
        }

        private AnonymousClass3(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.MiscSettingPreference$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends MiscSettingPreference {
        public /* synthetic */ AnonymousClass4() {
            this("Customize", 3, "customize");
        }

        public boolean support(Context context) {
            if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
                return false;
            }
            return true;
        }

        private AnonymousClass4(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.settings.MiscSettingPreference$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends MiscSettingPreference {
        public /* synthetic */ AnonymousClass5() {
            this("AlbumSuggestRule", 9, "suggested_contents_rule");
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM_SUGGESTION_RULE);
        }

        private AnonymousClass5(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    public String getKey() {
        return this.key;
    }

    public boolean setAndNotifyIfChanged(Context context, boolean z) {
        Log.d(TAG, "setAndNotifyIfChanged", name(), Boolean.valueOf(z));
        setEnabled(z);
        notifyChanged(context, z);
        return true;
    }

    private MiscSettingPreference(String str) {
        this.key = str;
    }
}
