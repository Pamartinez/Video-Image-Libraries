package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerMenuFactory {
    private static final ViewerMenuItemConstructor[] FAST_OPTION_OR_TOOLBAR_MENU = ((ViewerMenuItemConstructor[]) new ArrayList<ViewerMenuItemConstructor>() {
        {
            add(new p(12));
            add(new p(13));
            add(new p(25));
            if (PocFeatures.isEnabled(PocFeatures.WifiGalleryClient)) {
                add(new q(0));
            }
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                add(new q(1));
                add(new q(2));
            }
            add(new q(3));
            if (Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES)) {
                add(new q(5));
            } else if (GenEditMenuItem.SUPPORT) {
                add(new q(6));
                add(new q(7));
                if (GenEditVideoMenuItem.SUPPORT) {
                    add(new p(23));
                }
                add(new q(4));
            } else {
                add(new q(4));
            }
            if (PocFeatures.isEnabled(PocFeatures.RobinService)) {
                add(new q(8));
            }
            add(new q(9));
            add(new q(10));
            add(new q(11));
            add(new q(12));
            add(new q(13));
            add(new q(14));
            add(new p(14));
            add(new p(15));
            add(new p(16));
            add(new p(17));
            add(new p(18));
            add(new p(19));
            add(new p(20));
            add(new p(21));
            add(new p(22));
            add(new p(24));
            if (PreferenceFeatures.Poc.VIDEO_PLAY_ICON_ON_TOOLBAR) {
                add(new p(26));
            }
            add(new p(27));
            add(new p(28));
            add(new p(29));
        }
    }.toArray(new ViewerMenuItemConstructor[0]));
    private static final ViewerMenuItemConstructor[] MORE_MENU_GROUP_1 = ((ViewerMenuItemConstructor[]) new ArrayList<ViewerMenuItemConstructor>() {
        {
            add(new q(15));
            if (!PreferenceFeatures.Poc.VIDEO_PLAY_ICON_ON_TOOLBAR) {
                add(new p(26));
            }
            add(new q(21));
            add(new q(22));
            add(new q(23));
            add(new q(24));
            add(new q(25));
            if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS) {
                add(new q(27));
                add(new q(28));
            }
            if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS) {
                add(new q(29));
            }
            add(new q(26));
            add(new r(0));
            if (PreferenceFeatures.OneUi7x.IS_ONE_UI_70 && Features.isEnabled(Features.SUPPORT_HDR2SDR_MAX_8K)) {
                add(new r(1));
                add(new r(2));
            }
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                add(new r(3));
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5)) {
                add(new r(4));
            }
            add(new r(5));
            add(new r(6));
            add(new r(7));
            add(new q(16));
            add(new q(17));
            if (PocFeatures.isEnabled(PocFeatures.RemoveBackgroundEffectInfo)) {
                add(new q(18));
            }
            if (PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE_SAVE_AS) {
                add(new q(19));
            }
            if (PocFeatures.SUPPORT_SEARCH_JUMP_TO_TIMELINE) {
                add(new q(20));
            }
        }
    }.toArray(new ViewerMenuItemConstructor[0]));
    private static final ViewerMenuItemConstructor[] MORE_MENU_GROUP_2 = {new p(0), new p(5), new p(6), new p(7), new p(8)};
    private static final ViewerMenuItemConstructor[] MORE_MENU_GROUP_3;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ViewerMenuItemConstructor extends BiFunction<EventContext, ActionInvoker, ViewerMenuItem> {
    }

    static {
        p pVar;
        p pVar2 = new p(9);
        p pVar3 = new p(10);
        p pVar4 = new p(11);
        p pVar5 = new p(1);
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            pVar = new p(2);
        } else {
            pVar = null;
        }
        MORE_MENU_GROUP_3 = new ViewerMenuItemConstructor[]{pVar2, pVar3, pVar4, pVar5, pVar, new p(3), new p(4)};
    }

    private static void addToMenuMap(ViewerMenuMap viewerMenuMap, ViewerMenuItemConstructor[] viewerMenuItemConstructorArr, EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        for (ViewerMenuItemConstructor viewerMenuItemConstructor : viewerMenuItemConstructorArr) {
            if (viewerMenuItemConstructor != null) {
                ViewerMenuItem viewerMenuItem = (ViewerMenuItem) viewerMenuItemConstructor.apply(eventContext, actionInvoker);
                viewerMenuItem.setGroupId(i2);
                viewerMenuMap.addMenuItem(viewerMenuItem);
            }
        }
    }

    private static ViewerMenuMap createDefaultMenuMap(EventContext eventContext, ActionInvoker actionInvoker) {
        int i2;
        ViewerMenuMap viewerMenuMap = new ViewerMenuMap();
        ViewerMenuItemConstructor[][] viewerMenuItemConstructorArr = {FAST_OPTION_OR_TOOLBAR_MENU, MORE_MENU_GROUP_1, MORE_MENU_GROUP_2, MORE_MENU_GROUP_3};
        for (int i7 = 0; i7 < 4; i7++) {
            ViewerMenuItemConstructor[] viewerMenuItemConstructorArr2 = viewerMenuItemConstructorArr[i7];
            if (i7 == 0) {
                i2 = 1;
            } else {
                i2 = i7;
            }
            addToMenuMap(viewerMenuMap, viewerMenuItemConstructorArr2, eventContext, actionInvoker, i2);
        }
        viewerMenuMap.addMenuItem(new MtpDetailsMenuItem(eventContext, actionInvoker), new MtpImportMenuItem(eventContext, actionInvoker));
        if (PocFeatures.TBD.OPEN_IN_OTHER_WINDOW) {
            viewerMenuMap.addMenuItem(new OpenInOtherWindowMenuItem(eventContext, actionInvoker));
        }
        return viewerMenuMap;
    }

    private static ViewerMenuMap createSharingFamilyAlbumMenuMap(EventContext eventContext, ActionInvoker actionInvoker) {
        ViewerMenuMap viewerMenuMap = new ViewerMenuMap();
        viewerMenuMap.addMenuItem(new SharingFamilyAddToAlbumMenuItem(eventContext, actionInvoker), new SharingFamilyRemoveSuggestionMenuItem(eventContext, actionInvoker), new DetailsMenuItem(eventContext, actionInvoker));
        return viewerMenuMap;
    }

    public static ViewerMenuMap createSingleTakenSelectionMenu(EventContext eventContext, ActionInvoker actionInvoker) {
        ViewerMenuMap viewerMenuMap = new ViewerMenuMap();
        viewerMenuMap.addMenuItem(new SingleTakeBestImageMenuItem(eventContext, actionInvoker), new GroupPanelSingleTakeShareMenuItem(eventContext, actionInvoker), new GroupPanelSingleTakeSaveMenuItem(eventContext, actionInvoker), new SingleTakeGroupShotDeleteMenuItem(eventContext, actionInvoker));
        return viewerMenuMap;
    }

    public static ViewerMenuMap createViewerMenu(EventContext eventContext, ActionInvoker actionInvoker) {
        if (LocationKey.isSharingFamilyAlbumSuggested(eventContext.getLocationKey())) {
            return createSharingFamilyAlbumMenuMap(eventContext, actionInvoker);
        }
        return createDefaultMenuMap(eventContext, actionInvoker);
    }
}
