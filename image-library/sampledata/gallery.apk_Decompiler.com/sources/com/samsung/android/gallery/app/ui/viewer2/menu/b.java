package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2591a;
    public final /* synthetic */ ViewerMenuItem b;

    public /* synthetic */ b(ViewerMenuItem viewerMenuItem, int i2) {
        this.f2591a = i2;
        this.b = viewerMenuItem;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        int i2 = this.f2591a;
        ViewerMenuItem viewerMenuItem = this.b;
        switch (i2) {
            case 0:
                return ((AwesomeIntelligenceMenuItem) viewerMenuItem).lambda$setMenuAttribute$1(mediaItem, str);
            case 1:
                return ((ChangeSharingCoverMenuItem) viewerMenuItem).supportChangeSharingCover(mediaItem, str);
            case 2:
                return ((ConvertAPVToHEVCMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 3:
                return ((ConvertHDR10PlusToSDRMenu) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 4:
                return ((CopyEffectMenu) viewerMenuItem).lambda$setMenuAttribute$2(mediaItem, str);
            case 5:
                return ((CopyToAlbumMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 6:
                return ((DetailsFastOptionMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 7:
                return ((DetailsMenuItem) viewerMenuItem).lambda$setDetailsCommonAttribute$2(mediaItem, str);
            case 8:
                return ((EditMenuItem) viewerMenuItem).lambda$checkSharing$8(mediaItem, str);
            case 9:
                return ((GenEditDisabledMenuItem) viewerMenuItem).lambda$setMenuAttribute$1(mediaItem, str);
            case 10:
                return ((GenEditMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 11:
                return ((GenEditVideoMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 12:
                return ((MoveToAlbumMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 13:
                return ((OpenInOtherWindowMenuItem) viewerMenuItem).lambda$setMenuAttribute$1(mediaItem, str);
            case 14:
                return ((OpenInVideoPlayerMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 15:
                return ((PasteClipboardMenuItem) viewerMenuItem).lambda$setMenuAttribute$3(mediaItem, str);
            case 16:
                return ((RemasterMenuItem) viewerMenuItem).lambda$setMenuAttribute$1(mediaItem, str);
            case 17:
                return ((RemoveLiveEffectMenuItem) viewerMenuItem).lambda$setMenuAttribute$1(mediaItem, str);
            case 18:
                return ((RevertToOriginalMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            case 19:
                return ((SetAsWallpaperMenuItem) viewerMenuItem).lambda$setMenuAttribute$0(mediaItem, str);
            default:
                return ((ShareMenuItem) viewerMenuItem).lambda$setMenuAttribute$2(mediaItem, str);
        }
    }
}
