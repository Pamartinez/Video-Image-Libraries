package com.samsung.android.gallery.widget.filmstrip3;

import A4.C0366a;
import A4.C0367b;
import A4.Y;
import A9.b;
import Bb.g;
import Bb.h;
import Bb.i;
import Bb.j;
import Bb.k;
import Bb.l;
import Bb.m;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.MediaItemAccessibilityDelegate;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FilmStripViewHolder<VIEW_BINDING extends ViewBinding> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected final String TAG;
    protected Bitmap mBitmap;
    private int mDefaultHeight = -1;
    private int mDefaultWidth = -1;
    protected FilmStripView mFilmStripView;
    protected boolean mFocused;
    private int mMaxHeight = -1;
    private int mMaxWidth = -1;
    protected MediaItem mMediaItem;
    protected final ConcurrentLinkedQueue<OnFilmStripItemClickListener> mOnItemClickListener = new ConcurrentLinkedQueue<>();
    protected int mPosition;
    public final FilmStripProgressBarDelegate mProgressDelegate;
    protected final VIEW_BINDING mViewBinding;

    public FilmStripViewHolder(VIEW_BINDING view_binding) {
        super(view_binding.getRoot());
        FilmStripProgressBarDelegate filmStripProgressBarDelegate;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        this.mViewBinding = view_binding;
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            filmStripProgressBarDelegate = new FilmStripProgressBarDelegate(simpleName, (ViewGroup) this.itemView);
        } else {
            filmStripProgressBarDelegate = null;
        }
        this.mProgressDelegate = filmStripProgressBarDelegate;
        this.itemView.setOnClickListener(this);
        View findViewById = view_binding.getRoot().findViewById(R$id.film_strip_view_image);
        if (findViewById != null) {
            findViewById.addOnLayoutChangeListener(new g(1, this));
        }
        View view = this.itemView;
        setViewShape(view, view.getResources().getDimension(R$dimen.film_strip_item_corner_radius));
    }

    private static boolean isViewSame(FilmStripViewHolder filmStripViewHolder, int i2) {
        if (filmStripViewHolder.getLayoutPosition() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFilterThumbnailLoaded$8(int i2, Bitmap bitmap, ImageView imageView) {
        if (isViewSame(this, i2)) {
            if (bitmap == null) {
                setBrokenBitmap();
            } else {
                this.mBitmap = bitmap;
            }
            imageView.setImageBitmap(this.mBitmap);
            lambda$tryViewMatrix$4(imageView, this.mMediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFocused$0(FilmStripProgressBarDelegate filmStripProgressBarDelegate) {
        filmStripProgressBarDelegate.onFocused(getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultImage$1(int i2, Bitmap bitmap, ImageView imageView) {
        if (this.mMediaItem != null && isViewSame(this, i2)) {
            if (bitmap == null) {
                String str = this.TAG;
                Log.w(str, "bindImage broken {" + this.mMediaItem.getFileId() + "}");
                this.mMediaItem.setBroken(true);
                setBrokenBitmap();
            } else {
                this.mBitmap = bitmap;
            }
            if (imageView.getDrawable() == null) {
                imageView.setImageBitmap(this.mBitmap);
                lambda$tryViewMatrix$4(imageView, this.mMediaItem);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultImage$2(int i2, ImageView imageView, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (bitmap != null) {
            bitmap.prepareToDraw();
        }
        ImageView imageView2 = imageView;
        ThreadUtil.postOnUiThread(new i(this, i2, bitmap, imageView2, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setDefaultImage$3(int i2) {
        return !isViewSame(this, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultImageWithFilter$5(ImageView imageView, int i2, Bitmap bitmap, Filter filter) {
        onFilterThumbnailLoaded(imageView, bitmap, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultImageWithFilter$6(ImageView imageView, int i2, Bitmap bitmap, Filter filter) {
        onFilterThumbnailLoaded(imageView, bitmap, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultImageWithFilter$7(BiConsumer biConsumer, ImageView imageView, int i2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.mBitmap = bitmap;
        biConsumer.accept(bitmap, new h(this, imageView, i2, 0));
    }

    private void onFilterThumbnailLoaded(ImageView imageView, Bitmap bitmap, int i2) {
        int i7 = i2;
        ThreadUtil.runOnUiThread(new i(this, i7, bitmap, imageView, 0));
    }

    private void setBrokenBitmap() {
        this.mBitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(this.itemView.getContext(), ResourceUtil.getBrokenDrawable(this.mMediaItem), ResourceUtil.getBrokenBgColor(this.mMediaItem));
    }

    public void addViewHolderListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        if (!this.mOnItemClickListener.contains(onFilmStripItemClickListener)) {
            this.mOnItemClickListener.add(onFilmStripItemClickListener);
        }
    }

    public void bindView(MediaItem mediaItem, int i2) {
        this.mMediaItem = mediaItem;
        this.mPosition = i2;
        this.itemView.setAccessibilityDelegate(new MediaItemAccessibilityDelegate(mediaItem, this.itemView.getContentDescription()));
    }

    public void expandSeekerMode(float f) {
        this.mFilmStripView.restoreExpandedView();
    }

    public void foldCenterExpanded(View view) {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            restoreFrameView(filmStripView);
        }
    }

    public int getCenterPos() {
        return (this.itemView.getWidth() / 2) + ((int) this.itemView.getX());
    }

    public int getDefaultHeight() {
        if (this.mDefaultHeight < 0) {
            this.mDefaultHeight = this.itemView.getContext().getResources().getDimensionPixelSize(getDefaultHeightDimenId());
        }
        return this.mDefaultHeight;
    }

    public int getDefaultHeightDimenId() {
        return R$dimen.film_strip_item_height;
    }

    public int getDefaultWidth() {
        if (this.mDefaultWidth < 0) {
            this.mDefaultWidth = this.itemView.getContext().getResources().getDimensionPixelSize(getDefaultWidthDimenId());
        }
        return this.mDefaultWidth;
    }

    public int getDefaultWidthDimenId() {
        return R$dimen.film_strip_image_width;
    }

    public int getMaxHeight() {
        if (this.mMaxHeight < 0) {
            this.mMaxHeight = this.itemView.getContext().getResources().getDimensionPixelSize(R$dimen.film_strip_focused_height);
        }
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        if (this.mMaxWidth < 0) {
            this.mMaxWidth = this.itemView.getContext().getResources().getDimensionPixelSize(getMaxWidthDimenId());
        }
        return this.mMaxWidth;
    }

    public int getMaxWidthDimenId() {
        return R$dimen.film_strip_focused_image_width;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public int getViewHolderPosition() {
        return this.mPosition;
    }

    public boolean isAnimating() {
        return false;
    }

    public boolean isCenter() {
        float width = (float) (this.mFilmStripView.getWidth() / 2);
        if (this.itemView.getX() >= width || this.itemView.getX() + ((float) this.itemView.getWidth()) <= width) {
            return false;
        }
        return true;
    }

    public boolean isExpanded() {
        return false;
    }

    public boolean isFocused() {
        return this.mFocused;
    }

    public void onClick(View view) {
        Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
        while (it.hasNext()) {
            it.next().onItemClick(this.mPosition, this);
        }
    }

    public void onExpandableItemClick(View view) {
        if (!isCenter()) {
            Iterator<OnFilmStripItemClickListener> it = this.mOnItemClickListener.iterator();
            while (it.hasNext()) {
                it.next().onItemClick(this.mPosition, this);
            }
        } else if (supportSeekerExpand()) {
            Iterator<OnFilmStripItemClickListener> it2 = this.mOnItemClickListener.iterator();
            while (it2.hasNext()) {
                it2.next().onRequestExpandSeeker(this.mPosition, this);
            }
        } else if (isExpanded()) {
            foldCenterExpanded(view);
        }
    }

    public void onFocusOut(RecyclerView recyclerView) {
        this.mFocused = false;
        Optional.ofNullable(this.mProgressDelegate).ifPresent(new C0366a(29));
    }

    public void onFocused(RecyclerView recyclerView) {
        this.mFocused = true;
        Optional.ofNullable(this.mProgressDelegate).ifPresent(new C0367b(16, this));
    }

    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        int i15 = i13 - i11;
        int i16 = i8 - i2;
        int i17 = i14 - i12;
        int i18 = i10 - i7;
        if (!(view instanceof ImageView)) {
            return;
        }
        if (i15 != i16 || i17 != i18) {
            lambda$tryViewMatrix$4((ImageView) view, this.mMediaItem);
        }
    }

    public void onViewAttached() {
        if (this.mFilmStripView == null) {
            this.mFilmStripView = (FilmStripView) this.mViewBinding.getRoot().getParent();
        }
    }

    public void onViewDetached() {
        if (isExpanded()) {
            this.mFilmStripView.setSeekerMode(false);
        }
    }

    public void onViewRecycled() {
        this.mBitmap = null;
        this.mMediaItem = null;
        this.mPosition = -1;
        ViewUtils.resize(this.itemView, getDefaultWidth(), getDefaultHeight());
        this.mFilmStripView = null;
        this.mOnItemClickListener.clear();
        Optional.ofNullable(this.mProgressDelegate).ifPresent(new l(0));
    }

    public void removeViewHolderListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        this.mOnItemClickListener.remove(onFilmStripItemClickListener);
    }

    public void restoreFrameView(RecyclerView recyclerView, boolean z) {
    }

    public void setDefaultImage(ImageView imageView) {
        MediaItem mediaItem;
        MediaItem mediaItem2 = this.mMediaItem;
        if (mediaItem2 == null || mediaItem2.getMediaType() != MediaType.UnlockScreen) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                tryViewMatrix(imageView, this.mMediaItem);
                return;
            }
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            MediaItem mediaItem3 = this.mMediaItem;
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            Bitmap memCache = instance.getMemCache(mediaItem3, thumbKind);
            this.mBitmap = memCache;
            if (memCache == null && ((mediaItem = this.mMediaItem) == null || mediaItem.isBroken())) {
                setBrokenBitmap();
            }
            Bitmap bitmap2 = this.mBitmap;
            if (bitmap2 != null) {
                imageView.setImageBitmap(bitmap2);
                lambda$tryViewMatrix$4(imageView, this.mMediaItem);
            } else if (this.mMediaItem != null) {
                int layoutPosition = getLayoutPosition();
                if (layoutPosition < 10) {
                    tryRecoverPppThumb(imageView);
                }
                ThumbnailLoader instance2 = ThumbnailLoader.getInstance();
                MediaItem mediaItem4 = this.mMediaItem;
                Objects.requireNonNull(imageView);
                instance2.loadThumbnail(mediaItem4, thumbKind, new j(imageView), new k(this, layoutPosition, imageView), new Y((Object) this, layoutPosition, 1));
            }
        }
    }

    public void setDefaultImageWithFilter(ImageView imageView, BiConsumer<Bitmap, BiConsumer<Bitmap, Filter>> biConsumer) {
        MediaItem mediaItem;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem2 = this.mMediaItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        Bitmap memCache = instance.getMemCache(mediaItem2, thumbKind);
        this.mBitmap = memCache;
        if (memCache != null || ((mediaItem = this.mMediaItem) != null && !mediaItem.isBroken())) {
            int layoutPosition = getLayoutPosition();
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                biConsumer.accept(bitmap, new h(this, imageView, layoutPosition, 1));
                return;
            }
            ThumbnailLoader instance2 = ThumbnailLoader.getInstance();
            MediaItem mediaItem3 = this.mMediaItem;
            Objects.requireNonNull(imageView);
            instance2.loadThumbnail(mediaItem3, thumbKind, new j(imageView), new m((Object) this, (Object) biConsumer, (Object) imageView, layoutPosition, 0));
            return;
        }
        onFilterThumbnailLoaded(imageView, this.mBitmap, getLayoutPosition());
    }

    public void setHeight(int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.itemView.getLayoutParams();
        if (i2 != marginLayoutParams.height) {
            int round = Math.round((((float) getDefaultWidth()) * ((float) i2)) / ((float) getDefaultHeight()));
            marginLayoutParams.width = round;
            marginLayoutParams.height = i2;
            if (Math.abs(round - getMaxWidth()) < 2 || Math.abs(marginLayoutParams.height - getMaxHeight()) < 2) {
                marginLayoutParams.width = getMaxWidth();
                marginLayoutParams.height = getMaxHeight();
            }
        }
        marginLayoutParams.topMargin = (getMaxHeight() - marginLayoutParams.height) / 2;
    }

    public void setSelected(boolean z) {
        this.itemView.setSelected(z);
    }

    /* renamed from: setViewMatrix */
    public void lambda$tryViewMatrix$4(ImageView imageView, MediaItem mediaItem) {
        if (mediaItem != null && imageView != null && imageView.getDrawable() != null) {
            imageView.setImageMatrix(ViewMatrixUtils.createImageMatrix(imageView, mediaItem));
        }
    }

    public void setViewShape(View view, final float f) {
        if (view != null) {
            view.setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    if (view.getWidth() > 0) {
                        outline.setRoundRect(view.getPaddingLeft(), view.getPaddingTop(), view.getWidth() - view.getPaddingRight(), view.getHeight() - view.getPaddingBottom(), f);
                    }
                }
            });
            view.setClipToOutline(true);
        }
    }

    public boolean supportSeeker() {
        return false;
    }

    public boolean supportSeekerExpand() {
        if (!PreferenceFeatures.VideoPlayerFeature.isSupportFilmSeeker() || isExpanded() || !MediaItemUtil.supportPreviewPlay(this.mMediaItem) || this.mMediaItem.isPostProcessing()) {
            return false;
        }
        return true;
    }

    public boolean supportSeekerOnRestored() {
        return true;
    }

    public boolean supportViewHolderSeek() {
        return false;
    }

    public String toString() {
        long j2;
        boolean z;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.TAG);
        sb2.append("{");
        sb2.append(this.mPosition);
        sb2.append(',');
        sb2.append(getLayoutPosition());
        sb2.append(',');
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = -1;
        }
        sb2.append(j2);
        sb2.append(',');
        if (this.mBitmap != null) {
            z = true;
        } else {
            z = false;
        }
        return C0086a.n(sb2, z, '}');
    }

    public void tryRecoverPppThumb(ImageView imageView) {
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Bitmap memCache = instance.getMemCache("ppp/" + this.mMediaItem.getFileId(), ThumbKind.MEDIUM_KIND, this.mMediaItem.getCropRectRatio());
        if (memCache != null) {
            String str = this.TAG;
            Log.d(str, "ppp recover bitmap : " + memCache);
            this.mBitmap = memCache;
            imageView.setImageBitmap(memCache);
            lambda$tryViewMatrix$4(imageView, this.mMediaItem);
        }
    }

    public final void tryViewMatrix(ImageView imageView, MediaItem mediaItem) {
        if (mediaItem != null && imageView.getDrawable() != null) {
            if (imageView.getWidth() <= 0 || imageView.getHeight() <= 0) {
                imageView.post(new b(this, imageView, mediaItem, 5));
            } else {
                lambda$tryViewMatrix$4(imageView, mediaItem);
            }
        }
    }

    public void restoreFrameView(RecyclerView recyclerView) {
        restoreFrameView(recyclerView, true);
    }
}
