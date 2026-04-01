package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuFactory;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements ViewerMenuFactory.ViewerMenuItemConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2601a;

    public /* synthetic */ p(int i2) {
        this.f2601a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f2601a) {
            case 0:
                return new CopyToClipboardMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 1:
                return new MoveToSecureFolderMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 2:
                return new MoveToPrivateMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 3:
                return new RemoveFromSecureFolderMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 4:
                return new PrinterMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 5:
                return new PasteClipboardMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 6:
                return new CopyToAlbumMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 7:
                return new MoveToAlbumMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 8:
                return new ViewOriginalVideoMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 9:
                return new SetAsWallpaperMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 10:
                return new MoveToKnoxFolderMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 11:
                return new RemoveFromKnoxFolderMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 12:
                return new FavoriteMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 13:
                return new UnFavoriteMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 14:
                return new RemoveSuggestionMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 15:
                return new RemoveSimpleMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 16:
                return new RemoveFromAutoAlbumMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 17:
                return new DeleteMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 18:
                return new SharedAlbumDeleteMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 19:
                return new DeleteWithTextMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 20:
                return new SuggestionDeleteVideoClipMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 21:
                return new GroupShotDeleteMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 22:
                return new SmartViewMenuItem2((EventContext) obj, (ActionInvoker) obj2);
            case 23:
                return new GenEditVideoMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 24:
                return new BixbyVisionMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 25:
                return new DownloadMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 26:
                return new OpenInVideoPlayerMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 27:
                return new ForceRotateMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 28:
                return new DetailsMenuItem((EventContext) obj, (ActionInvoker) obj2);
            default:
                return new SaveDualShotPhotoMenuItem((EventContext) obj, (ActionInvoker) obj2);
        }
    }
}
