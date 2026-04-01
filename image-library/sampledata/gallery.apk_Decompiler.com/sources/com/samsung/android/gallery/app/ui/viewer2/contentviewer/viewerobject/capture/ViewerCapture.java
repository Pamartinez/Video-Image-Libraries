package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.viewer.QuickCropHelper;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.photoview.QuickCropPreView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import o4.a;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ViewerCapture {
    private final Runnable hideCallback = new a(this, 0);
    protected String mActivityName;
    private ImageView mCaptureButton;
    private SaveCaptureCmd mCaptureCmd;
    CaptureEndListener mCaptureEndListener;
    private Animation mHideAnimation;
    protected boolean mIsCaptureAndGo;
    protected boolean mIsShare;
    protected final BooleanSupplier mIsSupport;
    protected final ContentModel mModel;
    protected String mPackageName;
    private QuickCropPreView mPreview;
    private FrameLayout mPreviewContainer;
    private View mPreviewLayout;
    private View mProgressBar;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CaptureEndListener {
    }

    public ViewerCapture(ContentModel contentModel, BooleanSupplier booleanSupplier) {
        this.mModel = contentModel;
        this.mIsSupport = booleanSupplier;
    }

    private void completeCapture(MediaItem mediaItem, String str) {
        SimpleAnimator.setVisibilityAni(this.mProgressBar, 8);
        QuickCropPreView quickCropPreView = this.mPreview;
        if (quickCropPreView == null) {
            return;
        }
        if (str == null) {
            quickCropPreView.setOnClickListener(new d(this, mediaItem));
            if (this.mPreviewContainer.isShown()) {
                ThreadUtil.removeCallbackOnUiThread(this.hideCallback);
                ThreadUtil.postOnUiThreadDelayed(this.hideCallback, 1500);
                return;
            }
            this.hideCallback.run();
            return;
        }
        ThreadUtil.postponeOnUiThread(new c(this, mediaItem, str, 1));
    }

    private void inflatePreview() {
        View view = this.mPreviewLayout;
        if (view instanceof ViewStub) {
            this.mPreviewLayout = ((ViewStub) view).inflate();
        }
        if (this.mPreview == null || this.mProgressBar == null) {
            this.mPreview = (QuickCropPreView) this.mPreviewLayout.findViewById(R.id.quick_crop_preview);
            this.mProgressBar = this.mPreviewLayout.findViewById(R.id.quick_crop_processing);
            this.mPreviewContainer = (FrameLayout) this.mPreviewLayout.findViewById(R.id.quick_crop_preview_container);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onCaptureClick();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeCapture$7(MediaItem mediaItem, View view) {
        onQuickCropPreviewClicked(mediaItem, (String) null);
        ThreadUtil.removeCallbackOnUiThread(this.hideCallback);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeCapture$8(MediaItem mediaItem, String str) {
        onQuickCropPreviewClicked(mediaItem, str);
        ThreadUtil.postOnUiThreadDelayed(this.hideCallback, 1500);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hidePreview$5() {
        ViewUtils.setVisibility(this.mPreviewContainer, 8);
        notifyCaptureEnded();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hidePreview$6() {
        ViewUtils.setVisibility(this.mProgressBar, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCaptureProcessing$3(Bitmap bitmap, MediaItem mediaItem) {
        showPreview(bitmap, mediaItem.getOrientation());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onQuickCropPreviewClicked$10() {
        hidePreview(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onQuickCropPreviewClicked$9(MediaItem mediaItem, MediaItem mediaItem2, boolean z, String str, String str2) {
        launchQuickCropItem(mediaItem, mediaItem2.getMediaId(), z, str, str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setButtonVisibility$1(boolean z, int i2) {
        if (z) {
            SimpleAnimator.setVisibilityAni(this.mCaptureButton, i2);
        } else {
            ViewUtils.setVisibility(this.mCaptureButton, i2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPreview$4() {
        if (this.mPreviewContainer != null) {
            ViewUtils.setVisibility(this.mPreview, 0);
            ViewUtils.setVisibility(this.mProgressBar, 0);
            ViewUtils.setVisibility(this.mPreviewContainer, 0);
            if (this.mPreviewContainer.isShown()) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this.mPreviewContainer.getContext(), R.anim.oneui50_viewer2_capture_preview_show);
                loadAnimation.setInterpolator(new PathInterpolator(0.17f, 0.89f, 0.32f, 1.05f));
                this.mPreviewContainer.startAnimation(loadAnimation);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$supportCapture$2() {
        ThreadUtil.runOnUiThread(new a(this, 1));
    }

    private void launchQuickCropItem(MediaItem mediaItem, long j2, boolean z, String str, String str2) {
        prepareTransition(mediaItem);
        new VuLauncher(this.mModel.getBlackboard()).setUriUpdater(new e(j2, str, z, str2)).launchSingle(mediaItem);
    }

    /* access modifiers changed from: private */
    public void notifyCaptureEnded() {
        CaptureEndListener captureEndListener = this.mCaptureEndListener;
        if (captureEndListener != null) {
            ((MotionPhotoCaptureHandler) ((p) captureEndListener).e).updateCaptureInternal();
            this.mCaptureEndListener = null;
        }
    }

    private void onCaptureComplete(Object[] objArr) {
        long j2;
        MediaItem mediaItem;
        MediaItem mediaItem2 = this.mModel.getMediaItem();
        if (objArr != null) {
            j2 = objArr[0].longValue();
        } else {
            j2 = -1;
        }
        String str = null;
        if (objArr != null) {
            mediaItem = objArr[1];
        } else {
            mediaItem = null;
        }
        if (objArr != null) {
            str = objArr[2];
        }
        if (mediaItem == null || mediaItem2 == null || mediaItem2.getMediaId() != j2) {
            ThreadUtil.runOnUiThread(new a(this, 0));
        } else {
            completeCapture(mediaItem, str);
        }
    }

    private void onCaptureProcessing(long j2) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || mediaItem.getMediaId() != j2) {
            Log.d("ViewerCapture", "on capture failed:: mediaItem null");
            return;
        }
        Blackboard blackboard = this.mModel.getBlackboard();
        inflatePreview();
        ThreadUtil.runOnUiThread(new c(this, (Bitmap) blackboard.pop("viewer_quick_crop_pre_bitmap/" + mediaItem.getMediaId()), mediaItem));
    }

    private void prepareTransition(MediaItem mediaItem) {
        Log.d("ViewerCapture", "prepareTransition " + MediaItemUtil.getSimpleLog(mediaItem));
        Blackboard blackboard = this.mModel.getBlackboard();
        if (this.mPreview != null) {
            Optional ofNullable = Optional.ofNullable(ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.MEDIUM_KIND));
            QuickCropPreView quickCropPreView = this.mPreview;
            Objects.requireNonNull(quickCropPreView);
            ofNullable.ifPresent(new a(26, quickCropPreView));
            SharedTransition.addSharedElement(blackboard, this.mPreview, SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, this.mPreview.getBitmap()));
            this.mPreview.setMediaItem(mediaItem);
            this.mPreview.setOrientation(mediaItem.getOrientation());
        }
    }

    private void showPreview(Bitmap bitmap, int i2) {
        boolean z;
        if (this.mPreview != null && bitmap != null) {
            StringBuilder o2 = C0086a.o(i2, "QuickCrop#show {", "}");
            o2.append(Logger.toSimpleString(bitmap));
            Log.d("ViewerCapture", o2.toString());
            this.mPreview.setOrientation(i2);
            this.mPreview.setImageBitmap(bitmap);
            this.mPreview.updateLayoutSize();
            this.mPreview.setOnClickListener((View.OnClickListener) null);
            Animation animation = this.mHideAnimation;
            if (animation == null || animation.hasEnded()) {
                z = false;
            } else {
                z = true;
            }
            if (!ViewUtils.isVisible(this.mPreviewContainer) || z) {
                if (z) {
                    this.mHideAnimation.cancel();
                }
                ViewUtils.post(this.mPreviewContainer, new a(this, 2));
            }
        }
    }

    private boolean unsupportableLocation() {
        String locationKey = this.mModel.getContainerModel().getLocationKey();
        if (LocationKey.isTempFile(locationKey) || LocationKey.isCleanOutPictures(locationKey) || LocationKey.isCleanOutDuplicatedPictures(locationKey) || LocationKey.isCleanOutMotionPhoto(locationKey) || LocationKey.isAllDayTimeLapse(locationKey) || LocationKey.isLongExposure(locationKey)) {
            return true;
        }
        return false;
    }

    public void bindView(View view) {
        this.mPreviewLayout = view.findViewById(R.id.quick_crop_preview_stub);
        ImageView imageView = (ImageView) view.findViewById(R.id.quick_crop_button);
        this.mCaptureButton = imageView;
        if (imageView != null) {
            String string = AppResources.getString(getTitleRes());
            this.mCaptureButton.setContentDescription(string);
            this.mCaptureButton.setTooltipText(string);
            this.mCaptureButton.setImageResource(setIconRes());
            this.mCaptureButton.setOnClickListener(new f(this));
        }
    }

    public void endCapture() {
        hidePreview(true);
        updateButtonVisibility();
        setEnabled(true);
    }

    public void execCapture() {
        Context context = this.mModel.getContext();
        if (Features.isEnabled(Features.SUPPORT_DC_HAPTIC) && context != null) {
            SeApiCompat.performHapticFeedback(context.getApplicationContext(), 4);
        }
    }

    public abstract int getTitleRes();

    public void handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3024) {
            Log.d("ViewerCapture", "EventKey.EVENT_VIEWER_QUICK_CROP_PROCESSING");
            onCaptureProcessing(((Long) eventMessage.obj).longValue());
            setEnabled(false);
        } else if (i2 == 3025) {
            Log.d("ViewerCapture", "EventKey.EVENT_VIEWER_QUICK_CROP_COMPLETE");
            onCaptureComplete((Object[]) eventMessage.obj);
            releaseCaptureCmd();
            setEnabled(true);
        }
    }

    public void hidePreview(boolean z) {
        QuickCropPreView quickCropPreView;
        if (!(this.mPreviewLayout == null || (quickCropPreView = this.mPreview) == null)) {
            quickCropPreView.setOnClickListener((View.OnClickListener) null);
        }
        if (!ViewUtils.isShown(this.mPreviewContainer) || !z) {
            ViewUtils.post(this.mPreviewContainer, new a(this, 4));
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.mPreviewContainer.getContext(), R.anim.oneui50_viewer2_capture_preview_hide);
            this.mHideAnimation = loadAnimation;
            loadAnimation.setInterpolator(new PathInterpolator(0.22f, 0.0f, 0.0f, 1.0f));
            SimpleAnimator.addListenerForHideAnimation(this.mPreviewContainer, this.mHideAnimation, new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    ViewerCapture.this.notifyCaptureEnded();
                }
            });
            this.mPreviewContainer.startAnimation(this.mHideAnimation);
        }
        ViewUtils.post(this.mPreviewContainer, new a(this, 5));
    }

    public boolean isButtonDimmed() {
        ImageView imageView = this.mCaptureButton;
        if (imageView == null || imageView.isEnabled()) {
            return false;
        }
        return true;
    }

    public boolean isCaptureAndGo() {
        return this.mIsCaptureAndGo;
    }

    public boolean isCaptureButtonVisible() {
        if (!this.mIsSupport.getAsBoolean() || !supportCapture(this.mModel.getMediaItem())) {
            return false;
        }
        return true;
    }

    public boolean isCaptureProcessing() {
        return ViewUtils.isVisible(this.mPreviewContainer);
    }

    public abstract boolean isVideoMode();

    public void onCaptureClick() {
        Context context;
        execCapture();
        ImageView imageView = this.mCaptureButton;
        if (!(imageView == null || (context = imageView.getContext()) == null)) {
            SeApiCompat.announceVoiceAssistant(context, context.getString(R.string.captured));
        }
        if (!isCaptureProcessing()) {
            removeCapturePreviewHideCallback();
        }
    }

    public void onQuickCropPreviewClicked(MediaItem mediaItem, String str) {
        String str2;
        MediaItem mediaItem2 = this.mModel.getMediaItem();
        if (mediaItem2 != null) {
            boolean z = UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(str, "KEY_IS_SHARE"));
            String str3 = null;
            if (z) {
                str2 = ArgumentsUtil.getArgValue(str, "KEY_TARGET_COMPONENT_PACKAGE");
            } else {
                str2 = null;
            }
            if (z) {
                str3 = ArgumentsUtil.getArgValue(str, "KEY_TARGET_COMPONENT_ACTIVITY");
            }
            String str4 = str3;
            if (this.mModel.getSystemUi().isScreenLocked()) {
                new RequestDismissKeyGuardCmd().execute(this.mModel.getContainerModel().getEventContext(), new b(this, mediaItem, mediaItem2, z, str2, str4), new a(this, 3));
                return;
            }
            launchQuickCropItem(mediaItem, mediaItem2.getMediaId(), z, str2, str4);
        }
    }

    public abstract void onViewConfirm();

    public void releaseCaptureCmd() {
        SaveCaptureCmd saveCaptureCmd = this.mCaptureCmd;
        if (saveCaptureCmd != null) {
            saveCaptureCmd.setCallback((Consumer<EventMessage>) null);
            this.mCaptureCmd.unsubscribeEvent();
            this.mCaptureCmd = null;
        }
    }

    public void removeCapturePreviewHideCallback() {
        ThreadUtil.removeCallbackOnUiThread(this.hideCallback);
    }

    public void resetCaptureAndGo() {
        this.mIsCaptureAndGo = false;
        this.mIsShare = false;
        this.mPackageName = null;
        this.mActivityName = null;
    }

    public void setButtonVisibility(int i2) {
        setButtonVisibility(i2, true);
    }

    public void setCallback(SaveCaptureCmd saveCaptureCmd) {
        if (this.mCaptureCmd != null) {
            Log.d("ViewerCapture", "Capture remove last callback");
            this.mCaptureCmd.setCallback((Consumer<EventMessage>) null);
        }
        this.mCaptureCmd = saveCaptureCmd;
        saveCaptureCmd.setCallback(new h(this));
    }

    public void setCaptureAndGo(boolean z, String str, String str2) {
        this.mIsCaptureAndGo = true;
        this.mIsShare = z;
        this.mPackageName = str;
        this.mActivityName = str2;
    }

    public void setCaptureEndListener(CaptureEndListener captureEndListener) {
        this.mCaptureEndListener = captureEndListener;
    }

    public void setEnabled(boolean z) {
        ViewUtils.setViewEnabled(this.mCaptureButton, z);
    }

    public abstract int setIconRes();

    public boolean supportCapture(MediaItem mediaItem) {
        if (mediaItem == null || unsupportableLocation() || !QuickCropHelper.isSupported(new a(this, 6))) {
            return false;
        }
        if ((!mediaItem.isImage() || mediaItem.getFileSize() < 2147483648L) && !mediaItem.isBroken() && !mediaItem.isDrm() && !mediaItem.isPrivateItem() && !TrashData.isTrash(mediaItem) && QuickCropHelper.isSupportedFormat(mediaItem) && !MediaItemUtil.isUsbFile(mediaItem)) {
            return true;
        }
        return false;
    }

    public void unbindView() {
        ImageView imageView = this.mCaptureButton;
        if (imageView != null) {
            imageView.setOnClickListener((View.OnClickListener) null);
            this.mCaptureButton = null;
        }
        onCaptureComplete((Object[]) null);
        releaseCaptureCmd();
    }

    public void updateButtonVisibility() {
        setButtonVisibility(isCaptureButtonVisible() ? 0 : 8, true);
    }

    public void setButtonVisibility(int i2, boolean z) {
        ViewUtils.post(this.mCaptureButton, new g(this, z, i2));
    }

    public void updateButtonVisibility(boolean z) {
        setButtonVisibility(isCaptureButtonVisible() ? 0 : 8, z);
    }

    public void onZoomChanged() {
    }
}
