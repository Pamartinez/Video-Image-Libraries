package com.samsung.android.gallery.app.ui.list.dragdrop;

import A6.a;
import Bd.C0726b;
import android.content.ClipData;
import android.net.Uri;
import android.view.DragEvent;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoHandedDragAndDrop extends ExtendedDragAndDrop {
    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem[] lambda$getSelectedItemsSupplier$0(ClipData clipData) {
        ArrayList<Uri> uriListFromClipData = getUriListFromClipData(clipData);
        if (uriListFromClipData == null || uriListFromClipData.isEmpty()) {
            Log.w(this.TAG, "getSelectedItemsSupplier uriList is null or empty!");
            return new MediaItem[0];
        }
        ArrayList arrayList = new ArrayList();
        if (UriItemLoader.loadMediaItemFromUris(uriListFromClipData, arrayList)) {
            return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
        }
        return new MediaItem[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startFileOperation$1(IBaseListView iBaseListView, ClipData clipData, MediaItem mediaItem) {
        MediaItem[] mediaItemArr = getSelectedItemsSupplier(iBaseListView.getPresenter(), clipData).get();
        if (mediaItemArr.length > 0) {
            new FileOpCmd().execute(iBaseListView.getPresenter(), FileOpCmdHelper.CmdType.TYPE_DRAG_TO_ITEMS, mediaItemArr, mediaItem, null);
        }
    }

    public Supplier<MediaItem[]> getSelectedItemsSupplier(EventContext eventContext, ClipData clipData) {
        return new C0726b(1, this, clipData);
    }

    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        return false;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return false;
    }

    public void startFileOperation(IBaseListView iBaseListView, ClipData clipData, MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new a((Object) this, (Object) iBaseListView, (Object) clipData, (Object) mediaItem, 20));
    }
}
