package com.samsung.android.gallery.app.controller.internals;

import A6.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveHighlightCmd extends BaseCommand {
    private boolean mFromViewer;

    private ArrayList<Long> getSelectedContentsIds(MediaItem[] mediaItemArr) {
        ArrayList<Long> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                arrayList.add(Long.valueOf(mediaItem.getFileId()));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr, Object[] objArr, EventContext eventContext) {
        int deleteHighlightTime = new FilesApi().deleteHighlightTime(getSelectedContentsIds(mediaItemArr));
        if (needsToShowToast(objArr)) {
            showResultToast(deleteHighlightTime);
        }
        BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
        getBlackboard().postEvent(EventMessage.obtain(1003));
        if (LocationKey.isHighLightPictures(eventContext.getLocationKey())) {
            getBlackboard().postEvent(EventMessage.obtain(3015));
        } else if (LocationKey.is2ndDepthSuggestionHighlightView(eventContext.getLocationKey())) {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        }
    }

    private boolean needsToShowToast(Object... objArr) {
        if (objArr.length < 2 || objArr[1].booleanValue()) {
            return true;
        }
        return false;
    }

    private void showResultToast(int i2) {
        String str;
        if (this.mFromViewer) {
            str = getContext().getResources().getString(R.string.suggestion_removed);
        } else {
            str = getContext().getResources().getQuantityString(R.plurals.remove_from_suggestions_plural, i2, new Object[]{Integer.valueOf(i2)});
        }
        showToast(str);
    }

    public String getEventId() {
        return super.getEventId();
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mFromViewer = LocationKey.isContentViewer(eventContext.getLocationKey());
        ThreadUtil.postOnBgThread(new a((Object) this, (Object) objArr[0], (Object) objArr, (Object) eventContext, 13));
    }
}
