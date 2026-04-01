package com.samsung.android.gallery.app.controller.internals;

import A5.a;
import Ad.C0720a;
import Fa.C0571z;
import O3.o;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeepCleanOutCmd extends BaseCommand {
    private boolean mFromViewer;

    /* access modifiers changed from: private */
    public long getId(MediaItem mediaItem) {
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SUGGESTIONS)) {
            return mediaItem.getFileId();
        }
        return mediaItem.getMediaId();
    }

    private ArrayList<Long> getSelectedContentsIds(MediaItem[] mediaItemArr) {
        return (ArrayList) Arrays.stream(mediaItemArr).filter(new C0571z(4)).map(new a(17, this)).collect(Collectors.toCollection(new C0720a(1)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr) {
        int keepCleanOutItems = SuggestedHelper.getInstance().keepCleanOutItems(getContext(), getSelectedContentsIds(mediaItemArr));
        postEventsForResult();
        showResultToast(keepCleanOutItems);
    }

    private void postEventsForResult() {
        BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        getBlackboard().postEvent(EventMessage.obtain(1057));
        getBlackboard().postEvent(EventMessage.obtain(1003));
        getBlackboard().postEvent(EventMessage.obtain(3015));
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

    private void updateMotionPhotoCleanOut(MediaItem[] mediaItemArr) {
        PreferenceCache.MotionPhotoCleanOutExcluded.append((String) Arrays.stream(mediaItemArr).map(new o(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_REMOVE_SUGGESTION.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mFromViewer = LocationKey.isContentViewer(eventContext.getLocationKey());
        MediaItem[] mediaItemArr = objArr[0];
        if (mediaItemArr != null && mediaItemArr.length != 0) {
            if (LocationKey.isCleanOutMotionPhoto(eventContext.getLocationKey())) {
                updateMotionPhotoCleanOut(mediaItemArr);
                postEventsForResult();
                showResultToast(mediaItemArr.length);
                return;
            }
            ThreadUtil.postOnBgThread(new M5.a(14, this, mediaItemArr));
        }
    }
}
