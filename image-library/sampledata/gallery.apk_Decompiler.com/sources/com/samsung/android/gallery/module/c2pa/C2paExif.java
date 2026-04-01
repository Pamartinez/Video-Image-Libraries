package com.samsung.android.gallery.module.c2pa;

import Fa.C0571z;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.scs.ai.visual.c2pa.Exif;
import i.C0212a;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paExif {
    final Map<Exif, String> mExifMap;

    public C2paExif(Map<Exif, String> map) {
        this.mExifMap = map;
    }

    private String convertFocalLength(Double d) {
        if (d != null) {
            return NumberFormat.getInstance(Locale.getDefault()).format((double) MathUtils.roundToDecimalPlace(d.doubleValue(), 3.0d));
        }
        return null;
    }

    private String getAperture() {
        try {
            return String.format(Locale.getDefault(), "F%.1f", new Object[]{Float.valueOf(UnsafeCast.toFloat(this.mExifMap.get(Exif.EXIF_FNUMBER), 4.8f))});
        } catch (Exception unused) {
            return null;
        }
    }

    private String getContentSize() {
        String str = this.mExifMap.get(Exif.TIFF_ORIENTATION);
        String str2 = this.mExifMap.get(Exif.TIFF_IMAGE_WIDTH);
        String str3 = this.mExifMap.get(Exif.TIFF_IMAGE_LENGTH);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return null;
        }
        if (str == null || "0".equals(str) || "180".equals(str)) {
            return C0212a.B(str2, "x", str3);
        }
        return C0212a.B(str3, "x", str2);
    }

    private String getEv() {
        try {
            return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(Double.parseDouble(this.mExifMap.get(Exif.EXIF_EXPOSURE_BIAS_VALUE)))});
        } catch (Exception unused) {
            return null;
        }
    }

    private String getExposureTime() {
        return ExifTag.toReciprocal(this.mExifMap.get(Exif.EXIF_EXPOSURE_TIME));
    }

    private String getFlash() {
        if ("1".equals(this.mExifMap.get(Exif.EXIF_FLASH))) {
            return AppResources.getString(R$string.flash_on);
        }
        return null;
    }

    private String getFocalLength() {
        String str = this.mExifMap.get(Exif.EXIF_FOCAL_LENGTH_IN_35MM_FILM);
        if (TextUtils.isEmpty(str)) {
            try {
                String str2 = this.mExifMap.get(Exif.EXIF_FOCAL_LENGTH);
                if (!TextUtils.isEmpty(str2)) {
                    str = convertFocalLength(Double.valueOf(Double.parseDouble(str2)));
                }
            } catch (Exception unused) {
                return null;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder s = C0212a.s(str);
        s.append(AppResources.getString(R$string.unit_mm));
        return s.toString();
    }

    private String getIso() {
        String str = this.mExifMap.get(Exif.EXIFEX_PHOTOGRAPHIC_SENSITIVITY);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return AppResources.getString(R$string.iso) + " " + str;
    }

    private String getPixelSize() {
        try {
            int round = Math.round(((float) (((long) Integer.parseInt(this.mExifMap.get(Exif.TIFF_IMAGE_WIDTH))) * ((long) Integer.parseInt(this.mExifMap.get(Exif.TIFF_IMAGE_LENGTH))))) / 1000000.0f);
            if (round > 0) {
                return StringCompat.toReadableNumber((long) round) + AppResources.getString(R$string.mega_pixel);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getInfo$0(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getInfo$1(String str) {
        return !TextUtils.isEmpty(str);
    }

    public String getInfo() {
        String str = (String) Stream.of(new String[]{getContentSize(), getPixelSize()}).filter(new C0571z(24)).collect(Collectors.joining(" | "));
        String str2 = (String) Stream.of(new String[]{getIso(), getFocalLength(), getEv(), getAperture(), getExposureTime(), getFlash()}).filter(new C0571z(25)).collect(Collectors.joining(" | "));
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return C0212a.B(str, "\n", str2);
    }
}
