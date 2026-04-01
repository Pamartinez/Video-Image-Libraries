package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import a8.d;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.CollagePageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import n0.C0235b;
import r6.C0512b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularGradientHelper {
    /* access modifiers changed from: private */
    public int[] mColor;
    /* access modifiers changed from: private */
    public Direction mDirection;
    private final boolean mUseViewRect;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ColorProps {
        static float brightness = 0.95f;
        static float colorAlpha = 0.65f;
        static float maxSaturation = 0.6f;
        static float minSaturation = 0.5f;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Direction {
        BOTTOM,
        SLANT
    }

    public IrregularGradientHelper(boolean z) {
        this.mUseViewRect = z;
    }

    private Bitmap convert(Drawable drawable, int i2, int i7) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i2, i7);
        drawable.draw(canvas);
        return createBitmap;
    }

    private Drawable createDrawable() {
        PaintDrawable paintDrawable = new PaintDrawable();
        paintDrawable.setShape(new RectShape());
        paintDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            public Shader resize(int i2, int i7) {
                if (Direction.BOTTOM == IrregularGradientHelper.this.mDirection) {
                    i2 = 0;
                }
                return new LinearGradient(0.0f, 0.0f, (float) i2, (float) i7, new int[]{IrregularGradientHelper.this.mColor[0], IrregularGradientHelper.this.mColor[1]}, new float[]{0.0f, 0.7f}, Shader.TileMode.MIRROR);
            }
        });
        return paintDrawable;
    }

    private Bitmap createGradientBitmap(View view, IrregularView irregularView, Drawable drawable) {
        Bitmap createBitmap = BitmapUtils.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        irregularView.drawShapes(canvas, this.mUseViewRect);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(convert(drawable, view.getWidth(), view.getHeight()), 0.0f, 0.0f, paint);
        return createBitmap;
    }

    private int createGradientColor(MediaItem mediaItem) {
        return createTopGradientColor(ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND));
    }

    private int createTopGradientColor(Bitmap bitmap) {
        return BitmapUtils.createTopGradientColor(bitmap, 0, 1.0f, ColorProps.minSaturation, ColorProps.maxSaturation, ColorProps.brightness, ColorProps.colorAlpha);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGradientBackground$0(ArrayList arrayList) {
        this.mColor[0] = createGradientColor((MediaItem) arrayList.get(0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGradientBackground$1(ArrayList arrayList) {
        this.mColor[1] = createGradientColor((MediaItem) C0212a.i(arrayList, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGradientBackground$3(ArrayList arrayList, View view, IrregularView irregularView) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mColor = new int[Math.min(2, arrayList.size())];
        new LatchBuilder("setGradientBackground").setCurrent(new C0512b(this, arrayList, 0)).addWorker(new C0512b(this, arrayList, 1)).start();
        Log.v("IrregularGradientHelper", "setGradientBackground" + Logger.vt(currentTimeMillis));
        ThreadUtil.postOnUiThreadDelayed(new C0235b(this, view, irregularView, 14), 50);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateGradientViewBitmap */
    public void lambda$setGradientBackground$2(View view, IrregularView irregularView) {
        ImageView imageView = (ImageView) view.findViewById(R.id.gradient_view);
        if (imageView != null) {
            imageView.setImageBitmap(createGradientBitmap(view, irregularView, createDrawable()));
            imageView.setVisibility(0);
        }
    }

    public Bitmap createBgForGradient(View view, IrregularView irregularView) {
        Bitmap createBitmap = BitmapUtils.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        irregularView.drawShapes(canvas, this.mUseViewRect);
        Paint paint = new Paint();
        paint.setColor(view.getContext().getColor(R.color.story_highlight_collage_content_bg_color));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawPaint(paint);
        return createBitmap;
    }

    public int[] getGradientColors() {
        return this.mColor;
    }

    public int getGradientDirection() {
        return this.mDirection.ordinal();
    }

    public void setGradientBackground(View view, IrregularView irregularView, PageItem pageItem, int i2) {
        ArrayList<MediaItem> arrayList;
        CollageInfo collageInfo = ((CollagePageItem) pageItem).getCollageInfo();
        if (collageInfo != null) {
            arrayList = collageInfo.getItems();
        } else {
            arrayList = new ArrayList<>();
        }
        ArrayList<MediaItem> arrayList2 = arrayList;
        this.mDirection = Direction.values()[i2 % Direction.values().length];
        if (arrayList2.size() >= 2) {
            SimpleThreadPool.getInstance().execute(new d((Object) this, (Object) arrayList2, (Object) view, (Object) irregularView, 18));
        }
    }

    public void updateFgViewTopPadding(View view, int i2) {
        ViewMarginUtils.setTopPadding(view.findViewById(R.id.gradient_view), -i2);
    }
}
