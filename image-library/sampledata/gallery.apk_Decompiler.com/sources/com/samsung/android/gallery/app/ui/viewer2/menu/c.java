package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2592a;

    public /* synthetic */ c(int i2) {
        this.f2592a = i2;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        switch (this.f2592a) {
            case 0:
                return ExportMotionPhotoMenuItem.lambda$setMenuAttribute$3(mediaItem, str);
            case 1:
                return ExportMotionPhotoMenuItem.lambda$setMenuAttribute$4(mediaItem, str);
            case 2:
                return FavoriteMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 3:
                return FavoriteMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 4:
                return ForceRotateMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 5:
                return GroupPanelSingleTakeShareMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 6:
                return JumpToTimelineMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 7:
                return MoveFromPrivateMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 8:
                return ViewerMenuItem.supportKnoxMoveType(KnoxUtil.MoveType.MOVE_TO_KNOX);
            case 9:
                return ViewerMenuItem.supportKnoxMoveType(KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
            case 10:
                return OpenInOtherWindowMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 11:
                return PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer();
            case 12:
                return PasteClipboardMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 13:
                return PasteClipboardMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 14:
                return PasteClipboardMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
            case 15:
                return PasteEffectMenu.lambda$setMenuAttribute$0(mediaItem, str);
            case 16:
                return Features.isEnabled(Features.SUPPORT_COPY_PASTE_EFFECTS);
            case 17:
                return RemasterMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 18:
                return PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_REMASTER;
            case 19:
                return ViewerMenuItem.isRemasteringImage(mediaItem);
            case 20:
                return ViewerMenuItem.isRemasteringImage(mediaItem);
            case 21:
                return ViewerMenuItem.isRemasteringImage(mediaItem);
            case 22:
                return RemoveBackgroundEffectMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 23:
                return RemoveBackgroundEffectMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 24:
                return RemoveFromAutoAlbumMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 25:
                return ViewerMenuItem.supportKnoxMoveType(KnoxUtil.MoveType.REMOVE_FROM_KNOX);
            case 26:
                return ViewerMenuItem.supportKnoxMoveType(KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER);
            case 27:
                return LocationKey.isStoryPictures(str);
            case 28:
                return Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_VIEWER);
            default:
                return RobinMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
        }
    }
}
