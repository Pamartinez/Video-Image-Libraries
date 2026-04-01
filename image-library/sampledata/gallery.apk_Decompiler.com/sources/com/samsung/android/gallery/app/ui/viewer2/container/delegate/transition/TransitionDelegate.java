package com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.viewer.LastPreviewData;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionData;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.ocr.MOCRLang;
import com.sec.android.gallery3d.R;
import g6.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import l6.a;
import n0.C0235b;
import q6.e;
import s7.C0515a;
import s7.C0516b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionDelegate extends AbsVuDelegate<IVuContainerView> {
    private final HashMap<String, Bitmap> mEnterTransitionBitmapMap = new HashMap<>();
    private TransitionInfo mEnterTransitionInfo;
    protected PhotoPreView mPreview;

    public TransitionDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void createEnterTransitionBitmapMap() {
        Map<String, View> transitionMap = SharedTransition.getTransitionMap(this.mBlackboard);
        if (transitionMap != null) {
            transitionMap.forEach(new f(9, this));
        }
    }

    private float getInitialScale(PhotoViewCompat photoViewCompat, Bitmap bitmap, MediaItem mediaItem) {
        int i2;
        if (mediaItem.isCloudOnly()) {
            int computeInSampleSize = BitmapOptions.computeInSampleSize(photoViewCompat.getSourceWidth(), photoViewCompat.getSourceHeight(), BitmapSizeHolder.size());
            if (mediaItem.getOrientation() % MOCRLang.KHMER == 0) {
                i2 = bitmap.getWidth();
            } else {
                i2 = bitmap.getHeight();
            }
            RectF displayRect = photoViewCompat.getDisplayRect();
            if (displayRect != null) {
                return (displayRect.width() / ((float) computeInSampleSize)) / ((float) i2);
            }
        }
        return photoViewCompat.getCurrentScale();
    }

    private Bitmap getReturnTransitionBitmap(MediaItem mediaItem, String str) {
        Bitmap bitmap = this.mEnterTransitionBitmapMap.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        return ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.MEDIUM_KIND);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createEnterTransitionBitmapMap$6(String str, View view) {
        BitmapDrawable bitmapDrawable;
        if (view instanceof ImageView) {
            bitmapDrawable = (BitmapDrawable) ((ImageView) view).getDrawable();
        } else {
            bitmapDrawable = null;
        }
        if (bitmapDrawable != null) {
            this.mEnterTransitionBitmapMap.put(str, bitmapDrawable.getBitmap());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreview$4(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        String str = this.TAG;
        Log.d(str, "Preview load async media = " + mediaItem);
        setPreview(bitmap, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$2() {
        this.mPreview.setBottomMargin(getPhotoPreViewMargin().bottomMargin);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBitmapLoaded$1() {
        ViewUtils.setVisibleOrGone(this.mPreview, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandleEvent$0() {
        ViewUtils.setVisibleOrGone(this.mPreview, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareGroupPanelReturnTransition$10() {
        TransitionInfo popTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || popTransitionInfo == null || currentViewer.getModel().getMediaItem() == null) {
            this.mPreview.setVisibility(8);
            String str = this.TAG;
            Log.st(str, "current viewer is " + currentViewer + ", info is " + popTransitionInfo);
            return;
        }
        MediaItem mediaItem = currentViewer.getModel().getMediaItem();
        SharedTransition.setTransitionName(this.mPreview, "groupPanel/");
        this.mPreview.setBasicInfo(popTransitionInfo.bitmap, mediaItem, getPhotoPreViewMargin());
        this.mPreview.setVisibility(0);
        currentViewer.getModel().setPendingPhotoViewSet(true);
        PhotoViewCompat photoViewCompat = (PhotoViewCompat) currentViewer.getViewHolder().itemView.findViewById(R.id.photo_view);
        ViewUtils.post(photoViewCompat, new C0235b(this, mediaItem, photoViewCompat, 20));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareGroupPanelReturnTransition$9(MediaItem mediaItem, PhotoViewCompat photoViewCompat) {
        float f;
        PointF pointF;
        if (mediaItem.isCloudOnly() || photoViewCompat.isMinScale()) {
            f = -1.0f;
        } else {
            f = photoViewCompat.getCurrentScale();
        }
        if (mediaItem.isCloudOnly()) {
            pointF = null;
        } else {
            pointF = photoViewCompat.getScaledPosition();
        }
        this.mPreview.setScaledTransitionInfo(f, pointF);
        this.mPreview.updateLayoutSize();
        ViewUtils.setVisibility(photoViewCompat, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareRemasterReturnTransition$7(PhotoViewCompat photoViewCompat) {
        this.mPreview.setScaledTransitionInfo(-1.0f, (PointF) null);
        this.mPreview.updateLayoutSize();
        ViewUtils.setVisibility(photoViewCompat, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareRemasterReturnTransition$8() {
        TransitionInfo popTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || popTransitionInfo == null) {
            this.mPreview.setVisibility(8);
            String str = this.TAG;
            Log.st(str, "current viewer is " + currentViewer + ", info is " + popTransitionInfo);
            return;
        }
        MediaItem mediaItem = currentViewer.getModel().getMediaItem();
        if (mediaItem != null) {
            SharedTransition.setTransitionName(this.mPreview, SharedTransition.getTransitionName("remaster/", popTransitionInfo.item));
            this.mPreview.setBasicInfo(popTransitionInfo.bitmap, mediaItem, getPhotoPreViewMargin());
            this.mPreview.setVisibility(0);
            currentViewer.getModel().setPendingPhotoViewSet(true);
            PhotoViewCompat photoViewCompat = (PhotoViewCompat) currentViewer.getViewHolder().itemView.findViewById(R.id.photo_view);
            photoViewCompat.resetScaleAndCenter();
            photoViewCompat.post(new e(6, this, photoViewCompat));
            return;
        }
        this.mPreview.setVisibility(8);
        Log.st(this.TAG, "prepareReturnTransition skip");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPreview$5(Bitmap bitmap, MediaItem mediaItem) {
        this.mPreview.setBasicInfo(bitmap, mediaItem, getPhotoPreViewMargin());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startTransition$3(SharedTransition.TransitionListener transitionListener) {
        if (!((IVuContainerView) this.mView).isDestroyed()) {
            transitionListener.onEnterTransitionStartV2();
            transitionListener.onEnterTransitionEndV2();
            return;
        }
        Log.v(this.TAG, "destroyed. skip enter tr");
    }

    private void loadPreview() {
        Bitmap loadPreview;
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        if (currentMediaItem != null && (loadPreview = ThumbnailLoader.getInstance().loadPreview(currentMediaItem, new a(6, this, currentMediaItem))) != null) {
            Log.d(this.TAG, "Preview load sync");
            setPreview(loadPreview, currentMediaItem);
        }
    }

    /* access modifiers changed from: private */
    public void onBitmapLoaded(Object... objArr) {
        ViewerObjectComposite currentViewer;
        if (this.mPreview.getVisibility() == 0 && (currentViewer = ((IVuContainerView) this.mView).getCurrentViewer()) != null && !currentViewer.getModel().isPendingPhotoViewSet()) {
            Log.d(this.TAG, "Preview gone by image loaded");
            ThreadUtil.runOnUiThread(new C0516b(this, 1));
        }
    }

    private void prepareBurstShotReturnTransition() {
        TransitionInfo popTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || popTransitionInfo == null) {
            this.mPreview.setVisibility(8);
            String str = this.TAG;
            Log.st(str, "current viewer is " + currentViewer + ", info is " + popTransitionInfo);
            return;
        }
        MediaItem mediaItem = popTransitionInfo.item;
        if (mediaItem == null) {
            mediaItem = currentViewer.getModel().getMediaItem();
        }
        if (mediaItem != null) {
            SharedTransition.setTransitionName(this.mPreview, "burstShotSelection/");
            this.mPreview.setBasicInfo(popTransitionInfo.bitmap, mediaItem, getPhotoPreViewMargin());
            ViewUtils.setVisibility(this.mPreview, 0);
            currentViewer.getModel().setPendingPhotoViewSet(true);
            return;
        }
        this.mPreview.setVisibility(8);
        Log.st(this.TAG, "prepareReturnTransition skip");
    }

    private void prepareGroupPanelReturnTransition() {
        ViewUtils.post(this.mPreview, new C0516b(this, 0));
    }

    private void prepareRemasterReturnTransition() {
        ViewUtils.post(this.mPreview, new C0516b(this, 4));
    }

    /* access modifiers changed from: private */
    public void prepareReturnTransition(Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "prepareReturnTransition failed : item is null");
            return;
        }
        int intValue = objArr[1].intValue();
        String str = objArr[2];
        String transitionName = str != null ? str : SharedTransition.getTransitionName(mediaItem);
        Bitmap returnTransitionBitmap = getReturnTransitionBitmap(mediaItem, transitionName);
        if (returnTransitionBitmap != null) {
            SharedTransition.setTransitionName(this.mPreview, transitionName);
            ViewUtils.setVisibleOrGone(this.mPreview, true);
            this.mPreview.setScaledTransitionInfo(-1.0f, (PointF) null);
            this.mPreview.setBasicInfo(returnTransitionBitmap, mediaItem, getPhotoPreViewMargin());
            SharedTransition.setReturnPosition(this.mBlackboard, intValue);
            SharedTransition.prepareReturn(new TransitionData.Builder().setBlackboard(this.mBlackboard).setListener((SharedTransition.TransitionListener) this.mView).setEnterTransitionId(SharedTransition.getViewerEnterSharedTransitionResourceId()).setReturnTransitionId(SharedTransition.getViewerEnterSharedTransitionResourceId()).setReparentWithOverlay(true).build());
        } else {
            Log.e((CharSequence) this.TAG, "prepareReturnTransition failed", this.mEnterTransitionBitmapMap);
        }
        this.mEnterTransitionBitmapMap.clear();
    }

    private void setPreview(Bitmap bitmap, MediaItem mediaItem) {
        if (bitmap != null) {
            ThreadUtil.runOnUiThread(new C0235b(this, bitmap, mediaItem, 21));
        }
    }

    /* access modifiers changed from: private */
    public void startGroupPanelVI(Object... objArr) {
        Bitmap bitmap;
        float f;
        if (Utils.isAnimationDuration0x(getContext())) {
            Log.d(this.TAG, "failed to start remaster transition: animation off");
            return;
        }
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            ContentModel model = currentViewer.getModel();
            MediaItem mediaItem = currentViewer.getModel().getMediaItem();
            if (mediaItem != null) {
                if (model.hasChildModel()) {
                    bitmap = model.getChildBitmap();
                } else {
                    bitmap = model.getBitmap();
                }
                PhotoViewCompat photoViewCompat = (PhotoViewCompat) currentViewer.getViewHolder().itemView.findViewById(R.id.photo_view);
                SharedTransition.setTransitionName(this.mPreview, "groupPanel/");
                if (photoViewCompat != null && (mediaItem.isLocal() || !photoViewCompat.isMinScale())) {
                    if (mediaItem.isVideo()) {
                        f = -1.0f;
                    } else {
                        f = getInitialScale(photoViewCompat, bitmap, mediaItem);
                    }
                    this.mPreview.setScaledTransitionInfo(f, photoViewCompat.getScaledPosition());
                }
                this.mPreview.setBasicInfo(bitmap, mediaItem, getPhotoPreViewMargin());
                this.mPreview.setVisibility(0);
                SharedTransition.addSharedElement(this.mBlackboard, this.mPreview, "groupPanel/", new TransitionInfo(mediaItem, bitmap));
                return;
            }
            Log.st(this.TAG, "current item is null");
            return;
        }
        Log.st(this.TAG, "current viewer is null");
    }

    private boolean startPreview() {
        this.mEnterTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        createEnterTransitionBitmapMap();
        return startPreview(this.mEnterTransitionInfo);
    }

    /* access modifiers changed from: private */
    public void startRemasterViewVi(Object... objArr) {
        Bitmap bitmap;
        float f;
        if (Utils.isAnimationDuration0x(getContext())) {
            Log.d(this.TAG, "failed to start remaster transition: animation off");
            return;
        }
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            MediaItem mediaItem = currentViewer.getModel().getMediaItem();
            if (mediaItem != null) {
                if (!mediaItem.isSingleTakenShot() || LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
                    bitmap = currentViewer.getModel().getBitmap();
                } else {
                    bitmap = currentViewer.getModel().getChildBitmap();
                }
                PhotoViewCompat photoViewCompat = (PhotoViewCompat) currentViewer.getViewHolder().itemView.findViewById(R.id.photo_view);
                SharedTransition.setTransitionName(this.mPreview, "remaster/");
                this.mPreview.setImageBitmap(bitmap);
                if (mediaItem.isLocal() || !photoViewCompat.isMinScale()) {
                    this.mPreview.setScaledTransitionInfo(getInitialScale(photoViewCompat, bitmap, mediaItem), photoViewCompat.getScaledPosition());
                }
                MarginParams photoPreViewMargin = getPhotoPreViewMargin();
                if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS && !BottomSheetState.Details.isClosed((BottomSheetState.StateListener) this.mModel) && objArr[0].booleanValue()) {
                    Size displaySize = DeviceInfo.getDisplaySize(((ContainerModel) this.mModel).getActivity());
                    if (!((IVuContainerView) this.mView).isLandscape() || ((ContainerModel) this.mModel).isTableMode()) {
                        RectF displayRect = photoViewCompat.getDisplayRect();
                        float height = (float) displaySize.getHeight();
                        if (displayRect != null) {
                            f = photoViewCompat.getDisplayRect().bottom;
                        } else {
                            f = 0.0f;
                        }
                        photoPreViewMargin.bottomMargin = (int) (height - f);
                    } else {
                        photoPreViewMargin.rightMargin = displaySize.getWidth() / 2;
                    }
                }
                this.mPreview.setBasicInfo(bitmap, mediaItem, photoPreViewMargin);
                this.mPreview.setVisibility(0);
                SharedTransition.addSharedElement(this.mBlackboard, this.mPreview, SharedTransition.getTransitionName("remaster/", mediaItem), new TransitionInfo(mediaItem, bitmap));
                return;
            }
            Log.st(this.TAG, "current item is null");
            return;
        }
        Log.st(this.TAG, "current viewer is null");
    }

    /* access modifiers changed from: private */
    public void startSelectionViewVi(Object... objArr) {
        Bitmap bitmap;
        MediaItem mediaItem;
        if (Utils.isAnimationDuration0x(getContext())) {
            Log.d(this.TAG, "failed to start burst shot transition: animation off");
            return;
        }
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            bitmap = currentViewer.getModel().getBitmap();
        } else {
            bitmap = null;
        }
        if (currentViewer != null) {
            mediaItem = currentViewer.getModel().getMediaItem();
        } else {
            mediaItem = null;
        }
        if (bitmap == null || mediaItem == null) {
            Log.w((CharSequence) this.TAG, "startBurstShotVi fail", bitmap, this.mPreview, mediaItem);
            return;
        }
        PhotoViewCompat photoViewCompat = (PhotoViewCompat) currentViewer.getViewHolder().itemView.findViewById(R.id.photo_view);
        if (photoViewCompat != null) {
            photoViewCompat.resetScaleAndCenter();
        }
        ViewUtils.setVisibility(this.mPreview, 0);
        this.mPreview.setScaledTransitionInfo(-1.0f, (PointF) null);
        this.mPreview.setBasicInfo(bitmap, mediaItem, getPhotoPreViewMargin());
        SharedTransition.setTransitionName(getTransitionView(), (String) null);
        SharedTransition.addSharedElement(this.mBlackboard, this.mPreview, "burstShotSelection/", new TransitionInfo(mediaItem, bitmap));
    }

    /* access modifiers changed from: private */
    public void startTransition(Blackboard blackboard, SharedTransition.TransitionListener transitionListener) {
        if (!SharedTransition.startPostponedEnterTransition(transitionListener, blackboard)) {
            ThreadUtil.postOnUiThread(new e(7, this, transitionListener));
        }
    }

    public void bindView(View view) {
        if (!startPreview()) {
            Object[] objArr = (Object[]) this.mBlackboard.pop("data://viewer_first_bitmap");
            if (objArr != null) {
                startPreview(new TransitionInfo((MediaItem) objArr[0], (Bitmap) objArr[1], 0));
            } else {
                Log.i(this.TAG, "preview null");
                loadPreview();
            }
            startTransition(this.mBlackboard, (SharedTransition.TransitionListener) this.mView);
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
    }

    public Context getContext() {
        PhotoPreView photoPreView;
        Context context = super.getContext();
        if (context != null || (photoPreView = this.mPreview) == null) {
            return context;
        }
        return photoPreView.getContext();
    }

    public int getEnterSharedTransitionResourceId() {
        return SharedTransition.getViewerEnterSharedTransitionResourceId();
    }

    public MarginParams getPhotoPreViewMargin() {
        MarginParams marginParams = new MarginParams();
        if (((IVuContainerView) this.mView).isInMultiWindowMode() || !((ContainerModel) this.mModel).isTableMode()) {
            if (((ContainerModel) this.mModel).isFlipCoverGallery() && !((IVuContainerView) this.mView).isDestroyed() && !((ContainerModel) this.mModel).getStateHelper().isFullScreenInFlipCover(((ContainerModel) this.mModel).getCurrentMediaItem(), DeviceInfo.getDisplaySize(((ContainerModel) this.mModel).getActivity()))) {
                Rect cutouts = ((ContainerModel) this.mModel).getCutouts();
                marginParams.topMargin = cutouts.top;
                marginParams.leftMargin = cutouts.left;
                marginParams.bottomMargin = cutouts.bottom;
                marginParams.rightMargin = cutouts.right;
            }
            return marginParams;
        }
        marginParams.bottomMargin = DeviceInfo.getDisplaySize(((ContainerModel) this.mModel).getActivity()).getHeight() / 2;
        return marginParams;
    }

    public int getReturnSharedTransitionResourceId() {
        return SharedTransition.getViewerEnterSharedTransitionResourceId();
    }

    public View getTransitionView() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            return currentViewer.getViewHolder().getTransitionView();
        }
        return null;
    }

    public void onApplyWindowInsets() {
        ViewUtils.post(this.mPreview, new C0516b(this, 2));
    }

    public void onBindView(View view) {
        if (this.mPreview == null) {
            PhotoPreView photoPreView = (PhotoPreView) view.findViewById(R.id.viewer_container_preview);
            this.mPreview = photoPreView;
            photoPreView.setLogTag(((ContainerModel) this.mModel).getPosition());
            return;
        }
        PhotoViewCompat photoViewCompat = (PhotoViewCompat) view.findViewById(R.id.photo_view);
        if (ViewUtils.isVisible(photoViewCompat) && ViewUtils.isVisible(this.mPreview) && photoViewCompat.getBitmap() != null) {
            this.mPreview.setVisibility(8);
        }
    }

    public void onEnterTransitionEnd() {
        if (this.mEnterTransitionInfo != null) {
            ViewUtils.setVisibleOrGone(this.mPreview, false);
            this.mEnterTransitionInfo = null;
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 20001) {
            return false;
        }
        if (((Boolean) eventMessage.obj).booleanValue()) {
            return true;
        }
        ThreadUtil.runOnUiThread(new C0516b(this, 3));
        return true;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        if (viewerObjectComposite.getModel().getBitmap() != null && this.mPreview.getVisibility() == 0) {
            Log.d(this.TAG, "Preview gone by onPageSelected");
            ViewUtils.setVisibleOrGone(this.mPreview, false);
        }
    }

    public void onPrepareSharedTransitionV2() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            SharedTransition.TransitionListener transitionListener = (SharedTransition.TransitionListener) this.mView;
            SharedTransition.safePostPoneEnterTransition(blackboard, transitionListener);
            SharedTransition.onPrepare(new TransitionData.Builder().setBlackboard(blackboard).setListener(transitionListener).setEnterTransitionId(getEnterSharedTransitionResourceId()).setReturnTransitionId(getReturnSharedTransitionResourceId()).build());
            prepareReturnTransition();
        }
    }

    public void onReturnTransitionEnd() {
        ViewUtils.setVisibleOrGone(this.mPreview, false);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.START_SELECTION_VIEW, new C0515a(this, 0));
        actionInvoker.add(ViewerAction.REMASTER_VIEW_SELECTED, new C0515a(this, 1));
        actionInvoker.add(ViewerAction.START_GROUP_ITEM_PANEL_VIEW, new C0515a(this, 2));
        actionInvoker.add(ViewerAction.PREPARE_RETURN_TRANSITION, new C0515a(this, 3));
        actionInvoker.add(ViewerAction.PREVIEW_LOADED, new C0515a(this, 4));
        actionInvoker.add(ViewerAction.BITMAP_LOADED, new C0515a(this, 4));
    }

    public void startPostponedEnterTransition() {
        if (this.mPreview.getWidth() > 0) {
            startTransition(this.mBlackboard, (SharedTransition.TransitionListener) this.mView);
        } else {
            this.mPreview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                    if (i13 - i11 == 0 && i8 - i2 != 0) {
                        TransitionDelegate.this.mPreview.removeOnLayoutChangeListener(this);
                        TransitionDelegate transitionDelegate = TransitionDelegate.this;
                        transitionDelegate.startTransition(transitionDelegate.mBlackboard, (SharedTransition.TransitionListener) TransitionDelegate.this.mView);
                    }
                }
            });
        }
    }

    public void updateItemSizeInfo(MediaItem mediaItem) {
        if (!mediaItem.isBroken() && mediaItem.isImage() && !mediaItem.isMtp()) {
            if (mediaItem.getWidth() <= 0 || mediaItem.getHeight() <= 0) {
                long currentTimeMillis = System.currentTimeMillis();
                BitmapOptions parse = BitmapOptionsFactory.parse(mediaItem.getPath(), true);
                if (parse.outWidth > 0 && parse.outHeight > 0) {
                    String str = this.TAG;
                    Log.e(str, "updateItemSizeInfo" + MediaItemUtil.getMediaLog(mediaItem) + Logger.vt(Integer.valueOf(parse.outWidth), Integer.valueOf(parse.outHeight), Long.valueOf(currentTimeMillis)));
                    mediaItem.setSize(parse.outWidth, parse.outHeight);
                    if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                        FilesApi.updateMediaAsync(ContentUri.getSecUri(mediaItem), parse.outWidth, parse.outHeight);
                    }
                }
            }
        }
    }

    public boolean startPreview(TransitionInfo transitionInfo) {
        if (transitionInfo == null || transitionInfo.item == null) {
            Log.st(this.TAG, "no transition info");
            return false;
        }
        SharedTransition.setTransitionName(this.mPreview, TextUtils.isEmpty(transitionInfo.name) ? SharedTransition.getTransitionName(transitionInfo.item) : transitionInfo.name);
        updateItemSizeInfo(transitionInfo.item);
        this.mPreview.setScaledTransitionInfo(transitionInfo.scale, transitionInfo.point);
        this.mPreview.setBasicInfo(transitionInfo.bitmap, transitionInfo.item, getPhotoPreViewMargin());
        this.mPreview.setLogTag(transitionInfo.dataPosition);
        ((ContainerModel) this.mModel).setLastPreviewData(new LastPreviewData(transitionInfo.bitmap, transitionInfo.item, transitionInfo.dataPosition), "tr");
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.CF_PV);
        transitionInfo.recycle();
        startPostponedEnterTransition();
        return true;
    }

    private void prepareReturnTransition() {
        String transitionInfoName = SharedTransition.getTransitionInfoName(this.mBlackboard);
        if ("remaster/".equals(transitionInfoName)) {
            prepareRemasterReturnTransition();
        } else if ("groupPanel/".equals(transitionInfoName)) {
            prepareGroupPanelReturnTransition();
        } else if ("burstShotSelection/".equals(transitionInfoName)) {
            prepareBurstShotReturnTransition();
        }
    }
}
