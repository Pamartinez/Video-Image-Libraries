package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2597a;

    public /* synthetic */ l(int i2) {
        this.f2597a = i2;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        switch (this.f2597a) {
            case 0:
                return PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER;
            case 1:
                return SaveAsMotionPhotoMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 2:
                return SaveAsMotionPhotoMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
            case 3:
                return MediaItemUtil.isMotionPhotoAutoPlayViewMode(mediaItem);
            case 4:
                return SaveAsMotionPhotoMenuItem.lambda$setMenuAttribute$4(mediaItem, str);
            case 5:
                return SaveDualShotPhotoMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 6:
                return ShareMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 7:
                return ShareMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 8:
                return SingleTakeShareMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 9:
                return Features.isEnabled(Features.USE_SMART_MIRRORING);
            case 10:
                return LocationKey.isRemoteDisplaySupportedView(str);
            case 11:
                return SmartViewMenuItem2.lambda$setMenuAttribute$3(mediaItem, str);
            case 12:
                return SmartViewMenuItem2.lambda$setMenuAttribute$4(mediaItem, str);
            case 13:
                return LocationKey.isCleanOutMotionPhoto(str);
            case 14:
                return TrashRestorePrivateMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 15:
                return UnFavoriteMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 16:
                return ViewOriginalVideoMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 17:
                return ViewerMenuItem.lambda$static$18(mediaItem, str);
            case 18:
                return ViewerMenuItem.lambda$static$19(mediaItem, str);
            case 19:
                return ViewerMenuItem.lambda$static$1(mediaItem, str);
            case 20:
                return ViewerMenuItem.lambda$static$20(mediaItem, str);
            case 21:
                return ViewerMenuItem.lambda$static$21(mediaItem, str);
            case 22:
                return ViewerMenuItem.lambda$static$22(mediaItem, str);
            case 23:
                return ViewerMenuItem.lambda$static$23(mediaItem, str);
            case 24:
                return ViewerMenuItem.lambda$static$2(mediaItem, str);
            case 25:
                return ViewerMenuItem.lambda$static$3(mediaItem, str);
            case 26:
                return ViewerMenuItem.lambda$static$4(mediaItem, str);
            case 27:
                return ViewerMenuItem.lambda$static$5(mediaItem, str);
            case 28:
                return ViewerMenuItem.lambda$static$6(mediaItem, str);
            default:
                return ViewerMenuItem.lambda$static$7(mediaItem, str);
        }
    }
}
