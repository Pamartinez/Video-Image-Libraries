package vizinsight.atl.gallery_scan;

import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.sdk.cover.ScoverState;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VZImageDecoder {
    public static final int IMAGE_FORMAT_ARGB8888 = 1;
    public static final int IMAGE_FORMAT_RGBA8888 = 2;
    public static final int IMAGE_FORMAT_YUV420 = 0;

    public static byte[] decodeImage(Bitmap bitmap, int i2) {
        Log.i("BIPLAB_DEBUG", "Decoding Image ...");
        if (i2 == 1) {
            return getARGB(bitmap.getWidth(), bitmap.getHeight(), bitmap);
        }
        if (i2 == 0) {
            return getNV21(bitmap.getWidth(), bitmap.getHeight(), bitmap);
        }
        if (i2 == 2) {
            return getRGBA(bitmap.getWidth(), bitmap.getHeight(), bitmap);
        }
        Log.i("BIPLAB_DEBUG", "Decoding Image completed sucessfully.");
        return null;
    }

    public static void encodeYUV420SP(byte[] bArr, int[] iArr, int i2, int i7) {
        int i8 = i2;
        int i10 = i7;
        int i11 = i8 * i10;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i10; i14++) {
            int i15 = 0;
            while (i15 < i8) {
                int i16 = iArr[i13];
                int i17 = (16711680 & i16) >> 16;
                int i18 = (65280 & i16) >> 8;
                int i19 = ScoverState.TYPE_NFC_SMART_COVER;
                int i20 = i16 & ScoverState.TYPE_NFC_SMART_COVER;
                int i21 = ((((i20 * 25) + ((i18 * 129) + (i17 * 66))) + 128) >> 8) + 16;
                int i22 = ((((i20 * 112) + ((i17 * -38) - (i18 * 74))) + 128) >> 8) + 128;
                int i23 = (((((i17 * 112) - (i18 * 94)) - (i20 * 18)) + 128) >> 8) + 128;
                int i24 = i12 + 1;
                if (i21 < 0) {
                    i21 = 0;
                } else if (i21 > 255) {
                    i21 = 255;
                }
                bArr[i12] = (byte) i21;
                if (i14 % 2 == 0 && i13 % 2 == 0) {
                    int i25 = i11 + 1;
                    if (i23 < 0) {
                        i23 = 0;
                    } else if (i23 > 255) {
                        i23 = 255;
                    }
                    bArr[i11] = (byte) i23;
                    i11 += 2;
                    if (i22 < 0) {
                        i19 = 0;
                    } else if (i22 <= 255) {
                        i19 = i22;
                    }
                    bArr[i25] = (byte) i19;
                }
                i13++;
                i15++;
                i12 = i24;
            }
        }
    }

    public static byte[] getARGB(int i2, int i7, Bitmap bitmap) {
        int i8 = i2 * i7;
        int[] iArr = new int[i8];
        Bitmap bitmap2 = bitmap;
        bitmap2.getPixels(iArr, 0, i2, 0, 0, i2, i7);
        ByteBuffer allocate = ByteBuffer.allocate(i8 * 4);
        allocate.asIntBuffer().put(iArr);
        return allocate.array();
    }

    public static byte[] getNV21(int i2, int i7, Bitmap bitmap) {
        int i8 = i2 * i7;
        int[] iArr = new int[i8];
        int i10 = i2;
        int i11 = i7;
        bitmap.getPixels(iArr, 0, i10, 0, 0, i2, i11);
        byte[] bArr = new byte[((i8 * 3) / 2)];
        encodeYUV420SP(bArr, iArr, i10, i11);
        return bArr;
    }

    public static byte[] getRGBA(int i2, int i7, Bitmap bitmap) {
        int[] iArr = new int[(i2 * i7)];
        byte[] bArr = new byte[(i2 * 4 * i7)];
        int i8 = i2;
        int i10 = i7;
        bitmap.getPixels(iArr, 0, i8, 0, 0, i2, i10);
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < i10; i13++) {
            for (int i14 = 0; i14 < i8; i14++) {
                int i15 = iArr[i12];
                int i16 = (-16777216 & i15) >> 24;
                int i17 = (16711680 & i15) >> 16;
                int i18 = (65280 & i15) >> 8;
                int i19 = i15 & ScoverState.TYPE_NFC_SMART_COVER;
                int i20 = i11 + 1;
                if (i17 < 0) {
                    i17 = 0;
                } else if (i17 > 255) {
                    i17 = 255;
                }
                bArr[i11] = (byte) i17;
                int i21 = i11 + 2;
                if (i18 < 0) {
                    i18 = 0;
                } else if (i18 > 255) {
                    i18 = 255;
                }
                bArr[i20] = (byte) i18;
                int i22 = i11 + 3;
                if (i19 < 0) {
                    i19 = 0;
                } else if (i19 > 255) {
                    i19 = 255;
                }
                bArr[i21] = (byte) i19;
                i11 += 4;
                if (i16 < 0) {
                    i16 = 0;
                } else if (i16 > 255) {
                    i16 = 255;
                }
                bArr[i22] = (byte) i16;
                i12++;
            }
        }
        return bArr;
    }
}
