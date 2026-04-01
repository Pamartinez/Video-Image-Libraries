package com.samsung.android.gallery.module.thumbnail;

import G9.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.AppResources;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IconResources {
    private static final ConcurrentHashMap<Integer, Drawable> sIconDrawable = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Drawable> sReplacedIconDrawable = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IconRatioHolder {
        private static final HashSet<Integer> ALBUM_CENTER_ICON_RESOURCE_ID_SET = new HashSet<>(Arrays.asList(new Integer[]{Integer.valueOf(R$drawable.gallery_ic_album_no_pic), Integer.valueOf(R$drawable.gallery_ic_album_mtp)}));
        private static final HashSet<Integer> BROKEN_RESOURCE_ID_SET = new HashSet<>(Arrays.asList(new Integer[]{Integer.valueOf(R$drawable.gallery_ic_timeview_error), Integer.valueOf(R$drawable.gallery_ic_timeview_cloud_only), Integer.valueOf(R$drawable.gallery_ic_timeview_drm_video), Integer.valueOf(R$drawable.gallery_ic_timeview_drm_image)}));

        public static int computeIconSize(int i2, int i7) {
            float f;
            float f5;
            if (ALBUM_CENTER_ICON_RESOURCE_ID_SET.contains(Integer.valueOf(i2))) {
                f = (float) i7;
                f5 = 0.38317758f;
            } else if (!BROKEN_RESOURCE_ID_SET.contains(Integer.valueOf(i2))) {
                return i7;
            } else {
                f = (float) i7;
                f5 = 0.22497188f;
            }
            return (int) (f * f5);
        }
    }

    public static void clear() {
        sIconDrawable.clear();
        sReplacedIconDrawable.clear();
    }

    public static Bitmap createBrokenIconBitmapLegacy(Context context, int i2, int i7, int i8) {
        if (context == null) {
            return null;
        }
        int color = context.getColor(i7);
        Bitmap createBitmap = BitmapUtils.createBitmap(i8, i8, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(color);
        Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(context.getDrawable(i2));
        canvas.drawBitmap(bitmapFromDrawable, (float) ((i8 - bitmapFromDrawable.getWidth()) >> 1), (float) ((i8 - bitmapFromDrawable.getHeight()) >> 1), (Paint) null);
        return BitmapUtils.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), (Matrix) null, true);
    }

    public static Bitmap createColorBitmap(int i2, int i7) {
        return createColorBitmap(AppResources.getAppContext(), i2, i7, i7);
    }

    public static Bitmap createIconBitmap(int i2, int i7) {
        return createIconBitmap(i2, i7, ThumbKind.MEDIUM_KIND.size());
    }

    public static Drawable getIconDrawable(Context context, int i2) {
        if (context != null) {
            return sIconDrawable.computeIfAbsent(Integer.valueOf(i2), new a(context, 5));
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Drawable lambda$getIconDrawable$0(Context context, Bitmap bitmap, String str) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static Bitmap createColorBitmap(Context context, int i2, int i7, int i8) {
        int color = context != null ? context.getColor(i2) : -12303292;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap createBitmap = Bitmap.createBitmap(i7, i8, config);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0.0f, 0.0f, (float) i7, (float) i8, paint);
        return createBitmap.copy(config, false);
    }

    public static Bitmap createIconBitmap(int i2, int i7, int i8) {
        return createIconBitmap(i2, i7, i8, 0);
    }

    public static Drawable getIconDrawable(Context context, Bitmap bitmap, String str) {
        if (context != null) {
            return sReplacedIconDrawable.computeIfAbsent(str, new Qa.a(context, (Object) bitmap, 10));
        }
        return null;
    }

    public static Bitmap createIconBitmap(int i2, int i7, int i8, int i10) {
        Context appContext = AppResources.getAppContext();
        Drawable drawable = (appContext == null || i2 == -1) ? null : appContext.getDrawable(i2);
        if (drawable == null) {
            return createColorBitmap(appContext, i7, i8, i8);
        }
        int color = appContext.getColor(i7);
        int i11 = (i10 * 2) + i8;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap createBitmap = Bitmap.createBitmap(i11, i11, config);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(color);
        int computeIconSize = IconRatioHolder.computeIconSize(i2, i8);
        if (i10 == 0) {
            i10 = (i8 - computeIconSize) >> 1;
        }
        int i12 = computeIconSize + i10;
        drawable.setBounds(i10, i10, i12, i12);
        drawable.draw(canvas);
        return createBitmap.copy(config, false);
    }
}
