package com.samsung.android.gallery.compat.qrencoder;

import android.graphics.Bitmap;
import com.samsung.android.gallery.compat.qrencoder.common.BitMatrix;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class QRCodeWriter {
    public static Bitmap createBitmap(BitMatrix bitMatrix) {
        int i2;
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[(width * height)];
        for (int i7 = 0; i7 < height; i7++) {
            int i8 = i7 * width;
            for (int i10 = 0; i10 < width; i10++) {
                int i11 = i8 + i10;
                if (bitMatrix.get(i10, i7)) {
                    i2 = -16777216;
                } else {
                    i2 = -1;
                }
                iArr[i11] = i2;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    private static BitMatrix renderResult(QRCode qRCode, int i2, int i7, int i8) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix != null) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int i10 = i8 * 2;
            int i11 = width + i10;
            int i12 = i10 + height;
            int max = Math.max(i2, i11);
            int max2 = Math.max(i7, i12);
            int min = Math.min(max / i11, max2 / i12);
            int i13 = (max - (width * min)) / 2;
            int i14 = (max2 - (height * min)) / 2;
            BitMatrix bitMatrix = new BitMatrix(max, max2);
            int i15 = 0;
            while (i15 < height) {
                int i16 = 0;
                int i17 = i13;
                while (i16 < width) {
                    if (matrix.get(i16, i15) == 1) {
                        bitMatrix.setRegion(i17, i14, min, min);
                    }
                    i16++;
                    i17 += min;
                }
                i15++;
                i14 += min;
            }
            return bitMatrix;
        }
        throw new IllegalStateException();
    }

    public Bitmap encode(String str, int i2, int i7) {
        return createBitmap(encode(str, i2, i7, (Map<EncodeHintType, ?>) null));
    }

    public BitMatrix encode(String str, int i2, int i7, Map<EncodeHintType, ?> map) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (i2 < 0 || i7 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i2 + 'x' + i7);
        } else {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int i8 = 4;
            if (map != null) {
                EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
                if (map.containsKey(encodeHintType)) {
                    errorCorrectionLevel = ErrorCorrectionLevel.valueOf(map.get(encodeHintType).toString());
                }
                EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
                if (map.containsKey(encodeHintType2)) {
                    i8 = Integer.parseInt(map.get(encodeHintType2).toString());
                }
            }
            return renderResult(Encoder.encode(str, errorCorrectionLevel, map), i2, i7, i8);
        }
    }
}
