package com.samsung.android.gallery.app.ui.list.dragdrop;

import B5.c;
import android.net.Uri;
import android.view.DragEvent;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexDragAndDrop extends ExtendedDragAndDrop {
    private String getOperationId(DragEvent dragEvent) {
        String str;
        if (dragEvent.getClipDescription() == null || dragEvent.getClipDescription().getExtras() == null) {
            str = null;
        } else {
            str = dragEvent.getClipDescription().getExtras().getString("operation");
        }
        if ("move".equals(str)) {
            return "move";
        }
        return "copy";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDropFromExternal$0(IBaseListView iBaseListView, ArrayList arrayList, MediaItem mediaItem, DragEvent dragEvent) {
        ArrayList arrayList2 = new ArrayList();
        if (UriItemLoader.loadMediaItemFromUrisOnSkipException(iBaseListView.getContext(), arrayList, arrayList2)) {
            new FileOpCmd().execute(iBaseListView.getPresenter(), FileOpCmdHelper.CmdType.TYPE_DRAG_TO_ITEMS, (MediaItem[]) arrayList2.toArray(new MediaItem[0]), mediaItem, getOperationId(dragEvent));
            return;
        }
        Utils.showToast(iBaseListView.getContext(), (int) R.string.file_format_not_supported);
    }

    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (isInvalidTargetItem(mediaItem)) {
            Utils.showToast(iBaseListView.getContext(), (int) R.string.drag_and_drop_not_supported, 0);
            return false;
        }
        ArrayList<Uri> uriListFromClipData = getUriListFromClipData(dragEvent.getClipData());
        if (uriListFromClipData == null || uriListFromClipData.isEmpty()) {
            Log.w(this.TAG, "getUriListFromClipData uriList is null or empty!");
            return false;
        }
        SimpleThreadPool.getInstance().execute(new c(this, iBaseListView, uriListFromClipData, mediaItem, dragEvent, 9));
        return true;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        try {
            return dragEvent.getClipDescription().getExtras().containsKey("secdndfiletype");
        } catch (NullPointerException unused) {
            return false;
        }
    }
}
