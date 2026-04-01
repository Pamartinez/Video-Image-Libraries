package androidx.media3.datasource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.ParserException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BitmapUtil {
    public static Bitmap decode(byte[] bArr, int i2, BitmapFactory.Options options, int i7) {
        Throwable th;
        if (i7 != -1) {
            if (options == null) {
                options = new BitmapFactory.Options();
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, i2, options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = 1;
            for (int max = Math.max(options.outWidth, options.outHeight); max > i7; max /= 2) {
                options.inSampleSize *= 2;
            }
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, i2, options);
        if (options != null) {
            options.inSampleSize = 1;
        }
        if (decodeByteArray != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                ExifInterface exifInterface = new ExifInterface((InputStream) byteArrayInputStream);
                byteArrayInputStream.close();
                int rotationDegrees = exifInterface.getRotationDegrees();
                if (rotationDegrees == 0) {
                    return decodeByteArray;
                }
                Matrix matrix = new Matrix();
                matrix.postRotate((float) rotationDegrees);
                return Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, false);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            throw ParserException.createForMalformedContainer("Could not decode image data", new IllegalStateException());
        }
        throw th;
    }
}
