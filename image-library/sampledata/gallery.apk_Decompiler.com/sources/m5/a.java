package M5;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.google.gson.JsonObject;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CopyEffectCmd;
import com.samsung.android.gallery.app.controller.externals.PasteEffectCmd;
import com.samsung.android.gallery.app.controller.externals.ShareAlbumCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.externals.StartPrintCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.app.controller.internals.KeepCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.LTWDownloadCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveLiveEffectCmd;
import com.samsung.android.gallery.app.controller.internals.RemovePortraitCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveRemasteredImageCmd;
import com.samsung.android.gallery.app.controller.internals.RobinServiceCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsCopyCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsPdfCmd;
import com.samsung.android.gallery.app.controller.internals.SavePortraitEffectCmd;
import com.samsung.android.gallery.app.controller.internals.UploadToRemoteCmd;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoiceAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoiceViewHolder;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharedAlbumLinkTip;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolder;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.CleanOutPicturesFragment;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SharingAlbumChoiceAdapter) this.e).lambda$onBindViewHolder$0((ListViewHolder) this.f);
                return;
            case 1:
                ((SharingAlbumChoicePresenter) this.e).lambda$addToSharedAlbum$4((MediaItem) this.f);
                return;
            case 2:
                ((SharingAlbumChoiceViewHolder) this.e).lambda$bindImage$0((Bitmap) this.f);
                return;
            case 3:
                ((StoriesViewHolder) this.e).lambda$bindImage$0((View) this.f);
                return;
            case 4:
                ((CopyEffectCmd) this.e).lambda$onExecute$0((MediaItem) this.f);
                return;
            case 5:
                ((PasteEffectCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f);
                return;
            case 6:
                ((ShareAlbumCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f);
                return;
            case 7:
                ((ShareViaCmd) this.e).lambda$perform$2((MediaItem[]) this.f);
                return;
            case 8:
                ((StartPrintCmd) this.e).lambda$onExecute$0((MediaItem) this.f);
                return;
            case 9:
                ((StartPrintCmd) this.e).lambda$onExecute$2((MediaItem[]) this.f);
                return;
            case 10:
                ((ObjectCaptureHelper) this.e).lambda$onMenuClick$3((MenuItem) this.f);
                return;
            case 11:
                ((PhotoPreView) this.e).lambda$setBasicInfo$0((Bitmap) this.f);
                return;
            case 12:
                ((ChangeBestItemCmd) this.e).lambda$onCompleted$2((MediaItem) this.f);
                return;
            case 13:
                ((ChangeBestItemCmd2) this.e).lambda$onCompleted$4((MediaItem) this.f);
                return;
            case 14:
                ((KeepCleanOutCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f);
                return;
            case 15:
                ((LTWDownloadCmd) this.e).lambda$onExecute$0((MediaItem) this.f);
                return;
            case 16:
                ((RemoveLiveEffectCmd) this.e).lambda$executeInternal$3((MediaItem) this.f);
                return;
            case 17:
                ((RemovePortraitCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f);
                return;
            case 18:
                ((RemoveRemasteredImageCmd) this.e).lambda$onExecute$0((Object[]) this.f);
                return;
            case 19:
                ((RobinServiceCmd) this.e).lambda$execute$0((MediaItem) this.f);
                return;
            case 20:
                ((SaveAsCopyCmd) this.e).lambda$onExecute$0((MediaItem) this.f);
                return;
            case 21:
                ((SaveAsPdfCmd) this.e).lambda$save$3((List) this.f);
                return;
            case 22:
                ((SavePortraitEffectCmd) this.e).lambda$onExecute$0((MediaItem) this.f);
                return;
            case 23:
                ((SavePortraitEffectCmd) this.e).lambda$handleSaveAsCopy$2((Uri) this.f);
                return;
            case 24:
                UploadToRemoteCmd.lambda$onComplete$1((EventContext) this.e, (MediaItem[]) this.f);
                return;
            case 25:
                ((SharedAlbumLinkTip) this.e).lambda$show$0((View) this.f);
                return;
            case 26:
                ((SharingPicturesFragment) this.e).lambda$checkTipCard$21((Pair) this.f);
                return;
            case 27:
                ((CleanOutPicturesFragment) this.e).lambda$adjustEmptyViewMargin$1((FloatingToolbarLayout) this.f);
                return;
            case 28:
                ((TextExtractionHelper) this.e).lambda$getSuggestion$10((JsonObject) this.f);
                return;
            default:
                ((TextExtractionHelper) this.e).lambda$extract$0((Bitmap) this.f);
                return;
        }
    }
}
