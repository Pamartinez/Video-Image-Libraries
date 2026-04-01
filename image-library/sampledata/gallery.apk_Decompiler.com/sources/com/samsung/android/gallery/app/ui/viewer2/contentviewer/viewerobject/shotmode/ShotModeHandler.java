package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode;

import A8.C0545b;
import A9.b;
import Bb.g;
import G7.a;
import G7.c;
import G7.d;
import G7.e;
import G7.f;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.SavePortraitEffectCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.AbsShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.AudioEmojiHandler;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.GenericVideoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.CloudDownloader;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.PlayButtonTextView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShotModeHandler extends ViewerObject implements FragmentLifeCycle {
    private ShotModeAudio mAudioHandler;
    private View mDecorLayout;
    private BiPredicate<MediaItem, String> mPredicateShotModeButton;
    private View mScrollView;
    private View.OnLayoutChangeListener mScrollViewListener;
    private View mShotModeButton;
    private View mShotModeButtonExtra;
    private AbsShotModeHandler mShotModeHandler;

    private void clearExtraButton() {
        View.OnLayoutChangeListener onLayoutChangeListener;
        View view = this.mScrollView;
        if (!(view == null || (onLayoutChangeListener = this.mScrollViewListener) == null)) {
            view.removeOnLayoutChangeListener(onLayoutChangeListener);
            this.mScrollViewListener = null;
            ViewUtils.setVisibility(this.mScrollView, 8);
            this.mScrollView = null;
        }
        View view2 = this.mShotModeButtonExtra;
        if (view2 != null) {
            ((PlayButtonTextView) view2).reset();
            this.mShotModeButtonExtra = null;
        }
    }

    /* access modifiers changed from: private */
    public void downloadCompleted(Object obj) {
        if (this.mModel.isViewConfirmed()) {
            MediaItem mediaItem = (MediaItem) obj;
            if (mediaItem != null && mediaItem.isVideo() && supportVideoController(mediaItem)) {
                this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VISIBILITY_CHANGE, 0);
            }
            executeShotMode(mediaItem);
            return;
        }
        Log.v(this.TAG, "downloadCompleted skip");
    }

    private void executeShotMode(MediaItem mediaItem) {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (eventContext == null || absShotModeHandler == null || mediaItem == null) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "executeShotMode : fail, mShotModeHandler = " + absShotModeHandler + ", mediaItem = " + mediaItem);
        } else if (!absShotModeHandler.isEnableToRunCloudOnly() && CloudDownloader.acceptable(mediaItem)) {
            new CloudDownloader(this.mModel, this.mThread).exec(new d(this, 1));
        } else if (LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            ThreadUtil.postOnUiThreadDelayed(new a(this, 2), 500);
            this.mActionInvoker.invoke(ViewerAction.BACK_KEY_PRESSED, new Object[0]);
        } else if (absShotModeHandler.mScaleToMin) {
            this.mActionInvoker.invoke(ViewerAction.ZOOM_TO_MIN_SCALE, new Object[0]);
            ThreadUtil.postOnUiThreadDelayed(new b(absShotModeHandler, eventContext, mediaItem, 20), 200);
        } else if (absShotModeHandler.execute(eventContext, mediaItem) && (absShotModeHandler instanceof GenericVideoHandler)) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_VIDEO_APP_TRANSITION, new Object[0]);
            setDecorVisibilityWhenEnterVideoPlayer(mediaItem, eventContext);
        }
    }

    private AbsShotModeHandler getShotModeHandler(MediaItem mediaItem) {
        AbsShotModeHandler absShotModeHandler = AbsShotModeHandler.get(mediaItem);
        if (absShotModeHandler != null) {
            return absShotModeHandler;
        }
        if (mediaItem.isJpeg()) {
            return AbsShotModeHandler.getFromJpegFile(mediaItem);
        }
        if (mediaItem.isGif()) {
            return AbsShotModeHandler.getFromImageFile(mediaItem);
        }
        return absShotModeHandler;
    }

    private void inflateExtraButtonView() {
        View findViewById = this.mDecorLayout.findViewById(R.id.shot_mode_two_button);
        this.mScrollView = findViewById;
        if (findViewById instanceof ViewStub) {
            ((ViewStub) findViewById).setLayoutResource(R.layout.special_contents_play_two_button_view);
            this.mScrollView = ((ViewStub) this.mScrollView).inflate();
        }
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (absShotModeHandler == null || !absShotModeHandler.mDualButton) {
            ((PlayButtonTextView) this.mScrollView.findViewById(R.id.button1)).reset();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mScrollView.getLayoutParams();
            marginLayoutParams.width = -2;
            this.mScrollView.setLayoutParams(marginLayoutParams);
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mScrollView.getLayoutParams();
            marginLayoutParams2.width = -1;
            this.mScrollView.setLayoutParams(marginLayoutParams2);
            this.mScrollView.setVisibility(4);
            g gVar = new g(2, this);
            this.mScrollViewListener = gVar;
            this.mScrollView.addOnLayoutChangeListener(gVar);
            View findViewById2 = this.mScrollView.findViewById(R.id.button1);
            this.mShotModeButtonExtra = findViewById2;
            findViewById2.setOnClickListener(new f(this, 0));
        }
        View findViewById3 = this.mScrollView.findViewById(R.id.button2);
        this.mShotModeButton = findViewById3;
        findViewById3.setOnClickListener(new f(this, 1));
        updateTouchArea();
    }

    private void inflateView() {
        View findViewById = this.mDecorLayout.findViewById(R.id.shot_mode_button);
        this.mShotModeButton = findViewById;
        if (findViewById instanceof ViewStub) {
            this.mShotModeButton = ((ViewStub) findViewById).inflate();
        }
        this.mActionInvoker.invoke(ViewerAction.SHOT_MODE_BUTTON, this.mShotModeButton);
        this.mShotModeButton.setOnClickListener(new f(this, 2));
        updateTouchArea();
    }

    private boolean isScreenShotAlbum(String str) {
        String argValue;
        if (ArgumentsUtil.getArgValue(str, "mergedAlbumId", -1) <= -1 || (argValue = ArgumentsUtil.getArgValue(str, "ids", (String) null)) == null) {
            return BucketUtils.isScreenshot(ArgumentsUtil.getArgValue(str, "id", 0));
        }
        return Arrays.stream(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).mapToInt(new C0545b(3)).anyMatch(new D3.b(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mDecorLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        lambda$invalidate$16(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        lambda$invalidate$16(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        lambda$invalidate$16(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$5(MediaItem mediaItem) {
        this.mThread.runOnUiThread(new c(this, mediaItem, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$6(Object[] objArr) {
        this.mPredicateShotModeButton = objArr[0];
        Optional.ofNullable(this.mModel.getMediaItem()).ifPresent(new d(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$7(Object[] objArr) {
        lambda$invalidate$16(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeShotMode$18() {
        getBlackboard().postEvent(EventMessage.obtain(3054, Integer.valueOf(hashCode())));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$8() {
        lambda$invalidate$16(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$9() {
        clearExtraButton();
        this.mThread.runOnBgThread(new a(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateExtraButtonView$11(boolean z, View view, View view2) {
        int i2;
        String str;
        if (z) {
            i2 = view.getWidth();
        } else {
            i2 = -1;
        }
        if (!(i2 == 0 || i2 == view2.getWidth())) {
            ViewUtils.setWidth(view2, i2);
            StringCompat stringCompat = this.TAG;
            if (z) {
                str = "wrap";
            } else {
                str = "match";
            }
            Log.d(stringCompat, "TwoButtonView : ".concat(str), Integer.valueOf(view2.getWidth()), Integer.valueOf(i2));
        }
        view2.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateExtraButtonView$12(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        boolean z;
        if (view.getVisibility() == 4 && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            View childAt = viewGroup.getChildAt(0);
            if (viewGroup.getChildCount() != 1 || i8 - childAt.getRight() <= 10) {
                z = false;
            } else {
                z = true;
            }
            View view2 = view;
            view2.post(new F8.a((Object) this, z, (Object) childAt, (Object) view2, 2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateExtraButtonView$13(View view) {
        onExtraButtonClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateExtraButtonView$14(View view) {
        onButtonClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateView$10(View view) {
        onButtonClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewAttached$15() {
        lambda$invalidate$16(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDecorVisibilityWhenEnterVideoPlayer$20() {
        this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDecorVisibilityWhenEnterVideoPlayer$21(Object obj, Bundle bundle) {
        ThreadUtil.runOnUiThread(new a(this, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateShotModeHandler$17(MediaItem mediaItem) {
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (absShotModeHandler == null) {
            updateButtonVisibility(false);
            return;
        }
        Log.d(this.TAG, "shotMode", absShotModeHandler.getType());
        if (absShotModeHandler.mDualButton) {
            inflateExtraButtonView();
            lambda$addActionInvokeListener$4(mediaItem);
            setShotModeButtonForExtra();
        } else {
            ViewUtils.setVisibility(this.mScrollView, 8);
            inflateView();
            lambda$addActionInvokeListener$4(mediaItem);
        }
        if (absShotModeHandler instanceof AudioEmojiHandler) {
            this.mActionInvoker.invoke(ViewerAction.AUDIO_ENABLED, Boolean.TRUE);
        }
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        int intValue = objArr[0].intValue();
        if (objArr[1].booleanValue() && intValue == 3) {
            stopAudio();
        }
    }

    private void onButtonClicked() {
        if (this.mShotModeHandler instanceof AudioEmojiHandler) {
            onPlayAudioClicked();
        } else {
            executeShotMode(this.mModel.getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public void onExecuteCurrentShotMode(Object... objArr) {
        executeShotMode(this.mModel.getMediaItem());
    }

    private void onExtraButtonClicked() {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (absShotModeHandler != null) {
            absShotModeHandler.executeExtra(eventContext, this.mModel.getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public void onMotionPlayModeChanged(Object... objArr) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE) {
            boolean z = false;
            if (MotionPlayViewer.isViewModeChanged(objArr[0], objArr[1])) {
                if (objArr[1] == MotionPlayViewer.PHOTO) {
                    z = true;
                }
                updateButtonVisibility(z);
            }
        }
    }

    private void onPlayAudioClicked() {
        if (this.mAudioHandler == null) {
            this.mAudioHandler = new ShotModeAudio(this.TAG, this.mModel, this.mShotModeButton);
        }
        this.mAudioHandler.onPlayAudioClicked(this.mShotModeHandler);
    }

    /* access modifiers changed from: private */
    public void restartAudio(Object... objArr) {
        ShotModeAudio shotModeAudio = this.mAudioHandler;
        if (shotModeAudio != null) {
            shotModeAudio.restartAudio();
        }
    }

    private void setDecorVisibilityWhenEnterVideoPlayer(MediaItem mediaItem, EventContext eventContext) {
        if (!this.mModel.isInMultiWindowMode() && !SeApiCompat.isDexMode(this.mModel.getContext()) && !mediaItem.isBroken()) {
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
            eventContext.subscribeInstant("lifecycle://on_activity_resume", new e(0, this));
        }
    }

    private void setShotModeButton(MediaItem mediaItem) {
        int i2;
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (absShotModeHandler != null) {
            i2 = absShotModeHandler.getTitleId(mediaItem);
        } else {
            i2 = 0;
        }
        if (i2 != 0 && this.mShotModeButton != null) {
            Log.d(this.TAG, "set ShotMode Button mShotMode = [" + absShotModeHandler + "]");
            ((PlayButtonTextView) this.mShotModeButton).setText(i2);
            ((PlayButtonTextView) this.mShotModeButton).setButtonContentDescription(i2);
            ((PlayButtonTextView) this.mShotModeButton).resizeFontLarge((float) this.mModel.getContext().getResources().getDimensionPixelSize(R.dimen.play_button_view_font_size));
            if (mediaItem.isVideo()) {
                ((PlayButtonTextView) this.mShotModeButton).setIconVisibility(0);
            } else {
                int playButtonIconId = absShotModeHandler.getPlayButtonIconId();
                if (playButtonIconId != 0) {
                    View view = this.mShotModeButton;
                    ((PlayButtonTextView) view).setIcon(view.getContext().getDrawable(playButtonIconId));
                } else {
                    ((PlayButtonTextView) this.mShotModeButton).setIconVisibility(8);
                }
            }
            updateButtonVisibility(absShotModeHandler.isVisible(mediaItem));
            ViewUtils.setViewEnabled(this.mShotModeButton, !TrashData.isTrash(mediaItem));
        }
    }

    private void setShotModeButtonForExtra() {
        View view;
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (absShotModeHandler != null) {
            if (absShotModeHandler.mDualButton) {
                view = this.mShotModeButtonExtra;
            } else {
                view = this.mShotModeButton;
            }
            String extraTitleString = absShotModeHandler.getExtraTitleString();
            Uri extraButtonUri = absShotModeHandler.getExtraButtonUri();
            if (view == null || extraTitleString == null) {
                ViewUtils.setVisibility(view, 8);
                return;
            }
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "set mShotModeButtonLeft mShotMode = [" + absShotModeHandler + "]");
            PlayButtonTextView playButtonTextView = (PlayButtonTextView) view;
            playButtonTextView.setText((CharSequence) extraTitleString);
            playButtonTextView.setContentDescription((CharSequence) extraTitleString);
            playButtonTextView.resizeFontLarge((float) this.mModel.getContext().getResources().getDimensionPixelSize(R.dimen.play_button_view_font_size));
            playButtonTextView.setIcon(extraButtonUri);
            ViewUtils.setVisibility(view, 0);
            ViewUtils.setViewEnabled(view, true);
            View view2 = this.mScrollView;
            if (view2 != null && view2.getVisibility() == 8) {
                ViewUtils.setVisibility(this.mScrollView, 0);
            }
        }
    }

    private void setVideoModeButton(MediaItem mediaItem) {
        int i2;
        boolean z;
        BiPredicate<MediaItem, String> biPredicate;
        AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
        if (absShotModeHandler != null) {
            i2 = absShotModeHandler.getTitleId(mediaItem);
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            if (!supportPreviewPlay(mediaItem) || ((biPredicate = this.mPredicateShotModeButton) != null && !biPredicate.test(mediaItem, this.mModel.getContainerModel().getLocationKey()))) {
                z = false;
            } else {
                z = true;
            }
            if (!z || ((!LocationKey.isAiEditGroupPanelViewer(this.mModel.getContainerModel().getLocationKey()) && DynamicViewData.of(mediaItem).dynamicViewPlayInfo != null) || !supportVideoController(mediaItem))) {
                this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VISIBILITY_CHANGE, 8);
                setShotModeButton(mediaItem);
                return;
            }
            this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VISIBILITY_CHANGE, 0);
            updateButtonVisibility(false);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showShotModeView */
    public void lambda$addActionInvokeListener$4(MediaItem mediaItem) {
        if (mediaItem.isImage()) {
            AbsShotModeHandler absShotModeHandler = this.mShotModeHandler;
            if (!(absShotModeHandler == null || absShotModeHandler.getExtraTitleString() == null)) {
                setShotModeButtonForExtra();
            }
            setShotModeButton(mediaItem);
        } else if (mediaItem.isVideo()) {
            setVideoModeButton(mediaItem);
        }
    }

    private void stopAudio() {
        ShotModeAudio shotModeAudio = this.mAudioHandler;
        if (shotModeAudio != null) {
            shotModeAudio.stopAudio();
        }
    }

    private boolean supportPreviewPlay(MediaItem mediaItem) {
        if (!MediaItemUtil.supportPreviewPlay(mediaItem) || mediaItem.isSingleTakenShot()) {
            return false;
        }
        return true;
    }

    private boolean supportVideoController(MediaItem mediaItem) {
        if (mediaItem.isBroken()) {
            return false;
        }
        if (PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer() || this.mModel.getStateHelper().isForcePlayVideoInGallery()) {
            return true;
        }
        return false;
    }

    private void updateButtonVisibility(boolean z) {
        int i2;
        View view = this.mShotModeButton;
        int i7 = 8;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
        View view2 = this.mScrollView;
        if (view2 != null && view2.getVisibility() != 4) {
            View view3 = this.mScrollView;
            if (z) {
                i7 = 0;
            }
            ViewUtils.setVisibility(view3, i7);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateShotModeHandler */
    public void lambda$invalidate$16(MediaItem mediaItem) {
        if (mediaItem == null || (!mediaItem.isVideo() && mediaItem.isBroken())) {
            Log.d(this.TAG, "update shotModeHandler failed : item invalid");
            return;
        }
        String locationKey = this.mModel.getContainerModel().getLocationKey();
        if (!Features.isEnabled(Features.SUPPORT_SCREEN_SHOT_ALBUM_AI_BUTTON) || (!LocationKey.isSearchCategoryScreenShot(locationKey) && (!LocationKey.isAlbumPictures(locationKey) || !isScreenShotAlbum(locationKey)))) {
            this.mShotModeHandler = getShotModeHandler(mediaItem);
        } else {
            AbsShotModeHandler fromScreenShot = AbsShotModeHandler.getFromScreenShot(mediaItem);
            this.mShotModeHandler = fromScreenShot;
            if (fromScreenShot == null) {
                this.mShotModeHandler = getShotModeHandler(mediaItem);
            }
        }
        this.mThread.runOnUiThread(new c(this, mediaItem, 1));
    }

    /* access modifiers changed from: private */
    public void updateTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mShotModeButton, R.dimen.decor_button_touch_area);
        View view = this.mShotModeButtonExtra;
        if (view != null) {
            ViewUtils.setTouchAreaComposite(view, R.dimen.decor_button_touch_area);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new G7.b(this, 3));
        this.mActionInvoker.add(ViewerAction.AUDIO_RESTART, new G7.b(this, 4));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new G7.b(this, 5));
        this.mActionInvoker.add(ViewerAction.EXECUTE_CURRENT_SHOT_MODE, new G7.b(this, 6));
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            this.mActionInvoker.add(ViewerAction.INITIALIZE_SHARED_VIDEO, new G7.b(this, 7));
            this.mActionInvoker.add(ViewerAction.DOWNLOADED_SHARE_VIDEO_VERIFY_UPDATED, new G7.b(this, 8));
        }
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new G7.b(this, 9));
        this.mActionInvoker.add(ViewerAction.XMP_SHOT_MODE_UPDATED, new G7.b(this, 0));
        this.mActionInvoker.add(ViewerAction.CHANGE_PREVIEW_CONTROLLABLE, new G7.b(this, 1));
        this.mActionInvoker.add(ViewerAction.GROUP_CURRENT_ITEM_CHANGED, new G7.b(this, 2));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1037) {
            new SavePortraitEffectCmd().execute(this.mModel.getContainerModel().getEventContext(), this.mModel.getMediaItem(), eventMessage.obj, Boolean.FALSE);
            return true;
        } else if (i2 == 3028) {
            if (!Features.isEnabled(Features.SUPPORT_SCREEN_SHOT_ALBUM_AI_BUTTON) || this.mScrollView == null || this.mScrollViewListener == null) {
                lambda$invalidate$16(this.mModel.getMediaItem());
                return false;
            }
            this.mThread.runOnUiThread(new a(this, 0));
            return false;
        } else if (i2 != 3054 || ((Integer) eventMessage.obj).intValue() == hashCode()) {
            return false;
        } else {
            onButtonClicked();
            return true;
        }
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!(!Features.isEnabled(Features.SUPPORT_SCREEN_SHOT_ALBUM_AI_BUTTON) || this.mScrollView == null || this.mScrollViewListener == null)) {
            clearExtraButton();
        }
        this.mThread.runOnBgThread(new c(this, mediaItem, 0));
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        updateButtonVisibility(false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        View view = this.mScrollView;
        if (!(view == null || this.mShotModeButtonExtra == null)) {
            view.setVisibility(4);
        }
        ViewUtils.postOnGlobalLayout(this.mShotModeButton, new a(this, 3));
    }

    public void onPause() {
        stopAudio();
    }

    public void onViewAttached() {
        super.onViewAttached();
        if (!(!Features.isEnabled(Features.SUPPORT_SCREEN_SHOT_ALBUM_AI_BUTTON) || this.mScrollView == null || this.mScrollViewListener == null)) {
            clearExtraButton();
        }
        this.mThread.runOnBgThread(new a(this, 5), 100);
    }

    public void onViewDetached() {
        super.onViewDetached();
        stopAudio();
        this.mThread.cancel();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mShotModeHandler = null;
        this.mAudioHandler = null;
        if (Features.isEnabled(Features.SUPPORT_SCREEN_SHOT_ALBUM_AI_BUTTON) && this.mScrollView != null && this.mScrollViewListener != null) {
            clearExtraButton();
        }
    }
}
