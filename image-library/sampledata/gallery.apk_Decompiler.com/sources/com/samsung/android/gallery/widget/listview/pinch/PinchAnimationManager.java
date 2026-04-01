package com.samsung.android.gallery.widget.listview.pinch;

import I4.b;
import Ib.a;
import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.AlbumPinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.StoriesPinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchAdapter;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PinchAnimationManager {
    /* access modifiers changed from: protected */
    public final String TAG = getClass().getSimpleName();
    private final ArrayList<Animator> mAnimator = new ArrayList<>();
    private final ConcurrentHashMap<Object, ThumbKeyHolder> mBitmapKeyHolders = new ConcurrentHashMap<>();
    protected int mBottomFakeViewCount = 0;
    protected boolean mFinishingAnimation = false;
    /* access modifiers changed from: protected */
    public final GridInfo mGridInfo;
    private PinchAnimationInterface mInterface;
    protected boolean mIsRtl = false;
    /* access modifiers changed from: protected */
    public PinchLayoutManager mLayoutManager;
    protected AnimPositionCache mPositionCache = new AnimPositionCache();
    /* access modifiers changed from: protected */
    public int mScrollOffset = Integer.MAX_VALUE;
    /* access modifiers changed from: protected */
    public int mScrollPosition = 0;
    protected int mShiftHeaderOffset = 0;
    protected int mShiftOffset = 0;
    protected int mTopFakeViewCount = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ThumbKeyHolder {
        RectF mCropRectRatio;
        boolean mIsBroken;
        String mKey;
        ThumbKind mThumbKind;

        public ThumbKeyHolder(String str, ThumbKind thumbKind, boolean z, RectF rectF) {
            this.mKey = str;
            this.mThumbKind = thumbKind;
            this.mIsBroken = z;
            this.mCropRectRatio = rectF;
        }
    }

    public PinchAnimationManager(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo) {
        this.mLayoutManager = pinchLayoutManager;
        this.mGridInfo = new GridInfo(clusterInfo);
    }

    private void cancelDecodeRequest() {
        if (this.mGridInfo.fromYear()) {
            ThumbnailLoader.getInstance().clearMiniDecodeQueue();
        }
    }

    private Bitmap getBrokenThumbnail(Context context, MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(context, ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    private StaticLayout getStaticLayout(TextView textView, int i2, int i7, TextUtils.TruncateAt truncateAt) {
        int i8;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        int i10 = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        CharSequence text = textView.getText();
        if (i2 <= 0 || (i8 = i2 - i10) <= 0 || text == null || text.length() == 0) {
            return null;
        }
        StaticLayout.Builder includePad = StaticLayout.Builder.obtain(text, 0, text.length(), textView.getPaint(), i8).setIncludePad(textView.getIncludeFontPadding());
        if (i7 > 0) {
            includePad.setMaxLines(i7);
        }
        if (truncateAt != null) {
            includePad.setEllipsize(truncateAt);
        }
        return includePad.build();
    }

    private void restoreBitmaps() {
        for (int i2 = 0; i2 < this.mLayoutManager.getChildCount(); i2++) {
            restoreBitmapFromCache((ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2)));
        }
    }

    public void addAlphaAnimator(View view, float f, float f5, float f8) {
        if (view != null && view.getVisibility() != 8) {
            AlphaAnimator alphaAnimator = new AlphaAnimator(view, f, f5);
            alphaAnimator.setAnimationListener(new a(f8));
            addAnimation((PropertyAnimator) alphaAnimator);
        }
    }

    public void addAnimation(PropertyAnimator propertyAnimator) {
        this.mAnimator.add(propertyAnimator);
    }

    public void addBitmapToCache(ListViewHolder listViewHolder) {
        if (listViewHolder != null && listViewHolder.hasBitmap()) {
            putToBitmapKeyHolder(listViewHolder.getMediaItem(), listViewHolder.getThumbKind());
        }
    }

    public void animationCompletedInternal(boolean z, boolean z3) {
        this.mFinishingAnimation = z;
        if (!z) {
            clearBitmapCache();
            getRecyclerView().addDefaultItemAnimator();
        } else {
            cacheBitmap();
            getRecyclerView().setPreserveFocusAfterLayout(false);
        }
        cancelDecodeRequest();
    }

    public void cacheBitmap() {
        for (int i2 = 0; i2 < this.mLayoutManager.getChildCount(); i2++) {
            addBitmapToCache((ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2)));
        }
        addFakeViewBitmapToCache();
    }

    public void clearAnimators() {
        Iterator<Animator> it = this.mAnimator.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (next instanceof PropertyAnimator) {
                ((PropertyAnimator) next).notifyPropertyAnimationEnd();
            }
        }
        this.mAnimator.clear();
    }

    public void clearBitmapCache() {
        this.mBitmapKeyHolders.clear();
    }

    public PinchFakeLayoutManager createFakeLayoutManager(ViewGroup viewGroup) {
        if (isAlbum()) {
            return new AlbumPinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
        }
        if (isStories()) {
            return new StoriesPinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
        }
        return new PinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
    }

    public void endYearOrMonthForViewingAnimation() {
        PinchAnimationInterface pinchAnimationInterface = this.mInterface;
        if (pinchAnimationInterface != null) {
            pinchAnimationInterface.endYearOrMonthForViewingAnimation();
        }
    }

    public IPinchAdapter getAdapter() {
        if (getRecyclerView() != null) {
            return getRecyclerView().getAdapter();
        }
        return null;
    }

    public RecyclerView.ViewHolder getChildViewHolder(View view) {
        PinchAnimationInterface pinchAnimationInterface;
        if (view == null || (pinchAnimationInterface = this.mInterface) == null) {
            return null;
        }
        try {
            return pinchAnimationInterface.getViewHolder(view);
        } catch (IllegalArgumentException unused) {
            Log.e(this.TAG, "Illegal argument : view must be attached to recycler view");
            return null;
        }
    }

    public float[] getFocusXY() {
        PinchAnimationInterface pinchAnimationInterface = this.mInterface;
        if (pinchAnimationInterface != null) {
            return pinchAnimationInterface.getFocusXY();
        }
        return new float[2];
    }

    public StaticLayout getHeaderDescriptionFrom(TextView textView, int i2, int i7, TextUtils.TruncateAt truncateAt) {
        return getStaticLayout(textView, i2, i7, truncateAt);
    }

    public StaticLayout getHeaderDescriptionTo(TextView textView, int i2, int i7, TextUtils.TruncateAt truncateAt) {
        return getStaticLayout(textView, i2, i7, truncateAt);
    }

    public ArrayList<Animator> getPropertyAnimators() {
        return this.mAnimator;
    }

    public RectF getRect(View view) {
        return new RectF(view.getX(), view.getY(), view.getX() + ((float) view.getWidth()), view.getY() + ((float) view.getHeight()));
    }

    public IPinchRecyclerView getRecyclerView() {
        PinchAnimationInterface pinchAnimationInterface = this.mInterface;
        if (pinchAnimationInterface != null) {
            return pinchAnimationInterface.getRecyclerView();
        }
        return null;
    }

    public final int[] getScrollPositionAndOffset() {
        return new int[]{this.mScrollPosition, this.mScrollOffset};
    }

    public boolean isAlbum() {
        return false;
    }

    public boolean isAnimating() {
        return false;
    }

    public boolean isSelectionMode() {
        return this.mLayoutManager.isSelectionMode();
    }

    public boolean isSingleSelectionMode() {
        return this.mLayoutManager.isSingleSelectionMode();
    }

    public boolean isStories() {
        return false;
    }

    public abstract boolean isThumbKindChanged();

    public void onAnimationCompleted(boolean z, boolean z3) {
        this.mAnimator.clear();
        animationCompletedInternal(z, z3);
        this.mGridInfo.reset();
        this.mShiftOffset = 0;
        this.mPositionCache.clear();
        this.mShiftHeaderOffset = 0;
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            restoreBitmaps();
            clearBitmapCache();
            this.mFinishingAnimation = false;
            getRecyclerView().setPreserveFocusAfterLayout(true);
            getRecyclerView().addDefaultItemAnimator();
            updateScrollPosition();
        }
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        updateColumns();
        this.mGridInfo.set(i2, i7, i8);
        this.mIsRtl = getRecyclerView().getContext().getResources().getBoolean(R$bool.is_right_to_left);
        getRecyclerView().setClipChildren(false);
    }

    public void putToBitmapKeyHolder(MediaItem mediaItem, ThumbKind thumbKind) {
        this.mBitmapKeyHolders.put(mediaItem.getThumbCacheKey(), new ThumbKeyHolder(mediaItem.getThumbCacheKey(), thumbKind, mediaItem.isBroken(), mediaItem.getCropRectRatio()));
    }

    public void resetScale(View view) {
        if (view != null) {
            view.setPivotX(((float) view.getWidth()) / 2.0f);
            view.setPivotX(((float) view.getHeight()) / 2.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
    }

    public void resetTranslate(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    public void restoreBitmapFromCache(ListViewHolder listViewHolder) {
        MediaItem mediaItem;
        ThumbKeyHolder thumbKeyHolder;
        Bitmap bitmap;
        if (listViewHolder != null && listViewHolder.getImage() != null && (mediaItem = listViewHolder.getMediaItem()) != null && (thumbKeyHolder = this.mBitmapKeyHolders.get(mediaItem.getThumbCacheKey())) != null) {
            if (thumbKeyHolder.mIsBroken) {
                bitmap = getBrokenThumbnail(listViewHolder.getImage().getContext(), mediaItem);
            } else if (isThumbKindChanged()) {
                ThumbnailLoader instance = ThumbnailLoader.getInstance();
                String str = thumbKeyHolder.mKey;
                ThumbKind thumbKind = thumbKeyHolder.mThumbKind;
                ThumbKind thumbKind2 = ThumbKind.XLARGE_NC_KIND;
                if (thumbKind != thumbKind2) {
                    thumbKind2 = ThumbKind.FREE_KIND;
                }
                bitmap = instance.getMemCache(str, thumbKind2, thumbKeyHolder.mCropRectRatio);
            } else if (!listViewHolder.hasBitmap()) {
                bitmap = ThumbnailLoader.getInstance().getMemCache(thumbKeyHolder.mKey, thumbKeyHolder.mThumbKind, thumbKeyHolder.mCropRectRatio);
            } else {
                bitmap = null;
            }
            if (bitmap != null) {
                listViewHolder.bindImage(bitmap);
            }
        }
    }

    public void setItemViewMargin() {
        int childCount = this.mLayoutManager.getChildCount();
        int spanCount = this.mLayoutManager.getSpanCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            if (!(childAt == null || getChildViewHolder(childAt) == null || getAdapter() == null)) {
                getAdapter().setViewHolderMargin((ListViewHolder) getChildViewHolder(childAt), spanCount);
            }
        }
    }

    public void setPinchAnimationInterface(PinchAnimationInterface pinchAnimationInterface) {
        this.mInterface = pinchAnimationInterface;
    }

    public void startAnimation() {
        PinchAnimationInterface pinchAnimationInterface = this.mInterface;
        if (pinchAnimationInterface != null) {
            pinchAnimationInterface.startAnimation();
        }
        getRecyclerView().removeItemAnimator();
    }

    public void startMonthForViewingClickedAnimation(int i2, float f, float f5) {
    }

    public void stopPreview() {
        Optional.ofNullable(getAdapter()).ifPresent(new b(12));
    }

    public void updateAnimation() {
        PinchAnimationInterface pinchAnimationInterface = this.mInterface;
        if (pinchAnimationInterface != null) {
            pinchAnimationInterface.updateAnimation();
        }
    }

    public void updateColumns() {
        PinchAnimationInterface pinchAnimationInterface = this.mInterface;
        if (pinchAnimationInterface != null) {
            this.mGridInfo.setActiveColumns(pinchAnimationInterface.getActiveColumns());
        }
    }

    public void updateScrollPosition() {
        getRecyclerView().refreshScrollPosition();
    }

    public void addAnimation(ArrayList<PropertyAnimator> arrayList) {
        this.mAnimator.addAll(arrayList);
    }

    public void startMonthForViewingClickedAnimation(int i2, RectF rectF) {
    }

    public void addFakeViewBitmapToCache() {
    }

    public void onAnimationStarted() {
    }

    public void onDump(PrintWriter printWriter, String str) {
    }

    public void startYearClickedAnimation(int i2, RectF rectF) {
    }

    public void updateAnimator(int i2, int i7) {
    }
}
