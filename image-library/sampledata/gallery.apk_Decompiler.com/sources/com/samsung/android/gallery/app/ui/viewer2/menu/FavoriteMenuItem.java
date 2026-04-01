package com.samsung.android.gallery.app.ui.viewer2.menu;

import B5.c;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.tag.TagEditor;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FavoriteMenuItem extends ViewerMenuItem {
    public FavoriteMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.add_to_favorites);
    }

    private void forceSwipeForFavouriteAlbum(Blackboard blackboard) {
        if (isFavoriteAlbum()) {
            forceSwipe(blackboard);
        }
    }

    private boolean isFavoriteAlbum() {
        return BucketUtils.isFavourite(ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "id", -1));
    }

    private boolean isInvalidItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            return true;
        }
        if (mediaItem.isImage() || mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isFavourite()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    private void postFavoriteSaLog(MediaItem mediaItem, boolean z) {
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FAVORITE.toString(), VuAnalytics.getViewerCustomDimensions(mediaItem, AnalyticsDetail.getOnOff(z)));
    }

    private void publishNotifyChanged(Blackboard blackboard, String str) {
        if (!LocationKey.isTimelinePictures(str)) {
            blackboard.post("command://event/TimelineDataDirty", (Object) null);
        }
        if (!AlbumType.isAutoAlbum(ArgumentsUtil.getArgValue(str, "type", 0))) {
            BlackboardUtils.forceRefreshPicturesData(blackboard, false);
        }
        BlackboardUtils.publishDataChangedToOtherActivities(blackboard, true);
        blackboard.publish("data://user/favoriteUpdatged", Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    /* renamed from: setFavorite */
    public void lambda$onMenuSelectInternal$2(Activity activity, Blackboard blackboard, MediaItem mediaItem, String str) {
        boolean isFavourite = mediaItem.isFavourite();
        boolean z = !isFavourite;
        if (setFavorite(activity, mediaItem, z) > 0) {
            SeApiCompat.announceVoiceAssistant(activity, activity.getString(!isFavourite ? R.string.item_added_to_favorites : R.string.item_removed_from_favorites));
            forceSwipeForFavouriteAlbum(blackboard);
            publishNotifyChanged(blackboard, str);
            mediaItem.setFavourite(z);
            postFavoriteSaLog(mediaItem, z);
            this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
            return;
        }
        Log.e((CharSequence) this.TAG, "updateFavorite failed { locationKey : ", str + ArcCommonLog.TAG_COMMA + mediaItem + "}");
    }

    public boolean onMenuSelectInternal(View view) {
        Activity activity = this.mEventContext.getActivity();
        Blackboard blackboard = this.mEventContext.getBlackboard();
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        String locationKey = this.mEventContext.getLocationKey();
        if (activity == null || blackboard == null || isInvalidItem(currentItem)) {
            Log.d(this.TAG, "Favorite Menu Select failed");
            return false;
        }
        SeApiCompat.performHapticFeedback(activity, 1);
        if (Utils.isAnimationDuration0x(this.mEventContext.getContext()) || !supportAnimatedDrawable()) {
            lambda$onMenuSelectInternal$2(activity, blackboard, currentItem, locationKey);
            return true;
        }
        this.mActionInvoker.invoke(ViewerAction.START_FAVORITE_ICON_ANIMATION, new c(this, activity, blackboard, currentItem, locationKey, 6));
        return true;
    }

    public ViewerMenuItem setFavoriteCommonAttribute() {
        return setFastOptionMenu().setExecutableOnScreenLocked().setShowAsActionFlag(2).exclude("location://trash", "location://mtp/fileList", "location://dynamicViewList", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_PRIVATE_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST);
    }

    public void setMenuAttribute() {
        int i2;
        ViewerMenuItem favoriteCommonAttribute = setFavoriteCommonAttribute();
        if (isFlipCoverTheme()) {
            i2 = R.drawable.gallery_ic_flip_cover_like_off;
        } else {
            i2 = R.drawable.gallery_detailview_like_heart_layer;
        }
        favoriteCommonAttribute.setIconResId(i2).validate(new c(2)).setSupportPpp(true);
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            validate(new c(3));
        }
    }

    public boolean supportAnimatedDrawable() {
        return !isFlipCoverTheme();
    }

    public FavoriteMenuItem(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        super(eventContext, actionInvoker, i2);
    }

    private int setFavorite(Context context, MediaItem mediaItem, boolean z) {
        if (mediaItem.isCloudOnly()) {
            return SamsungCloudCompat.setFavorite(context, mediaItem.getCloudServerId(), mediaItem.getCloudServerPath(), z) ? 1 : 0;
        }
        if (mediaItem.isPostProcessing()) {
            return new TagEditor().setFavoriteToSecMp(mediaItem, z);
        }
        return new TagEditor().setFavorite(mediaItem, z);
    }
}
