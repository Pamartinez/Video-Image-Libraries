package com.samsung.android.gallery.widget.filmstrip3;

import A2.d;
import A9.b;
import B2.i;
import Bb.e;
import Bb.f;
import Bb.g;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.VideoThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.observable.ObservableArrayList;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.SquareImageView;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.databinding.Filmstrip3VideoItemLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripVideoViewHolder extends FilmStripViewHolder<Filmstrip3VideoItemLayoutBinding> {
    private ObservableArrayList<Bitmap> mBitmapList;
    private final Context mContext;
    private final ArrayList<SquareImageView> mFrameView = new ArrayList<>();
    private boolean mIsAnimating = false;
    private VideoSearchView mSearchView;
    private final Runnable mTouchCallback = new f(this, 0);
    private final ViewHolderAnimation mViewHolderAnimation;

    public FilmStripVideoViewHolder(Filmstrip3VideoItemLayoutBinding filmstrip3VideoItemLayoutBinding) {
        super(filmstrip3VideoItemLayoutBinding);
        this.mContext = filmstrip3VideoItemLayoutBinding.getRoot().getContext();
        this.mViewHolderAnimation = new ViewHolderAnimation(filmstrip3VideoItemLayoutBinding.getRoot());
        this.itemView.setOnTouchListener(new i(2, this));
    }

    private void bindSearchView(MediaItem mediaItem) {
        if (VideoPropData.of(mediaItem).videoFrameIds != null) {
            ThreadUtil.postOnBgThread(new d(21, this, mediaItem));
        }
    }

    private void clearBitmapList(ObservableArrayList<Bitmap> observableArrayList) {
        if (observableArrayList != null) {
            observableArrayList.removeOnListChangedCallback();
            observableArrayList.clear();
        }
    }

    private void clearFrameView() {
        ViewUtils.removeAllViews(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripFrame);
        this.mFrameView.clear();
        clearBitmapList(this.mBitmapList);
    }

    private void clearSearchView() {
        VideoSearchView videoSearchView = this.mSearchView;
        if (videoSearchView != null) {
            videoSearchView.clear();
            ViewUtils.setVisibility(this.mSearchView, 8);
        }
    }

    private ObservableArrayList<Bitmap> createBitmapList(int i2) {
        Bitmap[] bitmapArr = new Bitmap[i2];
        Arrays.fill(bitmapArr, this.mBitmap);
        ObservableArrayList<Bitmap> observableArrayList = new ObservableArrayList<>(bitmapArr);
        observableArrayList.setOnListChangedCallback(new e(this, 2));
        return observableArrayList;
    }

    private AttributeSet getAttributeSet() {
        XmlResourceParser layout = this.mContext.getResources().getLayout(R$layout.filmstrip3_frame_item_layout);
        try {
            layout.next();
            layout.nextTag();
        } catch (Exception unused) {
            Log.e(this.TAG, "Xml parsing exception");
        }
        return Xml.asAttributeSet(layout);
    }

    private int getVideoSeekerMaxFrames() {
        int i2;
        MediaItem mediaItem;
        if (PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER && (mediaItem = this.mMediaItem) != null && mediaItem.isMotionPhoto()) {
            return 4;
        }
        MediaItem mediaItem2 = this.mMediaItem;
        if (mediaItem2 != null) {
            i2 = mediaItem2.getFileDuration() / 1000;
        } else {
            i2 = 1;
        }
        return Math.max(3, (int) (Math.log10((double) i2) * 7.0d));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSearchView$2(ArrayList arrayList, MediaItem mediaItem) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (this.mSearchView == null) {
                VideoSearchView videoSearchView = (VideoSearchView) ((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).searchViewStub.inflate();
                this.mSearchView = videoSearchView;
                setViewShape(videoSearchView, this.mContext.getResources().getDimension(R$dimen.film_strip_search_view_corner_radius));
            }
            this.mSearchView.setSegmentInfo((long) mediaItem.getFileDuration(), arrayList);
            ViewUtils.setVisibility(this.mSearchView, 0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSearchView$3(MediaItem mediaItem) {
        ThreadUtil.postOnUiThread(new b(this, VideoPropData.of(mediaItem).loadSegmentInfoList(), mediaItem, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBitmapList$5(int i2, ObservableList observableList) {
        if (this.mFrameView.size() > i2) {
            this.mFrameView.get(i2).setImageBitmap((Bitmap) observableList.get(i2));
            lambda$tryViewMatrix$4(this.mFrameView.get(i2), this.mMediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBitmapList$6(ObservableList observableList, int i2, int i7) {
        for (int i8 = i2; i8 < i2 + i7; i8++) {
            ThreadUtil.postOnUiThread(new Ab.b((Object) this, i8, (Object) observableList, 5));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$expandSeekerMode$1() {
        this.mIsAnimating = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFrameBitmap$7() {
        if (this.mMediaItem != null) {
            int i2 = 2;
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) && !SdkConfig.atLeast(SdkConfig.GED.T) && (!SdkConfig.atLeast(SdkConfig.SEM.R_MR1) || MediaItemUtil.isHdr10OrHlgVideo(this.mMediaItem))) {
                i2 = 0;
            }
            new VideoThumbnailLoader(true, i2).load(this.mMediaItem, this.mBitmapList, ThumbKind.MEDIUM_KIND.size());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.mMediaItem != null && this.mPosition >= 0) {
            String str = this.TAG;
            Log.majorEvent(str, "onViewTouch#EXPAND " + this.mPosition);
            Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
            while (it.hasNext()) {
                it.next().onRequestExpandSeeker(this.mPosition, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreFrameView$4() {
        ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripFrame, 8);
        this.mIsAnimating = false;
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemRestored(this.mPosition, this);
        }
    }

    private void loadFrameBitmap() {
        this.mBitmapList = createBitmapList(this.mFrameView.size());
        ThreadUtil.postOnBgThread(new f(this, 1));
    }

    /* access modifiers changed from: private */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onViewTouch(motionEvent);
    }

    private boolean onViewTouch(MotionEvent motionEvent) {
        if (!supportSeekerExpand() || this.mFilmStripView.getCenterViewHolder() != this || motionEvent.getAction() != 0) {
            return false;
        }
        Log.d(this.TAG, "onViewTouch#DOWN");
        this.itemView.removeCallbacks(this.mTouchCallback);
        this.itemView.postDelayed(this.mTouchCallback, 180);
        return true;
    }

    private void setFrameView(int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            SquareImageView squareImageView = new SquareImageView(this.mContext, getAttributeSet());
            setDefaultImage(squareImageView);
            squareImageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            squareImageView.addOnLayoutChangeListener(new g(0, this));
            this.mFrameView.add(squareImageView);
            ((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripFrame.addView(squareImageView);
        }
    }

    public void bindView(MediaItem mediaItem, int i2) {
        super.bindView(mediaItem, i2);
        setDefaultImage(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripViewImage);
    }

    public int expandFrameView(int i2) {
        if (this.mFrameView.size() != i2) {
            clearFrameView();
            setFrameView(i2);
            loadFrameBitmap();
        }
        ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripFrame, 0);
        ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripViewImage, 8);
        if (this.mFrameView.isEmpty()) {
            return getDefaultWidth();
        }
        return this.mContext.getResources().getDimensionPixelSize(R$dimen.film_strip_item_gap) + (this.mContext.getResources().getDimensionPixelSize(R$dimen.film_strip_video_frame_width) * this.mFrameView.size());
    }

    public void expandSeekerMode(float f) {
        super.expandSeekerMode(f);
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        int expandFrameView = expandFrameView(getVideoSeekerMaxFrames());
        this.mIsAnimating = true;
        this.mViewHolderAnimation.toSeeker((FilmStripView) this.itemView.getParent(), expandFrameView, f, new e(this, 1));
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemExpanded(this.mPosition, this);
        }
    }

    public int getDefaultWidthDimenId() {
        return R$dimen.film_strip_video_width;
    }

    public int getMaxWidthDimenId() {
        return R$dimen.film_strip_focused_video_width;
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public boolean isExpanded() {
        return ViewUtils.isVisible(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripFrame);
    }

    public void onClick(View view) {
        onExpandableItemClick(view);
    }

    public void onFocusOut(RecyclerView recyclerView) {
        super.onFocusOut(recyclerView);
        this.itemView.removeCallbacks(this.mTouchCallback);
        if (!isExpanded()) {
            ViewUtils.setVisibleOrGone(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).videoPlayIcon, supportVideoIcon());
        }
        clearSearchView();
    }

    public void onFocused(RecyclerView recyclerView) {
        super.onFocused(recyclerView);
        if (PreferenceFeatures.VideoPlayerFeature.isSupportFilmSeeker()) {
            ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).videoPlayIcon, 8);
        }
        if (PreferenceFeatures.OneUi8x.VIDEO_SEARCH) {
            bindSearchView(this.mMediaItem);
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        restoreView();
    }

    public void onViewDetached() {
        super.onViewDetached();
        restoreView();
    }

    public void onViewRecycled() {
        this.itemView.removeCallbacks(this.mTouchCallback);
        super.onViewRecycled();
        restoreView();
        ((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageDrawable((Drawable) null);
        ((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageMatrix((Matrix) null);
        clearSearchView();
    }

    public void restoreFrameView(RecyclerView recyclerView, boolean z) {
        SimpleAnimator.setVisibilityAni(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripViewImage, 0);
        this.mIsAnimating = true;
        this.mViewHolderAnimation.restoreVideo(recyclerView, z, new e(this, 0));
        if (!this.mFocused) {
            ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).videoPlayIcon, 0);
        }
    }

    public void restoreView() {
        clearFrameView();
        ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripViewImage, 0);
        ViewUtils.setVisibility(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).filmStripFrame, 8);
        ViewUtils.setVisibleOrGone(((Filmstrip3VideoItemLayoutBinding) this.mViewBinding).videoPlayIcon, supportVideoIcon());
        ViewUtils.setWidth(this.itemView, getDefaultWidth());
        this.mFocused = false;
        this.mIsAnimating = false;
    }

    public void setHeight(int i2) {
        if (isExpanded()) {
            this.mViewHolderAnimation.setHeight((RecyclerView) this.itemView.getParent(), i2);
        } else {
            super.setHeight(i2);
        }
    }

    public boolean supportSeeker() {
        if (!PreferenceFeatures.VideoPlayerFeature.isSupportFilmSeeker() || !MediaItemUtil.supportPreviewPlay(this.mMediaItem)) {
            return false;
        }
        return true;
    }

    public boolean supportVideoIcon() {
        return true;
    }

    public boolean supportViewHolderSeek() {
        return supportSeeker();
    }
}
