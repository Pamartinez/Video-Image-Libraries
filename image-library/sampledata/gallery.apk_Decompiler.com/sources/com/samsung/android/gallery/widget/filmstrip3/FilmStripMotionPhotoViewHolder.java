package com.samsung.android.gallery.widget.filmstrip3;

import A4.C0367b;
import Ab.b;
import Bb.c;
import Bb.d;
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
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.thumbnail.VideoThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.observable.ObservableArrayList;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.SquareImageView;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.databinding.Filmstrip3MotionPhotoItemLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripMotionPhotoViewHolder extends FilmStripViewHolder<Filmstrip3MotionPhotoItemLayoutBinding> {
    private ObservableArrayList<Bitmap> mBitmapList;
    private final Context mContext;
    private final ArrayList<SquareImageView> mFrameView = new ArrayList<>();
    private boolean mIsAnimating = false;
    private final ViewHolderAnimation mViewHolderAnimation;

    public FilmStripMotionPhotoViewHolder(Filmstrip3MotionPhotoItemLayoutBinding filmstrip3MotionPhotoItemLayoutBinding) {
        super(filmstrip3MotionPhotoItemLayoutBinding);
        this.mContext = filmstrip3MotionPhotoItemLayoutBinding.getRoot().getContext();
        this.mViewHolderAnimation = new ViewHolderAnimation(filmstrip3MotionPhotoItemLayoutBinding.getRoot());
    }

    private void clearBitmapList(ObservableArrayList<Bitmap> observableArrayList) {
        if (observableArrayList != null) {
            observableArrayList.removeOnListChangedCallback();
            observableArrayList.clear();
        }
    }

    private void clearFrameView() {
        ViewUtils.removeAllViews(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripFrame);
        this.mFrameView.clear();
        clearBitmapList(this.mBitmapList);
    }

    private ObservableArrayList<Bitmap> createBitmapList(int i2) {
        Bitmap[] bitmapArr = new Bitmap[i2];
        Arrays.fill(bitmapArr, this.mBitmap);
        ObservableArrayList<Bitmap> observableArrayList = new ObservableArrayList<>(bitmapArr);
        observableArrayList.setOnListChangedCallback(new c(this, 2));
        return observableArrayList;
    }

    private int expandFrameView(int i2) {
        if (this.mFrameView.size() != i2) {
            clearFrameView();
            setFrameView(i2);
            loadFrameBitmap();
        }
        ViewUtils.setVisibility(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripFrame, 0);
        ViewUtils.setVisibility(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripViewImage, 8);
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

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createBitmapList$3(ObservableList observableList, int i2) {
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
    public /* synthetic */ void lambda$createBitmapList$4(ObservableList observableList, int i2, int i7) {
        for (int i8 = i2; i8 < i2 + i7; i8++) {
            ThreadUtil.postOnUiThread(new b((Object) this, (Object) observableList, i8, 4));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$expandSeekerMode$0() {
        this.mIsAnimating = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$foldCenterExpanded$2() {
        closeVideoUI(this.mFilmStripView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFrameBitmap$5(MediaItem mediaItem) {
        new VideoThumbnailLoader(true, 2).load(mediaItem, this.mBitmapList, ThumbKind.MEDIUM_KIND.size());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFrameBitmap$6() {
        Optional.ofNullable(this.mMediaItem).ifPresent(new C0367b(15, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restoreFrameView$1() {
        ViewUtils.setVisibility(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripFrame, 8);
        this.mIsAnimating = false;
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemRestored(this.mPosition, this);
        }
    }

    private void loadFrameBitmap() {
        this.mBitmapList = createBitmapList(this.mFrameView.size());
        ThreadUtil.postOnBgThread(new d(this, 1));
    }

    private void setFrameView(int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            SquareImageView squareImageView = new SquareImageView(this.mContext, getAttributeSet());
            setDefaultImage(squareImageView);
            squareImageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            this.mFrameView.add(squareImageView);
            ((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripFrame.addView(squareImageView);
        }
    }

    public void bindView(MediaItem mediaItem, int i2) {
        super.bindView(mediaItem, i2);
        setDefaultImage(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripViewImage);
    }

    public void closeVideoUI(RecyclerView recyclerView) {
        try {
            Blackboard.getInstance(recyclerView.getContext().toString()).postEvent(EventMessage.obtain(1119));
        } catch (Exception unused) {
        }
    }

    public void expandSeekerMode(float f) {
        super.expandSeekerMode(f);
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        int expandFrameView = expandFrameView(4);
        this.mIsAnimating = true;
        this.mViewHolderAnimation.toSeeker((FilmStripView) this.itemView.getParent(), expandFrameView, f, new c(this, 1));
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemExpanded(this.mPosition, this);
        }
    }

    public void foldCenterExpanded(View view) {
        super.foldCenterExpanded(view);
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            filmStripView.postDelayed(new d(this, 0), 30);
        }
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public boolean isExpanded() {
        return ViewUtils.isVisible(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripFrame);
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
        ((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageDrawable((Drawable) null);
        ((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageMatrix((Matrix) null);
    }

    public void restoreFrameView(RecyclerView recyclerView, boolean z) {
        SimpleAnimator.setVisibilityAni(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripViewImage, 0);
        this.mIsAnimating = true;
        this.mViewHolderAnimation.restorePhoto(recyclerView, z, new c(this, 0));
    }

    public void restoreView() {
        clearFrameView();
        ViewUtils.setVisibility(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripViewImage, 0);
        ViewUtils.setVisibility(((Filmstrip3MotionPhotoItemLayoutBinding) this.mViewBinding).filmStripFrame, 8);
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
            if (mediaItem.isCreature() || mediaItem.isPanoramic() || mediaItem.isCustomCover()) {
                z = false;
            }
            squareImageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(squareImageView, z).withCropRect(rect)));
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
        if (!PreferenceFeatures.VideoPlayerFeature.SUPPORT_GALLERY_PLAYER || isExpanded() || (mediaItem = this.mMediaItem) == null || mediaItem.isCloudOnly() || this.mMediaItem.isBroken() || TrashData.isTrash(this.mMediaItem)) {
            return false;
        }
        if (!this.mMediaItem.isSharing() || MediaItemMde.hasDownloadMotionPhotoPath(this.mMediaItem)) {
            return true;
        }
        return false;
    }
}
