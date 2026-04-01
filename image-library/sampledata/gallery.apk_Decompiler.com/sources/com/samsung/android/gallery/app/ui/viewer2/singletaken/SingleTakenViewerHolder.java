package com.samsung.android.gallery.app.ui.viewer2.singletaken;

import W7.a;
import W7.b;
import W7.c;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewCompositeFactory;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.databinding.SingleTakenViewerLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakenViewerHolder extends AbsViewerHolder<SingleTakenViewerLayoutBinding> {
    private final ContentViewCompositeFactory mCompositeFactory;
    private ViewerObjectComposite mCurrentComposite = null;
    private SingleTakenSubItemPool mViewerPool;

    public SingleTakenViewerHolder(SingleTakenViewerLayoutBinding singleTakenViewerLayoutBinding, AbsViewerHolder.StateListener stateListener, ContentViewCompositeFactory contentViewCompositeFactory) {
        super(singleTakenViewerLayoutBinding, stateListener);
        this.mCompositeFactory = contentViewCompositeFactory;
    }

    private void attachView(ViewerObjectComposite viewerObjectComposite) {
        ((ViewGroup) this.itemView).addView(viewerObjectComposite.getViewHolder().itemView);
        viewerObjectComposite.attachActionInvoker(this.mActionInvoker);
        viewerObjectComposite.onViewAttached();
        if (this.mModel.isViewConfirmed()) {
            this.mThread.runOnUiThread(new b(this, 2), 100);
        }
    }

    private ViewerObjectComposite createContentViewerHolder(MediaItem mediaItem) {
        ViewerObjectComposite viewerObjectComposite = this.mViewerPool.get(mediaItem);
        MediaItem representativeItem = viewerObjectComposite.getModel().getRepresentativeItem();
        if (!MediaItemUtil.equals(representativeItem, mediaItem)) {
            if (representativeItem != null) {
                detachView(viewerObjectComposite);
                viewerObjectComposite = this.mViewerPool.removeAndGetNewComposite(mediaItem);
            }
            int poolSize = this.mViewerPool.getPoolSize() + (this.mModel.getPosition() * 1000);
            viewerObjectComposite.getModel().setGroupPanelViewer(true);
            viewerObjectComposite.onBind(mediaItem, poolSize);
        }
        return viewerObjectComposite;
    }

    private void detachView(ViewerObjectComposite viewerObjectComposite) {
        if (viewerObjectComposite != null) {
            ViewUtils.removeView((ViewGroup) this.itemView, viewerObjectComposite.getViewHolder().itemView);
            detachCurrentViewer();
            viewerObjectComposite.detachActionInvoker();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$attachView$5() {
        pageSelectCurrentViewer();
        confirmCurrentViewer();
        this.mActionInvoker.invoke(ViewerAction.CURRENT_ITEM_CHANGED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBottomSheetSlide$7() {
        ViewUtils.setVisibility(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageLoaded$0() {
        ViewUtils.setVisibility(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTransitionEnd$6() {
        ViewUtils.setVisibility(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStarted$1() {
        ViewUtils.setVisibility(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$replaceComposite$4(MediaItem mediaItem) {
        this.mViewerPool.remove(mediaItem);
    }

    /* access modifiers changed from: private */
    public void onBottomSheetSlide(Object... objArr) {
        if (((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview.getVisibility() == 0) {
            this.mThread.runOnUiThread(new b(this, 1));
        }
    }

    /* access modifiers changed from: private */
    public void onImageLoaded(Object... objArr) {
        this.mThread.runOnUiThread(new b(this, 3));
    }

    private void onItemChanged(Object... objArr) {
        MediaItem mediaItem;
        if (setSubItemIndex(objArr[0]) && (mediaItem = this.mModel.getMediaItem()) != null) {
            this.mThread.runOnUiThread(new c(this, mediaItem, 0));
        }
    }

    /* access modifiers changed from: private */
    public void onPreviewLoaded(Object... objArr) {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null && viewerObjectComposite.getModel().isBitmapLoaded()) {
            Log.d(this.TAG, "onPreviewLoaded skip. bitmap of current view is already loaded");
        } else if (this.mModel.getStateHelper().isQuickViewShrink()) {
            Log.d(this.TAG, "onPreviewLoaded skip. quick view shrink mode");
        } else {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onPreviewLoaded " + this.mModel.getPosition());
            MediaItem mediaItem = objArr[1];
            if (mediaItem != null && mediaItem.isVideo()) {
                ((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview.setMaxScale(10.0f);
            }
            setPhotoViewBmp(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, objArr[0], objArr[1]);
        }
    }

    /* access modifiers changed from: private */
    public void onSubItemsUpdated(Object... objArr) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            if (mediaItem.isSingleTakenPostProcessing()) {
                BlackboardUtils.forceRefreshPicturesData(this.mModel.getBlackboard(), false);
            }
            this.mThread.runOnUiThread(new c(this, mediaItem, 1));
        }
    }

    /* access modifiers changed from: private */
    public void onTransitionEnd(Object... objArr) {
        if (this.mCurrentComposite != null) {
            this.mThread.runOnUiThread(new b(this, 4), 50);
        }
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        this.mThread.runOnUiThread(new b(this, 0));
    }

    /* access modifiers changed from: private */
    /* renamed from: replaceComposite */
    public void lambda$onSubItemsUpdated$3(MediaItem mediaItem) {
        ViewUtils.setVisibility(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, 8);
        ViewerObjectComposite createContentViewerHolder = createContentViewerHolder(mediaItem);
        if (createContentViewerHolder == this.mCurrentComposite) {
            Log.d(this.TAG, "same composite, replace composite skip");
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "replaceComposite " + this.mCurrentComposite + " > " + createContentViewerHolder);
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            detachView(viewerObjectComposite);
            Optional.ofNullable(this.mCurrentComposite.getModel().getRepresentativeItem()).ifPresent(new U9.b(10, this));
            this.mCurrentComposite.onViewRecycled();
            this.mCurrentComposite.onFinalized();
        }
        this.mCurrentComposite = createContentViewerHolder;
        updateCurrentCompositeModel();
        attachView(this.mCurrentComposite);
        this.mActionInvoker.invoke(ViewerAction.ON_REPLACED_COMPOSITE, new Object[0]);
    }

    private boolean setSubItemIndex(Object obj) {
        int intValue = ((Integer) obj).intValue();
        if (intValue >= this.mModel.getSubMediaItemCount()) {
            StringBuilder o2 = C0086a.o(intValue, "item index out of bound", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            o2.append(this.mModel.getSubMediaItemCount());
            new InternalException(o2.toString()).post();
            intValue = this.mModel.getSubMediaItemCount() - 1;
        }
        if (intValue == this.mModel.getSubItemCurrentIndex()) {
            return false;
        }
        this.mModel.setSubItemCurrentIndex(intValue);
        return true;
    }

    private void updateCurrentCompositeModel() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            this.mModel.setChildModel(viewerObjectComposite.getModel());
            this.mCurrentComposite.getModel().setParentModel(this.mModel);
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.PREVIEW_LOADED, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.GROUP_SUB_ITEMS_UPDATED, new a(this, 1));
        this.mActionInvoker.add(ViewerAction.ENTER_TRANSITION_END, new a(this, 2));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new a(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new a(this, 4));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new a(this, 5));
    }

    public void confirmCurrentViewer() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.getModel().setViewConfirmed(true);
            this.mCurrentComposite.onViewConfirm();
        }
    }

    public void detachCurrentViewer() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.getModel().setViewConfirmed(false);
            this.mCurrentComposite.onViewDetached();
        }
    }

    public View getTransitionView() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            return viewerObjectComposite.getViewHolder().getTransitionView();
        }
        return super.getTransitionView();
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what == 3053) {
            onItemChanged(Integer.valueOf(eventMessage.arg1));
            return true;
        }
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            return viewerObjectComposite.handleBlackboardEvent(eventMessage);
        }
        return false;
    }

    public void initialize() {
        this.mModel.setGroupPanelViewer(true);
        ((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview.setLogTag("STP");
        this.mActionInvoker.invoke(ViewerAction.VIEWER_LAYOUT, ((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerLayout);
        this.mActionInvoker.invoke(ViewerAction.IMAGE_PHOTO_VIEW, ((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview);
        this.mActionInvoker.invoke(ViewerAction.CONTENT_CONTAINER, ((SingleTakenViewerLayoutBinding) this.mViewBinding).contentContainer);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (this.mCurrentComposite != null) {
            MediaItem mediaItem3 = this.mModel.getMediaItem();
            if (mediaItem3 == null || mediaItem3.getFileId() != mediaItem.getFileId()) {
                Log.d(this.TAG, "singleTake subItem updated : skip invalidate and load subItems");
            } else {
                this.mCurrentComposite.invalidate(mediaItem, i2, mediaItem2, i7);
            }
        }
    }

    public void onApplyWindowInsets() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onApplyWindowInsets();
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mViewerPool = new SingleTakenSubItemPool(this.mModel, (ViewGroup) this.itemView, this.mCompositeFactory);
        ViewUtils.setVisibility(((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview, 0);
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onConfigurationChanged(configuration);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onMultiWindowModeChanged(z);
        }
    }

    public void onPageSelected() {
        pageSelectCurrentViewer();
    }

    public void onPause() {
        super.onPause();
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onPause();
        }
    }

    public void onResume() {
        super.onResume();
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onResume();
        }
    }

    public void onStart() {
        super.onStart();
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onStop();
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onTableModeChanged(z, i2);
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onViewAttached();
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.attachActionInvoker(this.mActionInvoker);
            confirmCurrentViewer();
        }
    }

    public void onViewDetached() {
        detachCurrentViewer();
        super.onViewDetached();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ((SingleTakenViewerLayoutBinding) this.mViewBinding).viewerPreview.clearBitmap();
        detachView(this.mCurrentComposite);
        SingleTakenSubItemPool singleTakenSubItemPool = this.mViewerPool;
        if (singleTakenSubItemPool != null) {
            singleTakenSubItemPool.recycle();
        }
        this.mCurrentComposite = null;
    }

    public void pageSelectCurrentViewer() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentComposite;
        if (viewerObjectComposite != null) {
            viewerObjectComposite.onPageSelected();
        }
    }
}
