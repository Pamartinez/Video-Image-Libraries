package com.samsung.android.gallery.module.graphics;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BitmapOptionsFactory {
    public static BitmapOptions applyHdr(BitmapOptions bitmapOptions, FileItemInterface fileItemInterface) {
        boolean z;
        if (BitmapOptions.FeatureHolder.SUPPORT_PHOTO_HDR) {
            boolean z3 = false;
            if (!SuperHdrConfig.isEnabled() || !fileItemInterface.isPhotoHdrSupported()) {
                z = false;
            } else {
                z = true;
            }
            if (z && fileItemInterface.isPhotoHdrCandidate()) {
                z3 = true;
            }
            bitmapOptions.withHdr(z, z3);
        }
        return bitmapOptions;
    }

    public static BitmapOptions of(FileItemInterface fileItemInterface) {
        boolean z;
        BitmapOptions bitmapOptions = new BitmapOptions(fileItemInterface.getWidth(), fileItemInterface.getHeight(), fileItemInterface.getMimeType());
        if (fileItemInterface.getFileSize() < 5242880) {
            z = true;
        } else {
            z = false;
        }
        return bitmapOptions.withHeap(z).withQuramCodec(fileItemInterface.isQuramDecodable()).withComplexHash(fileItemInterface.getComplexHashCode());
    }

    public static BitmapOptions parse(FileItemInterface fileItemInterface, boolean z) {
        BitmapOptions parse = parse(fileItemInterface.getPath());
        if (z) {
            applyHdr(parse, fileItemInterface);
        }
        return parse;
    }

    public static BitmapOptions parse(byte[] bArr, int i2, int i7) {
        BitmapOptions bitmapOptions = new BitmapOptions(bArr, i2, i7);
        if (CodecCompat.PATCH_HEIF_STREAM_FATAL && !CodecCompat.ensureIntegrity(bArr, i2)) {
            bitmapOptions.withAndroidCodec(true);
        }
        return bitmapOptions;
    }

    public static BitmapOptions parse(String str) {
        return new BitmapOptions(str);
    }

    public static BitmapOptions parse(String str, boolean z) {
        if (z) {
            return new BitmapOptions(str);
        }
        throw new AssertionError("use BitmapOptions(String path)");
    }
}
