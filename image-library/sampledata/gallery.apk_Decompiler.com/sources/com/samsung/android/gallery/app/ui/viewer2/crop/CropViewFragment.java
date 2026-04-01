package com.samsung.android.gallery.app.ui.viewer2.crop;

import I7.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewPresenter;
import com.samsung.android.gallery.app.ui.viewer2.crop.ICropView;
import com.samsung.android.gallery.app.ui.viewer2.crop.handler.CropHelper;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.crop.api.CropView;
import com.samsung.android.gallery.widget.crop.config.CropViewConfig;
import com.samsung.android.gallery.widget.photoview.CropPhotoView;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CropViewFragment<V extends ICropView, P extends CropViewPresenter> extends MvpBaseFragment<V, P> implements ICropView {
    /* access modifiers changed from: private */
    public final CropMenuDelegate mCropMenuDelegate = new CropMenuDelegate(this.TAG);
    protected CropView mCropView;
    private boolean mInternalCrop;
    /* access modifiers changed from: private */
    public boolean mIsFromGalleryPicker;
    private boolean mIsSetAsContactPhoto;
    private boolean mIsTransitionFinish = false;
    final q mMenuItemSelectedListener = new q() {
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            AnalyticsEventId analyticsEventId;
            AnalyticsEventId analyticsEventId2;
            CropViewFragment cropViewFragment = CropViewFragment.this;
            if (!cropViewFragment.setInputBlock(CropViewFragment.this.TAG + "_onNavigationItemSelected")) {
                return true;
            }
            switch (menuItem.getItemId()) {
                case R.id.menu_edit_app_bar_cancel:
                    CropViewFragment cropViewFragment2 = CropViewFragment.this;
                    if (cropViewFragment2.mIsFromGalleryPicker) {
                        analyticsEventId = AnalyticsEventId.EVENT_CROP_IMAGE_CANCEL_FROM_PICK;
                    } else {
                        analyticsEventId = AnalyticsEventId.EVENT_CROP_IMAGE_CANCEL;
                    }
                    cropViewFragment2.postAnalyticsLog(analyticsEventId);
                    CropViewFragment.this.mCropMenuDelegate.discardCropChange();
                    return true;
                case R.id.menu_edit_app_bar_done:
                    CropViewFragment cropViewFragment3 = CropViewFragment.this;
                    if (cropViewFragment3.mIsFromGalleryPicker) {
                        analyticsEventId2 = AnalyticsEventId.EVENT_CROP_IMAGE_SAVE_FROM_PICK;
                    } else {
                        analyticsEventId2 = AnalyticsEventId.EVENT_CROP_IMAGE_DONE;
                    }
                    cropViewFragment3.postAnalyticsLog(analyticsEventId2);
                    CropViewFragment.this.mCropMenuDelegate.saveCropChange();
                    return true;
                default:
                    return true;
            }
        }
    };
    protected CropPhotoView mPhotoView;
    protected PhotoPreView mPreview;
    private Bitmap mPreviewBitmap;
    private View mProgressCircle;
    private int mReturnPosition;

    private RectF getDisplayRect() {
        RectF displayRect = this.mPhotoView.getDisplayRect();
        if (displayRect == null) {
            return null;
        }
        int statusBarHeight = getStatusBarHeight();
        int horizontalInset = getHorizontalInset();
        float f = (float) statusBarHeight;
        displayRect.top += f;
        displayRect.bottom += f;
        float f5 = (float) horizontalInset;
        displayRect.left += f5;
        displayRect.right += f5;
        return displayRect;
    }

    private int getHorizontalInset() {
        if (!isLandscape() || isInMultiWindowMode()) {
            return 0;
        }
        return DeviceInfo.getNavigationBarHeight();
    }

    private TransitionInfo getTransitionInfo() {
        Bitmap bitmap;
        CropPhotoView cropPhotoView;
        if (Features.isEnabled(Features.SUPPORT_ORIGINAL_BITMAP_SHRINK) && this.mBlackboard != null) {
            MediaItem mediaItem = ((CropViewPresenter) this.mPresenter).getMediaItem();
            if (mediaItem != null) {
                bitmap = (Bitmap) this.mBlackboard.read(MediaItemUtil.getViewerBitmapKey(mediaItem));
            } else {
                bitmap = null;
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                TransitionInfo original = new TransitionInfo(mediaItem, bitmap, this.mReturnPosition).setOriginal(true);
                if (!mediaItem.isSharing() && !mediaItem.isCloudOnly() && (cropPhotoView = this.mPhotoView) != null) {
                    original.setScale(cropPhotoView.getCurrentScale(), this.mPhotoView.getScaledPosition());
                }
                return original;
            }
        }
        return new TransitionInfo(((CropViewPresenter) this.mPresenter).getMediaItem(), this.mPreviewBitmap, this.mReturnPosition);
    }

    private void initPreview() {
        Bitmap bitmap;
        MediaItem mediaItem = ((CropViewPresenter) this.mPresenter).getMediaItem();
        if (mediaItem != null && this.mPreview != null && (bitmap = this.mPreviewBitmap) != null) {
            int errorResult = CropHelper.getErrorResult(bitmap, this.mIsSetAsContactPhoto);
            if (errorResult != 0) {
                StringCompat stringCompat = this.TAG;
                StringBuilder o2 = C0086a.o(errorResult, "invalid preview bitmap( ", ") ");
                o2.append(Logger.toString(this.mPreviewBitmap));
                Log.e(stringCompat, o2.toString());
                ((CropViewPresenter) this.mPresenter).finishWithErrorMsg(errorResult);
                return;
            }
            updateItemSizeInfo(mediaItem, this.mPreviewBitmap);
            this.mPreview.setBasicInfo(this.mPreviewBitmap, mediaItem, new MarginParams());
            ViewUtils.setVisibility(this.mPhotoView, 8);
            ViewUtils.setVisibility(this.mPreview, 0);
            SharedTransition.setTransitionName(this.mPreview, SharedTransition.getTransitionName(mediaItem));
            ThreadUtil.postOnUiThread(new a(this, 0));
        }
    }

    private void initialize(Bundle bundle) {
        if (!this.mBlackboard.isEmpty("data://user/Crop/ReqInfo")) {
            this.mInternalCrop = true;
        }
        this.mIsFromGalleryPicker = bundle.getBoolean("FromGalleryPicker", false);
        this.mIsSetAsContactPhoto = bundle.getBoolean("set-as-contactphoto", false);
        this.mCropMenuDelegate.initBottomNavigation(CropHelper.getCustomNavigationMenu(getContext(), bundle), this.mMenuItemSelectedListener);
        this.mCropMenuDelegate.setBlackboard(this.mBlackboard);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreview$0() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
    }

    private Bundle loadBundle() {
        Bundle bundle = (Bundle) this.mBlackboard.read("data://user/Crop/ReqInfo", null);
        if (bundle != null) {
            return bundle;
        }
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent", null);
        if (launchIntent == null) {
            return null;
        }
        return launchIntent.getExtra();
    }

    private void setFinishTransition() {
        if (this.mReturnPosition != -1) {
            this.mBlackboard.publish("data://shrink_active", DataKey.ShrinkType.BACK_PRESSED);
        }
        this.mBlackboard.publish("data://avoid_status_bar_color", Boolean.TRUE);
        SharedTransition.setReturnPosition(this.mBlackboard, this.mReturnPosition);
        TransitionInfo transitionInfo = getTransitionInfo();
        if (transitionInfo == null || !transitionInfo.hasValidData()) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "onBackPressed. invalid transition data " + transitionInfo);
            setSharedElementReturnTransition((Object) null);
            return;
        }
        transitionInfo.setDisplayRect(getDisplayRect());
        transitionInfo.publish(this.mBlackboard);
        Log.st(this.TAG, "onBackPressed");
    }

    /* access modifiers changed from: private */
    /* renamed from: setMovieBitmap */
    public void lambda$onAnimationFrameUpdated$1(Bitmap bitmap) {
        if (this.mPhotoView != null && bitmap != null && !bitmap.isRecycled()) {
            this.mPhotoView.setImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    private void updateItemSizeInfo(MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem.isBroken()) {
            mediaItem.setSize(bitmap.getWidth(), bitmap.getHeight());
        } else if (MediaItemUtil.checkWrongSize(mediaItem, bitmap)) {
            long currentTimeMillis = System.currentTimeMillis();
            BitmapOptions parse = BitmapOptionsFactory.parse(mediaItem.getPath(), true);
            if (parse.outWidth > 0 && parse.outHeight > 0) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "updateItemInfoSize " + MediaItemUtil.getMediaLog(mediaItem) + Logger.vt(Integer.valueOf(parse.outWidth), Integer.valueOf(parse.outHeight), Long.valueOf(currentTimeMillis)));
                mediaItem.setSize(parse.outWidth, parse.outHeight);
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    FilesApi.updateMediaAsync(ContentUri.getSecUri(mediaItem), parse.outWidth, parse.outHeight);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateViewVisibility() {
        this.mCropMenuDelegate.showBottomNavigation();
        PhotoPreView photoPreView = this.mPreview;
        if (photoPreView != null) {
            photoPreView.setImageBitmap((Bitmap) null);
        }
        ViewUtils.setVisibility(this.mPreview, 8);
        ViewUtils.setVisibility(this.mPhotoView, 0);
        ViewUtils.setVisibility(this.mProgressCircle, 8);
    }

    public void finishCropViewer(Object obj) {
        if (this.mInternalCrop) {
            setFinishTransition();
            if (BlackboardUtils.isViewerShrink(getBlackboard())) {
                this.mPreview.setTransitionName((String) null);
            }
            this.mBlackboard.post("data://user/Crop", obj);
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            return;
        }
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    public int getLayoutId() {
        return R.layout.image_cropper_fragment_layout;
    }

    public RectF getRectToBeSaved() {
        return this.mCropView.getRectToBeSaved();
    }

    public String getScreenId() {
        AnalyticsScreenId analyticsScreenId;
        if (this.mIsFromGalleryPicker) {
            analyticsScreenId = AnalyticsScreenId.SCREEN_CROP_IMAGE_FROM_PICK;
        } else {
            analyticsScreenId = AnalyticsScreenId.SCREEN_CROP_IMAGE;
        }
        return analyticsScreenId.toString();
    }

    public void initView(View view) {
        Bundle loadBundle = loadBundle();
        if (loadBundle != null) {
            initialize(loadBundle);
            this.mCropView.onViewCreated(new CropViewConfig(loadBundle, ((CropViewPresenter) this.mPresenter).getMediaItem()), this.mPhotoView, getScreenId());
            CropPhotoView cropPhotoView = this.mPhotoView;
            cropPhotoView.setMotionControl(cropPhotoView.createDefaultMotionControl(cropPhotoView.getParent()), (OnViewerExitGestureListener) null);
            if (this.mPhotoView.getMotionControl() != null) {
                this.mPhotoView.getMotionControl().supportExitGesture(false);
            }
            initPreview();
            return;
        }
        Log.w(this.TAG, "onViewCreated failed : no extra");
    }

    public boolean isReadyCropView() {
        return this.mIsTransitionFinish;
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        CropPhotoView cropPhotoView = this.mPhotoView;
        if (cropPhotoView != null) {
            cropPhotoView.postOnAnimation(new H.a(15, this, bitmap));
        }
    }

    public boolean onBackPressed() {
        this.mCropMenuDelegate.showConfirmDialog(getContext(), this.mCropView.isCropAreaChanged());
        return true;
    }

    public void onBindView(View view) {
        this.mCropView = (CropView) view.findViewById(R.id.crop_view);
        this.mPhotoView = (CropPhotoView) view.findViewById(R.id.photo_view);
        this.mPreview = (PhotoPreView) view.findViewById(R.id.viewer_preview);
        this.mProgressCircle = view.findViewById(R.id.progressCircle);
        this.mCropMenuDelegate.bindViewInternal(view);
        TransitionInfo popTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        if (popTransitionInfo != null) {
            this.mPreviewBitmap = popTransitionInfo.bitmap;
            this.mReturnPosition = popTransitionInfo.dataPosition;
            return;
        }
        this.mPreview.setImageBitmap((Bitmap) null);
        this.mReturnPosition = -1;
        this.mIsTransitionFinish = true;
    }

    public void onDestroy() {
        super.onDestroy();
        CropPhotoView cropPhotoView = this.mPhotoView;
        if (cropPhotoView != null) {
            cropPhotoView.clearBitmap();
            this.mPhotoView = null;
        }
        PhotoPreView photoPreView = this.mPreview;
        if (photoPreView != null) {
            photoPreView.destroy();
        }
        this.mPreview = null;
        this.mCropMenuDelegate.unbindView();
        this.mPreviewBitmap = null;
        this.mReturnPosition = -1;
    }

    public void onEnterTransitionEndV2() {
        this.mIsTransitionFinish = true;
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((CropViewPresenter) p6).onEnterTransitionEndV2();
            return;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3017));
        this.mBlackboard.erase("data://shared_original_bitmap");
    }

    public void setDefaultImage(Bitmap bitmap) {
        if (isDestroyed() || !this.mIsTransitionFinish) {
            Log.w((CharSequence) this.TAG, "setDefaultImage skip", Boolean.valueOf(isDestroyed()), Boolean.valueOf(!this.mIsTransitionFinish));
            return;
        }
        MediaItem mediaItem = ((CropViewPresenter) this.mPresenter).getMediaItem();
        CropHelper.fixWrongOrientation(mediaItem, bitmap);
        int errorResult = CropHelper.getErrorResult(bitmap, this.mIsSetAsContactPhoto);
        if (this.mPhotoView == null || errorResult != 0) {
            Log.e(this.TAG, "setDefaultImage failed");
            ((CropViewPresenter) this.mPresenter).finishWithErrorMsg(errorResult);
            return;
        }
        if (bitmap != null && !bitmap.isRecycled()) {
            ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.VF_HQ);
            ViewerPerformanceTracker.dump();
            if (mediaItem != null) {
                this.mPhotoView.setImage(mediaItem, bitmap);
            } else {
                this.mPhotoView.setImage(bitmap);
            }
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setDefaultImage " + Logger.toSimpleString(bitmap));
        this.mPhotoView.post(new a(this, 1));
        this.mCropView.onBitmapPrepared(bitmap);
        ((CropViewPresenter) this.mPresenter).onViewReady();
    }

    public void setProgressCircleVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mProgressCircle, z);
    }

    public void setScreenMode() {
        enterFullScreen(true);
    }

    public boolean supportToolbar() {
        return false;
    }

    public CropViewPresenter createPresenter(ICropView iCropView) {
        return new CropViewPresenter(this.mBlackboard, this);
    }
}
