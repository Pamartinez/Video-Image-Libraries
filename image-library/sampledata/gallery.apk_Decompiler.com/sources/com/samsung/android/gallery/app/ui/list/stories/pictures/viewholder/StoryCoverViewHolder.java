package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import A4.C0367b;
import Ab.a;
import Da.f;
import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryCoverViewHolder extends PreviewViewHolder {
    ViewGroup mCoverView;
    ViewGroup mCoverViewLayout;
    private ImageView mCurrentCoverView;
    private boolean mIsCoverRatio = true;
    View mPlayIcon;
    ViewStub mPlayIconStub;
    ViewGroup mSlideParent;
    ViewStub mSlideParentStub;
    private final ArrayList<ImageView> mSlideViews = new ArrayList<>();
    /* access modifiers changed from: private */
    public Animator mTransitAnimator;

    public StoryCoverViewHolder(View view, int i2) {
        super(view, i2);
        bindExtraView();
    }

    private void bindSlideItem(Bitmap bitmap) {
        cancelSlideAnimation();
        boolean needCoverTransition = needCoverTransition();
        ImageView nextSlideshowView = getNextSlideshowView();
        ViewUtils.setVisibility(nextSlideshowView, 0);
        ViewUtils.setAlpha(nextSlideshowView, 1.0f);
        bindSlideItem(nextSlideshowView, this.mMediaItem, bitmap);
        if (needCoverTransition) {
            performTransition(this.mCurrentCoverView, nextSlideshowView);
        } else {
            ViewUtils.post(nextSlideshowView, new f(3, this, nextSlideshowView));
        }
        this.mCurrentCoverView = nextSlideshowView;
    }

    private void cancelSlideAnimation() {
        Animator animator = this.mTransitAnimator;
        if (animator != null) {
            animator.cancel();
            this.mTransitAnimator = null;
        }
    }

    private boolean coverClickable() {
        if (!PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW || supportHighlightVideo()) {
            return true;
        }
        return false;
    }

    private RectF getCropRect(MediaItem mediaItem) {
        if (PreviewFactory.isPreviewableFormat(mediaItem) || !this.mIsCoverRatio) {
            return null;
        }
        return RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSlideItem$1(ImageView imageView) {
        setViewMatrix(this.mMediaItem, imageView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(Object obj) {
        ((View) obj).setOnClickListener(new a(16, this));
    }

    private boolean needCoverTransition() {
        if (this.mCurrentCoverView != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onItemLongClicked(View view) {
        if (this.mOnItemLongClickListener == null || !onItemLongClickInternal(getItemViewType())) {
            return false;
        }
        return true;
    }

    private void performTransition(final ImageView imageView, final ImageView imageView2) {
        AlphaAnimator alphaAnimator = new AlphaAnimator((View) imageView, 1.0f, 0.0f);
        alphaAnimator.setDuration(150);
        alphaAnimator.addListener(new SimpleAnimatorListener() {
            private void finishAnimation() {
                ViewUtils.setVisibility(imageView, 4);
                StoryCoverViewHolder.this.mTransitAnimator = null;
            }

            public void onAnimationCancel(Animator animator) {
                finishAnimation();
            }

            public void onAnimationEnd(Animator animator) {
                imageView2.bringToFront();
                finishAnimation();
            }
        });
        alphaAnimator.start();
        this.mTransitAnimator = alphaAnimator;
    }

    private boolean supportHighlightVideo() {
        if ((!PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW || !Features.isEnabled(Features.SUPPORT_CREATE_MOVIE)) && !Features.isEnabled(Features.SUPPORT_CREATE_MOVIE_V2) && !Features.isEnabled(Features.SUPPORT_CREATE_HIGHLIGHT_REEL)) {
            return false;
        }
        return true;
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        Object obj;
        super.bind(mediaItem);
        this.itemView.setOnLongClickListener(new E6.a(0, this));
        addThumbnailBorder(getContext().getDrawable(R.drawable.story_pictures_cover_thumbnail_border));
        ImageView image = getImage();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TimeUtil.getLocalizedDateTime(mediaItem.getDateTaken()));
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (mediaItem.isImage()) {
            i2 = R.string.image;
        } else {
            i2 = R.string.video;
        }
        sb2.append(getString(i2));
        if (mediaItem.isImage()) {
            obj = "";
        } else {
            obj = Integer.valueOf(VideoPropData.getVideoDuration(mediaItem));
        }
        sb2.append(obj);
        image.setContentDescription(sb2.toString());
    }

    public void bindExtraView() {
        if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
            bindPlayIconView();
            ViewStub viewStub = this.mSlideParentStub;
            if (viewStub != null && this.mSlideParent == null) {
                ViewGroup viewGroup = (ViewGroup) inflateStub(viewStub);
                this.mSlideParent = viewGroup;
                this.mSlideViews.add((ImageView) viewGroup.findViewById(R.id.thumbnail_slide1));
                this.mSlideViews.add((ImageView) this.mSlideParent.findViewById(R.id.thumbnail_slide2));
            }
        }
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
            bindSlideItem(bitmap);
        }
    }

    public final void bindPlayIconView() {
        ViewStub viewStub;
        if (supportHighlightVideo() && (viewStub = this.mPlayIconStub) != null && this.mPlayIcon == null) {
            this.mPlayIcon = inflateStub(viewStub);
        }
    }

    public void bindPreviewView(View view) {
        this.mCoverView.setVisibility(0);
        this.mCoverView.addView(view);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCoverViewLayout = (ViewGroup) view.findViewById(R.id.thumbnail_container);
        this.mCoverView = (ViewGroup) view.findViewById(R.id.thumbnail_preview_layout);
        this.mPlayIconStub = (ViewStub) view.findViewById(R.id.play_icon_view_stub);
        this.mSlideParentStub = (ViewStub) view.findViewById(R.id.slide_parent_stub);
        Optional.ofNullable(view.findViewById(R.id.thumbnail)).ifPresent(new C0367b(29, this));
    }

    public boolean canLooping(int i2) {
        return false;
    }

    public View getDecoView(int i2) {
        if (i2 == 58) {
            return this.mCoverViewLayout;
        }
        return super.getDecoView(i2);
    }

    public ImageView getNextSlideshowView() {
        Iterator<ImageView> it = this.mSlideViews.iterator();
        while (it.hasNext()) {
            ImageView next = it.next();
            if (next != this.mCurrentCoverView) {
                return next;
            }
        }
        return null;
    }

    public int getPreviewDuration() {
        int i2;
        MediaItem mediaItem = getMediaItem();
        if (mediaItem != null) {
            i2 = mediaItem.getFileDuration();
        } else {
            i2 = 10000;
        }
        return Math.min(i2, 10000);
    }

    public PreviewViewHolder.PreviewListener getPreviewListener() {
        return new PreviewViewHolder.PreviewListener() {
            public void onPreviewStart() {
                super.onPreviewStart();
                StoryCoverViewHolder.this.mImageView.setVisibility(0);
            }
        };
    }

    public int getPreviewThumbnailOffsetMs() {
        return 0;
    }

    public void onClick(View view) {
        onItemClick(view);
    }

    public final void onItemClick(View view) {
        boolean z;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onItemClick{");
        if (this.mOnItemClickListener != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(coverClickable());
        sb2.append("}");
        Log.d(str, sb2.toString());
        if (this.mOnItemClickListener != null && coverClickable()) {
            this.mOnItemClickListener.onItemClick(this, getViewPosition(), getMediaItem(), -1);
        }
    }

    public void recycle() {
        this.mIsCoverRatio = true;
        ViewUtils.setVisibility(this.mCoverView, 8);
        cancelSlideAnimation();
        super.recycle();
    }

    public void recycleToBackup() {
        super.recycleToBackup();
        replaceWithViewStubToClear(this.mPlayIcon, this.mPlayIconStub);
        replaceWithViewStubToClear(this.mSlideParent, this.mSlideParentStub);
    }

    public void removePreviewView(View view) {
        super.removePreviewView(view);
        ViewUtils.setVisibility(this.mCoverView, 8);
    }

    public void setUseCustomCoverRatio(boolean z) {
        this.mIsCoverRatio = z;
    }

    public void setViewMatrix() {
        setViewMatrix(this.mMediaItem, getImage());
    }

    private void setViewMatrix(MediaItem mediaItem, ImageView imageView) {
        if (mediaItem != null) {
            RectF cropRect = getCropRect(mediaItem);
            ViewMatrixUtils.setViewMatrix(imageView, mediaItem, cropRect, (mediaItem.isVideo() || mediaItem.isBroken()) ? 0 : mediaItem.getOrientation(), RectUtils.isStretchable(cropRect, mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getOrientation()));
        }
    }

    private void bindSlideItem(ImageView imageView, MediaItem mediaItem, Bitmap bitmap) {
        if (imageView != null) {
            ViewUtils.setVisibility(getImage(), 4);
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageDrawable(getBrokenDrawable(getBrokenBitmap(mediaItem), getColorFilter(false)));
            }
            setViewMatrix(mediaItem, imageView);
        }
    }
}
