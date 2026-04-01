package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Ab.a;
import H7.i;
import H7.j;
import H7.k;
import H7.l;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.MediaCaptureCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.MediaCaptureMode;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animations.viewer.InstantSlowMoSaveClipAnimation;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.sum.core.Def;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSlowMoSaveClipHandler extends ViewerObject implements FragmentLifeCycle {
    private InstantSlowMoSaveClipAnimation mAnimation;
    private final Runnable mHideRunnable = new i(this, 0);
    private IMediaPlayerView mMediaPlayerView;
    private ImageView mSaveCircleThumb;
    private View mSaveClipLayout;
    private View mSaveClipLayoutContainer;
    private CoordinatorLayout mViewerLayout;

    private void bindView(View view) {
        this.mSaveCircleThumb = (ImageView) view.findViewById(R.id.circle_thumb);
        view.setOnClickListener(new a(29, this));
    }

    private boolean checkValidExecutedSection() {
        boolean z;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            if (mediaItem.isSharing()) {
                Log.w(this.TAG, "checkValidExecutedSection : not support MDE content!");
                return false;
            }
            long[] jArr = VideoPropData.of(mediaItem).instantSlowMoLastExecutedSection;
            if (jArr != null) {
                long j2 = jArr[1] - jArr[0];
                if (j2 >= 250) {
                    z = true;
                } else {
                    z = false;
                }
                Log.d(this.TAG, "checkValidExecutedSection(" + z + ") : " + j2, jArr[0] + "-" + jArr[1]);
                return z;
            }
            Log.w((CharSequence) this.TAG, "checkValidExecutedSection : section is NOT existed!!", Integer.valueOf(mediaItem.hashCode()));
        }
        return false;
    }

    private InstantSlowMoSaveClipAnimation getAnimation() {
        if (this.mAnimation == null) {
            this.mAnimation = new InstantSlowMoSaveClipAnimation(getSaveClipLayout());
        }
        return this.mAnimation;
    }

    private void getMediaCaptureFrame() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "getMediaCaptureFrame : " + this.mMediaPlayerView.isInPlayState());
        if (this.mMediaPlayerView.isInPlayState()) {
            this.mMediaPlayerView.captureFrame(new E9.a(23, this), ThumbKind.SMALL_CROP_KIND.size());
        } else if (supportMotionPhoto()) {
            SimpleThreadPool.getInstance().execute(new i(this, 1));
        } else {
            Log.w(this.TAG, "getMediaCaptureFrame failed because player is not in playState");
        }
    }

    private View getSaveClipLayout() {
        if (this.mSaveClipLayout == null) {
            View inflateViewStub = ViewUtils.inflateViewStub(this.mViewerLayout.findViewById(R.id.instant_slow_mo_save_clip_stub));
            this.mSaveClipLayoutContainer = inflateViewStub;
            this.mSaveClipLayout = inflateViewStub.findViewById(R.id.instant_slow_mo_save_clip_layout);
        }
        return this.mSaveClipLayout;
    }

    /* access modifiers changed from: private */
    public void hide() {
        hideSaveClipView(true);
    }

    private void hideSaveClipView(boolean z) {
        getAnimation().hide(z);
    }

    private void hideSaveClipViewDirectly() {
        getAnimation().hideDirectly();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        onDetailsSlide(objArr[1].floatValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        if (!objArr[0].booleanValue() && ViewUtils.isVisible(this.mSaveClipLayout)) {
            hideSaveClipViewDirectly();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$5(View view) {
        onSaveClick();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getMediaCaptureFrame$7(Bitmap bitmap) {
        ThreadUtil.postOnUiThread(new j(this, bitmap, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getMediaCaptureFrame$9() {
        if (this.mModel.getBitmap() != null && this.mModel.getMediaItem() != null) {
            ThreadUtil.postOnUiThread(new j(this, ThumbnailUtil.getTinyCropFromBitmap(this.mModel.getBitmap(), this.mModel.getMediaItem(), 64), 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$10(EventMessage eventMessage) {
        onSaveFinish(eventMessage, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$11(EventMessage eventMessage) {
        onSaveFinish(eventMessage, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartSaveClipView$4() {
        if (checkValidExecutedSection()) {
            onLayoutBinding();
            getMediaCaptureFrame();
        }
    }

    private void onDetailsSlide(float f) {
        ViewUtils.setAlpha(this.mSaveClipLayoutContainer, Math.max(0.0f, Math.min(1.0f, 1.0f - (f * 2.0f))));
    }

    private void onLayoutBinding() {
        bindView(getSaveClipLayout());
        updateLayout();
        setTitleEnabled();
    }

    private void onSaveClick() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (mediaItem == null || eventContext == null || this.mMediaPlayerView == null) {
            Log.d(this.TAG, "onSaveClick : save video failed");
            return;
        }
        ThreadUtil.removeCallbackOnUiThread(this.mHideRunnable);
        if (this.mMediaPlayerView.isInPlayState()) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
            new MediaCaptureCmd().execute(eventContext, mediaItem, ConvertingUsageType.NONE, MediaCaptureMode.INSTANT_SLOW_MO_CLIP);
            postSaveLog(AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_INSTANT_SLOW_MO_CLICKED);
        } else if (supportMotionPhoto()) {
            new MediaCaptureCmd().execute(eventContext, mediaItem, ConvertingUsageType.NONE, MediaCaptureMode.INSTANT_SLOW_MO_CLIP);
            postSaveLog(AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_INSTANT_SLOW_MO_CLICKED);
        } else {
            Log.w(this.TAG, "onSaveClick : mMediaPlayerView.isInPlayState = false");
        }
        hideSaveClipViewDirectly();
    }

    private void onSaveFinish(EventMessage eventMessage, boolean z) {
        AnalyticsEventId analyticsEventId;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && !mediaItem.isMotionPhoto()) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_START, new Object[0]);
        }
        if (z) {
            analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_INSTANT_SLOW_MO;
        } else if (eventMessage.what == 1133) {
            analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_INSTANT_SLOW_MO_CANCEL;
        } else {
            analyticsEventId = null;
        }
        postSaveLog(analyticsEventId);
    }

    private void onSaveProcessing() {
        hideSaveClipView(true);
    }

    /* access modifiers changed from: private */
    public void onStartSaveClipView(Object... objArr) {
        if (!support(this.mModel.getMediaItem())) {
            Log.d(this.TAG, "save slow-mo skip. not supported item");
            return;
        }
        boolean booleanValue = objArr[0].booleanValue();
        ThreadUtil.removeCallbackOnUiThread(this.mHideRunnable);
        if (!booleanValue && !this.mModel.getContainerModel().isSelectMode()) {
            ThreadUtil.postOnUiThreadDelayed(new i(this, 2), 200);
        } else if (ViewUtils.isVisible(this.mSaveClipLayout)) {
            hideSaveClipViewDirectly();
        }
    }

    private void postSaveLog(AnalyticsEventId analyticsEventId) {
        if (this.mModel.getContainerModel() != null && this.mModel.getContainerModel().getEventContext() != null && analyticsEventId != null) {
            AnalyticsLogger.getInstance().postLog(this.mModel.getContainerModel().getEventContext().getScreenId(), analyticsEventId.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setSaveClipView */
    public void lambda$getMediaCaptureFrame$8(Bitmap bitmap) {
        long j2;
        if (this.mModel.getMediaItem() != null) {
            this.mSaveCircleThumb.setImageBitmap(bitmap);
            this.mSaveCircleThumb.setClipToOutline(true);
            showSaveClipView();
            Runnable runnable = this.mHideRunnable;
            if (getAnimation().includeTitle()) {
                j2 = Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS;
            } else {
                j2 = Def.SURFACE_CHANNEL_TIMEOUT_MILLIS;
            }
            ThreadUtil.postOnUiThreadDelayed(runnable, j2);
        }
    }

    private void setTitleEnabled() {
        boolean supportSaveTitle = supportSaveTitle();
        getAnimation().setIncludeTitle(supportSaveTitle);
        updateDescription(supportSaveTitle);
    }

    private void showSaveClipView() {
        getAnimation().cancel();
        getAnimation().show();
    }

    private boolean support(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isDrm() || mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    private boolean supportMotionPhoto() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!PreferenceFeatures.OneUi6x.SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO || mediaItem == null || !mediaItem.isMotionPhoto()) {
            return false;
        }
        return true;
    }

    private boolean supportSaveTitle() {
        Boolean bool = (Boolean) this.mModel.getBlackboard().read("instant_slow_mo_save_clip_title_shown");
        if (bool != null && bool.booleanValue()) {
            return false;
        }
        this.mModel.getBlackboard().publish("instant_slow_mo_save_clip_title_shown", Boolean.TRUE);
        return InstantSlowMoUtils.checkInstantSlowMoSaveClipTitleShownPreference();
    }

    private void updateDescription(boolean z) {
        if (z) {
            this.mSaveClipLayout.setContentDescription((CharSequence) null);
            this.mSaveClipLayout.setTooltipText((CharSequence) null);
            return;
        }
        String string = this.mModel.getContext().getResources().getString(R.string.save_instant_slow_mo_clip);
        this.mSaveClipLayout.setContentDescription(string);
        this.mSaveClipLayout.setTooltipText(string);
    }

    private void updateLayout() {
        int i2;
        int start = RectUtils.getStart(this.mModel.getContainerModel().getSystemInsets());
        if (this.mModel.getContainerModel().getStateHelper().supportGroupPanelLandscapeMode()) {
            i2 = 0;
        } else {
            i2 = RectUtils.getEnd(this.mModel.getContainerModel().getSystemInsets());
        }
        ViewMarginUtils.setHorizontalRelativeMargin(this.mSaveClipLayoutContainer, start, i2);
        boolean isTableMode = this.mModel.getContainerModel().isTableMode();
        boolean isLandscape = ResourceCompat.isLandscape(this.mModel.getContext());
        int i7 = this.mModel.getContainerModel().getOverlaySize().top;
        int toolBarHeightWithPadding = SystemUi.getToolBarHeightWithPadding(this.mModel.getContext());
        if (!isTableMode) {
            i7 += toolBarHeightWithPadding;
        }
        View view = this.mSaveClipLayoutContainer;
        if (!isLandscape || this.mModel.getSystemUi().isTabletDpi()) {
            toolBarHeightWithPadding = i7;
        }
        ViewMarginUtils.setTopPadding(view, toolBarHeightWithPadding);
        ViewMarginUtils.setTopMargin(this.mSaveClipLayoutContainer, this.mModel.getContext().getResources().getDimensionPixelOffset(R.dimen.video_instant_slow_mo_save_clip_margin_top));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new k(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new k(this, 1));
        this.mActionInvoker.add(ViewerAction.INSTANT_SLOW_MO_PLAY, new k(this, 2));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new k(this, 3));
        this.mActionInvoker.add(ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY, new k(this, 4));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        String str;
        int i2 = eventMessage.what;
        if (i2 == 1115) {
            Log.d(this.TAG, "EVENT_ON_MEDIA_CAPTURE_SUCCESS");
            ThreadUtil.postOnUiThreadDelayed(new l(this, eventMessage, 0), 100);
            return true;
        } else if (i2 == 1116) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "EVENT_ON_MEDIA_CAPTURE_PREPARE{" + this.mModel.getVideoSeekPosition() + "}");
            onSaveProcessing();
            return true;
        } else if (i2 != 1133 && i2 != 1134) {
            return false;
        } else {
            StringCompat stringCompat2 = this.TAG;
            if (1133 == i2) {
                str = "_SERVICE_INTERRUPTED";
            } else {
                str = "_FAILED";
            }
            Log.d(stringCompat2, "EVENT_ON_MEDIA_CAPTURE".concat(str));
            ThreadUtil.postOnUiThreadDelayed(new l(this, eventMessage, 1), 100);
            return true;
        }
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayout();
    }

    public void onPause() {
        if (ViewUtils.isVisible(this.mSaveClipLayout)) {
            hideSaveClipViewDirectly();
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateLayout();
    }

    public void onViewDetached() {
        super.onViewDetached();
        if (ViewUtils.isVisible(this.mSaveClipLayout)) {
            hideSaveClipView(true);
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ImageView imageView = this.mSaveCircleThumb;
        if (imageView != null) {
            imageView.setImageBitmap((Bitmap) null);
        }
        View view = this.mSaveClipLayout;
        if (view != null) {
            view.setOnClickListener((View.OnClickListener) null);
            this.mSaveClipLayout = null;
        }
        View view2 = this.mSaveClipLayoutContainer;
        if (view2 != null) {
            view2.setAlpha(1.0f);
            this.mSaveClipLayoutContainer = null;
        }
        InstantSlowMoSaveClipAnimation instantSlowMoSaveClipAnimation = this.mAnimation;
        if (instantSlowMoSaveClipAnimation != null) {
            instantSlowMoSaveClipAnimation.recycle();
        }
    }
}
