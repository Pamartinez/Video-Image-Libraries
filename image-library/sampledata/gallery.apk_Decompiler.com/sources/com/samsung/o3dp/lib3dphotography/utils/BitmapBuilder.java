package com.samsung.o3dp.lib3dphotography.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapBuilder {
    private static final int MAX_PIXEL = 24000000;
    private static final String TAG = "BitmapBuilder";
    private Context context;
    private boolean enableScale = true;
    private boolean mutable = true;
    private Uri uri;

    private BitmapBuilder() {
    }

    public static BitmapBuilder from(Context context2, Uri uri2) {
        BitmapBuilder bitmapBuilder = new BitmapBuilder();
        bitmapBuilder.context = context2;
        bitmapBuilder.uri = uri2;
        return bitmapBuilder;
    }

    public static Bitmap getBitmapFromIntent(Activity activity) {
        Intent intent = activity.getIntent();
        if (!(intent == null || intent.getData() == null)) {
            try {
                return loadImage(activity, intent.getData());
            } catch (IOException e) {
                LogUtil.e(TAG, "getBitmapFromIntent: " + e);
            }
        }
        return null;
    }

    private long getPixelCount(Bitmap bitmap) {
        return ((long) bitmap.getWidth()) * ((long) bitmap.getHeight());
    }

    private float getScaleFactor(Bitmap bitmap) {
        return (float) Math.sqrt(2.4E7d / ((double) getPixelCount(bitmap)));
    }

    private boolean isScaleRequired(Bitmap bitmap) {
        if (getPixelCount(bitmap) <= 24000000 || !this.enableScale) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$retreiveBitmap$0(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.setMutableRequired(this.mutable);
    }

    public static Bitmap loadImage(Context context2, Uri uri2) {
        return from(context2, uri2).withMutable(true).withEnableScale(true).build();
    }

    private Bitmap retreiveBitmap() {
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.context.getContentResolver(), this.uri), new a(this));
    }

    private Bitmap scale(Bitmap bitmap) {
        return ImageUtil.scaleBitmap(bitmap, getScaleFactor(bitmap));
    }

    public Bitmap build() {
        Bitmap retreiveBitmap = retreiveBitmap();
        if (isScaleRequired(retreiveBitmap)) {
            return scale(retreiveBitmap);
        }
        return retreiveBitmap;
    }

    public BitmapBuilder withEnableScale(boolean z) {
        this.enableScale = z;
        return this;
    }

    public BitmapBuilder withMutable(boolean z) {
        this.mutable = z;
        return this;
    }
}
