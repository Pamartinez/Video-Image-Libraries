package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.aiedit.PortraitEffectUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2590a;

    public /* synthetic */ a(int i2) {
        this.f2590a = i2;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        switch (this.f2590a) {
            case 0:
                return PortraitEffectUtil.supportPortraitEffect(mediaItem);
            case 1:
                return BixbyVisionMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 2:
                return BixbyVisionMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 3:
                return Features.isEnabled(Features.SUPPORT_VISION_INTELLIGENCE);
            case 4:
                return ChangeSharingCoverMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 5:
                return LocationKey.isStoryPictures(str);
            case 6:
                return Features.isEnabled(Features.SUPPORT_STORY_COVER);
            case 7:
                return ConvertAPVToHEVCMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 8:
                return ConvertHDR10PlusToSDRMenu.lambda$setMenuAttribute$1(mediaItem, str);
            case 9:
                return ConvertHEIFToJPEGMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 10:
                return CopyEffectMenu.lambda$setMenuAttribute$0(mediaItem, str);
            case 11:
                return Features.isEnabled(Features.SUPPORT_COPY_PASTE_EFFECTS);
            case 12:
                return CopyToClipboardMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 13:
                return CreateGifMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 14:
                return CreateVideoGifMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 15:
                return CreateVideoGifMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 16:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.CreateVideoGifMenu);
            case 17:
                return CreateVideoGifMenuItem.lambda$setMenuAttribute$3(mediaItem, str);
            case 18:
                return PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER;
            case 19:
                return DeleteVideoClipMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 20:
                return DeleteVideoClipMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
            case 21:
                return DeleteVideoClipMenuItem.lambda$setMenuAttribute$3(mediaItem, str);
            case 22:
                return DeleteVideoClipMenuItem.lambda$setMenuAttribute$4(mediaItem, str);
            case 23:
                return DetailsMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 24:
                return DetailsMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            case 25:
                return DownloadFromRemoteMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 26:
                return ViewerMenuItem.isCloudOnly(mediaItem);
            case 27:
                return PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER;
            case 28:
                return ExportMotionPhotoMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            default:
                return ExportMotionPhotoMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
        }
    }
}
