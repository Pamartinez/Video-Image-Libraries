package srib.vizinsight.dvs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.ExifInterface;
import com.samsung.android.ocr.MOCRLang;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageUtils {
    public static Bitmap changeBGColor(Bitmap bitmap) {
        Bitmap.Config config;
        if (bitmap == null || bitmap.getConfig() != (config = Bitmap.Config.ARGB_8888)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return createBitmap;
    }

    public static void dumpTransparentPNG(Bitmap bitmap, String str) {
        Bitmap.Config config;
        if (bitmap != null && bitmap.getConfig() == (config = Bitmap.Config.ARGB_8888)) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
                createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                createBitmap.recycle();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap readBitmap(String str) {
        IOException iOException;
        Bitmap bitmap = null;
        try {
            int i2 = 0;
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (attributeInt == 3) {
                i2 = MOCRLang.KHMER;
            } else if (attributeInt == 6) {
                i2 = 90;
            } else if (attributeInt == 8) {
                i2 = 270;
            }
            if (i2 == 0) {
                return decodeFile;
            }
            try {
                Matrix matrix = new Matrix();
                matrix.postRotate((float) i2);
                return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
            } catch (IOException e) {
                iOException = e;
                bitmap = decodeFile;
                iOException.printStackTrace();
                return bitmap;
            }
        } catch (IOException e7) {
            iOException = e7;
            iOException.printStackTrace();
            return bitmap;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i2) {
        int i7;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > i2 || height > i2) {
            if (width >= height) {
                i7 = (int) ((((float) height) / ((float) width)) * ((float) i2));
            } else {
                int i8 = (int) ((((float) width) / ((float) height)) * ((float) i2));
                i7 = i2;
                i2 = i8;
            }
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i7, true);
            if (createScaledBitmap != bitmap) {
                bitmap.recycle();
                return createScaledBitmap;
            }
        }
        return bitmap;
    }
}
