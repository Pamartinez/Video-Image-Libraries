package com.samsung.android.gallery.app.ui.list.picker;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PickerMenuFactory {
    public static MenuDataBinding create(Blackboard blackboard, String str) {
        LaunchModeType launchModeType = (LaunchModeType) blackboard.read("data://launch_mode_type");
        if (supportMenuBinding(launchModeType, str)) {
            return createDataBinding(blackboard, launchModeType);
        }
        return null;
    }

    private static MenuDataBinding createDataBinding(Blackboard blackboard, LaunchModeType launchModeType) {
        if (isSinglePickMode(launchModeType)) {
            return createSinglePickMenu(blackboard, launchModeType);
        }
        return createMultiPickMenu();
    }

    public static MenuDataBinding createMultiPickMenu() {
        return new MenuDataBinding(R.menu.menu_picker_multi_pick);
    }

    private static MenuDataBinding createSinglePickMenu(final Blackboard blackboard, final LaunchModeType launchModeType) {
        AnonymousClass1 r0 = new MenuDataBinding(R.menu.menu_picker_single_pick) {
            public boolean checkSelectItemMode(MenuDataBinding.ItemMode itemMode, MenuItem menuItem) {
                return true;
            }
        };
        r0.addBinding(new MenuDataBinding.MenuData(R.id.action_all_album) {
            public boolean getDefaultVisibility() {
                if (!PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER || !PickerMenuFactory.isCoverItemPickMode(launchModeType) || !PickerMenuFactory.isAlbumCoverPickMode(blackboard) || PickerMenuFactory.isCoverHistoryItemPickMode(blackboard)) {
                    return false;
                }
                return true;
            }
        });
        return r0;
    }

    /* access modifiers changed from: private */
    public static boolean isAlbumCoverPickMode(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isAlbumCoverPick()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static boolean isCoverHistoryItemPickMode(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isCoverHistoryItemPick()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static boolean isCoverItemPickMode(LaunchModeType launchModeType) {
        if (launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return true;
        }
        return false;
    }

    private static boolean isSinglePickMode(LaunchModeType launchModeType) {
        if (launchModeType == LaunchModeType.ACTION_PICK || isCoverItemPickMode(launchModeType)) {
            return true;
        }
        return false;
    }

    private static boolean supportMenuBinding(LaunchModeType launchModeType, String str) {
        boolean z;
        boolean isSinglePickMode = isSinglePickMode(launchModeType);
        if (str != null) {
            z = true;
        } else {
            z = false;
        }
        if (isSinglePickMode == z) {
            return true;
        }
        return false;
    }
}
