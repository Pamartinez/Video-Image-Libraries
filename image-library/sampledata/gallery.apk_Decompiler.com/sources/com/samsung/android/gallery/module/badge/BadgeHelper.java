package com.samsung.android.gallery.module.badge;

import C3.i;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.settings.MarketUpgradeManager;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BadgeHelper {
    public static boolean hasBadgeOnAboutPagePref() {
        boolean isUpgradeAvailable = MarketUpgradeManager.getInstance().isUpgradeAvailable();
        if (!isUpgradeAvailable) {
            PreferenceCache preferenceCache = PreferenceCache.AboutPageBadgeOnTab;
            if (!preferenceCache.getBoolean()) {
                preferenceCache.setBoolean(true);
            }
        }
        return isUpgradeAvailable;
    }

    public static boolean hasNewSettings() {
        if (needBadgeForAboutPage() || needBadgeForSyncWithOneDrive()) {
            return true;
        }
        return false;
    }

    public static boolean hasNewSharedAlbums() {
        return MdeAlbumHelper.hasNewBadge();
    }

    public static boolean hasNewSuggestions() {
        return SuggestedHelper.getInstance().needNewBadgeInMenuTab();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateMenuTabBadgeIfNecessary$0() {
        if (PreferenceCache.MenuBadgeOnTab.getBoolean() != needBottomMenuTabBadge()) {
            Blackboard.publishGlobal(CommandKey.DATA_REQUEST("data://badge/bottom_menu"), (Object) null);
        }
    }

    private static boolean needBadgeForAboutPage() {
        if (!hasBadgeOnAboutPagePref() || !PreferenceCache.AboutPageBadgeOnTab.getBoolean()) {
            return false;
        }
        return true;
    }

    private static boolean needBadgeForSyncWithOneDrive() {
        if (!PreferenceCache.OneDriveBadgeOnTab.getBoolean() || !CloudStateCompat.isNewBadgeRequired()) {
            return false;
        }
        return true;
    }

    public static boolean needBottomMenuTabBadge() {
        if ((!PreferenceFeatures.OneUi8x.IS_ONE_UI_85 && hasNewSharedAlbums()) || hasNewSettings()) {
            return true;
        }
        if (!Features.isEnabled(Features.SUPPORT_USB_STORAGE) || !UsbBadgeManager.needBadgeForUsbConnection()) {
            return false;
        }
        return true;
    }

    public static void updateMenuTabBadgeIfNecessary() {
        SimpleThreadPool.getInstance().execute(new i(3));
    }
}
