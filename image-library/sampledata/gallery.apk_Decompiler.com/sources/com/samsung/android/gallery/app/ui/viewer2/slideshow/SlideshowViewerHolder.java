package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import G6.a;
import H7.A;
import X7.b;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.viewer.LastPreviewData;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.databinding.SlideshowViewerLayoutBinding;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowViewerHolder extends AbsViewerHolder<SlideshowViewerLayoutBinding> {
    private IMediaPlayerView mMediaPlayerView;

    public SlideshowViewerHolder(SlideshowViewerLayoutBinding slideshowViewerLayoutBinding, AbsViewerHolder.StateListener stateListener) {
        super(slideshowViewerLayoutBinding, stateListener);
    }

    private String getDefaultContentDescription() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            return "";
        }
        String mimeType = mediaItem.getMimeType();
        Cursor query = DbCompat.query("mp://RelatedScene", new a(mediaItem, 5));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        Log.d(this.TAG, "RELATED_SCENE : " + string);
                        if (!TextUtils.isEmpty(string)) {
                            String translatedString = TranslationManager.getInstance().getTranslatedString("Things Scenery", string);
                            if (translatedString != null) {
                                mimeType = mimeType + translatedString;
                            } else {
                                Log.w(this.TAG, "fail translate : " + string);
                            }
                        }
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return mimeType;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageLoaded$0() {
        this.mModel.getBlackboard().publishIfEmpty("lifecycle://onQuickViewDone", Long.valueOf(System.currentTimeMillis()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageLoaded$1() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            Log.d(this.TAG, "onImageLoaded", Integer.valueOf(mediaItem.getOrientation()), Integer.valueOf(mediaItem.getWidth()), Integer.valueOf(mediaItem.getHeight()));
            ((SlideshowViewerLayoutBinding) this.mViewBinding).photoView.setImage(mediaItem, this.mModel.getBitmap());
            ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.VF_HQ);
            ViewerPerformanceTracker.dump();
            if (this.mModel.getStateHelper().isQuickView() && this.mModel.getPosition() == 0) {
                ((SlideshowViewerLayoutBinding) this.mViewBinding).photoView.post(new b(this, 2));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setContentDescription$3() {
        if (SeApiCompat.isVoiceServiceEnabled(this.itemView.getContext())) {
            this.itemView.setContentDescription(getDefaultContentDescription());
        }
    }

    /* access modifiers changed from: private */
    public void onImageLoaded(Object... objArr) {
        this.mThread.runOnUiThread(new b(this, 1));
    }

    /* access modifiers changed from: private */
    public void onLastPreviewData(Object... objArr) {
        Log.d(this.TAG, "onLastFrame");
        LastPreviewData lastPreviewData = objArr[0];
        setPhotoViewBmp(((SlideshowViewerLayoutBinding) this.mViewBinding).photoView, lastPreviewData.getBitmap(), lastPreviewData.getMediaItem());
    }

    /* access modifiers changed from: private */
    public void onMediaViewStubRequest(Object... objArr) {
        if (this.mMediaPlayerView == null) {
            IMediaPlayerView iMediaPlayerView = (IMediaPlayerView) ((SlideshowViewerLayoutBinding) this.mViewBinding).videoViewStub.inflate();
            this.mMediaPlayerView = iMediaPlayerView;
            iMediaPlayerView.setTouchInteractionViewParent(((SlideshowViewerLayoutBinding) this.mViewBinding).contentContainer.getParent().getParent());
            ((View) this.mMediaPlayerView).setImportantForAccessibility(2);
        }
        this.mActionInvoker.invoke(ViewerAction.MEDIA_VIEW, this.mMediaPlayerView);
    }

    /* access modifiers changed from: private */
    public void onPreviewLoaded(Object... objArr) {
        Log.d(this.TAG, "onPreviewLoaded");
        setPhotoViewBmp(((SlideshowViewerLayoutBinding) this.mViewBinding).photoView, objArr[0], objArr[1]);
    }

    private void setContentDescription() {
        ThreadUtil.postOnBgThread(new b(this, 0));
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW_INFLATE, new X7.a(this, 0));
        this.mActionInvoker.add(ViewerAction.PREVIEW_LOADED, new X7.a(this, 1));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new X7.a(this, 2));
        this.mActionInvoker.add(ViewerAction.LAST_PREVIEW_DATA, new X7.a(this, 3));
    }

    public long getDelayTime() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (this.mModel.getPosition() != 0 || mediaItem == null || !mediaItem.isVideo()) {
            return super.getDelayTime();
        }
        return 200;
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.IMAGE_PHOTO_VIEW, ((SlideshowViewerLayoutBinding) this.mViewBinding).photoView);
    }

    public boolean isNeedToConfirm() {
        if (!super.isNeedToConfirm() || this.itemView.getAlpha() != 1.0f) {
            return false;
        }
        return true;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        setContentDescription();
    }

    public void onInitialized() {
        ((SlideshowViewerLayoutBinding) this.mViewBinding).viewerLayout.setOnClickListener(new A(27, this));
    }

    public void onViewDetached() {
        super.onViewDetached();
        ((SlideshowViewerLayoutBinding) this.mViewBinding).photoView.resetScaleAndCenter();
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setDefaultPosition(false);
            this.mMediaPlayerView.setVisibility(8);
        }
        ((SlideshowViewerLayoutBinding) this.mViewBinding).photoView.setVisibility(0);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ((SlideshowViewerLayoutBinding) this.mViewBinding).photoView.clearBitmap();
    }

    public void toggleOsd(View view) {
        this.mModel.getBlackboard().postEvent(EventMessage.obtain(3200));
    }
}
