package vizinsight.atl.gallery_scan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.ExifInterface;
import android.util.Log;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Utils {
    public static Bitmap YUV2Bitmap(byte[] bArr, int i2, int i7, int i8) {
        int i10 = i2;
        int i11 = i7;
        YuvImage yuvImage = new YuvImage(bArr, 17, i10, i11, (int[]) null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i10, i11), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return rotateBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length), i8);
    }

    public static Bitmap convert_argmax_mask_buffer_to_bitmap(byte[] bArr, int i2, int i7) {
        int[] iArr = new int[bArr.length];
        byte b = 0;
        for (byte b5 : bArr) {
            if (b <= b5) {
                b = b5;
            }
        }
        Log.v("CD Debug:", " " + b);
        if (b == 0) {
            b = 1;
        }
        int i8 = ScoverState.TYPE_NFC_SMART_COVER / b;
        for (int i10 = 0; i10 < bArr.length; i10++) {
            byte b8 = bArr[i10];
            if (b8 >= 1) {
                int i11 = b8 * i8;
                iArr[i10] = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, i11, i11, i11);
            }
            if (b8 < 1) {
                int i12 = b8 * i8;
                iArr[i10] = Color.argb(0, i12, i12, i12);
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i2, i7, Bitmap.Config.ARGB_8888);
        return Bitmap.createScaledBitmap(createBitmap, createBitmap.getWidth(), createBitmap.getHeight(), true);
    }

    public static Bitmap convert_mask_buffer_to_bitmap(byte[] bArr, int i2, int i7) {
        int[] iArr = new int[bArr.length];
        for (int i8 = 0; i8 < bArr.length; i8++) {
            byte b = bArr[i8];
            iArr[i8] = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, b, b, b);
            if (b < 128) {
                iArr[i8] = Color.argb(0, b, b, b);
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i2, i7, Bitmap.Config.ARGB_8888);
        return Bitmap.createScaledBitmap(createBitmap, createBitmap.getWidth(), createBitmap.getHeight(), true);
    }

    public static Bitmap get_exif_corrected_bitmap(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            Matrix matrix = new Matrix();
            if (attributeInt == 3) {
                matrix.preRotate(180.0f);
            } else if (attributeInt == 6) {
                matrix.preRotate(90.0f);
            } else if (attributeInt == 8) {
                matrix.preRotate(270.0f);
            }
            return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, false);
        } catch (Exception e) {
            e.printStackTrace();
            return decodeFile;
        }
    }

    public static Bitmap get_resized_bitmap(Bitmap bitmap, int i2, int i7) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i2) / ((float) width), ((float) i7) / ((float) height));
        Bitmap bitmap2 = bitmap;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix, false);
        bitmap2.recycle();
        return createBitmap;
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, int i2) {
        Matrix matrix = new Matrix();
        if (i2 == 3) {
            matrix.preRotate(180.0f);
        } else if (i2 == 6) {
            matrix.preRotate(90.0f);
        } else if (i2 == 8) {
            matrix.preRotate(270.0f);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    public static void writeToFile(String str, Context context, String str2) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(str2, 0));
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
