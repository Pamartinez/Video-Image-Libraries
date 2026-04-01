package com.samsung.android.gallery.app.ui.list.dragdrop;

import R6.c;
import android.net.Uri;
import android.view.DragEvent;
import com.samsung.android.gallery.app.controller.internals.LTWDownloadCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MSYourPhoneDragAndDrop extends ExtendedDragAndDrop {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleDropFromExternal$0(IBaseListView iBaseListView, ArrayList arrayList, MediaItem mediaItem) {
        ArrayList arrayList2 = new ArrayList();
        if (UriItemLoader.loadMediaItemFromUrisOnSkipException(iBaseListView.getContext(), arrayList, arrayList2)) {
            new LTWDownloadCmd().execute(iBaseListView.getPresenter(), (MediaItem[]) arrayList2.toArray(new MediaItem[0]), mediaItem);
        } else {
            Utils.showToast(iBaseListView.getContext(), (int) R.string.file_format_not_supported);
        }
    }

    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (!YourPhone.isConnected(iBaseListView.getContext()) || !isValidDropEvent(dragEvent)) {
            Utils.showToast(iBaseListView.getContext(), (int) R.string.drag_and_drop_not_supported, 0);
            return false;
        } else if (isInvalidTargetItem(mediaItem)) {
            String str = this.TAG;
            Log.d(str, "Invalid drop target, Will be copied to 'Downloads' folder. targetItem = " + mediaItem);
            return false;
        } else if (!YourPhone.isValidMimeType(iBaseListView.getContext(), dragEvent)) {
            Utils.showToast(iBaseListView.getContext(), (int) R.string.file_format_not_supported);
            return false;
        } else {
            ArrayList<Uri> uriListFromClipData = getUriListFromClipData(dragEvent.getClipData());
            if (uriListFromClipData == null || uriListFromClipData.isEmpty()) {
                Log.w(this.TAG, "getUriListFromClipData uriList is null or empty!");
                return false;
            }
            SimpleThreadPool.getInstance().execute(new c(iBaseListView, uriListFromClipData, mediaItem, 5));
            return true;
        }
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        if (ClipDataUtils.getObjectExtra(dragEvent.getClipData(), "com.microsoft.appmanager") != null) {
            return true;
        }
        return false;
    }
}
