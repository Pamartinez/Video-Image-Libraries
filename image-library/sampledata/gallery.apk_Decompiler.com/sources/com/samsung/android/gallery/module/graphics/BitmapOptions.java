package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapOptions extends BitmapFactory.Options {
    public int inComplexHash;
    public boolean inPreferredAndroidCodec;
    public boolean inPreferredHdr;
    public boolean inPreferredHeifCodec;
    public boolean inPreferredJavaHeap;
    public boolean inPreferredQuramCodec;
    public int outCodecReason;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FeatureHolder {
        static final boolean SUPPORT_PHOTO_HDR = SuperHdrConfig.SUPPORT;
    }

    public BitmapOptions() {
        this.inPreferredQuramCodec = true;
        this.inJustDecodeBounds = false;
        this.inSampleSize = 1;
        this.inMutable = true;
        this.inPreferredConfig = Bitmap.Config.ARGB_8888;
    }

    public static int computeInSampleSize(int i2, int i7, int i8, int i10) {
        int min;
        int i11 = 1;
        while ((Math.max(i2, i7) / 2) / i11 > i8) {
            i11 *= 2;
        }
        if (i11 > 1 && i10 > 0 && (min = Math.min(i2, i7) / i11) > 0 && min < i10) {
            while (min < i10 && i11 > 1) {
                min *= 2;
                i11 /= 2;
            }
        }
        return i11;
    }

    public static int computeInSampleSizeLargerThan(int i2, int i7, int i8) {
        int min = Math.min(i2, i7) / 2;
        int i10 = 1;
        while (min / i10 > i8) {
            i10 *= 2;
        }
        return i10;
    }

    public static int computeInSampleSizeLowerBound(int i2, int i7, int i8) {
        return computeInSampleSize(i2, i7, i8, 32);
    }

    public static int computeInSampleSizeSmallerThan(int i2, int i7, int i8) {
        int max = Math.max(i2, i7);
        int i10 = 1;
        while (max > i8) {
            max >>>= 1;
            i10 <<= 1;
        }
        return i10;
    }

    private boolean isHeif() {
        if ("image/heic".equals(this.outMimeType) || "image/heif".equals(this.outMimeType)) {
            return true;
        }
        return false;
    }

    public BitmapOptions adjustInSampleSize(int i2) {
        return adjustInSampleSize(i2, 32);
    }

    public BitmapOptions adjustInSampleSizeLargerThan(int i2) {
        int i7;
        int i8;
        if (i2 > 0 && (i7 = this.outWidth) > 0 && (i8 = this.outHeight) > 0) {
            this.inSampleSize = computeInSampleSizeLargerThan(i7, i8, i2);
        }
        return this;
    }

    public BitmapOptions adjustInSampleSizeSmallerThan(int i2) {
        int i7;
        int i8;
        if (i2 > 0 && (i7 = this.outWidth) > 0 && (i8 = this.outHeight) > 0) {
            this.inSampleSize = computeInSampleSizeSmallerThan(i7, i8, i2);
        }
        return this;
    }

    public BitmapOptions parse(byte[] bArr, int i2, int i7) {
        this.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i2, i7, this);
        this.inJustDecodeBounds = false;
        this.inSampleSize = 1;
        this.inMutable = true;
        this.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return this;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        StringBuilder sb2 = new StringBuilder("BitmapOptions{");
        if (this.inPreferredAndroidCodec) {
            str = "A";
        } else {
            str = "a";
        }
        sb2.append(str);
        if (this.inPreferredQuramCodec) {
            str2 = "Q";
        } else {
            str2 = "q";
        }
        sb2.append(str2);
        String str9 = "h";
        if (this.inPreferredHeifCodec) {
            str3 = "H";
        } else {
            str3 = str9;
        }
        sb2.append(str3);
        if (this.inPreferredJavaHeap) {
            str4 = "M";
        } else {
            str4 = "F";
        }
        sb2.append(str4);
        if (this.inJustDecodeBounds) {
            str5 = "J";
        } else {
            str5 = "j";
        }
        sb2.append(str5);
        if (this.inBitmap != null) {
            str6 = "B";
        } else {
            str6 = "b";
        }
        sb2.append(str6);
        if (FeatureHolder.SUPPORT_PHOTO_HDR) {
            if (this.semInApplyPhotoHdr) {
                str9 = "H";
            }
            if (this.semInCreateGainmap) {
                str8 = "G";
            } else {
                str8 = "g";
            }
            str7 = str9.concat(str8);
        } else {
            str7 = "";
        }
        sb2.append(str7);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.outMimeType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.outWidth);
        sb2.append("x");
        sb2.append(this.outHeight);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.inSampleSize);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.outCodecReason, "}");
    }

    public BitmapOptions withAndroidCodec(boolean z) {
        this.inPreferredAndroidCodec = z;
        return this;
    }

    public BitmapOptions withComplexHash(int i2) {
        this.inComplexHash = i2;
        return this;
    }

    public BitmapOptions withHdr(boolean z, boolean z3) {
        boolean z7;
        this.inPreferredHdr = z;
        if (FeatureHolder.SUPPORT_PHOTO_HDR) {
            this.semInApplyPhotoHdr = z;
            if (!z || !z3) {
                z7 = false;
            } else {
                z7 = true;
            }
            this.semInCreateGainmap = z7;
        }
        return this;
    }

    public BitmapOptions withHeap(long j2) {
        this.inPreferredJavaHeap = j2 > 0 && j2 < 5242880;
        return this;
    }

    public BitmapOptions withHeifCodec(boolean z) {
        this.inPreferredHeifCodec = z;
        return this;
    }

    public BitmapOptions withQuramCodec(boolean z) {
        this.inPreferredQuramCodec = z;
        return this;
    }

    public BitmapOptions adjustInSampleSize(int i2, int i7) {
        int i8;
        int i10;
        if (i2 <= 0 || (i8 = this.outWidth) <= 0 || (i10 = this.outHeight) <= 0) {
            this.inSampleSize = 1;
            return this;
        }
        this.inSampleSize = computeInSampleSize(i8, i10, i2, i7);
        return this;
    }

    public BitmapOptions withHeap(boolean z) {
        this.inPreferredJavaHeap = z;
        return this;
    }

    public static int computeInSampleSize(int i2, int i7, int i8) {
        return computeInSampleSize(i2, i7, i8, 0);
    }

    public BitmapOptions(String str) {
        this();
        this.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, this);
        this.inJustDecodeBounds = false;
        this.inPreferredHeifCodec = isHeif();
    }

    public BitmapOptions(byte[] bArr, int i2, int i7) {
        this();
        this.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i2, i7, this);
        this.inJustDecodeBounds = false;
        this.inPreferredHeifCodec = isHeif();
    }

    public BitmapOptions(BitmapOptions bitmapOptions) {
        this(bitmapOptions.outWidth, bitmapOptions.outHeight, bitmapOptions.outMimeType);
        this.inSampleSize = bitmapOptions.inSampleSize;
    }

    public BitmapOptions(int i2, int i7, String str) {
        this();
        this.outWidth = i2;
        this.outHeight = i7;
        this.outMimeType = str;
        this.inPreferredHeifCodec = isHeif();
    }
}
