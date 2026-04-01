package com.samsung.android.gallery.app.activity.external;

import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.remote.SConnectUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.IntentAction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ViewNavigatorExternalControllerFactory {
    public static ViewNavigatorController create(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        LaunchIntent launchIntent = iGalleryActivityView.getLaunchIntent();
        String action = launchIntent.getAction();
        if (IntentAction.isForPick(action)) {
            return new PickerViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isForDeepLink(launchIntent)) {
            return new LinkViewNavController(blackboard, iGalleryActivityView);
        }
        if (isForSharings(launchIntent, action)) {
            return new SharingsViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isForStories(launchIntent, action)) {
            return new StoriesViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isForPictures(launchIntent, action)) {
            return new PicturesViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isForAlbum(action)) {
            return new AlbumViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isForQuickView(launchIntent, action) || IntentAction.isForSearchView(action) || SConnectUtil.isSConnectIntent(action)) {
            return new QuickViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (IntentAction.isForCrop(action)) {
            return new CropImageNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isShortCut(launchIntent, action) || IntentAction.isForTrash(action)) {
            return new ShortcutViewNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isHidRule(action)) {
            return new HideRuleNavigatorController(blackboard, iGalleryActivityView);
        }
        if (isForFaceTagging(action)) {
            return new FaceTaggingNavigatorController(blackboard, iGalleryActivityView);
        }
        return new ViewNavigatorController(blackboard, iGalleryActivityView);
    }

    private static boolean isForAlbum(String str) {
        return IntentAction.isForAlbumView(str);
    }

    private static boolean isForDeepLink(LaunchIntent launchIntent) {
        return LocationKey.isDeepLink(launchIntent.getUriData());
    }

    private static boolean isForFaceTagging(String str) {
        return false;
    }

    private static boolean isForPictures(LaunchIntent launchIntent, String str) {
        if (IntentAction.isForPicturesView(str)) {
            return true;
        }
        if (!IntentAction.isForView(str) || !launchIntent.isCursorDirBaseType()) {
            return false;
        }
        return true;
    }

    private static boolean isForQuickView(LaunchIntent launchIntent, String str) {
        if (!IntentAction.isForView(str) || launchIntent.isViewRequestFromShortcut()) {
            return false;
        }
        return true;
    }

    private static boolean isForSharings(LaunchIntent launchIntent, String str) {
        if (IntentAction.isForSharedView(str) || IntentAction.isForFamilyAlbumView(str) || IntentAction.isForSharedPicturesView(str) || IntentAction.isForAddContentsToSharedView(str)) {
            return true;
        }
        if (!IntentAction.isForView(str)) {
            return false;
        }
        if (launchIntent.isStartSharedView() || launchIntent.isStartSharedDetailView() || launchIntent.isStartSharingInvitationsView() || launchIntent.isStartSharingStorageUseView()) {
            return true;
        }
        return false;
    }

    private static boolean isForStories(LaunchIntent launchIntent, String str) {
        if (!IntentAction.isForView(str)) {
            return false;
        }
        if (launchIntent.isFromReminder() || launchIntent.isViewStoryPicture()) {
            return true;
        }
        return false;
    }

    private static boolean isHidRule(String str) {
        return IntentAction.isForHideRule(str);
    }

    private static boolean isShortCut(LaunchIntent launchIntent, String str) {
        if (IntentAction.isForShortCut(str) || launchIntent.isViewRequestFromShortcut()) {
            return true;
        }
        return false;
    }
}
