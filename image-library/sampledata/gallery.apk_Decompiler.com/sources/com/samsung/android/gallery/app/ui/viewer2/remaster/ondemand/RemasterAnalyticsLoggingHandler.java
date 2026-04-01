package com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand;

import U7.a;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.RemasterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.remaster.ErrorReason;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.BucketUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterAnalyticsLoggingHandler extends ViewerObject {
    private String getAlbumDetail(FileItemInterface fileItemInterface) {
        AnalyticsDetail analyticsDetail;
        if (BucketUtils.isCameras(fileItemInterface.getBucketID())) {
            analyticsDetail = AnalyticsDetail.EVENT_DETAIL_REMASTER_CAMERA_ALBUM;
        } else {
            analyticsDetail = AnalyticsDetail.EVENT_DETAIL_REMASTER_OTHER_ALBUM;
        }
        return analyticsDetail.toString();
    }

    private String getAnalyticsScreenId(EventContext eventContext, MediaItem mediaItem, int i2) {
        if (i2 == 11 || mediaItem.isGif()) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_REMASTER_GIF.toString();
        }
        if (i2 == 9) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_REMOVE_MOIRE.toString();
        }
        if (i2 == 17) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_REMOVE_LENS_FLARE.toString();
        }
        if (i2 == 15) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_FIX_LENS_DISTORTION.toString();
        }
        if (i2 == 19) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_COLORIZE.toString();
        }
        return eventContext.getScreenId();
    }

    private Pair<String, String>[] getCustomDimensions(MediaItem mediaItem, long j2) {
        return new Pair[]{new Pair(AnalyticsDetailKey.RESOLUTION.toString(), getResolutionDetail(mediaItem)), new Pair(AnalyticsDetailKey.ALBUM.toString(), getAlbumDetail(mediaItem)), new Pair(AnalyticsDetailKey.REMASTER_TYPE.toString(), RemasterType.getRemasterTypeEventDetail(j2, this.mModel.getContainerModel().getLocationKey()))};
    }

    private String getResolutionDetail(MediaItem mediaItem) {
        int heightInDB = mediaItem.getHeightInDB() * mediaItem.getWidthInDB();
        if (heightInDB >= 108000000) {
            return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_108MP.toString();
        }
        if (heightInDB >= 64000000) {
            return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_64MP.toString();
        }
        if (heightInDB >= 32000000) {
            return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_32MP.toString();
        }
        if (heightInDB >= 16000000) {
            return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_16MP.toString();
        }
        if (heightInDB >= 3000000) {
            return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_3MP.toString();
        }
        if (heightInDB >= 1000000) {
            return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_1MP.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_REMASTER_RESOLUTION_1MP_UNDER.toString();
    }

    /* access modifiers changed from: private */
    public void loggingErrorType(Object... objArr) {
        ErrorReason errorReason = objArr[0];
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        MediaItem originMediaItem = this.mModel.getOriginMediaItem();
        if (eventContext != null && originMediaItem != null) {
            AnalyticsLogger.getInstance().postCustomDimension(getAnalyticsScreenId(eventContext, originMediaItem, (int) MediaItemSuggest.getRevitalizedType(this.mModel.getMediaItem())), AnalyticsEventId.EVENT_DETAIL_VIEW_SELECT_REMASTER_PICTURES.toString(), eventContext.getScreenLabel(), (Pair<String, String>[]) getCustomDimensions(originMediaItem, errorReason));
        }
    }

    /* access modifiers changed from: private */
    public void loggingRemasterType(Object... objArr) {
        long longValue = objArr[0].longValue();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        MediaItem originMediaItem = this.mModel.getOriginMediaItem();
        if (eventContext != null && originMediaItem != null) {
            String analyticsScreenId = getAnalyticsScreenId(eventContext, originMediaItem, (int) longValue);
            String screenLabel = eventContext.getScreenLabel();
            if (originMediaItem.isGif()) {
                AnalyticsLogger.getInstance().postLog(analyticsScreenId, AnalyticsEventId.EVENT_DETAIL_VIEW_SELECT_REMASTER_GIF.toString());
            } else if (0 == MediaItemSuggest.getRevitalizedType(originMediaItem)) {
                AnalyticsLogger.getInstance().postCustomDimension(analyticsScreenId, AnalyticsEventId.EVENT_DETAIL_VIEW_SELECT_REMASTER_PICTURES.toString(), screenLabel, (Pair<String, String>[]) getCustomDimensions(originMediaItem, longValue));
            }
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTER_ANALYTIC_LOGGING, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTER_ANALYTIC_LOGGING_FOR_ERROR, new a(this, 1));
    }

    private Pair<String, String>[] getCustomDimensions(MediaItem mediaItem, ErrorReason errorReason) {
        String analyticsDetail = errorReason == ErrorReason.ERROR_NO_REMASTERING_NEED ? AnalyticsDetail.EVENT_DETAIL_No_REMASTERING_NEEDED.toString() : null;
        if (!TextUtils.isEmpty(analyticsDetail)) {
            return new Pair[]{new Pair(AnalyticsDetailKey.RESOLUTION.toString(), getResolutionDetail(mediaItem)), new Pair(AnalyticsDetailKey.ALBUM.toString(), getAlbumDetail(mediaItem)), new Pair(AnalyticsDetailKey.REMASTER_TYPE.toString(), analyticsDetail)};
        }
        return null;
    }
}
