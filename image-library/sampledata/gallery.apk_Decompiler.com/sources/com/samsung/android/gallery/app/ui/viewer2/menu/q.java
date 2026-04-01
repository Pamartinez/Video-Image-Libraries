package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuFactory;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements ViewerMenuFactory.ViewerMenuItemConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2602a;

    public /* synthetic */ q(int i2) {
        this.f2602a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f2602a) {
            case 0:
                return new DownloadFromRemoteMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 1:
                return new MoveFromPrivateMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 2:
                return new TrashRestorePrivateMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 3:
                return new EditMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 4:
                return new DetailsFastOptionMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 5:
                return new AwesomeIntelligenceMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 6:
                return new GenEditMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 7:
                return new GenEditDisabledMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 8:
                return new RobinMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 9:
                return new ShareMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 10:
                return new RemasterShareMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 11:
                return new SuggestionShareMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 12:
                return new RemasterSaveMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 13:
                return new SaveAsCopyMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 14:
                return new TrashRestoreMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 15:
                return new RemasterSaveAsMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 16:
                return new ExportMotionPhotoMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 17:
                return new DeleteVideoClipMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 18:
                return new RemoveBackgroundEffectMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 19:
                return new SaveAsMotionPhotoMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 20:
                return new JumpToTimelineMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 21:
                return new DownloadMoreMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 22:
                return new CreateGifMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 23:
                return new ChangeSharingCoverMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 24:
                return new RemoveFromStoryMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 25:
                return new ChangeStoryCoverMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 26:
                return new CopyEffectMenu((EventContext) obj, (ActionInvoker) obj2);
            case 27:
                return new RemasterMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 28:
                return new GifRemasterMenuItem((EventContext) obj, (ActionInvoker) obj2);
            default:
                return new AddPortraitEffectMenuItem((EventContext) obj, (ActionInvoker) obj2);
        }
    }
}
