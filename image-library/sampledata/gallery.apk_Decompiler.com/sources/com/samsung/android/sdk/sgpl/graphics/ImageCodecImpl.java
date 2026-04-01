package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Gainmap;
import android.graphics.Rect;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ImageCodecImpl {
    protected final String TAG = getClass().getSimpleName();

    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(bArr, i2, i7, updateInBitmapIfPresent(options));
    }

    public Bitmap decodeDngThumbnail(String str, int i2) {
        return null;
    }

    public Bitmap decodeEmbeddedThumbnail(byte[] bArr, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        int max = Math.max(options.outWidth, options.outHeight);
        while (true) {
            max >>= 1;
            if (max < i2) {
                break;
            }
            options.inSampleSize <<= 1;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        if (!(decodeByteArray == null || Build.VERSION.SDK_INT < 34 || decodeByteArray.getGainmap() == null)) {
            decodeByteArray.setGainmap((Gainmap) null);
        }
        return decodeByteArray;
    }

    public Bitmap decodeFile(String str, BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(str, updateInBitmapIfPresent(options));
    }

    public Bitmap decodeHeifThumbnail(String str, int i2) {
        return decodeThumbnail(str, i2);
    }

    public Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(inputStream, (Rect) null, updateInBitmapIfPresent(options));
    }

    public Bitmap decodeThumbnail(String str, int i2) {
        try {
            byte[] thumbnail = new ExifInterface(str).getThumbnail();
            if (thumbnail == null || thumbnail.length <= 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.length, new BitmapFactory.Options());
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeThumbnail failed. e=")));
            return null;
        }
    }

    public boolean isHeif(BitmapFactory.Options options) {
        if ("image/heif".equals(options.outMimeType) || "image/heic".equals(options.outMimeType)) {
            return true;
        }
        return false;
    }

    public String parseDngVersion(String str) {
        return "";
    }

    public BitmapFactory.Options updateInBitmapIfPresent(BitmapFactory.Options options) {
        if (options.inBitmap != null && isHeif(options)) {
            BitmapToolkit.putBitmap(options.inBitmap);
            options.inBitmap = null;
        }
        return options;
    }

    public Bitmap decodeDngThumbnail(byte[] bArr, int i2, int i7, int i8) {
        return null;
    }

    public Bitmap decodeHeifThumbnail(byte[] bArr, int i2, int i7, int i8) {
        return decodeThumbnail(bArr, i2, i7, i8);
    }

    public String parseDngVersion(byte[] bArr, int i2, int i7) {
        return "";
    }

    public Bitmap decodeThumbnail(byte[] bArr, int i2, int i7, int i8) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i7);
            byte[] thumbnailBytes = new ExifInterface((InputStream) byteArrayInputStream).getThumbnailBytes();
            if (thumbnailBytes == null || thumbnailBytes.length <= 0) {
                byteArrayInputStream.close();
                return null;
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(thumbnailBytes, 0, thumbnailBytes.length, new BitmapFactory.Options());
            byteArrayInputStream.close();
            return decodeByteArray;
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeThumbnail(byte) failed e=")));
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
