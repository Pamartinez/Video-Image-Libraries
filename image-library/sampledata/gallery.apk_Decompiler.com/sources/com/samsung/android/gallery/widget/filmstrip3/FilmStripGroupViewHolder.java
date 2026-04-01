package com.samsung.android.gallery.widget.filmstrip3;

import A4.C0367b;
import Bb.a;
import Bb.b;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.observable.ObservableArrayList;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.SquareImageView;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.databinding.Filmstrip3GroupImageItemLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripGroupViewHolder extends FilmStripViewHolder<Filmstrip3GroupImageItemLayoutBinding> {
    private ObservableArrayList<Bitmap> mBitmapList;
    private final Context mContext;
    private final ArrayList<SquareImageView> mFrameView = new ArrayList<>();
    private boolean mIsAnimating = false;
    private final ViewHolderAnimation mViewHolderAnimation;

    public FilmStripGroupViewHolder(Filmstrip3GroupImageItemLayoutBinding filmstrip3GroupImageItemLayoutBinding) {
        super(filmstrip3GroupImageItemLayoutBinding);
        this.mContext = filmstrip3GroupImageItemLayoutBinding.getRoot().getContext();
        this.mViewHolderAnimation = new ViewHolderAnimation(filmstrip3GroupImageItemLayoutBinding.getRoot());
    }

    private void clearBitmapList(ObservableArrayList<Bitmap> observableArrayList) {
        if (observableArrayList != null) {
            observableArrayList.removeOnListChangedCallback();
            observableArrayList.clear();
        }
    }

    private void clearFrameView() {
        ((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripFrame.removeAllViews();
        this.mFrameView.clear();
        clearBitmapList(this.mBitmapList);
    }

    private ObservableArrayList<Bitmap> createBitmapList(int i2) {
        Bitmap[] bitmapArr = new Bitmap[i2];
        Arrays.fill(bitmapArr, this.mBitmap);
        ObservableArrayList<Bitmap> observableArrayList = new ObservableArrayList<>(bitmapArr);
        observableArrayList.setOnListChangedCallback(new a(this, 2));
        return observableArrayList;
    }

    private int expandFrameView(int i2) {
        if (this.mFrameView.size() != i2) {
            clearFrameView();
            setFrameView(i2);
            loadFrameBitmap();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new b(this, 0), 500);
        }
        ViewUtils.setVisibility(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripFrame, 0);
        ViewUtils.setVisibility(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripViewImage, 8);
        ViewUtils.setVisibleOrGone(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).videoPlayIcon, false);
        if (this.mFrameView.size() <= 0) {
            return getDefaultWidth();
        }
        return this.mContext.getResources().getDimensionPixelSize(R$dimen.film_strip_item_gap) + (this.mContext.getResources().getDimensionPixelSize(R$dimen.film_strip_video_frame_width) * this.mFrameView.size());
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

    public static Bitmap getCropBitmap(ThumbnailInterface thumbnailInterface, int i2, Bitmap bitmap, int i7) {
        Rect rect;
        RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
        boolean z = true;
        if (RectUtils.isValidRect(cropRectRatio)) {
            rect = RectUtils.getSmartCropRect(bitmap, cropRectRatio, i7, true);
        } else {
            rect = null;
        }
        if (thumbnailInterface.isPeople() || thumbnailInterface.isPanoramic() || thumbnailInterface.isCustomCover()) {
            z = false;
        }
        return new BitmapOperator(bitmap).resize(i2).crop(rect).stretchable(z).rotate(i7).apply();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBitmapList$1(ObservableList observableList, int i2) {
        SquareImageView squareImageView;
        if (observableList.size() > i2) {
            if (i2 < this.mFrameView.size()) {
                squareImageView = this.mFrameView.get(i2);
            } else {
                squareImageView = null;
            }
            Bitmap bitmap = (Bitmap) observableList.get(i2);
            if (squareImageView != null && bitmap != null) {
                squareImageView.setImageBitmap(bitmap);
                setViewMatrixMotionPhoto(squareImageView, this.mMediaItem);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBitmapList$2(ObservableList observableList, int i2, int i7) {
        for (int i8 = i2; i8 < i2 + i7; i8++) {
            ThreadUtil.postOnUiThread(new Ab.b((Object) this, (Object) observableList, i8, 3));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$expandSeekerMode$0() {
        this.mIsAnimating = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFrameBitmap$3(MediaItem mediaItem) {
        ArrayList<MediaItem> groupShotList = GroupItemLoader.getGroupShotList(mediaItem);
        for (int i2 = 0; i2 < groupShotList.size(); i2++) {
            MediaItem mediaItem2 = groupShotList.get(i2);
            if (mediaItem2 != null) {
                this.mBitmapList.set(i2, getCropBitmap(mediaItem2, 120, ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem2, ThumbKind.MEDIUM_KIND), mediaItem2.getOrientation()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFrameBitmap$4() {
        Optional.ofNullable(this.mMediaItem).ifPresent(new C0367b(13, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreFrameView$5() {
        ViewUtils.setVisibility(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripFrame, 8);
        ViewUtils.setVisibleOrGone(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).videoPlayIcon, true);
        this.mIsAnimating = false;
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemRestored(this.mPosition, this);
        }
    }

    private void loadFrameBitmap() {
        this.mBitmapList = createBitmapList(this.mFrameView.size());
        SimpleThreadPool.getInstance().execute(new b(this, 1));
    }

    /* access modifiers changed from: private */
    public void postExpandEvent() {
        Blackboard.getInstance(this.itemView.getContext().toString()).postEvent(EventMessage.obtain(3072, this.mMediaItem));
    }

    private void setFrameView(int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            SquareImageView squareImageView = new SquareImageView(this.mContext, getAttributeSet());
            setDefaultImage(squareImageView);
            squareImageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            this.mFrameView.add(squareImageView);
            ((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripFrame.addView(squareImageView);
        }
    }

    public void bindView(MediaItem mediaItem, int i2) {
        super.bindView(mediaItem, i2);
        setDefaultImage(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripViewImage);
        ViewUtils.setVisibleOrGone(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).videoPlayIcon, true);
    }

    public void expandSeekerMode(float f) {
        super.expandSeekerMode(f);
        int expandFrameView = expandFrameView(this.mMediaItem.getCount());
        this.mIsAnimating = true;
        this.mViewHolderAnimation.toSeeker((FilmStripView) this.itemView.getParent(), expandFrameView, f, new a(this, 1));
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemExpanded(this.mPosition, this);
        }
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public boolean isExpanded() {
        return ViewUtils.isVisible(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripFrame);
    }

    public void onClick(View view) {
        boolean z;
        if (isCenter() || !isExpanded()) {
            z = false;
        } else {
            z = true;
        }
        onExpandableItemClick(view);
        if (z) {
            restoreFrameView(this.mFilmStripView);
        }
    }

    public void onFocusOut(RecyclerView recyclerView) {
        super.onFocusOut(recyclerView);
    }

    public void onFocused(RecyclerView recyclerView) {
        super.onFocused(recyclerView);
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
        super.onViewRecycled();
        restoreView();
        ((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageDrawable((Drawable) null);
        ((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageMatrix((Matrix) null);
    }

    public void restoreFrameView(RecyclerView recyclerView, boolean z) {
        SimpleAnimator.setVisibilityAni(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripViewImage, 0);
        this.mIsAnimating = true;
        this.mViewHolderAnimation.restorePhoto(recyclerView, z, new a(this, 0));
    }

    public void restoreView() {
        clearFrameView();
        ViewUtils.setVisibility(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripViewImage, 0);
        ViewUtils.setVisibility(((Filmstrip3GroupImageItemLayoutBinding) this.mViewBinding).filmStripFrame, 8);
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

    public void setViewMatrixMotionPhoto(SquareImageView squareImageView, MediaItem mediaItem) {
        Rect rect;
        if (mediaItem != null && squareImageView != null && squareImageView.getDrawable() != null) {
            RectF cropRectRatio = mediaItem.getCropRectRatio();
            boolean z = true;
            if (RectUtils.isValidRect(cropRectRatio)) {
                rect = RectUtils.getSmartCropRect(squareImageView.getDrawable(), cropRectRatio, 0, true);
            } else {
                rect = null;
            }
            if (mediaItem.isPeople() || mediaItem.isPanoramic() || mediaItem.isCustomCover()) {
                z = false;
            }
            squareImageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(squareImageView, z).withCropRect(rect).withOrientation(mediaItem.getOrientation()).withOrientationTag(mediaItem.getOrientationTag())));
        }
    }

    public boolean supportSeeker() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || mediaItem.isCloudOnly() || this.mMediaItem.isBroken() || this.mMediaItem.isSharing()) {
            return false;
        }
        return true;
    }

    public boolean supportSeekerExpand() {
        MediaItem mediaItem;
        if (!PreferenceFeatures.VideoPlayerFeature.isSupportFilmSeeker() || isExpanded() || (mediaItem = this.mMediaItem) == null || mediaItem.isCloudOnly() || this.mMediaItem.isBroken() || TrashData.isTrash(this.mMediaItem) || this.mMediaItem.isSharing()) {
            return false;
        }
        return true;
    }
}
