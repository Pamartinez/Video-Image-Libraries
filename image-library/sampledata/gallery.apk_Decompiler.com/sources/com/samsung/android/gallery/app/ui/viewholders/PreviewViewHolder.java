package com.samsung.android.gallery.app.ui.viewholders;

import A.a;
import A4.b0;
import U5.c;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.previewable.Previewable;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreviewViewHolder extends ImageViewHolder {
    private OnCompletionListener mCompletionListener;
    private int mFilterLevel;
    private String mFilterPath;
    protected boolean mIsPreviewing;
    /* access modifiers changed from: private */
    public boolean mMuteAudio = true;
    protected Previewable mPreview;
    /* access modifiers changed from: private */
    public boolean mPreviewDone;
    /* access modifiers changed from: private */
    public boolean mPreviewError;
    private PreviewListener mPreviewListener;
    /* access modifiers changed from: protected */
    public View mPreviewView;
    private int mStyleRes = R.style.ThumbnailStyle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCompletionListener {
        void onCompletion(PreviewViewHolder previewViewHolder, boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PreviewListener implements Previewable.PreviewListener {
        private final String HASH_TAG = ("#" + Integer.toHexString(hashCode()));

        public PreviewListener() {
        }

        private boolean hasSameData(Object obj, MediaItem mediaItem) {
            String str;
            if (obj == null) {
                return true;
            }
            if (mediaItem == null) {
                str = null;
            } else {
                str = mediaItem.getPath();
            }
            if (str == null || !str.equals(obj)) {
                return false;
            }
            return true;
        }

        private boolean isAcceptableError(int i2, int i7) {
            if (i2 != 1) {
                return false;
            }
            if (i7 == -19 || i7 == -38) {
                return true;
            }
            return false;
        }

        private void previewFailHandle(Object obj, int i2, int i7) {
            if (!isCriticalError(i2, i7) && PreviewViewHolder.this.isSurfaceUnavailable()) {
                String access$000 = PreviewViewHolder.this.TAG;
                Log.w(access$000, "onPreviewFail " + this.HASH_TAG + " {I," + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + "} " + PreviewViewHolder.this);
            } else if (isAcceptableError(i2, i7) || !hasSameData(obj, PreviewViewHolder.this.getPreviewItem())) {
                String access$200 = PreviewViewHolder.this.TAG;
                Log.w(access$200, "onPreviewFail " + this.HASH_TAG + " {S," + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + "} " + PreviewViewHolder.this);
            } else {
                String access$100 = PreviewViewHolder.this.TAG;
                Log.e(access$100, "onPreviewFail " + this.HASH_TAG + " {E," + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + "} " + PreviewViewHolder.this);
                PreviewViewHolder.this.mPreviewError = true;
            }
            ThreadUtil.postOnUiThread(new b0(PreviewViewHolder.this, 3));
        }

        public boolean isCriticalError(int i2, int i7) {
            if (i2 != 1) {
                return false;
            }
            if (i7 == -1004 || i7 == -1007 || i7 == -1010) {
                return true;
            }
            return false;
        }

        public boolean isMuteAudio() {
            return PreviewViewHolder.this.mMuteAudio;
        }

        public void onPreviewEnd() {
            PreviewViewHolder.this.mPreviewDone = true;
            PreviewViewHolder.this.stopPreview();
        }

        public void onPreviewFail(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
            previewFailHandle(mediaPlayerCompat != null ? mediaPlayerCompat.getTag() : null, i2, i7);
        }

        public void onPreviewReady() {
            PreviewViewHolder previewViewHolder = PreviewViewHolder.this;
            if (previewViewHolder.mPreview != null) {
                int seekTime = previewViewHolder.getSeekTime();
                PreviewViewHolder.this.mPreview.seekTo(seekTime);
                PreviewViewHolder previewViewHolder2 = PreviewViewHolder.this;
                previewViewHolder2.mPreview.setLooping(previewViewHolder2.canLooping(seekTime));
            }
        }

        public void onPreviewStart() {
            PreviewViewHolder previewViewHolder = PreviewViewHolder.this;
            if (previewViewHolder.mPreviewView != null) {
                previewViewHolder.mImageView.setVisibility(4);
                PreviewViewHolder.this.mPreviewView.setVisibility(0);
            }
        }

        public void onPreviewFail(Object obj, int i2, int i7) {
            previewFailHandle(obj, i2, i7);
        }
    }

    public PreviewViewHolder(View view, int i2, boolean z) {
        super(view, i2, z);
    }

    /* access modifiers changed from: private */
    public boolean isSurfaceUnavailable() {
        return !this.itemView.isAttachedToWindow();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hidePreviewView$0(View view) {
        removePreviewView(view);
        this.mImageView.setImageBitmap(getBitmap());
        setViewMatrix();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hidePreviewView$1() {
        if (this.mImageView.getVisibility() != 0) {
            String str = this.TAG;
            Log.d(str, "restore ImageView wrong visibility " + this);
            this.mImageView.setVisibility(0);
        }
        removePreviewView(this.mPreviewView);
        this.mPreviewView = null;
    }

    public void applyFilter(String str, int i2) {
        this.mFilterPath = str;
        this.mFilterLevel = i2;
        Previewable previewable = this.mPreview;
        if (previewable != null) {
            previewable.applyFilter(str, i2);
        }
    }

    public void bindPreviewView(View view) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) this.mImageView.getParent();
        viewGroup2.addView(view, viewGroup2.indexOfChild(this.mImageView));
        if ((view instanceof SurfaceView) && (viewGroup = (ViewGroup) viewGroup2.getParent()) != null) {
            viewGroup.setClipChildren(true);
        }
    }

    public boolean cachePreview(Previewable previewable) {
        if (!previewCacheable()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Bitmap previewBitmap = previewable.getPreviewBitmap();
            Matrix previewMatrix = previewable.getPreviewMatrix();
            if (!(previewBitmap == null || previewBitmap.isRecycled() || previewMatrix == null)) {
                this.mImageView.setImageBitmap(previewBitmap);
                this.mImageView.setImageMatrix(previewMatrix);
                String str = this.TAG;
                Log.d(str, "cachePreview{" + previewBitmap.getWidth() + ',' + previewBitmap.getHeight() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                return true;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("cachePreview fail="), this.TAG);
        }
        return false;
    }

    public boolean canLooping(int i2) {
        if (i2 != getPreviewThumbnailOffsetMs() || getMediaItem().getFileDuration() < i2 || getMediaItem().getFileDuration() >= i2 + 5000) {
            return false;
        }
        return true;
    }

    public Previewable createPreview() {
        return PreviewFactory.createPreview(getPreviewItem(), getPreviewListener());
    }

    public PreviewListener createPreviewListener() {
        return new PreviewListener();
    }

    public int getPreviewDuration() {
        return 5000;
    }

    public MediaItem getPreviewItem() {
        return this.mMediaItem;
    }

    public PreviewListener getPreviewListener() {
        if (this.mPreviewListener == null) {
            this.mPreviewListener = createPreviewListener();
        }
        return this.mPreviewListener;
    }

    public int getPreviewThumbnailOffsetMs() {
        return 15000;
    }

    public int getSeekTime() {
        MediaItem mediaItem = getMediaItem();
        if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL && MediaItemUtil.isHighLightClip(mediaItem)) {
            return (int) (mediaItem.getVideoThumbStartTime() / 1000);
        }
        if (!PreferenceFeatures.VIDEO_THUMBNAIL_WITH_FIRST_FRAME && !MediaItemUtil.isNonMovieClip(mediaItem)) {
            return getPreviewThumbnailOffsetMs();
        }
        return 0;
    }

    public void hidePreviewView(boolean z) {
        boolean z3;
        this.mIsPreviewing = false;
        Previewable previewable = this.mPreview;
        if (previewable != null) {
            if (z) {
                z3 = cachePreview(previewable);
            } else {
                z3 = false;
            }
            this.mPreview.stopPreview();
            this.mPreview = null;
        } else {
            z3 = false;
        }
        this.mImageView.setVisibility(0);
        View view = this.mPreviewView;
        if (view == null) {
            return;
        }
        if (z3) {
            view.setVisibility(4);
            this.mImageView.post(new c(27, this, this.mPreviewView));
            this.mPreviewView = null;
            return;
        }
        ThreadUtil.postOnUiThreadDelayed(new b0(this, 2), 50);
    }

    public boolean isPreviewDone() {
        return this.mPreviewDone;
    }

    public boolean isPreviewError() {
        return this.mPreviewError;
    }

    public boolean isPreviewing() {
        return this.mIsPreviewing;
    }

    public void muteAudio(boolean z) {
        this.mMuteAudio = z;
        Previewable previewable = this.mPreview;
        if (previewable != null) {
            previewable.muteAudio(z);
        }
    }

    public boolean pausePlayer() {
        Previewable previewable = this.mPreview;
        if (previewable == null) {
            return false;
        }
        previewable.pausePlayer();
        return true;
    }

    public void preparePreview(OnCompletionListener onCompletionListener) {
        this.mCompletionListener = onCompletionListener;
        this.mIsPreviewing = true;
    }

    public boolean previewCacheable() {
        return true;
    }

    public void recycle() {
        if (this.mPreviewView != null) {
            this.mPreviewDone = false;
            stopPreview();
        }
        this.mPreviewError = false;
        View scalableView = getScalableView();
        if (scalableView.getVisibility() != 0) {
            scalableView.setVisibility(0);
        }
        super.recycle();
    }

    public void removePreviewView(View view) {
        ViewUtils.removeSelf(view);
    }

    public void resetPreviewError() {
        this.mPreviewError = false;
    }

    public boolean resumePlayer() {
        Previewable previewable = this.mPreview;
        if (previewable == null) {
            return false;
        }
        previewable.resumePlayer();
        return true;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        Previewable previewable = this.mPreview;
        if (previewable != null) {
            previewable.setColorFilter(colorFilter);
        }
    }

    public void startPreview() {
        Previewable previewable;
        View view = this.mPreviewView;
        if (view != null) {
            removePreviewView(view);
            this.mPreviewView = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean isBackgroundPlaying = RemoteDisplayState.getInstance().isBackgroundPlaying();
        if (isBackgroundPlaying) {
            Log.w(this.TAG, "startPreview will be skipped by remote video +" + (System.currentTimeMillis() - currentTimeMillis));
        }
        if (this.mPreviewError || isBackgroundPlaying) {
            previewable = null;
        } else {
            previewable = createPreview();
        }
        this.mPreview = previewable;
        if (previewable == null) {
            if (this.mPreviewError) {
                Log.e(this.TAG, "startPreview skip for error " + this);
            }
            this.mIsPreviewing = false;
            OnCompletionListener onCompletionListener = this.mCompletionListener;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(this, true);
                this.mCompletionListener = null;
                return;
            }
            return;
        }
        View createPreviewView = previewable.createPreviewView(getContext(), this.mStyleRes);
        this.mPreviewView = createPreviewView;
        int i2 = this.mThumbnailShapeType;
        if (i2 >= 0) {
            ViewUtils.setViewShape(createPreviewView, i2, this.mThumbnailShapeRadius);
        }
        bindPreviewView(this.mPreviewView);
        updatePreviewViewLayout(this.mPreviewView);
        this.mPreview.applyFilter(this.mFilterPath, this.mFilterLevel);
        this.mPreview.setTag(Integer.valueOf(getLayoutPosition()));
        this.mPreview.startPreview();
    }

    public void stopPreview() {
        stopPreview(false);
    }

    public void stopPreviewForever() {
        hidePreviewView(false);
        OnCompletionListener onCompletionListener = this.mCompletionListener;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(this, false);
            this.mCompletionListener = null;
        }
    }

    public void updatePreviewViewLayout(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mImageView.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(marginLayoutParams.getMarginStart(), marginLayoutParams.topMargin, marginLayoutParams.getMarginEnd(), marginLayoutParams.bottomMargin);
        view.setPadding(this.mImageView.getPaddingStart(), this.mImageView.getPaddingTop(), this.mImageView.getPaddingEnd(), this.mImageView.getPaddingBottom());
        setColorFilter(this.mImageView.getColorFilter());
    }

    public void stopPreview(boolean z) {
        hidePreviewView(z);
        OnCompletionListener onCompletionListener = this.mCompletionListener;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(this, true);
            this.mCompletionListener = null;
        }
    }

    public PreviewViewHolder(View view, int i2) {
        super(view, i2);
    }
}
