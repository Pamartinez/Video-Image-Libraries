package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import M9.o;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveRemasteredImageCmd extends BaseCommand {
    private boolean mFromViewer;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(Object[] objArr) {
        remove(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$remove$1() {
        getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    private void remove(MediaItem[] mediaItemArr) {
        String str;
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                if (mediaItem.getPath() != null) {
                    FileUtils.delete(mediaItem.getPath());
                }
                arrayList.add(Long.valueOf(mediaItem.getFileId()));
            }
        }
        if (arrayList.size() > 0) {
            int deleteRevitalizationData = new FilesApi().deleteRevitalizationData(arrayList);
            if (deleteRevitalizationData > 0) {
                if (this.mFromViewer) {
                    str = getContext().getResources().getString(R.string.suggestion_removed);
                } else {
                    str = getContext().getResources().getQuantityString(R.plurals.remove_from_suggestions_plural, deleteRevitalizationData, new Object[]{Integer.valueOf(deleteRevitalizationData)});
                }
                showToast(str);
                MediaData mediaData = getHandler().getMediaData();
                if (mediaData == null || mediaData.getCount() - deleteRevitalizationData != 0) {
                    getBlackboard().postEvent(EventMessage.obtain(3015));
                } else {
                    ThreadUtil.postOnBgThreadDelayed(new o(14, this), 100);
                }
                BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
                getBlackboard().postEvent(EventMessage.obtain(1003));
                return;
            }
            Log.e(this.TAG, "failed, deleteRevitalizationData");
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_REMOVE_SUGGESTION.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mFromViewer = LocationKey.isContentViewer(eventContext.getLocationKey());
        SimpleThreadPool.getInstance().execute(new a(18, this, objArr));
    }
}
