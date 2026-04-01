package com.samsung.android.gallery.app.controller.story;

import B8.j;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.ChangeCoverCmd;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.CoverPickType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.sum.core.message.Message;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeStoryCoverCmd extends ChangeCoverCmd {
    private int mStoryId;
    protected boolean mWithContentsOrder;

    private String finalCoverRectRatio(String str) {
        if (isValidCoverRatio(str)) {
            return str;
        }
        return "0, 0, 0, 0";
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isValidCoverRatio(java.lang.String r2) {
        /*
            r1 = this;
            java.util.StringTokenizer r1 = new java.util.StringTokenizer
            java.lang.String r0 = ","
            r1.<init>(r2, r0)
        L_0x0007:
            boolean r2 = r1.hasMoreTokens()
            if (r2 == 0) goto L_0x0022
            java.lang.String r2 = r1.nextToken()
            float r2 = java.lang.Float.parseFloat(r2)
            r0 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0020
            r0 = 0
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0007
        L_0x0020:
            r1 = 0
            return r1
        L_0x0022:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.story.ChangeStoryCoverCmd.isValidCoverRatio(java.lang.String):boolean");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateContentsOrder$0(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2.getFileId() == mediaItem.getFileId()) {
            return true;
        }
        return false;
    }

    private void updateContentsOrder(MediaItem mediaItem) {
        FileItemInterface fileItemInterface = (FileItemInterface) Arrays.stream(getHandler().getAllItems()).filter(new j(mediaItem, 3)).findFirst().orElse((Object) null);
        if (fileItemInterface != null) {
            DbCompat.storyApi().updateContentOrder(this.mStoryId, Collections.singletonList(fileItemInterface));
        } else {
            Log.e((CharSequence) this.TAG, "not found from story album", MediaItemUtil.getSimpleLog(mediaItem));
        }
    }

    public void changeCover(MediaItem mediaItem, String str) {
        if (DbCompat.storyApi().changeStoryCover(this.mStoryId, mediaItem.getFileId(), finalCoverRectRatio(str))) {
            if (this.mWithContentsOrder) {
                updateContentsOrder(mediaItem);
            }
            getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
            Blackboard.publishGlobal("data://user/storyUpdated", (Object) null);
            if (getHandler().isSelectionMode()) {
                getBlackboard().postEvent(EventMessage.obtain(1003));
                return;
            }
            return;
        }
        Log.e(this.TAG, "changeStoryCover is failed");
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CHANGE_COVER_IMAGE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        Boolean bool;
        if (objArr != null && objArr.length != 0) {
            boolean z3 = false;
            MediaItem mediaItem = objArr[0];
            Boolean bool2 = objArr[1];
            if (bool2 == null || !bool2.booleanValue()) {
                z = false;
            } else {
                z = true;
            }
            if (objArr.length > 2 && (bool = objArr[2]) != null && bool.booleanValue()) {
                z3 = true;
            }
            this.mWithContentsOrder = z3;
            if (mediaItem == null) {
                Log.e(this.TAG, eventContext.getLocationKey() + " : item is null");
                return;
            }
            this.mMediaItem = mediaItem;
            this.mStoryId = mediaItem.getAlbumID();
            if (z) {
                setCoverByPickAndCrop();
            } else if (PreviewFactory.isPreviewableFormat(mediaItem)) {
                changeCover(mediaItem);
            } else {
                setCoverByCrop();
            }
        }
    }

    public void setCoverByCrop() {
        int[] preferCoverCropRatio = StoryHelper.getPreferCoverCropRatio(this.mMediaItem);
        UriBuilder makeCommonDataByCrop = makeCommonDataByCrop(preferCoverCropRatio[0], preferCoverCropRatio[1]);
        makeCommonDataByCrop.appendArg("FromStoryCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.FROM_STORY.toInt()).appendArg("key-story-album-bucket-id", this.mStoryId).appendArg("pick-from-gallery", true);
        Uri uri = ContentUri.getUri(this.mMediaItem);
        if (uri != null) {
            makeCommonDataByCrop.appendArg("content_uri", uri.toString());
        }
        launchCropper(makeCommonDataByCrop.build());
    }

    public void setCoverByPickAndCrop() {
        launchPickerForCrop(makeCommonDataByPickAndCrop().appendArg("FromStoryCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.FROM_STORY.toInt()).appendArg("key-story-album-bucket-id", this.mStoryId).appendArg("key-story-list-position", ArgumentsUtil.getArgValue((String) getBlackboard().read("location://variable/currentv1"), Message.KEY_POSITION)).appendArg("key-current-cover-item", Long.toString(this.mMediaItem.getFileId())).appendArg("disable_timeline_divider", true).build());
    }
}
