package com.samsung.android.gallery.module.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.provider.MediaStore;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FaceDecoder {
    private static Bitmap decodeFaceByCropBitmap(RectF rectF, String str) {
        Bitmap decodeFile = ImageDecoder.decodeFile(str, new BitmapOptions());
        if (decodeFile == null) {
            return null;
        }
        return BitmapUtils.crop(decodeFile, RectUtils.getSmartCropRectWithLimit(rectF, decodeFile.getWidth(), decodeFile.getHeight()));
    }

    private static Bitmap decodeFaceByRegionDecoder(RectF rectF, String str) {
        ImageRegionDecoder create = ImageRegionDecoderFactory.create(str, false);
        if (create == null) {
            return null;
        }
        return create.decodeRegion(RectUtils.getSmartCropRectWithLimit(rectF, create.getWidth(), create.getHeight()), new BitmapOptions());
    }

    public static Bitmap getContactFaceBitmap(Context context, String str) {
        Uri parse;
        if (!(str == null || (parse = Uri.parse(str)) == null)) {
            try {
                return MediaStore.Images.Media.getBitmap(context.getContentResolver(), parse);
            } catch (IOException unused) {
                Log.d("FaceDecoder", "getContactFaceBitmap failed uri=".concat(str));
            }
        }
        return null;
    }

    public static Bitmap getFaceBitmap(RectF rectF, String str, int i2) {
        Bitmap bitmap;
        if (rectF == null || rectF.isEmpty()) {
            Log.w("FaceDecoder", "getFaceBitmap failed. null rect or empty");
            return null;
        }
        try {
            bitmap = decodeFaceByRegionDecoder(rectF, str);
        } catch (IllegalStateException unused) {
            bitmap = decodeFaceByCropBitmap(rectF, str);
        }
        if (bitmap == null) {
            return null;
        }
        if (i2 != 0) {
            return BitmapUtils.rotateBitmap(bitmap, i2);
        }
        return bitmap;
    }
}
