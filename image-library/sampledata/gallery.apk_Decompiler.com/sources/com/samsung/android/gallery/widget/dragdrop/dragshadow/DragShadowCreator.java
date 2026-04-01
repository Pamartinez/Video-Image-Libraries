package com.samsung.android.gallery.widget.dragdrop.dragshadow;

import Gb.a;
import I4.g;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;
import n4.C0491c;
import qa.e;
import w4.C0533c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragShadowCreator {
    private final DragShadowInfo mDragShadowInfo = new DragShadowInfo();
    private boolean mIsRTL;
    private int mItemCount;
    private int mLayerOffset;
    private float mThumbRadius;
    private int mThumbSize;
    private boolean mUseCircleThumb;
    private boolean mUseLayerThumb;
    private boolean mUseUncroppedBitmap;

    private void addThumbBorder(ListViewHolder listViewHolder, Drawable drawable) {
        Optional.ofNullable(drawable).ifPresent(new C0491c(22, listViewHolder, drawable));
    }

    private ColorFilter backupColorFilter(ImageView imageView) {
        ColorFilter colorFilter = imageView.getColorFilter();
        imageView.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
        return colorFilter;
    }

    private Bitmap createBitmap(ImageView imageView, MediaItem mediaItem) {
        ColorFilter backupColorFilter = backupColorFilter(imageView);
        Bitmap decoratedBitmap = getDecoratedBitmap(imageView.getContext(), getSourceBitmap(ViewUtils.getBitmapFromView(imageView), mediaItem));
        restoreColorFilter(imageView, backupColorFilter);
        return decoratedBitmap;
    }

    private void drawBorder(Canvas canvas, RectF rectF, Paint paint) {
        if (this.mUseCircleThumb) {
            drawOvalBorder(canvas, paint);
        } else {
            drawRectBorder(canvas, rectF, paint);
        }
    }

    private void drawCountIcon(Context context, Canvas canvas, RectF rectF) {
        float f;
        Drawable drawable = context.getDrawable(R$drawable.gallery_move_bg_text_dark);
        if (drawable != null) {
            Paint countTextPaint = getCountTextPaint(context);
            int countIconSidePadding = (this.mDragShadowInfo.getCountIconSidePadding(context) * 2) + ((int) countTextPaint.measureText(Integer.toString(this.mItemCount)));
            int countIconSize = this.mDragShadowInfo.getCountIconSize(context);
            int max = Math.max(countIconSidePadding, countIconSize);
            float f5 = (float) max;
            float f8 = f5 / 2.0f;
            float f10 = (float) countIconSize;
            float f11 = f10 / 2.0f;
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable, max, countIconSize);
            if (bitmapFromDrawable != null) {
                if (this.mIsRTL) {
                    float f12 = rectF.left;
                    if (this.mUseUncroppedBitmap) {
                        f11 = 0.0f;
                    }
                    f = f12 - f11;
                } else {
                    float f13 = rectF.right - f5;
                    if (this.mUseUncroppedBitmap) {
                        f11 = 0.0f;
                    }
                    f = f13 + f11;
                }
                canvas.drawBitmap(bitmapFromDrawable, f, 0.0f, (Paint) null);
                canvas.drawText(String.format(Locale.getDefault(), "%1$d", new Object[]{Integer.valueOf(this.mItemCount)}), f + f8, ((countTextPaint.getTextSize() + f10) - countTextPaint.descent()) / 2.0f, countTextPaint);
            }
        }
    }

    private void drawLayer(Context context, Canvas canvas, float f) {
        float f5;
        int i2;
        float f8;
        if (isLayerThumbVisible()) {
            float f10 = ((float) this.mLayerOffset) + f;
            int i7 = this.mThumbSize;
            RectF rectF = new RectF(0.0f, f10, (float) i7, ((float) i7) + f10);
            if (this.mItemCount == 2) {
                if (this.mIsRTL) {
                    f8 = Math.max(f - ((float) this.mLayerOffset), 0.0f);
                } else {
                    f8 = (float) this.mLayerOffset;
                }
                rectF.left = f8;
            } else {
                if (this.mIsRTL) {
                    f5 = Math.max(f - (((float) this.mLayerOffset) * 2.0f), 0.0f);
                } else {
                    f5 = ((float) this.mLayerOffset) * 2.0f;
                }
                float f11 = rectF.top;
                int i8 = this.mLayerOffset;
                RectF rectF2 = new RectF(f5, f11 + ((float) i8), ((float) this.mThumbSize) + f5, rectF.bottom + ((float) i8));
                float f12 = this.mThumbRadius;
                canvas.drawRoundRect(rectF2, f12, f12, getLayerPaint(this.mDragShadowInfo.getThirdLayerColor(context)));
                float f13 = rectF2.left;
                if (this.mIsRTL) {
                    i2 = this.mLayerOffset;
                } else {
                    i2 = -this.mLayerOffset;
                }
                rectF.left = f13 + ((float) i2);
            }
            rectF.right += rectF.left;
            float f14 = this.mThumbRadius;
            canvas.drawRoundRect(rectF, f14, f14, getLayerPaint(this.mDragShadowInfo.getSecondLayerColor(context)));
        }
    }

    private void drawOvalBorder(Canvas canvas, Paint paint) {
        int i2 = this.mThumbSize;
        canvas.drawCircle(((float) i2) / 2.0f, ((float) i2) / 2.0f, ((float) i2) / 2.0f, paint);
    }

    private void drawRectBorder(Canvas canvas, RectF rectF, Paint paint) {
        float f = this.mThumbRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
    }

    private RectF drawThumbnail(Canvas canvas, Bitmap bitmap, float f) {
        Canvas canvas2;
        float f5;
        Paint paint = new Paint();
        if (this.mUseCircleThumb) {
            int i2 = this.mThumbSize;
            float f8 = (float) i2;
            float f10 = (float) i2;
            canvas2 = canvas;
            canvas2.drawOval(0.0f, 0.0f, f8, f10, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        } else {
            canvas2 = canvas;
            bitmap = BitmapUtils.getRoundedCornerBitmap(bitmap, this.mThumbRadius);
        }
        if (!this.mUseUncroppedBitmap && this.mIsRTL) {
            f5 = Math.max(f, getLayerOffset());
        } else {
            f5 = 0.0f;
        }
        if (this.mUseUncroppedBitmap) {
            f = 0.0f;
        }
        int i7 = this.mThumbSize;
        RectF rectF = new RectF(f5, f, ((float) i7) + f5, ((float) i7) + f);
        canvas2.drawBitmap(bitmap, rectF.left, rectF.top, paint);
        return rectF;
    }

    private Bitmap getBitmapFromRef(ArrayList<ClipData.Item> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        Bitmap[] bitmapArr = new Bitmap[1];
        new LatchBuilder("getBitmapFromRef").addWorker(new C0533c(this, arrayList, bitmapArr, 2)).setTimeout(1000).start();
        Bitmap bitmap = bitmapArr[0];
        if (bitmap != null) {
            return bitmap;
        }
        return null;
    }

    private Paint getCountTextPaint(Context context) {
        Paint paint = new Paint(2);
        paint.setColor(context.getColor(R$color.count_badge_text_color));
        paint.setTextSize(context.getResources().getDimension(R$dimen.header_count_text_size));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(FontTypefaceUtils.TextFont.ROBOTO_SEMI_BOLD.getTypeface());
        return paint;
    }

    private Bitmap getCroppedBitmap(MediaItem mediaItem, Bitmap bitmap, int i2) {
        RectF rectF;
        Rect rect = null;
        if (bitmap == null) {
            return null;
        }
        if (mediaItem.isMotionPhoto()) {
            rectF = null;
        } else {
            rectF = mediaItem.getCropRectRatio();
        }
        boolean z = true;
        if (RectUtils.isValidRect(rectF)) {
            rect = RectUtils.getSmartCropRect(bitmap, rectF, i2, true);
        }
        if (mediaItem.isCreature() || mediaItem.isPanoramic() || mediaItem.isCustomCover()) {
            z = false;
        }
        return new BitmapOperator(bitmap).resize(this.mThumbSize).crop(rect).stretchable(z).rotate(i2).apply();
    }

    private Bitmap getDecoratedBitmap(Context context, Bitmap bitmap) {
        if (!(context == null || bitmap == null)) {
            float countIconSize = ((float) this.mDragShadowInfo.getCountIconSize(context)) / 2.0f;
            int[] targetWidthHeightOfBitmap = getTargetWidthHeightOfBitmap((int) countIconSize);
            try {
                Bitmap createBitmap = BitmapUtils.createBitmap(targetWidthHeightOfBitmap[0], targetWidthHeightOfBitmap[1], Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawLayer(context, canvas, countIconSize);
                RectF drawThumbnail = drawThumbnail(canvas, bitmap, countIconSize);
                drawBorder(canvas, drawThumbnail, this.mDragShadowInfo.getBorderPaint(context));
                drawCountIcon(context, canvas, drawThumbnail);
                return createBitmap;
            } catch (OutOfMemoryError unused) {
                System.gc();
            } catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private float getLayerOffset() {
        if (!isLayerThumbVisible()) {
            return 0.0f;
        }
        int i2 = this.mItemCount;
        int i7 = this.mLayerOffset;
        if (i2 != 2) {
            i7 *= 2;
        }
        return (float) i7;
    }

    private Paint getLayerPaint(int i2) {
        Paint paint = new Paint();
        paint.setColor(i2);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private Bitmap getSourceBitmap(Bitmap bitmap, MediaItem mediaItem) {
        if (bitmap == null) {
            return null;
        }
        if (this.mUseUncroppedBitmap) {
            return BitmapUtils.resize(bitmap, this.mThumbSize);
        }
        if (mediaItem != null) {
            return getCroppedBitmap(mediaItem, bitmap, 0);
        }
        return bitmap;
    }

    private int[] getTargetWidthHeightOfBitmap(int i2) {
        float f;
        float layerOffset = getLayerOffset();
        float f5 = (float) this.mThumbSize;
        float f8 = 0.0f;
        if (this.mUseUncroppedBitmap) {
            f = 0.0f;
        } else {
            f = Math.max((float) i2, layerOffset);
        }
        int i7 = (int) (f5 + f);
        float f10 = (float) this.mThumbSize;
        if (!this.mUseUncroppedBitmap) {
            f8 = ((float) i2) + layerOffset;
        }
        return new int[]{i7, (int) (f10 + f8)};
    }

    private boolean isLayerThumbVisible() {
        if (!this.mUseLayerThumb || this.mItemCount <= 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getBitmapFromRef$4(Bitmap[] bitmapArr, Uri uri) {
        ArrayList arrayList = new ArrayList();
        if (UriItemLoader.loadMediaItemFromUris(new ArrayList(Collections.singletonList(uri)), arrayList)) {
            MediaItem mediaItem = (MediaItem) arrayList.get(0);
            bitmapArr[0] = getCroppedBitmap(mediaItem, ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND), mediaItem.getThumbnailOrientation());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getBitmapFromRef$5(ArrayList arrayList, Bitmap[] bitmapArr) {
        arrayList.stream().map(new a(20)).filter(new com.samsung.android.gallery.module.dynamicview.a(17)).reduce(new com.samsung.android.motionphoto.utils.ex.a(2)).ifPresent(new C0491c(23, this, bitmapArr));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getUpdatedBitmap$2(ClipData.Item item) {
        if (item.getUri() != null) {
            return true;
        }
        return false;
    }

    private void restoreColorFilter(ImageView imageView, ColorFilter colorFilter) {
        imageView.setColorFilter(colorFilter);
    }

    private void restoreThumbBorder(ListViewHolder listViewHolder, Drawable drawable) {
        Optional.ofNullable(drawable).ifPresent(new g(listViewHolder, 3));
    }

    public Bitmap getDragStartBitmap(ClipData clipData, ListViewHolder listViewHolder, int i2) {
        ImageView image = listViewHolder.getImage();
        if (image == null) {
            return null;
        }
        this.mUseCircleThumb = ClipDataUtils.getBooleanExtra(clipData, "use_circle_thumb");
        this.mUseLayerThumb = ClipDataUtils.getBooleanExtra(clipData, "use_layer_thumb");
        this.mUseUncroppedBitmap = ClipDataUtils.getBooleanExtra(clipData, "use_uncropped_bitmap");
        this.mItemCount = i2;
        this.mIsRTL = this.mDragShadowInfo.isRtl();
        this.mThumbSize = this.mDragShadowInfo.getThumbSize(image.getContext(), listViewHolder);
        this.mLayerOffset = this.mDragShadowInfo.getLayerOffset(image.getContext());
        this.mThumbRadius = this.mDragShadowInfo.getRadius(image.getContext());
        MediaItem mediaItem = listViewHolder.getMediaItem();
        Drawable thumbnailBorder = listViewHolder.getThumbnailBorder();
        restoreThumbBorder(listViewHolder, thumbnailBorder);
        Bitmap createBitmap = createBitmap(image, mediaItem);
        addThumbBorder(listViewHolder, thumbnailBorder);
        return createBitmap;
    }

    public Bitmap getUpdatedBitmap(Context context, ArrayList<ClipData.Item> arrayList) {
        int i2;
        if (arrayList != null) {
            i2 = (int) arrayList.stream().filter(new e(15)).filter(new e(20)).count();
        } else {
            i2 = 0;
        }
        this.mItemCount = i2;
        Bitmap bitmapFromRef = getBitmapFromRef(arrayList);
        if (bitmapFromRef != null) {
            return getDecoratedBitmap(context, bitmapFromRef);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Uri lambda$getBitmapFromRef$3(Uri uri, Uri uri2) {
        return uri2;
    }
}
