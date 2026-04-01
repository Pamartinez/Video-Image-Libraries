package com.samsung.android.gallery.app.ui.list.dragdrop;

import A6.a;
import android.content.ClipData;
import android.net.Uri;
import android.text.TextUtils;
import android.view.DragEvent;
import com.samsung.android.gallery.app.controller.internals.LTWDownloadCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiControlDragAndDrop extends ExtendedDragAndDrop {
    private ArrayList<String> getMimeTypesFromClipData(DragEvent dragEvent) {
        ClipData clipData = dragEvent.getClipData();
        if (clipData == null || clipData.getDescription() == null) {
            Log.w((CharSequence) this.TAG, "getMimeTypesFromClipData skip. null data", Integer.valueOf(dragEvent.getAction()));
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < clipData.getDescription().getMimeTypeCount(); i2++) {
            String mimeType = clipData.getDescription().getMimeType(i2);
            if (!TextUtils.isEmpty(mimeType)) {
                arrayList.add(mimeType);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleDropFromExternal$0(ArrayList arrayList, ArrayList arrayList2, IBaseListView iBaseListView, MediaItem mediaItem) {
        HashMap hashMap;
        ArrayList arrayList3 = new ArrayList();
        if (arrayList == null || arrayList.size() != arrayList2.size()) {
            hashMap = null;
        } else {
            hashMap = new HashMap();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                hashMap.put((Uri) arrayList2.get(i2), (String) arrayList.get(i2));
            }
        }
        if (UriItemLoader.loadMediaItemFromUrisOnSkipException(iBaseListView.getContext(), arrayList2, arrayList3, hashMap)) {
            new LTWDownloadCmd().execute(iBaseListView.getPresenter(), (MediaItem[]) arrayList3.toArray(new MediaItem[0]), mediaItem);
        } else {
            Utils.showToast(iBaseListView.getContext(), (int) R.string.file_format_not_supported);
        }
    }

    public boolean handleDropFromExternal(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (isInvalidTargetItem(mediaItem)) {
            String str = this.TAG;
            Log.w(str, "handleDropFromExternal isInvalidTargetItem : " + mediaItem);
            return false;
        }
        ArrayList<Uri> uriListFromClipData = getUriListFromClipData(dragEvent.getClipData());
        if (uriListFromClipData == null || uriListFromClipData.isEmpty()) {
            Log.w(this.TAG, "handleDropFromExternal uriList is null or empty!");
            return false;
        } else if (!isValidDropEvent(dragEvent)) {
            return true;
        } else {
            SimpleThreadPool.getInstance().execute(new a((Object) getMimeTypesFromClipData(dragEvent), (Object) uriListFromClipData, (Object) iBaseListView, (Object) mediaItem, 19));
            return true;
        }
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return TextUtils.equals("startMultiControlDrag", ClipDataUtils.getLabel(dragEvent.getClipDescription()));
    }

    public boolean supportToast() {
        return false;
    }
}
