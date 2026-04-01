package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import G6.a;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.viewpager2.widget.ViewPager2;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import i.C0212a;
import v7.d;
import v7.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentDescriptionHandler extends ViewerObject {
    private static final int[] DURATION_FORMAT = {R.string.details_ms, R.string.details_hms};
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;

    /* access modifiers changed from: private */
    public String getCustomUsageHint() {
        int i2;
        Context context = this.mModel.getContext();
        if (context == null || !BottomSheetState.Details.isClosed(this.mModel.getContainerModel())) {
            return null;
        }
        if (this.mModel.getContainerModel().isOsdVisible()) {
            i2 = R.string.hide_options;
        } else {
            i2 = R.string.show_options;
        }
        return context.getString(i2);
    }

    /* access modifiers changed from: private */
    public String getDefaultContentDescription() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            return AppResources.getString(R.string.speak_nothing_selected);
        }
        String str = TimeUtil.getLocalizedDateTime(mediaItem.getDateTaken()) + ArcCommonLog.TAG_COMMA + mediaItem.getMimeType();
        if (mediaItem.isVideo()) {
            StringBuilder t = C0212a.t(str, ArcCommonLog.TAG_COMMA);
            t.append(TimeUtil.formatDuration(this.mModel.getContext(), mediaItem.getFileDuration(), DURATION_FORMAT));
            str = t.toString();
        }
        Cursor query = DbCompat.query("mp://RelatedScene", new a(mediaItem, 17));
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    String string = query.getString(0);
                    Log.d(this.TAG, "RELATED_SCENE : " + string);
                    if (!TextUtils.isEmpty(string)) {
                        String translatedString = TranslationManager.getInstance().getTranslatedString("Things Scenery", string);
                        if (translatedString == null || translatedString.equals(string)) {
                            Log.d(this.TAG, "fail translate : " + string);
                        } else {
                            str = str + ArcCommonLog.TAG_COMMA + translatedString;
                        }
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return str;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public void onOverlayStateChanged(Object... objArr) {
        int i2;
        if (OverlayViewState.isShow(objArr[0])) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            photoView.setImportantForAccessibility(i2);
        }
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.getView().setImportantForAccessibility(i2);
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new e(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new e(this, 1));
        this.mActionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new e(this, 2));
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mPhotoView.addContentDescription(new d(this, 0));
        this.mPhotoView.addUsageHint(new d(this, 1));
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.addContentDescription(new d(this, 0));
            this.mMediaView.addUsageHint(new d(this, 1));
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        ViewPager2 viewPager = this.mModel.getContainerModel().getViewPager();
        if (viewPager != null) {
            viewPager.sendAccessibilityEvent(8);
        }
    }
}
