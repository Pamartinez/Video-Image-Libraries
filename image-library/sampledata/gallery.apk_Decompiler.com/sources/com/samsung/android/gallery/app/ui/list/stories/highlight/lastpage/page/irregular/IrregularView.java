package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular;

import B6.c;
import B8.f;
import a8.d;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import b4.C0422b;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import l6.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IrregularView {
    protected final RectF DEFAULT_INSET = new RectF(0.1f, 0.1f, 0.1f, 0.1f);
    private int mBgColor;
    private ViewGroup mCollageLayout;
    protected IrregularCollageViewHolder mMainViewHolder;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Position {

        /* renamed from: x  reason: collision with root package name */
        float f2559x;
        float y;

        public Position(float f, float f5) {
            this.f2559x = f;
            this.y = f5;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ViewDirection {
        PORTRAIT(false),
        LANDSCAPE(true);
        
        private final boolean isLandscape;

        private ViewDirection(boolean z) {
            this.isLandscape = z;
        }

        public boolean isLandscape() {
            return this.isLandscape;
        }
    }

    private void bindIrregularShapeBitmap(ImageView imageView, Drawable drawable, Bitmap bitmap, Rect rect, int i2) {
        int i7 = i2;
        ThreadUtil.postOnUiThread(new C0422b(this, drawable, i7, bitmap, rect, imageView));
    }

    private void bindMainItem(MediaItem mediaItem) {
        IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
        if (irregularCollageViewHolder != null) {
            irregularCollageViewHolder.bind(mediaItem);
            ThumbKind thumbKind = ThumbKind.XLARGE_NC_KIND;
            Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
            if (memCache != null) {
                onThumbnailLoadCompleted(mediaItem, memCache, thumbKind);
            } else {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new a(5, this, mediaItem));
            }
        }
    }

    private void bindShapeImage(ImageView imageView, MediaItem mediaItem, Bitmap bitmap, RectF rectF) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            bindIrregularShapeBitmap(imageView, drawable, bitmap, new CropCalculator(mediaItem, drawable).getImageCropRect(bitmap, rectF), getOrientation(mediaItem));
            return;
        }
        ThreadUtil.postOnUiThread(new c(imageView, bitmap, 2));
    }

    private void createMainViewHolder() {
        View findViewById = this.mCollageLayout.findViewById(R.id.main_shape);
        if (findViewById != null) {
            this.mMainViewHolder = new IrregularCollageViewHolder(findViewById, 0, getMainFaceInset());
        }
    }

    private void drawSubShapes(Canvas canvas) {
        for (ImageView drawImageView : getSubShapes()) {
            drawImageView(canvas, drawImageView);
        }
    }

    private Bitmap getBrokenImage(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    private int getOrientation(MediaItem mediaItem) {
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            return 0;
        }
        return mediaItem.getOrientation();
    }

    private RectF getRectF(Bitmap bitmap, Rect rect) {
        if (rect == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        return new RectF(((float) rect.left) / width, ((float) rect.top) / height, ((float) rect.right) / width, ((float) rect.bottom) / height);
    }

    private RectF getSmartCropForCover(MediaItem[] mediaItemArr, int i2) {
        return CoverRect.getSmartCropForCover((FileItemInterface) mediaItemArr[i2], getViewDirections()[i2].isLandscape());
    }

    private void inflateCollageLayout(ViewGroup viewGroup) {
        this.mCollageLayout = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, true);
    }

    private void initBgColor(ViewGroup viewGroup) {
        this.mBgColor = viewGroup.getContext().getColor(R.color.story_highlight_collage_content_bg_color);
    }

    private void initMainCover() {
        ImageView imageView = (ImageView) this.mCollageLayout.findViewById(R.id.cover_image);
        if (imageView != null) {
            imageView.setColorFilter(new PorterDuffColorFilter(this.mBgColor, PorterDuff.Mode.SRC_OUT));
            Drawable drawable = imageView.getDrawable();
            IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
            if (irregularCollageViewHolder != null && drawable != null) {
                irregularCollageViewHolder.setDrawableSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
        }
    }

    private boolean isRotated(int i2) {
        if (i2 == 90 || i2 == 270) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$2(ImageView imageView, MediaItem mediaItem, RectF rectF, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(imageView, mediaItem, bitmap, rectF);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindIrregularShapeBitmap$5(Drawable drawable, int i2, Bitmap bitmap, Rect rect, ImageView imageView) {
        int i7;
        int i8;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        Paint paint = new Paint();
        if (isRotated(i2)) {
            i7 = intrinsicHeight;
        } else {
            i7 = intrinsicWidth;
        }
        if (isRotated(i2)) {
            i8 = intrinsicWidth;
        } else {
            i8 = intrinsicHeight;
        }
        Rect rect2 = new Rect(0, 0, i7, i8);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.save();
        canvas.translate(((float) intrinsicWidth) / 2.0f, ((float) intrinsicHeight) / 2.0f);
        canvas.rotate((float) i2);
        if (isRotated(i2)) {
            canvas.translate(((float) (-intrinsicHeight)) / 2.0f, ((float) (-intrinsicWidth)) / 2.0f);
        } else {
            canvas.translate(((float) (-intrinsicWidth)) / 2.0f, ((float) (-intrinsicHeight)) / 2.0f);
        }
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        canvas.restore();
        imageView.setTag(new RectF(getRectF(bitmap, rect)));
        imageView.setImageBitmap(createBitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindMainItem$0(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(mediaItem, bitmap, thumbKind);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$1(Bitmap bitmap, MediaItem mediaItem, ThumbKind thumbKind) {
        if (this.mMainViewHolder != null) {
            if (bitmap != null) {
                if (ThumbnailLoader.getInstance().isReplacedThumbnail(bitmap)) {
                    mediaItem.setBroken(true);
                }
                this.mMainViewHolder.setThumbKind(thumbKind);
                this.mMainViewHolder.bindImage(bitmap);
                return;
            }
            mediaItem.setBroken(true);
            this.mMainViewHolder.bindImage(getBrokenImage(mediaItem));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$3(Bitmap bitmap, MediaItem mediaItem, ImageView imageView, RectF rectF) {
        if (bitmap != null) {
            if (ThumbnailLoader.getInstance().isReplacedThumbnail(bitmap)) {
                mediaItem.setBroken(true);
            }
            bindShapeImage(imageView, mediaItem, bitmap, rectF);
            return;
        }
        mediaItem.setBroken(true);
        bindShapeImage(imageView, mediaItem, getBrokenImage(mediaItem), rectF);
    }

    private void onThumbnailLoadCompleted(MediaItem mediaItem, Bitmap bitmap, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new d((Object) this, (Object) bitmap, mediaItem, (Object) thumbKind, 20));
    }

    public void bindImage(ImageView imageView, MediaItem mediaItem, RectF rectF) {
        ThumbKind thumbKind = ThumbKind.XLARGE_NC_KIND;
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(imageView, mediaItem, memCache, rectF);
            return;
        }
        MediaItem mediaItem2 = mediaItem;
        ThumbnailLoader.getInstance().loadThumbnail(mediaItem2, thumbKind, new f((Object) this, (Object) imageView, mediaItem2, (Object) rectF, 8));
    }

    public void createView(ViewGroup viewGroup) {
        initBgColor(viewGroup);
        inflateCollageLayout(viewGroup);
        onViewCreated(this.mCollageLayout);
        createMainViewHolder();
        initMainCover();
    }

    public void drawImageView(Canvas canvas, ImageView imageView) {
        RectF viewRect = ViewUtils.getViewRect(imageView);
        canvas.save();
        canvas.translate(viewRect.left - ViewUtils.getViewRect((View) imageView.getParent()).left, viewRect.top);
        imageView.draw(canvas);
        canvas.restore();
    }

    public void drawShapes(Canvas canvas, boolean z) {
        float y = this.mCollageLayout.getY();
        ImageView imageView = (ImageView) this.mCollageLayout.findViewById(R.id.cover_image);
        imageView.setColorFilter(new PorterDuffColorFilter(this.mBgColor, PorterDuff.Mode.SRC_IN));
        Canvas canvas2 = canvas;
        boolean z3 = z;
        drawImageView(canvas2, imageView, y, getMainShapePosition(), z3);
        imageView.setColorFilter(new PorterDuffColorFilter(this.mBgColor, PorterDuff.Mode.SRC_OUT));
        ImageView[] subShapes = getSubShapes();
        Position[] subShapePosition = getSubShapePosition();
        for (int i2 = 0; i2 < subShapes.length; i2++) {
            drawImageView(canvas2, subShapes[i2], y, subShapePosition[i2], z3);
        }
    }

    public void drawWithoutMainViewHolder(Canvas canvas) {
        drawImageView(canvas, (ImageView) this.mCollageLayout.findViewById(R.id.cover_image));
        drawSubShapes(canvas);
    }

    public ArrayList<RectF> getCropRectFList(MediaItem[] mediaItemArr) {
        ArrayList<RectF> arrayList = new ArrayList<>();
        IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
        if (irregularCollageViewHolder == null || irregularCollageViewHolder.getCropRect() == null) {
            arrayList.add(getSmartCropForCover(mediaItemArr, 0));
        } else {
            arrayList.add(getRectF(this.mMainViewHolder.getBitmap(false), this.mMainViewHolder.getCropRect()));
        }
        ImageView[] subShapes = getSubShapes();
        for (int i2 = 0; i2 < subShapes.length; i2++) {
            RectF rectF = (RectF) subShapes[i2].getTag();
            if (rectF == null) {
                rectF = getSmartCropForCover(mediaItemArr, i2 + 1);
            }
            arrayList.add(rectF);
        }
        return arrayList;
    }

    public float getFloatDimen(int i2) {
        return ResourceCompat.getFloatFromDimension(this.mCollageLayout.getContext(), i2);
    }

    public ArrayList<View> getItemViews() {
        ArrayList<View> arrayList = new ArrayList<>();
        arrayList.add(getMainViewHolder().itemView);
        arrayList.addAll(Arrays.asList(getSubShapes()));
        return arrayList;
    }

    public abstract int getLayoutId();

    public abstract RectF getMainFaceInset();

    public abstract Position getMainShapePosition();

    public ListViewHolder getMainViewHolder() {
        return this.mMainViewHolder;
    }

    public abstract int getShapeCount();

    public abstract Position[] getSubShapePosition();

    public abstract ImageView[] getSubShapes();

    public abstract ViewDirection[] getViewDirections();

    public boolean hasVideo() {
        IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
        if (irregularCollageViewHolder == null || !irregularCollageViewHolder.getMediaItem().isVideo()) {
            return false;
        }
        return true;
    }

    public abstract void onViewCreated(ViewGroup viewGroup);

    public void recycle() {
        IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
        if (irregularCollageViewHolder != null) {
            irregularCollageViewHolder.recycle();
        }
        restoreDrawable();
    }

    public abstract void restoreDrawable();

    public void setCollageItems(ArrayList<MediaItem> arrayList) {
        bindMainItem(arrayList.get(0));
        setCollageItemsInternal(arrayList);
    }

    public abstract void setCollageItemsInternal(ArrayList<MediaItem> arrayList);

    public void setVisibleOrGone(boolean z) {
        ViewUtils.setVisibleOrGone(this.mCollageLayout, z);
    }

    public void startPreview() {
        IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
        if (irregularCollageViewHolder != null && !irregularCollageViewHolder.isPreviewing()) {
            this.mMainViewHolder.startPreview();
        }
    }

    public void stopPreview() {
        IrregularCollageViewHolder irregularCollageViewHolder = this.mMainViewHolder;
        if (irregularCollageViewHolder != null) {
            irregularCollageViewHolder.stopPreview();
        }
    }

    public void updateSize(int i2, int i7) {
        ViewUtils.setViewSize(this.mCollageLayout, Integer.valueOf(i2), Integer.valueOf(i7));
    }

    private void onThumbnailLoadCompleted(ImageView imageView, MediaItem mediaItem, Bitmap bitmap, RectF rectF) {
        Bitmap bitmap2 = bitmap;
        ThreadUtil.runOnBgThread(new B5.c(this, bitmap2, mediaItem, imageView, rectF, 28));
    }

    private void drawImageView(Canvas canvas, ImageView imageView, float f, Position position, boolean z) {
        float f5;
        float f8;
        if (z) {
            RectF viewRect = ViewUtils.getViewRect(imageView);
            f5 = viewRect.left - ViewUtils.getViewRect((View) imageView.getParent()).left;
            f8 = viewRect.top;
        } else {
            f5 = ((float) canvas.getWidth()) * position.f2559x;
            f8 = f + ((((float) canvas.getHeight()) - f) * position.y);
        }
        canvas.save();
        canvas.translate(f5, f8);
        imageView.draw(canvas);
        canvas.restore();
    }
}
